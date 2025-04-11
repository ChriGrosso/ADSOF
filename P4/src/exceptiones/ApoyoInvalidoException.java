/**
 * Excepción personalizada que se lanza cuando se produce un intento inválido de apoyar un proyecto.
 * Por ejemplo, si un usuario ya ha apoyado el proyecto, si es el propio proponente o si el apoyo es tardío.
 * 
 * @author Marco Paparella
 * @author Christian Grosso
 */
package exceptiones;

public class ApoyoInvalidoException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Construye una nueva excepción ApoyoInvalidoException con el mensaje de error proporcionado.
     *
     * @param mensaje descripción detallada del motivo del error.
     */
    public ApoyoInvalidoException(String mensaje) {
        super("Apoyo inválido: " + mensaje);
    }
}
