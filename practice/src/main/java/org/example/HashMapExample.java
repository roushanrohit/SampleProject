package org.example;

import java.util.HashMap;
import java.util.Map;

public class HashMapExample {

    public static void main(String[] args) {

        String text = "apple banana apple cherry apple banana";
        String[] words = text.split(" ");
        // create a Map to store the frequencies
        Map<String, Integer> wordCounts = new HashMap<>();
        for(String word : words){
            wordCounts.merge(word, 1, Integer::sum);
        }
        System.out.println(wordCounts);
    }
}
