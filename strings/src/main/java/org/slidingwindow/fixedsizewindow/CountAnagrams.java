package org.slidingwindow.fixedsizewindow;

public class CountAnagrams {

    public static void main(String[] args) {

        String str = "aabaabaa";
        String pattern = "aaba";
        System.out.println("Number of anagrams of " + pattern + " in String " + str + " is: " + countAnagrams(str, pattern));
    }

    private static int countAnagrams(String str, String pattern) {

        int n = str.length();
        int k = pattern.length();
        if(n < k) return 0;
        int[] arr = new int[256];
        int count = 0;
        int nonZeroCharactersCount = 0;

        // first window
        for(int i = 0; i < k; i++){

            if(arr[str.charAt(i)] == -1) nonZeroCharactersCount--;
            else if(arr[str.charAt(i)] == 0) nonZeroCharactersCount++;
            arr[str.charAt(i)]++;

            if(arr[pattern.charAt(i)] == 1) nonZeroCharactersCount--;
            else if(arr[pattern.charAt(i)] == 0) nonZeroCharactersCount++;
            arr[pattern.charAt(i)]--;
        }

        // result for first window
        if(nonZeroCharactersCount == 0) count++;

        // slide the window
        for(int end = k; end < n; end++){

            // new character entering window
            if(arr[str.charAt(end)] == -1) nonZeroCharactersCount--;
            else if(arr[str.charAt(end)] == 0) nonZeroCharactersCount++;
            arr[str.charAt(end)]++;

            // character leaving window
            if(arr[str.charAt(end - k)] == 1) nonZeroCharactersCount--;
            else if(arr[str.charAt(end - k)] == 0) nonZeroCharactersCount++;
            arr[str.charAt(end - k)]--;

            // add result for the current window
            if(nonZeroCharactersCount == 0) count++;
        }

        return count;
    }
}
