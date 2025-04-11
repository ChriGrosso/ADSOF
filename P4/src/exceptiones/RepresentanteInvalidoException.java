/**
 * Excepción que se lanza cuando se intenta agregar una asociación dentro de otra,
 * pero ambas no comparten el mismo representante. Esta operación solo es válida
 * si el representante es el mismo en ambas asociaciones.
 * 
 * @author Marco Paparella
 * @author Christian Grosso
 */
package exceptiones;

public class RepresentanteInvalidoException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Construye una nueva excepción RepresentanteInvalidoException con un mensaje predefinido.
     */
    public RepresentanteInvalidoException() {
        super("Todas las asociaciones deben tener el mismo representante.");
    }
}
