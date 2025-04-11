/**
 * Excepción que se lanza cuando el CIF proporcionado no cumple con el formato esperado.
 * El formato válido es: una letra, seguida de 7 dígitos y otra letra (por ejemplo, A1234567B).
 * 
 * @author Marco Paparella
 * @author Christian Grosso
 */
package exceptiones;

public class CIFFormatoInvalidoException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Construye una nueva excepción CIFFormatoInvalidoException con el CIF inválido proporcionado.
     *
     * @param cif el CIF que no cumple con el formato requerido.
     */
    public CIFFormatoInvalidoException(String cif) {
        super("Formato CIF inválido: " + cif);
    }
}
