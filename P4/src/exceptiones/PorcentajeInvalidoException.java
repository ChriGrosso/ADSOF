/**
 * Excepción que se lanza cuando el porcentaje proporcionado está fuera del rango válido.
 * El porcentaje debe estar comprendido entre 1 y 100 (ambos inclusive).
 * 
 * @author Marco Paparella
 * @author Christian Grosso
 */
package exceptiones;

public class PorcentajeInvalidoException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Construye una nueva excepción PorcentajeInvalidoException con un mensaje predefinido.
     */
    public PorcentajeInvalidoException() {
        super("El porcentaje debe estar entre 1 y 100.");
    }
}
