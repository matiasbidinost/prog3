package ej1;

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

    private void add(TreeNode root2, Integer value) {
        if (root2.getValue() > value) {
            if (root2.getLeft() == null) {
                root2.setLeft(new TreeNode(value));
            } else {
                add(root2.getLeft(), value);
            }
        } else {
            if (root2.getRight() == null) {
                root2.setRight(new TreeNode(value));
            } else {
                add(root2.getRight(), value);
            }
        }
    }
    public boolean cumple(int k) {
    	return cumpleConK(root,k);
    }

    // Método para verificar la propiedad con K
    private boolean cumpleConK(TreeNode root, int k) {
        if (root == null)
            return true;
        if (root.getLeft() == null && root.getRight() == null)
            return true;

        if (root.getLeft() != null) {
            int diff;
            if (root.getValue() > root.getLeft().getValue()) {
                diff = root.getValue() - root.getLeft().getValue();
            } else {
                diff = root.getLeft().getValue() - root.getValue();
            }
            if (diff > k) {
                return false;
            }
            if (!cumpleConK(root.getLeft(), k)) return false;
        }

        if (root.getRight() != null) {
            int diff;
            if (root.getValue() > root.getRight().getValue()) {
                diff = root.getValue() - root.getRight().getValue();
            } else {
                diff = root.getRight().getValue() - root.getValue();
            }
            if (diff > k) {
                return false;
            }
            if (!cumpleConK(root.getRight(), k)) return false;
        }

        return true;
    }

    // Getter para la raíz (así lo usás en el main)
    public TreeNode getRoot() {
        return this.root;
    }
}
