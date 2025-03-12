package code;

public class ModifyChange extends Change {
    private String content; // Contenido que reemplaza las líneas modificadas
    private int numberOfLines; // Número de líneas del nuevo contenido
    private int endLine; // Línea final de las líneas modificadas

    // Constructor: Crea un nuevo cambio de tipo ModifyChange.
    // Recibe la línea de inicio, la línea final, la ruta del archivo y el nuevo contenido.
    public ModifyChange(int startLine, int endLine, String filePath, String content) {
        super("/", startLine, filePath); // Llama al constructor de la superclase Change
        this.content = content; // Asigna el nuevo contenido
        this.numberOfLines = content.split("\n").length; // Calcula el número de líneas del nuevo contenido
        this.endLine = endLine; // Asigna la línea final
    }

    // Sobreescribe el método toString() de la superclase Change.
    // Devuelve una representación en cadena del objeto ModifyChange, incluyendo el nuevo contenido, el número de líneas y la línea final.
    @Override
    public String toString() {
        return super.toString() + ",\n\tcontent='" + content.replace("\n", "\\n") + "',\n\tnumber of lines=" + numberOfLines + ",\n\tend line=" + endLine + "\n}";
    }

    // Sobreescribe el método getNumberOfLines() de la superclase Change.
    // Devuelve el número de líneas del nuevo contenido.
    @Override
    public int getNumberOfLines() {
        return numberOfLines;
    }
}