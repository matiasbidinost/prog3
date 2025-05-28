package backtrackingEj3;

import java.util.Arrays;
import java.util.List;

public class Main {
	/*
	 * Suma desubconjuntos. Dados n números positivos distintos, se desea encontrar
	 * todas las combinaciones de esos números tal que la suma sea igual a M.
	 */
	public static void main(String[] args) {
		// declaro la clase busqueda y agrego un arr para buscar ahi los resultados
		// invoco al metodo de esa clase subconjuntos y le paso un numero
		// tiene q devolverme una lista

		// Lista de números disponibles
		List<Integer> numeros = Arrays.asList(2, 4, 6, 10);

		// Valor objetivo a alcanzar con la suma de subconjuntos
		int objetivo = 16;

		// Instancio la clase que maneja la búsqueda
		BusquedaBack buscador = new BusquedaBack();

		// Llamo al método para obtener todos los subconjuntos que suman al objetivo
		List<List<Integer>> soluciones = buscador.subconjuntos(numeros, objetivo);

		// Imprimo las soluciones encontradas
		System.out.println("Subconjuntos que suman " + objetivo + ":");
		for (List<Integer> subconjunto : soluciones) {
			System.out.println(subconjunto);
		}
	}

}
