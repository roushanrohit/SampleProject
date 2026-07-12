package misc;

public class MaximumSubarray {

    public static void main(String[] args) {

        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println("Maximum subarray sum: " + maxSubArraySum(nums));
        int[] maxSubarrayValues = maxSubArraySum2(nums);
        System.out.println("Overall start: " + maxSubarrayValues[0] + ", Overall End: " + maxSubarrayValues[1]
                + ", Max Sum: " + maxSubarrayValues[2]);
    }

    public static int maxSubArraySum(int[] arr){

        int currentSum = arr[0];
        int maxSum = arr[0];

        for(int i = 1; i < arr.length; i++){
            currentSum = Math.max(currentSum + arr[i], arr[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    public static int[] maxSubArraySum2(int[] arr){

        int currentSum = arr[0];
        int currentStart = 0;
        int maxSum = arr[0];
        int overAllStart = 0, overAllEnd = 0;

        for(int i = 1; i < arr.length; i++){

            if(currentSum < 0){
                currentSum = arr[i];
                currentStart = i;
            } else {
                currentSum = currentSum + arr[i];
            }

            if(maxSum < currentSum){
                maxSum = currentSum;
                overAllStart = currentStart;
                overAllEnd = i;
            }
        }

        return new int[]{overAllStart, overAllEnd, maxSum};
    }
}
