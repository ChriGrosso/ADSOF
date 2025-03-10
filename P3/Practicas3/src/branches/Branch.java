package branches;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import code.AddChange;
import code.Change;
import code.RemoveChange;
import commit.ChangeCommit;


public class Branch {
	private String name;
	private List<ChangeCommit> commits;

	public Branch(String name) {
		this.name = name;
		this.commits = new ArrayList<>();
	}

	public Branch(String name, Branch sourceBranch) {
		this.name = name;
		this.commits = new ArrayList<>(sourceBranch.getCommits());
	}

	public void addCommit(ChangeCommit commit) {
		commits.add(commit);
	}

	public List<ChangeCommit> getCommits() {
		return commits;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Branch: ").append(name);
		if (commits.size() > 0 && !name.equals(commits.get(0).getAuthor())){
			sb.append(" (from ").append(commits.get(0).getAuthor()).append(")");
		}
		sb.append("\n").append(commits.size()).append(" commits:\n");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		for (ChangeCommit commit : commits) {
			String commitId = commit.getCommitId().substring(0, 5);
			String description = commit.getDescription().substring(0, Math.min(30, commit.getDescription().length()));
			String date = commit.getDate().format(formatter);
			sb.append(String.format("%-6s %-31s %s\n", commitId, description, date));
		}
		return sb.toString();
	}
}