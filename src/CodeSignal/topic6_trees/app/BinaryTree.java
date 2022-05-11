package CodeSignal.topic6_trees.app;

public class BinaryTree<T extends Comparable<T>> implements Tree<T> {

    private Node<T> root;

    @Override
    public Node<T> getRoot() {
        return root;
    }

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
        if (root == null) return false;
        int searchValue = searchValue(root, data);
        return searchValue > 0;
    }

    private int searchValue(final Node<T> root, final T data) {
        if (root == null) return 0;

        if (root.getData().compareTo(data) == 0) return 1;

        return searchValue(root.getLeft(), data) + searchValue(root.getRight(), data);
    }

    @Override
    public T getMax() {
        if (root == null) return null;
        return getMaxValue(root);
    }

    private T getMaxValue(final Node<T> right) {
        if (right.getRight() == null) {
            return right.getData();
        } else {
            return getMaxValue(right.getRight());
        }
    }

    @Override
    public T getMin() {
        if (root == null) return null;
        return getMinValue(root);
    }

    private T getMinValue(final Node<T> left) {
        if (left.getLeft() == null) {
            return left.getData();
        } else {
            return getMinValue(left.getLeft());
        }
    }
}
