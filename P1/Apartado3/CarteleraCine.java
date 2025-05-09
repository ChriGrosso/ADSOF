import java.util.*;
public class CarteleraCine {
    private String nombreCine;
    private List < Pelicula > peliculas = new ArrayList < > ();
    public CarteleraCine(String cine, Pelicula[] peliculas) {
        this.nombreCine = cine;
        for (Pelicula p: peliculas) this.peliculas.add(p);
    }
    @Override public String toString() {
        return "Cine: " + this.nombreCine + "\nPeliculas en cartelera: " + this.peliculas;
    }
    public static void main(String...args) {
        Pelicula peliculas[] = {
            new Pelicula("Perfect days", "Drama", 2023),
            new Pelicula("Inception", "Accion", 2010),
            new Pelicula("Jumanji", "Aventura", 1995),
			new Pelicula("Perfect days", "Drama", 2023),/*Nessun errore*/
            /*new Pelicula("Inception", , 2010), Errore Compilazione*/
            /*new Pelicula("Jumanji", "Aventura","Drama", 1995) Errore Compilazione*/
        };
        CarteleraCine cinesTelmo = new CarteleraCine("Telmo", peliculas);
        System.out.println(cinesTelmo);
    }
}