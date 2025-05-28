package miGrafo;

import java.util.List;

import miGrafo.GrafoDirigido;

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
		
		//1--->5
		//encontrar camino (1, 5?
		// Agregar arcos
		// grafo.agregarArco(1,1,"A");
		grafo.agregarArco(1, 2, "B");
		grafo.agregarArco(1, 3, "C");
		grafo.agregarArco(4, 2, "E");
		grafo.agregarArco(3, 4, "F");
		grafo.agregarArco(2, 5, "G");
		grafo.agregarArco(3, 6, "D");
		grafo.agregarArco(6, 5, "D");

		grafo.agregarArco(5, 1, "H");

		// clase dfs para ese grafo
		Dfs dfs = new Dfs(grafo);

		List<Integer> resultado = dfs.depthFirstSearch();
		List<List<Integer>> foundCycles = dfs.findCycles();
	

		// Imprimimos el recorrido
		System.out.println("🧭 Recorrido DFS:");
		for (Integer nodo : resultado) {
			System.out.println("Nodo visitado: " + nodo);
		}

		// Imprimimos los colores finales
		System.out.println("🎨 Colores finales de cada nodo:");
		for (Integer nodo : resultado) {
			System.out.println("Nodo " + nodo + " → color: " + dfs.getColor(nodo));

		}
		Bfs bfs = new Bfs(grafo);
		List<Integer> resultadoBFS = bfs.bfs();

		System.out.println("\n🧭 Recorrido BFS:");
		for (Integer nodo : resultadoBFS) {
			System.out.println("Nodo visitado: " + nodo);
		}

		System.out.println("🎨 Colores finales de cada nodo (BFS):");
		for (Integer nodo : resultadoBFS) {
			System.out.println("Nodo " + nodo + " → color: " + bfs.getColor(nodo));
		}
		//ENCONTRAR CICLOS
		System.out.println("\n ciclos detectados:");
		for (List<Integer> cycle : foundCycles) {
			System.out.println("Nodo parte de un ciclo: " + cycle.get(0) + " al " + cycle.get(cycle.size() - 2) + " - " + cycle );
		}
		System.out.println("La ruta mas larga posible a los nodos es: " + dfs.getTheBestRoute(1, 3));
		
		System.out.println("Los que llegan al nodo son : " + dfs.findWaysTo(3));
	}
}