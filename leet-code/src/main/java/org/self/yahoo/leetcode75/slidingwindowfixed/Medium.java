package org.self.yahoo.leetcode75.slidingwindowfixed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Medium {

    private static List<Integer> testFindAllAnagramsV1(String s, String p) {

        List<Integer> result = new ArrayList<>();

        if (p.length() > s.length()) {
            return result;
        }

        Map<Character, Integer> pMap = new HashMap<>();


        for (char item : p.toCharArray()) { // O(k)
            pMap.put(item, pMap.getOrDefault(item, 0) + 1);
        }

        for (int i = 0; i <= s.length() - p.length(); i++) { // O(m)
            int ctr = 0;
            int j = i;
            Map<Character, Integer> sMap = new HashMap<>();

            while(ctr < p.length()) { // O(k)
                char currChar = s.charAt(j);
                sMap.put(currChar, sMap.getOrDefault(currChar, 0) + 1);
                ctr++;
                j++;
            }

            if (sMap.equals(pMap)) { // O(k)
                result.add(i);
            }

        }

        return result;

    }

    private static List<Integer> testFindAllAnagramsV2(String s, String p) {
        List<Integer> result = new ArrayList<>();

        if (p.length() > s.length()) {
            return result;
        }

        Map<Character, Integer> pMap = new HashMap<>();
        Map<Character, Integer> sMap = new HashMap<>();

        for (char item : p.toCharArray()) { // O(k)
            pMap.put(item, pMap.getOrDefault(item, 0) + 1);
        }
        int left = 0;
        int right = 0;
        int n = s.length();
        int windowSize = p.length();

        // Initial sMap load with chars till window size
        while (right < windowSize) { // O(k)
            char currChar = s.charAt(right);
            sMap.put(currChar, sMap.getOrDefault(currChar, 0) + 1);
            right++;
        }

        while (right < n) { // O(m)

            if (pMap.equals(sMap)) {
                result.add(left);
            }

            // Remove left char from to maintain the sliding window frame
            char leftChar = s.charAt(left);
            int leftFreq = sMap.get(leftChar);
            leftFreq -= 1;

            if (leftFreq == 0) {
                sMap.remove(leftChar);
            } else {
                sMap.put(leftChar, leftFreq);
            }
            left++;
            // Add right char to the sliding window
            char rightChar = s.charAt(right);
            sMap.put(rightChar, sMap.getOrDefault(rightChar, 0) + 1);
            right++;
        }

        if (pMap.equals(sMap)) {
            result.add(left);
        }
        return result;
    }


    private static List<Integer> testFindAllAnagramsV3(String s, String p) {
        List<Integer> result = new ArrayList<>();

        if (p.length() > s.length()) {
            return result;
        }

        int [] pMap = new int[26];
        int [] sMap = new int[26];

        int windowSize = p.length();
        int left = 0;
        int right = 0;
        int n = s.length();
        for (int i = 0; i < windowSize; i++) {
            pMap[p.charAt(i) - 'a']++;
            sMap[s.charAt(i) - 'a']++;
            right++;
        }

        while (right < n) {
            if (isValidAnagram(pMap, sMap)) {
                result.add(left);
            }

            // Remove left
            char leftChar = s.charAt(left);
            sMap[leftChar - 'a']--;
            left++;

            // Add right
            char rightChar = s.charAt(right);
            sMap[rightChar - 'a']++;
            right++;
        }

        if (isValidAnagram(pMap, sMap)) {
            result.add(left);
        }
        return result;
    }

    private static boolean isValidAnagram(int[] pMap, int[] sMap) {
        for (int i = 0; i < pMap.length; i++) {
            if (pMap[i] != sMap[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Sliding window ... Medium....");

        // Leet code 438. Find All Anagrams in a String
        String s = "cbaebabacd";
        String p = "abc";

//        String s = "abab";
//        String p = "ab";

        /*
            Time complexity: O(m * k)
                             Outer loop runs: O(m)
                                For each iteration of outer loop, inner loop loops O(k) times
                             Final t/c: O(m * k)

            Space complexity: O(m + k)
                              Final s/c: O(m)
         */
        List<Integer> result = testFindAllAnagramsV1(s, p);
        System.out.println("testFindAllAnagramsV1: " + result);


        /*
            Time complexity: O(m + k): Single pass over the s[] and p[]

            Space complexity: O(m): Worst vase when all the substrings are valid anagrams
         */
        result = testFindAllAnagramsV2(s, p);
        System.out.println("testFindAllAnagramsV2: " + result);

        /*
            NOTES: Why int [] is faster than Hash Map based comparisons
            1. HashMaps has extra overheads of hash code generations, boxing, un boxing and collisions
            2. equals() needs to loop through all the elements and compare both keys and values
            3. arr[] comparison is pure look up with O(1) constant time. No hash code generations, boxing, un boxing and collisions
            4. Comparing means just looping through the arr [] 26 times. Constant time

            Time complexity: O(m + k): Each iteration in the outer loop runs a inner loop of comparison of k [] elements

            Space complexity: O(m): Worst case when all substrings could possibly be anagrams
                              pMap[]: O(1) constant space
                              sMap[]: O(1) constant space
         */

        result = testFindAllAnagramsV3(s, p);
        System.out.println("testFindAllAnagramsV3: " + result);

        // Leet code 567. Permutation in String
        String s1 = "ab";
        String s2 = "eidbaooo";

        /*
            Time complexity: O(m + n) Sliding window for the s1.length() + s2.length()

            Space complexity: O(1) Fixed size arrays of size [26] used for storing the frequency
         */
        boolean isPermutation = testPermutationsInString(s1, s2);
        System.out.println("testPermutationsInString: " + isPermutation);
    }

    private static boolean testPermutationsInString(String s1, String s2) {

        if (s1.length() > s2.length()) {
            return false;
        }

        int windowSize = s1.length();

        int [] cMap = new int[26];
        int [] pMap = new int[26];

        for (int i = 0; i < windowSize; i++) { // O(m)
            char c = s1.charAt(i);
            cMap[c - 'a']++;

            char p = s2.charAt(i);
            pMap[p - 'a']++;
        }

        int n = s2.length();
        int left = 0;
        int right = windowSize;

        while (right < n) { // O(n)

            if (Arrays.equals(pMap, cMap)) { //O(26) => O(1)
                return true;
            }

            // Remove left
            char lChar = s2.charAt(left);
            pMap[lChar - 'a']--;
            left++;

            // Add right

            char rChar = s2.charAt(right);
            pMap[rChar - 'a']++;
            right++;
        }

        if (Arrays.equals(pMap, cMap)) { //O(26) => O(1)
            return true;
        }

        return false;
    }
}
