/*package parcial2021;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import miGrafo.*;

public class Dfs<T> {
	private Grafo<T> grafo;
	private Map<Integer, String> colores;
	private Solucion resultado;
	private int origen;
	
	public Dfs (Grafo<T> grafo, int origen){
		this.origen = origen;
		this.grafo =  grafo;
		this.colores = new HashMap<Integer, String>();
		this.resultado = new Solucion(); 
	}
	public Solucion buscar(){
		colores.clear();
		Iterator<Integer> it = grafo.obtenerVertices();
		
		while(it.hasNext()) {
			Integer next = it.next();
			colores.put(next, "blanco");
		}
		
		it=grafo.obtenerVertices();
		if(colores.containsKey(origen)) {
			busqueda(origen);
		}
		return resultado;
	}
	public void busqueda(Integer actual) {
		colores.put(actual, "amarillo");
		
		Iterator<Integer> it = grafo.obtenerAdyacentes(actual);
		
		while(it.hasNext()) {
			Integer adyacente = it.next();
			if(colores.get(adyacente).equals("amarillo")) {
				List<Integer> t = new LinkedList<Integer>();
				t.add(actual);
				t.add(adyacente);
				
				resultado.addBack(t);
			}
			if(colores.get(adyacente).equals("blanco")) {
				List<Integer> t = new LinkedList<Integer>();
				t.add(actual);
				t.add(adyacente);
				
				resultado.addArcosTree(t);
				busqueda(adyacente);
			}
		}
		colores.put(actual, "negro");
		
	}
}
*/
package parcial2021;

import java.util.*;
import miGrafo.Grafo;

public class Dfs<T> {
    private Grafo<T> grafo;
    private Map<Integer, String> colores;
    private Solucion resultado;
    private int origen;

    public Dfs(Grafo<T> grafo, int origen) {
        this.origen   = origen;
        this.grafo    = grafo;
        this.colores  = new HashMap<>();
        this.resultado = new Solucion();
    }

    public Solucion buscar() {
        colores.clear();
        for (Iterator<Integer> it = grafo.obtenerVertices(); it.hasNext();) {
            colores.put(it.next(), "blanco");
        }
        if (colores.containsKey(origen)) {
            dfsVisitar(origen);
        }
        return resultado;
    }

    private void dfsVisitar(int u) {
        colores.put(u, "amarillo");

        for (Iterator<Integer> it = grafo.obtenerAdyacentes(u); it.hasNext();) {
            int v = it.next();
            String c = colores.get(v);
            if ("amarillo".equals(c)) {
                // arco back: u->v
                resultado.addBack(Arrays.asList(u, v));
            } else if ("blanco".equals(c)) {
                // arco tree: u->v
                resultado.addArcosTree(Arrays.asList(u, v));
                dfsVisitar(v);
            }
            // si es negro, lo ignoramos
        }

        colores.put(u, "negro");
    }
}
