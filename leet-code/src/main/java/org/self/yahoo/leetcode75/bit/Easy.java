package org.self.yahoo.leetcode75.bit;

import java.util.Arrays;


public class Easy {

    private static int[] testCountingBitsV1(int n) {
        int [] result = new int[n + 1];

        for (int i = 0; i <= n; i++) { // O(n)
            int count = 0;
            int num = i;

            while (num > 0) { // O(log n)
                int remainder = num % 2; // Gets the last digit from binary will be a 0 or 1

                if (remainder == 1) {
                    count++;
                }

                num = num / 2;
            }
            result[i] = count;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Bit manipulation ...Easy...");

        // Leet code 338. Counting Bits
        int n = 5;
        /*
            Time complexity: O(n log n): For each number n, division by 2 takes O(log n) time to process * n

            Space complexity: O(n): Store the result []
         */
        int [] result = testCountingBitsV1(n);
        System.out.println("testCountingBitsV1: " + Arrays.toString(result));

        /*
            Approach is using dynamic programming

            Time complexity: O(n): Uses memoization to visit a number in the loop only once

            Space complexity: O(n): Extra compute space to store the number of ones for each number
         */
        result = testCountingBitsV2(n);
        System.out.println("testCountingBitsV2: " + Arrays.toString(result));
    }

    private static int[] testCountingBitsV2(int n) {
        int [] dp = new int[n + 1];
        int offset = 1;

        for (int i = 1; i <= n; i++) {
            if (offset * 2 == i) {
                offset = i;
            }
            dp[i] = 1 + dp[i - offset];
        }
        return dp;
    }


}
