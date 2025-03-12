package tester;

import java.util.List; // Importa la interfaz List para trabajar con listas

import code.AddChange; // Importa la clase AddChange del paquete code
import code.Change; // Importa la clase Change del paquete code
import code.ModifyChange; // Importa la clase ModifyChange del paquete code
import code.RemoveChange; // Importa la clase RemoveChange del paquete code

public class ChangeTester {
    public static void main(String[] args) {
        for (Change change : createChanges()) { // Itera a través de la lista de cambios creada por el método createChanges()
            System.out.println(change); // Imprime cada objeto Change en la consola
        }
    }

    // Método estático que crea y devuelve una lista de objetos Change.
    public static List<Change> createChanges() {
        Change c1 = new AddChange(0, "/src/main/NuevaClase.java", "import java.util.*;\nimport java.io.*;"); // Crea un nuevo cambio de tipo AddChange
        Change c2 = new ModifyChange(10, 10, "/src/main/ClaseExistente.java", "// Modificación en la clase existente"); // Crea un nuevo cambio de tipo ModifyChange
        Change c3 = new RemoveChange(1, 2, "/src/main/ClaseObsoleta.java"); // Crea un nuevo cambio de tipo RemoveChange
        return List.of(c1, c2, c3); // Devuelve una lista inmutable que contiene los cambios creados
    }
}