package commit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import code.Change;

public class MergeCommit extends ChangeCommit {
    private List<ChangeCommit> mergedCommits;

    public MergeCommit(String author, String description, List<ChangeCommit> mergedCommits) {
        super(author, description, new ArrayList<Change>());
        this.mergedCommits = mergedCommits;
    }

    public MergeCommit(List<ChangeCommit> mergedCommits){
        this(defaultAuthor, defaultDescription, mergedCommits);
    }

    @Override
    public int getTotalLinesChanged() {
        int total = 0;
        for (ChangeCommit commit : mergedCommits) {
            total += commit.getTotalLinesChanged();
        }
        return total;
    }

    @Override
    public List<Change> getChanges() {
        List<Change> allChanges = new ArrayList<>();
        for (ChangeCommit commit : mergedCommits) {
            allChanges.addAll(commit.getChanges());
        }
        return allChanges;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        StringBuilder sb = new StringBuilder();
        sb.append("commit ").append(commitId).append("\n");
        sb.append("Author: ").append(author).append("\n");
        sb.append("Date: ").append(date.format(formatter)).append("\n");
        sb.append("Description: ").append(description).append("\n");
        sb.append("Merged commits:").append("\n");
        for (ChangeCommit commit : mergedCommits) {
            sb.append(commit.commitId).append(" on ").append(commit.date.format(formatter)).append("\n");
        }
        return sb.toString();
    }
    
    @Override
    public String getAuthor() {
        return super.getAuthor();
    }

    @Override
    public String getCommitId() {
        return super.getCommitId();
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    @Override
    public LocalDateTime getDate() {
        return super.getDate();
    }
}