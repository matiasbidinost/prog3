package tp2ArbolesBinarios;

import java.util.ArrayList;
import java.util.List;

public class Tree {

	private TreeNode root;

	public Tree() {
		this.root = null;
	}

	public void add(Integer value) {
		if (this.root == null)
			this.root = new TreeNode(value);
		else
			this.add(this.root, value);
	}
	//get elemento en un determinado nivel
	public List<Integer> getElemAtLevel(int lvl){
		return getElemAtLevel(root, 0, lvl);
	}
	private List<Integer> getElemAtLevel(TreeNode root2, int actualLvl, int lvl) {
		List<Integer> list = new ArrayList<>();
		
		if(root2==null) {
			return list;	
		}
		if(actualLvl == lvl) {
			//agregar el valor del lvl donde estas
		list.add(root2.getValue());
		}else {
			//recorro para buscar el lvl de coincidencia y quedarme con eso
		list.addAll(getElemAtLevel(root2.getLeft(),actualLvl + 1, lvl));
		list.addAll(getElemAtLevel(root2.getRight(),actualLvl + 1, lvl));
		}
		return list;
	}
	//getMaxElem
	//solo tengo q buscar el maximo valor del nodo derecho XD
	public int getMaxElem() {
		return getMaxElem(root);
	}
	private int getMaxElem(TreeNode root) {
		if(root == null)
			return 0;
		if(root.getRight() != null) {
		return getMaxElem(root.getRight());	
		}else{
		return root.getValue();
		}
	}
	//get frontera
	public List<Integer> getFrontera(){	
		return getFrontera(root);
	}
	private List<Integer> getFrontera(TreeNode root){
		List<Integer> list = new ArrayList<>();
		
		if(root == null)
			return list;
		//si ambos costados son null lo agrego
		if(root.getLeft()==null && root.getRight()==null) {
			list.add(root.getValue());
		return list;
		}
		
		if(root.getLeft()!= null) {
			//necesito agregarlos a la lista para hacer el llamado recursivo, aunque lo q se agregue sea solo el caso donde sean null
			list.addAll(getFrontera(root.getLeft()));
			
		}
		if(root.getRight()!= null){
			list.addAll(getFrontera(root.getRight()));
			
		}
		return list;
	}
	
	//imprimir la rama mas larga
	
	public List<Integer> getLongestBranch() {
		 return getLongestBranch(root);
		
	}
	private List<Integer> getLongestBranch(TreeNode root) {
		List<Integer> list = new ArrayList<>();
	
		if(root==null)
			return list;
		
		//tengo q ir agregando tanto a left como right los valores para comparar
		List<Integer> left = getLongestBranch(root.getLeft());
		List<Integer> right = getLongestBranch(root.getRight());
		
		if(left.size()>right.size()) {
			//agrego todo left si es mas grande q el tamaño de right
			list.addAll(left);
		}else {
			list.addAll(right);
		}
		list.add(0, root.getValue());
		
		return list;	
	}

	//IMPRIMIRRRRRRRRRR
	public void printPreOrder() {
		printPreOrder(root);
	}
	public void printPreOrder(TreeNode root) {
	     if(root == null) {
	    	 return;
	     }
	     System.out.print(root.getValue() + "-");
	     printPreOrder(root.getLeft());
	     printPreOrder(root.getRight()); 
	}
	public void printPostOrder() {
		printPostOrder(root);
	}
	public void printPostOrder(TreeNode root) {
		if(root == null)
			return;
		printPostOrder(root.getLeft());
		printPostOrder(root.getRight());
		System.out.print(root.getValue()+ "-");
	}
	public void printInOrder() {
		printInOrder(root);
	}
	public void printInOrder(TreeNode root) {
		if(root == null)
			return;
		printInOrder(root.getLeft());
		System.out.print(root.getValue()+ "-");
		printInOrder(root.getRight());
	}
	//AGREGAR UN NODO
	private void add(TreeNode actual, Integer value) {
		if (actual.getValue() > value) {
			if (actual.getLeft() == null) {
				TreeNode temp = new TreeNode(value);
				actual.setLeft(temp);
			} else {
				add(actual.getLeft(), value);
			}
		} else if (actual.getValue() < value) {
			if (actual.getRight() == null) {
				TreeNode temp = new TreeNode(value);
				actual.setRight(temp);
			} else {
				add(actual.getRight(), value);
			}
		}
	}

	// imprimir la raiz del arbol
	public int getRoot() {
		if (this.root != null) {
			return this.root.getValue();
		}
		return 0;
	}

	// imprimir si existe ese elemento en el arbol AHORA ES RECURSIVO (sonido muy
	// explosivo y fantastico y poderozzzzzzo)
	public boolean hasElem(int elem) {
		return hasElemRec(root, elem);
	}

	private boolean hasElemRec(TreeNode node, int elem) {
		// modificado para hacerlo recursivo
		// tener en cuenta: node.getValue no se le puede preguntar si es null porq va a
		// dar error de Pointer
		if (node == null) {
			return false;
		}
		if (elem == node.getValue()) {
			return true;
		} else if (elem < node.getValue()) {
			return hasElemRec(node.getLeft(), elem);
		} else {
			return hasElemRec(node.getRight(), elem);
		}
	}

	// retornar true si el arbol esta vacio, si root es null significa q no hay raiz
	// osea q es null
	public boolean isEmpty() {
		return root == null;
	}
	//retornar la altura
	public int getHeight() {
		if(root == null)
			return 0;
		return getHeight(this.root, 1);
	}

	private int getHeight(TreeNode root, int cont) {
		if (root == null) //si llegamos a un null restamos uno para q no lo sume
			return cont - 1;
		int alturaIzq = getHeight(root.getLeft(), cont + 1); //llamado recursivo, si baja por la derecha suma y vuelve a hacerlo
		int alturaDer = getHeight(root.getRight(), cont + 1);//sino suma este y luego los compara
		//el que lo tiene mas grande es la altura del arbol
		if (alturaIzq > alturaDer)
			return alturaIzq;
		else
			return alturaDer;
	}

	public boolean delete(int nodoEliminable) {
		if (this.root == null) {
			return false;
		}
		return deleteNodo(null, this.root, nodoEliminable);
	}

	private boolean deleteNodo(TreeNode parent, TreeNode node, int elem) {
		if (node == null) //si el nodo es nulo es q no hay nada q eliminar
			return false;
		//tengo q buscar primero la coincidencia, asiq hago un recorrido normal
		if (elem < node.getValue()) {
			return deleteNodo(node, node.getLeft(), elem);
		} else if (elem > node.getValue()) {
			return deleteNodo(node, node.getRight(), elem);
		} else {
			//aca es donde empieza el quilombo dios mio, porq quise hacerlo recursivo?
			//primer caso, hoja sola, sin hijos
			//si der=null e izq=null entonces tengo q pararme en el padre y desvincular el hijo
			//hoja
			if (node.getLeft() == null && node.getRight() == null) {
				
				if (parent.getLeft() == node)
					//si el nodo a borrar es el 16, me paro primero en su padre (el 15) y le seteo el valor del hijo en null
					parent.setLeft(null);
				else
					//de lo contrario seteo el valor de la derecha en null
					parent.setRight(null);
			}
			//considerando q hay un hijo tengo q mover ese hijo al lugar q ahora le corresponda
			//un hijo
			else if (node.getLeft() == null) {
				//
				if (parent.getLeft() == node)
					parent.setLeft(node.getRight());
				else
					parent.setRight(node.getRight());
			} else if (node.getRight() == null) {
				if (parent.getLeft() == node)
					parent.setLeft(node.getLeft());
				else
					parent.setRight(node.getLeft());
			}
			//dos hijos
			else {
				TreeNode actual = node.getRight();
				while (actual.getLeft() != null) {
				    actual = actual.getLeft();
				}
				node.setValue(actual.getValue());
				return deleteNodo(node, node.getRight(), actual.getValue());
			}

			return true;
		}
	}

	// IMPRIMIR ARBOL CHATGPT
	public void printTree() {
		if (root == null) {
			System.out.println("El árbol está vacío");
			return;
		}

		List<List<String>> lines = new ArrayList<>();
		List<TreeNode> level = new ArrayList<>();
		List<TreeNode> next = new ArrayList<>();

		level.add(root);
		int nn = 1;
		int widest = 0;

		while (nn != 0) {
			List<String> line = new ArrayList<>();
			nn = 0;

			for (TreeNode n : level) {
				if (n == null) {
					line.add(null);
					next.add(null);
					next.add(null);
				} else {
					String aa = n.getValue().toString();
					line.add(aa);
					if (aa.length() > widest)
						widest = aa.length();

					next.add(n.getLeft());
					next.add(n.getRight());

					if (n.getLeft() != null)
						nn++;
					if (n.getRight() != null)
						nn++;
				}
			}

			if (widest % 2 == 1)
				widest++;

			lines.add(line);
			List<TreeNode> tmp = level;
			level = next;
			next = tmp;
			next.clear();
		}

		int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
		for (int i = 0; i < lines.size(); i++) {
			List<String> line = lines.get(i);
			int hpw = (int) Math.floor(perpiece / 2f) - 1;

			if (i > 0) {
				for (int j = 0; j < line.size(); j++) {
					// split node
					char c = ' ';
					if (j % 2 == 1) {
						if (line.get(j - 1) != null) {
							c = (line.get(j) != null) ? '┴' : '┘';
						} else {
							if (j < line.size() && line.get(j) != null)
								c = '└';
						}
					}
					System.out.print(c);

					// lines and spaces
					if (line.get(j) == null) {
						for (int k = 0; k < perpiece - 1; k++) {
							System.out.print(" ");
						}
					} else {
						for (int k = 0; k < hpw; k++) {
							System.out.print(j % 2 == 0 ? " " : "─");
						}
						System.out.print(j % 2 == 0 ? "┌" : "┐");
						for (int k = 0; k < hpw; k++) {
							System.out.print(j % 2 == 0 ? "─" : " ");
						}
					}
				}
				System.out.println();
			}

			// print line of numbers
			for (String f : line) {
				if (f == null)
					f = "";
				int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
				int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

				// a number
				for (int k = 0; k < gap1; k++) {
					System.out.print(" ");
				}
				System.out.print(f);
				for (int k = 0; k < gap2; k++) {
					System.out.print(" ");
				}
			}
			System.out.println();

			perpiece /= 2;
		}
	}

}