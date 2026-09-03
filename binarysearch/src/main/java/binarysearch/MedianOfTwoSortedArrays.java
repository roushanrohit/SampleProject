package binarysearch;

public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {

        int[] a = {1,2};
        int[] b = {3,4};
        System.out.println(medianOfSortedArrays(a, b));
    }

    private static double medianOfSortedArrays(int[] a, int[] b) {

        // Binary search on the smaller array
        if (a.length > b.length) {
            return medianOfSortedArrays(b, a);
        }

        /* n should represent the number of elements that must be on
           the left side of the partition across both arrays.
         */
        int n = (a.length + b.length + 1)/2;
        int left = 0;
        int right = a.length;
        while(left <= right){
            int i = (left + right) / 2;
            int j = n - i;

            int aLeft  = (i == 0) ? Integer.MIN_VALUE : a[i - 1];
            int aRight = (i == a.length) ? Integer.MAX_VALUE : a[i];

            int bLeft  = (j == 0) ? Integer.MIN_VALUE : b[j - 1];
            int bRight = (j == b.length) ? Integer.MAX_VALUE : b[j];

            if (aLeft > bRight) {
                right = i - 1;
            } else if (bLeft > aRight) {
                left = i + 1;
            } else {
                // Correct partition
                int maxLeft = Math.max(aLeft, bLeft);
                int minRight = Math.min(aRight, bRight);

                // Odd total length
                if ((a.length + b.length) % 2 == 1) {
                    return maxLeft;
                } else {
                    // Even total length
                    return (maxLeft + minRight) / 2.0;
                }
            }
        }
        return 0.0;
    }
}
