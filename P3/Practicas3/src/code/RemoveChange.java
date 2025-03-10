package code;

import code.Change;

public class RemoveChange extends Change {
    private int endLine;

    public RemoveChange(int startLine, int endLine, String filePath) {
        super("-", startLine, filePath);
        this.endLine = endLine;
    }

    @Override
    public String toString() {
        return super.toString() + ", end line=" + endLine + "}";
    }

    @Override
    public int getNumberOfLines() {
        return endLine - startLine + 1;
    }
}