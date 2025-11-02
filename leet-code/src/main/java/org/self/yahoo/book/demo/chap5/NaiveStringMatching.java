package org.self.yahoo.book.demo.chap5;

import java.util.ArrayList;
import java.util.List;

public class NaiveStringMatching {
    /*
        Approach : Sliding window : Brute Force
        Time complexity : O(n * m) = O(n)

        Space Complexity : O(1) : No extra space during execution
     */
    private static List<Integer> match(char[] textArray, char[] patternArray) {
        List<Integer> shifts = new ArrayList<>();
        if (textArray == null && patternArray == null) {
            return shifts;
        }
        if (textArray.length == 0 && patternArray.length == 0) {
            return shifts;
        }
        int patternArrLength = patternArray.length;
        int deltaLength = textArray.length - patternArrLength + 1;

        if (deltaLength < 0) {
            System.out.println("Invalid Input...");
            return shifts;
        }


        for (int i = 0; i < deltaLength; i++) { // Loops through the text array
            int j;
            for (j = 0; j < patternArray.length; j++) {
                if (textArray[i + j] != patternArray[j]) {
                    break;
                }
            }
            if (j == patternArrLength) {
                shifts.add(i);
            }
        }
        return shifts;
    }

    public static void main(String[] args) {
        System.out.println("NaiveStringMatching...");
        String text = "ACAABCAABC";
        String pattern = "ABC";

        //  Approach : Sliding window : Brute Force
        var matchPosList = match(text.toCharArray(), pattern.toCharArray());
        for (int matchPositions: matchPosList) {
            System.out.println(matchPositions);
        }
    }


}
