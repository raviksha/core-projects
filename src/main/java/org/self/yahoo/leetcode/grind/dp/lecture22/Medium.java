package org.self.yahoo.leetcode.grind.dp.lecture22;

import java.util.Arrays;

public class Medium {
    public static void main(String[] args) {
        System.out.println("518. Coin Change II  .. Medium");

        // Leet code 518. Coin Change II
        int [] coins = {1,2,5};
        int target = 5;
        int n = coins.length;
        int [] [] dp = new int[n][target + 1];

        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        /*
            Approach: Using Memoization

            Time complexity: O(n * t): Not exponential

            Space complexity: O(n * t): dp[][] memoization array + O(n): Recursion stack depth
                             Final: O(n * t)
         */
        int combinations = testCoinChangeIIMemoization(coins, n - 1, dp, target);
        System.out.println("testCoinChangeII: " + combinations);
    }

    private static int testCoinChangeIIMemoization(int[] coins, int n, int[][] dp, int target) {
        if (n == 0) {
            if (target % coins[0] == 0) {
                return 1;
            } else {
                return 0;
            }
        }

        if (dp[n][target] != Integer.MAX_VALUE) {
            return dp[n][target];
        }

        int notTake = testCoinChangeII(coins, n - 1, dp, target);
        int take = 0;

        if (target >= coins[n]) {
            take = testCoinChangeII(coins, n, dp, target - coins[n]);
        }
        dp[n][target] = take + notTake;
        return dp[n][target];
    }
}
