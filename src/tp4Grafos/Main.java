package tp4Grafos;

public class Main {

	public static void main(String[] args) {

		// Creo un grafo dirigdo donde las etiquetas de los arcos son valores Float
		GrafoDirigido<Float> grafito = new GrafoDirigido<>();
		
		// Agrego los vertices 1 y 2
		grafito.agregarVertice(1);
		grafito.agregarVertice(2);

		// Genero un arco desde 1 hasta 2 con el valor de etiqueta 3
		grafito.agregarArco(1, 2, 3F);
		
		// Obtengo el arco entre 1 y 2, y le pido la etiqueta
		Float etiqueta = grafito.obtenerArco(1, 2).getEtiqueta();
		
		System.out.println(etiqueta); // Debería imprimir 3
		
		GrafoDirigido<String> grafo = new GrafoDirigido<>();

        // Agregar vértices
        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarVertice(3);
        grafo.agregarVertice(4);
        grafo.agregarVertice(5);
        grafo.agregarVertice(6);

        // Agregar arcos
        //  grafo.agregarArco(1,1,"A");
        grafo.agregarArco(1, 2, "B");
        grafo.agregarArco(1, 3, "C");
        grafo.agregarArco(4, 2, "E");
        grafo.agregarArco(3, 4, "F");
        grafo.agregarArco(2, 5, "G");
        grafo.agregarArco(3, 6, "D");
        grafo.agregarArco(6, 5, "D");


        grafo.agregarArco(5, 1, "H");

	}

}