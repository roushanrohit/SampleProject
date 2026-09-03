package misc;

import java.util.*;

public class OpenTheLock {

    public static void main(String[] args) {

        String[] deadends = {"0201","0101","0102","1212","2002"};
        String target = "0202";
        System.out.println(openLock(deadends, target));
    }

    public static int openLock(String[] deadends, String target) {
        int turns = 0;
        Queue<String> queue = new LinkedList<>();
        queue.add("0000");
        Set<String> deadEnds = new HashSet<>(Arrays.asList(deadends));
        Set<String> visited = new HashSet<>();
        visited.add("0000");
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int k = 0; k < size; k++){
                String current = queue.poll();
                if(current.equals(target)){
                    return turns;
                }
                for(int i = 0; i < 4; i++){
                    char[] chars = current.toCharArray();
                    // rotate forward
                    chars[i] = (char)('0' + (chars[i] - '0' + 1) % 10);
                    String next = new String(chars);
                    if(!visited.contains(next) && !deadEnds.contains(next)){
                        queue.add(next);
                        visited.add(next);
                    }
                    // rotate backwards
                    chars = current.toCharArray();
                    chars[i] = (char)('0' + (chars[i] - '0' + 9) % 10);
                    String prev = new String(chars);
                    if(!visited.contains(prev) && !deadEnds.contains(prev)){
                        queue.add(prev);
                        visited.add(prev);
                    }
                }
            }
            turns++;
        }

        // not possible
        return -1;
    }
}
