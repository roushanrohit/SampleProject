package misc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
    There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
    You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first
    if you want to take course ai.
    For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
    Return true if you can finish all courses. Otherwise, return false.

    Concepts : Topological sort and kahn's algorithm
 */
public class CourseSchedule {

    public static void main(String[] args) {
        int numCourses = 6;
        int[][] prerequisites = {{0,2},{4,2},{3,0},{3,4},{5,4},{1,5},{1,3},{1,0}};
        System.out.println("Can finish all the courses: " + canFinish(numCourses, prerequisites));
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {

        int[] inDegree = new int[numCourses];
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < numCourses; i++){
            adj.add(new ArrayList<>());
        }
        for(int[] p : prerequisites){
            adj.get(p[1]).add(p[0]);
            inDegree[p[0]]++;
        }

        // create a queue and add all indices with inDegree 0
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < inDegree.length; i++){
            if(inDegree[i] == 0){
                queue.offer(i);
            }
        }

        // kahn's algorithm
        int[] order = new int[numCourses];
        int k = 0;
        while(!queue.isEmpty()){

            int elem = queue.poll();
            order[k++] = elem;
            for(int index : adj.get(elem)){
                inDegree[index]--;
                if(inDegree[index] == 0){
                    queue.offer(index);
                }
            }
        }

        System.out.println("Topological order: ");
        for(int i : order) System.out.print(i + " ");
        System.out.println();
        return k == numCourses;
    }
}
