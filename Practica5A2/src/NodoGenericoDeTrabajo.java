
import java.util.function.Function;

/**
 * Implementación genérica de un {@code NodoDeTrabajo} que utiliza una expresión lambda
 * ({@code Function}) para definir su operación.
 * @param <T> El tipo del estado común manejado por el flujo de trabajo.
 */
class NodoGenericoDeTrabajo<T> implements NodoDeTrabajo<T> {
    private String nombre;
    private Function<T, T> operacion;

    /**
     * Constructor para crear un nuevo {@code NodoGenericoDeTrabajo}.
     * @param nombre El nombre del nodo.
     * @param operacion La función lambda que representa la operación del nodo.
     */
    public NodoGenericoDeTrabajo(String nombre, Function<T, T> operacion) {
        this.nombre = nombre;
        this.operacion = operacion;
    }
    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public T ejecutar(T estado) {
        return operacion.apply(estado);
    }
}