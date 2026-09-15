package org.linkedlists;

public class ReorderList {

    public static void main(String[] args) {

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        printLinkedList(node1);
        reorderList(node1);
        System.out.println();
        printLinkedList(node1);
    }

    public static void reorderList(ListNode head) {

        if(head == null || head.next == null) return;

        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode h2 = reverse(slow);
        ListNode h1 = head;
        while(h1 != null && h2 != null){
            ListNode h1Next = h1.next;
            ListNode h2Next = h2.next;
            h1.next = h2;
            h2.next = h1Next;
            h1 = h1Next;
            h2 = h2Next;
        }
    }

    public static ListNode reverse(ListNode head){

        ListNode prev = null;
        while(head != null){
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    private static void printLinkedList(ListNode head) {
        while(head != null){
            System.out.print(head.val + " ");
            head = head.next;
        }
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
