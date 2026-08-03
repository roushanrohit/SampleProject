package misc;

public class Count1Bits {

    public static void main(String[] args) {

        int[] ans = count1Bits(10);
        for(int i : ans) System.out.print(i + " ");
    }

    /*
        For any integer i, the number of set bits in i equals the number of set bits in i >> 1
        (i.e., i with its last bit dropped), plus whatever that last bit itself was (i & 1).
     */
    public static int[] count1Bits(int n) {

        int[] ans = new int[n + 1];
        for(int i = 1; i <= n; i++){
            // i/2 is i >> 1 and i % 2 is i & 1
            ans[i] = ans[i >> 1] + (i & 1);
        }
        return ans;
    }
}
