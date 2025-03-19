package change;

/**
 * Clase ModifyChange.
 * Representa un cambio de tipo modificación en un archivo de código.
 *
 * @author Christian Grosso, Marco Paparella
 * @version 1.0
 */
public class ModifyChange extends Change {
    private String content; // Contenido que reemplaza las líneas modificadas
    private int numberOfLines; // Número de líneas del nuevo contenido
    private int endLine; // Línea final de las líneas modificadas

    /**
     * Constructor de la clase ModifyChange.
     *
     * @param startLine Línea de inicio de la modificación.
     * @param endLine Línea final de la modificación.
     * @param filePath Ruta del archivo afectado.
     * @param content Contenido que reemplazará el existente.
     */
    public ModifyChange(int startLine, int endLine, String filePath, String content) {
        super("/", startLine, filePath);
        this.content = content;
        this.numberOfLines = content.split("\n").length;
        this.endLine = endLine;
    }

    /**
     * Devuelve una representación en cadena del objeto ModifyChange.
     *
     * @return Representación en cadena del cambio de modificación.
     */
    @Override
    public String toString() {
        return super.toString() + ",\n\tcontent='" + content.replace("\n", "\\n") + "',\n\tnumber of lines=" + numberOfLines + ",\n\tend line=" + endLine + "\n}";
    }

    /**
     * Obtiene el número de líneas del nuevo contenido.
     *
     * @return Número de líneas modificadas.
     */
    @Override
    public int getNumberOfLines() {
        return numberOfLines;
    }
}