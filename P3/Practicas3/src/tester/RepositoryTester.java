package tester;

import java.util.List;
import change.*;
import commit.ChangeCommit;
import commit.MergeCommit;
import repository.Repository;

/**
 * Clase RepositoryTester.
 * Clase de prueba para la funcionalidad de la clase Repository.
 *
 * @author Christian Grosso, Marco Paparella
 * @version 1.0
 */
public class RepositoryTester {
    public static void main(String[] args) {
        List<String> authorizedUsers = List.of("John Doe");
        Repository repository = new Repository("ADSOF p3", authorizedUsers);

        List<Change> changes1 = List.of(
                new AddChange(0, "/src/main/NuevaClase.java", "line1\nline2"),
                new ModifyChange(10, 10, "/src/main/ClaseExistente.java", "modified line"),
                new RemoveChange(1, 2, "/src/main/ClaseObsoleta.java")
        );
        ChangeCommit commit1 = new ChangeCommit("John Doe", "no comment", changes1);

        List<Change> changes2 = List.of(
                new AddChange(0, "/src/pkg1/Decorator.java", "line1\nline2\nline3"),
                new AddChange(0, "/src/pkg1/Decorator.java", "lastLine"),
                new ModifyChange(10, 10, "/src/pkg1/Decorator.java", "modified line")
        );
        ChangeCommit commit2 = new ChangeCommit("John Doe", "Decorator interface", changes2);

        MergeCommit mergeCommit = new MergeCommit("John Doe", "Merging previous commits", List.of(commit1, commit2));

        repository.addCommit(commit1);
        repository.addCommit(commit2);

        System.out.println(repository);

        repository.addBranch("Solving issue #1");
        repository.setActiveBranch("Solving issue #1");

        List<Change> changes3 = List.of(
                new AddChange(0, "/src/pkg1/Decorator.java", "Solving the issue")
        );
        ChangeCommit commit3 = new ChangeCommit("John Doe", "Solving the issue", changes3);

        repository.addCommit(commit3);

        System.out.println(repository);

        repository.setActiveBranch("main");
        repository.mergeBranches("Solving issue #1", "main");

        System.out.println(repository);
    }
}