package code;

public class ModifyChange extends Change {
    private String content;
    private int numberOfLines;
    private int endLine;

    public ModifyChange(int startLine, int endLine, String filePath, String content) {
        super("/", startLine, filePath);
        this.content = content;
        this.numberOfLines = content.split("\n").length;
        this.endLine = endLine;
    }

    @Override
    public String toString() {
        return super.toString() + ", content='" + content.replace("\n", "\\n") + "', number of lines=" + numberOfLines + ", end line=" + endLine + "}";
    }

    @Override
    public int getNumberOfLines() {
        return numberOfLines;
    }
}