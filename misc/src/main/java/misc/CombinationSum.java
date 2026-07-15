package misc;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

    public static void main(String[] args) {

        int[] candidates = {2,3,5};
        int target = 8;

        List<List<Integer>> combinations = combinationSum(candidates, target);
        for(List<Integer> list : combinations){
            System.out.println();
            for(int num : list){
                System.out.print(num + " ");
            }
        }
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {

        return combinationSum(candidates, target, 0);
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target, int si) {

        // base case
        if (target == 0) {
            List<List<Integer>> result = new ArrayList<>();
            result.add(new ArrayList<>());   // a list containing one empty combination
            return result;
        }
        if (target < 0 || si == candidates.length) {
            return new ArrayList<>();        // an empty list of combinations
        }

        List<List<Integer>> ans = new ArrayList<>();
        List<List<Integer>> smallAns1 = combinationSum(candidates, target - candidates[si], si);
        if(!smallAns1.isEmpty()){
            for(List<Integer> list : smallAns1){
                list.add(candidates[si]);
                ans.add(list);
            }
        }
        List<List<Integer>> smallAns2 = combinationSum(candidates, target, si + 1);
        ans.addAll(smallAns2);

        return ans;
    }
}
