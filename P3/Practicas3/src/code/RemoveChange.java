package code;

import code.Change;

public class RemoveChange extends Change {
    private int endLine; // Línea final de las líneas a eliminar

    // Constructor: Crea un nuevo cambio de tipo RemoveChange.
    // Recibe la línea de inicio, la línea final y la ruta del archivo.
    public RemoveChange(int startLine, int endLine, String filePath) {
        super("-", startLine, filePath); // Llama al constructor de la superclase Change
        this.endLine = endLine; // Asigna la línea final
    }

    // Sobreescribe el método toString() de la superclase Change.
    // Devuelve una representación en cadena del objeto RemoveChange, incluyendo la línea final.
    @Override
    public String toString() {
        return super.toString() + ",\n\tend line=" + endLine + "\n}";
    }

    // Sobreescribe el método getNumberOfLines() de la superclase Change.
    // Devuelve el número de líneas eliminadas.
    @Override
    public int getNumberOfLines() {
        return endLine - getStartLine() + 1; // Calcula el número de líneas eliminadas
    }
}