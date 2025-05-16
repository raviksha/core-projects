package org.self.yahoo.book.demo.chap5;

import java.util.ArrayList;
import java.util.List;

public class BruteForcePatternMatch {

    /*
        Time complexity :
                    Best case   O(n) : If matches are rare
                    Worst case :O(mn) : Control iterates through the Text string m * n times
        Space complexity : O(1) : No extra storage
     */

    private List<Integer> match(String P, String T) {
        if (P == null || T == null) {
            System.out.println("Invalid input ....");
            System.exit(1);
        }

        int patternLength = P.length();
        int textLength = T.length();

        if (patternLength > textLength) {
            System.out.println("Invalid pattern ...");
            System.exit(1);
        }

        List<Integer> shift = new ArrayList<>();
        boolean isMatch = true;
        int counter = 0;

        for (int i = 0; i < textLength - patternLength + 1; i++) {
            isMatch = true;
            counter = 0;

            while (counter < patternLength) {
               if (T.charAt(i + counter) != P.charAt(counter)) {
                   isMatch = false;
                   break;
               }
                counter++;
            }

            if (isMatch) {
                shift.add(i);
            }

        }
        return shift;
    }

    public static void main(String[] args) {
        System.out.println("BruteForcePatternMatch .....");

        String T = "ABABABABAC";
        String P = "ABA";

        BruteForcePatternMatch bruteForcePatternMatch = new BruteForcePatternMatch();
        var matchList = bruteForcePatternMatch.match(P, T);

        for (int match : matchList) {
            System.out.println("Pattern match found at: " + match);
        }
    }


}
