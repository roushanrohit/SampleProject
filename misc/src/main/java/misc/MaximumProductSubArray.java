package misc;

public class MaximumProductSubArray {

    public static void main(String[] args) {

        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println("Maximum sub array product: " + maxSubArrayProduct(nums));
    }

    public static int maxSubArrayProduct(int[] arr){

        int currentMax = arr[0];
        int currentMin = arr[0];
        int maxProduct = arr[0];

        for(int i = 1; i < arr.length; i++){
            int tempMax = currentMax;
            currentMax = Math.max(Math.max(currentMax * arr[i], currentMin * arr[i]), arr[i]);
            currentMin = Math.min(Math.min(currentMin * arr[i], tempMax * arr[i]), arr[i]);
            maxProduct = Math.max(currentMax, maxProduct);
        }
        return maxProduct;
    }
}
