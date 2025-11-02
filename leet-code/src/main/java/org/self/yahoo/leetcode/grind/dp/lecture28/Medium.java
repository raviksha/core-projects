package org.self.yahoo.leetcode.grind.dp.lecture28;

import java.util.Arrays;

public class Medium {
    public static void main(String[] args) {
        System.out.println("Longest Palindromic Subsequence... Medium..");

        // Leet code 516. Longest Palindromic Subsequence
        String s1 = "bbbab";
        int longPSeq = testLongestPalindromicSubsequenceMemoization(s1);
        System.out.println("testLongestPalindromicSubsequenceMemoization: " + longPSeq);
    }

    private static int testLongestPalindromicSubsequenceMemoization(String s1) {
        String s2 = new StringBuilder(s1).reverse().toString();

        int idx1 = s1.length();
        int idx2 = s2.length();

        int [][] dp = new int[idx1][idx2];
        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }

        int result = testLongestPalindromicSubsequenceMemoization(s1, s2, idx1 - 1, idx2 - 1, dp);
        /*
            Time complexity: O(m * n): Not exponential

            Space complexity: O(m * n): dp[][] memoization array + O(m): Recursion stack depth
                              Conclusion: O(m * n)
         */
        String pSubSeq = getLongestPalindromicSubsequence(s1, s2, dp);
        System.out.println("getLongestPalindromicSubsequence: " + pSubSeq);
        return result;
    }

    private static String getLongestPalindromicSubsequence(String s1, String s2, int[][] dp) {
        StringBuilder stringBuilder = new StringBuilder();
        int idx1 = s1.length() - 1;
        int idx2 = s2.length() - 1;

        while (idx1 >= 0 && idx2 >= 0) {

            if (s1.charAt(idx1) == s2.charAt(idx2)) {
                stringBuilder.append(s1.charAt(idx1));
                idx1--;
                idx2--;
            } else if (idx1 > 0 && (idx2 > 0 || dp[idx1 + 1][idx2] > dp[idx1][idx2 + 1])) {
                idx1--;
            } else {
                idx2--;
            }
        }

        return stringBuilder.reverse().toString();
    }

    private static int testLongestPalindromicSubsequenceMemoization(String s1, String s2, int idx1, int idx2, int[][] dp) {

        if (idx1 < 0 || idx2 < 0) {
            return 0;
        }

        if (dp[idx1][idx2] != Integer.MAX_VALUE) {
            return dp[idx1][idx2];
        }

        int match = 0;

        if (s1.charAt(idx1) == s2.charAt(idx2)) {
            match = 1 + testLongestPalindromicSubsequenceMemoization(s1, s2, idx1 -1, idx2 - 1, dp);
        }

        int notMatch = Math.max(testLongestPalindromicSubsequenceMemoization(s1, s2, idx1 - 1, idx2, dp),
                                testLongestPalindromicSubsequenceMemoization(s1, s2, idx1, idx2 - 1, dp));

        dp[idx1][idx2] = Math.max(match, notMatch);
        return dp[idx1][idx2];
    }
}
