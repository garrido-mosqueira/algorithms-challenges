package topic6_trees.app;

public class BinaryTree<T extends Comparable<T>> implements Tree<T> {

    private Node<T> root;

    @Override
    public void insert(final T data) {
        if (root == null) {
            this.root = new Node<>(data);
        } else {
            insertNode(root, data);
        }
    }

    private void insertNode(Node<T> root, final T data) {
        if (data.compareTo(root.getData()) > 0) {
            if (root.getRight() != null) {
                insertNode(root.getRight(), data);
            } else {
                root.setRight(new Node<>(data));
            }
        } else {
            if (root.getLeft() != null) {
                insertNode(root.getLeft(), data);
            } else {
                root.setLeft(new Node<>(data));
            }
        }
    }

    @Override
    public void traversal() {
        if (root != null) {
            travers(root);
//            inOrderTraversal(root);
        }
    }

    private void inOrderTraversal(final Node<T> node) {
        if (node.getLeft() != null) {
            inOrderTraversal(node.getLeft());
        }

        System.out.print(node + " -> ");

        if (node.getRight() != null) {
            inOrderTraversal(node.getRight());
        }
    }

    private void travers(final Node<T> node) {
        if (node == null) return;

        travers(node.getLeft());

        System.out.print(node.getData() + " -> ");

        travers(node.getRight());

    }

    @Override
    public String toString() {
        return postTravers(root);
    }

    private String postTravers(final Node<T> node) {
        if (node == null) return "";

        String left = postTravers(node.getLeft());
        String right = postTravers(node.getRight());

        return left + " " + node.getData() + " " + right;
    }

    @Override
    public boolean search(final T data) {
        return false;
    }

    @Override
    public T getMax() {
        return null;
    }

    @Override
    public T getMin() {
        return null;
    }
}
