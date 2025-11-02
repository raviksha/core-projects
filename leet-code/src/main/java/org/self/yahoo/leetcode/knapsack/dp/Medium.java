package org.self.yahoo.leetcode.knapsack.dp;

import java.util.Arrays;

public class Medium {
    public static void main(String[] args) {
        System.out.println("Knapsack DP .. Medium ...");

        // Leet code 416. Partition Equal Subset Sum
        int [] nums = {1,5,11,5};

        /*
            Approach: Using the DP approach for SubSet sum equals target || Identify DP on subsequences

            Time complexity: O(n * t): Not exponential coz of memoization

            Space complexity: O(n * m) dp[][] memoization arr + recursion stack depth O(n)
                              Final s/c: O(n * m)
         */
        boolean result = testPartitionEqualSubSetSumMemoization(nums);
        System.out.println("testPartitionEqualSubSetSumMemoization: " + result);
    }

    private static boolean testPartitionEqualSubSetSumMemoization(int[] nums) {
        int n = nums.length;
        int target = 0;

        for (int i : nums) {
            target += i;
        }

        if (target % 2 != 0) {
            return false;
        }
        target = target / 2;
        Boolean [] [] dp = new Boolean[n][target + 1];

        return testPartitionEqualSubSetSumMemoization(nums, target, dp, n -1);
    }

    private static boolean testPartitionEqualSubSetSumMemoization(int[] nums, int target, Boolean[][] dp, int n) {
        if (target == 0) {
            return true;
        }

        if (n == 0) {
            return nums[0] == target;
        }

        if (dp[n][target] != null) {
            return dp[n][target];
        }

        boolean notChoose = testPartitionEqualSubSetSumMemoization(nums, target, dp, n - 1);

        boolean choose = false;

        if (nums[n] < target) {
            choose = testPartitionEqualSubSetSumMemoization(nums, target - nums[n], dp, n -1);
        }
        dp[n][target] = choose || notChoose;
        return dp[n][target];
    }
}
