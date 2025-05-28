package backtrackingEj1;

import miGrafo.Grafo;
import miGrafo.GrafoDirigido;

public class Main {
	public static void main(String[] args) {
		/*
		 * Se tiene un conjunto de salas comunicadas entre sí a través de puertas que se
		 * abren solamente en un sentido. Una de las salas se denomina entrada y la otra
		 * salida. Construir un algoritmo que permita ir desde la entrada a la salida
		 * atravesando la máxima cantidad de salas. Idea: podría representar el problema
		 * mediante un grafo dirigido, donde cada nodo es una habitación, y cada puerta
		 * es un arco dirigido hacia otra habitación.
		 */
		    // Crear el grafo de habitaciones
			   Grafo<Integer> grafo = new GrafoDirigido<>(); // Asegurate de tener la clase GrafoDirigido

			    for (int i = 1; i <= 9; i++) {
			        grafo.agregarVertice(i);
			    }

			    // Agregar arcos con etiquetas (cualquier número, por ejemplo, el 1)
			    grafo.agregarArco(1, 2, 1);
			    grafo.agregarArco(1, 3, 1);
			    grafo.agregarArco(2, 4, 1);
			    grafo.agregarArco(2, 5, 1);
			    grafo.agregarArco(3, 6, 1);
			    grafo.agregarArco(4, 7, 1);
			    grafo.agregarArco(5, 8, 1);
			    grafo.agregarArco(6, 9, 1);
			    grafo.agregarArco(7, 8, 1);
			    grafo.agregarArco(8, 9, 1);

			    // Crear el objeto de la clase que resuelve el backtracking
			    Backtracking bt = new Backtracking(grafo,1,5);
			    System.out.println(bt.buscarCaminoLargo());
	}
}