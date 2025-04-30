import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

class GrafoDeEstados<T> {
    private String nombre;
    private Map<String, NodoDeTrabajo<T>> nodos = new HashMap<>();
    private String nodoInicial;
    private List<String> nodosFinales = new ArrayList<>();
    private Map<String, List<String>> listaDeAdyacencia = new HashMap<>();

    public GrafoDeEstados(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarNodo(String nombre, Function<T, T> operacion) {
        nodos.put(nombre, new NodoGenericoDeTrabajo<>(nombre, operacion));
        listaDeAdyacencia.put(nombre, new ArrayList<>());
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
                System.out.println("Paso " + paso + " (" + nodoActual.getNombre() + ") - Ejecutando nodo con estado: " + estadoActual);
            }

            estadoActual = nodoActual.ejecutar(estadoActual);

            if (debug) {
                System.out.println("Paso " + paso + " (" + nodoActual.getNombre() + ") - Estado después de la ejecución: " + estadoActual);
            }

            List<String> nodosSiguientes = listaDeAdyacencia.get(nodoActualNombre);
            if (nodosSiguientes == null || nodosSiguientes.isEmpty()) {
                if (nodosFinales.contains(nodoActualNombre)) {
                    if (debug) {
                        System.out.println("Alcanzado nodo final: " + nodoActualNombre);
                    }
                    break;
                } else {
                    nodoActualNombre = null;
                }
            } else {
                nodoActualNombre = nodosSiguientes.get(0);
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
        return "Workflow: " + nombre + "\n" +
               "- Nodos: [" + String.join(", ", nodos.keySet()) + "]\n" +
               "- Inicial: " + nodoInicial + "\n" +
               "- Finales: [" + String.join(", ", nodosFinales) + "]\n" +
               "- Conexiones:\n" +
               getStringDeConexiones();
    }

    private String getStringDeConexiones() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<String>> entry : listaDeAdyacencia.entrySet()) {
            String nodo = entry.getKey();
            List<String> destinos = entry.getValue();
            if (!destinos.isEmpty()) {
                sb.append("  ").append(nodo).append(" -> ").append(destinos).append("\n");
            }
        }
        return sb.toString();
    }
}
