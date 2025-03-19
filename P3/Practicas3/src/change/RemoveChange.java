package change;

/**
 * Clase RemoveChange.
 * Representa un cambio de tipo eliminación en un archivo de código.
 *
 * @author Christian Grosso, Marco Paparella
 * @version 1.0
 */
public class RemoveChange extends Change {
    private int endLine; // Línea final de las líneas a eliminar

    /**
     * Constructor de la clase RemoveChange.
     *
     * @param startLine Línea de inicio de las líneas a eliminar.
     * @param endLine Línea final de las líneas a eliminar.
     * @param filePath Ruta del archivo afectado.
     */
    public RemoveChange(int startLine, int endLine, String filePath) {
        super("-", startLine, filePath);
        this.endLine = endLine;
    }

    /**
     * Devuelve una representación en cadena del objeto RemoveChange.
     *
     * @return Representación en cadena del cambio de eliminación.
     */
    @Override
    public String toString() {
        return super.toString() + ",\n\tend line=" + endLine + "\n}";
    }

    /**
     * Obtiene el número de líneas eliminadas.
     *
     * @return Número de líneas eliminadas.
     */
    @Override
    public int getNumberOfLines() {
        return endLine - getStartLine() + 1;
    }
}