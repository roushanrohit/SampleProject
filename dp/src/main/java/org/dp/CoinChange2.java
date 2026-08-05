package org.dp;

/*
    You are given an integer array coins representing coins of different denominations and an integer amount
    representing a total amount of money.
    Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be
    made up by any combination of the coins, return -1.
    You may assume that you have an infinite number of each kind of coin.
 */
public class CoinChange2 {

    public static void main(String[] args) {
        int[] coins = {1,2,3};
        int amount = 5;
        System.out.println("Minimum number of ways to make change for " + amount + " : " + minCoinsDP(coins, amount));
    }

    private static int minCoins(int[] coins, int amount) {
        return minCoins(coins, amount, 0);
    }

    private static int minCoins(int[] coins, int amount, int i) {
        if(amount == 0) return 0;
        if(i == coins.length || amount < 0) return -1;

        int op1 = minCoins(coins, amount, i + 1); // exclude this coin -- at ith position
        int op2 = minCoins(coins, amount - coins[i], i); // include this coin -- at ith position
        if(op2 != -1) op2 += 1;
        if(op1 == -1) return op2;
        if(op2 == -1) return op1;

        return Math.min(op1, op2);
    }

    private static int minCoinsDP(int[] coins, int amount) {
        int[][] storage = new int[coins.length][amount + 1];
        for(int i = 0; i < coins.length; i++){
            storage[i][0] = 0; // for amount 0, no of coins required is also 0
        }
        for(int i = 1; i <= amount; i++){
            if(i >= coins[0] && i % coins[0] == 0){
                storage[0][i] = i / coins[0];
            } else {
                storage[0][i] = -1;
            }
        }

        for(int i = 1; i < coins.length; i++){
            for(int j = 1; j <= amount; j++){
                if(j < coins[i]){
                    storage[i][j] = storage[i-1][j];
                } else {
                    int op1 = storage[i-1][j]; // exclude this coin
                    int op2 = storage[i][j - coins[i]]; // include this coin
                    if(op2 != -1) op2 += 1;
                    if(op1 == -1) storage[i][j] = op2;
                    else if(op2 == -1) storage[i][j] = op1;
                    else storage[i][j] = Math.min(op1, op2);
                }
            }
        }

        return storage[coins.length - 1][amount];
    }
}
