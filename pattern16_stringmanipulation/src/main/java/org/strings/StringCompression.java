package org.strings;

public class StringCompression {

    public static void main(String[] args) {

        String input = "aaabbccdsa";
        System.out.println(compressedString(input));
    }

    private static String compressedString(String input) {

        String res = Character.toString(input.charAt(0));
        int count = 1;
        for(int i = 1; i < input.length(); i++){
            if(input.charAt(i) != res.charAt(res.length() - 1)){
                if(count > 1){
                    res += count;
                    count = 1;
                }
                res += input.charAt(i);
            } else {
                count++;
            }
        }
        return res;
    }
}
