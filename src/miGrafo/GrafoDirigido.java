package miGrafo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class GrafoDirigido<T> implements Grafo<T> {
	// grafo dirigido debe contener todas las ciudades (vertices)
	// y los vertices van a tener las rutas q los unen(arcos)
	// Entonces en este caso Integer representa los id de Vertices y una lista de
	// Arcos q dirigen a otros vertices
	private HashMap<Integer, List<Arco<T>>> verticeConArcos;

	// si tenemos contemplados todos los datos entonces deberiamos tener un
	// constructor para la clase

	public GrafoDirigido() {
		this.verticeConArcos = new HashMap<>();
	}

	@Override
	public void agregarVertice(int verticeId) {
		// primero debemos controlar que ese vertice no exista ya (no puede haber dos
		// ciudades q sean lo mismo)
		if (!verticeConArcos.containsKey(verticeId)) {
			// agregar el verticeid nuevo (integer) con sus arcos (arrayList)
			verticeConArcos.put(verticeId, new ArrayList<>());
		}
	}

	@Override
	public void borrarVertice(int verticeId) {
		if (verticeConArcos.containsKey(verticeId)) {
			// mientras esa key exista borro la key de ese vertice
			verticeConArcos.remove(verticeId);
			// y debo eliminar los arcos de ese vertice
			for (List<Arco<T>> listaArcos : verticeConArcos.values()) {
				// esto es un remove con condicion, primero pones lo q queres borrar (arco)
				// luego la condicion
				// si arco.getVerticeDestino es igual al verticeId entonces puedo removerlo
				listaArcos.removeIf(arco -> arco.getVerticeDestino() == verticeId);
			}
			/*
			 * VERSION SIN EL REMOVEIF for (List<Arco<T>> listaArcos :
			 * verticeConArcos.values()) { Iterator<Arco<T>> it = listaArcos.iterator();
			 * while (it.hasNext()) { Arco<T> arco = it.next(); if (arco.getVerticeDestino()
			 * == verticeId) { it.remove(); // borra el arco si apunta al vertice eliminado
			 * Porque no podés eliminar elementos de una lista mientras la estás recorriendo
			 * con for-each. Si lo hacés, salta ConcurrentModificationException
			 */
		}
	}

	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		// si el vertice existe y el vertice dos existe
		if (verticeConArcos.containsKey(verticeId1) && verticeConArcos.containsKey(verticeId2)) {
			// podria agregar que si o si tenga etiqueta para q todos los arcos tengan
			// etiqueta
			if (etiqueta != null) {
				// creo el arco con la etiqueta y los datos de los vertices origen y destino
				Arco<T> newArco = new Arco<T>(verticeId1, verticeId2, etiqueta);
				// una vez creado el arco debo agregarlo al vertice de origen
				// verticeid1 ----->verticeid2 osea el nuevo arco creado
				verticeConArcos.get(verticeId1).add(newArco);
			}
		}
	}

	@Override
	// necesito decir desde que vertice a que vertice quiero borrar el arco, sino
	// deberia borrar todos y es un quilombo
	public void borrarArco(int verticeId1, int verticeId2) {
		// mientras verticeConArcos contenga verticesId1 (porq es el q apunta a 2)
		if (verticeConArcos.containsKey(verticeId1)) {
			// realizo una lista con los arcos q tiene verticeId1
			List<Arco<T>> arcosDeV1 = verticeConArcos.get(verticeId1);
			// remuevo los arcos que van desde vertice 1 hasta el vertice 2
			// osea que coincidan v1--->v2 (si hay varios tambien tengo q borrarlos)
			arcosDeV1.removeIf(arcos -> arcos.getVerticeDestino() == verticeId2);
		}
	}

	@Override
	public boolean contieneVertice(int verticeId) {
		// si vertice con arcos contiene la key vertice, no hay mucho mas
		return verticeConArcos.containsKey(verticeId);
	}

	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		// simplemente si obtener arco es diferente de null retorno true
		return obtenerArco(verticeId1, verticeId2) != null;
	}

	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		// creo una lista para agregar los arcos de verticeId1
		List<Arco<T>> arcosDeV1 = verticeConArcos.get(verticeId1);
		// recorro arcosDeV1 para ver si coincide con verticeId2
		for (Arco<T> arco : arcosDeV1) {
			if (arco.getVerticeDestino() == verticeId2) {
				return arco;
			}
		}
		return null;
	}

	@Override
	public int cantidadVertices() {
		// esto funciona porq no deje q se pusieran vertices q tengan null arcos
		return verticeConArcos.size();
	}

	@Override
	public int cantidadArcos() {
		int cont = 0;
		for (List<Arco<T>> arcos : verticeConArcos.values()) {
			cont += arcos.size();
		}
		return cont;
	}

	@Override
	public Iterator<Integer> obtenerVertices() {
		// Devuelve un iterador con todos los vértices que tienen al menos una lista de
		// arcos (están en el mapa)
		return verticeConArcos.keySet().iterator();
	}

	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		// Obtiene la lista de arcos que salen del vértice dado
		List<Arco<T>> arcos = verticeConArcos.get(verticeId);

		// Lista donde voy a guardar los vértices adyacentes (destinos de los arcos)
		List<Integer> adyacentes = new ArrayList<>();

		// Si el vértice tiene arcos salientes
		if (arcos != null) {
			// Por cada arco, agrego el vértice destino a la lista de adyacentes
			for (Arco<T> arco : arcos) {
				adyacentes.add(arco.getVerticeDestino());
			}
		}

		// Devuelvo un iterador sobre la lista de adyacentes
		return adyacentes.iterator();
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		// Creo una lista para juntar todos los arcos del grafo
		List<Arco<T>> todos = new ArrayList<>();

		// Recorro todas las listas de arcos de cada vértice
		for (List<Arco<T>> lista : verticeConArcos.values()) {
			// Agrego todos los arcos de esa lista a la lista general
			todos.addAll(lista);
		}

		// Devuelvo un iterador con todos los arcos del grafo
		return todos.iterator();
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		// Obtengo la lista de arcos que salen del vértice
		List<Arco<T>> arcos = verticeConArcos.get(verticeId);

		// Si no tiene arcos, devuelvo un iterador de una lista vacía
		if (arcos == null) {
			return new ArrayList<Arco<T>>().iterator();
		}

		// Si tiene, devuelvo el iterador sobre esa lista
		return arcos.iterator();
	}
	public static void dfs(GrafoDirigido<?> grafo) {
	    HashMap<Integer, String> colores = new HashMap<>();
	    HashMap<Integer, Integer> d = new HashMap<>();
	    HashMap<Integer, Integer> f = new HashMap<>();
	    int[] tiempo = {0};
	    boolean[] hayCiclo = {false};

	    Iterator<Integer> it = grafo.obtenerVertices();
	    while (it.hasNext()) {
	        int v = it.next();
	        colores.put(v, "BLANCO");
	    }

	    it = grafo.obtenerVertices();
	    while (it.hasNext()) {
	        int u = it.next();
	        if (colores.get(u).equals("BLANCO")) {
	            dfsVisitar(u, grafo, colores, d, f, tiempo, hayCiclo);
	        }
	    }

	    if (hayCiclo[0]) {
	        System.out.println("¡Hay ciclo!");
	    } else {
	        System.out.println("No hay ciclo.");
	    }

	    // Imprimir tiempos si querés
	    for (Integer v : d.keySet()) {
	        System.out.println("Vertice " + v + ": d=" + d.get(v) + ", f=" + f.get(v));
	    }
	}

	private static void dfsVisitar(int u, GrafoDirigido<?> grafo,
	    HashMap<Integer, String> colores,
	    HashMap<Integer, Integer> d,
	    HashMap<Integer, Integer> f,
	    int[] tiempo,
	    boolean[] hayCiclo) {

	    colores.put(u, "AMARILLO");
	    tiempo[0]++;
	    d.put(u, tiempo[0]);

	    Iterator<Integer> it = grafo.obtenerAdyacentes(u);
	    while (it.hasNext()) {
	        int v = it.next();
	        String color = colores.getOrDefault(v, "BLANCO");
	        if (color.equals("BLANCO")) {
	            dfsVisitar(v, grafo, colores, d, f, tiempo, hayCiclo);
	        } else if (color.equals("AMARILLO")) {
	            hayCiclo[0] = true;
	        }
	    }

	    colores.put(u, "NEGRO");
	    tiempo[0]++;
	    f.put(u, tiempo[0]);
	}

}