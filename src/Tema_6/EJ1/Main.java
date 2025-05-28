package Tema_6.EJ1;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Lista de monedas disponibles
        ArrayList<Integer> monedas = new ArrayList<>();
        monedas.add(100);
        monedas.add(25);
        monedas.add(10);
        monedas.add(5);
        monedas.add(1);

        // Monto a alcanzar
        int monto = 289;

        // Crear instancia del algoritmo greedy
        GreedyEJ1 algoritmo = new GreedyEJ1(monedas);

        // Obtener solución
        Solucion resultado = algoritmo.cambio(monto);

        // Mostrar resultado
        resultado.mostrar();
    }

}
