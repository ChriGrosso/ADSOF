package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import branches.Branch;
import commit.ChangeCommit;
import commit.MergeCommit;

public class Repository {
    private String name; // Nombre del repositorio
    private Map<String, Branch> branches; // Mapa de ramas del repositorio
    private Branch activeBranch; // Rama activa del repositorio
    private List<String> authorizedUsers; // Lista de usuarios autorizados para hacer commits

    // Constructor: Crea un nuevo repositorio.
    public Repository(String name, List<String> authorizedUsers) {
        this.name = name; // Asigna el nombre al repositorio
        this.branches = new HashMap<>(); // Inicializa el mapa de ramas
        this.authorizedUsers = authorizedUsers; // Asigna la lista de usuarios autorizados
        createMainBranch(); // Crea la rama principal "main"
    }

    // Método privado: Crea la rama principal "main".
    private void createMainBranch() {
        Branch mainBranch = new Branch("main"); // Crea una nueva rama llamada "main"
        branches.put("main", mainBranch); // Agrega la rama al mapa de ramas
        activeBranch = mainBranch; // Establece la rama "main" como la rama activa
    }

    // Método: Agrega una nueva rama al repositorio.
    public void addBranch(String branchName) {
        Branch newBranch = new Branch(branchName, activeBranch); // Crea una nueva rama a partir de la rama activa
        branches.put(branchName, newBranch); // Agrega la nueva rama al mapa de ramas
    }

    // Método: Establece la rama activa del repositorio.
    public void setActiveBranch(String branchName) {
        if (branches.containsKey(branchName)) { // Verifica si la rama existe
            activeBranch = branches.get(branchName); // Establece la rama como la rama activa
        }
    }

    // Método: Agrega un commit a la rama activa.
    public void addCommit(ChangeCommit commit) {
        if (authorizedUsers.contains(commit.getAuthor())) { // Verifica si el usuario está autorizado
            activeBranch.addCommit(commit); // Agrega el commit a la rama activa
        }
    }
    
    public void mergeBranches(String originBranchName, String destinationBranchName) {
        Branch originBranch = branches.get(originBranchName);
        Branch destinationBranch = branches.get(destinationBranchName);

        if (originBranch == null || destinationBranch == null) {
            return;
        }

        ChangeCommit lastCommonCommit = findLastCommonCommit(originBranch, destinationBranch);

        if (lastCommonCommit == null) {
            return;
        }

        List<ChangeCommit> originCommitsToMerge = getCommitsAfter(originBranch, lastCommonCommit);

        mergeCommits(destinationBranch, originCommitsToMerge, originBranchName, destinationBranchName);
    }

    private ChangeCommit findLastCommonCommit(Branch originBranch, Branch destinationBranch) {
        List<ChangeCommit> originCommits = originBranch.getCommits();
        List<ChangeCommit> destinationCommits = destinationBranch.getCommits();

        for (int i = originCommits.size() - 1; i >= 0; i--) {
            ChangeCommit originCommit = originCommits.get(i);
            for (int j = destinationCommits.size() - 1; j >= 0; j--) {
                ChangeCommit destinationCommit = destinationCommits.get(j);
                if (originCommit.getCommitId().equals(destinationCommit.getCommitId())) {
                    return originCommit;
                }
            }
        }
        return null;
    }

    private List<ChangeCommit> getCommitsAfter(Branch branch, ChangeCommit commit) {
        List<ChangeCommit> commits = branch.getCommits();
        int index = commits.indexOf(commit);
        if (index == -1 || index == commits.size() - 1) {
            return new ArrayList<>();
        }
        return commits.subList(index + 1, commits.size());
    }

    private void mergeCommits(Branch destinationBranch, List<ChangeCommit> commits, String originBranchName, String destinationBranchName) {
        MergeCommit mergeCommit = new MergeCommit("John Doe", "Merge branches " + originBranchName + " and " + destinationBranchName, commits);
        destinationBranch.addCommit(mergeCommit);
    }

    // Sobreescribe el método toString() para imprimir la información del repositorio.
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Repository: ").append(name).append("\n"); // Agrega el nombre del repositorio
        sb.append("Branches:\n"); // Agrega el encabezado de las ramas
        for (String branchName : branches.keySet()) {
            sb.append("- ").append(branchName); // Agrega el nombre de la rama
            if (branches.get(branchName) == activeBranch) {
                sb.append(" (active)"); // Indica si la rama está activa
            }
            sb.append("\n");
        }
        sb.append("Branch: ").append(activeBranch.getName()).append("\n"); // Agrega el nombre de la rama activa
        sb.append(activeBranch.toString()); // Agrega la información de la rama activa
        return sb.toString();
    }
}