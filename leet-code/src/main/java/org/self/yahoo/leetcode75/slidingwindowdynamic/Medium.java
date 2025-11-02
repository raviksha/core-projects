package org.self.yahoo.leetcode75.slidingwindowdynamic;

import java.util.HashSet;
import java.util.Set;

public class Medium {
    public static void main(String[] args) {
        System.out.println("Sliding window dynamic ...Medium...");

        // Leet code 3. Longest Substring Without Repeating Characters
        String s = "abcabcbb";

        /*
            Time complexity: O(n + m): Sliding window with the left and right pointer traversing the str[] at most once
                             HashSet operations get

            Space complexity: O(n): Worst case when all the chars are unique
         */
        int result = testLongestSubStringWithoutRepeatingChars(s);
        System.out.println("testLongestSubStringWithoutRepeatingChars: " + result);
    }

    private static int testLongestSubStringWithoutRepeatingChars(String s) {
        int n = s.length();
        if (n < 2) {
            return n;
        }

        int length = 0;
        int maxLength = 0;
        int left = 0;
        int right = 0;

        Set<Character> freqSet = new HashSet<>(); // O(n)

        while (right < n) { // O(n)
            char currChar = s.charAt(right);

            if (freqSet.contains(currChar)) {
                length = right - left;
                maxLength = Math.max(length, maxLength);

                char leftChar = s.charAt(left);

                while (leftChar != currChar) {
                    freqSet.remove(leftChar);
                    left++;
                    leftChar = s.charAt(left);
                }
                freqSet.remove(leftChar);
                left++;

            }
            freqSet.add(currChar);
            right++;

            length = right - left;
            maxLength = Math.max(length, maxLength);
        }
        return maxLength;
    }
}
