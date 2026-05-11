package org.dp;

import java.math.BigInteger;

public class BalancedBTForHeightH {

    public static void main(String[] args) {

        int h = 7;
        System.out.println("Number of balanced binary trees for height " + h + " is : " + balancedBTForHeightHDp(h));
        System.out.println("Number of balanced binary trees for height " + h + " is : " + balancedBTForHeightH(h));
    }

    private static BigInteger balancedBTForHeightHDp(int h) {

        BigInteger[] storage = new BigInteger[h + 1];
        storage[0] = BigInteger.ONE;
        storage[1] = BigInteger.ONE;

        for(int i = 2; i <= h; i++){
            BigInteger x = storage[i - 1];
            BigInteger y = storage[i - 2];
            BigInteger first = x.multiply(x);
            BigInteger second = y.multiply(x).multiply(BigInteger.TWO);
            storage[i] = first.add(second);
        }

        return storage[h];
    }

    private static BigInteger balancedBTForHeightH(int h) {

        if(h <= 1) return BigInteger.ONE;

        BigInteger x = balancedBTForHeightH(h - 1);
        BigInteger y = balancedBTForHeightH(h - 2);
        BigInteger first = x.multiply(x);
        BigInteger second = y.multiply(x).multiply(BigInteger.TWO);
        return first.add(second);
    }
}
