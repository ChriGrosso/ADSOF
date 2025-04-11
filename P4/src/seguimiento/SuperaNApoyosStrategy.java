/**
 * Estrategia de anuncios que se activa cuando un proyecto recibe más de un número determinado de apoyos.
 * 
 * Esta clase implementa la estrategia de distribución de anuncios, enviando el anuncio a los seguidores
 * solo cuando el número de apoyos de un proyecto supera un umbral especificado.
 * Una vez que se envía un anuncio a un seguidor, este no volverá a recibir otro anuncio similar.
 * 
 * @author Marco Paparella
 * @author Christian Grosso
 */
package seguimiento;

import proyectos.Proyecto;

import java.util.*;

public class SuperaNApoyosStrategy implements AnnouncementStrategy {

    private final int n; // Umbral de apoyos para activar la estrategia
    private final Set<Follower> sent = new HashSet<>(); // Conjunto de seguidores que ya recibieron el anuncio

    /**
     * Crea una nueva estrategia que se activa cuando un proyecto supera el número de apoyos especificado.
     * 
     * @param n el umbral de apoyos que debe superar el proyecto para que el anuncio sea enviado
     */
    public SuperaNApoyosStrategy(int n) {
        this.n = n;
    }

    /**
     * Determina si un anuncio debe ser enviado a un seguidor, basado en el número de apoyos del proyecto.
     * 
     * @param a el anuncio a enviar
     * @param f el seguidor al que se desea enviar el anuncio
     * @return true si el anuncio debe ser enviado, false si no
     */
    @Override
    public boolean shouldSend(Announcement a, Follower f) {
        if (!(a instanceof ApoyoAnnouncement apoyo) || sent.contains(f)) return false; // Si ya fue enviado
        if (apoyo.getApoyos() >= n) { // Si el proyecto ha superado el umbral de apoyos
            sent.add(f); // Marca al seguidor como que ya recibió el anuncio
            return true;
        }
        return false;
    }
}
