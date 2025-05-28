package grafosEj6;

import java.util.*;

	public class Ciudad<T> {
	    private Grafo<T> grafo;
	    private HashMap<Integer, String> colores; // Mapa para marcar el estado de cada nodo (blanco, amarillo, negro)
	    
	    public Ciudad(Grafo<T> grafo) {
	        this.grafo = grafo;
	        this.colores = new HashMap<>();
	    }

    // Método para encontrar la ruta más corta entre dos calles (A y B) usando BFS
    public List<Integer> rutaMasCorta(Integer inicio, Integer destino) {
        // Inicializo colores: todos los nodos empiezan como "blancos" (no visitados)
        colores.clear();
        Iterator<Integer> it = grafo.obtenerVertices();
        while (it.hasNext()) {
            Integer next = it.next();
            colores.put(next, "blanco");
        }

        // Estructuras para BFS
        LinkedList<Integer> cola = new LinkedList<>(); // Cola de nodos a visitar
        HashMap<Integer, Integer> padre = new HashMap<>(); // Mapa para reconstruir el camino

        // Comenzamos desde el nodo inicio
        colores.put(inicio, "amarillo");
        cola.add(inicio);
        padre.put(inicio, null); // El inicio no tiene padre

        boolean encontrado = false; // Para cortar el BFS cuando lleguemos al destino

        // BFS
        while (!cola.isEmpty() && !encontrado) {
            Integer actual = cola.removeFirst(); // Sacamos el primer nodo de la cola

            // Iteramos sobre sus adyacentes
            Iterator<Integer> itAdy = grafo.obtenerAdyacentes(actual);
            while (itAdy.hasNext()) {
                Integer adyacente = itAdy.next();

                if (colores.get(adyacente).equals("blanco")) { // Si no fue visitado
                    colores.put(adyacente, "amarillo"); // Lo marcamos como visitado
                    padre.put(adyacente, actual); // Guardamos quién es su padre
                    cola.add(adyacente); // Lo agregamos a la cola para seguir el recorrido

                    if (adyacente.equals(destino)) { // Si encontramos el destino, cortamos
                        encontrado = true;
                        break;
                    }
                }
            }

            colores.put(actual, "negro"); // Marcamos como procesado
        }

        // Reconstruimos el camino desde destino hasta inicio usando el mapa padre
        List<Integer> camino = new LinkedList<>();
        if (padre.containsKey(destino)) { // Si existe un camino
            Integer actual = destino;
            while (actual != null) {
                camino.add(0, actual); // Insertamos al principio para que quede de inicio a destino
                actual = padre.get(actual);
            }
        }

        return camino; // Devuelve la lista vacía si no hay camino
    }
}
