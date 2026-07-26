package misc;

/*
    You are a professional robber planning to rob houses along a street.
    Each house has a certain amount of money stashed, the only constraint stopping you from robbing
    each of them is that adjacent houses have security systems connected and it will automatically
    contact the police if two adjacent houses were broken into on the same night.
    All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one
    Given an integer array nums representing the amount of money of each house,
    return the maximum amount of money you can rob tonight without alerting the police.
 */
public class HouseRobber2 {

    public static void main(String[] args) {

        int[] nums = {1,2,3,1};
        System.out.println("Maximum money that can be robbed: " + rob(nums));
    }

    public static int rob(int[] nums) {
        return Math.max(rob(nums, 0, nums.length - 2), rob(nums, 1, nums.length - 1));
    }

    public static int rob(int[] nums, int si, int ei){

        int n = ei - si + 1;
        int[] dp = new int[n];
        dp[0] = nums[si];
        if(n > 1){
            dp[1] = Math.max(nums[si], nums[si + 1]);
        }
        for(int i = si + 2; i <= ei; i++){
            int idx = i - si;
            dp[idx] = Math.max(dp[idx - 1], dp[idx - 2] + nums[i]);
        }
        return dp[n - 1];
    }
}
