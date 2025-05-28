package backtrackingEj2;

public class Main {
	public static void main(String[] args) {
		BacktrackingLaberinto lab = new BacktrackingLaberinto(3, 3);

		lab.setCasilla(0, 0, 1, true, true, true, false);
		lab.setCasilla(0, 1, 2, true, false, true, true);
		lab.setCasilla(0, 2, 3, true, false, false, true);
		lab.setCasilla(1, 0, 4, true, true, true, false);
		lab.setCasilla(1, 1, 5, true, true, true, true);
		lab.setCasilla(1, 2, 6, false, true, false, true);
		lab.setCasilla(2, 0, 7, true, false, true, false);
		lab.setCasilla(2, 1, 8, true, false, true, true);
		lab.setCasilla(2, 2, 9, false, false, false, true);

		lab.buscarCaminoMinimo(0, 0, 2, 2);
		/*
		 * arbol de desiciones: desde una mat de casillas determino hacia donde se puede
		 * mover rastreando el camino con una lista booleana de visitados y la desicion
		 * se determina con un VOY POR ESTE CAMINO - NO VOY POR ESTE CAMINO y guardar el
		 * coste(valor total) y los saltos(mejor camino)
		 */
		System.out.println("Mejor costo: " + lab.getMejorCosto());
		System.out.println("Mejor camino: " + lab.getMejorCamino());
	}
}