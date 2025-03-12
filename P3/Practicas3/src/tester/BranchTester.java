package tester;

import java.util.List;
import commit.ChangeCommit;
import branches.Branch;
import code.AddChange;
import code.Change;
import code.ModifyChange;
import code.RemoveChange;

public class BranchTester {
    public static void main(String[] args) {
        Branch mainBranch = new Branch("main"); // Crea una nueva rama llamada "main"

        List<Change> changes1 = List.of( // Crea una lista de cambios para el primer commit
                new AddChange(0, "/src/main/NuevaClase.java", "line1\nline2"),
                new ModifyChange(10, 10, "/src/main/ClaseExistente.java", "modified line"),
                new RemoveChange(1, 2, "/src/main/ClaseObsoleta.java")
        );
        ChangeCommit commit1 = new ChangeCommit("Author", "no comment", changes1); // Crea un nuevo commit con los cambios

        List<Change> changes2 = List.of( // Crea una lista de cambios para el segundo commit
                new AddChange(0, "/src/pkg1/Decorator.java", "line1\nline2\nline3"),
                new AddChange(0, "/src/pkg1/Decorator.java", "lastLine"),
                new ModifyChange(10, 10, "/src/pkg1/Decorator.java", "modified line")
        );
        ChangeCommit commit2 = new ChangeCommit("Author", "Decorator interface", changes2); // Crea un nuevo commit con los cambios

        commit.MergeCommit commit3 = new commit.MergeCommit("Author", "Merging previous commits", List.of(commit1, commit2)); // Crea un MergeCommit que combina los dos commits anteriores

        mainBranch.addCommit(commit1); // Agrega el primer commit a la rama "main"
        mainBranch.addCommit(commit2); // Agrega el segundo commit a la rama "main"
        mainBranch.addCommit(commit3); // Agrega el MergeCommit a la rama "main"

        Branch solvingIssueBranch = new Branch("Solving issue #1", mainBranch); // Crea una nueva rama llamada "Solving issue #1" a partir de la rama "main"

        System.out.println(mainBranch); // Imprime la rama "main"
        System.out.println(solvingIssueBranch); // Imprime la rama "Solving issue #1"
    }
}