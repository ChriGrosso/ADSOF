 /**  
 * Esta aplicación muestra la gestion de una Cartelera Cine y de sus peliculas  *
 * @author Christian Grosso, Marco Paparella*
 */
import java.util.*;

/**
 * Clase que representa una cartelera de cine, permitiendo almacenar y filtrar películas.
 * 
 * Contiene una lista de películas disponibles y métodos para obtener películas 
 * por género y por año de lanzamiento.
 * 
 * @author Christian Grosso, Marco Paparella
 */
public class CarteleraCine {
    private String nombreCine;
    private List<Pelicula> peliculas;

    /**
     * Constructor de la cartelera de cine.
     * 
     * @param cine Nombre del cine.
     * @param peliculas Array de películas que se añadirán a la cartelera.
     */
    public CarteleraCine(String cine, Pelicula[] peliculas) {

        this.nombreCine = cine;
        this.peliculas = new ArrayList<>();
        for (Pelicula p : peliculas) {
            this.peliculas.add(p);
        }
    }

    /**
     * Devuelve una representación en cadena de la cartelera del cine.
     * 
     * @return Cadena con el nombre del cine y la lista de películas en cartelera.
     */
    @Override
    public String toString() {
        return "Cine: " + this.nombreCine + "\nPelículas en cartelera: " + this.peliculas;
    }

    /**
     * Filtra las películas por género.
     * 
     * @param genero Género de las películas a buscar.
     * @return Lista de películas que coinciden con el género especificado.
     */
    public List<Pelicula> peliculasPorGenero(String genero) {
        List<Pelicula> resultado = new ArrayList<>();
        for (Pelicula p : peliculas) {
            if (p.getGenero().equalsIgnoreCase(genero)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    /**
     * Filtra las películas que fueron estrenadas después de un año específico.
     * 
     * @param anyo Año a partir del cual se buscan películas.
     * @return Lista de películas cuyo año de estreno es posterior al especificado.
     */
    public List<Pelicula> peliculasPosterioresA(int anyo) {
        List<Pelicula> resultado = new ArrayList<>();
        for (Pelicula p : peliculas) {
            if (p.getAnyo() > anyo) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    /*
    /**
     * Método genérico para filtrar películas según un criterio dado (género, año o director).
     *
     * @param criterio Criterio de búsqueda: "genero", "anyo" o "director".
     * @param valor Valor a comparar (String para género y director, Integer para año).
     * @return Lista de películas que cumplen con el criterio especificado.
     *
    public List<Pelicula> filtrarPeliculas(String criterio, String valor) {
        List<Pelicula> resultado = new ArrayList<>();

        for (Pelicula p : peliculas) {
            switch (criterio.toLowerCase()) {
                case "genero":
                    if (p.getGenero().equalsIgnoreCase(valor)) {
                        resultado.add(p);
                    }
                    break;
                case "anyo":
                    if (p.getAnyo() > Integer.parseInt(valor)) {
                        resultado.add(p);
                    }
                    break;
                case "director":
                    if (p.getDirector().equalsIgnoreCase(valor)) {
                        resultado.add(p);
                    }
                    break;
                default:
                    System.out.println("Criterio no válido: " + criterio);
                    return Collections.emptyList();
            }
        }
        return resultado;
    }
    */

    /**
     * Método principal que prueba la funcionalidad de la clase CarteleraCine.
     * 
     * Se crean películas, se agregan a la cartelera y se prueban los métodos de filtrado.
     * 
     * @param args Argumentos de la línea de comandos (no se utilizan).
     */
    public static void main(String... args) {
        // Creación de un array de películas
        Pelicula peliculas[] = {
            new Pelicula("Perfect days", "Drama", 2023, "Wim Wenders"),
            new Pelicula("Inception", "Acción", 2010, "Christopher Nolan"),
            new Pelicula("Jumanji", "Aventura", 1995, "Joe Johnston"),
            new Pelicula("The Father", "Drama", 2020, "Florian Zeller"),
            new Pelicula("Dune: Parte Dos", "Ciencia Ficción", 2024, "Denis Villeneuve"),
            new Pelicula("Nomadland", "Drama", 2021, "Chloé Zhao")
        };

        // Creación de la cartelera
        CarteleraCine cinesTelmo = new CarteleraCine("Telmo", peliculas);
        System.out.println(cinesTelmo);

        // Obtener películas de género "Drama"
        List<Pelicula> dramas = cinesTelmo.peliculasPorGenero("Drama");
        System.out.println("Dramas: " + dramas);

        // Obtener películas posteriores a 2020
        List<Pelicula> recientes = cinesTelmo.peliculasPosterioresA(2020);
        System.out.println("Recientes: " + recientes);

        /*
        // Filtrar películas de Christopher Nolan
        List<Pelicula> deNolan = cinesTelmo.filtrarPeliculas("director", "Christopher Nolan");
        System.out.println("Películas de Christopher Nolan: " + deNolan);
        */
    }
}
