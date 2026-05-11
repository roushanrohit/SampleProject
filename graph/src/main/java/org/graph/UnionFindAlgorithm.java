package org.graph;

import java.util.Arrays;
import java.util.Scanner;

public class UnionFindAlgorithm {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.println("Enter number of vertices in the graph:");
        int noOfVertices = s.nextInt();
        System.out.println("Enter number of edges in the graph:");
        int noOfEdges = s.nextInt();
        Edge[] input = new Edge[noOfEdges];

        for(int i = 0; i < noOfEdges; i++){
            System.out.println("Enter first vertex:");
            int firstVertex = s.nextInt();
            System.out.println("Enter second vertex:");
            int secondVertex = s.nextInt();
            System.out.println("Enter weight:");
            int weight = s.nextInt();
            input[i] = new Edge(firstVertex, secondVertex, weight);
        }

        // For a weighted, undirected and connected graph
        Edge[] output = mst(input, noOfVertices, noOfEdges);
        if(output.length > 0) {
            System.out.println("Printing edges in the MST:");
            int totalWeight = 0;
            for (Edge e : output) {
                e.print();
                totalWeight += e.weight;
            }
            System.out.println("Total weight of MST: " + totalWeight);
        } else {
            System.out.println("Graph is disconnected, no MST possible");
        }
    }

    // O(V3) - It is a greedy algorithm
    private static Edge[] mst(Edge[] input, int noOfVertices, int noOfEdges) {

        Arrays.sort(input);
        int[] parents = new int[noOfVertices];
        for(int i = 0; i < noOfVertices; i++) {
            parents[i] = i;
        }
        Edge[] output = new Edge[noOfVertices - 1];
        int count = 0;

        // O(E) = O(V2)
        for(int i = 0; i < noOfEdges && count < noOfVertices - 1; i++){
            Edge e = input[i];
            int v1 = e.source;
            int v2 = e.destination;

            // O(V)
            while (parents[v1] != v1){
                v1 = parents[v1];
            }
            // O(V)
            while (parents[v2] != v2){
                v2 = parents[v2];
            }

            if(v1 != v2){
                output[count++] = e;
                parents[v2] = v1;
            }
        }

        return output;
    }


}

class Edge implements Comparable<Edge> {
    int source;
    int destination;
    int weight;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public void print(){
        System.out.println(Math.min(source, destination) + " " + Math.max(source, destination));
    }

    @Override
    public int compareTo(Edge e) {
        return this.weight - e.weight;
    }
}