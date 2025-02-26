package code;

public class ModifyChange extends Change {

	private int toLine;
	private String modify;
	
	public ModifyChange(int fromLine, int toLine, String path, String modify) {
		super(fromLine,path);
		this.setToLine(toLine);
		this.setModify(modify);
		
	}

	public int getToLine() {
		return toLine;
	}

	public void setToLine(int toLine) {
		this.toLine = toLine;
	}

	public String getModify() {
		return modify;
	}

	public void setModify(String modify) {
		this.modify = modify;
	}

}
