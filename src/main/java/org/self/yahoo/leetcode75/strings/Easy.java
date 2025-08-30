package org.self.yahoo.leetcode75.strings;

public class Easy {

    private static boolean testIsSubsequence(String s, String t) {
        if (s.isEmpty() && t.isEmpty()) {
            return true;
        }

        if (s.isEmpty()) {
            return true;
        }

        int ctr = 0;

        for (int i = 0; i < t.length(); i++) {
            if (ctr < s.length() && s.charAt(ctr) == t.charAt(i)) {
                ctr++;
            }
        }

        return ctr == s.length();
    }

    public static void main(String[] args) {
        System.out.println("Strings .. Easy....");
        // Leet code 392. Is Subsequence
        String s = "abc";
        String t = "ahbgdc";

        /*
            Time complexity: O(n): Single pass over the string t
                                    Each char comparison using charAt() takes O(1) constant time

            Space complexity: O(1): No extra compute space required
         */
        boolean result = testIsSubsequence(s, t);
        System.out.println("testIsSubsequence: " + result);
    }


}
