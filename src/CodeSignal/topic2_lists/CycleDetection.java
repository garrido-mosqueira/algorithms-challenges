package CodeSignal.topic2_lists;

public class CycleDetection {

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

    static boolean hasCycle(SinglyLinkedListNode head) {

        if (head == null) return false;

        SinglyLinkedListNode fast = head;
        SinglyLinkedListNode slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SinglyLinkedList linkedList = new SinglyLinkedList();
        int indexBack = 1;

        int[] numbersList = new int[]{1, 2, 3};
        for (int i = 0; i < numbersList.length; i++) {
            linkedList.insertNode(numbersList[i]);
        }

        SinglyLinkedListNode extra = new SinglyLinkedListNode(-1);
        SinglyLinkedListNode temp = linkedList.head;

        for (int i = 0; i < numbersList.length; i++) {
            if (i == indexBack) {
                extra = temp;
            }

            if (i != numbersList.length - 1) {
                temp = temp.next;
            }
        }

        temp.next = extra;

        boolean result = hasCycle(linkedList.head);

        assert result : "Wrong";
    }

}

