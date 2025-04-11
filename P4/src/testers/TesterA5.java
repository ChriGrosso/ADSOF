/**
 * Tester para verificar la funcionalidad de las estrategias de distribución de anuncios aplicadas a los seguidores,
 * específicamente utilizando la estrategia "Uno de cada N".
 * 
 * Este tester realiza las siguientes acciones:
 * - Registra un ciudadano y lo inscribe en una asociación.
 * - Aplica la estrategia "Uno de cada N" al seguimiento de la asociación y la fundación por parte de Juan.
 * - Crea proyectos y los registra en el sistema, generando anuncios que Juan recibirá según la estrategia.
 * - Muestra los anuncios que Juan ha recibido.
 * 
 * @author Marco Paparella
 * @author Christian Grosso
 */
package testers;

import gestion.Sistema;
import proyectos.*;
import usuarios.*;
import seguimiento.*;
import exceptiones.*;

public class TesterA5 {

    public static void main(String[] args) {
        try {
            // Crear un sistema de gestión
            Sistema sistema = new Sistema();

            // Registro de un ciudadano
            sistema.registrarCiudadano("Juan Bravo", "12345678K", "pass1");
            Ciudadano juan = (Ciudadano) sistema.buscarPorNombre("Juan Bravo");

            // Registro de una asociación y suscripción de Juan
            sistema.registrarAsociacion("conservemos el manzanares", "passAsoc", juan);
            Asociacion manzanares = (Asociacion) sistema.buscarPorNombre("conservemos el manzanares");

            // Juan sigue la asociación con la estrategia UnoDeCadaN (enviando un anuncio de cada 2)
            manzanares.follow(juan, new UnoDeCadaNStrategy(2));

            // Juan es inscrito automáticamente, se genera el primer anuncio
            // Después de la inscripción, Juan recibirá: "Alta de Juan Bravo..."

            // Registro de un proyecto y su apoyo automático (generando el segundo anuncio)
            Proyecto p1 = new Proyecto("Limpieza del manzanares", 
                                        "Limpiar el manzanares para recuperar su flora y fauna", 
                                        manzanares);
            sistema.registrarProyecto(p1);
            p1.follow(manzanares); // El proyecto sigue la asociación para recibir anuncios
            // El apoyo automático al proyecto genera el segundo anuncio, que Juan recibirá según la estrategia 1/2

            // Registro de una fundación
            sistema.registrarFundacion("Fundacion Canal", "A1234567B", "canalpass");
            Fundacion canal = (Fundacion) sistema.buscarPorNombre("Fundacion Canal");

            // Juan sigue la fundación con la estrategia UnoDeCadaN (enviando un anuncio de cada 2)
            canal.follow(juan, new UnoDeCadaNStrategy(2));

            // Creación de un proyecto de la fundación (tercer anuncio que Juan recibirá)
            Proyecto p2 = new ProyectoFundacion("Gastemos menos agua", 
                                                 "Mejora de las infraestructuras de distribución y captación de agua", 
                                                 canal, 1_000_000, 80);
            sistema.registrarProyecto(p2);

            // Mostrar los anuncios recibidos por Juan
            System.out.println("Anuncios para Juan Bravo:\n");
            for (Announcement a : juan.getMensajes()) {
                System.out.println(a);
            }

        } catch (Exception e) {
            // Manejo de excepciones generales
            System.err.println("Error: " + e.getMessage());
        }
    }
}
