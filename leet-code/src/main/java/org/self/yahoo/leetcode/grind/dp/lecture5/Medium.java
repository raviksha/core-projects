package org.self.yahoo.leetcode.grind.dp.lecture5;

import java.util.Arrays;
/*
    Tabulation : Bottom to up : 0 => n
    Recursion + Memoization: Top down : n - 1 => 0
 */
public class Medium {
    private static int testMaxSumNonAdjacentNumberWithSpaceOptimization(int[] houses) {
        int prev2 = houses[0];
        int prev = Math.max(houses[0], houses[1]);

        for (int i = 2; i < houses.length; i++) {
            int pick = houses[i] + prev2;
            int nonPick = prev;

            prev2 = prev;
            prev = Math.max(pick, nonPick);
        }
        return prev;
    }

    private static int testMaxSumNonAdjacentNumberUsingTabulation(int[] houses) {
        int n = houses.length;
        int [] dp = new int[n];

        dp[0] = houses[0];
        dp[1] = Math.max(dp[0], dp[1]);

        for (int i = 2; i < n ; i++) {
            int pick = dp[i - 2] + houses[i];
            int notPick = dp[i - 1];
            dp[i] = Math.max(pick, notPick);
        }
        return dp[n -1];
    }

    private static int testMaxSumNonAdjacentNumberUsingMemoization(int[] houses, int n, int [] dp) {
        if (n == 0) {
            return houses[n];
        }

        if (n < 0) {
            return 0;
        }

        if (dp[n] != Integer.MAX_VALUE) {
            return dp[n];
        }

        int pick = houses[n] + testMaxSumNonAdjacentNumberUsingMemoization(houses, n - 2, dp);
        int notPick = testMaxSumNonAdjacentNumberUsingMemoization(houses, n - 1, dp);
        dp[n] = Math.max(pick, notPick);
        return dp[n];
    }

    private static int testMaxSumNonAdjacentNumberUsingRecursion(int[] houses, int n) {

        if (n == 0) {
            return houses[n];
        }

        if (n < 0) {
            return 0;
        }

        int pick = houses[n] + testMaxSumNonAdjacentNumberUsingRecursion(houses, n - 2);
        int nonPick = testMaxSumNonAdjacentNumberUsingRecursion(houses, n - 1);

        return Math.max(pick, nonPick);
    }
    public static void main(String[] args) {
        // Maximum sum of non adjacent number || House Robber 1
        int [] houses = {2, 1, 4, 9};
        int n = houses.length - 1;

        /*
            Time complexity: O(2 ^ n): Exponential time complexity
            Space complexity: O(n): Recursion stack O(n) level deep
         */
        int maxSum = testMaxSumNonAdjacentNumberUsingRecursion(houses, n);
        System.out.println("testMaxSumNonAdjacentNumberUsingRecursion: " + maxSum);

        int [] dp = new int[houses.length];
        Arrays.fill(dp, Integer.MAX_VALUE);

        /*
            Time complexity: O(n)

            Space complexity: O(n)
                              dp []: O(n) for the memoization array
                              Recursion stack: O(n)
         */
        maxSum = testMaxSumNonAdjacentNumberUsingMemoization(houses, n, dp);
        System.out.println("testMaxSumNonAdjacentNumberUsingMemoization: " + maxSum);

        /*
            Time complexity: O(n)
            Space complexity: O(n): dp [] memoization array
         */
        maxSum = testMaxSumNonAdjacentNumberUsingTabulation(houses);
        System.out.println("testMaxSumNonAdjacentNumberUsingTabulation: " + maxSum);

        /*
            Time complexity: O(n)
            Space complexity: O(1)
         */

        maxSum = testMaxSumNonAdjacentNumberWithSpaceOptimization(houses);
        System.out.println("testMaxSumNonAdjacentNumberWithSpaceOptimization: " + maxSum);

        // Leet code House Robber - II
        // {1,7,9,2};
        houses = new int[] {2,1,4,9};
        /*
            Time complexity: O(2 ^ n): Exponential t/c
            Space complexity: O(n): Recursion stack depth
         */
        int maxRob = testHouseRobberIIWithRecursion(houses);
        System.out.println("testHouseRobberIIWithRecursion: " + maxRob);

        dp = new int[houses.length];
        Arrays.fill(dp, Integer.MAX_VALUE);

        /*
            Time complexity: O(n)

            Space complexity: O(n) + O(n) => Recursion depth + dp [] memoization array
         */
        maxRob = testHouseRobberIIWithMemoization(houses, dp);
        System.out.println("testHouseRobberIIWithMemoization: " + maxRob);


        /*
            Time complexity: O(n)

            Space complexity: O(n) => dp[] memoization array
         */

        maxRob = testHouseRobberIIWithTabulation(houses);
        System.out.println("testHouseRobberIIWithTabulation: " + maxRob);

        /*
                Time complexity: O(n)

                Space complexity: O(1): No extra auxiliary space
         */
        maxRob = testHouseRobberIIWithSpaceOptimization(houses);
        System.out.println("testHouseRobberIIWithSpaceOptimization: " + maxRob);
    }

    private static int testHouseRobberIIWithSpaceOptimization(int[] houses) {
        int n = houses.length;
        int [] left = new int[n - 1];
        int [] right = new int[n - 1];

        for (int i = 0; i < n ; i++) {
            if (i != 0) {
                left[i -1] = houses[i];
            }

            if (i != n - 1) {
                right[i] = houses[i];
            }
        }

        int leftMax = testHouseRobberIIWithSpaceOptimization(left, "left");
        int rightMax = testHouseRobberIIWithSpaceOptimization(right, "right");

        return Math.max(leftMax, rightMax);
    }

    private static int testHouseRobberIIWithSpaceOptimization(int[] houses, String section) {
        int prev2 = houses[0];
        int prev = Math.max(houses[0], houses[1]);

        for (int i = 2; i < houses.length; i++) {
            int pick = houses[i] + prev2;
            int notPick = prev;

            int current = Math.max(pick, notPick);

            prev2 = prev;
            prev = current;
        }
        return prev;
    }

    private static int testHouseRobberIIWithTabulation(int[] houses) {
        int n = houses.length;
        int [] dp = new int[n];

        dp[0] = houses[0];
        dp[1] = Math.max(houses[0], houses[1]);

        int leftMax = testHouseRobberIIWithTabulation(houses, 2, n - 1, dp);

        dp = new int[n];
        dp[1] = houses[1];
        dp[2] = Math.max(houses[1], houses[2]);

        int rightMax = testHouseRobberIIWithTabulation(houses, 3, n, dp);

        return Math.max(leftMax, rightMax);
    }

    private static int testHouseRobberIIWithTabulation(int[] houses, int start, int end, int[] dp) {

        for (int i = start; i < end; i++) {
            int pick = houses[i] + dp[i - 2];
            int nonPick = dp[i - 1];

            dp[i] = Math.max(pick, nonPick);
        }
        return dp[end - 1];
    }


    private static int testHouseRobberIIWithMemoization(int[] houses, int[] dp) {
        int n = houses.length - 1;

        int leftMax = testHouseRobberIIWithMemoization(houses, 0, n - 1, dp);
        int rightMax = testHouseRobberIIWithMemoization(houses, 1, n, dp);
        return Math.max(leftMax, rightMax);
    }

    private static int testHouseRobberIIWithMemoization(int[] houses, int begin, int end, int[] dp) {
        if (end == begin) {
            return houses[end];
        }

        if (end < begin) {
            return 0;
        }

        if (dp[end] != Integer.MAX_VALUE) {
            return dp[end];
        }

        int pick = houses[end] + testHouseRobberIIWithMemoization(houses, begin, end - 2, dp);
        int notPick = testHouseRobberIIWithMemoization(houses, begin, end - 1, dp);

        dp[end] = Math.max(pick, notPick);
        return dp[end];
    }


    private static int testHouseRobberIIWithRecursion(int[] houses) {
        int n = houses.length - 1;

        int leftMax = testHouseRobberIIWithRecursion(houses, 0, n - 1);
        int rightMax = testHouseRobberIIWithRecursion(houses, 1, n);

        return Math.max(leftMax, rightMax);
    }

    private static int testHouseRobberIIWithRecursion(int[] houses, int begin, int end) {

        if (end == begin) {
            return houses[end];
        }

        if (end < begin) {
            return 0;
        }

        int pick = houses[end] + testHouseRobberIIWithRecursion(houses, begin, end - 2);
        int notPick = testHouseRobberIIWithRecursion(houses, begin, end - 1);

        return Math.max(pick, notPick);
    }


}
