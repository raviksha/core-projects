package org.self.yahoo.leetcode.grind.dp.lecture23;

import java.util.Arrays;

public class Medium {
    public static void main(String[] args) {
        // Unbounded Knapsack: DP 23. Unbounded Knapsack

        int [] weight = {2, 4, 7, 3};
        int [] values = {20, 3, 90, 100};
        int n = weight.length;

        int bagWeight = 6;

        /*
            Time complexity: O(>>>2 ^ n): Exponential

            Space complexity: O(n): Recursion stack depth
         */
        int maxWeight = testDPUnboundedKnapsackRecursion(weight, values, bagWeight, n - 1);
        System.out.println("testDPUnboundedKnapsackRecursion: " + maxWeight);

        int [][] dp = new int[n][bagWeight + 1];
        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }

        /*
            Time complexity: O((n * t): Number of weights * bagTarget
            Space complexity: O(n * t): Memoization dp[][] + O(t): Recursion stack depth of target weight
         */
        maxWeight = testDPUnboundedKnapsackMemoization(weight, values, bagWeight, n - 1, dp);
        System.out.println("testDPUnboundedKnapsackMemoization: " + maxWeight);
    }

    private static int testDPUnboundedKnapsackMemoization(int[] weight, int[] values, int bagWeight, int n, int[][] dp) {

        if (n ==0) {
            if (bagWeight >= weight[0]) {
                return (bagWeight / weight[0]) * values[0];
            } else {
                return 0;
            }
        }

        if (dp[n][bagWeight] != Integer.MAX_VALUE) {
            return dp[n][bagWeight];
        }

        int notTake = testDPUnboundedKnapsackMemoization(weight, values, bagWeight, n -1, dp);

        int take = Integer.MIN_VALUE;

        if (bagWeight >= weight[n]) {
            take = values[n] + testDPUnboundedKnapsackMemoization(weight, values, bagWeight - weight[n], n, dp);
        }
        dp[n][bagWeight] = Math.max(notTake, take);
        return dp[n][bagWeight];
    }


    private static int testDPUnboundedKnapsackRecursion(int[] weight, int[] values, int bagWeight, int n) {

        if (n == 0) {
            if (weight[0] <= bagWeight) {
                return (bagWeight / weight[0]) * values[0];
            } else {
                return 0;
            }
        }


        int notTake = testDPUnboundedKnapsackRecursion(weight, values, bagWeight, n -1);
        int take = Integer.MIN_VALUE;

        if (bagWeight >= weight[n]) {
            take = values[n] + testDPUnboundedKnapsackRecursion(weight, values, bagWeight - weight[n], n);
        }

        return Math.max(notTake, take);

    }
}
