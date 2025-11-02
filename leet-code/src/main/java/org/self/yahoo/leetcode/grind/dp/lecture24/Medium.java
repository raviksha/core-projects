package org.self.yahoo.leetcode.grind.dp.lecture24;

import java.util.Arrays;

public class Medium {
    public static void main(String[] args) {
        System.out.println("DP 24. Rod Cutting Problem..Medium...");

        // DP 24. Rod Cutting Problem
        int [] rods = {2, 5, 7, 8, 10};
        int targetRodLength = 5;
        int n = rods.length;

        /*
            Time complexity: O(2 ^ n): Exponential time complexity

            Space complexity: O(t): Recursion of t depth
         */
        int maxRodPrice = testRodCuttingRecursion(rods, targetRodLength, n - 1);
        System.out.println("testRodCuttingRecursion: " + maxRodPrice);

        int [][] dp = new int[n][targetRodLength + 1];
        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }

        /*
                Time complexity: O(n * t): Rods length * target rod length

                Space complexity: O(n * t): dp[][] memoization array + O(t): Recursion stack of depth t
                                  Final: O(n * t)
         */
        maxRodPrice = testRodCuttingMemoization(rods, targetRodLength, n - 1, dp);
        System.out.println("testRodCuttingMemoization: " + maxRodPrice);

    }

    private static int testRodCuttingMemoization(int[] rods, int targetRodLength, int n, int[][] dp) {

        if (n == 0) {
            return targetRodLength * rods[0];
        }

        if (dp[n][targetRodLength] != Integer.MAX_VALUE) {
            return dp[n][targetRodLength];
        }

        int notTake = 0 + testRodCuttingMemoization(rods, targetRodLength, n - 1, dp);

        int take = Integer.MIN_VALUE;

        int rodLength = n + 1;

        if (targetRodLength >= rodLength) {
            take = rods[n] + testRodCuttingMemoization(rods, targetRodLength - rodLength, n, dp);
        }

        dp[n][targetRodLength] = Math.max(notTake, take);
        return dp[n][targetRodLength];
    }

    private static int testRodCuttingRecursion(int[] rods, int targetRodLength, int n) {
        if (n == 0) {
            return rods[0] * targetRodLength;
        }

        int notTake = testRodCuttingRecursion(rods, targetRodLength, n - 1);

        int rodLength = n + 1;
        int take = Integer.MIN_VALUE;

        if (targetRodLength >= rodLength) {
            take = rods[n] + testRodCuttingRecursion(rods, targetRodLength - rodLength, n);
        }

        return Math.max(notTake, take);
    }
}
