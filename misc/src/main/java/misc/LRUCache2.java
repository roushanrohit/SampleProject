package misc;

import java.util.HashMap;
import java.util.Map;

class Node {
    int key;
    int value;
    Node prev;
    Node next;
    Node(int key, int value){
        this.key = key;
        this.value = value;
    }
}

public class LRUCache2 {

    int capacity = 0;
    Map<Integer, Node> data;
    Node head;
    Node tail;

    public LRUCache2(int capacity) {
        this.capacity = capacity;
        data = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {

        if(!data.containsKey(key)){
            return -1;
        }

        Node node = data.get(key);
        remove(node);
        add(node);
        return node.value;
    }

    public void put(int key, int value){

        if(data.containsKey(key)){
            Node existingNode = data.get(key);
            remove(existingNode);
        }

        Node node = new Node(key, value);
        data.put(key, node);
        add(node);

        if(data.size() > capacity){
            Node nodeToDelete = head.next;
            remove(nodeToDelete);
            data.remove(nodeToDelete.key);
        }
    }

    public void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void add(Node node){
        Node previousEnd = tail.prev;
        previousEnd.next = node;
        node.prev = previousEnd;
        node.next = tail;
        tail.prev = node;
    }

    public static void main(String[] args) {

        LRUCache2 lruCache = new LRUCache2(2);
        lruCache.put(1,1);
        lruCache.put(2,2);
        lruCache.put(3,3);
        lruCache.put(1,1);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(2));
        System.out.println(lruCache.get(3));
    }
}
