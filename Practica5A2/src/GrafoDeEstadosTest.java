import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GrafoDeEstadosTest {

    @Test
    void testAgregarNodo() {
        GrafoDeEstados<String> grafo = new GrafoDeEstados<>("Test Grafo");
        grafo.agregarNodo("nodo1", (String s) -> s + "1");
        // Prueba implícita: intenta ejecutar un flujo simple
        grafo.establecerInicial("nodo1");
        grafo.establecerFinal("nodo1");
        String resultado = grafo.ejecutar("", false);
        assertEquals("1", resultado);
    }

    @Test
    void testEstablecerInicialYFinal() {
        GrafoDeEstados<Integer> grafo = new GrafoDeEstados<>("Test Grafo");
        grafo.agregarNodo("inicio", (Integer i) -> i + 1);
        grafo.agregarNodo("fin", (Integer i) -> i * 2);
        grafo.establecerInicial("inicio");
        grafo.establecerFinal("fin");
        // Prueba implícita en otras pruebas de ejecución
    }

    @Test
    void testAgregarAristaYEjecucionLineal() {
        GrafoDeEstados<String> grafo = new GrafoDeEstados<>("Test Lineal");
        grafo.agregarNodo("nodo1", (String s) -> s + "A");
        grafo.agregarNodo("nodo2", (String s) -> s + "B");
        grafo.establecerInicial("nodo1");
        grafo.establecerFinal("nodo2");
        grafo.agregarArista("nodo1", "nodo2");
        String resultado = grafo.ejecutar("", false);
        assertEquals("AB", resultado);
    }

    @Test
    void testEjecucionConDebug() {
        GrafoDeEstados<Integer> grafo = new GrafoDeEstados<>("Test Debug");
        grafo.agregarNodo("incrementar", (Integer i) -> i + 1);
        grafo.establecerInicial("incrementar");
        grafo.establecerFinal("incrementar");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        grafo.ejecutar(0, true);

        System.setOut(originalOut);

        String output = outContent.toString();
        System.out.println("Salida real:\n" + output); // <-- Añade esta línea

        assertTrue(output.contains("Iniciando flujo de trabajo: Test Debug"));
        assertTrue(output.contains("Estado inicial: 0"));
        assertTrue(output.contains("Step 1 (incrementar) - Ejecutando nodo con estado: 0"));
        assertTrue(output.contains("Step 1 (incrementar) - Estado después de la ejecución: 1"));
        assertTrue(output.contains("Alcanzado nodo final: incrementar"));
        assertTrue(output.contains("Ejecución del flujo de trabajo finalizada."));
    }
    @Test
    void testAlcanzarNodoFinal() {
        GrafoDeEstados<Boolean> grafo = new GrafoDeEstados<>("Test Final");
        grafo.agregarNodo("inicio", (Boolean b) -> true);
        grafo.establecerInicial("inicio");
        grafo.establecerFinal("inicio");
        Boolean resultado = grafo.ejecutar(false, false);
        assertTrue(resultado);
    }

    @Test
    void testNodoSinSalidaNoFinal() {
        GrafoDeEstados<String> grafo = new GrafoDeEstados<>("Test Sin Salida");
        grafo.agregarNodo("nodo1", (String s) -> s + "X");
        grafo.establecerInicial("nodo1");
        String resultado = grafo.ejecutar("", false);
        assertEquals("X", resultado);
        // En este caso, la ejecución termina porque no hay nodos siguientes.
        // No es un error, pero el flujo finaliza. Podríamos añadir una aserción
        // para verificar que no se imprimió ningún mensaje de error.
    }

    @Test
    void testFlujoConEstadoString() {
        GrafoDeEstados<EstadoTexto> grafo = new GrafoDeEstados<>("Test String");
        grafo.agregarNodo("upper", (EstadoTexto estado) -> {
            estado.texto = estado.texto.toUpperCase();
            return estado;
        });
        grafo.establecerInicial("upper");
        grafo.establecerFinal("upper");
        EstadoTexto inicial = new EstadoTexto("hola");
        EstadoTexto resultado = grafo.ejecutar(inicial, false);
        assertEquals("HOLA", resultado.texto);
    }

    @Test
    void testFlujoConEstadoListaNumeros() {
        GrafoDeEstados<EstadoListaNumeros> grafo = new GrafoDeEstados<>("Test Lista");
        grafo.agregarNodo("addOne", (EstadoListaNumeros estado) -> {
            List<Integer> nuevaLista = new ArrayList<>();
            for (Integer num : estado.numeros) {
                nuevaLista.add(num + 1);
            }
            estado.numeros = nuevaLista;
            return estado;
        });
        grafo.establecerInicial("addOne");
        grafo.establecerFinal("addOne");
        EstadoListaNumeros inicial = new EstadoListaNumeros(List.of(1, 2, 3));
        EstadoListaNumeros resultado = grafo.ejecutar(inicial, false);
        assertEquals(List.of(2, 3, 4), resultado.numeros);
    }

    @Test
    void testFlujoConMultiplesNodos() {
        GrafoDeEstados<Contador> grafo = new GrafoDeEstados<>("Test Multiples Nodos");
        grafo.agregarNodo("incrementar1", (Contador c) -> { c.valor++; return c; });
        grafo.agregarNodo("incrementar2", (Contador c) -> { c.valor += 2; return c; });
        grafo.establecerInicial("incrementar1");
        grafo.establecerFinal("incrementar2");
        grafo.agregarArista("incrementar1", "incrementar2");
        Contador inicial = new Contador(0);
        Contador resultado = grafo.ejecutar(inicial, false);
        assertEquals(3, resultado.valor);
    }
    
    @Test
    void testEnlaceCondicionalSeCumple() {
        GrafoDeEstados<Map<String, String>> grafo = new GrafoDeEstados<>("Test Condicional Cumplido");

        grafo.agregarNodo("incrementar", (estado) -> {
            int valorActual = Integer.parseInt(estado.getOrDefault("valor", "0")) + 1;
            estado.put("valor", String.valueOf(valorActual));
            return estado;
        });
        grafo.agregarNodo("esMayorQueCinco", (estado) -> estado); // Nodo de condición
        grafo.agregarNodo("finalMayor", (estado) -> {
            estado.put("resultado", "mayor");
            return estado;
        });
        grafo.agregarNodo("finalNoMayor", (estado) -> {
            estado.put("resultado", "no mayor");
            return estado;
        });

        grafo.establecerInicial("incrementar");
        grafo.establecerFinal("finalMayor");
        grafo.establecerFinal("finalNoMayor");

        grafo.agregarAristaCondicional("incrementar", "esMayorQueCinco", (estado) -> Integer.parseInt(estado.getOrDefault("valor", "0")) > 5);
        grafo.agregarAristaCondicional("esMayorQueCinco", "finalMayor", (estado) -> Integer.parseInt(estado.getOrDefault("valor", "0")) > 5);
        grafo.agregarAristaCondicional("esMayorQueCinco", "finalNoMayor", (estado) -> Integer.parseInt(estado.getOrDefault("valor", "0")) <= 5);

        Map<String, String> estadoInicial = new HashMap<>();
        estadoInicial.put("valor", "6");
        Map<String, String> resultado = grafo.ejecutar(estadoInicial, false);

        assertEquals("mayor", resultado.get("resultado"));
    }


    // Clase de estado para String
    static class EstadoTexto {
        String texto;
        public EstadoTexto(String texto) {
            this.texto = texto;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EstadoTexto that = (EstadoTexto) o;
            return java.util.Objects.equals(texto, that.texto);
        }
        @Override
        public int hashCode() {
            return java.util.Objects.hash(texto);
        }
    }

    // Clase de estado para List<Integer>
    static class EstadoListaNumeros {
        List<Integer> numeros;
        public EstadoListaNumeros(List<Integer> numeros) {
            this.numeros = numeros;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EstadoListaNumeros that = (EstadoListaNumeros) o;
            return java.util.Objects.equals(numeros, that.numeros);
        }
        @Override
        public int hashCode() {
            return java.util.Objects.hash(numeros);
        }
    }

    // Clase de estado simple para probar con enteros
    static class Contador {
        int valor;
        public Contador(int valor) {
            this.valor = valor;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Contador contador = (Contador) o;
            return valor == contador.valor;
        }
        @Override
        public int hashCode() {
            return java.util.Objects.hash(valor);
        }
    }
}