package misc;

/*
    Given two integers a and b, return the sum of the two integers without
    using the operators + and -.
 */
public class SumOfTwoIntegers {

    public static void main(String[] args) {

        int a = 3;
        int b = 9;
        while(b != 0){
            int temp = (a & b) << 1;
            a = a ^ b;
            b = temp;
        }
        System.out.print(a);
    }
}
