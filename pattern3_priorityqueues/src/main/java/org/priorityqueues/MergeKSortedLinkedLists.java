package org.heap;

import java.util.Comparator;
import java.util.PriorityQueue;


class ListNode {
  int val;
  ListNode next;
  ListNode() {}
  ListNode(int val) { this.val = val; }
  ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class MergeKSortedLinkedLists {

    public static void main(String[] args) {

        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(4);
        head1.next.next = new ListNode(5);

        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(3);
        head2.next.next = new ListNode(4);

        ListNode head3 = new ListNode(2);
        head3.next = new ListNode(6);

        ListNode mergedHead = mergeKLists(new ListNode[]{head1, head2, head3});
        while(mergedHead != null){
            System.out.print(mergedHead.val + " ");
            mergedHead = mergedHead.next;
        }
    }

    public static ListNode mergeKLists(ListNode[] lists) {

        int k = lists.length;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(listNode -> listNode.val));

        for(ListNode listNode : lists){
            if(listNode != null){
                pq.offer(listNode);
            }
        }

        ListNode mergedListHead = null;
        ListNode mergedListTail = null;

        while(!pq.isEmpty()){
            ListNode listNode = pq.remove();
            if(mergedListHead == null){
                mergedListHead = listNode;
            } else {
                mergedListTail.next = listNode;
            }
            mergedListTail = listNode;
            if(listNode.next != null){
                pq.offer(listNode.next);
            }
        }

        return mergedListHead;
    }
}
