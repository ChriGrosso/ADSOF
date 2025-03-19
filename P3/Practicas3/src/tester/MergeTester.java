package tester;

import repository.Repository;
import branches.Branch;
import commit.MergeCommit;
import commit.ChangeCommit;
import java.util.List;
import java.util.ArrayList;

/**
 * Clase MergeTester.
 * Clase de prueba para la funcionalidad de la fusión de ramas.
 *
 * @author Christian Grosso, Marco Paparella
 * @version 1.0
 */
public class MergeTester {
    public static void main(String[] args) {
        List<String> authorizedUsers = List.of("System");
        Repository repository = new Repository("ADSOF p3", authorizedUsers);

        repository.printBranches();

        repository.setActiveBranch("main");
        repository.addCommit(new ChangeCommit("System", "no comment", new ArrayList<>()));
        repository.addCommit(new ChangeCommit("System", "Decorator interface", new ArrayList<>()));
        repository.addCommit(new ChangeCommit("System", "Merging previous commits", new ArrayList<>()));

        repository.printBranches();

        repository.addBranch("Solving issue #1");
        repository.setActiveBranch("Solving issue #1");
        repository.addCommit(new ChangeCommit("System", "Solving the issue", new ArrayList<>()));

        repository.printBranches();

        repository.mergeBranches("Solving issue #1", "main");

        repository.printBranches();
    }
}