package misc;

public class NumberOf1Bits {

    // also called hamming weight problem
    public static void main(String[] args) {

        int n = 43261596;
        int count = 0;
        while(n != 0){
            int lsb = (n & 1);
            if(lsb == 1) count++;
            n = n >>> 1;
        }
        System.out.println(count);
    }
}
