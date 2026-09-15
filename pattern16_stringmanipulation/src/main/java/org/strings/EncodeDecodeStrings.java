package org.strings;

import java.util.Arrays;
import java.util.List;

public class EncodeDecodeStrings {

    public static void main(String[] args) {
        String[] strs = {"Hello","World"};
        String encoded = encode(Arrays.asList(strs));
        System.out.print(encoded);
        List<String> decoded = decode(encoded);
        System.out.println();
        System.out.print(decoded);
    }

    // Encodes a list of strings to a single string.
    public static String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for(String str : strs){
            sb.append(str).append('\0');
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public static List<String> decode(String s) {
        String delimiter = "";
        delimiter += '\0';
        String[] strs = s.split(delimiter, -1);
        return Arrays.asList(Arrays.copyOf(strs, strs.length - 1));
    }
}
