package org.self.yahoo.leetcode.grind.dp.lecture6;

import java.util.Arrays;

/*
    Tabulation : Bottom to up : 0 => n
    Recursion + Memoization: Top down : n - 1 => 0
 */
public class Medium {
    public static void main(String[] args) {
        // 2D DP
        // Leet code Ninja training

        int [][] points = {{2, 1, 3}, {3, 4, 6}, {10, 1, 6}, {8, 3, 7}};
        int n = points.length;
        int lastTaskMarker = points[0].length;
        int[][] dp = new int[n][lastTaskMarker + 1];

        for (int[] tmp : dp) {
            Arrays.fill(tmp, Integer.MAX_VALUE);
        }

        /*
            Time complexity: n ^ m: Exponential time complexity

            Space complexity: O(n)
         */
        int maxPoints = testNinjaPointsRecursionPlusMemoization(n - 1, lastTaskMarker, points, dp);
        System.out.println("testNinjaPointsRecursionPlusMemoization: " + maxPoints);

    }

    private static int testNinjaPointsRecursionPlusMemoization(int days, int lastTask, int[][] points, int [][] dp) {
        if (days == 0) {
            int maxPoint = Integer.MIN_VALUE;
            for (int task = 0; task < points[0].length; task++) {
                if (task != lastTask) {
                    int currPoint = points[days][task];
                    maxPoint = Math.max(currPoint, maxPoint);
                }
            }
            return maxPoint;
        }

        if (dp[days][lastTask] != Integer.MAX_VALUE) {
            return dp[days][lastTask];
        }

        int maxPoint = Integer.MIN_VALUE;

        for (int task = 0; task < points[0].length; task++) {
            if (task != lastTask) {
                int currPoint = points[days][task] + testNinjaPointsRecursionPlusMemoization(days - 1, task, points, dp);
                maxPoint = Math.max(maxPoint, currPoint);
            }
        }
        dp[days][lastTask] = maxPoint;

        return dp[days][lastTask];
    }
}
