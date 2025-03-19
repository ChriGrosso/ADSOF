package repository;

import branches.Branch;
import commit.ChangeCommit;
import commit.MergeCommit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase Repository.
 * Representa un repositorio en un sistema de control de versiones.
 *
 * @author Christian Grosso, Marco Paparella
 * @version 1.0
 */
public class Repository {
    private String name; // Nombre del repositorio
    private Map<String, Branch> branches; // Mapa de ramas en el repositorio
    private Branch activeBranch; // Rama activa del repositorio

    /**
     * Constructor de la clase Repository.
     *
     * @param name Nombre del repositorio.
     * @param authorizedUsers Lista de usuarios autorizados (no utilizada en esta implementación).
     */
    public Repository(String name, List<String> authorizedUsers) {
        this.name = name;
        this.branches = new HashMap<>();
        this.activeBranch = null;

        // Creación automática de la rama principal
        addBranch("main");
        setActiveBranch("main");
    }

    /**
     * Agrega una rama al repositorio.
     *
     * @param branch Objeto Branch que representa la rama a agregar.
     */
    public void addBranch(Branch branch) {
        branches.put(branch.getName(), branch);
        if (activeBranch == null) {
            activeBranch = branch;
        }
    }

    /**
     * Crea y agrega una nueva rama al repositorio.
     *
     * @param branchName Nombre de la nueva rama.
     */
    public void addBranch(String branchName) {
        if (!branches.containsKey(branchName)) {
            Branch newBranch = new Branch(branchName);
            branches.put(branchName, newBranch);
            if (activeBranch == null) {
                activeBranch = newBranch;
            }
        } else {
            System.out.println("Branch ya existente: " + branchName);
        }
    }

    /**
     * Establece la rama activa en el repositorio.
     *
     * @param branchName Nombre de la rama a activar.
     */
    public void setActiveBranch(String branchName) {
        if (branches.containsKey(branchName)) {
            this.activeBranch = branches.get(branchName);
        } else {
            System.out.println("Branch no existente: " + branchName);
        }
    }

    /**
     * Establece la rama activa utilizando un objeto Branch.
     *
     * @param branch Objeto Branch a activar.
     */
    public void setActiveBranch(Branch branch) {
        if (branch != null && branches.containsKey(branch.getName())) {
            this.activeBranch = branch;
        } else {
            System.out.println("Branch no existente: " + (branch != null ? branch.getName() : "null"));
        }
    }

    /**
     * Obtiene una rama específica del repositorio.
     *
     * @param name Nombre de la rama.
     * @return Objeto Branch correspondiente al nombre especificado.
     */
    public Branch getBranch(String name) {
        return branches.get(name);
    }

    /**
     * Imprime la lista de ramas en el repositorio.
     */
    public void printBranches() {
        System.out.println("Repositorio: " + name);
        System.out.println("Branches:");
        for (String branchName : branches.keySet()) {
            String activeMarker = (activeBranch != null && activeBranch.getName().equals(branchName)) ? " (active)" : "";
            System.out.println("- " + branchName + activeMarker);
        }

        for (Branch branch : branches.values()) {
            System.out.println("Branch: " + branch.getName() + (branch.equals(activeBranch) ? " (active)" : ""));
            List<ChangeCommit> commits = branch.getCommits();
            if (commits.isEmpty()) {
                System.out.println("(Ningún commit presente)");
            } else {
                for (ChangeCommit commit : commits) {
                    System.out.println(commit.getCommitId() + " - " + commit.getDescription());
                }
            }
        }
    }

    /**
     * Agrega un commit a la rama activa.
     *
     * @param commit Objeto ChangeCommit a agregar.
     */
    public void addCommit(ChangeCommit commit) {
        if (activeBranch != null) {
            activeBranch.addCommit(commit);
        } else {
            System.out.println("Ninguna rama activa. No se puede agregar el commit.");
        }
    }

    /**
     * Fusiona dos ramas dentro del repositorio.
     *
     * @param sourceBranch Nombre de la rama de origen.
     * @param targetBranch Nombre de la rama de destino.
     */
    public void mergeBranches(String sourceBranch, String targetBranch) {
        if (!branches.containsKey(sourceBranch) || !branches.containsKey(targetBranch)) {
            System.out.println("Una de las ramas especificadas no existe.");
            return;
        }

        Branch source = branches.get(sourceBranch);
        Branch target = branches.get(targetBranch);
        List<ChangeCommit> sourceCommits = source.getCommits();

        MergeCommit mergeCommit = new MergeCommit("System", "Merge branches " + sourceBranch + " into " + targetBranch, sourceCommits, new java.util.ArrayList<>());
        target.addCommit(mergeCommit);

        setActiveBranch(target);
        System.out.println("Merge completado entre " + sourceBranch + " y " + targetBranch);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Repository: ").append(name).append("\\n");
        sb.append("Active Branch: ").append(activeBranch != null ? activeBranch.getName() : "None").append("\\n");
        sb.append("Branches: \\n");
        for (String branchName : branches.keySet()) {
            sb.append("- ").append(branchName);
            if (activeBranch != null && activeBranch.getName().equals(branchName)) {
                sb.append(" (active)");
            }
            sb.append("\\n");
        }
        return sb.toString();
    }
}
