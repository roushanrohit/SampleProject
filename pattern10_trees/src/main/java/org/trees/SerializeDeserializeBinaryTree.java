package org.trees;

import java.util.*;

/*
    Inorder traversal alone, even with null markers, does not uniquely identify the
    binary tree structure.
    Without Null Markers:
    1. Inorder + Preorder
    2. Inorder + Postorder
    With Null Markers:
    1. Preorder
    2. Postorder
    3. Levelorder
 */
public class SerializeDeserializeBinaryTree {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        BinaryTreeNode<Integer> root = takeInputLevelWise(s);
        printBinaryTreeLevelWise(root);
        String serializedTree = serializeBinaryTreeLvlOrder(root);
        System.out.println(serializedTree);
        String[] tokens = serializedTree.split(",");
        List<String> tokenList = new ArrayList<>(List.of(tokens));
        BinaryTreeNode<Integer> node = deserializeBinaryTreeLvlOrder(tokenList);
        printBinaryTreeLevelWise(node);
    }

    private static String serializeBinaryTreeLvlOrder(BinaryTreeNode<Integer> root) {

        if(root == null) {
            return "null,";
        }
        StringBuilder sb = new StringBuilder();
        Queue<BinaryTreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);
        sb.append(root.data).append(",");
        while(!queue.isEmpty()){
            BinaryTreeNode<Integer> node = queue.poll();
            if(node.left != null){
                queue.add(node.left);
                sb.append(node.left.data).append(",");
            } else {
                sb.append("null,");
            }
            if(node.right != null){
                queue.add(node.right);
                sb.append(node.right.data).append(",");
            } else {
                sb.append("null,");
            }
        }
        return sb.toString();
    }

    private static BinaryTreeNode<Integer> deserializeBinaryTreeLvlOrder(List<String> tokenList) {

        int index = 0;
        Queue<BinaryTreeNode<Integer>> queue = new LinkedList<>();
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(Integer.valueOf(tokenList.get(index)));
        queue.add(root);
        while(!queue.isEmpty()){
            BinaryTreeNode<Integer> node = queue.poll();
            if(!tokenList.get(index + 1).equals("null")){
                BinaryTreeNode<Integer> left = new BinaryTreeNode<>(Integer.valueOf(tokenList.get(index + 1)));
                node.left = left;
                queue.add(left);
            }
            index++;
            if(!tokenList.get(index + 1).equals("null")){
                BinaryTreeNode<Integer> right = new BinaryTreeNode<>(Integer.valueOf(tokenList.get(index + 1)));
                node.right = right;
                queue.add(right);
            }
            index++;
        }
        return root;
    }

    private static String serializeBinaryTree(BinaryTreeNode<Integer> root) {

        if(root == null) {
            return "null,";
        }
        String s = root.data + ",";
        s += serializeBinaryTree(root.left);
        s += serializeBinaryTree(root.right);
        return s;
    }

    private static BinaryTreeNode<Integer> deserializeBinaryTree(List<String> tokenList) {

        if(tokenList.get(0).equals("null")){
            tokenList.remove(0);
            return null;
        }
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(Integer.valueOf(tokenList.get(0)));
        tokenList.remove(0);
        root.left = deserializeBinaryTree(tokenList);
        root.right = deserializeBinaryTree(tokenList);
        return root;
    }

    // 1 2 3 4 5 -1 7 -1 -1 6 -1 8 9 -1 -1 -1 -1 -1 -1
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
