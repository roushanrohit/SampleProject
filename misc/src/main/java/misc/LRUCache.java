package misc;

import java.util.Map;
import java.util.HashMap;

public class LRUCache {

    int capacity = 0;
    int occurrence = 0;
    Map<Integer, Integer> data;
    Map<Integer, Integer> occurrenceMap;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        data = new HashMap<>();
        occurrenceMap = new HashMap<>();
    }

    public int get(int key) {
        if(data.containsKey(key)){
            occurrenceMap.put(key, occurrence++);
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
        occurrenceMap.put(key, occurrence++);
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

        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1,1);
        lruCache.put(2,2);
        lruCache.put(3,3);
        lruCache.put(1,1);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(2));
        System.out.println(lruCache.get(3));
    }
}
