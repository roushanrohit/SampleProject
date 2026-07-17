package org.trees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class LCABinaryTree {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        BinaryTreeNode<Integer> root = takeInputLevelWise(s);
        printBinaryTreeLevelWise(root);
        System.out.println("LCA of nodes 5 and 7: " + lca(root, 15, 17));
    }

    private static int lca(BinaryTreeNode<Integer> root, int a, int b) {

        if(root == null) return -1;
        if(root.data == a || root.data == b){
            return root.data;
        }

        int leftLCA = lca(root.left, a, b);
        int rightLCA = lca(root.right, a, b);

        if(leftLCA == -1) return rightLCA;
        if(rightLCA == -1) return leftLCA;

        return root.data;
    }

    // 1 2 3 4 5 6 7 8 9 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1
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

    private static void printBinaryTreeLevelWise(BinaryTreeNode<Integer> root){

        Queue<BinaryTreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){

            StringBuilder s = new StringBuilder();
            BinaryTreeNode<Integer> node = queue.poll();
            s.append(node.data).append(" : ");
            if(node.left != null){
                s.append(" L ").append(node.left.data).append(",");
                queue.add(node.left);
            }
            if(node.right != null){
                s.append(" R ").append(node.right.data).append(",");
                queue.add(node.right);
            }
            System.out.println(s);
        }
    }
}
