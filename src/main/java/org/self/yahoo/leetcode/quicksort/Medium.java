package org.self.yahoo.leetcode.quicksort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Medium {

    private static int testKthLargestElementV1(int[] arr, int k, int low, int high) {
        quickSort(arr, low, high);
        System.out.println("Sorted Array: " + Arrays.toString(arr));
        return arr[k - 1];
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }

        int pivot = getPivotIndex(arr, low, high);
        quickSort(arr, low, pivot -1);
        quickSort(arr, pivot + 1, high);
    }

    private static  int getPivotIndex(int[] arr, int low, int high) {
        int i = low -1;
        int pivot = arr[high];

        for (int j = low; j < high; j++) {
            if (arr[j] > pivot) {
                i++;
                swapPosition(arr, i, j);
            }
        }
        swapPosition(arr, i + 1, high);
        return i + 1;
    }

    private static void swapPosition(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static int testKthLargestElementV2(int[] nums, int k) {
        if (nums.length == 1 && k ==1) {
            return nums[0];
        }
        Comparator<Integer> comparator = Comparator.reverseOrder();
        PriorityQueue<Integer> queue = new PriorityQueue<>(comparator);

        for (int i = 0; i< nums.length; i++) { // O(n)
            queue.add(nums[i]);
        }
        int result = 0;
        while (k > 0) {
            result = queue.poll(); // O(1)
            k--;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Quick sort: Medium....");
        // Leet code 215. Kth Largest Element in an Array
        int [] arr = new int[] {6, 5, 5, 5, 4, 4, 4, 3, 3, 2, 2, 1};
        int k = 4;
        /*
            Using Quick Sort
                Time complexity: Same as Quick sort
                                 O(n log n): Avg case
                                 O(n ^ 2): Worst case:When the input array is already sorted or NO random pivot is used
                                           Instead of using the first or last element as the pivot, a random pivot selection can avoid the O(n ^ 2) time complexity

                Space complexity: O(log n): Recursion stack split into two halves from the pivot point
                                            This avoids the recursion stack of depth n and avoid the space complexity of O(n)
         */
        int result = testKthLargestElementV1(arr, k, 0, arr.length -1);
        System.out.println("testKthLargestElementV1: " + result);

        /*
            Using a max heap via a Priority Queue

            Time complexity: O(n) : Loops through the nums[] once
                                    Adding each to PQ of size takes: O(log m). Total n elements takes : O(n log m) time
                                    Polling each element from PQ: O(log n) time. For k elements total time taken is O(k log n) time
                                    Overall the time complexity is O(n log n) + O(k log n) Since the value of K is small hence the overall
                                    time complexity is dominated by : O(n log n)

            Space complexity: O(n) : PQ used to store max n unique elements
         */
        result = testKthLargestElementV2(arr, k);
        System.out.println("testKthLargestElementV2: " + result);
    }




}
