package parcial2021;
import java.util.ArrayList;
import java.util.List;

import miGrafo.*;
/*public class Solucion {
	private List<List<Integer>> arcosTree;
	private List<List<Integer>> back;
	private List<Integer> vertices;
	
	public Solucion() {
		this.arcosTree = new ArrayList<>();
		this.back = new ArrayList<>();
		this.vertices = new ArrayList<>();
	}
	public List<List<Integer>> getArcosTree() {
		return arcosTree;
	}
	public List<List<Integer>> getBack() {
		return back;
	}

	public List<Integer> getVertices() {
		return vertices;
	}
	public void addArcosTree(List<Integer> t) {
		arcosTree.add(t);
	}
	public void addBack(List<Integer> t) {
		back.add(t);
	}
	public void setVertices(List<Integer> vertices) {
		this.vertices = vertices;
	}
	


}*/
public class Solucion {
    private List<List<Integer>> arcosTree;
    private List<List<Integer>> back;

    public Solucion() {
        this.arcosTree = new ArrayList<>();
        this.back      = new ArrayList<>();
    }

    public List<List<Integer>> getArcosTree() {
        return arcosTree;
    }

    public List<List<Integer>> getBack() {
        return back;
    }

    public void addArcosTree(List<Integer> t) {
        arcosTree.add(t);
    }

    public void addBack(List<Integer> t) {
        back.add(t);
    }
}
