/**
 * Excepción que se lanza cuando se intenta inscribir a un ciudadano que ya está registrado,
 * ya sea directa o indirectamente, en una asociación.
 * 
 * @author Marco Paparella
 * @author Christian Grosso
 */
package exceptiones;

public class CiudadanoDuplicadoException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Construye una nueva excepción CiudadanoDuplicadoException con el mensaje especificado.
     *
     * @param mensaje mensaje detallado que describe el motivo de la duplicación.
     */
    public CiudadanoDuplicadoException(String mensaje) {
        super(mensaje);
    }
}
