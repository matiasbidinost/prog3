package conjuntosSubconjuntos;
import java.util.*;

public class Solucion {
    private Set<List<Integer>> resultado;

    public Solucion() {
        this.resultado = new HashSet<>();
    }

    public Set<List<Integer>> getResultado() {
        return resultado;
    }

    public void setResultado(Set<List<Integer>> resultado) {
        this.resultado = resultado;
    }

    // Agrega una nueva solución, ordenándola para evitar duplicados con diferente orden
    public void agregarSolucion(List<Integer> lista) {
        List<Integer> copiaOrdenada = new ArrayList<>(lista);
        Collections.sort(copiaOrdenada);
        this.resultado.add(copiaOrdenada);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Resultado:\n");
        for (List<Integer> lista : resultado) {
            sb.append(lista).append("\n");
        }
        return sb.toString();
    }
}