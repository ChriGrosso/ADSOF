package code;

public class AddChange extends Change {
    private String content; // Contenido que se va a añadir
    private int numberOfLines; // Número de líneas del contenido a añadir

    // Constructor: Crea un nuevo cambio de tipo AddChange.
    // Recibe la línea de inicio, la ruta del archivo y el contenido a añadir.
    public AddChange(int startLine, String filePath, String content) {
        super("+", startLine, filePath); // Llama al constructor de la superclase Change
        this.content = content; // Asigna el contenido
        this.numberOfLines = content.split("\n").length; // Calcula el número de líneas del contenido
    }

    // Sobreescribe el método toString() de la superclase Change.
    // Devuelve una representación en cadena del objeto AddChange, incluyendo el contenido y el número de líneas.
    @Override
    public String toString() {
        return super.toString() + ",\n\tcontent='" + content.replace("\n", "\n\t") + "',\n\tnumber of lines=" + numberOfLines + "\n}";
    }

    // Sobreescribe el método getNumberOfLines() de la superclase Change.
    // Devuelve el número de líneas del contenido a añadir.
    @Override
    public int getNumberOfLines() {
        return numberOfLines;
    }
}