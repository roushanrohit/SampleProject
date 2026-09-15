package org.strings;

public class RemoveConsecutiveDuplicates {

    public static void main(String[] args) {

        String str = "aaaaasbsewwwhhhewsgggdd";
        System.out.println(removeConsecutiveDuplicates(str));
    }

    private static String removeConsecutiveDuplicates(String str) {
        String res = Character.toString(str.charAt(0));
        for(int i = 1; i < str.length(); i++){
            // compare ith character with the last added character in res
            if(str.charAt(i) != res.charAt(res.length() - 1)){
                res += str.charAt(i);
            }
        }
        return res;
    }
}
