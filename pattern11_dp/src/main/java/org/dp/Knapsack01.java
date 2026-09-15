package org.dp;

class Result {
    int profit;
    String indices;
    Result(int profit, String indices){
        this.profit = profit;
        this.indices = indices;
    }
}

public class Knapsack01 {

    public static void main(String[] args) {

        int[] weights = new int[]{6,1,2,4,5};
        int[] values = new int[]{10,5,4,8,6};
        int bagCapacity = 5;
        System.out.println("Maximum profit: " + knapsack01(weights, values, bagCapacity));
        System.out.println("Maximum profit: " + knapsack01v2(weights, values, bagCapacity).profit);
        System.out.println("Indices chosen for maximum profit: " + knapsack01v2(weights, values, bagCapacity).indices);
        System.out.println("Maximum profit: " + knapsack01Dp(weights, values, bagCapacity).profit);
        System.out.println("Indices chosen for maximum profit: " + knapsack01Dp(weights, values, bagCapacity).indices);
    }

    private static Result knapsack01Dp(int[] weights, int[] values, int bagCapacity) {
        return new Result(0, "");
    }

    private static Result knapsack01v2(int[] weights, int[] values, int bagCapacity) {
        return knapsack01v2(weights, values, bagCapacity, 0);
    }

    private static Result knapsack01v2(int[] weights, int[] values, int bagCapacity, int i) {

        if(bagCapacity <= 0 || i >= weights.length) return new Result(0, "");

        if(weights[i] <= bagCapacity){
            Result op1 = knapsack01v2(weights, values, bagCapacity - weights[i], i + 1);
            op1.profit = values[i] + op1.profit;
            op1.indices = i + " " + op1.indices;
            Result op2 = knapsack01v2(weights, values, bagCapacity, i + 1);
            return (op1.profit > op2.profit) ? op1 : op2;
        } else {
            return knapsack01v2(weights, values, bagCapacity, i + 1);
        }
    }

    private static int knapsack01(int[] weights, int[] values, int bagCapacity) {
        return knapsack01(weights, values, bagCapacity, 0);
    }

    private static int knapsack01(int[] weights, int[] values, int bagCapacity, int i) {

        if(bagCapacity <= 0 || i >= weights.length) return 0;

        if(weights[i] <= bagCapacity){
            int op1 = values[i] + knapsack01(weights, values, bagCapacity - weights[i], i + 1);
            int op2 = knapsack01(weights, values, bagCapacity, i + 1);
            return Math.max(op1, op2);
        } else {
            return knapsack01(weights, values, bagCapacity, i + 1);
        }
    }
}
