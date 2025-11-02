package org.self.yahoo.leetcode.strings;

import java.util.Arrays;

public class Easy {

    private static boolean testIsSubsequenceV1(String s, String t) {
        if ((s == null || s.isEmpty()) || (t == null || t.isEmpty())) {
            return false;
        }
        int count = 0;
        int tLength = t.length();
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        int noOfMatches = 0;

        while (count < t.length() - 1) {

            for (int i = 0; i < s.length(); i++) {

                while ((count < tLength - 1) && tArr[count] != sArr[i]) {
                    // First character match found
                    count++;
                }

                if (count < tLength && tArr[count] == sArr[i]) {
                    System.out.println("Char: " + tArr[count] + " found at T position: " + count);
                    noOfMatches++;
                    count++;
                }
            }
        }
        return noOfMatches == s.length();
    }

    private static boolean testIsSubsequenceV2(String s, String t) {
        if (s == null || s.isEmpty()) {
            return true;
        }

        if (t == null || t.isEmpty()) {
            return false;
        }

        int tIndex = 0;
        int sIndex = 0;
        int numOfMatches = 0;

        while (tIndex < t.length()  && sIndex < s.length()) {
            if (t.charAt(tIndex) == s.charAt(sIndex)) {
                numOfMatches++;
                sIndex++;
            }
            tIndex++;
        }

        return numOfMatches == s.length();

    }


    private static boolean testValidPalindromeV1(String input) {
        if (input == null || input.isEmpty()) {
            return true;
        }
        input = input.replaceAll("[^A-Za-z0-9]", "").toLowerCase();

        int endIndex = input.length() -1;
        int midIndex = endIndex / 2;
        int beginIndex = 0;

        while (beginIndex <= midIndex) {
            if (input.charAt(beginIndex) != input.charAt(endIndex)) {
                return false;
            }
            beginIndex++;
            endIndex--;
        }
        return true;
    }

    private static boolean testValidPalindromeV2(String s, int start, int end) {
        if (start > end) {
            return true;
        }
        if (s.charAt(start) != s.charAt(end)) {
            return false;
        }
        start = start +1;
        end = end -1;
        return testValidPalindromeV2(s, start++, end--);
    }

    private static String testLongestPrefixV1(String[] strs) {

        if (strs == null || strs.length == 0) {
            return "";
        }

        String input = strs[0];
        String longestSubStr = "";

        for(int i = 1; i< strs.length; i++) {
            if (strs[i].length() < input.length()) {
                input = strs[i];
            }
        }

        System.out.println("Shortest string: " + input);

        int beginIndex = -1;
        int endIndex = -1;

        for(int i =  0; i < input.length(); i++) {
            var currChar = input.charAt(i);
            boolean matchFound = true;

            for(int j = 0; j < strs.length; j++) {
                if (currChar != strs[j].charAt(i)) {
                    matchFound = false;
                }
            }

            // All the strings om the array match at the i th position
            System.out.println("Match at position: " + i  + " : " + matchFound);
            if (matchFound) {
                if (beginIndex < 0) {
                    beginIndex = i;
                    endIndex = i;
                } else if (endIndex <= 0) {
                    endIndex = i;
                } else {
                    endIndex++;
                }
                System.out.println("Match indexes at position: " + i);
                System.out.println("Begin Index :" + beginIndex);
                System.out.println("End Index :" + endIndex);

                if (endIndex >= 0 && beginIndex >= 0) {
                    var tmpStr = input.substring(beginIndex, endIndex + 1);
                    System.out.println(tmpStr);
                    if (longestSubStr.length() < tmpStr.length()) {
                        longestSubStr = tmpStr;
                    }
                }
            } else {
                return longestSubStr;
            }

        }
        return longestSubStr;
    }

    private static String testLongestPrefixV2(String[] str) {
        if (str == null | str.length == 0) {
            return "";
        }
        Arrays.sort(str);
        System.out.println("Sorted Array: " + Arrays.toString(str));
        String strFirst = str[0];
        String strLast = str[str.length -1];

        int index = 0;

        while (index < strFirst.length()) {
            if (strFirst.charAt(index) == strLast.charAt(index)) {
                index++;
            } else {
                break;
            }
        }
        return strLast.substring(0, index);
    }

    public static void main(String[] args) {
        System.out.println("String easy ...");

        // Leet code 392. Is Subsequence
        String s = "aaaaaa";
        String t = "bbaaaa";

       // String s = "abc";
       // String t = "ahbgdc";

        /*
            Time complexity : O(t + s) : For each pass of t, the inner loop iterates s times. O(t + s)
            Space complexity : O(t + s) : Extra space required for the arrays
         */
        var matchFound = testIsSubsequenceV1(s, t);
        System.out.println("Match found V1: " + matchFound);

        /*
            Two pointer approach

            Time complexity : O(n) : Flow iterates through the loop only once. Each character is compared only once

            Space complexity : O(1) : No extra arrays created
         */
        matchFound = testIsSubsequenceV2(s, t);
        System.out.println("Match found V2: " + matchFound);

        System.out.println("**********************************************************");

        // Leet code 125. Valid Palindrome
        //s =" A man, a plan, a canal: Panama";
        s = "civic";

        /*
                Time complexity: O(n) : Does a single pass over the string char array

                Space complexity: O(n) : Extra space required for String manipulation
         */
        var isPalindrome = testValidPalindromeV1(s);
        System.out.println("isPalindrome: V1 " + isPalindrome);

        /*
            Recursive approach

            Time complexity: O(n): Single pass over the string char array

            Space complexity: O(n): Extra space required for the recursion stack
         */
        //s = "civic";
        s =" A man, a plan, a canal: Panama";
        s = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        int beginIndex = 0;
        int endIndex = s.length() -1;
        isPalindrome = testValidPalindromeV2(s, beginIndex, endIndex);
        System.out.println("isPalindrome: V2 " + isPalindrome);

        System.out.println("**********************************************************");
        // Leet code 14. Longest Common Prefix
       // String [] str = {"flower","fkow"};
        String [] str = {"flower","flow","flight"};

        /*
            Brute Force Approach

            Time complexity: O(m * n) n = length of the shortest string anf m = length of the array

            Space complexity: O(1)

         */

        var longestPreFix = testLongestPrefixV1(str);
        System.out.println("longestPreFix V1: " + longestPreFix);

        /*
            Uses the concept of Sorting of Array first

            Time complexity :
                            Sorting the array takes O(n log n) time
                            Loop iterates over n elements of the shortest string
                            Total time : O (n log n + m)

            Space complexity : O(1) : Constant space

         */
        str = new String[]{"flower","flow","flower"};
        longestPreFix = testLongestPrefixV2(str);
        System.out.println("longestPreFix V2: " + longestPreFix);
        System.out.println("**********************************************************");

    }




}
