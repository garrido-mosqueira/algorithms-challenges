package CoderPro;

/**
 * Session 11
 * https://www.techseries.dev/products/coderpro/categories/1831147/posts/6228782
 */

public class ReverseALinkedList {

    static class Node<T> {
        public T data;
        public Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    static class Solution<T> {
        private Node<T> root;

        public void insert(T data) {
            if (this.root == null) {
                root = new Node<>(data);
            } else {
                insertNode(root, data);
            }
        }

        private void insertNode(final Node<T> node, final T data) {
            if (node.next == null) {
                node.next = new Node<>(data);
            } else {
                insertNode(node.next, data);
            }
        }

        public void print() {
            Node<T> aux = root;
            while (aux.next != null) {
                System.out.print(aux.data + " -> ");
                aux = aux.next;
            }
            System.out.println();
        }

        public void reverse() {
            if (root == null) return;

            Node<T> cur = root;
            Node<T> prev = null;

            while (cur != null) {
                Node<T> next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }
            root = prev;
        }

    }

    public static void main(String[] args) {
        Solution<Integer> solution = new Solution<>();

        solution.insert(1);
        solution.insert(3);
        solution.insert(5);
        solution.insert(7);
        solution.insert(9);

        solution.print();

        solution.reverse();

        solution.print();
    }


}
