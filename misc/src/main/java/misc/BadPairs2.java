package misc;

/*
    You are given an array of 0s and 1s and an integer k, you need to find the number of windows
    of size k which do not have 2 consecutive 0s or 2 consecutive 1s
    But after the last element, we can wrap around to the beginning.
    For a circular array, there are n windows, one starting at every index.
 */
public class BadPairs2 {

    public static void main(String[] args) {

        int[] arr = {0,1,0,1};
        int k = 3; // there will be 4 windows, one starting at every index
        System.out.println(validWindows(arr, k));
    }

    private static int validWindows(int[] arr, int k) {

        int n = arr.length;
        // edge case
        if(k <= 1) return n;

        int right = 1;
        int badPairs = 0;
        int validWindows = 0;
        // first window
        while(right < n && right < k){
            if(arr[right] == arr[right - 1]){
                badPairs++;
            }
            right++;
        }
        if(badPairs == 0){
            validWindows++;
        }

        /*
            Why n + k - 1?
            For a circular array, we have n windows, we have already counted the first window
            right is already at index k, we need to slide n - 1 more times
            so, right has to go till k + n - 1 = n + k - 1
         */
        while (right < n + k - 1){
            // element coming in
            if(arr[right % n] == arr[(right - 1) % n]){
                badPairs++;
            }
            // element going out
            if(arr[(right - k + 1) % n] == arr[(right - k) % n]){
                badPairs--;
            }
            if(badPairs == 0){
                validWindows++;
            }
            right++;
        }

        return validWindows;
    }
}
