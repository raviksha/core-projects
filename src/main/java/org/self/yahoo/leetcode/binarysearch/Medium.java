package org.self.yahoo.leetcode.binarysearch;

import java.util.Arrays;

public class Medium {

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

    private static int testFindPeakElementV1(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        for (int i = 0; i< nums.length - 1; i++) {
            if (i == 0) {
                if (nums[i] > nums[i + 1]) {
                    return i;
                }
            } else {
                int prev = nums [i - 1];
                int current = nums[i];
                int next = nums[i + 1];

                if ((current > prev) && (current > next)) {
                    return i;
                }
            }

        }
        return nums.length - 1;
    }

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

        // Leet code 162. Find Peak Element
        //int [] nums = {1,5,1,2,1};
         int [] nums = {1,2,3,1};
        //int [] nums = {1};
        /*

         */
        int peakElement = testFindPeakElementV1(nums);
        System.out.println("testFindPeakElementV1: " + peakElement);

        peakElement = testFindPeakElementV2(nums);
        System.out.println("testFindPeakElementV2: " + peakElement);

        peakElement = testFindPeakElementV3(nums);
        System.out.println("testFindPeakElementV3: " + peakElement);

    }

    private static int testFindPeakElementV2(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }

        if (nums[0] > nums[1]) {
            return 0;
        }

        if (nums[nums.length - 1] > nums[nums.length - 2]) {
            return nums.length -1;
        }

        int low = 1;
        int high = nums.length - 2;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] > nums[mid -1] && nums[mid] > nums[mid + 1]) {
                return mid;
            } else if (nums[mid] > nums[mid -1]){
                low = mid + 1;
            } else {
                high = mid -1;
            }
        }
        return 0;
    }


    private static int testFindPeakElementV3(int[] nums) {
        int low = 0;
        int high = nums.length -1 ;
        int size = nums.length;

        if (nums.length == 1) {
            return 0;
        }

        if (nums[0] > nums[1]) {
            return 0;
        }

        if (nums[size -1] > nums[size -2]) {
            return size -1;
        }

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] > nums[mid + 1]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
