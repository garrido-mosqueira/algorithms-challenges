package topic6_trees;

public class HeightOfBinaryTree {

    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public static Node insert(Node node, int data) {
        if (node == null) {
            return new Node(data);
        } else {
            if (data <= node.data) {
                node.left = insert(node.left, data);
            } else {
                node.right = insert(node.right, data);
            }
        }
        return node;
    }

    public static int height(Node root) {
        if (root == null) return -1;

        int left = height(root.left) + 1;
        int right = height(root.right) + 1;

        return left > right ? left : right;
    }

    public static void main(String[] args) {

        Node root = insert(null, 3);
        insert(root, 5);
        insert(root, 2);
        insert(root, 1);
        insert(root, 4);
        insert(root, 6);
        insert(root, 7);

        int height = height(root);
        System.out.println(height);
        assert height == 3;
    }
}
