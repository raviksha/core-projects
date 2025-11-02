package org.self.yahoo.leetcode.grind.dp.lecture29;

import java.util.Arrays;

public class Medium {
    public static void main(String[] args) {
        System.out.println("1312. Minimum Insertion Steps to Make a String Palindrome ...Medium");

        // Leet code 1312. Minimum Insertion Steps to Make a String Palindrome

        String s1 = "leetcode";

        /*
            Time complexity: O(m * n): Not exponential

            Space complexity: O(m * n): dp[][] memoization array + O(n): Recursion stack depth
                                        Conclusion: O(m * n)
         */
        int result = testMinInsertionForPalindromeMemoization(s1);
        System.out.println("testMinInsertionForPalindromeMemoization: " + result);
    }

    private static int testMinInsertionForPalindromeMemoization(String s1) {

        String s2 = new StringBuilder(s1).reverse().toString();

        int idx1 = s1.length();
        int idx2 = s2.length();

        int [][] dp = new int[idx1][idx2];
        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }

        int insertCount = testMinInsertionForPalindromeMemoization(s1, s2, idx1 - 1, idx2 - 1, dp);
        return s1.length() - insertCount;
    }

    private static int testMinInsertionForPalindromeMemoization(String s1, String s2, int idx1, int idx2, int[][] dp) {

        if (idx1 < 0 || idx2 < 0) {
            return 0;
        }

        if (dp[idx1][idx2] != Integer.MAX_VALUE) {
            return dp[idx1][idx2];
        }

        int match = 0;

        if(s1.charAt(idx1) == s2.charAt(idx2)) {
            match = 1 + testMinInsertionForPalindromeMemoization(s1, s2, idx1 - 1, idx2 - 1, dp);
        }

        int notMatch = Math.max(testMinInsertionForPalindromeMemoization(s1, s2, idx1 -1, idx2, dp),
                                testMinInsertionForPalindromeMemoization(s1, s2, idx1, idx2 -1, dp));

        dp[idx1][idx2] = Math.max(match, notMatch);
        return dp[idx1][idx2];
    }
}
