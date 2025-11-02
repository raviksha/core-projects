package org.self.yahoo.leetcode75.bsearch;

import java.util.Arrays;

public class Medium {


    private static int [] testFirstLastPosition(int[] nums, int target) {
        int leftBeginIndex =  getLeftBound(nums, target);
        int rightEndIndex = getRightBound(nums, target);
        return new int [] {leftBeginIndex, rightEndIndex};
    }

    private static int getRightBound(int[] nums, int target) {
        int index = -1;
        int low = 0;
        int high = nums.length - 1;

        while (!(low > high)) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                index = mid;
                low = mid + 1;
            }

            if (target > nums[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return index;
    }

    private static int getLeftBound(int[] nums, int target) {
        int index = -1;
        int low = 0;
        int high = nums.length - 1;

        while (!(low > high)) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                index = mid;
                high = mid - 1;
            }

            if (target > nums[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        System.out.println("Binary search .. Medium");
        // Leet code 34. Find First and Last Position of Element in Sorted Array
        int [] nums = {5,7,7,8,8,101};
        int target = 8;

        /*
            Time complexity: O(log n) 2 Binary search across the nums[] on left and right halves

            Space complexity:O(1): No extra space required
         */
        int [] result = testFirstLastPosition(nums, target);
        System.out.println("testFirstLastPosition: " + Arrays.toString(result));

        // Leet code 33. Search in Rotated Sorted Array
        nums = new int[] {4,5,6,7,0,1,2};
        target = 0;

        /*
            Time complexity: 2 Binary searches if left anf right half each
                             O(2 log n) => O(log n)

            Space complexity: O(1) No extra storage space required
         */
        int targetIndex = testSearchInRotatedArray(nums, target);
        System.out.println("testSearchInRotatedArray: " + targetIndex);
    }

    private static int testSearchInRotatedArray(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // Check is left or right part of the array is sorted

            if (nums[low] < nums[mid]) {
                // Left part is sorted

                if (nums[low] <= target && target <= nums[mid]) {
                    // Target exists in the left sorted half
                    high = mid - 1;
                } else {
                    // Target exists in right unsorted half
                    low = mid + 1;
                }

            } else {
                // Right part is sorted
                if (nums[mid] <= target && target <= nums[high]) {
                    // Target exists in right sorted part
                    low = mid + 1;
                } else {
                    // Target exists in left unsorted part
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
}

