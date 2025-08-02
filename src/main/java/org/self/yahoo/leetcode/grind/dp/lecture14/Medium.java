package org.self.yahoo.leetcode.grind.dp.lecture14;

import java.util.Arrays;

public class Medium {
    public static void main(String[] args) {
        // Leet code: Subset sum equals target
        int [] nums = {2, 3, 1, 1};
        int target = 4;

        /*
            All problems related to subsequences or subsets are solved by splitting the recursion options into
                1. Take
                2. Not take

                Time complexity: O(2 ^ n):

                Space complexity: O(n): Auxiliary space for recursion stack
         */
        int n = nums.length;
        boolean result = testSubsetSumEqualsTargetRecursion(nums, n - 1, target);
        System.out.println("testSubsetSumEqualsTargetRecursion: " + result);

        Boolean[][] dp = new Boolean[n][target + 1];

        /*
            We need a 2-dimensional memoization dp[][] here to track the state of each possible combination of dp[index][sum]

            Time complexity: O(m * t): All possible computation combinations for dp[n][t]

            Space complexity: O(m * t): Memoization dp[m][t] array
                              O(n): Recursion stack space in worst case
         */
        result = testSubsetSumEqualsTargetMemoization(nums, n - 1, target, dp);
        System.out.println("testSubsetSumEqualsTargetMemoization: " + result);

        // TODO
//        result = testSubsetSumEqualsTargetTabulation(nums, n - 1, target);
//        System.out.println("testSubsetSumEqualsTargetTabulation: " + result);
    }



    private static boolean testSubsetSumEqualsTargetMemoization(int[] nums, int n, int target, Boolean[][] dp) {

        if (target == 0) {
            return true;
        }

        if (n == 0) {
            return nums[0] == target;
        }

        if (dp[n][target] != null) {
            return dp[n][target];
        }

        boolean notTake = testSubsetSumEqualsTargetMemoization(nums, n - 1, target, dp);

        boolean take = false;

        if (nums[n] < target) {
            take = testSubsetSumEqualsTargetMemoization(nums, n - 1, target - nums[n], dp);
        }
        dp[n][target] = take || notTake;
        return dp[n][target];
    }

    private static boolean testSubsetSumEqualsTargetRecursion(int[] nums, int n, int target) {

        if (target == 0) {
            return true;
        }

        if (n == 0) {
            return nums[0] == target;
        }

        boolean notTake = testSubsetSumEqualsTargetRecursion(nums, n - 1, target);

        boolean take = false;

        if (nums[n] < target) {
            take = testSubsetSumEqualsTargetRecursion(nums, n -1, target - nums[n]);
        }

        return take || notTake;

    }
}
