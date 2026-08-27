package misc;

public class LongestIncreasingSubarray {

    public static void main(String[] args) {

        int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(longestIncreasingSubarray(arr));
    }

    public static int longestIncreasingSubarray(int[] arr){
        if(arr.length == 0) return 0;
        int current = 1;
        int max = 1;
        for(int i = 1; i < arr.length; i++){
            if(arr[i] > arr[i - 1]){
                current++;
            } else {
                current = 1;
            }
            max = Math.max(current, max);
        }
        return max;
    }
}
