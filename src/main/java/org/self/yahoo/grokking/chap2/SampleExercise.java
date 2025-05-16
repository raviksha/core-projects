package org.self.yahoo.grokking.chap2;

import java.util.Arrays;

public class SampleExercise {

    /*
        Time complexity:

        Space complexity:
     */
    private static void testSelectionSort(int[] arr) {
        if (arr != null && arr.length == 0) {
            System.out.println("Empty array...");
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            int smallest = arr[i];
            int swapIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < smallest) {
                    smallest = arr[j];
                    swapIndex = j;
                }
            }
            int tmp = arr[i];
            arr[i] = smallest;
            arr[swapIndex] = tmp;
        }

        System.out.println("Sorted Array: " + Arrays.toString(arr));
    }
    public static void main(String[] args) {
        System.out.println("SampleExercise..");

        /*
            Selection Sort:
            Selection sorts works on the concept of :
                1. Start with the first element in the array : i = 0
                2. Navigate through the array to find the number which is the smallest from the current selected element
                3. Swap the positions and value of the current element with that of the smallest one in the current iteration
                4. By the end of the first iteration the first position will have the smallest element in the whole array
                4. Repeat the above steps starting from the i + 1 index
                5. At the end when i = n, the array would have elements sorted

             Time complexity : O(n^2) : Loop is repeated n times for each n elements of the array
             Space complexity : O(1)  : No extra space required to do the in place sorting
         */
        int [] arr = new int [] {64, 25, 12, 22, 11};
        testSelectionSort(arr);
    }
}
