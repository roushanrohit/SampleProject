package org.linkedlists;

public class RemoveNthNodeFromEnd {

    public static void main(String[] args) {

        LinkedListNode head = new LinkedListNode(1);
        LinkedListNode node1 = new LinkedListNode(3);
        LinkedListNode node2 = new LinkedListNode(5);
        LinkedListNode node3 = new LinkedListNode(6);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;

        printLinkedList(head);
        LinkedListNode newHead = removeNthFromEnd(head, 4);
        printLinkedList(newHead);
    }

    public static LinkedListNode removeNthFromEnd(LinkedListNode head, int n) {

        LinkedListNode dummy = new LinkedListNode(0);
        dummy.next = head;
        LinkedListNode slow = dummy, fast = dummy;
        for(int i = 0; i < n; i++){
            fast = fast.next;
        }
        while(fast.next != null){
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    private static void printLinkedList(LinkedListNode head) {
        while(head != null){
            System.out.print(head.data + "->");
            head = head.next;
        }
        System.out.print("null");
        System.out.println();
    }
}
