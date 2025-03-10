package commit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import code.AddChange;
import code.Change;
import code.RemoveChange;

public class ChangeCommit {
    protected String commitId;
    protected String author;
    protected LocalDateTime date;
    protected String description;
    private List<Change> changes;
    private static int commitCounter = 1;
    protected static String defaultDescription = "no comment";
    protected static String defaultAuthor = "John Doe";

    public ChangeCommit(String author, String description, List<Change> changes) {
        this.commitId = String.format("%05d", commitCounter++) + UUID.randomUUID().toString().replace("-", "").substring(0, 15);
        this.author = author;
        this.date = LocalDateTime.now();
        this.description = description;
        this.changes = changes;
    }

    public ChangeCommit(List<Change> changes){
        this(defaultAuthor, defaultDescription, changes);
    }

    public ChangeCommit(String author, List<Change> changes){
        this(author, defaultDescription, changes);
    }

    public ChangeCommit(){
        this(defaultAuthor, defaultDescription, new ArrayList<Change>());
    }

    public int getTotalLinesChanged() {
        int total = 0;
        for (Change change : changes) {
            if (change instanceof AddChange) {
                total += ((AddChange) change).getNumberOfLines();
            } else if (change instanceof RemoveChange) {
                total -= ((RemoveChange) change).getNumberOfLines();
            }
        }
        return total;
    }

    public List<Change> getChanges() {
        return changes;
    }
    
    public String getAuthor() {
        return author;
    }

    public String getCommitId() {
        return commitId;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        StringBuilder sb = new StringBuilder();
        sb.append("commit ").append(commitId).append("\n");
        sb.append("Author: ").append(author).append("\n");
        sb.append("Date: ").append(date.format(formatter)).append("\n");
        sb.append("Description: ").append(description).append("\n");
        for (Change change : changes) {
            sb.append(changeToString(change)).append("\n");
        }
        return sb.toString();
    }

    private String changeToString(Change change) {
        String lineChange = " (0)";
        if (change instanceof AddChange) {
            lineChange = " (+" + ((AddChange) change).getNumberOfLines() + ")";
        } else if (change instanceof RemoveChange) {
            lineChange = " (-" + ((RemoveChange) change).getNumberOfLines() + ")";
        }
        return change.type + " : " + change.filePath + lineChange;
    }
}
