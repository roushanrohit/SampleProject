package org.hashmap;

public class HashMapUse {
    public static void main(String[] args) {

        TreeMap<Integer, String> treeMap = new TreeMap<>();
        try {
            treeMap.put(20, "Apples,Oranges,Kiwi");
            treeMap.put(10,"Tomato,Potato,Capsicum");

            System.out.println("--- Order of keys maintained in TreeMap ---");
            for(Integer key : treeMap.getKeys()){
                System.out.println(key + " : " + treeMap.get(key));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        HashMap<Integer, String> hmap = new HashMap<>();
        try {
            hmap.put(20, "Apples,Oranges,Kiwi");
            hmap.put(10,"Tomato,Potato,Capsicum");

            System.out.println("--- No order of keys maintained in HashMap ---");
            for(Integer key : hmap.getKeys()){
                System.out.println(key + " : " + hmap.get(key));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}