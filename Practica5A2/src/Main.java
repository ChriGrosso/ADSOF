public class Main {
	private static GrafoDeEstados<DatosNumericos> construirFlujoDeTrabajo() {
	    GrafoDeEstados<DatosNumericos> sg = new GrafoDeEstados<>("math1 (Add two numbers, and square if even)");

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

	    sg.agregarNodo("final", (DatosNumericos datos) -> datos); // Nodo final incondicional

	    sg.establecerInicial("sumar");
	    sg.establecerFinal("final"); // Ahora el nodo final es 'final'

	    // Enlace condicional: ir a 'cuadrado' si el resultado de la suma es par
	    sg.agregarAristaCondicional("sumar", "cuadrado", (DatosNumericos datos) -> datos.get("result") % 2 == 0);

	    // Enlace incondicional desde 'cuadrado' a 'final'
	    sg.agregarArista("cuadrado", "final");

	    // Enlace incondicional desde 'sumar' a 'final' si la condición no se cumple
	    // (es decir, si la suma es impar, va directamente al final)
	    sg.agregarAristaCondicional("sumar", "final", (DatosNumericos datos) -> datos.get("result") % 2 != 0);

	    return sg;
	}

	public static void main(String[] args) {
	    GrafoDeEstados<DatosNumericos> sg = construirFlujoDeTrabajo();
	    System.out.println(sg);

	    // Prueba con entrada donde la suma es impar (2 + 3 = 5)
	    DatosNumericos entradaImpar = new DatosNumericos(2, 3);
	    System.out.println("input = " + entradaImpar);
	    DatosNumericos resultadoImpar = sg.ejecutar(entradaImpar, true);
	    System.out.println("result = " + resultadoImpar);

	    // Prueba con entrada donde la suma es par (2 + 2 = 4)
	    DatosNumericos entradaPar = new DatosNumericos(2, 2);
	    System.out.println("input = " + entradaPar);
	    DatosNumericos resultadoPar = sg.ejecutar(entradaPar, true);
	    System.out.println("result = " + resultadoPar);
	}
}