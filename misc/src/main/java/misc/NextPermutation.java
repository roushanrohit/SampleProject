package misc;

public class NextPermutation {

    public static void main(String[] args) {

        int[] arr = {7, 5, 3, 2, 6, 9, 8, 4, 1};
        nextPermutation(arr);
        for(int num : arr){
            System.out.print(num);
        }
        System.out.println();
        int[] arr2 = {7, 5, 3, 2, 6, 9, 8, 4, 1};
        prevPermutation(arr2);
        for(int num : arr2){
            System.out.print(num);
        }
    }

    /*
         1 2 3 4 5 --> the smallest number with digits 1,2,3,4,5
         5 4 3 2 1 --> the largest number with digits 1,2,3,4,5
         Here we have to find the next greater number, we'll scan the array from the back
         As long as we are observing an increasing sequence, we cannot make the number larger
         When we get a number which is smaller than the previous number we encountered(called 'pivot'),
         we have to replace it with a number just greater -- which is the first number greater than the pivot
         when we scan from the last.

         Point to note is after you swap the pivot with the just greater number, numbers after the pivot index
         are still in decreasing order. We need to reverse it to get the smallest number possible with those digits.
     */
    private static void nextPermutation(int[] arr) {

        int n = arr.length;
        int i = n - 2;
        while(i >= 0 && arr[i] >= arr[i + 1]){
            i--;
        }
        // if we find the pivot
        if(i >= 0){
            int j = n - 1;
            while(arr[j] <= arr[i]){
                j--;
            }
            swap(arr, i, j);
        }
        // now, we have to reverse the numbers from index i + 1
        reverse(arr, i + 1, n - 1);
    }

    /*
         1 2 3 4 5 --> the smallest number with digits 1,2,3,4,5
         5 4 3 2 1 --> the largest number with digits 1,2,3,4,5
         Here we have to find the prev smaller number, we'll scan the array from the back
         As long as we are observing a decreasing sequence, we cannot make the number smaller
         When we get a number which is greater than the previous number we encountered(called 'pivot'),
         we have to replace it with a number just smaller -- which is the first number smaller than the pivot
         when we scan from the last.

         Point to note is after you swap the pivot with the just smaller number, numbers after the pivot index
         are still in increasing order. We need to reverse it to get the largest number possible with those digits.
     */
    private static void prevPermutation(int[] arr) {

        int n = arr.length;
        int i = n - 2;
        while(i >= 0 && arr[i] <= arr[i + 1]){
            i--;
        }
        // if we find the pivot
        if(i >= 0){
            int j = n - 1;
            while(arr[j] >= arr[i]){
                j--;
            }
            swap(arr, i, j);
        }
        // now, we have to reverse the numbers from index i + 1
        reverse(arr, i + 1, n - 1);
    }

    public static void reverse(int[] arr, int left, int right){
        while(left < right){
            swap(arr, left, right);
            left++;
            right--;
        }
    }

    public static void swap(int[] arr, int left, int right){
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
}
