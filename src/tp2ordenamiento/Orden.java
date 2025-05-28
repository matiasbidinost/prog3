package tp2ordenamiento;

import java.util.ArrayList;

public class Orden {
	private int[] arr;

	public Orden(int[] arr) {
		this.arr = arr;
	}

	public boolean estaEnOrden() {
		return estaOrdenado(0);
	}

	public boolean estaOrdenado(int i) {
		if (i >= arr.length - 1) {
			return true; // si llegamos al final es q esta ordenado
		}
		if (arr[i] <= arr[i + 1]) {
			return estaOrdenado(i + 1);
		} else {
			return false; // esta desordenado
		}
	}

	/*
	 * Implemente un algoritmo recursivo para buscar un elemento en un arreglo
	 * ordenado ascendentemente.
	 */
	public String buscador(int elem) {
		int i = recorrido(elem, 0);
		if (i == -1) {
			return "no se hayaba en la casa ese elemento";
		}
		return "el elemento esta en la posicion " + i;
	}

	public int recorrido(int elem, int i) {
		if (i >= arr.length) {
			return -1; // si recorrio todo y no esta es q no existe ese elemento
		}
		if (elem != arr[i]) {// mientras q sea diferente del elemento buscado debe llamarse asi mismo
			return recorrido(elem, i + 1);
		} else {
			return i;
		}
	}

	/*
	 * Implemente un algoritmo recursivo que convierta un número en notación decimal
	 * a su equivalente en notación binaria.
	 */
	public ArrayList<String> impresionBinaria() {
		ArrayList<String> almacen = new ArrayList<String>();
		convertidorBinario(almacen, 0); // es lo mismo q antes, empiezo en la posicion 0 cada vez q necesito llamarlo
		return almacen;
	}

	private ArrayList<String> convertidorBinario(ArrayList<String> almacen, int i) {
		if (i >= arr.length) {
			return almacen;// si i es mayor al arr, es q ya termino
		}
		String codigo;// almacen para el codigo de cada numero
		codigo = convertidorBxNumero(arr[i]);// llamado recursivo al convertidor binario por numero del arr
		almacen.add("elemento " + arr[i] + " codigo binario = " + codigo);
		return convertidorBinario(almacen, i + 1);// prosigo por el siguiente numero del arr
	}

	private String convertidorBxNumero(int i) {
		if (i == 0) {
			return "0";// si la division es resto 0 ese es su numero
		}
		if (i == 1) {
			return "1"; // sino es 1
		}
		return convertidorBxNumero(i / 2) + (i % 2); // el resto (% 2) te da los bits, y el cociente (/ 2) va bajando el
														// número
	}

	public void impresionFibonacci(int i) {
		for (int j = 0; j < i; j++) {
			System.out.print(fibonacci(j) + " ");
		}
	}

	private int fibonacci(int j) {
		if (j == 0) {//primer salto siempre es 0
			return 0;
		} 
		if (j == 1) {
			return 1; // segundo salto es 1
		}
		return fibonacci(j-1) + fibonacci(j-2); // por posicion empieza en 2: hace 2-1= pos 1 y 2-2 = pos 0 osea 0+1 = 1
	}
}
