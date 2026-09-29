package org.trees;

import java.util.*;

public class RootToLeafPath {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        BinaryTreeNode<Integer> root = takeInputLevelWise(s);
        printBinaryTreeLevelWise(root);

        // [1->2->4->8, 1->2->4->9, 1->2->5, 1->3->6, 1->3->7]
        List<String> rootToLeafPaths = new ArrayList<>();
        rootToLeafPath(root, "", rootToLeafPaths);
        for(String str : rootToLeafPaths) {
            System.out.println(str);
        }

        System.out.println("<<<<<<<<<");

        /*
            [8, 4, 2, 1]
            [9, 4, 2, 1]
            [5, 2, 1]
            [6, 3, 1]
            [7, 3, 1]
         */
        List<List<Integer>> rootToLeafPaths2 = rootToLeafPaths(root);
        for(List<Integer> list : rootToLeafPaths2) {
            System.out.println(list);
        }
    }

    private static List<List<Integer>> rootToLeafPaths(BinaryTreeNode<Integer> root) {
        if(root.left == null && root.right == null) {
            List<Integer> l = new ArrayList<>();
            l.add(root.data);
            return new ArrayList<>(List.of(l));
        }
        List<List<Integer>> ans = new ArrayList<>();
        List<List<Integer>> lo = rootToLeafPaths(root.left);
        for(List<Integer> l : lo){
            l.add(root.data);
            ans.add(l);
        }
        List<List<Integer>> ro = rootToLeafPaths(root.right);
        for(List<Integer> r : ro){
            r.add(root.data);
            ans.add(r);
        }
        return ans;
    }

    private static void rootToLeafPath(BinaryTreeNode<Integer> root, String path, List<String> rootToLeafPaths) {
        if(root == null) {
            return;
        }
        path = path + root.data + " ";
        if(root.left == null && root.right == null){
            rootToLeafPaths.add(path);
            return;
        }
        rootToLeafPath(root.left, path, rootToLeafPaths);
        rootToLeafPath(root.right, path, rootToLeafPaths);
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
