
package proyectos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import usuarios.Ciudadano;
import usuarios.Usuario;

public class Proyecto {
    private String titulo;
    private String descripcion;
    private Usuario proponente;
    private int codigo;
    private LocalDateTime fechaHora;
    private double presupuesto;
    private double porcentaje;
    private List<Usuario> apoyos = new ArrayList<>();

    // Costruttore per progetti di cittadini o associazioni
    public Proyecto(String titulo, String descripcion, Usuario proponente, int codigo) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.proponente = proponente;
        this.codigo = codigo;
        this.fechaHora = LocalDateTime.now();
    }

    // Costruttore per progetti di fondazioni
    public Proyecto(String titulo, String descripcion, Usuario proponente, int codigo, double presupuesto, double porcentaje) throws BudgetInvalidoException, PorcentajeInvalidoException {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.proponente = proponente;
        this.codigo = codigo;
        this.fechaHora = LocalDateTime.now();
        if (presupuesto <= 0) {
            throw new BudgetInvalidoException("El presupuesto debe ser mayor que cero.");
        }
        if (porcentaje < 1 || porcentaje > 100) {
            throw new PorcentajeInvalidoException("El porcentaje debe estar entre 1 y 100.");
        }
        this.presupuesto = presupuesto;
        this.porcentaje = porcentaje;
    }

    // Metodi getter
    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Usuario getProponente() {
        return proponente;
    }

    public int getCodigo() {
        return codigo;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public double getPorcentaje() {
        return porcentaje;
    }
    
    public void agregarApoyo(Usuario usuario) {
		apoyos.add(usuario);
    }
    
    public int obtenerNumeroApoyos() {
        int totalApoyos = 0;
        for (Usuario usuario : apoyos) {
            if (usuario instanceof Ciudadano) {
                totalApoyos++;
            } else if (usuario instanceof usuarios.Asociacion) {
                totalApoyos += ((usuarios.Asociacion) usuario).obtenerNumeroMiembros();
            }
        }
        return totalApoyos;
    }
    
    
    public List<Ciudadano> obtenerCiudadanosApoyo() {
        List<Ciudadano> ciudadanosApoyo = new ArrayList<>();
        for (Usuario usuario : apoyos) {
            if (usuario instanceof Ciudadano) {
                ciudadanosApoyo.add((Ciudadano) usuario);
            }
        }
        return ciudadanosApoyo;
    }

    @Override
    public String toString() {
        String resultado = codigo + ": " + titulo + ". Proponente: " + proponente;
        if (presupuesto > 0) {
            resultado += ". Presupuesto: " + presupuesto + "€.";
            resultado += " Porcentaje: " + porcentaje + "% /proyecto de fundación/";
        }
        resultado += "=" + obtenerNumeroApoyos();
        return resultado;
    }

	public List<Usuario> getApoyos() {
		return apoyos;
	}
}