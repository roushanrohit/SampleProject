package org.example;

import java.util.Map;
import java.util.TreeMap;

public class TreeMapExample {
    public static void main(String[] args) {

        TreeMap<Long, String> history = new TreeMap<>();
        history.put(100L, "A");
        history.put(200L, "B");
        history.put(300L, "C");

        Map.Entry<Long, String> ceilingEntry = history.ceilingEntry(200L);
        System.out.println("key: " + ceilingEntry.getKey() + " , value: " + ceilingEntry.getValue());
        Map.Entry<Long, String> floorEntry = history.floorEntry(200L);
        System.out.println("key: " + floorEntry.getKey() + " , value: " + floorEntry.getValue());

        Map.Entry<Long, String> firstEntry = history.firstEntry();
        System.out.println("key: " + firstEntry.getKey() + " , value: " + firstEntry.getValue());
        Map.Entry<Long, String> lastEntry = history.lastEntry();
        System.out.println("key: " + lastEntry.getKey() + " , value: " + lastEntry.getValue());

        Map.Entry<Long, String> higherEntry = history.higherEntry(200L);
        System.out.println("key: " + higherEntry.getKey() + " , value: " + higherEntry.getValue());
        Map.Entry<Long, String> lowerEntry = history.lowerEntry(200L);
        System.out.println("key: " + lowerEntry.getKey() + " , value: " + lowerEntry.getValue());
    }
}