/**
 * Estrategia de anuncios por defecto que siempre permite el envío de los anuncios
 * a los seguidores, sin ningún filtro o condición adicional.
 * 
 * Esta clase es útil cuando se desea enviar todos los anuncios a todos los seguidores sin aplicar
 * ninguna restricción o estrategia de distribución personalizada.
 * 
 * @author Marco Paparella
 * @author Christian Grosso
 */
package seguimiento;

public class DefaultAnnouncementStrategy implements AnnouncementStrategy {

    /**
     * Siempre devuelve true, permitiendo el envío de cualquier anuncio a todos los seguidores.
     * 
     * @param a el anuncio que se desea enviar
     * @param f el seguidor al que se le enviará el anuncio
     * @return true siempre, indicando que el anuncio debe ser enviado
     */
    @Override
    public boolean shouldSend(Announcement a, Follower f) {
        return true; // invia siempre
    }
}
