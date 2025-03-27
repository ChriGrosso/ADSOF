package proyectos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import apoyo.ApoyoInvalidoException;
import usuarios.Asociacion;
import usuarios.Ciudadano;
import usuarios.Usuario;

public class GestorProyectos {
    private List<Proyecto> proyectos = new ArrayList<>();
    private int codigoProyecto = 0;

    public void proponerProyecto(Proyecto proyecto) throws BudgetInvalidoException, PorcentajeInvalidoException {
        proyectos.add(proyecto);
        codigoProyecto++;
    }

    public List<Proyecto> obtenerTodosLosProyectos() {
        return proyectos;
    }

    public Proyecto obtenerProyectoPorCodigo(int codigo) {
        for (Proyecto proyecto : proyectos) {
            if (proyecto.getCodigo() == codigo) {
                return proyecto;
            }
        }
        return null;
    }

    public Proyecto obtenerProyectoPorTitulo(String titulo) {
        for (Proyecto proyecto : proyectos) {
            if (proyecto.getTitulo().equals(titulo)) {
                return proyecto;
            }
        }
        return null;
    }
    
    public void apoyarProyecto(Proyecto proyecto, Usuario usuario) throws ApoyoInvalidoException {
        if (proyecto.getProponente().equals(usuario)) {
            throw new ApoyoInvalidoException("No puedes apoyar tu propio proyecto.");
        }
        if (proyecto.getApoyos().contains(usuario)) {
            throw new ApoyoInvalidoException("Ya has apoyado este proyecto.");
        }
        if (proyecto.getFechaHora().isBefore(LocalDateTime.now().minusDays(60))) {
            throw new ApoyoInvalidoException("El proyecto tiene más de 60 días.");
        }
        if (usuario instanceof Asociacion) {
            proyecto.getApoyos().addAll(((Asociacion) usuario).getMiembros());
        }
        proyecto.agregarApoyo(usuario);
    }

    public Map<Proyecto, Integer> obtenerApoyosPorProyecto() {
        Map<Proyecto, Integer> apoyosPorProyecto = new HashMap<>();
        for (Proyecto proyecto : proyectos) {
            apoyosPorProyecto.put(proyecto, proyecto.obtenerNumeroApoyos());
        }
        return apoyosPorProyecto.entrySet().stream()
                .sorted(Map.Entry.<Proyecto, Integer>comparingByValue().reversed()
                        .thenComparing(entry -> entry.getKey().getFechaHora()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public Map<Proyecto, List<Ciudadano>> obtenerCiudadanosPorProyecto() {
        Map<Proyecto, List<Ciudadano>> ciudadanosPorProyecto = new HashMap<>();
        for (Proyecto proyecto : proyectos) {
            ciudadanosPorProyecto.put(proyecto, proyecto.obtenerCiudadanosApoyo());
        }
        return ciudadanosPorProyecto;
    }
}