package miGrafo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Bfs {
	/*
	 * guardo en grafos el grafo q tengo q marcar para hacer el recorrido de bfs
	 * BFS:Breadth First Search o busqueda de ruta por nivel, por ahora no buscamos
	 * nada pero hay q realizar el recorrido colors va a tener un string con el
	 * color blanco con los no visitados, amarillo con los visitados una vezz y
	 * negro con los q no se pueden visitar mas resultado simplemente devuelve el
	 * recorrido
	 */
	private Grafo<?> grafo;
	private HashMap<Integer, String> colors;
	private List<Integer> result;

	public Bfs(Grafo<?> grafo) {
		this.grafo = grafo;
		this.colors = new HashMap<>();
		this.result = new LinkedList<>();
	}

	public List<Integer> bfs() {
		result.clear();
		Iterator<Integer> it = grafo.obtenerVertices();

		// Inicializo todos los vértices como blancos
		while (it.hasNext()) {
			Integer v = it.next();
			colors.put(v, "blanco");
		}
		// arranca la busqueda en cada componente que no esta conectado del grafo
		// despues del primer recorrido hay q buscar los que quedaron sin visitar
		it = grafo.obtenerVertices();
		while (it.hasNext()) {
			Integer v = it.next();
			if (colors.get(v).equals("blanco")) { // si ya estan pintados de amarillo o negro
				//descarto y empiezo por blanco												
				bfsDesde(v); // arranco bfs desde el nodo no visitado
			}
		}
		// result tiene q retornar porq es lo q me marca el recorrido y se modifica en
		// bfsDesde
		return result;
	}

	private void bfsDesde(Integer v) {
		LinkedList<Integer> cola = new LinkedList<>(); // Creamos una cola para ir guardando los nodos que vamos a visitar

		colors.put(v, "amarillo"); // Marcamos el nodo inicial como "amarillo", o sea, descubierto pero no terminado
		cola.add(v); // Lo metemos en la cola para empezar a recorrer desde ahí
		System.out.println("Inicio BFS desde: " + v);

		while (!cola.isEmpty()) { // Mientras haya nodos por visitar...
			Integer actual = cola.removeFirst(); // Sacamos el primero de la cola (el más antiguo en espera)
			result.add(actual); // Lo agregamos a la lista de resultado, porque ya lo estamos visitando
			System.out.println("Visitando nodo: " + actual);

			Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(actual); // Buscamos los vecinos del nodo actual

			while (adyacentes.hasNext()) { // Recorremos todos los vecinos
				Integer vecino = adyacentes.next();
				System.out.println("  Mirando adyacente: " + vecino + " (color: " + colors.get(vecino) + ")");

				if (colors.get(vecino).equals("blanco")) { // Si ese vecino no fue visitado todavía...
					colors.put(vecino, "amarillo"); // Lo marcamos como descubierto
					cola.add(vecino); // Y lo agregamos a la cola para visitarlo después
					System.out.println("    → Lo marco como amarillo y lo agrego a la cola");
				}
			}

			colors.put(actual, "negro"); // Ya terminamos de procesar el nodo actual, lo marcamos como terminado
			System.out.println("Finalicé nodo: " + actual + " → color: negro");
		}
	}

	public String getColor(Integer nodo) {
		return colors.get(nodo);
	}
}