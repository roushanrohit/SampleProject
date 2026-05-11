package org.trees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BinaryTreeProblems {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        BinaryTreeNode<Integer> root = takeInputLevelWise(s);
        System.out.println("count nodes : " + countNodes(root));
        System.out.println("height: " + height(root));
        mirror(root);
        printNryTreeLevelWise(root);
        checkBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean checkBST(BinaryTreeNode<Integer> root, int minValue, int maxValue) {

        if(root == null) return true;
        if(root.data < minValue || root.data > maxValue) return false;
        boolean isLeftOk = checkBST(root.left, minValue, root.data);
        boolean isRightOk = checkBST(root.right, root.data + 1, maxValue);
        return isLeftOk && isRightOk;
    }

    private static void mirror(BinaryTreeNode<Integer> root) {
        if(root == null) return;
        mirror(root.left);
        mirror(root.right);
        BinaryTreeNode<Integer> temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    private static int height(BinaryTreeNode<Integer> root) {

        if(root == null) return 0;
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    private static int countNodes(BinaryTreeNode<Integer> root) {

        if(root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
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

    private static void printNryTreeLevelWise(BinaryTreeNode<Integer> root){

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
