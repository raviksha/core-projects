package org.self.yahoo.leetcode75.bsearch;

import java.util.Arrays;

public class Medium {
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
    }

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


}



//class Solution {
//    public int[] searchRange(int[] nums, int target) {
//        int leftIndex = leftBound(nums, target);
//        int rightIndex = rightBound(nums, target);
//        return new int [] {leftIndex, rightIndex};
//
//    }
//
//    private int leftBound(int [] nums, int target) {
//        int index = -1;
//        int left = 0;
//        int right = nums.length - 1;
//
//        while (left <= right) {
//            int mid = left + (right - left) /2;
//
//            if (nums[mid] == target) {
//                index = mid;
//                right = mid -1;
//            }
//
//            if (nums[mid] < target) {
//                left = mid + 1;
//            } else {
//                right = mid - 1;
//            }
//        }
//        return index;
//    }
//
//    private int rightBound(int [] nums, int target) {
//        int index = -1;
//        int left = 0;
//        int right = nums.length - 1;
//
//        while (left <= right) {
//            int mid = left + (right - left) / 2;
//
//            if (nums[mid] == target) {
//                index = mid;
//                left = mid + 1;
//            }
//
//            if (nums[mid] > target) {
//                right = right -1;
//            } else {
//                left = mid + 1;
//            }
//        }
//        return index;
//    }
//}
