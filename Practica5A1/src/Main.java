public class Main {
    public static void main(String[] args) {
        GrafoDeEstados<DatosNumericos> sg = construirFlujoDeTrabajo();
        System.out.println(sg);

        DatosNumericos entrada = new DatosNumericos(2, 3);
        System.out.println("Entrada = " + entrada);
        DatosNumericos salida = sg.ejecutar(entrada, true); // Ejecución con debug
        System.out.println("Resultado = " + salida);
    }

    private static GrafoDeEstados<DatosNumericos> construirFlujoDeTrabajo() {
        GrafoDeEstados<DatosNumericos> sg = new GrafoDeEstados<>("Math2 (Sumar dos números y luego elevar al cuadrado)");

        sg.agregarNodo("sumar", (DatosNumericos datos) -> {
            int op1 = datos.get("op1");
            int op2 = datos.get("op2");
            datos.put("result", op1 + op2);
            return datos;
        });

        sg.agregarNodo("cuadrado", (DatosNumericos datos) -> {
            int resultado = datos.get("result");
            datos.put("result", resultado * resultado);
            return datos;
        });

        sg.establecerInicial("sumar");
        sg.establecerFinal("cuadrado");
        sg.agregarArista("sumar", "cuadrado");

        return sg;
    }
}