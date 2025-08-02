package org.self.yahoo.leetcode.grind.dp;

import java.util.Arrays;
/*
    Tabulation : Bottom to up : 0 => n
    Recursion + Memoization: Top down : n - 1 => 0
 */
public class Medium {

    private static int testFindNthFibonacciNumber(int n) {
        if (n <= 1) {
            return n;
        }
        return testFindNthFibonacciNumber(n - 1) + testFindNthFibonacciNumber((n - 2));
    }

    private static int testFindNthFibonacciNumberWithDP(int n, int[] dp) {

        if (n <= 1) {
            return n;
        }

        if (dp[n] != Integer.MIN_VALUE) {
            return dp[n];
        }
        dp[n] = testFindNthFibonacciNumberWithDP(n - 1, dp) + testFindNthFibonacciNumberWithDP(n - 2, dp);
        return dp[n];
    }


    private static int testFindNthFibonacciNumberWithTabulation(int n) {
        int [] dp = new int[n + 1];

        // Initialize base case:
        dp[0] = 0;
        dp[1] = 1;
        /*
            i <= n: coz the requirement is to get the nth fibonacci number starting from index 0
            Fibonacci:[0, 1, 1, 2, 3, 5, 8, 13]
            Index =>  [0, 1, 2, 3, 4, 5, 6, 7]

         */

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp [i - 2];
        }

        return dp[n];
    }

    private static int testFindNthFibonacciNumberWithNoExtraSpace(int n) {
        int prev2 = 0;
        int prev1 = 1;

        for (int i = 2; i <= n; i++) {

            int curr = prev2 + prev1;

            prev2 = prev1;
            prev1 = curr;

        }

        return prev1;
    }

    private static int testClimbingStairsWithNoExtraSpace(int n) {
        int prev2 = 1;
        int prev = 1;

        for (int i = 2; i <= n; i++) {
            int current = prev + prev2;

            prev2 = prev;
            prev = current;
        }

        return prev;
    }

    private static int testClimbingStairsUsingTabulation(int n) {
        int [] dp = new int[n + 1];
        dp[0] = 1; // There is 1 way to stay at ground (do nothing)
        dp[1] = 1; // There is one way to reach step 1

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    private static int testClimbingStairsUsingRecursion(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }

        int leftCount = testClimbingStairsUsingRecursion(n - 2);
        int rightCount = testClimbingStairsUsingRecursion(n - 1);

        return leftCount + rightCount;
    }

    private static int testClimbingStairsUsingMemoization(int n, int[] dp) {

        if (n < 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }

        if (dp[n] != Integer.MIN_VALUE) {
            return dp[n];
        }

        int leftCount = testClimbingStairsUsingMemoization(n - 2, dp);
        int rightCount = testClimbingStairsUsingMemoization(n - 1, dp);
        dp[n] = leftCount + rightCount;
        return dp[n];
    }


    private static int testMinimumEnergyRequiredWithNoExtraSpace(int[] energy) {
        int prev2 = 0;
        int prev = Math.abs(energy[1] - energy[0]);

        for (int i = 1; i < energy.length; i++) {

            int right = Integer.MAX_VALUE;
            int left = energy[i - 1] + energy[i] - prev;
            if (i > 1) {
                right = energy[i - 2] + energy[i] - prev2;
            }

            int current = Math.min(left, right);

            prev2 = prev;
            prev = current;

        }
        return prev;
    }


    private static int testMinimumEnergyRequiredUsingTabulation(int[] energy) {
        int n = energy.length;
        int [] dp = new int[n];

        dp [0] = 0;

        for (int i = 1; i < energy.length; i++) {

            int right = Integer.MAX_VALUE;
            int left = dp [i - 1] + Math.abs(energy[i] - energy [i - 1]);
            if (i > 1) {
                right = dp [i - 2] + Math.abs(energy[i] - energy [i - 2]);
            }
            dp[i] = Math.min(left, right);
        }
        return dp[n - 1];
    }


    private static int testMinimumEnergyRequiredUsingMemoization(int[] energy, int n, int[] dp) {
        if (n == 0) {
            return 0;
        }
        if (dp[n] != Integer.MAX_VALUE) {
            return dp[n];
        }
        int rightTree = Integer.MAX_VALUE;

        int leftTree = testMinimumEnergyRequiredUsingMemoization(energy, n - 1, dp) + Math.abs(energy[n] - energy[n - 1]);
        if (n > 1) {
            rightTree = testMinimumEnergyRequiredUsingMemoization(energy, n - 2, dp) + Math.abs(energy[n] - energy[n - 2]);
        }
        dp[n] = Math.min(leftTree, rightTree);
        return dp[n];
    }

    private static int testMinimumEnergyRequiredUsingRecursion(int[] energy, int n) {
        if (n == 0) {
            return 0;
        }
        int rightTree = Integer.MAX_VALUE;
        int leftTree = testMinimumEnergyRequiredUsingRecursion(energy, n - 1) + Math.abs(energy[n] - energy[n -1]);
        if (n > 1) {
            rightTree = testMinimumEnergyRequiredUsingRecursion(energy, n - 2) + Math.abs(energy[n] - energy[n -2]);
        }

        return Math.min(leftTree, rightTree);
    }

    public static void main(String[] args) {
        System.out.println("Grind DP.....");

        // test Fibonacci series
        /*
            Fibonacci:[0, 1, 1, 2, 3, 5, 8, 13]
            Index =>  [0, 1, 2, 3, 4, 5, 6, 7]
         */
        int n = 6;
        /*
            Approach is using pure recursion with no memoization: Top down approach
            Time complexity: O(2 ^ n): Exponential time complexity

            Space complexity: O(n): Recursion stack depth
         */
        int result = testFindNthFibonacciNumber(n);
        System.out.println("testFibonacciSeries: " + result);
        int [] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MIN_VALUE);

        /*
            Approach is using recursion using memoization: Top down approach

            Time complexity: O(n): Each fibonacci number from 0 to n is computed only once

            Space complexity: O(n)
                              dp []: O(n)
                              recursion stack: O(n)
                              Final s/c: O(n)
         */
        result = testFindNthFibonacciNumberWithDP(n, dp);
        System.out.println("testFibonacciSeriesWithDP: " + result);

        /*
            Approach is using Tabulation instead of using recursion or memoization

            Time complexity: O(n): Each element from 0 to n is visited only once

            Space complexity: O(n)
                              No recursion stack space.
                              dp[]: Memoization array: O(n)
         */
        result = testFindNthFibonacciNumberWithTabulation(n);
        System.out.println("testFindNthFibonacciNumberWithTabulation: " + result);

        /*
            Approach is using DP without an extra memoization space complexity

            Time complexity: O(n): Each element from 0 to n is visited only once

            Space complexity: O(1): NO extra auxiliary compute space required
         */

        result = testFindNthFibonacciNumberWithNoExtraSpace(n);
        System.out.println("testFindNthFibonacciNumberWithNoExtraSpace: " + result);

        // Leet code 70. Climbing Stairs
        n = 8;
        /*
            Approach is using recursion

            Time complexity: O(2 ^ n)

            Space complexity: O(n): Auxiliary space for recursion stack
       */
        result = testClimbingStairsUsingRecursion(n);
        System.out.println("testClimbingStairsUsingRecursion: " + result);


        dp = new int[n + 1];
        Arrays.fill(dp, Integer.MIN_VALUE);
        /*
            Approach: Use memoization instead of recursion

            Time complexity: O(n): Each iteration between 0 => n is visited only once

            Space complexity: O(n)
                              dp []: O(n) extra compute space
                              Recursion stack: O(n)
         */
        result = testClimbingStairsUsingMemoization(n, dp);
        System.out.println("testClimbingStairsUsingMemoization: " + result);

        /*
            Approach: Dynamic programming with using Tabular format instead of Recursion + Memoization

            Time complexity: O(n): Each element from 0 => n is visited once

            Space complexity: O(n): Extra compute space for dp[] for memoization
                                    NO recursion required
         */
        result = testClimbingStairsUsingTabulation(n);
        System.out.println("testClimbingStairsUsingTabulation: " + result);


        /*
            Approach: Dynamic programming without using any extra compute space

            Time complexity: O(n): Each element from 0 => n is visited once


            Space complexity: O(1): No extra auxiliary space required
         */
        result = testClimbingStairsWithNoExtraSpace(n);
        System.out.println("testClimbingStairsWithNoExtraSpace: " + result);


        // Leet code: Minimum energy required by Frog to jump 1 and 2  steps
        int [] energy = {30, 10, 60, 10, 60, 50};
        int steps = 5; // 0 indexed array

        /*
            Time complexity: O(2 ^ n): Exponential time complexity

            Space complexity: O(n): Recursion tack depth
         */
        result = testMinimumEnergyRequiredUsingRecursion(energy, steps);
        System.out.println("testMinimumEnergyRequiredUsingRecursion: " + result);

        dp = new int[steps + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        /*
            Time complexity: O(n): Each element between 0 => n is visited once

            Space complexity: O(n)
                              Recursion stack: O(n)
                              dp [] for memoization: O(n)
         */
        result = testMinimumEnergyRequiredUsingMemoization(energy, steps, dp);
        System.out.println("testMinimumEnergyRequiredUsingMemoization: " + result);

        /*
            Time complexity: O(n)

            Space complexity: O(n)
                              dp[] Auxiliary space for memoization
         */

        result = testMinimumEnergyRequiredUsingTabulation(energy);
        System.out.println("testMinimumEnergyRequiredUsingTabulation: " + result);

        result = testMinimumEnergyRequiredWithNoExtraSpace(energy);
        System.out.println("testMinimumEnergyRequiredWithNoExtraSpace: " + result);

        // Leet code: Minimum energy required by Frog to jump 1 to k  steps
        energy = new int [] {30, 10, 60, 10, 60, 50};
        steps = 5; // 0 indexed array
        int k = 2;
        /*
            Approach is using recursion

            Time complexity: O(k ^ n)

            Space complexity: O(n): Recursion stack
         */
        result = testMinimumEnergyRequiredKJumpsUsingRecursion(energy, steps, k);
        System.out.println("testMinimumEnergyRequiredKJumpsUsingRecursion: " + result);

        dp = new int[steps + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        /*
            Time complexity: O(n * k)

            Space complexity: O(n)
                              Recursion stack: O(n)
                              Memoization array: O(n)
         */
        result = testMinimumEnergyRequiredKJumpsUsingMemoization(energy, steps, k, dp);
        System.out.println("testMinimumEnergyRequiredKJumpsUsingMemoization: " + result);

        /*
            Time complexity: O(n * k)

            Space complexity: O(n): dp[] memoization array
         */

        result = testMinimumEnergyRequiredKJumpsUsingTabulation(energy, steps, k);
        System.out.println("testMinimumEnergyRequiredKJumpsUsingTabulation: " + result);

    }

    private static int testMinimumEnergyRequiredKJumpsUsingTabulation(int[] energy, int n, int k) {
        int [] dp = new int[energy.length];
        dp[0] = 0;

        for (int i = 1; i < energy.length; i++) {
            int totalEnergy = Integer.MAX_VALUE;
            for (int j = 1; j <= k ; j++) {
                if (i - j >= 0) {
                    int  minEnergy = dp[i - j] + Math.abs(energy[i] - energy[i - j]);
                    totalEnergy = Math.min(minEnergy, totalEnergy);
                }
            }
            dp[i] = totalEnergy;

        }
        return dp[n];
    }

    private static int testMinimumEnergyRequiredKJumpsUsingMemoization(int[] energy, int n, int k, int[] dp) {
        if (n == 0) {
            return 0;
        }
        if (dp[n] != Integer.MAX_VALUE) {
            return dp[n];
        }
        int totalEnergy = Integer.MAX_VALUE;

        for (int i = 1; i <= k; i++) {
            if (n - i >= 0) {
                int minEnergy = testMinimumEnergyRequiredKJumpsUsingMemoization(energy, n - i, k, dp) + Math.abs(energy[n] - energy[n - i]);
                totalEnergy = Math.min(totalEnergy, minEnergy);
                dp[n] = totalEnergy;
            }

        }

        return dp[n];
    }

    private static int testMinimumEnergyRequiredKJumpsUsingRecursion(int[] energy, int n, int k) {
        if (n == 0) {
            return 0;
        }
        int minEnergy = Integer.MAX_VALUE;
        for (int i = 1; i <= k; i++) {
            if (n - i >= 0) {
                int kEnergy =  testMinimumEnergyRequiredKJumpsUsingRecursion(energy, n - i, k) + Math.abs(energy[n] - energy[n - i]);
                minEnergy = Math.min(kEnergy, minEnergy);
            }
        }
        return minEnergy;
    }

}
