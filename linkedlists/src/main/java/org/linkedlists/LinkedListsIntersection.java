package org.linkedlists;

public class LinkedListsIntersection {
    public static void main(String[] args) {

        LinkedListNode head1 = new LinkedListNode(1);
        LinkedListNode node1 = new LinkedListNode(3);
        LinkedListNode node2 = new LinkedListNode(5);
        LinkedListNode node3 = new LinkedListNode(6);
        head1.next = node1;
        node1.next = node2;
        node2.next = node3;

        LinkedListNode head2 = new LinkedListNode(4);
        head2.next = head1;
        LinkedListNode intersectionNode = findIntersectionBetter(head1, head2);
        if(intersectionNode == null){
            System.out.println("No intersection found");
        } else {
            System.out.println("Intersection Node data: " + intersectionNode.data);
        }
    }

    /*
         Approach 1: find length difference between the two lists
                     move h1 length difference times
                     move h1 and h2 till you find the common node
     */
    private static LinkedListNode findIntersection(LinkedListNode head1, LinkedListNode head2) {

        int length1 = 0;
        LinkedListNode temp1 = head1;
        while(temp1 != null){
            temp1 = temp1.next;
            length1++;
        }
        int length2 = 0;
        LinkedListNode temp2 = head2;
        while(temp2 != null){
            temp2 = temp2.next;
            length2++;
        }
        if(length1 > length2){
            for(int i = 1; i <= length1 - length2; i++){
                head1 = head1.next;
            }
        } else if(length2 > length1){
            for(int i = 1; i <= length2 - length1; i++){
                head2 = head2.next;
            }
        }
        while(head1 != null && head2!= null && head1 != head2){
            head1 = head1.next;
            head2 = head2.next;
        }
        if(head1 == null || head2 == null){
            return null; // no intersection
        } else {
            return head1;
        }
    }

    /*
         Approach 2: For two intersecting lists:
                     List1: a1 -> a2 -> a3 -> c1 -> c2 -> c3
                     List2: b1 -> b2 -> c1 -> c2 -> c3
                     h1: a1 -> a2 -> a3 -> c1 -> c2 -> c3 -> b1 -> b2 -> c1
                     h2: b1 -> b2 -> c1 -> c2 -> c3 -> a1 -> a2 -> a3 -> c1

                     For two distinct lists:
                     List1: a1 -> a2 -> a3
                     List2: b1 -> b2
                     h1: a1 -> a2 -> a3 -> b1 -> b2 -> null
                     h2: b1 -> b2 -> a1 -> a2 -> a3 -> null
     */
    public static LinkedListNode findIntersectionBetter(LinkedListNode head1, LinkedListNode head2){

        LinkedListNode temp1 = head1;
        LinkedListNode temp2 = head2;
        while(temp1 != null || temp2 != null){
            if(temp1 == temp2) return temp1;
            if(temp1 == null){
                temp1 = head2;
                temp2 = temp2.next;
            } else if(temp2 == null) {
                temp2 = head1;
                temp1 = temp1.next;
            } else {
                temp1 = temp1.next;
                temp2 = temp2.next;
            }
        }
        return null;
    }
}