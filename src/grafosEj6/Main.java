package grafosEj6;

import java.util.Iterator;
import java.util.List;

public class Main{
	public static void main(String[] args) {
        GrafoNoDirigido<String> grafo = new GrafoNoDirigido<>();

        // Agregamos vértices
        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarVertice(3);
        grafo.agregarVertice(4);

        // Agregamos arcos (calles)
        grafo.agregarArco(1, 2, "calle A");
        grafo.agregarArco(2, 3, "calle B");
        grafo.agregarArco(3, 4, "calle C");
        grafo.agregarArco(4, 1, "calle D");

        // Mostramos los vértices
        System.out.println("Vértices:");
        Iterator<Integer> itVertices = grafo.obtenerVertices();
        while (itVertices.hasNext()) {
            System.out.println(" - " + itVertices.next());
        }

        // Mostramos los arcos
        System.out.println("\nCalles:");
        Iterator<Calle<String>> itCalles = grafo.obtenerArcos();
        while (itCalles.hasNext()) {
            Calle<String> calle = itCalles.next();
            System.out.println(" - " + calle.getVerticeOrigen() + " <-> " + calle.getVerticeDestino() + " (" + calle.getEtiqueta() + ")");
        }

        // Consultamos adyacentes
        System.out.println("\nAdyacentes al vértice 2:");
        Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(2);
        while (adyacentes.hasNext()) {
            System.out.println(" - " + adyacentes.next());
        }
        //la ruta mas corta
        Ciudad<String> ciudad = new Ciudad<String>(grafo);
        List<Integer> ruta = ciudad.rutaMasCorta(1, 3);
        System.out.println("\nRuta más corta de 1 a 3:");
        if (ruta.isEmpty()) {
            System.out.println("No hay camino.");
        } else {
            for (Integer nodo : ruta) {
                System.out.print(nodo + " ");
            }
            System.out.println(); // Salto de línea al final
        }
        
    }

}