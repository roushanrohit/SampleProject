package org.hashmap;

import java.util.ArrayList;
import java.util.List;

class BinaryTreeNode<V> {

    int key;
    V value;

    BinaryTreeNode<V> left;
    BinaryTreeNode<V> right;

    BinaryTreeNode(int key, V value){
        this.key = key;
        this.value = value;
    }

}
public class TreeMap<K, V> {

    private BinaryTreeNode<V> root;

    private int getHashCode(K key) throws NoSuchMethodException, HashCodeNotFoundException {
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

    public void put(K key, V value) throws KeyCannotBeNullException, NoSuchMethodException, HashCodeNotFoundException {
        if(key == null) throw new KeyCannotBeNullException();

        int hashCode = getHashCode(key);
        root = put(hashCode, value, root);
    }

    public BinaryTreeNode<V> put(int key, V value, BinaryTreeNode<V> root) {

        if(root == null) {
            BinaryTreeNode<V> node = new BinaryTreeNode<>(key, value);
            return node;
        }

        if(key > root.key) root.right = put(key, value, root.right);
        else if(key < root.key) root.left = put(key, value, root.left);
        return root;
    }

    public V get(K key) throws KeyCannotBeNullException, HashCodeNotFoundException, NoSuchMethodException {
        if(key == null) throw new KeyCannotBeNullException();

        int hashCode = getHashCode(key);
        return get(hashCode, root);
    }

    private V get(int key, BinaryTreeNode<V> root) {

        if(root == null) return null;
        if(root.key == key) return root.value;
        else if(root.key > key) return get(key, root.left);
        else return get(key, root.right);
    }

    public List<Integer> getKeys(){

        List<Integer> allKeys = new ArrayList<>();
        getKeys(root, allKeys);
        return allKeys;
    }

    private void getKeys(BinaryTreeNode<V> root, List<Integer> allKeys){
        // in-order traversal to get the keys in sorted order
        if(root == null) return;
        getKeys(root.left, allKeys);
        allKeys.add(root.key);
        getKeys(root.right, allKeys);
    }

    public void delete(K key) throws KeyCannotBeNullException, HashCodeNotFoundException, NoSuchMethodException {
        if(key == null) throw new KeyCannotBeNullException();

        int hashCode = getHashCode(key);

        root = delete(hashCode, root);
    }

    private BinaryTreeNode<V> delete(int key, BinaryTreeNode<V> root) {

        if(root == null) return null;

        if(key > root.key) {
            root.right = delete(key, root.right);
            return root;
        }
        if(key < root.key){
            root.left = delete(key, root.left);
            return root;
        }

        // we've reached the node to be deleted
        if(root.left == null && root.right == null) return null;
        else if(root.left == null) return root.right;
        else if(root.right == null) return root.left;
        else {

            BinaryTreeNode<V> minNode = root.right;
            while (minNode.left != null) minNode = minNode.left;
            root.key = minNode.key;
            root.value = minNode.value;
            root.right = delete(minNode.key, root.right);
            return root;
        }
    }
}
