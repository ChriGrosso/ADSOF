package code;

public abstract class Change {
    public String type;
    public String filePath;
    protected int startLine;

    public Change(String type, int startLine, String filePath) {
        this.type = type;
        this.startLine = startLine;
        this.filePath = filePath;
    }

    public String toString() {
        return "{type=" + type + ", start line=" + startLine + ", file path='" + filePath + "'";
    }

    public abstract int getNumberOfLines(); 
}
