package org.self.yahoo.grokking.chap4;

import java.util.Arrays;

public class MergeSort {

    private static void merge(int[] arr, int low, int mid, int high) {
        int [] tmp = new int[high - low + 1];
        int leftIndex = low;
        int rightIndex = mid + 1;
        int counter = 0;

        while (leftIndex <= mid && rightIndex <=high) {

            if (arr[leftIndex] < arr[rightIndex]) {
                tmp[counter] = arr[leftIndex];
                leftIndex++;
            } else {
                tmp[counter] = arr[rightIndex];
                rightIndex++;
            }
            counter++;
        }

        // Add the remaining elements from the sorted left half
        while (leftIndex <= mid) {
            tmp[counter] = arr[leftIndex];
            leftIndex++;
            counter++;
        }

        // Add the remaining elements from sorted right half
        while (rightIndex <= high) {
            tmp[counter] = arr[rightIndex];
            counter++;
            rightIndex++;
        }

        // Update the original array with the contents of tmp array
        for (int i = 0, j = low; i < counter; i++, j++) {
            arr[j] = tmp[i];
        }
    }

    private static void testMergeSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = low + (high - low) / 2;
        // Keeps calling recursively on the left till the split array size is 1.
        // Starts tracking back the recursion call after that to the right of the left call stack
        testMergeSort(arr, low, mid);

        // Splits the right portion of the arrays beginning from the mid
        testMergeSort(arr, mid + 1, high);

        // Merge begins from the tail elements of the left recursion and moving up 1 recursion stack height at a time
        // Sorting is done while merging
        merge(arr, low, mid, high);
    }

    public static void main(String[] args) {
        System.out.println("MergeSort....");
        int [] arr  = {3, 2, 4, 1, 3, 11, 0};
        int low = 0;
        int high = arr.length -1;

        /*
            Time Complexity: O(n log n) : Avg and worst case
                             Unlike Quick sort the time complexity does not change when the array is already sorted

            Space complexity : O(n) : Extra tmp storage required for saving the sorted array from each recursion stack

            Uses Divide and Conquer by breaking the problem into the smallest unit and then applying recursion on top of it
         */

        testMergeSort(arr, low, high);
        System.out.println("Merge Sort: " + Arrays.toString(arr));
    }
}
