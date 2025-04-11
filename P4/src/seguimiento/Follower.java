/**
 * Interfaz que representa a un seguidor que puede recibir anuncios de entidades seguidas.
 * 
 * Los seguidores pueden ser ciudadanos o asociaciones, y su rol es recibir anuncios
 * relacionados con las entidades a las que siguen.
 * 
 * @author Marco Paparella
 * @author Christian Grosso
 */
package seguimiento;

public interface Follower {

    /**
     * Permite que el seguidor reciba un anuncio de una entidad seguida.
     * 
     * @param anuncio el anuncio que se desea enviar al seguidor
     */
    void receive(Announcement anuncio);
}
