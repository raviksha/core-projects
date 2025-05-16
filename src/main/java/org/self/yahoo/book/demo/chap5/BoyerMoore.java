package org.self.yahoo.book.demo.chap5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoyerMoore {

    private List<Integer> match(String P, String T) {

        if (P == null || T == null) {
            System.out.println("Invalid input ...");
            System.exit(0);
        }

        Map<Character, Integer> badCharacterTable = BadCharacterRule.buildBadCharacterTable(P);
        int[] goodSuffixTable = GoodSuffixRule.computeGoodSuffixTable(P);

        int patternLength = P.length();
        int textLength = T.length();

        if (patternLength > textLength) {
            System.out.println("Invalid input...");
            System.exit(0);
        }
        boolean isMatch = true;
        List<Integer> shift = new ArrayList<>();
        int numShift = 0;
        for (int i = 0; i < textLength - patternLength +1 ; i+= numShift) {
            isMatch = true;
            numShift = 0;
            for (int j = patternLength -1 ; j >= 0 ; j--) {
                if (T.charAt(i + j) != P.charAt(j)) {
                    isMatch = false;
                    var badCharScore = getBadCharScore(badCharacterTable, i, j, patternLength, T);
                    var goodSuffixScore = getGoodSuffixScore(goodSuffixTable, j);
                    numShift = fetchNumOfShifts(badCharScore, goodSuffixScore, j);
                    break;
                }
            }

            if (isMatch) {
                numShift = goodSuffixTable[0];
                shift.add(i);
            }
        }
        return shift;
    }

    private int fetchNumOfShifts(int badCharScore, int goodSuffixScore, int k) {
        int d1;
        int d2;
        int d;
        if (k == 0) {
            d =  badCharScore;
        } else {
            d1 = Math.max(badCharScore - k, 1);
            d2 = goodSuffixScore;
            d = Math.max(d1, d2);
        }
        return d;
    }

    private int getGoodSuffixScore(int[] goodSuffixTable, int j) {
        return goodSuffixTable[j];
    }

    private int getBadCharScore(Map<Character, Integer> badCharacterTable, int i, int j, int patternLength, String T) {
        return badCharacterTable.getOrDefault(T.charAt(i + j), patternLength);
    }


    public static void main(String[] args) {
        System.out.println("BoyerMoore.....");
        BoyerMoore boyerMoore = new BoyerMoore();
        List<Integer> matches = boyerMoore.match("rabrabracad", "abacadabrabracabracadabrabrabracad");

        for (int match : matches) {
            System.out.println("Match found at positions: " + match);
        }
    }


}
