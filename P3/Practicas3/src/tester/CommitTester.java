package tester;

import java.time.LocalDateTime; // Importa la clase LocalDateTime para trabajar con fechas y horas
import java.time.format.DateTimeFormatter; // Importa la clase DateTimeFormatter para formatear fechas y horas
import java.util.ArrayList; // Importa la clase ArrayList para trabajar con listas dinámicas
import java.util.List; // Importa la interfaz List para trabajar con listas
import java.util.UUID; // Importa la clase UUID para generar identificadores únicos

import code.AddChange; // Importa la clase AddChange del paquete code
import code.Change; // Importa la clase Change del paquete code
import code.ModifyChange; // Importa la clase ModifyChange del paquete code
import code.RemoveChange; // Importa la clase RemoveChange del paquete code
import commit.ChangeCommit; // Importa la clase ChangeCommit del paquete commit
import commit.MergeCommit; // Importa la clase MergeCommit del paquete commit

public class CommitTester {
    public static void main(String[] args) {
        // Crea una lista de cambios para el primer commit
        List<Change> changes1 = List.of(
                new AddChange(0, "/src/main/NuevaClase.java", "line1\nline2"),
                new ModifyChange(10, 10, "/src/main/ClaseExistente.java", "modified line"),
                new RemoveChange(1, 2, "/src/main/ClaseObsoleta.java")
        );
        ChangeCommit commit1 = new ChangeCommit("John Doe", "no comment", changes1); // Crea un nuevo commit con los cambios

        // Crea una lista de cambios para el segundo commit
        List<Change> changes2 = List.of(
                new AddChange(0, "/src/pkg1/Decorator.java", "line1\nline2\nline3"),
                new AddChange(0, "/src/pkg1/Decorator.java", "lastLine"),
                new ModifyChange(10, 10, "/src/pkg1/Decorator.java", "modified line")
        );
        ChangeCommit commit2 = new ChangeCommit("John Doe", "Decorator interface", changes2); // Crea un nuevo commit con los cambios

        MergeCommit mergeCommit = new MergeCommit("John Doe", "Merging previous commits", List.of(commit1, commit2)); // Crea un MergeCommit que combina los dos commits anteriores

        System.out.println(commit1); // Imprime el primer commit
        System.out.println(commit2); // Imprime el segundo commit
        System.out.println(mergeCommit); // Imprime el MergeCommit
        System.out.println("Total lines changed in commit1: " + commit1.getTotalLinesChanged()); // Imprime el número total de líneas modificadas en el primer commit
        System.out.println("Total lines changed in commit2: " + commit2.getTotalLinesChanged()); // Imprime el número total de líneas modificadas en el segundo commit
        System.out.println("Total lines changed in mergeCommit: " + mergeCommit.getTotalLinesChanged()); // Imprime el número total de líneas modificadas en el MergeCommit
    }
}