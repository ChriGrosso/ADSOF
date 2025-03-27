package commit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import commit.ChangeCommit;
import change.Change;

/**
 * Clase MergeCommit.
 * Representa un commit de fusión en un sistema de control de versiones.
 *
 * @author Christian Grosso, Marco Paparella
 * @version 1.0
 */
public class MergeCommit extends ChangeCommit {
    private List<ChangeCommit> mergedCommits; // Lista de commits fusionados

    /**
     * Constructor de la clase MergeCommit.
     *
     * @param author Autor del commit.
     * @param description Descripción del commit.
     * @param mergedCommits Lista de commits que se fusionan.
     * @param changes Lista de cambios resultantes.
     */
    public MergeCommit(String author, String description, List<ChangeCommit> mergedCommits, List<Change> changes) {
        super(author, description, changes);
        this.mergedCommits = mergedCommits;
    }

    public MergeCommit(String author, String description, List<ChangeCommit> mergedCommits, List<Change> changes, LocalDateTime date, String commitId) {
        super(author, description, changes);
        this.mergedCommits = mergedCommits;
        this.setDate(date);
        this.setCommitId(commitId);
    }

    public MergeCommit(String author, String description, List<ChangeCommit> commits) {
        this(author, description, commits, new ArrayList<Change>());
    }

    /**
     * Calcula el número total de líneas modificadas en el merge commit.
     *
     * @return Número total de líneas cambiadas.
     */
    @Override
    public int getTotalLinesChanged() {
        int total = 0;
        for (ChangeCommit commit : mergedCommits) { total += commit.getTotalLinesChanged(); }
        return total;
    }

    @Override
    public List<Change> getChanges() {
        List<Change> allChanges = new ArrayList<>();
        for (ChangeCommit commit : mergedCommits) { allChanges.addAll(commit.getChanges()); }
        return allChanges;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        StringBuilder sb = new StringBuilder();
        sb.append("commit ").append(this.getCommitId()).append("\n");
        sb.append("Author: ").append(this.getAuthor()).append("\n");
        sb.append("Date: ").append(this.getDate().format(formatter)).append("\n");
        sb.append("Description: ").append(this.getDescription()).append("\n");
        sb.append("Merged commits:").append("\n");
        for (ChangeCommit commit : mergedCommits) {
            sb.append(commit.getCommitId()).append(" on ").append(commit.getDate().format(formatter)).append("\n");
        }
        return sb.toString();
    }
}