package org.trees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class TakeInput {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        NryTreeNode<Integer> root = takeInputLevelWise(s);
        printNryTreeLevelWise(root);
    }

    public static NryTreeNode<Integer> takeInputRecursive(Scanner s){

        int rootData = s.nextInt();
        NryTreeNode<Integer> root = new NryTreeNode<>(rootData);
        int childCount = s.nextInt();
        for(int i = 0; i < childCount; i++){
            root.children.add(takeInputRecursive(s));
        }
        return root;
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

    private static void printNryTree(NryTreeNode<Integer> root) {

        System.out.print(root.data + " : ");
        for(NryTreeNode<Integer> child : root.children){
            System.out.print(child.data + " ");
        }
        System.out.println();
        for(NryTreeNode<Integer> child : root.children){
            printNryTree(child);
        }
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
