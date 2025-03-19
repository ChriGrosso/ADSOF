package tester;

import java.util.List;
import change.AddChange;
import change.Change;
import change.ModifyChange;
import change.RemoveChange;

/**
 * Clase ChangeTester.
 * Clase de prueba para la funcionalidad de la clase Change y sus subclases.
 *
 * @author Christian Grosso, Marco Paparella
 * @version 1.0
 */
public class ChangeTester {
    public static void main(String[] args) {
        for (Change change : createChanges()) {
            System.out.println(change);
        }
    }

    /**
     * Crea y devuelve una lista de objetos Change.
     *
     * @return Lista de objetos Change.
     */
    public static List<Change> createChanges() {
        Change c1 = new AddChange(0, "/src/main/NuevaClase.java", "import java.util.*;\nimport java.io.*;");
        Change c2 = new ModifyChange(10, 10, "/src/main/ClaseExistente.java", "// Modificación en la clase existente");
        Change c3 = new RemoveChange(1, 2, "/src/main/ClaseObsoleta.java");
        return List.of(c1, c2, c3);
    }
}