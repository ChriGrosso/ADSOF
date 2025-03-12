package code;

public abstract class Change {
    private String type; // Tipo de cambio (+, /, -)
    private String filePath; // Ruta del archivo
    private int startLine; // Línea de inicio del cambio

    // Constructor: Crea un nuevo cambio.
    // Recibe el tipo de cambio, la línea de inicio y la ruta del archivo.
    public Change(String type, int startLine, String filePath) {
        this.setType(type); // Asigna el tipo de cambio
        this.setStartLine(startLine); // Asigna la línea de inicio
        this.setFilePath(filePath); // Asigna la ruta del archivo
    }

    // Devuelve una representación en cadena del objeto Change.
    @Override
    public String toString() {
        return "{\n\ttype=" + getType() + ",\n\tstart line=" + getStartLine() + ",\n\tfile path='" + getFilePath() + "'";
    }

    // Método abstracto: Devuelve el número de líneas afectadas por el cambio.
    // Este método debe ser implementado por las subclases.
    public abstract int getNumberOfLines();

	/**
	 * @return the startLine
	 */
	public int getStartLine() {
		return startLine;
	}

	/**
	 * @param startLine the startLine to set
	 */
	public void setStartLine(int startLine) {
		this.startLine = startLine;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}