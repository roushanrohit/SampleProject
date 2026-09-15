package org.dp;

/*
    For the given infinite supply of coins of each of the denominations:
    D = {D0, D1, D2, D3 ....... Dn-1}
    Figure out the total number of ways(W) in which you can make a change of value(V)
    eg:
       coins: {1,2,3}
       V = 5
       (1,1,1,1,1),(1,1,1,2),(1,2,2),(1,1,3),(2,3) -- W = 5
 */
public class CoinChange {

    public static void main(String[] args) {

        int[] coins = {1,2,3};
        int amount = 5;
        System.out.println("Number of ways to make change for " + amount + " : " + countWaysDP(coins, amount));
    }

    private static int countWays(int[] coins, int amount) {
        return countWays(coins, amount, 0);
    }

    private static int countWays(int[] coins, int amount, int i) {

        // base case
        if(amount == 0) return 1;
        if(i == coins.length || amount < 0) return 0;

        if(coins[i] > amount){
            return countWays(coins, amount, i + 1);
        } else {
            int op1 = countWays(coins, amount, i + 1);
            int op2 = countWays(coins, amount - coins[i], i);
            return op1 + op2;
        }
    }

    private static int countWaysDP(int[] coins, int amount) {

        int[][] storage = new int[coins.length][amount + 1];
        for(int i = 0; i < coins.length; i++){
            storage[i][0] = 1;
        }
        for(int i = 1; i <= amount; i++){
            if(i >= coins[0] && i % coins[0] == 0){
                storage[0][i] = 1;
            }
        }

        for(int i = 1; i < coins.length; i++){
            for(int j = 1; j <= amount; j++){
                if(j < coins[i]){
                    storage[i][j] = storage[i-1][j];
                } else {
                    int op1 = storage[i-1][j]; // exclude this coin
                    int op2 = storage[i][j - coins[i]]; // include this coin
                    storage[i][j] = op1 + op2;
                }
            }
        }

        return storage[coins.length - 1][amount];
    }
}
