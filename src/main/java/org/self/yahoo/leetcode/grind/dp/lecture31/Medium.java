package org.self.yahoo.leetcode.grind.dp.lecture31;

import java.util.Arrays;

public class Medium {
    public static void main(String[] args) {
        System.out.println("DP 31. Shortest Common Super sequence ...Medium ..");
        // DP 31. Shortest Common Super sequence
        String s1 = "groot";
        String s2 = "brute";

        int minLength = testShortestCommonSSMemoization(s1, s2);
        System.out.println("testShortestCommonSSMemoization: " + minLength);

    }

    private static int testShortestCommonSSMemoization(String s1, String s2) {

        int idx1 = s1.length();
        int idx2 = s2.length();

        int [][] dp = new int[idx1][idx2];

        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }

        int lcs = testShortestCommonSSMemoization(s1, s2, idx1 -1, idx2 - 1, dp);
        System.out.println("LCS: " + lcs);
        return s1.length() + s2.length() - lcs;
    }

    private static int testShortestCommonSSMemoization(String s1, String s2, int idx1, int idx2, int[][] dp) {
        if (idx1 < 0 || idx2 < 0) {
            return 0;
        }

        if (dp[idx1][idx2] != Integer.MAX_VALUE) {
            return dp[idx1][idx2];
        }

        int match = 0;

        if (s1.charAt(idx1) == s2.charAt(idx2)) {
            match = 1 + testShortestCommonSSMemoization(s1, s2, idx1 - 1, idx2 -1 , dp);
        }

        int notMatch = Math.max(testShortestCommonSSMemoization(s1, s2, idx1 -1, idx2, dp),
                                testShortestCommonSSMemoization(s1, s2, idx1, idx2 -1 , dp));

        dp[idx1][idx2] = Math.max(match, notMatch);
        return dp[idx1][idx2];
    }
}
