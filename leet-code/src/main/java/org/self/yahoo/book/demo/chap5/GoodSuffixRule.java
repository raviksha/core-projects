package org.self.yahoo.book.demo.chap5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoodSuffixRule {

    public static int[] computeGoodSuffixTable(String pattern) {
        int m = pattern.length();
        int[] goodSuffix = new int[m];  // Good Suffix Shift table
        int[] suffix = new int[m];      // Suffix array

        // Step 1: Compute Suffix Array
        Arrays.fill(suffix, -1);
        Arrays.fill(goodSuffix, m); // Default shift value is pattern length

        for (int i = m - 2; i >= 0; i--) {
            int j = i;

            int temp1 = pattern.charAt(j);
            int temp2 = pattern.charAt(m - 1 - (i - j));

            while (j >= 0 && temp1 == temp2) {
                j--;
            }
            suffix[i] = i - j;
        }

        // Step 2: Populate Good Suffix Table
        for (int i = 0; i < m - 1; i++) {
            int j = m - 1 - suffix[i];
            if (suffix[i] != -1) {
                goodSuffix[j] = m - 1 - i;
            }
        }

        return goodSuffix;
    }

    private List<Integer> match(String T, String P) {
        if (P == null || T == null) {
            System.out.println("Invalid Input...");
            System.exit(-1);
        }

        int pLength = P.length();
        int tLength = T.length();

        if (pLength > tLength) {
            System.out.println("Pattern string is greater than search text");
            System.exit(-1);
        }
        int [] suffixTable = computeGoodSuffixTable(P);
        int shift = 0;
        boolean isMatch = true;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < tLength - pLength + 1; i+= shift) {
            shift = 0;
            isMatch = true;
            for (int j = pLength -1; j >=0 ; j--) {
                if (T.charAt(i + j) != P.charAt(j)) {
                    shift = suffixTable[j];
                    isMatch = false;
                    break;
                }
            }

            if (isMatch) {
                list.add(i);
                shift = suffixTable[0];
            }
        }
        return list;
    }

    public static void main(String[] args) {
        String pattern = "ABAB";
        String text = "ADABABH";

        GoodSuffixRule gst = new GoodSuffixRule();
        List<Integer> matchList = gst.match(text, pattern);

        for (int match : matchList) {
            System.out.println("Pattern occurred at positions: " + match);
        }
    }
}