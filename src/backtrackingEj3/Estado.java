package backtrackingEj3;

import java.util.ArrayList;
import java.util.List;

public class Estado {
	private List<Integer> arr; // Lista original de números
	private List<Integer> camino; // Subconjunto actual
	private int indice; // Índice actual en el arreglo
	private int sumaParcial; // Suma acumulada

	public Estado(List<Integer> arr) {
		this.arr = arr;
		this.camino = new ArrayList<>();
		this.indice = 0;
		this.sumaParcial = 0;
	}

	public boolean numerosPorProbar() {
		//mientras el indice no haya llegado al tamanio del arr original
		return indice < arr.size();
	}

	public boolean esFactible() {
		return true; // Siempre podemos intentar un número
	}

	public void iteracion() {
		//guardo el valor del arr>indice
		int valor = arr.get(indice);
		//agrego al camino el valor en el q estoy
		camino.add(valor);
		//sumo el nuevo valor a la sumaParcial
		sumaParcial += valor;
		//aumento el indice
		indice++;
	}

	public void saltarNumActual() {
		indice++; // Avanza sin sumar el número actual
	}

	public void volverMov() {
		//reduzco el indice actual de estado
		indice--;
		int valor = arr.get(indice);//obtengo el indice
		sumaParcial -= valor;//resto el valor de ese indice
		camino.remove(camino.size() - 1);//remuevo el ultimo elem de camino
	}

	public int getSuma() {
		//me devuelve la suma actual que tiene estado
		return sumaParcial;
	}

	public List<Integer> getResultadoArr() {
		//devuelvo el arr con los numeros almacenados que recorri (para compararlo si es igual al resultado)
		return new ArrayList<>(camino);
	}

	public int getIndiceActual() {
		return indice;
	}

	public int getValorActual() {
		return arr.get(indice);
	}

	public void resetIndice(int i) {
		this.indice = i;
	}
}
