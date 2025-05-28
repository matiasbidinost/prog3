package tp1ej1;

import java.util.Iterator;

public class MySimpleLinkedList<T extends Comparable<T>> implements Iterable<T>{

	private Node<T> first;
	private int size;

	public MySimpleLinkedList() {
		this.first = null;
		this.size = 0;
	}

	// O(1) se inserta una vez el nodo deseado
	public void insertFront(T info) {
		Node<T> tmp = new Node<T>(info, null);
		tmp.setNext(this.first);
		this.first = tmp;
		size++;
	}

	// O(1) se busca una sola vez el nodo que se va a extraer
	public T extractFront() {
		// TODO
		if (!isEmpty()) {
			Node<T> tmp = this.first;
			this.first = this.first.getNext();
			this.size--;
			return tmp.getInfo();
		}
		return null;
	}
	public void insertBack(T info) { //creo un metodo para insertar en el inicio, porq sino no puedo ordenar
	    Node<T> newNode = new Node<>(info, null);
	    
	    if (isEmpty()) {  
	        this.first = newNode;  //Si la lista está vacia, el nuevo nodo es el primero
	    } else {
	        Node<T> cursor = first;
	        while (cursor.getNext() != null) {  //Recorre hasta el último nodo
	            cursor = cursor.getNext();
	        }
	        cursor.setNext(newNode);  //Agrega el nuevo nodo al final
	    }
	    size++;
	}

	public boolean isEmpty() {
		return this.first == null;
	}

	// O(n) se debe buscar n veces los nodos hasta llegar al indice deseado
	public T get(int index) {
		// solo conozco first y size (y el índice que busco)
		Node<T> cursor = this.first;
		int i = 0;
		while (i < index) {
			cursor = cursor.getNext(); // avanzo
			i++;
		}
		if (i == index)
			return cursor.getInfo(); // lo encontré
		else
			return null;
	}

	// O(1) se consulta una vez sola el valor
	public int size() {
		// TODO
		return this.size;
	}

	@Override
	public String toString() {
		String result = "";
		Node<T> current = this.first;

		while (current != null) {
			result += current.getInfo();
			if (current.getNext() != null) {
				result += "-";
			}
			current = current.getNext();
		}

		return result;
	}

	public boolean delete(int pos) { // peor caso O(n)
		if (pos >= 0 && pos < size) { // mientras que la posicion que se quiere eliminar sea mayor a 0 y menor al
										// tamaño
			Node<T> pointerForward = this.first;
			if (pos == 0) { // si la posicion es igual a 0 es el primer nodo, asiq reemplazo first con el
							// getNext
				this.first = first.getNext();// desconecto el primero de la lista y conecto al que le sigue como el
												// primero
				// luego la memoria se libera por si misma
				this.size--;
				return true;
			} else {
				for (int i = 0; i < pos; i++) {
					if (i == pos - 1) { // Si estamos en el nodo anterior al que queremos borrar
						pointerForward.setNext(pointerForward.getNext().getNext()); // Saltea el nodo a borrar 2 lugares
																					// (debo eliminar el del medio)
						this.size--;
						return true; // Salimos del for porque ya hicimos la eliminacion
					}
					pointerForward = pointerForward.getNext(); // salteamos posiciones hasta llegar al nodo anterior
				}
			}

		}
		return false;
	}
	public int indexOf(int value) { // O(n) 
		
		//HAY UN ERROR, YA QUE SI HAY MAS DE 1 VALUE IGUAL SOLO RETORNARA EL PRIMERO Q ENCUENTRA Y SU POSICION
		
		Node<T> pointer = this.first; //guardo la variable en un pointer
		//si el size es 0 entonces no necesito chequear que pointer sea null ya q nunca va a ejecutarse el for
		for (int i = 0; i < size; i++) { // recorro la cantidad de veces que sea necesarias hasta encontrar value
			if(pointer.getInfo().equals(value)) { // si en el primer caso pointer ya encuentra uno, retorna 1
				return i;
			}else {
				pointer = pointer.getNext(); //pointer se vuelve el proximo pointer al cual comparar para saber si es ese
			}
		}		
		return -1;//si el for se completa y no encontro coincidencias es que no existe ese valor o pointer es null
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new MyIterator<>(first);
	}
	public MySimpleLinkedList<T> sort() {
	    if (size <= 1) { //si la lista es menor o igual a uno no tiene sentido ordenarla
	        return this;
	    }

	    MySimpleLinkedList<T> left = new MySimpleLinkedList<>(); //listaas para ordenar
	    MySimpleLinkedList<T> right = new MySimpleLinkedList<>();
	    int middle = size / 2;//punto medio

	    Node<T> current = first;
	    int index = 0;
	    while (current != null) {//debe recorrer mientras que el node sea diferente de null
	        if (index < middle) {// el index solo debe llegar hasta el punto medio de las listas
	            left.insertBack(current.getInfo()); // para mantener orden lo inserto al inicio y no al final de left
	        } else {
	            right.insertBack(current.getInfo());//ahora de right
	        }
	        current = current.getNext(); //voy al siguiente nodo
	        index++;//aumento el tamaño de la nueva lista
	    }

	    left = left.sort();//vuelvo a reordenarlos ahora q tengo todos los numeros
	    right = right.sort();
	    return merge(left, right); //uno las dos listas en una nueva
	}

    public MySimpleLinkedList<T> merge(MySimpleLinkedList<T> left, MySimpleLinkedList<T> right) {
        MySimpleLinkedList<T> result = new MySimpleLinkedList<>();

        while (!left.isEmpty() && !right.isEmpty()) {
            if (left.get(0).compareTo(right.get(0)) >= 0) { // primero debo tener el mayor de right o left
                result.insertBack(left.extractFront());//y lo agrego a left
            } else {
                result.insertBack(right.extractFront());//sino lo agrego a right(va a ser el menor)
            }
        }
        //Si quedaron elementos en right, los agregamos al resultado
        while (!left.isEmpty()) {
            result.insertBack(left.extractFront());
        }

        while (!right.isEmpty()) {
            result.insertBack(right.extractFront());
        }

        return result;
    }
}