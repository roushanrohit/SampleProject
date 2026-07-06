package misc;

/*
    There are n bulbs that are initially off. You first turn on all the bulbs, then you turn off every second bulb.
    On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on).
    For the ith round, you toggle every i bulb. For the nth round, you only toggle the last bulb.
    Return the number of bulbs that are on after n rounds.

    Hint:
    A number that is not a perfect square always has an even number of divisors.
    Reason:
    Divisors usually come in pairs.
    For example, for 12:
    1 × 12
    2 × 6
    3 × 4
    So the divisors are 1, 2, 3, 4, 6, 12 → 6 divisors (even). What happens with perfect squares?
    Take 36:
    1 × 36
    2 × 18
    3 × 12
    4 × 9
    6 × 6 ← middle divisor repeats
    Divisors: 1, 2, 3, 4, 6, 9, 12, 18, 36 → 9 divisors (odd).

    The number of perfect squares from 1 to n is simply: Math.sqrt(n)
 */
public class BulbSwitcher {

    public static void main(String[] args) {

        int n = 10;
        boolean[] bulbs = new boolean[n];
        for(int i = 1; i <= n; i++){
            // ith round
            for(int j = 1; j <= n; j++){
                if(j%i == 0){
                    // toggle the switch
                    bulbs[j-1] = !bulbs[j-1];
                }
            }
        }

        int count = 0;
        for(int i = 0; i < n; i++){
            if(bulbs[i]) count++;
        }

        System.out.println(count);

        // optimized -- only perfect squares have an odd number of divisors.
        System.out.println((int)Math.sqrt(n));
    }
}
