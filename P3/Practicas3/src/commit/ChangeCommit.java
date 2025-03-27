package commit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import change.AddChange;
import change.Change;
import change.RemoveChange;

/**
 * Clase ChangeCommit.
 * Representa un commit de cambios en un sistema de control de versiones.
 *
 * @author Christian Grosso, Marco Paparella
 * @version 1.0
 */
public class ChangeCommit {
    private String commitId; // Identificador único del commit
    private String author; // Autor del commit
    private LocalDateTime date; // Fecha y hora del commit
    private String description; // Descripción del commit
    private List<Change> changes; // Lista de cambios incluidos en el commit
    private static int commitCounter = 1; // Contador para generar identificadores únicos
    private static String defaultDescription = "no comment"; // Descripción por defecto
    private static String defaultAuthor = "John Doe"; // Autor por defecto

    /**
     * Constructor de la clase ChangeCommit.
     *
     * @param author Autor del commit.
     * @param description Descripción del commit.
     * @param changes Lista de cambios incluidos en el commit.
     */
    public ChangeCommit(String author, String description, List<Change> changes) {
        this.commitId = String.format("%05d", commitCounter++) + UUID.randomUUID().toString().replace("-", "").substring(0, 15);
        this.author = author;
        this.date = LocalDateTime.now();
        this.description = description;
        this.changes = changes;
    }

    public ChangeCommit(List<Change> changes) { this(getDefaultAuthor(), getDefaultDescription(), changes); }
    public ChangeCommit(String author, List<Change> changes) { this(author, getDefaultDescription(), changes); }
    public ChangeCommit() { this(getDefaultAuthor(), getDefaultDescription(), new ArrayList<Change>()); }

<<<<<<< HEAD
    // Constructor: Crea un nuevo commit con el autor y la lista de cambios.
    // Utiliza la descripción por defecto.
    
    public ChangeCommit(String author, List<Change> changes){
        this(author, getDefaultDescription(), changes);
    }

    // Constructor: Crea un nuevo commit sin parámetros.
    // Utiliza el autor y la descripción por defecto y una lista de cambios vacía.
    public ChangeCommit(){
        this(getDefaultAuthor(), getDefaultDescription(), new ArrayList<Change>());
    }

    // Calcula el número total de líneas modificadas en el commit.
=======
    /**
     * Calcula el número total de líneas modificadas en el commit.
     *
     * @return Número total de líneas cambiadas.
     */
>>>>>>> 7cc839b223729c3b7110e91b50b92fa679e67792
    public int getTotalLinesChanged() {
        int total = 0;
        for (Change c : changes) {
            if (c instanceof AddChange) { total += ((AddChange) c).getNumberOfLines(); }
            else if (c instanceof RemoveChange) { total -= ((RemoveChange) c).getNumberOfLines(); }
        }
        return total;
    }

    public List<Change> getChanges() { return changes; }
    public String getAuthor() { return author; }
    public String getCommitId() { return commitId; }
    public String getDescription() { return description; }
    public LocalDateTime getDate() { return date; }
    public void setCommitId(String commitId) { this.commitId = commitId; }
    public void setAuthor(String author) { this.author = author; }
    public void setDate(LocalDateTime date) { this.date = date; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        StringBuilder sb = new StringBuilder();
        sb.append("commit ").append(commitId).append("\n");
        sb.append("Author: ").append(author).append("\n");
        sb.append("Date: ").append(date.format(formatter)).append("\n");
        sb.append("Description: ").append(description).append("\n");
        for (Change change : changes) { sb.append(changeToString(change)).append("\n"); }
        return sb.toString();
    }

    private String changeToString(Change change) {
        String lineChange = " (0)";
        if (change instanceof AddChange) { lineChange = " (+" + ((AddChange) change).getNumberOfLines() + ")"; }
        else if (change instanceof RemoveChange) { lineChange = " (-" + ((RemoveChange) change).getNumberOfLines() + ")"; }
        return change.getType() + " : " + change.getFilePath() + lineChange;
    }

    public static String getDefaultAuthor() { return defaultAuthor; }
    public static void setDefaultAuthor(String defaultAuthor) { ChangeCommit.defaultAuthor = defaultAuthor; }
    public static String getDefaultDescription() { return defaultDescription; }
    public static void setDefaultDescription(String defaultDescription) { ChangeCommit.defaultDescription = defaultDescription; }
}