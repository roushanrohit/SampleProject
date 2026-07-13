package misc;

/*
    You are given an integer array nums. You are initially positioned at the array's first index,
    and each element in the array represents your maximum jump length at that position.
    Return true if you can reach the last index, or false otherwise.
 */
public class JumpGame {

    public static void main(String[] args) {

        int[] nums = {3,2,1,0,4};
        System.out.println("Can reach the last index: " + canJump(nums));
    }

    // the trick is to track the last position from where one can reach the end
    public static boolean canJump(int[] nums) {

        int lastPosition = nums.length - 1;
        for(int i = nums.length - 2; i >= 0; i--){

            if(nums[i] > 0 && (i + nums[i] >= lastPosition)){
                lastPosition = i;
            }
        }

        return lastPosition == 0;
    }
}
