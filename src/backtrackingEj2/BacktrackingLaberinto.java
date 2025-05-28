package backtrackingEj2;

import java.util.ArrayList;
import java.util.List;
/*
 * Dado un laberinto consistente en una matriz cuadrada que tiene en cada
 * posición un valor natural y cuatro valores booleanos, indicando estos últimos
 * si desde esa casilla se puede ir al norte, este, sur y oeste, encontrar un
 * camino de longitud mínima entre dos casillas dadas, siendo la longitud de un
 * camino la suma de los valores naturales de las casillas por las que pasa.
 * Idea: podría representarse el laberinto como una matriz, de objetos, donde
 * cada objeto tiene el valor natural, y cuatro booleanos, uno para cada
 * dirección a la que se permite ir desde allí.
 */

//para que voy a hacer una matriz si puedo determinar la pos con una lista de casillas
//arr me da la pos de columna y luego dentro de casilla tengo la pos de fila, su valor, y a donde se puede mover
public class BacktrackingLaberinto {
	private Casilla[][] laberinto;
	private boolean[][] visitados;
	private int filas, columnas;
	private int mejorCosto;
	private List<String> mejorCamino;

	public BacktrackingLaberinto(int filas, int columnas) {
		this.filas = filas;
		this.columnas = columnas;
		laberinto = new Casilla[filas][columnas];
		visitados = new boolean[filas][columnas];
		mejorCosto = Integer.MAX_VALUE;
		mejorCamino = new ArrayList<>();
	}

	public void setCasilla(int fila, int columna, int valor, boolean norte, boolean sur, boolean este, boolean oeste) {
		laberinto[fila][columna] = new Casilla(valor, norte, sur, este, oeste);
	}

	public void buscarCaminoMinimo(int filaInicio, int colInicio, int filaDestino, int colDestino) {
		List<String> caminoActual = new ArrayList<>();
		backtracking(filaInicio, colInicio, filaDestino, colDestino, 0, caminoActual);
	}

	private void backtracking(int f, int c, int filaDestino, int colDestino, int costoAcumulado,
			List<String> caminoActual) {
		//si esa fila y columna fue visitada la marco como true
		visitados[f][c] = true;
		//sumo el valor para calcular el costo y lo agrego al camino actual
		costoAcumulado += laberinto[f][c].getValor();
		caminoActual.add("(" + f + "," + c + ")");

		if (f == filaDestino && c == colDestino) {
			//lo mismo de siempre
			if (costoAcumulado < mejorCosto) {
				mejorCosto = costoAcumulado;
				mejorCamino = new ArrayList<>(caminoActual);
			}
		} else {
			//hijos.. pero hijos de puta, porq no se me ocurre otra manera
			if (laberinto[f][c].isNorte() && f > 0 && !visitados[f - 1][c])
				backtracking(f - 1, c, filaDestino, colDestino, costoAcumulado, caminoActual);

			if (laberinto[f][c].isSur() && f < filas - 1 && !visitados[f + 1][c])
				backtracking(f + 1, c, filaDestino, colDestino, costoAcumulado, caminoActual);

			if (laberinto[f][c].isEste() && c < columnas - 1 && !visitados[f][c + 1])
				backtracking(f, c + 1, filaDestino, colDestino, costoAcumulado, caminoActual);

			if (laberinto[f][c].isOeste() && c > 0 && !visitados[f][c - 1])
				backtracking(f, c - 1, filaDestino, colDestino, costoAcumulado, caminoActual);
		}

		visitados[f][c] = false;
		caminoActual.remove(caminoActual.size() - 1);
	}

	public int getMejorCosto() {
		return mejorCosto;
	}

	public List<String> getMejorCamino() {
		return mejorCamino;
	}
}