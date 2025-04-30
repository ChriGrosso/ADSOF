/**
 * Interfaz que define el contrato para un nodo dentro de un flujo de trabajo.
 * Cada nodo tiene un nombre y una operación ejecutable que transforma el estado.
 * @param <T> El tipo del estado común manejado por el flujo de trabajo.
 */
interface NodoDeTrabajo<T> {
    String getNombre();
    T ejecutar(T estado);
}