package code;

public class AddChange extends Change {
	private String text;
	
	public AddChange(int fromLine, String path, String text) {
		super(fromLine, path);
		this.setText(text);
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
