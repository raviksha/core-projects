package org.self.yahoo.leetcode.grind.dp.lecture30;

import java.util.Arrays;

public class Medium {
    public static void main(String[] args) {
        System.out.println(" Leet code 583. Delete Operation for Two Strings ... Medium...");

        //  Leet code 583. Delete Operation for Two Strings
        String s1 = "leetcode";
        String s2 = "etco";

        /*
            Time complexity: O(n * m): Not exponential

            Space complexity:O(n * m): dp[][] memoization array + O(n): Recursion stack depth
                                       Conclusion: O(n * m)
         */
        int delOperations = testDeleteOperationTwoStringMemoization(s1, s2);
        System.out.println("testDeleteOperationTwoStringMemoization: " + delOperations);
    }

    private static int testDeleteOperationTwoStringMemoization(String s1, String s2) {

        int idx1 = s1.length();
        int idx2 = s2.length();

        int[][] dp = new int[idx1][idx2];
        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }

        int lcs = testDeleteOperationTwoStringMemoization(s1, s2, idx1 - 1, idx2 - 1, dp);
        return s1.length() - lcs + s2.length() - lcs;
    }

    private static int testDeleteOperationTwoStringMemoization(String s1, String s2, int idx1, int idx2, int[][] dp) {
        if (idx1 < 0 || idx2 < 0) {
            return 0;
        }

        if (dp[idx1][idx2] != Integer.MAX_VALUE) {
            return dp[idx1][idx2];
        }

        int match = 0;

        if (s1.charAt(idx1) == s2.charAt(idx2)) {
            match = 1 + testDeleteOperationTwoStringMemoization(s1, s2, idx1 - 1, idx2 - 1, dp);
        }

        int notMatch = Math.max(testDeleteOperationTwoStringMemoization(s1, s2, idx1 - 1, idx2, dp),
                testDeleteOperationTwoStringMemoization(s1, s2, idx1, idx2 - 1, dp));

        dp[idx1][idx2] = Math.max(match, notMatch);

        return dp[idx1][idx2];
    }
}
