/**
 * Representa un proyecto propuesto por una fundación.
 * Incluye presupuesto estimado y porcentaje de financiación que asumirá la fundación.
 * 
 * Valida que el presupuesto sea mayor que cero y que el porcentaje esté entre 1 y 100.
 * 
 * @author Marco Paparella
 * @author Christian Grosso
 */
package proyectos;

import exceptiones.*;
import usuarios.Fundacion;

public class ProyectoFundacion extends Proyecto {

    private final double presupuesto;
    private final double porcentaje;

    /**
     * Construye un nuevo proyecto de fundación, validando presupuesto y porcentaje.
     * 
     * @param titulo título del proyecto
     * @param descripcion descripción del proyecto
     * @param proponente fundación que propone el proyecto
     * @param presupuesto presupuesto estimado del proyecto (debe ser > 0)
     * @param porcentaje porcentaje del presupuesto que cubrirá la fundación (1-100)
     * @throws PresupuestoInvalidoException si el presupuesto no es válido
     * @throws PorcentajeInvalidoException si el porcentaje está fuera del rango permitido
     */
    public ProyectoFundacion(String titulo, String descripcion, Fundacion proponente,
                              double presupuesto, double porcentaje)
            throws PresupuestoInvalidoException, PorcentajeInvalidoException {
        super(titulo, descripcion, proponente);
        if (presupuesto <= 0) throw new PresupuestoInvalidoException();
        if (porcentaje < 1 || porcentaje > 100) throw new PorcentajeInvalidoException();
        this.presupuesto = presupuesto;
        this.porcentaje = porcentaje;
    }

    /**
     * Devuelve una representación textual del proyecto de fundación, incluyendo
     * presupuesto y porcentaje financiado.
     */
    @Override
    public String toString() {
        return "1: " + getTitulo() + ". Proponente: " + getProponente()
                + ". Presupuesto: " + presupuesto + "€.\nPorcentaje: " + porcentaje + "% /proyecto de fundacion/";
    }
}
