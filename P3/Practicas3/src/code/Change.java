package code;

public abstract class Change {
    public String type; // Tipo de cambio (+, /, -)
    public String filePath; // Ruta del archivo
    protected int startLine; // Línea de inicio del cambio

    // Constructor: Crea un nuevo cambio.
    // Recibe el tipo de cambio, la línea de inicio y la ruta del archivo.
    public Change(String type, int startLine, String filePath) {
        this.type = type; // Asigna el tipo de cambio
        this.startLine = startLine; // Asigna la línea de inicio
        this.filePath = filePath; // Asigna la ruta del archivo
    }

    // Devuelve una representación en cadena del objeto Change.
    @Override
    public String toString() {
        return "{type=" + type + ", start line=" + startLine + ", file path='" + filePath + "'";
    }

    // Método abstracto: Devuelve el número de líneas afectadas por el cambio.
    // Este método debe ser implementado por las subclases.
    public abstract int getNumberOfLines();
}