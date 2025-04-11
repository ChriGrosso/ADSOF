/**
 * Excepción que se lanza cuando se intenta agregar una asociación que no está vacía
 * dentro de otra asociación. Solo se permite agregar asociaciones vacías.
 * 
 * @author Marco Paparella
 * @author Christian Grosso
 */
package exceptiones;

public class AsociacionNoVaciaException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Construye una nueva excepción AsociacionNoVaciaException con el nombre de la asociación implicada.
     *
     * @param nombre el nombre de la asociación que se intentó agregar sin estar vacía.
     */
    public AsociacionNoVaciaException(String nombre) {
        super("La asociación '" + nombre + "' no está vacía y no puede ser agregada.");
    }
}
