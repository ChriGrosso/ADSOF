/**
 * Extiende la clase Announcement para representar un anuncio específico relacionado con el apoyo
 * de un proyecto, incluyendo el número total de apoyos recibidos.
 * 
 * Esta clase se utiliza para generar anuncios especiales sobre apoyos a proyectos, con el mensaje
 * correspondiente y el conteo de apoyos actual.
 * 
 * @author Marco Paparella
 * @author Christian Grosso
 */
package seguimiento;

public class ApoyoAnnouncement extends Announcement {

    private int apoyos;

    /**
     * Crea un nuevo anuncio de tipo ApoyoAnnouncement, con el mensaje y el número de apoyos.
     * 
     * @param message el mensaje del anuncio
     * @param apoyos número total de apoyos que el proyecto ha recibido hasta el momento
     */
    public ApoyoAnnouncement(String message, int apoyos) {
        super(message);
        this.apoyos = apoyos;
    }

    /**
     * Devuelve el número total de apoyos que ha recibido el proyecto.
     * 
     * @return el número de apoyos
     */
    public int getApoyos() {
        return apoyos;
    }
}
