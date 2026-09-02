package org.example;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
Example 1:
Input: n = 2, tasks = [[0,10],[1,5],[2,7],[3,4]]
Output: 0
Explanation:
- Time 0: Both workers free. Task [0,10] assigned to worker 0 (busy until time 10).
- Time 1: Worker 1 free. Task [1,5] assigned to worker 1 (busy until time 5).
- Time 2: All workers busy. Task [2,7] (duration 5) is queued.
- Time 3: All workers busy. Task [3,4] (duration 1) is queued.
- Time 5: Worker 1 finishes. Queued task [2,7] starts on worker 1 for interval [5,10).
- Time 10: Both workers finish. Queued task [3,4] starts on worker 0 for interval [10,11).
Result: Worker 0 and Worker 1 both processed 2 tasks. Return 0 (smallest index).
 */
public class TaskProblem {

    public static void main(String[] args) {

        int n = 3;
        int[][] tasks = {{0,4}, {0,4}, {0,8}, {1,3}, {2,3}};
        //int[][] tasks = {{1,20},{2,10},{3,5},{4,9},{6,8}};
        serverWithMostTasks(n, tasks);
    }

    private static void serverWithMostTasks(int n, int[][] tasks) {

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        int[][] servers = new int[n][];
        for(int i = 0; i < n; i++){
            servers[i] = new int[]{i, 0};
            pq.offer(servers[i]);
        }

        int[] ans = new int[n];
        for(int[] task : tasks){
            // get the server with the earliest end time
            int[] server = pq.poll();
            server[1] = server[1] + task[1];
            pq.offer(server);
            ans[server[0]]++;
        }

        for(int num : ans){
            System.out.print(num + " ");
        }
        System.out.println();
        int maxIndex = 0;
        for(int i = 0; i < ans.length; i++){
            if(ans[i] > ans[maxIndex]){
                maxIndex = i;
            }
        }
        System.out.println("maxIndex: " + maxIndex);
    }

}
