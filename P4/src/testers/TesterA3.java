/**
 * Tester para verificar la funcionalidad de los apoyos a proyectos en el sistema, incluyendo:
 * - Registro de ciudadanos, asociaciones y fundaciones.
 * - Creación de proyectos y el apoyo automático al ser registrados.
 * - Apoyo explícito de un ciudadano a un proyecto.
 * - Obtención de mapas de conteo de apoyos y los ciudadanos que apoyan cada proyecto.
 * 
 * Este tester realiza las siguientes acciones:
 * - Registra tres ciudadanos, dos asociaciones y una fundación.
 * - Crea dos proyectos, uno propuesto por una asociación y otro por una fundación.
 * - Realiza un apoyo explícito de un ciudadano a un proyecto.
 * - Muestra el conteo de apoyos y los ciudadanos que apoyan cada proyecto.
 * 
 * @author Marco Paparella
 * @author Christian Grosso
 */
package testers;

import gestion.Sistema;
import proyectos.*;
import usuarios.*;
import exceptiones.*;

import java.util.*;

public class TesterA3 {

    public static void main(String[] args) {
        try {
            // Crear un sistema de gestión
            Sistema sistema = new Sistema();

            // Registro de ciudadanos
            sistema.registrarCiudadano("Juan Bravo", "12345678K", "pass1");
            Ciudadano juan = (Ciudadano) sistema.buscarPorNombre("Juan Bravo");

            sistema.registrarCiudadano("Ana Lopez", "12345678L", "pass2");
            Ciudadano ana = (Ciudadano) sistema.buscarPorNombre("Ana Lopez");

            sistema.registrarCiudadano("Luisa Gomez", "12345678G", "pass3");
            Ciudadano luisa = (Ciudadano) sistema.buscarPorNombre("Luisa Gomez");

            // Registro de asociaciones
            sistema.registrarAsociacion("amigos de los pajaros", "birdpass", juan);
            Asociacion pajaros = (Asociacion) sistema.buscarPorNombre("amigos de los pajaros");

            sistema.registrarAsociacion("conservemos el manzanares", "riverpass", juan);
            Asociacion manzanares = (Asociacion) sistema.buscarPorNombre("conservemos el manzanares");

            // Agregar la asociación 'amigos de los pajaros' a 'conservemos el manzanares'
            manzanares.agregarAsociacion(pajaros);

            // Inscripción de ciudadanos en la asociación 'amigos de los pajaros'
            pajaros.inscribirCiudadano(ana);
            pajaros.inscribirCiudadano(luisa);

            // Registro de fundación
            sistema.registrarFundacion("Fundacion Canal", "A1234567B", "canalpass");
            Fundacion canal = (Fundacion) sistema.buscarPorNombre("Fundacion Canal");

            // Creación de proyectos
            Proyecto p1 = new Proyecto("Limpieza del manzanares", "Proyecto ambiental", manzanares);
            Proyecto p2 = new ProyectoFundacion("Gastemos menos agua", "Campaña de ahorro", canal, 1_000_000, 80);

            // Registro de proyectos
            sistema.registrarProyecto(p1);  // Apoyo automático al registrar
            sistema.registrarProyecto(p2);  // Apoyo automático al registrar

            // Apoyo explícito de un ciudadano al proyecto
            sistema.apoyarProyecto(juan, p2);

            // Mostrar el conteo de apoyos por proyecto
            System.out.println("\nMAPA CONTEO DE APOYOS:");
            Map<Proyecto, Integer> conteo = sistema.obtenerConteoApoyos();
            for (Map.Entry<Proyecto, Integer> entry : conteo.entrySet()) {
                System.out.println(entry.getKey() + "=" + entry.getValue());
            }

            // Mostrar el mapa de ciudadanos que apoyan cada proyecto
            System.out.println("\nMAPA CIUDADANOS QUE APOYAN:");
            Map<Proyecto, Set<Ciudadano>> apoyantes = sistema.obtenerCiudadanosQueApoyan();
            for (Map.Entry<Proyecto, Set<Ciudadano>> entry : apoyantes.entrySet()) {
                System.out.println(entry.getKey() + "=" + entry.getValue());
            }

        } catch (ApoyoInvalidoException e) {
            // Manejo de excepciones de apoyo inválido
            System.err.println("Error de apoyo: " + e.getMessage());
        } catch (Exception e) {
            // Manejo de cualquier otra excepción
            System.err.println("Error general: " + e.getMessage());
        }
    }
}
