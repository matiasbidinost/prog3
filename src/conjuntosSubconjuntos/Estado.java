package conjuntosSubconjuntos;

import java.util.*;

public class Estado {
    Set<Integer> visitados;
    List<Integer> caminoParcial;
    Integer sumaParcial;

    public Estado() {
        this.caminoParcial = new ArrayList<>();
        this.visitados = new HashSet<>();
        this.sumaParcial = 0;
    }

    public void aculumarSuma(Integer elem){
        this.sumaParcial+= elem;
    }

    public Integer getSumaParcial() {
        return sumaParcial;
    }

    public void setSumaParcial(Integer sumaParcial) {
        this.sumaParcial = sumaParcial;
    }

    public List<Integer> getCaminoParcial() {
        return caminoParcial;
    }

    public void setCaminoParcial(List<Integer> caminoParcial) {
        this.caminoParcial = caminoParcial;
    }

    public Set<Integer> getVisitados() {
        return visitados;
    }

    public void setVisitados(Set<Integer> visitados) {
        this.visitados = visitados;
    }
}