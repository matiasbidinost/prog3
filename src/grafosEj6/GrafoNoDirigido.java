package grafosEj6;

import java.util.*;

public class GrafoNoDirigido<T> implements Grafo<T> {
    private HashMap<Integer, List<Calle<T>>> verticeConArcos;

    public GrafoNoDirigido() {
        this.verticeConArcos = new HashMap<>();
    }

    @Override
    public void agregarVertice(int verticeId) {
        if (!verticeConArcos.containsKey(verticeId)) {
            verticeConArcos.put(verticeId, new ArrayList<>());
        }
    }

    @Override
    public void borrarVertice(int verticeId) {
        if (verticeConArcos.containsKey(verticeId)) {
            verticeConArcos.remove(verticeId);
            for (List<Calle<T>> listaArcos : verticeConArcos.values()) {
                listaArcos.removeIf(arco -> arco.getVerticeDestino() == verticeId);
            }
        }
    }

    @Override
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
        if (verticeConArcos.containsKey(verticeId1) && verticeConArcos.containsKey(verticeId2) && etiqueta != null) {
            Calle<T> arco1 = new Calle<>(verticeId1, verticeId2, etiqueta);
            Calle<T> arco2 = new Calle<>(verticeId2, verticeId1, etiqueta);
            verticeConArcos.get(verticeId1).add(arco1);
            verticeConArcos.get(verticeId2).add(arco2);
        }
    }

    @Override
    public void borrarArco(int verticeId1, int verticeId2) {
        if (verticeConArcos.containsKey(verticeId1)) {
            verticeConArcos.get(verticeId1).removeIf(arco -> arco.getVerticeDestino() == verticeId2);
        }
        if (verticeConArcos.containsKey(verticeId2)) {
            verticeConArcos.get(verticeId2).removeIf(arco -> arco.getVerticeDestino() == verticeId1);
        }
    }

    @Override
    public boolean contieneVertice(int verticeId) {
        return verticeConArcos.containsKey(verticeId);
    }

    @Override
    public boolean existeArco(int verticeId1, int verticeId2) {
        return obtenerArco(verticeId1, verticeId2) != null;
    }

    @Override
    public Calle<T> obtenerArco(int verticeId1, int verticeId2) {
        List<Calle<T>> arcos = verticeConArcos.get(verticeId1);
        if (arcos != null) {
            for (Calle<T> arco : arcos) {
                if (arco.getVerticeDestino() == verticeId2) {
                    return arco;
                }
            }
        }
        return null;
    }

    @Override
    public int cantidadVertices() {
        return verticeConArcos.size();
    }

    @Override
    public int cantidadArcos() {
        int cont = 0;
        for (List<Calle<T>> arcos : verticeConArcos.values()) {
            cont += arcos.size();
        }
        return cont / 2; // porque están duplicados
    }

    @Override
    public Iterator<Integer> obtenerVertices() {
        return verticeConArcos.keySet().iterator();
    }

    @Override
    public Iterator<Integer> obtenerAdyacentes(int verticeId) {
        List<Integer> adyacentes = new ArrayList<>();
        List<Calle<T>> arcos = verticeConArcos.get(verticeId);
        if (arcos != null) {
            for (Calle<T> arco : arcos) {
                adyacentes.add(arco.getVerticeDestino());
            }
        }
        return adyacentes.iterator();
    }

    @Override
    public Iterator<Calle<T>> obtenerArcos() {
        Set<String> vistos = new HashSet<>();
        List<Calle<T>> resultado = new ArrayList<>();

        for (Map.Entry<Integer, List<Calle<T>>> entry : verticeConArcos.entrySet()) {
            for (Calle<T> arco : entry.getValue()) {
                int desde = arco.getVerticeOrigen();
                int hasta = arco.getVerticeDestino();
                String clave = Math.min(desde, hasta) + "-" + Math.max(desde, hasta);
                if (!vistos.contains(clave)) {
                    resultado.add(arco);
                    vistos.add(clave);
                }
            }
        }
        return resultado.iterator();
    }

    @Override
    public Iterator<Calle<T>> obtenerArcos(int verticeId) {
        List<Calle<T>> arcos = verticeConArcos.get(verticeId);
        if (arcos == null) return new ArrayList<Calle<T>>().iterator();
        return arcos.iterator();
    }
}