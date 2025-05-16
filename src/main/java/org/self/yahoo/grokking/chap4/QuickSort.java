package org.self.yahoo.grokking.chap4;

import java.util.Arrays;

public class QuickSort {

    private static int getPartition(int[] arr, int low, int high) {
        int x = low - 1;
        int pivot = arr[high];

        for (int i = low; i < high; i++) {
            // arr[i] > pivot : Sorts the array in descending order
            if (arr[i] < pivot) {
                x++;
                swap(arr, x, i);
            }
        }
        /*
            1. Before this final swap, the pivot is the only element which is not swapped.
            2. All elements less than the pivot is moved to the left of it
            3. x + 1 is currently pointing to the first element which is larger than the pivot
            4. x + 1 is the actual position where the pivot should be as all the element left of position x + 1 are smaller than it
         */
        swap(arr, x + 1, high);
        return x + 1;
    }

    private static void swap(int [] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    private static void testQuickSortV1(int[] arr, int low, int high) {
        if (low < high) {
            int partition = getPartition(arr, low, high);
            testQuickSortV1(arr, low, partition - 1);
            testQuickSortV1(arr, partition + 1, high);
        }
    }


    private static int getPartitionV2(int[] arr2, int low2, int high2) {
        int i = low2;
        int j = high2;
        int pivot = arr2[low2];

        while (i < j) {
            while (arr2[i] <= pivot && i < high2) {
                i++;
            }

            while (arr2[j] > pivot && j > low2) {
                j--;
            }

            if (i < j) {
                swap(arr2, i, j);
            }
        }
        // Final pivot swap
        swap(arr2, low2, j);
        return j;
    }

    private static void testQuickSortV2(int[] arr2, int low2, int high2) {
        if (low2 < high2) {
            int partition = getPartitionV2(arr2, low2, high2);
            testQuickSortV2(arr2, low2, partition - 1);
            testQuickSortV2(arr2, partition + 1, high2);
        }
    }

    public static void main(String[] args) {
        System.out.println("Quick Sort ....");

        /*
            Quick Sort : Using divide & conquer

            Time Complexity  :
                        O(n log n) : Avg case.
                        O(n ^ 2)   : Worst case when the array already sorted and the pivot chosen is the largest or smallest element
            Space complexity :
                        O(log n) : Avg case for the recursion depth
                        O(n)     : Deepest recursion depth in case of sorted arrays and Arrays having same numbers
         */
        int [] arr = {2, 1, 3, 5, 4};
        int low = 0;
        int high = arr.length - 1;
        // Uses the high as the pivot
        testQuickSortV1(arr, low, high );
        System.out.println("Sorted Array V1:" + Arrays.toString(arr));

        /*
            Time and Space complexity is the same as testQuickSortV1()
            Only difference in that the testQuickSortV2() implementation uses the arr[low] as the pivot
         */
        int [] arr2 = {2, 1, 3, 5, 4};
        int low2 = 0;
        int high2 = arr.length - 1;
        testQuickSortV2(arr2, low2, high2);
        System.out.println("Sorted Array V2:" + Arrays.toString(arr2));
    }
}
