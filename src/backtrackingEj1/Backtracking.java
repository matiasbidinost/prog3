package backtrackingEj1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import miGrafo.Grafo;

public class Backtracking {
	private Grafo<Integer> habitaciones; // El grafo con las salas
	private List<Integer> caminoActual; // El camino que estoy armando
	private List<Integer> caminoMasLargo; // El mejor camino encontrado

	private int entrada; // Nodo de inicio
	private int salida; // Nodo de destino

	// Constructor
	public Backtracking(Grafo<Integer> habitaciones, int entrada, int salida) {
		this.habitaciones = habitaciones;
		this.entrada = entrada;
		this.salida = salida;
		this.caminoActual = new ArrayList<>();
		this.caminoMasLargo = new ArrayList<>();
	}

	public List<Integer> buscarCaminoLargo() {
		caminoActual.clear();
		Estado estadoInicial = new Estado(entrada); // Arranca en la entrada
		busqueda(estadoInicial);
		return caminoMasLargo;
	}

	private void busqueda(Estado e) {
		// si la pos actual de estado es igual al destino
		if (e.getPosActual() == salida) {
			// si el tam del camino de estado es mas largo q el caminoMasLargo reemplazo
			if (e.getTamanioCamino() > caminoMasLargo.size()) {
				caminoMasLargo = new ArrayList<>(e.getCaminoActual());
			}
		} else {
			Iterator<Integer> itAdy = habitaciones.obtenerAdyacentes(e.getPosActual());
			while (itAdy.hasNext()) {
				Integer ady = itAdy.next();
				if (!e.yaVisitado(ady)) {
					int anterior = e.getPosActual(); // Guarda la posición antes de avanzar
					e.avanzar(ady);
					busqueda(e); // Recursión
					e.retroceder(anterior); // Volvés al estado anterior
				}
			}
		}
	}

}
