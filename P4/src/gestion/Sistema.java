/**
 * Clase principal del sistema de gestión. Permite registrar usuarios y proyectos,
 * gestionar apoyos y consultar estadísticas de participación.
 * 
 * Esta clase centraliza todas las operaciones del sistema relacionadas con:
 * - Registro de ciudadanos, asociaciones y fundaciones.
 * - Registro y búsqueda de proyectos.
 * - Apoyos a proyectos con control de validez.
 * - Consultas sobre apoyos recibidos y ciudadanos que los emitieron.
 * 
 * @author Marco Paparella
 * @author Christian Grosso
 */
package gestion;

import exceptiones.*;
import usuarios.*;
import proyectos.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Sistema {

    private Set<String> identificadores = new HashSet<>();
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Proyecto> proyectos = new ArrayList<>();
    private Map<Proyecto, Set<Usuario>> apoyos = new HashMap<>();

    /**
     * Registra un nuevo ciudadano, validando NIF y unicidad del identificador.
     */
    public void registrarCiudadano(String nombre, String nif, String contrasenia)
            throws NIFFormatoInvalidoException, IdentificadorDuplicadoException {
        validarIdentificadorUnico(nif);
        Ciudadano c = new Ciudadano(nombre, nif, contrasenia);
        usuarios.add(c);
        identificadores.add(nif);
    }

    /**
     * Registra una nueva fundación, validando CIF y unicidad del identificador.
     */
    public void registrarFundacion(String nombre, String cif, String contrasenia)
            throws CIFFormatoInvalidoException, IdentificadorDuplicadoException {
        validarIdentificadorUnico(cif);
        Fundacion f = new Fundacion(nombre, cif, contrasenia);
        usuarios.add(f);
        identificadores.add(cif);
    }

    /**
     * Registra una nueva asociación con su representante, validando identificador único.
     */
    public void registrarAsociacion(String nombre, String contrasenia, Ciudadano representante)
            throws IdentificadorDuplicadoException {
        Asociacion a = new Asociacion(nombre, contrasenia, representante);
        validarIdentificadorUnico(a.getIdentificador());
        usuarios.add(a);
        identificadores.add(a.getIdentificador());
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Busca un usuario registrado por su nombre.
     */
    public Usuario buscarPorNombre(String nombre) {
        return usuarios.stream().filter(u -> u.getNombre().equals(nombre)).findFirst().orElse(null);
    }

    /**
     * Lanza una excepción si el identificador ya está registrado.
     */
    private void validarIdentificadorUnico(String id) throws IdentificadorDuplicadoException {
        if (identificadores.contains(id)) throw new IdentificadorDuplicadoException(id);
    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    /**
     * Busca un proyecto por su título (ignorando mayúsculas/minúsculas).
     */
    public Proyecto buscarProyectoPorTitulo(String titulo) {
        for (Proyecto p : proyectos) {
            if (p.getTitulo().equalsIgnoreCase(titulo)) return p;
        }
        return null;
    }

    /**
     * Busca un proyecto por su código identificativo.
     */
    public Proyecto buscarProyectoPorCodigo(String codigo) {
        for (Proyecto p : proyectos) {
            if (p.getCodigo().equals(codigo)) return p;
        }
        return null;
    }

    /**
     * Permite a un usuario apoyar un proyecto, validando:
     * - que no sea el proponente,
     * - que no lo haya apoyado previamente,
     * - que no hayan pasado más de 60 días desde su propuesta,
     * - que no esté ya representado en el apoyo mediante una asociación.
     */
    public void apoyarProyecto(Usuario usuario, Proyecto proyecto) throws ApoyoInvalidoException {
        if (proyecto.getProponente().equals(usuario))
            throw new ApoyoInvalidoException("No se puede apoyar un proyecto propuesto por el mismo usuario.");

        if (ChronoUnit.DAYS.between(proyecto.getFechaPropuesta(), LocalDateTime.now()) > 60)
            throw new ApoyoInvalidoException("El proyecto fue propuesto hace más de 60 días.");

        apoyos.putIfAbsent(proyecto, new HashSet<>());
        Set<Usuario> actuales = apoyos.get(proyecto);

        if (actuales.contains(usuario))
            throw new ApoyoInvalidoException("El usuario ya ha apoyado este proyecto.");

        if (usuario instanceof Asociacion asociacion) {
            for (Usuario u : new HashSet<>(actuales)) {
                if (asociacion.contieneUsuario(u)) {
                    actuales.remove(u);
                }
            }
            actuales.add(asociacion);
        } else {
            for (Usuario u : actuales) {
                if (u instanceof Asociacion a && a.contieneUsuario(usuario)) {
                    throw new ApoyoInvalidoException("El ciudadano ya está representado por una asociación que apoya este proyecto.");
                }
            }
            actuales.add(usuario);
        }
    }

    /**
     * Devuelve un mapa con los proyectos y el número total de apoyos recibidos.
     * Ordena los proyectos de mayor a menor número de apoyos; en caso de empate,
     * primero los más recientes.
     */
    public Map<Proyecto, Integer> obtenerConteoApoyos() {
        Map<Proyecto, Integer> resultado = new TreeMap<>((a, b) -> {
            int comp = Integer.compare(contarApoyos(b), contarApoyos(a));
            if (comp == 0) {
                return b.getFechaPropuesta().compareTo(a.getFechaPropuesta());
            }
            return comp;
        });
        for (Proyecto p : proyectos) {
            resultado.put(p, contarApoyos(p));
        }
        return resultado;
    }

    /**
     * Devuelve un mapa con los proyectos y los ciudadanos que los apoyan,
     * incluyendo aquellos representados por asociaciones.
     */
    public Map<Proyecto, Set<Ciudadano>> obtenerCiudadanosQueApoyan() {
        Map<Proyecto, Set<Ciudadano>> resultado = new HashMap<>();
        for (Proyecto p : apoyos.keySet()) {
            Set<Ciudadano> ciudadanos = new HashSet<>();
            for (Usuario u : apoyos.get(p)) {
                if (u instanceof Ciudadano c) ciudadanos.add(c);
                else if (u instanceof Asociacion a) ciudadanos.addAll(a.obtenerCiudadanosRecursivos());
            }
            resultado.put(p, ciudadanos);
        }
        return resultado;
    }

    /**
     * Cuenta el número total de apoyos que ha recibido un proyecto.
     * Los apoyos de asociaciones cuentan como el número total de ciudadanos miembros.
     */
    private int contarApoyos(Proyecto proyecto) {
        int total = 0;
        for (Usuario u : apoyos.getOrDefault(proyecto, Set.of())) {
            if (u instanceof Ciudadano) total++;
            else if (u instanceof Asociacion a) total += a.contarCiudadanosTotales();
        }
        return total;
    }

    /**
     * Registra un nuevo proyecto en el sistema. El proponente lo apoya automáticamente.
     * Si el proponente es una fundación o asociación, se emite un anuncio correspondiente.
     */
    public void registrarProyecto(Proyecto proyecto) {
        proyectos.add(proyecto);
        apoyos.putIfAbsent(proyecto, new HashSet<>());
        apoyos.get(proyecto).add(proyecto.getProponente());

        if (proyecto.getProponente() instanceof Fundacion f) {
            f.anunciarProyectoNuevo(proyecto);
        } else if (proyecto.getProponente() instanceof Asociacion a) {
            a.registrarProyecto(proyecto);
        }
    }
}