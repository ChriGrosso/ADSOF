/**
 * Excepción que se lanza cuando se intenta registrar un identificador (NIF o CIF)
 * que ya ha sido previamente utilizado en el sistema.
 * 
 * @author Marco Paparella
 * @author Christian Grosso
 */
package exceptiones;

public class IdentificadorDuplicadoException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Construye una nueva excepción IdentificadorDuplicadoException con el identificador duplicado.
     *
     * @param id el identificador que ya está registrado (NIF o CIF).
     */
    public IdentificadorDuplicadoException(String id) {
        super("Identificador ya registrado: " + id);
    }
}
