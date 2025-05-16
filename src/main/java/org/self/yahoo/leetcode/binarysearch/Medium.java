package org.self.yahoo.leetcode.binarysearch;

import java.util.Arrays;

public class Medium {
    public static void main(String[] args) {
        System.out.println("Binary search Medium.....");
        // Leet code 34. Find First and Last Position of Element in Sorted Array
        int [] arr = new int[] {5,7,7,8,8,10};
        int target = 8;
        /*
            Using two binary search approach

            Time complexity : O(log n): Time complexity for binary search

            Space complexity: O(1): No extra space required
         */
        var resp = testElementPosition(arr, target);
        System.out.println("testElementPosition: " + Arrays.toString(resp));

    }

    private static int [] testElementPosition(int[] arr, int target) {
        int leftIndex = leftBound(arr, target);
        int rightIndex = rightBound(arr, target);
        return new int [] {leftIndex, rightIndex};
    }

    private static int rightBound(int[] nums, int target) {
        int index = -1;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                index = mid;
                left = mid + 1;
            }

            if (nums[mid] > target) {
                right = right -1;
            } else {
                left = mid + 1;
            }
        }
        return index;
    }

    private static int leftBound(int[] nums, int target) {
        int index = -1;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) /2;

            if (nums[mid] == target) {
                index = mid;
                right = mid -1;
            }

            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return index;
    }
}
