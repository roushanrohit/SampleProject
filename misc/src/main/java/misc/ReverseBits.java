package misc;

// Reverse bits of a given 32 bits signed integer.
public class ReverseBits {

    public static void main(String[] args) {

        int n = 43261596;
        int res = 0, power = 31;
        while(n != 0){
            // extract the lsb and left shift power positions
            res += (n & 1) << power;
            // right shift n
            n = n >>> 1; // leftmost bit is filled with 0
            power -= 1;
        }
        System.out.println(res);
    }
}
