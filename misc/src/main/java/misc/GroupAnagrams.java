package misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {

    public static void main(String[] args) {

        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        for(List<String> anagrams : groupAnagrams(strs)){
            System.out.println(anagrams);
        }
    }

    public static List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> hmap = new HashMap<>();

        for(String str : strs){

            String charStr = characterRepresentation(str);
            if(hmap.containsKey(charStr)){
                List<String> anagrams = hmap.get(charStr);
                anagrams.add(str);
                hmap.put(charStr, anagrams);
            } else {
                hmap.put(charStr, new ArrayList<>(List.of(str)));
            }
        }

        List<List<String>> ans = new ArrayList<>();
        ans.addAll(hmap.values());
        return ans;
    }

    public static String characterRepresentation(String str){

        int[] arr = new int[256];
        for(char ch : str.toCharArray()){
            arr[ch]++;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < arr.length; i++){
            sb.append(i).append(arr[i]).append("_");
        }

        return sb.toString();
    }
}
