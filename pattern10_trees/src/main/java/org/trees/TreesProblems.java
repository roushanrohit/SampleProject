package org.trees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class TreesProblems {

    // 4 3 2 3 5 1 6 1 7 0 0 0
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        NryTreeNode<Integer> root = takeInputLevelWise(s);
        System.out.println("count of nodes : " + countNodes(root));
        int x = 5;
        System.out.println("number of nodes greater than " + x + " are : " + numNodesGreaterThanX(root, x));
        System.out.println("height of tree: " + findHeight(root));
        System.out.println("printing nodes at depth 0: ");
        printNodesAtDepthK(root, 0);
        System.out.println();
        System.out.println("printing nodes at depth 1: ");
        printNodesAtDepthK(root, 1);
        System.out.println();
        System.out.println("printing nodes at depth 2: ");
        printNodesAtDepthK(root, 2);
        System.out.println();
        System.out.println("printing nodes at depth 3: ");
        printNodesAtDepthK(root, 3);
        System.out.println();
        System.out.println("number of leaf nodes : " + countLeafNodes(root));
        NryTreeNode<Integer> maxSumNode = nodeWithSumOfChildrenAndItselfMaximum(root);
        System.out.println("node having sum of children and itself maximum : "
                + (maxSumNode != null ? maxSumNode.data : " invalid input" ));

        x = 5;
        System.out.println("value of node just greater than " + x + " is : " + nodeJustGreaterThanX(root, x).data);
        System.out.println("second largest node : " + secondLargestNode(root).second);
        System.out.println("depth of node : " + x + " is : " + findDepth(root, x, 0));
        preOrderTraversal(root);
        System.out.println();
        postOrderTraversal(root);
        System.out.println();
        replaceNodeWithDepth(root, 0);
        printNryTreeLevelWise(root);
    }

    private static void postOrderTraversal(NryTreeNode<Integer> root) {
        for(int i = 0; i < root.children.size(); i++){
            postOrderTraversal(root.children.get(i));
        }
        System.out.print(root.data + " ");
    }

    private static void preOrderTraversal(NryTreeNode<Integer> root) {
        System.out.print(root.data + " ");
        for(int i = 0; i < root.children.size(); i++){
            preOrderTraversal(root.children.get(i));
        }
    }

    private static void replaceNodeWithDepth(NryTreeNode<Integer> root, int depth) {

        if(root == null) return;
        root.data = depth;
        for(int i = 0; i < root.children.size(); i++){
            replaceNodeWithDepth(root.children.get(i), depth + 1);
        }
    }

    private static int findDepth(NryTreeNode<Integer> root, int x, int depth) {

        if(root == null) return -1;
        if(root.data == x) return depth;

        for(int i = 0; i < root.children.size(); i++){
            int smallAns = findDepth(root.children.get(i), x, depth + 1);
            if(smallAns != -1) return smallAns;
        }

        return -1; // not found
    }

    private static Pair<Integer, Integer> secondLargestNode(NryTreeNode<Integer> root) {

        if(root == null) return null;
        Integer largest = root.data;
        Integer secondLargest = Integer.MIN_VALUE;

        for(int i = 0; i < root.children.size(); i++){
            Pair<Integer, Integer> smallAns = secondLargestNode(root.children.get(i));
            if(smallAns.first > largest){
                if(smallAns.second > largest){
                    secondLargest = smallAns.second;
                } else {
                    secondLargest = largest;
                }
                largest = smallAns.first;
            } else {
                // smallAns.first == largest
                if(smallAns.first.equals(largest)){
                    if(smallAns.second > secondLargest){
                        secondLargest = smallAns.second;
                    }
                } else {
                    // smallAns.first < largest
                    if (smallAns.first > secondLargest) {
                        secondLargest = smallAns.first;
                    }
                }
            }
        }

        return new Pair<>(largest, secondLargest);
    }

    private static NryTreeNode<Integer> nodeJustGreaterThanX(NryTreeNode<Integer> root, int x) {

        if(root == null) return null;
        NryTreeNode<Integer> ans = new NryTreeNode<>(Integer.MAX_VALUE);
        ans = root.data > x ? root : ans;

        for(int i = 0; i < root.children.size(); i++){
            NryTreeNode<Integer> smallAns = nodeJustGreaterThanX(root.children.get(i), x);
            if(smallAns.data < ans.data){
                ans = smallAns;
            }
        }

        return ans;
    }

    private static NryTreeNode<Integer> nodeWithSumOfChildrenAndItselfMaximum(NryTreeNode<Integer> root) {

        if(root == null) return null;

        Integer maxSum = root.data;
        for(NryTreeNode<Integer> child : root.children){
            maxSum += child.data;
        }
        NryTreeNode<Integer> maxSumNode = root;

        for(int i = 0; i < root.children.size(); i++){
            NryTreeNode<Integer> smallAns = nodeWithSumOfChildrenAndItselfMaximum(root.children.get(i));
            Integer temp = smallAns.data;
            for(NryTreeNode<Integer> child : smallAns.children){
                temp += child.data;
            }
            if(temp > maxSum){
                maxSumNode = smallAns;
                maxSum = temp;
            }
        }

        return maxSumNode;
    }

    private static int countLeafNodes(NryTreeNode<Integer> root) {

        if(root == null) return 0;
        int count = root.children.isEmpty() ? 1 : 0;
        for(int i = 0; i < root.children.size(); i++){
            count += countLeafNodes(root.children.get(i));
        }
        return count;
    }

    private static void printNodesAtDepthK(NryTreeNode<Integer> root, int k) {

        if(root == null) return;
        if(k == 0){
            System.out.print(root.data + " ");
        }
        for(int i = 0; i < root.children.size(); i++){
            printNodesAtDepthK(root.children.get(i), k - 1);
        }
    }

    private static int findHeight(NryTreeNode<Integer> root) {

        if(root == null) return 0;
        int maxSubTreeHeight = 0;
        for(int i = 0; i < root.children.size(); i++){
            maxSubTreeHeight = Math.max(maxSubTreeHeight, findHeight(root.children.get(i)));
        }
        return 1 + maxSubTreeHeight;
    }

    private static int numNodesGreaterThanX(NryTreeNode<Integer> root, int x) {

        if(root == null) return -1;
        int count = root.data > x ? 1 : 0;
        for(int i = 0; i < root.children.size(); i++){
            count += numNodesGreaterThanX(root.children.get(i), x);
        }
        return count;
    }

    private static int countNodes(NryTreeNode<Integer> root) {

        if(root == null) return 0;
        int count = 1;
        for(int i = 0; i < root.children.size(); i++){
            count += countNodes(root.children.get(i));
        }
        return count;
    }

    public static NryTreeNode<Integer> takeInputLevelWise(Scanner s){

        Queue<NryTreeNode<Integer>> queue = new LinkedList<>();
        int rootData = s.nextInt();
        NryTreeNode<Integer> root = new NryTreeNode<>(rootData);
        queue.add(root);

        while(!queue.isEmpty()){

            NryTreeNode<Integer> node = queue.poll();
            int childCount = s.nextInt();
            for(int i = 0; i < childCount; i++){
                int data = s.nextInt();
                NryTreeNode<Integer> child = new NryTreeNode<>(data);
                node.children.add(child);
                queue.add(child);
            }
        }
        return root;
    }

    private static void printNryTreeLevelWise(NryTreeNode<Integer> root){

        Queue<NryTreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        System.out.println(root.data);

        while(!queue.isEmpty()){

            StringBuilder s = new StringBuilder();
            while(queue.peek() != null){
                NryTreeNode<Integer> node = queue.poll();
                for(int i = 0; i < node.children.size(); i++){
                    NryTreeNode<Integer> child = node.children.get(i);
                    s.append(child.data);
                    queue.add(child);
                }
            }

            System.out.println(s);
            queue.poll();
            if(!queue.isEmpty()) queue.add(null);
        }

        System.out.println("Printing nry tree finished");
    }
}
