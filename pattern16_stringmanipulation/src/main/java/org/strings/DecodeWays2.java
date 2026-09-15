package org.strings;

import java.util.ArrayList;
import java.util.List;

public class DecodeWays2 {

    public static void main(String[] args) {

        //String s = "909";
        String s = "109";
        System.out.println("Number of ways to decode string: " + s + " : " + decodeString(s, 0));
    }

    private static List<String> decodeString(String s, int index){

        // successfully decoded the entire string
        if(index == s.length()) {
            return List.of("");
        }

        // Strings starting with 0 are invalid
        if (s.charAt(index) == '0') {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();
        char ch = (char) ('A' + (s.charAt(index) - '1'));
        for(String suffix : decodeString(s, index + 1)){
            result.add(ch + suffix);
        }

        // Take two digits if valid (10-26)
        if (index + 1 < s.length()) {
            int num = (s.charAt(index) - '0') * 10 + (s.charAt(index + 1) - '0');
            if (num <= 26) {
                char two = (char) ('A' + num - 1);
                for (String suffix : decodeString(s, index + 2)) {
                    result.add(two + suffix);
                }
            }
        }

        return result;
    }
}
