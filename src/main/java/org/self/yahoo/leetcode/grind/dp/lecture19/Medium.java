package org.self.yahoo.leetcode.grind.dp.lecture19;

import java.util.Arrays;

public class Medium {
    public static void main(String[] args) {
        /*
            DP on subsequences works on the recursion tree of options: pick and non pick
         */
        System.out.println("O1 Knapsack: DP on subsequences....");

        int [] weight = {3, 2, 5};
        int [] value = {30, 40, 50};

        int n = weight.length;
        int bw = 6;

        /*
            DP approach is to traverse through all the possible combination of between the weight and value and pick the maximum

            Time complexity: (2 ^ n): Exponential time complexity

            Space complexity: O(n): Recursion stack depth
         */
        int maxRobValue = testKnapsackWithRecursion(weight, value, bw, n - 1);
        System.out.println("testKnapsackWithRecursion: " + maxRobValue);

        int [][] dp = new int[n][bw + 1];
        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }

        /*
            Approach is using Memoization on DP

            Time complexity: O(w * v) weight * values
            Space complexity: O(w * v): dp[][] memoization array + O(w): Max recursion stack
         */
        maxRobValue = testKnapsackWithMemoization(weight, value, bw, n - 1, dp);
        System.out.println("testKnapsackWithMemoization: " + maxRobValue);

    }



    private static int testKnapsackWithMemoization(int[] weight, int[] value, int bw, int n, int[][] dp) {
        if (n == 0) {
            if (weight[0] <= bw) {
                return value[0];
            } else {
                return 0;
            }
        }

        if (dp[n][bw] != Integer.MAX_VALUE) {
            return dp[n][bw];
        }

        int notPick = testKnapsackWithMemoization(weight, value, bw, n - 1, dp);

        int pick = Integer.MIN_VALUE;

        if (bw >= weight[n]) {
            pick = value[n] + testKnapsackWithMemoization(weight, value, bw - weight[n], n - 1, dp);
        }
        dp[n][bw] = Math.max(pick, notPick);
        return dp[n][bw];
    }


    private static int testKnapsackWithRecursion(int[] weight, int[] value, int bw, int n) {

        if (n == 0) {
            if (weight[0] <= bw) {
                return value[0];
            } else {
                return 0;
            }
        }

        int notPick = 0 + testKnapsackWithRecursion(weight, value, bw, n - 1);
        int pick = Integer.MIN_VALUE;

        if (weight[n] <= bw) {
            pick = value[n] + testKnapsackWithRecursion(weight, value, bw - weight[n], n - 1);
        }
        return Math.max(pick, notPick);
    }
}
