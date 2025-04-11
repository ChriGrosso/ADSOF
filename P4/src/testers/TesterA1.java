/**
 * Tester para verificar el correcto funcionamiento del sistema en cuanto al registro de ciudadanos, asociaciones y fundaciones,
 * así como la inscripción de ciudadanos en asociaciones y la relación entre ellas.
 * 
 * Este tester realiza las siguientes acciones:
 * - Registra tres ciudadanos en el sistema.
 * - Registra dos asociaciones y un representante para una de ellas.
 * - Intenta inscribir a los ciudadanos en una de las asociaciones.
 * - Muestra los usuarios registrados en el sistema.
 * 
 * @author Marco Paparella
 * @author Christian Grosso
 */
package testers;

import gestion.*;
import exceptiones.*;
import usuarios.*;

public class TesterA1 {

    public static void main(String[] args) {
        try {
            // Crear un sistema de gestión
            Sistema sistema = new Sistema();

            // Registrar tres ciudadanos
            sistema.registrarCiudadano("Juan Bravo", "12345678K", "pass1");
            sistema.registrarCiudadano("Ana Lopez", "12345678L", "pass2");
            sistema.registrarCiudadano("Luisa Gomez", "12345678G", "pass3");

            // Buscar los ciudadanos registrados
            Ciudadano juan = (Ciudadano) sistema.buscarPorNombre("Juan Bravo");
            Ciudadano ana = (Ciudadano) sistema.buscarPorNombre("Ana Lopez");
            Ciudadano luisa = (Ciudadano) sistema.buscarPorNombre("Luisa Gomez");

            // Registrar dos asociaciones
            sistema.registrarAsociacion("amigos de los pajaros", "birdpass", juan);
            sistema.registrarAsociacion("conservemos el manzanares", "riverpass", juan);

            // Buscar las asociaciones registradas
            Asociacion pajaros = (Asociacion) sistema.buscarPorNombre("amigos de los pajaros");
            Asociacion manzanares = (Asociacion) sistema.buscarPorNombre("conservemos el manzanares");

            // Agregar una asociación dentro de otra
            manzanares.agregarAsociacion(pajaros);

            // Inscribir ciudadanos en la asociación
            pajaros.inscribirCiudadano(ana);
            pajaros.inscribirCiudadano(luisa);

            // Intentar inscribir un ciudadano ya inscrito (esto generaría una excepción)
            // pajaros.inscribirCiudadano(ana);

            // Registrar una fundación
            sistema.registrarFundacion("Fundacion Canal", "A1234567B", "canalpass");

            // Mostrar los usuarios registrados en el sistema
            System.out.println("[");
            for (Usuario u : sistema.getUsuarios()) {
                System.out.println(u.toString() + ",");
            }
            System.out.println("]");

        } catch (NIFFormatoInvalidoException | CIFFormatoInvalidoException | IdentificadorDuplicadoException |
                 CiudadanoDuplicadoException | AsociacionNoVaciaException | RepresentanteInvalidoException e) {
            // Manejo de excepciones
            System.err.println("Errore: " + e.getMessage());
        }
    }
}
