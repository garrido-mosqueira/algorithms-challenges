package topic6_trees;

public class Insertion {

    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        }
        insertNode(root, data);
        return root;
    }

    private static void insertNode(final Node aux, final int data) {
        if (data > aux.data) {
            if (aux.right != null) {
                insertNode(aux.right, data);
            } else {
                aux.right = new Node(data);
            }
        } else {
            if (aux.left != null) {
                insertNode(aux.left, data);
            } else {
                aux.left = new Node(data);
            }
        }
    }

    public static void main(String[] args) {
//        Node root = new Node(4);
        Node root = null;
        root = insert(root, 2);
        insert(root, 2);
        insert(root, 7);
        insert(root, 1);
        insert(root, 3);

        System.out.println("");
    }

}
