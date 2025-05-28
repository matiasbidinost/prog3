package Tema_6.EJ1;

import java.util.LinkedHashMap;
import java.util.Map;

public class Solucion {
	private Map<Integer, Integer> monedasUsadas = new LinkedHashMap<>();

	public void agregarMoneda(int valor, int cantidad) {
		monedasUsadas.put(valor, cantidad);
	}
    public void mostrar() {
        System.out.println("Cantidad mínima de billetes:");
        for (Map.Entry<Integer, Integer> entrada : monedasUsadas.entrySet()) {
            System.out.println(entrada.getKey() + "$ x " + entrada.getValue());
        }
    }
}
