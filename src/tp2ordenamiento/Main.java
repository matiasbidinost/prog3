package tp2ordenamiento;

public class Main {
	public static void main(String[] args) {
		int[] arr = { 1, 4, 12, 40 };
		int[] arr1 = { 1, 8, 2, 7 };

		Orden o1 = new Orden(arr);

		Orden o2 = new Orden(arr1);

		System.out.println(o1.estaEnOrden());
		System.out.println(o2.estaEnOrden());
		// complejidad O(n) porq en el peor caso voy a tener q recorrer todo el arreglo

		// puede tener problemas de stackOverflow si el arr es demasiado grande y hay
		// demasiadas llamadas recursivas
		/* Acceso por índice:
		 * En un array (int[]) acceder a arr[i] es O(1), o sea instantáneo.
		 * En una LinkedList, acceder a list.get(i) es O(n), porque tiene que recorrer
		 * la lista desde el principio hasta el índice i.
		 * Si hacés el mismo algoritmo accediendo con índices (list.get(i) y list.get(i
		 * + 1)), el tiempo total se vuelve O(n²) en el peor caso.
		 * Esto se debe a que por cada llamada recursiva estás haciendo un recorrido
		 * nuevo para acceder a los elementos. 
		 * Cómo mejorarlo:
		 * Una forma más eficiente sería usar un iterador (Iterator) y comparar
		 * elementos uno a uno sin usar índices. Así mantenés una complejidad O(n).
		 */
		System.out.println(o1.buscador(4) );
		System.out.println(o1.impresionBinaria());
		o1.impresionFibonacci(10);
	}
}
