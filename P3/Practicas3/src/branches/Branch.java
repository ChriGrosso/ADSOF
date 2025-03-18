package branches;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import code.AddChange;
import code.Change;
import code.RemoveChange;
import commit.ChangeCommit;

public class Branch {
    private String name; // Nombre de la rama
    private List<ChangeCommit> commits; // Lista de commits que contiene la rama

    public Branch(String name) {
        this.name = name; // Asigna el nombre a la rama
        this.commits = new ArrayList<>(); // Inicializa la lista de commits como vacía
    }

    public Branch(String name, Branch sourceBranch) {
        this.name = name; // Asigna el nombre a la nueva rama
        this.commits = new ArrayList<>(sourceBranch.getCommits()); // Copia los commits de la rama fuente a la nueva rama
    }

    public void addCommit(ChangeCommit commit) {
        commits.add(commit); // Agrega un commit a la lista de commits de la rama
    }

    public List<ChangeCommit> getCommits() {
        return commits; // Devuelve la lista de commits de la rama
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Branch: ").append(name); // Agrega el nombre de la rama a la cadena de salida
        if (commits.size() > 0 && !name.equals(commits.get(0).getAuthor())){ // Verifica si la rama tiene commits y si el nombre no es igual al autor del primer commit
            sb.append(" (from ").append(commits.get(0).getAuthor()).append(")"); // Agrega información sobre la rama de origen
        }
        sb.append("\n").append(commits.size()).append(" commits:\n"); // Agrega el número de commits a la cadena de salida
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Crea un formateador de fechas
        for (ChangeCommit commit : commits) {
            String commitId = commit.getCommitId().substring(0, 5); // Obtiene los primeros 5 caracteres del ID del commit
            String description = commit.getDescription().substring(0, Math.min(30, commit.getDescription().length())); // Obtiene los primeros 30 caracteres de la descripción
            String date = commit.getDate().format(formatter); // Formatea la fecha del commit
            sb.append(String.format("%-6s %-31s %s\n", commitId, description, date)); // Agrega la información del commit formateada a la cadena de salida
        }
        return sb.toString(); // Devuelve la cadena de salida formateada
    }

	public String getName() {
		return name;
	}
}