package usuarios;

import java.util.ArrayList;
import java.util.List;

public class GestorUsuarios {
    private List<Usuario> usuarios = new ArrayList<>();

    public void registrarUsuario(Usuario usuario) throws UsuarioExistenteException {
        if (usuarios.stream().anyMatch(u -> u.getIdentificador().equals(usuario.getIdentificador()))) {
            throw new UsuarioExistenteException("El usuario ya existe.");
        }
        usuarios.add(usuario);
    }

    public Usuario obtenerUsuarioPorNombre(String nombre) {
        return usuarios.stream().filter(u -> u.getNombre().equals(nombre)).findFirst().orElse(null);
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarios;
    }
}