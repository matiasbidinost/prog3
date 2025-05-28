package tp1ej1;

import java.util.Iterator;

public class MyIterator<T> implements Iterator<T> {
    private Node<T> cursor;

    public MyIterator(Node<T> first) {
        this.cursor = first;
    }

    @Override
    public boolean hasNext() {
        return this.cursor != null; // Devuelve true si hay más elementos
    }

    @Override
    public T next() {
        T info = cursor.getInfo(); // Obtiene el valor actual
        cursor = cursor.getNext(); // Avanza al siguiente nodo
        return info;
    }
}
