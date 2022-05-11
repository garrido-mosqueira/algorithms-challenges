package CodeSignal.topic2_lists;

public class InsertNodeAtPosition {

    static class SinglyLinkedListNode {
        public int data;
        public SinglyLinkedListNode next;

        public SinglyLinkedListNode(int nodeData) {
            this.data = nodeData;
            this.next = null;
        }
    }

    static class SinglyLinkedList {
        public SinglyLinkedListNode head;
        public SinglyLinkedListNode tail;

        public SinglyLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void insertNode(int nodeData) {
            SinglyLinkedListNode node = new SinglyLinkedListNode(nodeData);

            if (this.head == null) {
                this.head = node;
            } else {
                this.tail.next = node;
            }

            this.tail = node;
        }
    }

    public static void printSinglyLinkedList(SinglyLinkedListNode node) {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

    public static String getStringLinkedList(SinglyLinkedListNode node) {
        String aux = "";
        while (node != null) {
            aux += node.data + " ";
            node = node.next;
        }
        return aux.trim();
    }

    static SinglyLinkedListNode insertNodeAtPosition(SinglyLinkedListNode head, int data, int position) {
        SinglyLinkedListNode aux = head;

        for (int i = 0; i < position - 1; i++) {
            aux = aux.next;
        }

        SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);

        newNode.next = aux.next;
        aux.next = newNode;

        return head;
    }

    public static void main(String[] args) {
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();

        int[] numbersList = new int[]{16, 13, 7};
        for (int i = 0; i < numbersList.length; i++) {
            singlyLinkedList.insertNode(numbersList[i]);
        }

        SinglyLinkedListNode head = insertNodeAtPosition(singlyLinkedList.head, 1, 2);

        printSinglyLinkedList(head);
        String stringLinkedList = getStringLinkedList(head);

        assert stringLinkedList.equals("16 13 1 7") : "Wrong";
    }
}