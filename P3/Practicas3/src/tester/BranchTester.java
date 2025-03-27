package tester;

import java.util.List;
import branches.Branch;
import commit.ChangeCommit;
import change.AddChange;
import change.Change;
import change.ModifyChange;
import change.RemoveChange;

/**
 * Clase BranchTester.
 * Clase de prueba para la funcionalidad de la clase Branch.
 *
 * @author Christian Grosso, Marco Paparella
 * @version 1.0
 */
public class BranchTester {
    public static void main(String[] args) {
        Branch mainBranch = new Branch("main");

        List<Change> changes1 = List.of(
                new AddChange(0, "/src/main/NuevaClase.java", "line1\nline2"),
                new ModifyChange(10, 10, "/src/main/ClaseExistente.java", "modified line"),
                new RemoveChange(1, 2, "/src/main/ClaseObsoleta.java")
        );
        ChangeCommit commit1 = new ChangeCommit("Author", "no comment", changes1);

        List<Change> changes2 = List.of(
                new AddChange(0, "/src/pkg1/Decorator.java", "line1\nline2\nline3"),
                new AddChange(0, "/src/pkg1/Decorator.java", "lastLine"),
                new ModifyChange(10, 10, "/src/pkg1/Decorator.java", "modified line")
        );
        ChangeCommit commit2 = new ChangeCommit("John Doe", "Decorator interface", changes2);

        commit.MergeCommit commit3 = new commit.MergeCommit("Author", "Merging previous commits", List.of(commit1, commit2));

        mainBranch.addCommit(commit1);
        mainBranch.addCommit(commit2);
        mainBranch.addCommit(commit3);

        Branch solvingIssueBranch = new Branch("Solving issue #1", mainBranch);

        System.out.println(mainBranch);
        System.out.println(solvingIssueBranch);
    }
}