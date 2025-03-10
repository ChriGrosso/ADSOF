package tester;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import code.AddChange;
import code.Change;
import code.ModifyChange;
import code.RemoveChange;
import commit.ChangeCommit;
import commit.MergeCommit;


public class CommitTester {
    public static void main(String[] args) {
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

        System.out.println(commit1);
        System.out.println(commit2);
        System.out.println(mergeCommit);
        System.out.println("Total lines changed in commit1: " + commit1.getTotalLinesChanged());
        System.out.println("Total lines changed in commit2: " + commit2.getTotalLinesChanged());
        System.out.println("Total lines changed in mergeCommit: " + mergeCommit.getTotalLinesChanged());
    }
}