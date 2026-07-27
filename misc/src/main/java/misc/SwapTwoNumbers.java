package misc;

/*
    swap two numbers without using the third variable
    trick is to use XOR, X^X = 0 and X^0 = X
    lets say the numbers are a and b:
    a = a^b
    b = a^b = (a^b)^b = a^(b^b) = a^0 = a
    a = a^b = (a^b)^a = (a^a)^b = 0^b = b
 */
public class SwapTwoNumbers {

    public static void main(String[] args) {

        int a = 5;
        int b = 3;
        System.out.println("Original values of a: " + a + " and b: " + b);
        a = a^b;
        b = a^b;
        a = a^b;
        System.out.println("Swapped values of a: " + a + " and b: " + b);
    }
}
