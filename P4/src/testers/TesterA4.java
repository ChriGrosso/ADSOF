/**
 * Tester para verificar la funcionalidad de los anuncios distribuidos entre los seguidores de los proyectos y entidades,
 * incluyendo ciudadanos, asociaciones y fundaciones.
 * 
 * Este tester realiza las siguientes acciones:
 * - Registra tres ciudadanos, dos asociaciones y una fundación en el sistema.
 * - Crea proyectos tanto por una asociación como por una fundación.
 * - Los ciudadanos se inscriben en asociaciones, lo que genera anuncios automáticos.
 * - Los proyectos se registran, y los seguidores reciben anuncios sobre dichos proyectos.
 * - Muestra los anuncios recibidos por cada ciudadano.
 * 
 * @author Marco Paparella
 * @author Christian Grosso
 */
package testers;

import gestion.Sistema;
import proyectos.*;
import usuarios.*;
import seguimiento.*;

public class TesterA4 {

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

            // Registro de fundación
            sistema.registrarFundacion("Fundacion Canal", "A1234567B", "canalpass");
            Fundacion canal = (Fundacion) sistema.buscarPorNombre("Fundacion Canal");
            canal.follow(juan); // Juan sigue la fundación

            // Inscripción de ciudadanos en la asociación 'amigos de los pajaros' (esto también lanza un anuncio)
            pajaros.inscribirCiudadano(ana);
            pajaros.inscribirCiudadano(luisa);

            // Creación de proyectos
            Proyecto p1 = new Proyecto("Limpieza del manzanares", "Limpiar el manzanares para recuperar su flora y fauna", manzanares);
            Proyecto p2 = new ProyectoFundacion("Gastemos menos agua", "Mejora de las infraestructuras de distribución y captación de agua", canal, 1_000_000, 80);

            // Registro de proyectos en el sistema
            sistema.registrarProyecto(p1);  // Apoyo automático al registrar el proyecto
            sistema.registrarProyecto(p2);  // Apoyo automático al registrar el proyecto

            // El proyecto manzanares sigue el propio proyecto para redistribuir el anuncio
            p1.follow(manzanares);

            // Mostrar los anuncios recibidos por cada ciudadano
            System.out.println("\nAnuncios para Juan Bravo:\n");
            for (Announcement a : juan.getMensajes()) {
                System.out.println(a);
            }

            System.out.println("\nAnuncios para Ana Lopez:\n");
            for (Announcement a : ana.getMensajes()) {
                System.out.println(a);
            }

            System.out.println("\nAnuncios para Luisa Gomez:\n");
            for (Announcement a : luisa.getMensajes()) {
                System.out.println(a);
            }

        } catch (Exception e) {
            // Manejo de excepciones generales
            System.err.println("Error: " + e.getMessage());
        }
    }
}
