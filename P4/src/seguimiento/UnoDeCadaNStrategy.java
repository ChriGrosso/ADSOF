/**
 * Estrategia de anuncios que envía un anuncio a los seguidores de manera escalonada,
 * enviando un anuncio cada 'n' anuncios generados.
 * 
 * Esta estrategia permite controlar la frecuencia con la que un seguidor recibe anuncios,
 * enviando el primer anuncio y luego uno de cada 'n' anuncios. Es útil cuando se quiere limitar
 * la cantidad de anuncios que un seguidor recibe.
 * 
 * @author Marco Paparella
 * @author Christian Grosso
 */
package seguimiento;

import java.util.*;

public class UnoDeCadaNStrategy implements AnnouncementStrategy {

    private final int n; // Número de anuncios que deben pasar antes de enviar el siguiente
    private final Map<Follower, Integer> counter = new HashMap<>(); // Contador de anuncios enviados a cada seguidor

    /**
     * Crea una nueva estrategia que envía un anuncio cada 'n' anuncios generados.
     * 
     * @param n número de anuncios que deben pasar antes de enviar el siguiente
     */
    public UnoDeCadaNStrategy(int n) {
        this.n = n;
    }

    /**
     * Determina si un anuncio debe ser enviado a un seguidor, basándose en un contador interno.
     * Envia el primer anuncio, luego uno de cada 'n' anuncios.
     * 
     * @param a el anuncio que se desea enviar
     * @param f el seguidor al que se desea enviar el anuncio
     * @return true si el anuncio debe ser enviado, false si no
     */
    @Override
    public boolean shouldSend(Announcement a, Follower f) {
        int count = counter.getOrDefault(f, 0) + 1; // Incrementa el contador de anuncios enviados
        counter.put(f, count);
        return count % n == 1; // Envia el primer anuncio, luego uno de cada 'n'
    }
}
