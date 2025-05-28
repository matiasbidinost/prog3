package tp1ej1;

public class main1 {

	public static void main(String[] args) {
		MySimpleLinkedList<Integer> lista = new MySimpleLinkedList<Integer>();
		lista.insertFront(10);
		lista.insertFront(20);
		lista.insertFront(30);
		lista.insertFront(30);

		for (Integer list : lista) { // la complejidad baja a O(n), porque simplemente se mueve de nodo en nodo sin
										// empezar de nuevo en cada iteración.
			System.out.print("[ " + list + " ]");
		}
		System.out.println(" "); // espacio necesario para q se imprima la lista y luego una tabulacion abajo
		System.out.println(lista.indexOf(30));

		System.out.println(lista); // En una lista enlazada (LinkedList), recorrerla manualmente con un for clásico
									// (get(i)) es O(n²) en el peor caso, porque cada get(i) necesita avanzar desde
									// el inicio hasta el índice i
		System.out.println(lista.get(2));

		System.out.println(lista.extractFront());

		System.out.println(lista);

		System.out.println(lista.delete(1));

		System.out.println(lista);
		
		lista.insertFront(200);
		lista.insertFront(20);
		lista = lista.sort();//sentencia para que la liste se actualice luego del sort
		//Si no la reasignamos (lista = lista.sort();), la lista original nunca se actualiza.
		
		System.out.println("lista ordenada: " + lista);
		// ordenando 2 listas en 1
		MySimpleLinkedList<Integer> list1 = new MySimpleLinkedList<>();
		MySimpleLinkedList<Integer> list2 = new MySimpleLinkedList<>();

		// Insertar elementos ordenados en ambas listas
		list1.insertFront(10);
		list1.insertFront(20);
		list1.insertFront(30);

		list2.insertFront(5);
		list2.insertFront(15);
		list2.insertFront(25);

		System.out.println("Lista 1: " + list1);
		System.out.println("Lista 2: " + list2);
		
		MySimpleLinkedList<Integer> list3 = list1.merge(list1, list2);
		System.out.println("esta es la nueva lista integra a las dos listas " + list3);
	}

}
