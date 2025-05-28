package tp4Grafos;

import java.util.*;

public class Vertice<T> {

    private int id;
    private Hashtable<Integer, Arco<T>> arcos;

    public Vertice(int id) {
        this.id = id;
        this.arcos = new Hashtable<>();
    }

    public void agregarArco(Arco<T> arco) {
        arcos.put(arco.getVerticeDestino(), arco);
    }

    public void borrarArco(int destino) {
        arcos.remove(destino);
    }

    public boolean tieneArco(int destino) {
        return arcos.containsKey(destino);
    }

    public Arco<T> obtenerArco(int destino) {
        return arcos.get(destino);
    }

    public Iterator<Integer> obtenerAdyacentes() {
        return arcos.keySet().iterator();
    }

    public Iterator<Arco<T>> obtenerArcos() {
        return arcos.values().iterator();
    }
}
