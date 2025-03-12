package commit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import code.AddChange;
import code.Change;
import code.RemoveChange;

public class ChangeCommit {
    protected String commitId; // Identificador único del commit
    protected String author; // Autor del commit
    protected LocalDateTime date; // Fecha y hora del commit
    protected String description; // Descripción del commit
    private List<Change> changes; // Lista de cambios incluidos en el commit
    private static int commitCounter = 1; // Contador para generar identificadores únicos
    protected static String defaultDescription = "no comment"; // Descripción por defecto para los commits
    protected static String defaultAuthor = "John Doe"; // Autor por defecto para los commits

    // Constructor: Crea un nuevo commit.
    // Recibe el autor, la descripción y la lista de cambios.
    public ChangeCommit(String author, String description, List<Change> changes) {
        this.commitId = String.format("%05d", commitCounter++) + UUID.randomUUID().toString().replace("-", "").substring(0, 15); // Genera un identificador único
        this.author = author; // Asigna el autor
        this.date = LocalDateTime.now(); // Asigna la fecha y hora actual
        this.description = description; // Asigna la descripción
        this.changes = changes; // Asigna la lista de cambios
    }

    // Constructor: Crea un nuevo commit con la lista de cambios.
    // Utiliza el autor y la descripción por defecto.
    public ChangeCommit(List<Change> changes){
        this(defaultAuthor, defaultDescription, changes);
    }

    // Constructor: Crea un nuevo commit con el autor y la lista de cambios.
    // Utiliza la descripción por defecto.
    public ChangeCommit(String author, List<Change> changes){
        this(author, defaultDescription, changes);
    }

    // Constructor: Crea un nuevo commit sin parámetros.
    // Utiliza el autor y la descripción por defecto y una lista de cambios vacía.
    public ChangeCommit(){
        this(defaultAuthor, defaultDescription, new ArrayList<Change>());
    }

    // Calcula el número total de líneas modificadas en el commit.
    public int getTotalLinesChanged() {
        int total = 0;
        for (Change change : changes) {
            if (change instanceof AddChange) { // Si el cambio es de tipo AddChange
                total += ((AddChange) change).getNumberOfLines(); // Suma el número de líneas añadidas
            } else if (change instanceof RemoveChange) { // Si el cambio es de tipo RemoveChange
                total -= ((RemoveChange) change).getNumberOfLines(); // Resta el número de líneas eliminadas
            }
        }
        return total;
    }

    // Devuelve la lista de cambios del commit.
    public List<Change> getChanges() {
        return changes;
    }

    // Devuelve el autor del commit.
    public String getAuthor() {
        return author;
    }

    // Devuelve el identificador único del commit.
    public String getCommitId() {
        return commitId;
    }

    // Devuelve la descripción del commit.
    public String getDescription() {
        return description;
    }

    // Devuelve la fecha y hora del commit.
    public LocalDateTime getDate() {
        return date;
    }

    // Sobreescribe el método toString() de la clase Object.
    // Devuelve una representación en cadena del objeto ChangeCommit, incluyendo el identificador, el autor, la fecha, la descripción y los cambios.
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Formateador para la fecha
        StringBuilder sb = new StringBuilder();
        sb.append("commit ").append(commitId).append("\n"); // Agrega el identificador del commit
        sb.append("Author: ").append(author).append("\n"); // Agrega el autor del commit
        sb.append("Date: ").append(date.format(formatter)).append("\n"); // Agrega la fecha del commit formateada
        sb.append("Description: ").append(description).append("\n"); // Agrega la descripción del commit
        for (Change change : changes) {
            sb.append(changeToString(change)).append("\n"); // Agrega la información de cada cambio
        }
        return sb.toString();
    }

    // Método auxiliar: Devuelve una representación en cadena de un objeto Change.
    // Incluye el tipo de cambio, la ruta del archivo y la diferencia en el número de líneas.
    private String changeToString(Change change) {
        String lineChange = " (0)"; // Diferencia por defecto en el número de líneas
        if (change instanceof AddChange) { // Si el cambio es de tipo AddChange
            lineChange = " (+" + ((AddChange) change).getNumberOfLines() + ")"; // Agrega la diferencia positiva
        } else if (change instanceof RemoveChange) { // Si el cambio es de tipo RemoveChange
            lineChange = " (-" + ((RemoveChange) change).getNumberOfLines() + ")"; // Agrega la diferencia negativa
        }
        return change.type + " : " + change.filePath + lineChange; // Devuelve la información del cambio formateada
    }
}