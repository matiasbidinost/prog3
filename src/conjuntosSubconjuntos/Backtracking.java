package conjuntosSubconjuntos;

import java.util.*;

public class Backtracking {
    private List<Integer> conjunto;

    public Backtracking(List<Integer> conjunto) {
        this.conjunto = conjunto;
    }

    public Solucion solucionar2(Integer suma) {
        Solucion solucion = new Solucion();
        Estado estado = new Estado();

        solucionar2(estado, solucion, suma);

        return solucion;
    }
    
    private void solucionar2(Estado estado, Solucion solucion, Integer sumaBuscada) {
        if (estado.getSumaParcial().equals(sumaBuscada)) {
            // 🔥 En vez de agregar al set directamente, uso el método agregarSolucion()
            solucion.agregarSolucion(estado.getCaminoParcial());

        } else {
            for (int i = 0; i < conjunto.size(); i++) {
                Integer numero = conjunto.get(i);

                // 🔥 Si el número no fue visitado
                if (!estado.getVisitados().contains(numero)) {
                    estado.getVisitados().add(numero);
                    estado.getCaminoParcial().add(numero);
                    estado.aculumarSuma(numero);

                    // 🔥 Llamada recursiva
                    solucionar2(estado, solucion, sumaBuscada);

                    // 🔥 Deshago el paso (backtracking)
                    estado.aculumarSuma(-numero);
                    estado.getCaminoParcial().remove(estado.getCaminoParcial().size() - 1);
                    estado.getVisitados().remove(numero);
                }
            }
        }
    }
}