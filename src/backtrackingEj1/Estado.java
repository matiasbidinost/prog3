package backtrackingEj1;

import java.util.HashSet;
import java.util.Set;

public class Estado {
    private int posActual; // Sala actual
    private Set<Integer> caminoActual; // Conjunto de salas visitadas

    // Constructor
    public Estado(int posActual) {
        this.posActual = posActual;
        this.caminoActual = new HashSet<>();
        this.caminoActual.add(posActual); // Empiezo desde la posición inicial
    }

    // Clonar el estado (útil para pasar copias al backtracking)
    public Estado clonar() {
        Estado copia = new Estado(this.posActual);
        copia.caminoActual = new HashSet<>(this.caminoActual);
        return copia;
    }

    // Avanzar a una nueva sala
    public void avanzar(int nuevaPos) {
        this.posActual = nuevaPos;
        this.caminoActual.add(nuevaPos);
    }

    // Retroceder (quita la última sala del conjunto)
    public void retroceder(int anterior) {
        this.caminoActual.remove(this.posActual);
        this.posActual = anterior;
    }

    // Getters
    public int getPosActual() {
        return posActual;
    }

    public Set<Integer> getCaminoActual() {
        return caminoActual;
    }

    public int getTamanioCamino() {
        return caminoActual.size();
    }

    // Verificar si ya pasé por una sala (para evitar ciclos)
    public boolean yaVisitado(int sala) {
        return caminoActual.contains(sala);
    }
}