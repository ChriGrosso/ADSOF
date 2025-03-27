package usuarios;

import java.util.ArrayList;
import java.util.List;

import proyectos.GestorProyectos;
import proyectos.Proyecto;

public class Asociacion extends Usuario {
    private Ciudadano representante;
    private List<Usuario> miembros = new ArrayList<>();

    public Asociacion(String nombre, String password, Ciudadano representante) {
        super(nombre, password);
        this.representante = representante;
    }

    public void agregarMiembro(Usuario usuario) throws AsociacionException {
        if (miembros.contains(usuario)) {
            throw new AsociacionException("El usuario ya es miembro de la asociación.");
        }
        if (usuario instanceof Ciudadano && miembros.stream().anyMatch(m -> m instanceof Ciudadano && ((Ciudadano) m).getIdentificador().equals(usuario.getIdentificador()))) {
            throw new AsociacionException("No se puede agregar el mismo ciudadano dos veces.");
        }
        miembros.add(usuario);
        actualizarApoyosProyectos(usuario, true);
    }

    public void eliminarMiembro(Usuario usuario) {
    	miembros.remove(usuario);
        actualizarApoyosProyectos(usuario, false);
    }
    
    public int obtenerNumeroMiembros() {
        int totalMiembros = miembros.size();
        for (Usuario usuario : miembros) {
            if (usuario instanceof Asociacion) {
                totalMiembros += ((Asociacion) usuario).obtenerNumeroMiembros();
            }
        }
        return totalMiembros;
    }

    private void actualizarApoyosProyectos(Usuario usuario, boolean agregar) {
    	// Obtiene el gestor de propuestas
        GestorProyectos gestorProyectos = new GestorProyectos();

        // Itera sobre todas las propuestas
        for (Proyecto proyecto : gestorProyectos.obtenerTodosLosProyectos()) {
            // Verifica si la asociación soporta la propuesta
            if (proyecto.getApoyos().contains(this)) {
                // Si el usuario es miembro de la asociación
                if (miembros.contains(usuario)) {
                    // Si se agrega el usuario, lo agrega como sosteniente de la propuesta
                    if (agregar) {
                        proyecto.agregarApoyo(usuario);
                    } else {
                        // Si se elimina el usuario, lo elimina como sosteniente de la propuesta
                        proyecto.getApoyos().remove(usuario);
                    }
                }
            }
        }
    }

    @Override
    public String getIdentificador() {
        return nombre;
    }

    @Override
    public String getTipo() {
        return "asociación con " + miembros.size() + " ciudadanos";
    }

    public List<Usuario> getMiembros() {
        return miembros;
    }
}