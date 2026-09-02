package binarysearch;

public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {

        int[] a = {1, 2, 3, 10};
        int[] b = {4, 5, 6, 7};
        int n = a.length;
        System.out.println(medianOfSortedArrays(a, b, n));
    }

    private static double medianOfSortedArrays(int[] a, int[] b, int n) {

        int left = 0;
        int right = n;
        while(left <= right){
            int i = (left + right)/2;
            int j = n - i;
            if(a[i - 1] > b[j]){
                right = i - 1;
            } else if(b[j - 1] > a[i]){
                left = i + 1;
            } else {
                // partition is correct
                int max = Math.max(a[i - 1], b[j - 1]);
                int min = Math.min(a[i], b[j]);
                return (min + max)/2.0;
            }
        }
        return 0.0;
    }
}
