package tester;

import java.util.List;

import code.AddChange;
import code.Change;
import code.ModifyChange;
import code.RemoveChange;

public class ChangeTester {
    public static void main(String[] args) {
        for (Change change : createChanges()) {
            System.out.println(change);
        }
    }

    public static List<Change> createChanges() {
        Change c1 = new AddChange(0, "/src/main/NuevaClase.java", "import java.util.*;\nimport java.io.*;");
        Change c2 = new ModifyChange(10, 10, "/src/main/ClaseExistente.java", "// Modificación en la clase existente");
        Change c3 = new RemoveChange(1, 2, "/src/main/ClaseObsoleta.java");
        return List.of(c1, c2, c3);
    }
}