package change;

/**
 * Clase AddChange.
 * Representa un cambio de tipo adición en un archivo de código.
 *
 * @author Christian Grosso, Marco Paparella
 * @version 1.0
 */
public class AddChange extends Change {
    private String content; // Contenido que se va a añadir
    private int numberOfLines; // Número de líneas del contenido a añadir

    /**
     * Constructor de la clase AddChange.
     *
     * @param startLine Línea de inicio donde se añadirá el contenido.
     * @param filePath Ruta del archivo donde se aplicará el cambio.
     * @param content Contenido a añadir.
     */
    public AddChange(int startLine, String filePath, String content) {
        super("+", startLine, filePath); // Llama al constructor de la superclase Change
        this.content = content; // Asigna el contenido
        this.numberOfLines = content.split("\n").length; // Calcula el número de líneas del contenido
    }

    /**
     * Devuelve una representación en cadena del objeto AddChange.
     *
     * @return Representación en cadena del cambio de adición.
     */
    @Override
    public String toString() {
        return super.toString() + ",\n\tcontent='" + content.replace("\n", "\n\t") + "',\n\tnumber of lines=" + numberOfLines + "\n}";
    }

    /**
     * Obtiene el número de líneas añadidas.
     *
     * @return Número de líneas añadidas.
     */
    @Override
    public int getNumberOfLines() {
        return numberOfLines;
    }
}