/**
 * Clase que representa una Asociación, que es una entidad que agrupa a ciudadanos y otras asociaciones.
 * Las asociaciones pueden proponer proyectos, seguir otras entidades y gestionar a sus miembros.
 * Además, implementa la interfaz `Follower` para recibir anuncios y la interfaz `FollowedEntity` para ser seguida.
 * 
 * Esta clase permite:
 * - Registrar ciudadanos e inscribirlos en la asociación.
 * - Agregar asociaciones y gestionar las relaciones jerárquicas entre ellas.
 * - Anunciar proyectos y actividades a los seguidores, basándose en estrategias de distribución de anuncios.
 * - Seguir a otras entidades (proyectos, asociaciones, fundaciones) y recibir anuncios según la estrategia definida.
 * 
 * @author Marco Paparella
 * @author Christian Grosso
 */
package usuarios;

import exceptiones.*;
import seguimiento.*;
import proyectos.*;

import java.util.*;

public class Asociacion extends Usuario implements Follower, FollowedEntity {
    private Set<Ciudadano> ciudadanos = new HashSet<>();
    private Set<Asociacion> asociaciones = new HashSet<>();
    private Map<Follower, AnnouncementStrategy> seguidores = new HashMap<>();
    private Ciudadano representante;

    /**
     * Constructor para crear una nueva asociación.
     * 
     * @param nombre Nombre de la asociación.
     * @param contrasenia Contraseña de la asociación.
     * @param representante Ciudadano que representa la asociación.
     */
    public Asociacion(String nombre, String contrasenia, Ciudadano representante) {
        super(nombre, UUID.randomUUID().toString(), contrasenia);
        this.representante = representante;
        ciudadanos.add(representante);
    }

    /**
     * Elimina un ciudadano de la lista de miembros de la asociación.
     * 
     * @param c Ciudadano a eliminar.
     */
    public void eliminarCiudadano(Ciudadano c) {
        ciudadanos.remove(c);
    }

    /**
     * Agrega una asociación a esta asociación.
     * 
     * @param a Asociación que se quiere agregar.
     * @throws AsociacionNoVaciaException Si la asociación no está vacía.
     * @throws RepresentanteInvalidoException Si el representante no coincide.
     */
    public void agregarAsociacion(Asociacion a)
            throws AsociacionNoVaciaException, RepresentanteInvalidoException {
        if (!a.estaVacia())
            throw new AsociacionNoVaciaException(a.getNombre());
        if (!a.representante.equals(this.representante))
            throw new RepresentanteInvalidoException();
        asociaciones.add(a);
        a.follow(this);
    }

    /**
     * Verifica si un ciudadano está presente directamente o indirectamente en la asociación.
     * 
     * @param c Ciudadano a verificar.
     * @return true si el ciudadano está presente, false en caso contrario.
     */
    public boolean contieneCiudadanoRecursivamente(Ciudadano c) {
        if (ciudadanos.contains(c)) return true;
        for (Asociacion a : asociaciones)
            if (a.contieneCiudadanoRecursivamente(c)) return true;
        return false;
    }

    /**
     * Verifica si la asociación está vacía, es decir, solo tiene al representante.
     * 
     * @return true si está vacía, false en caso contrario.
     */
    public boolean estaVacia() {
        return ciudadanos.size() == 1 && ciudadanos.contains(representante) && asociaciones.isEmpty();
    }

    /**
     * Obtiene el tipo de la asociación, con el número de ciudadanos.
     * 
     * @return Descripción del tipo de la asociación.
     */
    @Override
    public String getTipo() {
        return "asociación con " + contarCiudadanosTotales() + " ciudadanos";
    }

    /**
     * Cuenta el total de ciudadanos en la asociación, incluyendo los de asociaciones subyacentes.
     * 
     * @return Número total de ciudadanos.
     */
    public int contarCiudadanosTotales() {
        return obtenerCiudadanosRecursivos().size();
    }

    /**
     * Obtiene todos los ciudadanos, incluyendo los de asociaciones subyacentes.
     * 
     * @return Conjunto de ciudadanos.
     */
    public Set<Ciudadano> obtenerCiudadanosRecursivos() {
        Set<Ciudadano> resultado = new HashSet<>(ciudadanos);
        for (Asociacion a : asociaciones) {
            resultado.addAll(a.obtenerCiudadanosRecursivos());
        }
        return resultado;
    }

    /**
     * Verifica si un usuario está presente en la asociación o en alguna de sus asociaciones subyacentes.
     * 
     * @param u Usuario a verificar.
     * @return true si el usuario está presente, false en caso contrario.
     */
    public boolean contieneUsuario(Usuario u) {
        if (u instanceof Ciudadano c) {
            return obtenerCiudadanosRecursivos().contains(c);
        } else if (u instanceof Asociacion a) {
            return asociaciones.contains(a) || asociaciones.stream().anyMatch(sub -> sub.contieneUsuario(a));
        }
        return false;
    }

    /**
     * Recibe un anuncio y lo distribuye a todos los ciudadanos y asociaciones miembros.
     * 
     * @param anuncio Anuncio a recibir.
     */
    public void receive(Announcement anuncio) {
        for (Ciudadano c : ciudadanos) {
            c.receive(anuncio);
        }
        for (Asociacion a : asociaciones) {
            a.receive(anuncio);
        }
    }

    /**
     * Permite a un seguidor seguir la asociación con una estrategia de anuncios por defecto.
     * 
     * @param f Follower que va a seguir la asociación.
     * @return true si el seguidor fue añadido correctamente.
     */
    @Override
    public boolean follow(Follower f) {
        return follow(f, new DefaultAnnouncementStrategy());
    }

    /**
     * Permite a un seguidor seguir la asociación con una estrategia personalizada de anuncios.
     * 
     * @param f Follower que va a seguir la asociación.
     * @param s Estrategia de anuncios que se aplicará al seguidor.
     * @return true si el seguidor fue añadido correctamente.
     */
    @Override
    public boolean follow(Follower f, AnnouncementStrategy s) {
        seguidores.put(f, s);
        return true;
    }

    /**
     * Permite a un seguidor dejar de seguir la asociación.
     * 
     * @param f Follower que deja de seguir.
     * @return true si el seguidor fue eliminado correctamente.
     */
    @Override
    public boolean unfollow(Follower f) {
        return seguidores.remove(f) != null;
    }

    /**
     * Anuncia un mensaje a todos los seguidores de la asociación según su estrategia de anuncios.
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
     * Inscribe un ciudadano en la asociación y envía un anuncio a los miembros.
     * 
     * @param c Ciudadano a inscribir.
     * @throws CiudadanoDuplicadoException Si el ciudadano ya está inscrito.
     */
    public void inscribirCiudadano(Ciudadano c) throws CiudadanoDuplicadoException {
        if (ciudadanos.contains(c)) {
            throw new CiudadanoDuplicadoException("El ciudadano " + c.getNombre() + " ya está inscrito directamente en " + this.getNombre());
        }
        if (contieneUsuario(c)) {
            throw new CiudadanoDuplicadoException("El ciudadano " + c.getNombre() + " ya está inscrito indirectamente en una asociación contenida de " + this.getNombre());
        }
        ciudadanos.add(c);
        follow(c);
        announce(new Announcement("Alta de " + c.getNombre() + " en " + this.getNombre()
                + " (" + contarCiudadanosTotales() + " miembros)"));
    }

    /**
     * Registra un proyecto en la asociación y envía un anuncio sobre la propuesta.
     * 
     * @param p Proyecto a registrar.
     */
    public void registrarProyecto(Proyecto p) {
        p.follow(this);
        announce(new Announcement(this.getNombre() + " propone el proyecto " + p.getTitulo() +
                ": \"" + p.getDescripcion() + "\""));
    }

    /**
     * Devuelve la representación en formato string de la asociación.
     * 
     * @return Nombre de la asociación con el número de ciudadanos.
     */
    @Override
    public String toString() {
        return getNombre() + " <asociación con " + contarCiudadanosTotales() + " ciudadanos>";
    }
}
