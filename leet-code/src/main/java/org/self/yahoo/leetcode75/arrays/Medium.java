package org.self.yahoo.leetcode75.arrays;

import java.util.Arrays;

public class Medium {

    private static int[] testProductOfArrayExceptSelf(int[] nums) {
        int n = nums.length;;
        int [] result = new int[n];
        int [] left = new int[n];
        int [] right = new int[n];

        left[0] = 1;
        right[n - 1] = 1;

        int leftTotal = 1;
        int rightTotal = 1;

        for (int i = 1; i < n; i++) {
            leftTotal *= nums[i - 1];
            left[i] = leftTotal;
        }

        for (int i = n - 2; i >= 0; i--) {
            rightTotal *= nums[i + 1];
            right[i] = rightTotal;
        }

        for (int i = 0; i < n; i++) {
            result[i] = left[i] * right[i];
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("Arrays ..medium..");
        // Leet code: 238. Product of Array Except Self
        int [] nums = {1,2,3,4};
        /*
            Time complexity: O(3n) => O(n): Performs 3 iterations of nums[] length

            Space complexity: O(3n) => O(n): Extra compute space required to store result[], right[] and left[]
         */
        int [] result = testProductOfArrayExceptSelf(nums);
        System.out.println("testProductOfArrayExceptSelf: " + Arrays.toString(result));
    }


}
