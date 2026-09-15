package org.trees;

import java.util.*;

public class RootToNodePath {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        BinaryTreeNode<Integer> root = takeInputLevelWise(s);
        printBinaryTreeLevelWise(root);
        System.out.println("Root to node path : " + rootToNodePath(root, 11));
    }

    private static List<Integer> rootToNodePath(BinaryTreeNode<Integer> root, int a) {

        if(root == null) return null;
        if(root.data == a) {
            return new ArrayList<>(List.of(root.data));
        }

        List<Integer> lo = rootToNodePath(root.left, a);
        if(lo != null){
            lo.add(root.data);
            return lo;
        }

        List<Integer> ro = rootToNodePath(root.right, a);
        if(ro != null){
            ro.add(root.data);
            return ro;
        }

        return null;
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
