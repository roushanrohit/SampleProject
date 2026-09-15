package org.linkedlists;

public class WinnerOfCircularGame {

    public static void main(String[] args) {

        int n = 5;
        int k = 2;
        System.out.println(winnerOfCircularGame(n, k));
    }

    private static int winnerOfCircularGame(int n, int k) {

        // create the circular linked list
        LinkedListNode head = new LinkedListNode(1);
        LinkedListNode current = head;
        for(int i = 2; i <= n; i++){
            current.next =  new LinkedListNode(i);
            current = current.next;
        }
        current.next = head;

        LinkedListNode prev = current;
        current = head;
        while (prev != current){
            // move k - 1 times
            for(int i = 1; i < k; i++){
                prev = current;
                current = current.next;
            }
            // remove the loser -- current
            prev.next = current.next;
            // move current ahead
            current = current.next;
        }

        return current.data;
    }
}
