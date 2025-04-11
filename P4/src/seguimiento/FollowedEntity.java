/**
 * Interfaz que representa una entidad que puede ser seguida por un seguidor.
 * Las entidades seguidas pueden ser proyectos, asociaciones, fundaciones, etc.
 * 
 * Esta interfaz define los métodos necesarios para seguir y dejar de seguir una entidad,
 * así como para anunciar nuevos eventos a los seguidores.
 * 
 * @author Marco Paparella
 * @author Christian Grosso
 */
package seguimiento;

public interface FollowedEntity {

    /**
     * Permite a un seguidor seguir la entidad.
     * 
     * @param f el seguidor que va a seguir la entidad
     * @return true si se añade correctamente el seguidor
     */
    boolean follow(Follower f);

    /**
     * Permite a un seguidor seguir la entidad con una estrategia de distribución de anuncios personalizada.
     * 
     * @param f el seguidor que va a seguir la entidad
     * @param strategy la estrategia que se utilizará para la distribución de anuncios
     * @return true si se añade correctamente el seguidor con la estrategia
     */
    boolean follow(Follower f, AnnouncementStrategy strategy);

    /**
     * Permite que un seguidor deje de seguir la entidad.
     * 
     * @param f el seguidor que dejará de seguir la entidad
     * @return true si se elimina correctamente el seguidor
     */
    boolean unfollow(Follower f);

    /**
     * Anuncia un mensaje a todos los seguidores de la entidad.
     * 
     * @param a el anuncio que se enviará a los seguidores
     */
    void announce(Announcement a);
}
