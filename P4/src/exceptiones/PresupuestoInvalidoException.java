/**
 * Excepción que se lanza cuando el presupuesto indicado para un proyecto
 * es menor o igual a cero, lo cual no está permitido.
 * 
 * @author Marco Paparella
 * @author Christian Grosso
 */
package exceptiones;

public class PresupuestoInvalidoException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Construye una nueva excepción PresupuestoInvalidoException con un mensaje de error predefinido.
     */
    public PresupuestoInvalidoException() {
        super("El presupuesto debe ser mayor que cero.");
    }
}
