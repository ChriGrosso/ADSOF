import java.util.function.Predicate;

 public class ConditionalEdge<U> {
        private String nodoDestino;
        private Predicate<U> condicion;

        public ConditionalEdge(String nodoDestino, Predicate<U> condicion) {
            this.nodoDestino = nodoDestino;
            this.condicion = condicion;
        }

        public String getNodoDestino() {
            return nodoDestino;
        }

        public Predicate<U> getCondicion() {
            return condicion;
        }
    }
