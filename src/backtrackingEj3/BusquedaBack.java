package backtrackingEj3;

import java.util.ArrayList;
import java.util.List;

public class BusquedaBack {
	/*
	 * Suma desubconjuntos. Dados n números positivos distintos, se desea encontrar
	 * todas las combinaciones de esos números tal que la suma sea igual a M.
	 */
    private List<List<Integer>> resultado;

    public BusquedaBack() {
        this.resultado = new ArrayList<>();
    }
	public List<List<Integer>> subconjuntos(List<Integer>numeros, Integer x){
		resultado.clear();
		Estado estado =  new Estado(numeros);
		backtrack(estado,x); //siendo x el objetivo y estado el estado inicial con los numeros
		return resultado; 
				
	}
	private void backtrack(Estado e, Integer x) {
		//primero contemplo el primer caso como solucion... osea cuando estado se quede sin arr q recorrer
		//primer caso, recorri todo, me quede sin solucion
		if(!e.numerosPorProbar()) {
			//segundo caso, recorri todo, hay coincidencia?
			if(e.getSuma()==x) {//la suma da igual al objetivo?
				//agrego al resultado el arr resultante
				resultado.add(e.getResultadoArr());
			}
		}else {
			//incluir el numero actual
			//backtracking
			int i = e.getIndiceActual();
			//hace la iteracion, suma el valor del indice y suma al nuevo indice en 1
			e.iteracion();
			backtrack(e,x);
			e.volverMov();
			
			//no incluir el numero actual
			
			e.resetIndice(i);
			e.saltarNumActual();
			backtrack(e,x);
			e.resetIndice(i);
		}
		
	}
}
