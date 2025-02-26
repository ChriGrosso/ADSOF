package code;

public class RemoveChange extends Change {
	
	private int toLine;
	
	public RemoveChange(int fromLine, int toLine, String path) {
		super(fromLine,path);
		this.setToLine(toLine);
	}

	public int getToLine() {
		return toLine;
	}

	public void setToLine(int toLine) {
		this.toLine = toLine;
	}

}
