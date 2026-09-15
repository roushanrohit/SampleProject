package org.dp;

public class MinCountOfSquares {

    public static void main(String[] args) {

        int n = 100;
        System.out.println("Minimum count of squares to reach " + n + " is " + minCountOfSquaresDp(n));
        System.out.println("Minimum count of squares to reach " + n + " is " + minCountOfSquares(n));
    }

    private static int minCountOfSquaresDp(int n) {

        int[] storage = new int[n + 1];
        storage[1] = 1;
        storage[2] = 2;
        storage[3] = 3;

        for(int i = 4; i <= n; i++){
            int min = n;
            for(int j = 1; j * j <= n; j++){
                int temp = storage[n - j * j];
                if(1 + temp < min) min = 1 + temp;
            }
            storage[i] = min;
        }

        return storage[n];
    }

    private static int minCountOfSquares(int n) {

        if(n <= 3) return n;

        int min = n;
        for(int i = 1; i * i <= n; i++){
            int temp = minCountOfSquares(n - i * i);
            if(1 + temp < min) min = 1 + temp;
        }

        return min;
    }
}
