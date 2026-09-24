package binarysearch;

public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {

        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};    // m = 10
        int[] b = {11, 12};                    // n = 2
        System.out.println(medianOfSortedArrays(a, b));
    }

    private static double medianOfSortedArrays(int[] a, int[] b){

        int m = a.length;
        int n = b.length;
        int leftSize = (m + n + 1)/2;

        int left = Math.max(0, leftSize - n);
        int right = Math.min(leftSize, m);
        while(left <= right){
            int i = (left + right)/2;
            int j = leftSize - i;
            int aLeft = (i == 0) ? Integer.MIN_VALUE : a[i - 1];
            int aRight = (i == m) ? Integer.MAX_VALUE : a[i];
            int bLeft = (j == 0) ? Integer.MIN_VALUE : b[j - 1];
            int bRight = (j == n) ? Integer.MAX_VALUE : b[j];
            // check validity
            if(aLeft > bRight){
                right = i - 1;
            } else if(bLeft > aRight){
                left = i + 1;
            } else {
                // valid pair
                if((m + n) % 2 == 0){
                    return (Math.max(aLeft, bLeft) + Math.min(aRight, bRight))/2.0;
                } else {
                    return Math.max(aLeft, bLeft);
                }
            }
        }
        return 0.0;
    }
}
