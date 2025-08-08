package org.self.yahoo.leetcode.grind.dp.lecture41;

import java.util.Arrays;

public class Medium {
    public static void main(String[] args) {
        System.out.println("300. Longest Increasing Subsequence.. Medium ..");
        // Leet code 300. Longest Increasing Subsequence
        int index = 0;
        int prev = -1;
        int [] nums = {10,9,2,5,3,7,101,18};

        /*
            Time complexity: O(2 ^ n): Exponential time complexity

            Space complexity: Auxiliary space of O(n): Recursion stack
         */
        int result = testLongestIncreasingSubsequenceRecursion(nums, index, prev);
        System.out.println("testLongestIncreasingSubsequence: " + result);

        int n = nums.length;
        int [][] dp = new int[n][n + 1];
        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        result = testLongestIncreasingSubsequenceMemoization(nums, index, prev,  dp);
        System.out.println("testLongestIncreasingSubsequenceMemoization: " + result);
    }

    private static int testLongestIncreasingSubsequenceMemoization(int[] nums, int index, int prev, int[][] dp) {
        if (index == nums.length) {
            return 0;
        }

        if (dp[index][prev + 1] != Integer.MAX_VALUE) {
            return dp[index][prev + 1];
        }

        int notTake = testLongestIncreasingSubsequenceMemoization(nums, index + 1, prev, dp);

        int take = 0;

        if (prev == -1 || nums[index] > nums[prev]) {
            take = 1 + testLongestIncreasingSubsequenceMemoization(nums, index + 1, index, dp);
        }

        dp[index][prev + 1] = Math.max(take, notTake);

        return dp[index][prev + 1];
    }

    private static int testLongestIncreasingSubsequenceRecursion(int[] nums, int index, int prev) {

        if (index == nums.length) {
            return 0;
        }

        int notTake = testLongestIncreasingSubsequenceRecursion(nums, index + 1, prev);

        int take = 0;

        if (prev == -1 || nums[index] > nums[prev]) {
            take = 1 + testLongestIncreasingSubsequenceRecursion(nums, index + 1, index);
        }

        return Math.max(take, notTake);
    }
}
