package org.self.yahoo.book.demo.chap5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BadCharacterRule {
    public static Map<Character, Integer> buildBadCharacterTable(String pattern) {
        Map<Character, Integer> badCharacterMap = new HashMap<>();
        int patternLength = pattern.length();
        for (int i = 0; i < patternLength; i++) {
            char currChar = pattern.charAt(i);
            int maxShift = Math.max(1, (patternLength - i - 1));
            badCharacterMap.put(currChar, maxShift);
        }
        return badCharacterMap;
    }

    /*
        https://www.youtube.com/watch?v=hXqRLILcC1k
     */
    private List<Integer> match(String P, String T) {
        List<Integer> shifts = new ArrayList<>();

        int textLength = T.length();
        int patternLength = P.length();

        if (patternLength > textLength) {
            System.out.println("Invalid input ...");
            System.exit(-1);
        }

        var badCharTable = buildBadCharacterTable(P);
        int numOfSkips;
        for (int i = 0; i < (textLength - patternLength + 1); i += numOfSkips) {
            numOfSkips = 0;
            for (int j = patternLength - 1; j >= 0; j--) {
                if (T.charAt(i + j) != P.charAt(j)) {
                    // In case of mismatch the pattern string needs to shift my the number of position matching the character in the Bad character table
                    // If the char is absent then the shift is equal to the length of the pattern string
                    numOfSkips = badCharTable.getOrDefault(T.charAt(i + j), patternLength);
                    break;
                }
            }

            // Control reaching at this point with numOfSkips == 0 indicates that the pattern found a complete match in the String T
            if (numOfSkips == 0) {
                shifts.add(i);
                numOfSkips = 1;
            }
        }
        return shifts;
    }


    public static void main(String[] args) {
        System.out.println("BadCharacterRule.....");
        BadCharacterRule bcr = new BadCharacterRule();
        List<Integer> matchList = bcr.match("rabrabracad", "abacadabrabracabracadabrabrabracad");

        for (int matchPosition : matchList) {
            System.out.println("Match found at positions: " + matchPosition);
        }
    }
}
