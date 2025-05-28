package tp2ArbolesBinarios;

public class Main {

	public static void main(String[] args) {
		
		Tree t1 = new Tree();
	
		t1.add(10);
		t1.add(12);
		t1.add(11);
		t1.add(6);
		t1.add(15);
		t1.add(14);
		t1.add(13);
		t1.add(18);
		t1.add(20);
			
		t1.add(8);
		t1.add(7);
		t1.add(4);
	    t1.add(2);
		t1.add(16);
		t1.add(5);
		
		//IMPRIMIR ARBOL
		t1.printTree();
		//IMPRIMIR LA RAIZ (NO LA DE MIS PROBLEMAS, ESA ES IMPOSIBLE)
		System.out.println("La raiz es: " + t1.getRoot());
		//DECIRME BIEN DECIDO SI ESTA UN ELEMENTO O NO EN EL BENDITO ARBOL
		System.out.println("El elemento existe? " + t1.hasElem(12));
		//ELIMINAR UN ELEMENTO DEL ARBOL
		System.out.println("Se logro eliminar el elemento? " + t1.delete(14));
		t1.printTree();
		//IMPRIMIR LA ALTURA
		System.out.println("Altura del arbol es " + t1.getHeight());
		//IMPRIMIR POSTORDER PREORDER INORDER
		t1.printPostOrder();
		System.out.println(' ');
		t1.printPreOrder();
		System.out.println(' ');
		t1.printInOrder();
		System.out.println(' ');
		//IMPRIMIR LA RAMA MAS LARGA
		System.out.println("La rama mas larga es: " + t1.getLongestBranch());
		//DEVOLVER LISTA DE LOS HIJOS SIN HOJAS GETFRONTERA
		System.out.println("Encontrar la frontera " + t1.getFrontera());
		//DEVOLVER EL MAXIMO VALOR DEL ARBOL
		System.out.println("Encontrar el valor mas grande " + t1.getMaxElem());
		//ENCONTRAR UN ELEMENTO EN CIERTO NIVEL
		System.out.println("Los elementos del lvl son: " + t1.getElemAtLevel(0));
	}
}
