package org.graph.common;

import java.util.*;

public class AlienDictionary {

    public static void main(String[] args) {
        String[] strArray = {"wrt","wrf","er","ett","rftt"};
        System.out.println(alienDictionary(strArray));
    }

    public static List<Character> alienDictionary(String[] strArray){

        Map<Character, Integer> indegreeMap = new HashMap<>();
        Map<Character, Set<Character>> outwardEdges = new HashMap<>();
        for(String s : strArray){
            for(char c : s.toCharArray()){
                indegreeMap.putIfAbsent(c, 0);
            }
        }

        for(int i = 0; i < strArray.length - 1; i++){
            String s = strArray[i];
            String t = strArray[i+1];
            int length = Math.min(s.length(), t.length());
            int j = 0;
            while(j < length){
                if(s.charAt(j) != t.charAt(j)){
                    Set<Character> edge = outwardEdges.get(s.charAt(j));
                    if(edge == null) edge = new HashSet<>();
                    if(edge.add(t.charAt(j))) {
                        outwardEdges.put(s.charAt(j), edge);
                        indegreeMap.put(t.charAt(j), indegreeMap.getOrDefault(t.charAt(j), 0) + 1);
                    }
                    break;
                }
                j++;
            }
            if(j == length && s.length() > t.length()){
                // s is longer than t, but they matched for all of t's length,
                // meaning t is a prefix of s — s should never come before its own prefix
                return null;
            }
        }

        // kahn's algorithm
        // 1. create a queue and add all characters with indegree zero
        Queue<Character> queue = new LinkedList<>();
        for(char ch : indegreeMap.keySet()){
            if(indegreeMap.get(ch) == 0){
                queue.add(ch);
            }
        }
        // 2. iterate over the queue
        List<Character> ans = new ArrayList<>();
        while(!queue.isEmpty()){
            char ch = queue.poll();
            ans.add(ch);
            for(char c : outwardEdges.getOrDefault(ch, Collections.emptySet())){
                int indegree = indegreeMap.get(c);
                indegree--;
                indegreeMap.put(c, indegree);
                if(indegree == 0){
                    queue.add(c);
                }
            }
        }

        if(ans.size() < indegreeMap.size()){
            // cycle exists -- ans contains every character that could be legitimately ordered
            // (everything outside the cycle, or upstream of it)
            return null;
        }
        return ans;
    }
}