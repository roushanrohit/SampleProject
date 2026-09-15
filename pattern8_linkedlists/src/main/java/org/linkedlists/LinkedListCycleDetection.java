package org.linkedlists;

public class LinkedListCycleDetection {

    public static void main(String[] args) {

        LinkedListNode head = new LinkedListNode(1);
        LinkedListNode node1 = new LinkedListNode(3);
        LinkedListNode node2 = new LinkedListNode(5);
        LinkedListNode node3 = new LinkedListNode(6);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = head;

        System.out.println("has cycle: " + hasCycle(head));
    }

    public static boolean hasCycle(LinkedListNode head) {
        if(head == null) return false;

        LinkedListNode slow = head;
        LinkedListNode fast = head.next;

        while(slow != fast){
            if(fast == null || fast.next == null){
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        return true;
    }
}
