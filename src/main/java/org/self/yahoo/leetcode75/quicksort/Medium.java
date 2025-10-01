package org.self.yahoo.leetcode75.quicksort;

import java.util.Arrays;

public class Medium {
    public static void main(String[] args) {
        System.out.println("Quick Sort:....Medium....");
        // int [] nums = {2,0,2 ,1,1,0};
        int [] nums = {10, 80, 30, 90, 40, 50, 70};
        int low = 0;
        int high = nums.length - 1;

        /*
            Time complexity: O(n log n)
                             Avg case: O(n log n)
                             Worst case: O(n ^ 2): If the inout array is already sorted and chosen pivot is either the first or last arr [] element

            Space complexity: O(n log n): Recursion stack depth
         */
        testQuickSort(nums, low, high);
        System.out.println("testQuickSort: " + Arrays.toString(nums));
    }

    private static void testQuickSort(int[] nums, int low, int high) {

        if (low >= high) {
            return;
        }

        int pivot = getPivot(nums, low, high);
        testQuickSort(nums, low, pivot - 1);
        testQuickSort(nums, pivot + 1, high);
    }

    private static int getPivot(int[] nums, int low, int high) {
        int i = low - 1;
        int pivot = nums[high];

        for (int j = low; j < high; j++) {
            if (nums[j] < pivot) {
                i++;
                swap(nums, i , j);
            }
        }
        swap(nums, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }
}




//class Solution {
//    public void sortColors(int[] nums) {
//
//        int low = 0;
//        int high = nums.length - 1;
//
//        quickSort(nums, low, high);
//
//    }
//
//    private void quickSort(int [] nums, int low, int high) {
//        if (low >= high) {
//            return;
//        }
//
//        int pivot = getPivot(nums, low, high);
//        quickSort(nums, low, pivot - 1);
//        quickSort(nums, pivot + 1, high);
//    }
//
//    private int getPivot(int [] nums, int low, int high) {
//        int i = low -1;
//        int pivot = nums[high];
//
//        for (int j = low; j < high; j++) {
//            if (nums[j] < pivot) {
//                i++;
//                swap(nums, i, j);
//            }
//        }
//        swap(nums, i + 1, high);
//        return i + 1;
//    }
//
//    private void swap(int []nums, int i, int j) {
//        int tmp = nums[i];
//        nums[i] = nums[j];
//        nums[j] = tmp;
//    }
//}