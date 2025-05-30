package ej1;

public class Main {
    public static void main(String[] args) {
        Tree arbol = new Tree();

        // Cargamos los valores del árbol como en la imagen
        int[] valores = {10, 8, 13, 4, 9, 2, 12, 20, 11};

        for (int valor : valores) {
            arbol.add(valor);
        }

        // Probamos con K = 3
        int k = 3;
        System.out.println("Probando con K = " + k);
        System.out.println("Nodos con diferencia mayor a K:");
        System.out.println(arbol.cumple(k));

        // Probamos con K = 5
        k = 5;
        System.out.println("Probando con K = " + k);
        System.out.println("Nodos con diferencia mayor a K:");
        System.out.println(arbol.cumple(k));
    }
}