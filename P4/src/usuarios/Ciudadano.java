/**
 * Clase que representa a un ciudadano, que es un usuario que puede inscribirse en asociaciones y recibir anuncios.
 * Los ciudadanos pueden seguir asociaciones y otros tipos de entidades, recibir anuncios según su suscripción
 * y gestionar su inscripción o baja en las asociaciones.
 * 
 * Esta clase implementa la interfaz `Follower`, lo que le permite recibir anuncios a través de las estrategias definidas
 * en las entidades seguidas. Además, permite la inscripción en asociaciones, la baja de las mismas y el manejo de mensajes
 * personalizados.
 * 
 * @author Marco Paparella
 * @author Christian Grosso
 */
package usuarios;

import exceptiones.*;
import seguimiento.*;

import java.util.*;

public class Ciudadano extends Usuario implements Follower {
    private Set<Asociacion> asociaciones = new HashSet<>();
    private List<Announcement> mensajes = new ArrayList<>();

    /**
     * Constructor para crear un nuevo ciudadano con validación de su formato de NIF.
     * 
     * @param nombre Nombre del ciudadano.
     * @param nif Número de identificación fiscal del ciudadano.
     * @param contrasenia Contraseña del ciudadano.
     * @throws NIFFormatoInvalidoException Si el NIF no cumple con el formato correcto.
     */
    public Ciudadano(String nombre, String nif, String contrasenia) throws NIFFormatoInvalidoException {
        super(nombre, nif, contrasenia);
        if (!nif.matches("\\d{8}[A-Z]")) throw new NIFFormatoInvalidoException(nif);
    }

    /**
     * Permite al ciudadano inscribirse en una asociación.
     * 
     * @param a Asociación en la que se desea inscribir al ciudadano.
     * @throws CiudadanoDuplicadoException Si el ciudadano ya está inscrito en la asociación.
     */
    public void inscribirse(Asociacion a) throws CiudadanoDuplicadoException {
        a.inscribirCiudadano(this);
        asociaciones.add(a);
    }

    /**
     * Permite al ciudadano darse de baja de una asociación específica.
     * 
     * @param a Asociación de la que el ciudadano se dará de baja.
     */
    public void darseDeBaja(Asociacion a) {
        a.eliminarCiudadano(this);
        asociaciones.remove(a);
    }

    /**
     * Permite al ciudadano darse de baja de todas las asociaciones a las que está inscrito.
     */
    public void darseDeBajaDeTodas() {
        for (Asociacion a : new HashSet<>(asociaciones)) a.eliminarCiudadano(this);
        asociaciones.clear();
    }

    /**
     * Recibe un anuncio y lo agrega a la lista de mensajes del ciudadano si no está ya presente.
     * 
     * @param t Anuncio a recibir.
     */
    public void receive(Announcement t) {
    	if (!mensajes.contains(t)) {
            mensajes.add(t);
        }
    }

    /**
     * Obtiene la lista de mensajes (anuncios) que el ciudadano ha recibido.
     * 
     * @return Lista de anuncios recibidos.
     */
    public List<Announcement> getMensajes() {
        return mensajes;
    }

    /**
     * Devuelve la representación en formato string del ciudadano.
     * 
     * @return Representación en cadena del ciudadano.
     */
    public String toString() {
        return getNombre() + " NIF (" + getIdentificador() + ") <usuario>";
    }

    /**
     * Obtiene el tipo de usuario, que en este caso siempre será "usuario".
     * 
     * @return Tipo de usuario.
     */
    @Override
    public String getTipo() { return "usuario"; }
}
