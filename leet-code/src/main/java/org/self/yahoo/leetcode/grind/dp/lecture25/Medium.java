package org.self.yahoo.leetcode.grind.dp.lecture25;

import java.util.Arrays;

public class Medium {
    public static void main(String[] args) {
        System.out.println("Dp 25. Longest Common Subsequence");

        //  Leet code 1143. Longest Common Subsequence

        String s1 = "abcde";
        String s2 = "ace";

        int idx1 = s1.length();
        int idx2 = s2.length();

        /*
            Time complexity: O(2 ^ m + n): Exponential time complexity

            Space complexity: O(m + n): O(m): Recursion stack depth of s1 and s2
         */
        int longSubSeq = testLongestSubsequenceRecursion(s1, s2, idx1 -1, idx2 -1);
        System.out.println("testLongestSubsequenceRecursion: " + longSubSeq);

        int [] [] dp = new int[idx1][idx2];

        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        longSubSeq = testLongestSubsequenceMemoization(s1, s2, idx1 -1, idx2 -1, dp);
        System.out.println("testLongestSubsequenceMemoization: " + longSubSeq);

        StringBuilder longestSubStr = getLongestSubsequence(s1, s2, dp);
        System.out.println("getLongestSubsequence: " + longestSubStr);
    }

    private static StringBuilder getLongestSubsequence(String s1, String s2, int[][] dp) {
        StringBuilder stringBuilder = new StringBuilder();
        int idx1 = s1.length() - 1;
        int idx2 = s2.length() - 1;

        while (idx1 >= 0 && idx2 >= 0) {
            if (s1.charAt(idx1) == s2.charAt(idx2)) {
                stringBuilder.append(s1.charAt(idx1));
                idx1--;
                idx2--;
            } else if (idx1 > 0 && ( idx2 == 0 || dp[idx1 - 1][idx2] > dp[idx1][idx2 -1])) {
                idx1--;
            } else {
                idx2--;
            }
        }
        return stringBuilder.reverse();
    }

    private static int testLongestSubsequenceMemoization(String s1, String s2, int idx1, int idx2, int [][] dp) {
        if (idx1 < 0 || idx2 < 0) {
            return 0;
        }

        if (dp[idx1][idx2] != Integer.MAX_VALUE) {
            return dp[idx1][idx2];
        }

        int notMatch = Math.max(testLongestSubsequenceMemoization(s1, s2, idx1 -1, idx2, dp),
                                testLongestSubsequenceMemoization(s1, s2, idx1, idx2 -1, dp));

        int match = 0;
        if (s1.charAt(idx1) == s2.charAt(idx2)) {
            match =  1 + testLongestSubsequenceMemoization(s1, s2, idx1 -1, idx2 - 1, dp);
        }

        dp[idx1][idx2] = Math.max(notMatch, match);

        return dp[idx1][idx2];
    }


    private static int testLongestSubsequenceRecursion(String s1, String s2, int idx1, int idx2) {

        if (idx1 < 0 || idx2 < 0) {
            return 0;
        }


        int notMatch = Math.max(testLongestSubsequenceRecursion(s1, s2, idx1 -1, idx2), testLongestSubsequenceRecursion(s1, s2, idx1, idx2 -1));

        int match = 0;
        if (s1.charAt(idx1) == s2.charAt(idx2)) {
            match = 1 + testLongestSubsequenceRecursion(s1, s2, idx1 - 1, idx2 -1);
        }

        return Math.max(match, notMatch);
    }
}
