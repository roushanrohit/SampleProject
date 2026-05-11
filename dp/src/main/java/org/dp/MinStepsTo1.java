package org.dp;

public class MinStepsTo1 {
    public static void main(String[] args) {

        int n = 1000;
        System.out.println("Min steps to 1 from " + n + " is : " + minStepsTo1Dp(n));
        System.out.println("Min steps to 1 from " + n + " is : " + minStepsTo1(n));
    }

    private static int minStepsTo1Dp(int n) {

        int[] storage = new int[n + 1];
        for(int i = 2; i <= n; i++){

            int op1 = storage[i - 1];
            int op2 = Integer.MAX_VALUE;
            if(i % 2 == 0) op2 = storage[i / 2];
            int op3 = Integer.MAX_VALUE;
            if(i % 3 == 0) op3 = storage[i / 3];

            storage[i] = 1 + Math.min(op1, Math.min(op2, op3));
        }

        return storage[n];
    }

    private static int minStepsTo1(int n) {

        if(n <= 1) return 0;

        int op1 = minStepsTo1(n - 1);
        int op2 = Integer.MAX_VALUE;
        if(n % 2 == 0) op2 = minStepsTo1(n / 2);
        int op3 = Integer.MAX_VALUE;
        if(n % 3 == 0) op3 = minStepsTo1(n / 3);

        return 1 + Math.min(op1, Math.min(op2, op3));
    }
}