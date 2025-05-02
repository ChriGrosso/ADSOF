import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

class GrafoDeEstados<T> {
    private String nombre;
    private Map<String, NodoDeTrabajo<T>> nodos = new HashMap<>();
    private String nodoInicial;
    private List<String> nodosFinales = new ArrayList<>();
    private Map<String, List<String>> listaDeAdyacencia = new HashMap<>();
    private Map<String, List<ConditionalEdge<T>>> enlacesCondicionales = new HashMap<>();

    public GrafoDeEstados(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarNodo(String nombre, Function<T, T> operacion) {
        nodos.put(nombre, new NodoGenericoDeTrabajo<>(nombre, operacion));
        listaDeAdyacencia.put(nombre, new ArrayList<>());
        enlacesCondicionales.put(nombre, new ArrayList<>());
    }

    public void establecerInicial(String nombreDelNodo) {
        this.nodoInicial = nombreDelNodo;
    }

    public void establecerFinal(String nombreDelNodo) {
        this.nodosFinales.add(nombreDelNodo);
    }

    public void agregarArista(String nodoOrigen, String nodoDestino) {
        listaDeAdyacencia.get(nodoOrigen).add(nodoDestino);
    }

    public void agregarAristaCondicional(String nodoOrigen, String nodoDestino, Predicate<T> condicion) {
        enlacesCondicionales.get(nodoOrigen).add(new ConditionalEdge<>(nodoDestino, condicion));
    }

    public T ejecutar(T estadoInicial, boolean debug) {
        if (nodoInicial == null || nodos.isEmpty()) {
            System.out.println("El flujo de trabajo no está correctamente inicializado.");
            return estadoInicial;
        }

        T estadoActual = estadoInicial;
        String nodoActualNombre = nodoInicial;
        int paso = 1;

        if (debug) {
            System.out.println("Iniciando flujo de trabajo: " + nombre);
            System.out.println("Estado inicial: " + estadoActual);
        }

        while (nodoActualNombre != null) {
            NodoDeTrabajo<T> nodoActual = nodos.get(nodoActualNombre);
            if (nodoActual == null) {
                System.out.println("Error: Nodo no encontrado - " + nodoActualNombre);
                break;
            }

            if (debug) {
                System.out.println("Step " + paso + " (" + nodoActual.getNombre() + ") - input: " + estadoActual);
            }

            estadoActual = nodoActual.ejecutar(estadoActual);

            if (debug) {
                System.out.println("Step " + paso + " (" + nodoActual.getNombre() + ") - sum executed: " + estadoActual);
            }

            List<String> nodosSiguientesIncondicionales = listaDeAdyacencia.get(nodoActualNombre);
            List<ConditionalEdge<T>> nodosSiguientesCondicionales = enlacesCondicionales.get(nodoActualNombre);
            List<String> siguientesNodosAEjecutar = new ArrayList<>();

            if (nodosSiguientesIncondicionales != null) {
                siguientesNodosAEjecutar.addAll(nodosSiguientesIncondicionales);
            }

            if (nodosSiguientesCondicionales != null) {
                for (ConditionalEdge<T> enlace : nodosSiguientesCondicionales) {
                    if (enlace.getCondicion().test(estadoActual)) {
                        siguientesNodosAEjecutar.add(enlace.getNodoDestino());
                    }
                }
            }

            if (siguientesNodosAEjecutar.isEmpty()) {
                if (nodosFinales.contains(nodoActualNombre)) {
                    if (debug) {
                        System.out.println("Alcanzado nodo final: " + nodoActualNombre);
                    }
                    break;
                } else {
                    nodoActualNombre = null;
                }
            } else {
                nodoActualNombre = siguientesNodosAEjecutar.get(0);
            }

            paso++;
        }

        if (debug) {
            System.out.println("Ejecución del flujo de trabajo finalizada.");
        }
        return estadoActual;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Flujo de trabajo: " + nombre + "\n");
        sb.append("- Nodos: [").append(String.join(", ", nodos.keySet())).append("]\n");
        sb.append("- Inicial: ").append(nodoInicial).append("\n");
        sb.append("- Finales: [").append(String.join(", ", nodosFinales)).append("]\n");
        return sb.toString();
    }
    }