package change;

/**
 * Clase abstracta Change.
 * Representa un cambio genérico en un archivo de código.
 *
 * @author Christian Grosso, Marco Paparella
 * @version 1.0
 */
public abstract class Change {
    private String type; // Tipo de cambio (+, /, -)
    private String filePath; // Ruta del archivo
    private int startLine; // Línea de inicio del cambio

    /**
     * Constructor de la clase Change.
     *
     * @param type Tipo de cambio.
     * @param startLine Línea de inicio del cambio.
     * @param filePath Ruta del archivo afectado.
     */
    public Change(String type, int startLine, String filePath) {
        this.setType(type);
        this.setStartLine(startLine);
        this.setFilePath(filePath);
    }

    /**
     * Devuelve una representación en cadena del objeto Change.
     *
     * @return Representación en cadena del cambio.
     */
    @Override
    public String toString() {
        return "{\n\ttype=" + getType() + ",\n\tstart line=" + getStartLine() + ",\n\tfile path='" + getFilePath() + "'";
    }

    /**
     * Método abstracto para obtener el número de líneas afectadas por el cambio.
     *
     * @return Número de líneas afectadas.
     */
    public abstract int getNumberOfLines();

    public int getStartLine() { return startLine; }
    public void setStartLine(int startLine) { this.startLine = startLine; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
}