package code;

public class AddChange extends Change {
    private String content;
    private int numberOfLines;

    public AddChange(int startLine, String filePath, String content) {
        super("+", startLine, filePath);
        this.content = content;
        this.numberOfLines = content.split("\n").length;
    }

    @Override
    public String toString() {
        return super.toString() + ", content='" + content.replace("\n", "\\n") + "', number of lines=" + numberOfLines + "}";
    }

    @Override
    public int getNumberOfLines() {
        return numberOfLines;
    }
}