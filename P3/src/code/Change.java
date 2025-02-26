package code;

public abstract class Change {
	private int fromLine;
	private String path;		
	
	public Change(int fromLine, String path) {
		this.fromLine=fromLine;
		this.path=path;
	}
}
