package org.strings;

public class DecodeWays {

    public static void main(String[] args) {

        //String s = "909";
        String s = "109";
        System.out.println("Number of ways to decode string: " + s + " : " + decodeString(s, 0));
    }

    private static int decodeString(String s, int index){

        // successfully decoded the entire string
        if(index == s.length()) {
            return 1;
        }

        // Strings starting with 0 are invalid
        if (s.charAt(index) == '0') {
            return 0;
        }

        // Take one digit
        int ways = decodeString(s, index + 1);

        // Take two digits if valid (10-26)
        if (index + 1 < s.length()) {
            int num = (s.charAt(index) - '0') * 10 + (s.charAt(index + 1) - '0');
            if (num >= 10 && num <= 26) {
                ways += decodeString(s, index + 2);
            }
        }

        return ways;
    }
}
