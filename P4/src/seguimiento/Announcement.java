/**
 * Representa un anuncio enviado a los seguidores de una entidad seguida (proyecto, fundación o asociación).
 * Cada anuncio contiene un mensaje y la fecha y hora en que fue generado.
 * 
 * Esta clase sirve como base para la comunicación entre entidades seguidas y sus seguidores.
 * 
 * @author Marco Paparella
 * @author Christian Grosso
 */
package seguimiento;

import java.time.LocalDateTime;

public class Announcement {

    private final String mensaje;
    private final LocalDateTime fecha;

    /**
     * Crea un nuevo anuncio con el mensaje especificado. La fecha se asigna automáticamente.
     * 
     * @param mensaje el contenido del anuncio
     */
    public Announcement(String mensaje) {
        this.mensaje = mensaje;
        this.fecha = LocalDateTime.now();
    }

    /**
     * Devuelve el mensaje del anuncio.
     * 
     * @return contenido textual del anuncio
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Devuelve la fecha y hora en que se creó el anuncio.
     * 
     * @return marca temporal del anuncio
     */
    public LocalDateTime getFecha() {
        return fecha;
    }

    /**
     * Representación textual del anuncio, usada por defecto al imprimirlo.
     */
    @Override
    public String toString() {
        return mensaje;
    }
}
