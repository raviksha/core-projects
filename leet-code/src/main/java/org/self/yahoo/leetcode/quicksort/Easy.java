package org.self.yahoo.leetcode.quicksort;

import java.util.Arrays;

public class Easy {
    public static void main(String[] args) {
        System.out.println("Quick sort: easy..");
        // Leet code 75. Sort Colors
        int[] array = {10, 7, 8, 9, 1, 5};
        /*
            Quick sort using in place swapping

            Time complexity: O(n log n) : Avg case
                             O(n ^ 2): Worst case : Array is already sorted or NO random pivot is used.
                                                    Instead of using the first or last element as a pivot, using a random pivot avoid the
                                                    worst time complexity of O(n ^ 2)

             Space complexity: O(log n) : Recursion stack is split into 2 halves from the pivot point
                                          That avoids the recursion stack of depth n and settles at O(log n)

         */
        testQuickSort(array, 0, 5);
        System.out.println("Quick sort: " + Arrays.toString(array));
    }

    private static void testQuickSort(int[] array, int low, int high) {
        if (low >= high) {
            return;
        }
        int pivot = getPivot(array, low, high);
        testQuickSort(array, low, pivot - 1);
        testQuickSort(array, pivot + 1, high);
    }

    private static int getPivot(int[] array, int low, int high) {
        int i = low -1;
        int pivot = array[high];
        for (int j = low; j < high ; j++) {
            if (array[j] <= pivot) {
                i++;
                swapElements(array, i, j);
            }
        }
        swapElements(array, i + 1, high);
        return i + 1;
    }

    private static void swapElements(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
