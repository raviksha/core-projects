package org.self.yahoo.leetcode.grind.dp.lecture20;

import java.util.Arrays;

public class Medium {
    public static void main(String[] args) {
        // Leet code Leet code 322. Coin Change
        // Minimum coin OR Infinite supply pattern
        int [] coins = {1, 2, 3};
        int target = 8;

        int n = coins.length;

        /*
            Time complexity: O(2 ^ N): Exponential

            Space complexity: O(n)
         */
        int minCoins = testMinCoinInfiniteSupplyRecursion(coins, target, n - 1);
        System.out.println("testMinCoinInfiniteSupplyRecursion: " + minCoins);

        int [][] dp = new int[coins.length][target + 1];
        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }

        /*
            Approach: Use memoization to reduce the time complexity

            Time complexity: O(n * t) => Number of coins * target

            Space complexity: O(n * t) => dp [][] memoization array + O(t): Recursion stack depth of target depth
                              Final: O(n * t)
         */
        minCoins = testMinCoinInfiniteSupplyMemoization(coins, target, n - 1, dp);
        System.out.println("testMinCoinInfiniteSupplyMemoization: " + minCoins);
    }

    private static int testMinCoinInfiniteSupplyMemoization(int[] coins, int target, int n, int[][] dp) {
        if (n == 0) {
            if (target % coins[n] == 0) {
                return target / coins[0];
            } else {
                return Integer.MAX_VALUE;
            }
        }

        if (dp[n][target] != Integer.MAX_VALUE) {
            return dp[n][target];
        }

        int notTake = 0 + testMinCoinInfiniteSupplyMemoization(coins, target, n - 1, dp);

        int take = Integer.MAX_VALUE;
        if (target >= coins[n]) {
            take = 1 + testMinCoinInfiniteSupplyMemoization(coins, target - coins[n], n , dp);
        }

        dp[n][target] = Math.min(notTake, take);

        return dp[n][target];
    }

    private static int testMinCoinInfiniteSupplyRecursion(int[] coins, int target, int n) {
         if (n == 0) {

            if (target % coins[0] == 0) {
                return target / coins[0];
            } else {
                return Integer.MAX_VALUE;
            }

         }

         int notTake = 0 + testMinCoinInfiniteSupplyRecursion(coins, target, n - 1);

         int take = Integer.MAX_VALUE;

         if (target >= coins[n]) {
             take = 1 + testMinCoinInfiniteSupplyRecursion(coins, target - coins[n], n);
         }

         return Math.min(notTake, take);
    }
}
