/**
 * Interfaz funcional que define una estrategia para determinar si un anuncio
 * debe ser enviado a un seguidor específico.
 * 
 * Las estrategias permiten personalizar el comportamiento de difusión de anuncios,
 * por ejemplo, filtrando la frecuencia o el tipo de anuncio.
 * 
 * @author Marco Paparella
 * @author Christian Grosso
 */
package seguimiento;

public interface AnnouncementStrategy {

    /**
     * Determina si un anuncio debe ser enviado a un seguidor determinado,
     * en función de la estrategia implementada.
     * 
     * @param a el anuncio que se quiere enviar
     * @param f el seguidor potencial que podría recibirlo
     * @return true si debe enviarse el anuncio, false en caso contrario
     */
    boolean shouldSend(Announcement a, Follower f);
}
