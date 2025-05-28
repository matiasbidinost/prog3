package parcial2021;

import miGrafo.GrafoDirigido;
import miGrafo.Grafo;

public class Main {
    public static void main(String[] args) {
        Grafo<String> graf = new GrafoDirigido<>();

        // Agregar vértices 1..9
        for (int i = 1; i <= 9; i++) {
            graf.agregarVertice(i);
        }

        // Arcos según la imagen
        graf.agregarArco(1, 2, "1→2");
        graf.agregarArco(2, 4, "2→4");
        graf.agregarArco(4, 7, "4→7");
        graf.agregarArco(7, 9, "7→9");
        graf.agregarArco(9, 6, "9→6");
        graf.agregarArco(6, 3, "6→3");
        graf.agregarArco(3, 2, "3→2");
        graf.agregarArco(2, 1, "2→1");
        graf.agregarArco(4, 2, "4→2");
        graf.agregarArco(4, 8, "4→8");
        graf.agregarArco(8, 7, "8→7");
        graf.agregarArco(6, 5, "6→5");
        graf.agregarArco(5, 1, "5→1");

        // Hacemos DFS desde el vértice 4
        Dfs<String> dfs = new Dfs<>(graf, 4);
        Solucion sol = dfs.buscar();

        System.out.println("Arcos TREE: " + sol.getArcosTree());
        System.out.println("Arcos BACK: " + sol.getBack());
    }
}