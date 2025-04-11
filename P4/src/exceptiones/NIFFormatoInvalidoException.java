/**
 * Excepción que se lanza cuando el NIF proporcionado no cumple con el formato requerido.
 * El formato válido es: 8 dígitos seguidos de una letra (por ejemplo, 12345678A).
 * 
 * @author Marco Paparella
 * @author Christian Grosso
 */
package exceptiones;

public class NIFFormatoInvalidoException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Construye una nueva excepción NIFFormatoInvalidoException con el NIF inválido proporcionado.
     *
     * @param nif el NIF que no cumple con el formato esperado.
     */
    public NIFFormatoInvalidoException(String nif) {
        super("Formato NIF inválido: " + nif);
    }
}
