package org.self.yahoo.leetcode75.strings;

import java.util.Arrays;

public class Medium {

    private static String testReverseWordsInString(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] strArr = s.trim().split(" ");
        int n = strArr.length - 1;
        for (int i = n; i >= 0; i--) {
            String str = strArr[i];
            if (!str.isEmpty()) {
                stringBuilder.append(str);
                if (i != 0) {
                    stringBuilder.append(" ");
                }
            }

        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println("Strings .. Medium ....");

        // Leet code 151. Reverse Words in a String
        String s = "  hello world  ";
        /*
            Time complexity: O(n): Single pass over the split str[]
                             trim() = O(n) where n is the length of the input string
                             split(" ")= O(n) splitting scans the entire string once
                             loop over strArr: O(n)
                             StringBuilder append(): O(1) constant time

            Space complexity: O(2n) => O(n): Extra space required to store the str[] and result string object
         */
        String result = testReverseWordsInString(s);
        System.out.println("testReverseWordsInString:" + result + "|");
    }


}
