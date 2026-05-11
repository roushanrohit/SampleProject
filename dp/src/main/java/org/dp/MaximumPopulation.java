package org.dp;

public class MaximumPopulation {

    public static void main(String[] args) {

        int[] arr = new int[]{5,4,5,1};
        String gates = "0111";
        System.out.println(maxPopulation(arr, gates, 0));
    }

    private static int maxPopulation(int[] arr, String gates, int si) {

        // base case
        if(si >= gates.length()) return 0;
        for(int i = si; i <= gates.length() - 2; i++){
            if(gates.charAt(i) == '0' && gates.charAt(i + 1) == '1'){

                // with swapping
                int op1 = arr[i] + maxPopulation(arr, '0' + gates.substring(i + 2), i + 1);
                // without swapping
                int op2 = arr[i + 1] + maxPopulation(arr, gates.substring(i + 2), i + 2);
                return Math.max(op1, op2);
            }
        }
        // no 0 1 combination found starting from startIndex;
        int max = 0;
        for(int i = si; i <= gates.length() - 1; i++){
            if(gates.charAt(i) == '1'){
                max += arr[i];
            }
        }
        return max;
    }
}
