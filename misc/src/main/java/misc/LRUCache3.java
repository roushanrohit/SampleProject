package misc;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache3 {

    int capacity = 0;
    Map<Integer, Integer> data;

    public LRUCache3(int capacity) {
        this.capacity = capacity;

        /*
            By default, LinkedHashMap never removes anything on its own.
            It just maintains ordering (insertion order, or access order if you pass true as the
            third constructor argument). It will grow forever unless you tell it otherwise.

            default implementation of removeEldestEntry is just return false;

            This method is called automatically by put()/putAll() after an insertion, and if it returns true,
            the map removes the eldest entry (the one at the front of the iteration order).
            But since the default always returns false, nothing happens unless you override it.
         */
        data = new LinkedHashMap<>(5, 0.75f, true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest){
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        return data.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        data.put(key, value);
    }

    public static void main(String[] args) {

        LRUCache3 lruCache = new LRUCache3(2);
        lruCache.put(1,1);
        lruCache.put(2,2);
        lruCache.put(3,3);
        lruCache.put(1,1);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(2));
        System.out.println(lruCache.get(3));
    }
}
