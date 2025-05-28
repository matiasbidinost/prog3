package backtrackingEj2;

public class Casilla {
    private int valor;
    private boolean norte, sur, este, oeste;

    public Casilla(int valor, boolean norte, boolean sur, boolean este, boolean oeste) {
        this.valor = valor;
        this.norte = norte;
        this.sur = sur;
        this.este = este;
        this.oeste = oeste;
    }

    public int getValor() { return valor; }
    public boolean isNorte() { return norte; }
    public boolean isSur() { return sur; }
    public boolean isEste() { return este; }
    public boolean isOeste() { return oeste; }
}
