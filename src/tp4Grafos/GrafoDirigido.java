package tp4Grafos;

import java.util.*;

public class GrafoDirigido<T> implements Grafo<T> {

	private Hashtable<Integer, Vertice<T>> vertices;
	
	public GrafoDirigido() {
		this.vertices = new Hashtable<>();
	}

	@Override
	public void agregarVertice(int verticeId) {
		if (!vertices.containsKey(verticeId)) {
			vertices.put(verticeId, new Vertice<>(verticeId));
		}
	}

	@Override
	public void borrarVertice(int verticeId) {
		if (vertices.containsKey(verticeId)) {
			vertices.remove(verticeId);
			for (Vertice<T> v : vertices.values()) {
				v.borrarArco(verticeId);
			}
		}
	}

	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		if (vertices.containsKey(verticeId1) && vertices.containsKey(verticeId2)) {
			vertices.get(verticeId1).agregarArco(new Arco<>(verticeId1, verticeId2, etiqueta));
		}
	}

	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		if (vertices.containsKey(verticeId1)) {
			vertices.get(verticeId1).borrarArco(verticeId2);
		}
	}

	@Override
	public boolean contieneVertice(int verticeId) {
		return vertices.containsKey(verticeId);
	}

	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		if (vertices.containsKey(verticeId1)) {
			return vertices.get(verticeId1).tieneArco(verticeId2);
		}
		return false;
	}

	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		if (vertices.containsKey(verticeId1)) {
			return vertices.get(verticeId1).obtenerArco(verticeId2);
		}
		return null;
	}

	@Override
	public int cantidadVertices() {
		return vertices.size();
	}

	@Override
	public int cantidadArcos() {
		int arcoCount = 0;

		for (Vertice<T> v : vertices.values()) {
			Iterator<Arco<T>> arcos = v.obtenerArcos();

			int cantidad = 0;
			while (arcos.hasNext()) {
				cantidad++;
				arcos.next();
			}

			arcoCount += cantidad;
		}

		return arcoCount;
	}

	@Override
	public Iterator<Integer> obtenerVertices() {
		return vertices.keySet().iterator();
	}

	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		if (vertices.containsKey(verticeId)) {
			return vertices.get(verticeId).obtenerAdyacentes();
		}
		return new ArrayList<Integer>().iterator();
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		ArrayList<Arco<T>> todos = new ArrayList<>();
		for (Vertice<T> v : vertices.values()) {
			Iterator<Arco<T>> it = v.obtenerArcos();
			while (it.hasNext()) {
				todos.add(it.next());
			}
		}
		return todos.iterator();
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		if (vertices.containsKey(verticeId)) {
			return vertices.get(verticeId).obtenerArcos();
		}
		return new ArrayList<Arco<T>>().iterator();
	}
}
