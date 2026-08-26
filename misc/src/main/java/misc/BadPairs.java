package misc;

/*
    You are given an array of 0s and 1s and an integer k, you need to find the number of windows
    of size k which do not have 2 consecutive 0s or 2 consecutive 1s
 */
public class BadPairs {

    public static void main(String[] args) {

        int[] arr = {0,1,0,1};
        int k = 3;
        System.out.println(validWindows(arr, k));
    }

    private static int validWindows(int[] arr, int k) {

        // edge case
        if(k <= 1) return arr.length;

        int right = 1;
        int badPairs = 0;
        int validWindows = 0;
        // first window
        while(right < arr.length && right < k){
            if(arr[right] == arr[right - 1]){
                badPairs++;
            }
            right++;
        }
        if(badPairs == 0){
            validWindows++;
        }

        // slide the window
        while (right < arr.length){
            // element coming in
            if(arr[right] == arr[right - 1]){
                badPairs++;
            }
            // element going out
            if(arr[right - k + 1] == arr[right - k]){
                badPairs--;
            }
            right++;
            if(badPairs == 0){
                validWindows++;
            }
        }

        return validWindows;
    }
}
