package branches;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import commit.ChangeCommit;

/**
 * Clase Branch.
 * Representa una rama en un sistema de control de versiones.
 *
 * @author Christian Grosso, Marco Paparella
 * @version 1.0
 */
public class Branch {
    private String name; // Nombre de la rama
    private List<ChangeCommit> commits; // Lista de commits que contiene la rama

    /**
     * Constructor de la clase Branch.
     *
     * @param name Nombre de la rama.
     */
    public Branch(String name) {
        this.name = name; // Asigna el nombre a la rama
        this.commits = new ArrayList<>(); // Inicializa la lista de commits como vacía
    }

    /**
     * Constructor que crea una nueva rama basada en una rama existente.
     *
     * @param name Nombre de la nueva rama.
     * @param sourceBranch Rama fuente de la que se copiarán los commits.
     */
    public Branch(String name, Branch sourceBranch) {
        this.name = name; // Asigna el nombre a la nueva rama
        this.commits = new ArrayList<>(sourceBranch.getCommits()); // Copia los commits de la rama fuente a la nueva rama
    }

    /**
     * Agrega un commit a la rama.
     *
     * @param commit Objeto ChangeCommit que representa un cambio en la rama.
     */
    public void addCommit(ChangeCommit commit) {
        commits.add(commit); // Agrega un commit a la lista de commits de la rama
    }

    /**
     * Obtiene la lista de commits de la rama.
     *
     * @return Lista de objetos ChangeCommit.
     */
    public List<ChangeCommit> getCommits() {
        return commits; // Devuelve la lista de commits de la rama
    }

    /**
     * Devuelve una representación en cadena de la rama.
     *
     * @return Representación de la rama en formato de texto.
     */
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

    /**
     * Obtiene el nombre de la rama.
     *
     * @return Nombre de la rama.
     */
    public String getName() {
        return name;
    }
}
