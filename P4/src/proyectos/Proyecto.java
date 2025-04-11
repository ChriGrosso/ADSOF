/**
 * Representa un proyecto participativo propuesto por un ciudadano, asociación o fundación.
 * Cada proyecto tiene un título, descripción, proponente y una fecha de propuesta.
 * 
 * También puede ser seguido por seguidores (ciudadanos o asociaciones), los cuales recibirán
 * anuncios según la estrategia de difusión configurada.
 * 
 * @author Marco Paparella
 * @author Christian Grosso
 */
package proyectos;

import usuarios.*;
import seguimiento.*;
import java.time.LocalDateTime;
import java.util.*;

public class Proyecto {

    private final String codigo;
    private final String titulo;
    private final String descripcion;
    private final Usuario proponente;
    private final LocalDateTime fechaPropuesta;
    private Map<Follower, AnnouncementStrategy> seguidores = new HashMap<>();

    /**
     * Construye un nuevo proyecto con título, descripción y usuario proponente.
     * Se genera automáticamente un código único y se registra la fecha actual.
     * 
     * @param titulo título del proyecto
     * @param descripcion descripción del proyecto
     * @param proponente usuario que propone el proyecto
     */
    public Proyecto(String titulo, String descripcion, Usuario proponente) {
        this.codigo = UUID.randomUUID().toString();
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.proponente = proponente;
        this.fechaPropuesta = LocalDateTime.now();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Usuario getProponente() {
        return proponente;
    }

    public LocalDateTime getFechaPropuesta() {
        return fechaPropuesta;
    }

    /**
     * Permite a un seguidor seguir el proyecto con la estrategia por defecto.
     * 
     * @param f seguidor a registrar
     * @return true si se registra correctamente
     */
    public boolean follow(Follower f) {
        return follow(f, new DefaultAnnouncementStrategy());
    }

    /**
     * Permite a un seguidor seguir el proyecto con una estrategia personalizada.
     * 
     * @param f seguidor a registrar
     * @param s estrategia de anuncios que se aplicará a ese seguidor
     * @return true si se registra correctamente
     */
    public boolean follow(Follower f, AnnouncementStrategy s) {
        seguidores.put(f, s);
        return true;
    }

    /**
     * Permite que un seguidor deje de seguir el proyecto.
     * 
     * @param f seguidor a eliminar
     * @return true si se eliminó correctamente
     */
    public boolean unfollow(Follower f) {
        return seguidores.remove(f) != null;
    }

    /**
     * Difunde un anuncio a todos los seguidores que cumplan su estrategia de difusión.
     * 
     * @param anuncio objeto de tipo Announcement a enviar
     */
    public void announce(Announcement anuncio) {
        for (Map.Entry<Follower, AnnouncementStrategy> entry : seguidores.entrySet()) {
            Follower f = entry.getKey();
            AnnouncementStrategy s = entry.getValue();
            if (s.shouldSend(anuncio, f)) {
                f.receive(anuncio);
            }
        }
    }

    /**
     * Genera y anuncia un mensaje específico con el número total de apoyos al proyecto.
     * 
     * @param totalApoyos número total de apoyos actuales
     */
    public void anunciarApoyo(int totalApoyos) {
        String mensaje = getProponente().getNombre() + " da apoyo al proyecto " + getTitulo() +
                " (" + totalApoyos + " apoyos)";
        announce(new ApoyoAnnouncement(mensaje, totalApoyos));
    }

    /**
     * Devuelve una representación textual del proyecto para mostrar en listados.
     */
    @Override
    public String toString() {
        return "0: " + titulo + ". Proponente: " + proponente;
    }
}
