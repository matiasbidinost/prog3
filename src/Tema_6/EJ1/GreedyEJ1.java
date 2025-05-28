package Tema_6.EJ1;

import java.util.ArrayList;
import java.util.Collections;


public class GreedyEJ1 {

	private ArrayList<Integer> monedas;

	public GreedyEJ1(ArrayList<Integer> monedas) {
		this.monedas = new ArrayList<>(monedas);
		Collections.sort(this.monedas); // Orden ascendente
	}

	public Solucion cambio(int monto) {
		//creo la clase solucion
		Solucion solucion = new Solucion();
		//realizo el llamado recursivo restando el tamaño de monedas. size para achicarlo, paso el mononto y la clas solucion
		cambioRecursivo(monedas.size() - 1, monto, solucion);
		return solucion;
	}

	private void cambioRecursivo(int index, int monto, Solucion solucion) {
		//si monton llego a 0 y el indice ya se resto hasta llegar por debajo de 0 entonces retorno
		if (monto == 0 || index < 0)
			return;
		
		//moneda se vuelve monedas, get index q es el size(va a iterar desde el final)
		int moneda = monedas.get(index);
		//si la condicion de moneda es menor al monto
		if (moneda <= monto) {
			int cantidad = monto / moneda; //divido la moneda por el monto para ver cuantas veces entra esa moneda en ese momento
			//agrego la moneda a la posible solucion a retornar
			solucion.agregarMoneda(moneda, cantidad);
			//resto la moneda del monto q voy
			monto -= cantidad * moneda;
		}
		//llamado recursivo con el monto restado y el tamaño menor
		cambioRecursivo(index - 1, monto, solucion);
	}

}