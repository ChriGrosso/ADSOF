/**
 * Clase que representa una película con título, género, año de estreno y director.
 * 
 * Permite obtener información sobre la película a través de métodos de acceso
 * y proporciona una representación en cadena mediante toString().
 * 
 * @author Christian Grosso, Marco Paparella
 */
public class Pelicula {
    private String titulo;   // Título de la película
    private String genero;   // Género de la película (Ej: Drama, Acción, etc.)
    private int anyo;        // Año de estreno de la película
    private String director; // Nombre del director de la película

    /**
     * Constructor que inicializa una película con su título, género, año y director.
     * 
     * @param titulo Nombre de la película.
     * @param genero Género de la película.
     * @param anyo Año de estreno de la película.
     * @param director Nombre del director de la película.
     */
    public Pelicula(String titulo, String genero, int anyo, String director) {
        this.titulo = titulo;
        this.genero = genero;
        this.anyo = anyo;
        this.director = director;
    }

    /**
     * Devuelve una representación en cadena de la película.
     * 
     * @return Una cadena con el título, director, género y año de la película.
     */
    @Override
    public String toString() {
        return this.titulo + " - dirigida por: " + this.director + " (" + this.genero + "): " + this.anyo;
    }

    /**
     * Obtiene el título de la película.
     * 
     * @return El título de la película.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Obtiene el género de la película.
     * 
     * @return El género de la película.
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Obtiene el año de estreno de la película.
     * 
     * @return El año de estreno de la película.
     */
    public int getAnyo() {
        return anyo;
    }

    /**
     * Obtiene el nombre del director de la película.
     * 
     * @return El director de la película.
     */
    public String getDirector() {
        return director;
    }
}
