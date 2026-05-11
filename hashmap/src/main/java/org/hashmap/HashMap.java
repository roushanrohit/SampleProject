package org.hashmap;

import java.util.ArrayList;
import java.util.List;

class Node<K, V>{
    K key;
    V value;
    Node<K, V> next;
    Node(K key, V value){
        this.key = key;
        this.value = value;
    }
}

public class HashMap<K, V> {

    private int size = 0;
    private int numBuckets = 16;
    private List<Node<K, V>> buckets;

    public HashMap(){
        buckets = new ArrayList<>(numBuckets);
        for(int i = 0; i < numBuckets; i++) buckets.add(null);
    }

    public int size(){ return size; }

    public int getHashCode(K key) throws NoSuchMethodException, HashCodeNotFoundException {
        if(key instanceof Integer) return (int) key;
        else if(key instanceof String){
            double hashCode = 0;
            int placeValue = 1;
            for(int i = ((String) key).length() - 1; i >= 0; i--){
                hashCode += (placeValue++) * ((String) key).charAt(i);
            }
            return (int) hashCode;
        } else {
            if(key.getClass().getMethod("hashCode").getDeclaringClass() != Object.class)
                return (int)key.hashCode();
            else throw new HashCodeNotFoundException();
        }
    }

    private int getBucketIndex(K key) throws HashCodeNotFoundException, NoSuchMethodException {
        int hashCode = getHashCode(key);
        return hashCode % numBuckets;
    }

    public V get(K key) throws HashCodeNotFoundException, NoSuchMethodException, KeyCannotBeNullException {

        if(key == null) throw new KeyCannotBeNullException();
        int bucketIndex = getBucketIndex(key);

        Node<K, V> head = buckets.get(bucketIndex);
        while (head != null){

            // key has to implement the .equals method
            if(head.key.equals(key)) return head.value;
            head = head.next;
        }
        return null;
    }

    // we have to maintain load factor <= 0.7 to get O(1) time complexity for all operations
    public double loadFactor(){
        return ((double) size)/numBuckets;
    }

    public List<K> getKeys(){
        List<K> keys = new ArrayList<>();
        for(int i = 0; i < numBuckets; i++){
            Node<K, V> head = buckets.get(i);
            while (head != null){
                keys.add(head.key);
                head = head.next;
            }
        }
        return keys;
    }

    public void put(K key, V value) throws KeyCannotBeNullException, HashCodeNotFoundException, NoSuchMethodException {

        if(key == null) throw new KeyCannotBeNullException();
        if(loadFactor() >= 0.7) rehash();;
        int bucketIndex = getBucketIndex(key);

        Node<K, V> head = buckets.get(bucketIndex);
        while(head != null){

            // key has to implement the .equals method
            if(head.key.equals(key)){
                head.value = value;
                break;
            }
            head = head.next;
        }

        // we didn't find the key -- new insertion
        if(head == null){
            head = buckets.get(bucketIndex);
            Node<K, V> node = new Node<>(key, value);
            node.next = head;
            buckets.set(bucketIndex, node);
            size++;
        }
    }

    public V remove(K key) throws KeyCannotBeNullException, HashCodeNotFoundException, NoSuchMethodException {
        if(key == null) throw new KeyCannotBeNullException();
        int bucketIndex = getBucketIndex(key);

        Node<K, V> head = buckets.get(bucketIndex);
        Node<K, V> prev = null;
        while(head != null){

            // key has to implement the .equals method
            if(head.key.equals(key)){
                size--;
                if(prev == null) buckets.set(bucketIndex, head.next);
                else prev.next = head.next;
                return head.value;
            }
            prev = head;
            head = head.next;
        }
        // key not found
        return null;
    }

    private void rehash() throws KeyCannotBeNullException, HashCodeNotFoundException, NoSuchMethodException {

        List<Node<K,V>> temp = buckets;
        numBuckets = numBuckets * 2;
        size = 0;
        buckets = new ArrayList<>(numBuckets);
        for(int i = 0; i < numBuckets; i++) buckets.add(null);

        for (Node<K, V> head : temp) {
            while (head != null) {
                put(head.key, head.value);
                head = head.next;
            }
        }
    }
}
