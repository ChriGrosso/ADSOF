/**
 * Clase que representa una Fundación, que es una entidad que puede proponer proyectos y recibir seguidores.
 * Las fundaciones pueden anunciar proyectos y seguir a otros usuarios o entidades.
 * Además, implementa la interfaz `FollowedEntity` para ser seguida por otros usuarios y distribuir anuncios.
 * 
 * Esta clase permite:
 * - Seguir a seguidores y distribuir anuncios a los mismos.
 * - Anunciar proyectos nuevos propuestos por la fundación.
 * 
 * @author Marco Paparella
 * @author Christian Grosso
 */
package usuarios;

import java.util.*;
import proyectos.*;
import seguimiento.*;

public class Fundacion extends Usuario implements FollowedEntity {
    private Map<Follower, AnnouncementStrategy> seguidores = new HashMap<>();

    /**
     * Constructor para crear una nueva fundación, validando su formato de CIF.
     * 
     * @param nombre Nombre de la fundación.
     * @param cif Código de identificación fiscal de la fundación.
     * @param contrasenia Contraseña de la fundación.
     * @throws IllegalArgumentException Si el CIF no tiene el formato válido.
     */
    public Fundacion(String nombre, String cif, String contrasenia) {
        super(nombre, cif, contrasenia);
        if (!cif.matches("[A-Z]\\d{7}[A-Z]"))
            throw new IllegalArgumentException("CIF inválido: " + cif);
    }

    /**
     * Permite a un seguidor seguir la fundación con una estrategia de anuncios por defecto.
     * 
     * @param f Follower que va a seguir la fundación.
     * @return true si el seguidor fue añadido correctamente.
     */
    @Override
    public boolean follow(Follower f) {
        return follow(f, new DefaultAnnouncementStrategy());
    }

    /**
     * Permite a un seguidor seguir la fundación con una estrategia personalizada de anuncios.
     * 
     * @param f Follower que va a seguir la fundación.
     * @param s Estrategia de anuncios que se aplicará al seguidor.
     * @return true si el seguidor fue añadido correctamente.
     */
    @Override
    public boolean follow(Follower f, AnnouncementStrategy s) {
        seguidores.put(f, s);
        return true;
    }

    /**
     * Permite a un seguidor dejar de seguir la fundación.
     * 
     * @param f Follower que deja de seguir.
     * @return true si el seguidor fue eliminado correctamente.
     */
    @Override
    public boolean unfollow(Follower f) {
        return seguidores.remove(f) != null;
    }

    /**
     * Anuncia un mensaje a todos los seguidores de la fundación según su estrategia de anuncios.
     * 
     * @param anuncio Anuncio a distribuir.
     */
    @Override
    public void announce(Announcement anuncio) {
        for (Map.Entry<Follower, AnnouncementStrategy> entry : seguidores.entrySet()) {
            if (entry.getValue().shouldSend(anuncio, entry.getKey())) {
                entry.getKey().receive(anuncio);
            }
        }
    }

    /**
     * Anuncia un nuevo proyecto propuesto por la fundación.
     * 
     * @param p Proyecto que la fundación está proponiendo.
     */
    public void anunciarProyectoNuevo(Proyecto p) {
        String mensaje = getNombre() + " propone el proyecto " + p.getTitulo() + ": \"" + p.getDescripcion() + "\"";
        announce(new Announcement(mensaje));
    }

    /**
     * Devuelve la representación en formato string de la fundación.
     * 
     * @return Representación en cadena de la fundación.
     */
    @Override
    public String toString() {
        return getNombre() + " CIF (" + getIdentificador() + ") <fundación>";
    }

    /**
     * Obtiene el tipo de la entidad, que en este caso es siempre "fundación".
     * 
     * @return Tipo de entidad.
     */
    @Override
    public String getTipo() {
        return "fundación";
    }
}
