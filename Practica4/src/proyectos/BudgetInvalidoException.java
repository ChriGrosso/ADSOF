package proyectos;

public class BudgetInvalidoException extends Exception {
    public BudgetInvalidoException(String mensaje) {
        super(mensaje);
    }
}