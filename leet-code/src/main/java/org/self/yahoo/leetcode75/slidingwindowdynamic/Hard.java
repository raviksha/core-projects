package org.self.yahoo.leetcode75.slidingwindowdynamic;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class Hard {
    public static void main(String[] args) {
        System.out.println("Sliding window dynamic ... Hard");
        // Leet code 76. Minimum Window Substring
        String s = "ADOBECODEBANC";
        String t = "ABC";

        /*
            Time complexity: O(m + n)
                             Populating the freq Map: O(n)
                             Map operations get/put/contains: O(1)
                             Sliding window traversal: O(m): The left and right pointers visit each index at most once

            Space complexity: O(n) => Extra compute space to store t[] freqMap
         */

        String result = testMinWindowSubString(s, t);
        System.out.println("testMinWindowSubString: " + result);
    }

    private static String testMinWindowSubString(String s, String t) {
        int m = s.length();
        int n = t.length();

        if (n > m) {
            return StringUtils.EMPTY;
        }

        Map<Character, Integer> freqMap = new HashMap<>();

        for (char item : t.toCharArray()) { // O(n)
            freqMap.put(item, freqMap.getOrDefault(item, 0) + 1); // O(1)
        }
        int minLength = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int beginIndex = 0;
        int count = 0;

        while (right < m) { // O(m)
            char currChar = s.charAt(right);
            if (freqMap.containsKey(currChar)) {
                int freq = freqMap.get(currChar);
                if (freq > 0) {
                    count++;
                }
                freqMap.put(currChar, freq - 1);
            }

            while (count == t.length()) {
                int l = right - left;

                if (l < minLength) {
                    minLength = l;
                    beginIndex = left;
                }

                char leftChar = s.charAt(left);
                if (freqMap.containsKey(leftChar)) {
                    int freq = freqMap.get(leftChar) + 1;
                    if (freq > 0) {
                        count--;
                    }
                    freqMap.put(leftChar, freq);
                }
                left++;
            }
            right++;
        }

        return (minLength == Integer.MAX_VALUE) ? "" : s.substring(beginIndex, beginIndex + minLength + 1);
    }
}

