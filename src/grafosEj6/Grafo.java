package grafosEj6;

import java.util.Iterator;

import miGrafo.Arco;

public interface Grafo<T> {
	

		public void agregarVertice(int verticeId);
		public void borrarVertice(int verticeId);
		public void agregarArco(int verticeId1, int verticeId2, T etiqueta);
		public void borrarArco(int verticeId1, int verticeId2);
		public boolean contieneVertice(int verticeId);
		public boolean existeArco(int verticeId1, int verticeId2);
		public Calle<T> obtenerArco(int verticeId1, int verticeId2);
		public int cantidadVertices();
		public int cantidadArcos();
		public Iterator<Integer> obtenerVertices();
		public Iterator<Integer> obtenerAdyacentes(int verticeId);
		public Iterator<Calle<T>> obtenerArcos();
		public Iterator<Calle<T>> obtenerArcos(int verticeId);
}
