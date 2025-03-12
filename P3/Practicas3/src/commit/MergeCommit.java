package commit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import code.Change;

public class MergeCommit extends ChangeCommit {
    private List<ChangeCommit> mergedCommits; // Lista de commits fusionados en este commit

    // Constructor: Crea un nuevo commit de mezcla.
    // Recibe el autor, la descripción y la lista de commits fusionados.
    // Llama al constructor de la superclase ChangeCommit.
    public MergeCommit(String author, String description, List<ChangeCommit> mergedCommits) {
        super(author, description, new ArrayList<Change>()); // Llama al constructor de la superclase ChangeCommit con una lista de cambios vacía
        this.mergedCommits = mergedCommits; // Asigna la lista de commits fusionados
    }

    // Constructor: Crea un nuevo commit de mezcla con la lista de commits fusionados.
    // Utiliza el autor y la descripción por defecto.
    public MergeCommit(List<ChangeCommit> mergedCommits){
        this(defaultAuthor, defaultDescription, mergedCommits);
    }

    // Sobreescribe el método getTotalLinesChanged() de la superclase ChangeCommit.
    // Calcula el número total de líneas modificadas en este commit de mezcla, sumando las líneas modificadas por los commits fusionados.
    @Override
    public int getTotalLinesChanged() {
        int total = 0;
        for (ChangeCommit commit : mergedCommits) {
            total += commit.getTotalLinesChanged(); // Suma las líneas modificadas por cada commit fusionado
        }
        return total;
    }

    // Sobreescribe el método getChanges() de la superclase ChangeCommit.
    // Devuelve una lista con todos los cambios incluidos en los commits fusionados.
    @Override
    public List<Change> getChanges() {
        List<Change> allChanges = new ArrayList<>();
        for (ChangeCommit commit : mergedCommits) {
            allChanges.addAll(commit.getChanges()); // Agrega todos los cambios de cada commit fusionado a la lista
        }
        return allChanges;
    }

    // Sobreescribe el método toString() de la superclase ChangeCommit.
    // Devuelve una representación en cadena del objeto MergeCommit, incluyendo el identificador, el autor, la fecha, la descripción y la lista de commits fusionados.
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Formateador para la fecha
        StringBuilder sb = new StringBuilder();
        sb.append("commit ").append(commitId).append("\n"); // Agrega el identificador del commit
        sb.append("Author: ").append(author).append("\n"); // Agrega el autor del commit
        sb.append("Date: ").append(date.format(formatter)).append("\n"); // Agrega la fecha del commit formateada
        sb.append("Description: ").append(description).append("\n"); // Agrega la descripción del commit
        sb.append("Merged commits:").append("\n"); // Agrega el encabezado para los commits fusionados
        for (ChangeCommit commit : mergedCommits) {
            sb.append(commit.commitId).append(" on ").append(commit.date.format(formatter)).append("\n"); // Agrega la información de cada commit fusionado
        }
        return sb.toString();
    }

    // Sobreescribe el método getAuthor() de la superclase ChangeCommit.
    // Devuelve el autor del commit.
    @Override
    public String getAuthor() {
        return super.getAuthor(); // Llama al método de la superclase
    }

    // Sobreescribe el método getCommitId() de la superclase ChangeCommit.
    // Devuelve el identificador único del commit.
    @Override
    public String getCommitId() {
        return super.getCommitId(); // Llama al método de la superclase
    }

    // Sobreescribe el método getDescription() de la superclase ChangeCommit.
    // Devuelve la descripción del commit.
    @Override
    public String getDescription() {
        return super.getDescription(); // Llama al método de la superclase
    }

    // Sobreescribe el método getDate() de la superclase ChangeCommit.
    // Devuelve la fecha y hora del commit.
    @Override
    public LocalDateTime getDate() {
        return super.getDate(); // Llama al método de la superclase
    }
}