package Tester;

import java.util.List;
import java.util.Map;

import proyectos.GestorProyectos;
import proyectos.Proyecto;
import usuarios.*;

public class ProyectosTester {
    public static void main(String[] args) {
        GestorProyectos gestor = new GestorProyectos();

        try {
            // Creazione di utenti di esempio
            Ciudadano juan = new Ciudadano("Juan Bravo", "01234567K", "1234");
            Ciudadano ana = new Ciudadano("Ana López", "01234567L", "5678");
            Ciudadano luisa = new Ciudadano("Luisa Gómez", "01234567G", "9012");
            Fundacion canal = new Fundacion("Canal Cif", "A1234567B", "3456");
            Asociacion conservemos = new Asociacion("conservemos el manzanares", "7890", juan);
            conservemos.agregarMiembro(ana);
            conservemos.agregarMiembro(luisa);

            // Creazione di progetti
            Proyecto proyecto1 = new Proyecto("Limpieza del manzanares", "Limpieza del río", conservemos, 0);
            Proyecto proyecto2 = new Proyecto("Gastemos menos agua", "Proyecto para ahorrar agua", canal, 1, 1000000.0, 80.0);

            // Aggiunta di progetti al gestore
            gestor.proponerProyecto(proyecto1);
            gestor.proponerProyecto(proyecto2);

            // Supporto ai progetti (assicurati che nessun proponente supporti il proprio progetto)
            gestor.apoyarProyecto(proyecto1, conservemos);
            gestor.apoyarProyecto(proyecto2, juan);

            // Stampa della mappa dei cittadini sostenitori
            System.out.println(gestor.obtenerCiudadanosPorProyecto());

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}