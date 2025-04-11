/**
 * Tester para verificar el registro de proyectos en el sistema y su correcta representación.
 * 
 * Este tester realiza las siguientes acciones:
 * - Registra un ciudadano, una asociación y una fundación en el sistema.
 * - Crea dos proyectos: uno propuesto por una asociación y otro por una fundación.
 * - Registra ambos proyectos en el sistema.
 * - Muestra los proyectos registrados en el sistema.
 * 
 * @author Marco Paparella
 * @author Christian Grosso
 */
package testers;

import gestion.Sistema;
import proyectos.*;
import usuarios.*;
import exceptiones.*;

public class TesterA2 {

    public static void main(String[] args) {
        try {
            // Crear un sistema de gestión
            Sistema sistema = new Sistema();

            // Registrar un ciudadano
            sistema.registrarCiudadano("Juan Bravo", "12345678K", "pass1");
            Ciudadano juan = (Ciudadano) sistema.buscarPorNombre("Juan Bravo");

            // Registrar una asociación con el ciudadano como representante
            sistema.registrarAsociacion("conservemos el manzanares", "riverpass", juan);
            Asociacion manzanares = (Asociacion) sistema.buscarPorNombre("conservemos el manzanares");

            // Registrar una fundación
            sistema.registrarFundacion("Fundacion Canal", "A1234567B", "canalpass");
            Fundacion canal = (Fundacion) sistema.buscarPorNombre("Fundacion Canal");

            // Crear proyectos (uno por una asociación y otro por una fundación)
            Proyecto p1 = new Proyecto("Limpieza del manzanares", "Proyecto ambiental", manzanares) {
                @Override
                public String toString() {
                    return "0: " + getTitulo() + ". Proponente: " + getProponente();
                }
            };

            Proyecto p2 = new ProyectoFundacion("Gastemos menos agua", "Campaña de ahorro", canal, 1_000_000, 80);

            // Registrar los proyectos en el sistema
            sistema.registrarProyecto(p1);
            sistema.registrarProyecto(p2);

            // Mostrar los proyectos registrados
            System.out.println("[");
            for (Proyecto p : sistema.getProyectos()) {
                System.out.println(p.toString());
            }
            System.out.println("]");

        } catch (NIFFormatoInvalidoException | CIFFormatoInvalidoException | IdentificadorDuplicadoException |
                 PresupuestoInvalidoException | PorcentajeInvalidoException e) {
            // Manejo de excepciones
            System.err.println("Errore: " + e.getMessage());
        }
    }
}
