package org.self.yahoo.leetcode.grind.dp.lecture21;

import java.util.HashMap;
import java.util.Map;

public class Medium {
    public static void main(String[] args) {
        // Leet code 494. Target Sum
        System.out.println("DP 21. Target Sum | DP on Subsequences");

        int [] nums = {1,1,1,1,1};
        int target = 3;
        int n = nums.length;

        int currentSum = 0;

        /*
            Approach: Using recursion

            Time complexity: O(2 ^ n): Exponential time complexity

            Space complexity: O(n): Recursion stack

         */

        int numWays = testTargetSumRecursion(nums, target, n - 1, currentSum);
        System.out.println("testTargetSumRecursion: " + numWays);


        Map<String, Integer> dp = new HashMap<>();

        /*
            Time complexity: O(n * t): Not exponential

            Space complexity: O(n * t): Memoization Map + O(n): Recursion stack
                              Final: O(n * t)
         */
        numWays = testTargetSumMemoization(nums, target, n - 1, currentSum, dp);
        System.out.println("testTargetSumMemoization: " + numWays);
    }

    private static int testTargetSumMemoization(int[] nums, int target, int n, int currentSum, Map<String, Integer> dp) {
        if (n < 0) {
            return target == currentSum ? 1 : 0;
        }
        String key = n + "," + currentSum;

        if (dp.containsKey(key)) {
            return dp.get(key);
        }

        int add = testTargetSumMemoization(nums, target, n - 1, currentSum + nums[n], dp);
        int subtract = testTargetSumMemoization(nums, target, n - 1, currentSum - nums[n], dp);

        dp.put(key, (add + subtract));
        return dp.get(key);
    }

    private static int testTargetSumRecursion(int[] nums, int target, int n, int currentSum) {
        if (n < 0) {
            return target == currentSum ? 1: 0;
        }

        int add = testTargetSumRecursion(nums, target, n - 1, currentSum + nums[n]);
        int subtract = testTargetSumRecursion(nums, target, n -1, currentSum - nums[n]);

        return add  + subtract;

    }
}
