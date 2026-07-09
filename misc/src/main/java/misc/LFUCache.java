package misc;

import java.util.HashMap;
import java.util.Map;

public class LFUCache {

    int capacity = 0;
    Map<Integer, Integer> data;
    Map<Integer, Integer> occurrenceMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        data = new HashMap<>();
        occurrenceMap = new HashMap<>();
    }

    public int get(int key) {
        if(data.containsKey(key)){
            occurrenceMap.put(key, occurrenceMap.getOrDefault(key, 0) + 1);
            return data.get(key);
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {

        if(!data.containsKey(key)){
            if(data.size() == capacity){
                int leastRecentKey = getLeastRecentlyUsedKey(occurrenceMap);
                data.remove(leastRecentKey);
                occurrenceMap.remove(leastRecentKey);
            }
        }

        data.put(key, value);
        occurrenceMap.put(key, occurrenceMap.getOrDefault(key, 0) + 1);
    }

    public int getLeastRecentlyUsedKey(Map<Integer, Integer> occurrenceMap){
        int leastValue = Integer.MAX_VALUE;
        int leastKey = -1;
        for(int key : occurrenceMap.keySet()){
            if(occurrenceMap.get(key) < leastValue){
                leastValue = occurrenceMap.get(key);
                leastKey = key;
            }
        }
        return leastKey;
    }

    public static void main(String[] args) {

        LFUCache lfuCache = new LFUCache(3);
        lfuCache.put(1,1);
        lfuCache.put(2,2);
        lfuCache.put(3,3);
        lfuCache.get(2);
        lfuCache.get(1);
        lfuCache.put(4,4);

        System.out.println(lfuCache.get(1));
        System.out.println(lfuCache.get(2));
        System.out.println(lfuCache.get(3));
        System.out.println(lfuCache.get(4));
    }
}
