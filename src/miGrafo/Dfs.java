package miGrafo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dfs {
	private Grafo<?> grafo;// grafo en el cual voy a aplicar dfs
	private HashMap<Integer, String> colors; // lista de colores, su id (int) y su string (color blanco)
	private List<Integer> result; // resultado con los visitados
	private List<List<Integer>> cycles; // cada vez q tengo que encontrar un ciclo debo guardarlo

	public Dfs(Grafo<?> grafo) {
		this.grafo = grafo;
		colors = new HashMap<>();
		result = new LinkedList<>();
		this.cycles = new ArrayList<>();
	}

	public List<Integer> depthFirstSearch() {
		result.clear();// limpio la lista
		// iterator proporciona una forma standart de recorrer una lista, en este caso
		// el grafo
		// que nos va a dar una lista con los vertices
		Iterator<Integer> it = grafo.obtenerVertices();
		// Inicializo todos los vértices como blancos
		while (it.hasNext()) {
			Integer v = it.next();
			colors.put(v, "blanco");
		}
		// Empiezo DFS desde cada vértice blanco
		it = grafo.obtenerVertices(); // Reinicio el iterador
		while (it.hasNext()) { // mientras haya un iterador siguiente
			Integer v = it.next();// iterador siguiente
			if (colors.get(v).equals("blanco")) {
				// si int v es igual a blanco quiere decir q ya fue visitado
				dfsVisit(v); // llamo a visit para marcarlo como amarillo o como negro si fue visitado dos
								// veces
			}
		}

		return result;
	}

	private void dfsVisit(Integer v) {
		System.out.println("Visitando nodo: " + v + " → color: blanco → amarillo");
		colors.put(v, "amarillo"); // lo estoy visitando
		result.add(v); // lo agrego al resultado

		Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(v);
		while (adyacentes.hasNext()) {
			Integer w = adyacentes.next();
			System.out
					.println("  Nodo actual: " + v + " → mirando adyacente: " + w + " (color: " + colors.get(w) + ")");
			if (colors.get(w).equals("blanco")) {
				dfsVisit(w);
			}
		}

		colors.put(v, "negro"); // ya terminé con este nodo
		System.out.println("Finalicé nodo: " + v + " → color: negro");
	}

	public String getColor(Integer nodo) {
		return colors.get(nodo);
	}

	public List<List<Integer>> findCycles() {
		cycles.clear();
		initialState();
		Iterator<Integer> it = grafo.obtenerVertices();
		while (it.hasNext()) {
			Integer node = it.next();
			if (colors.get(node).equals("blanco")) {
				dfsCycle(node, new ArrayList<>());
			}
		}

		return cycles;
	}

	private void dfsCycle(Integer current, List<Integer> path) {
		colors.put(current, "amarillo");
		path.add(current);

		Iterator<Integer> neighbors = grafo.obtenerAdyacentes(current);
		while (neighbors.hasNext()) {
			Integer neighbor = neighbors.next();

			if (colors.get(neighbor).equals("blanco")) {
				dfsCycle(neighbor, new ArrayList<>(path));
			} else if (colors.get(neighbor).equals("amarillo")) {
				// ciclo detectado
				int index = path.indexOf(neighbor);
				if (index != -1) {
					List<Integer> cycle = new ArrayList<>();
					for (int i = index; i < path.size(); i++) {
						cycle.add(path.get(i));
					}
					cycle.add(neighbor); // cerrar el ciclo
					cycles.add(cycle);
					System.out.println("ciclo encontrado: 1ro= " + cycle.get(0) + " 2do= " + neighbor);
				}
			}
		}
		colors.put(current, "negro");
	}

	// dfs pero con un punto de origen y destino
	public List<Integer> getTheBestRoute(int origin, int destiny) {
		result.clear();
		initialState();
		ArrayList<Integer> currentRoute = new ArrayList<>();
		route(origin, destiny, currentRoute);
		return result;
	}

	private void route(int current, int destiny, ArrayList<Integer> currentRoute) {
		// Si el tamaño actual es mayor al anterior guardo el mayor
		colors.put(current, "amarillo");
		// agregar current a la ruta alternativa
		currentRoute.add(current);
		// siempre primero condicion de corte, en este caso cuando current llega a
		// destino se encontro una ruta
		if (current == destiny) {
			if (currentRoute.size() > result.size()) {
				result = new ArrayList<>(currentRoute);
			}
		} else {
			// iniciamos con los adyacentes de current(osea origen)
			Iterator<Integer> it = grafo.obtenerAdyacentes(current);

			while (it.hasNext()) {
				Integer next = it.next();// recordar almacenar siempre antes el next sino va a iterando uno encima del
											// otro

				if (colors.get(next).equals("blanco")) {
					// si son blancos falta visitar, entonces hago llamado recursivo
					route(next, destiny, currentRoute);

				}
			}
		}
		// backtracking: desmarco el nodo y lo sacamos del camino actual
		colors.put(current, "blanco");
		// disminuimos su tamaño en 1 asi vamos tambien modificando su cant total
		currentRoute.remove(currentRoute.size() - 1);
	}

	private void initialState() {
		colors.clear();
		Iterator<Integer> it = grafo.obtenerVertices();
		while (it.hasNext()) {
			Integer next = it.next();
			colors.put(next, "blanco");
		}
	}
	/*
	 * Escriba un algoritmo que dado un grafo G y un vértice v de dicho grafo,
	 * devuelva una lista con todos los vértices a partir de los cuales exista un
	 * camino en G que termine en v.
	 */
	public List<Integer> findWaysTo(Integer vertice) {
	    result.clear();
	    //desde el destino a el inicio
	    // Invertir el grafo
	    //desde cualquier punto trato de llegar a destino y si no puedo llegar a destino no lo guardo
	    //destino siendo el punto final e inicio, cualquier posicion en el grafo
	    Map<Integer, List<Integer>> invertido = new HashMap<>();

	    Iterator<Integer> it = grafo.obtenerVertices();
	    while (it.hasNext()) {
	        Integer origen = it.next();
	        Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(origen);
	        while (adyacentes.hasNext()) {
	            Integer destino = adyacentes.next();
	            
	            if (!invertido.containsKey(destino)) {
	                invertido.put(destino, new ArrayList<>());
	            }
	            invertido.get(destino).add(origen);
	        }
	    }

	    // DFS desde el vértice destino, pero en el grafo invertido
	    Set<Integer> visitados = new HashSet<>();
	    findPathTo(vertice, invertido, visitados);

	    visitados.remove(vertice); // Opcional, si no querés incluir al propio vértice
	    result.addAll(visitados);
	    return result;
	}

	private void findPathTo(Integer actual, Map<Integer, List<Integer>> invertido, Set<Integer> visitados) {
	    visitados.add(actual);

	    if (invertido.containsKey(actual)) {
	        for (Integer vecino : invertido.get(actual)) {
	            if (!visitados.contains(vecino)) {
	                findPathTo(vecino, invertido, visitados);
	            }
	        }
	    }
	}
	
}
