package org.trees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SubtreeOfAnotherTree {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        // 3 4 5 1 2 -1 -1 -1 -1 -1 -1
        BinaryTreeNode<Integer> root = takeInputLevelWise(s);
        // 4 1 2 -1 -1 -1 -1
        BinaryTreeNode<Integer> subRoot = takeInputLevelWise(s);
        System.out.println("Is subtree: " + isSubtree(root, subRoot));
    }

    public static boolean isSubtree(BinaryTreeNode<Integer> root, BinaryTreeNode<Integer> subRoot) {
        if(subRoot == null) return true;
        if(root == null) return false;
        if(isSameTree(root, subRoot)) return true;
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public static boolean isSameTree(BinaryTreeNode<Integer> root, BinaryTreeNode<Integer> subRoot) {
        if(root == null && subRoot == null) return true;
        if(root == null || subRoot == null) return false;
        return root.data == subRoot.data && isSameTree(root.left, subRoot.left)
                && isSameTree(root.right, subRoot.right);
    }

    public static BinaryTreeNode<Integer> takeInputLevelWise(Scanner s){

        Queue<BinaryTreeNode<Integer>> queue = new LinkedList<>();
        int rootData = s.nextInt();
        if(rootData == -1) return null;
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(rootData);
        queue.add(root);

        while(!queue.isEmpty()){

            BinaryTreeNode<Integer> node = queue.poll();
            int left = s.nextInt();
            if(left != -1){
                BinaryTreeNode<Integer> leftNode = new BinaryTreeNode<>(left);
                node.left = leftNode;
                queue.add(leftNode);
            }
            int right = s.nextInt();
            if(right != -1){
                BinaryTreeNode<Integer> rightNode = new BinaryTreeNode<>(right);
                node.right = rightNode;
                queue.add(rightNode);
            }
        }
        return root;
    }
}
