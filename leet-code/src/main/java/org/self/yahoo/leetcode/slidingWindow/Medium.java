package org.self.yahoo.leetcode.slidingWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Medium {


    private static List<Integer> testFindAllAnagramsV1(String s, String p) {
        List<Integer> indexList = new ArrayList<>();
        int sLength = s.length();
        int pLength = p.length();

        char [] c = p.toCharArray();
        Arrays.sort(c); // TC : O(n log n)
        p = new String(c);


        for (int i = 0; i <= sLength - pLength;) { // TC O(n)
            char [] tmp = new char [pLength];

            int index= 0;
            for (int j = i; j < i + pLength;) { // TC O(m)
                while (index < pLength) {
                    tmp[index] = s.charAt(j);
                    j++;
                    index++;
                }
                Arrays.sort(tmp); // TC O(m log m)

                String tmpStr = new String(tmp);

                if (tmpStr.equals(p)) {
                    indexList.add(i);
                }
            }
            i = i + 1;
        }
        return indexList;
    }

    private static List<Integer> testFindAllAnagramsV2(String s, String p) {
        List<Integer> indexList = new ArrayList<>();
        int left = 0;
        int right = p.length();

        char[] c = p.toCharArray();
        Arrays.sort(c); // TC : O(n log n)
        p = new String(c);

        char[] tmpArr = new char[p.length()];

        while ((right <= s.length())) { // TC O(m)

            String tmpStr = s.substring(left, right);
            tmpArr = tmpStr.toCharArray();
            Arrays.sort(tmpArr); // TC : O(n log n)
            tmpStr = new String(tmpArr);

            if (tmpStr.equals(p)) {
                indexList.add(left);
            }
            left = left + 1;
            right = right + 1;
        }
        return indexList;
    }

    private static List<Integer> testFindAllAnagramsV3(String s, String p) {
        List<Integer> indexList = new ArrayList<>();

        Map<Character, Integer>  pMap = new HashMap<>();
        Map<Character, Integer> sMap = new HashMap<>();

        for (char c : p.toCharArray()) {
            pMap.put(c, pMap.getOrDefault(c, 0) + 1); // O(1)
        }
        int windowSize = p.length();

        for (int i = 0; i < s.length(); i++) { // O(n)
            char currChar = s.charAt(i);
            sMap.put(currChar, sMap.getOrDefault(currChar, 0) + 1);

            if (i >= windowSize) {
                char tmpChar = s.charAt(i - windowSize);
                int freq = sMap.get(tmpChar);
                freq = freq - 1;
                if (freq == 0) {
                    sMap.remove(tmpChar);
                } else {
                    sMap.put(tmpChar, sMap.get(tmpChar) -1);
                }
            }
            if (sMap.equals(pMap)) { // O(k) : Map equals comparisons take k iterations over each element
                indexList.add(i - windowSize + 1);
            }
        }
        return indexList;
    }

    private static boolean testPermutationInStringV1(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        Map<Character, Integer> s1Map = new HashMap<>();
        Map<Character, Integer> s2Map = new HashMap<>();

        for (char c: s1.toCharArray()) {  // O(m)
            s1Map.put(c, s1Map.getOrDefault(c, 0) + 1);
        }

        System.out.println("s1Map: " + s1Map);

        for (int i = 0; i < s2.length(); i++) { // O(n)
            char currChar = s2.charAt(i);

            if (s1Map.containsKey(currChar)) {
                s2Map.put(currChar, s2Map.getOrDefault(currChar, 0) + 1);
                int ctr = s1.length() - 1;
                int index = i + 1;
                while (ctr > 0 && index < s2.length()) { // O(m)
                    currChar = s2.charAt(index);
                    s2Map.put(currChar, s2Map.getOrDefault(currChar, 0) + 1);
                    index++;
                    ctr--;
                }
                System.out.println("s2Map: " + s2Map);

                if (s2Map.equals(s1Map)) { // Hashmap comparison takes O(m)
                    return true;
                } else {
                    s2Map.clear();
                }
            }

        }
        return false;
    }

    private static boolean testPermutationInStringV2(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        Map<Character, Integer> s1Map = new HashMap<>();
        Map<Character, Integer> s2Map = new HashMap<>();

        for (char c : s1.toCharArray()) {  // O(m)
            s1Map.put(c, s1Map.getOrDefault(c, 0) + 1);
        }
        int windowLength = s1.length();

        for (int i = 0; i < windowLength; i++) { // O(m)
            char currChar = s2.charAt(i);
            s2Map.put(currChar, s2Map.getOrDefault(currChar, 0) + 1);
        }

        if (s1Map.equals(s2Map)) {
            return true;
        }

        for (int i = windowLength; i < s2.length(); i++) { // O(n - m)
            char newChar = s2.charAt(i);
            char oldCHar = s2.charAt(i - windowLength);

            if (s2Map.get(oldCHar) == 1) {
                s2Map.remove(oldCHar);
            } else {
                s2Map.put(oldCHar, s2Map.get(oldCHar) -1);
            }
            s2Map.put(newChar, s2Map.getOrDefault(newChar, 0) + 1);
            if (s1Map.equals(s2Map)) { // O(m)
                return true;
            }
        }
        return false;
    }

    private static boolean testPermutationInStringV3(String s1, String s2) {

        if (s1.length() > s2.length()) {
            return false;
        }

        char s1Arr [] = new char[26];
        char s2Arr [] = new char[26];

        for (char c : s1.toCharArray()) { // O(m)
            s1Arr[c - 'a']++;
        }
        int windowLength = s1.length();

        for (int i = 0; i < windowLength; i++) { // O(m)
            char currChar = s2.charAt(i);
            s2Arr[currChar - 'a']++;
        }
        if (Arrays.equals(s1Arr, s2Arr)) {  // O(m)
            return true;
        }
        for (int i = windowLength; i < s2.length(); i++) { // O(n - m)
            int newChar = s2.charAt(i);
            int oldChar = s2.charAt(i - windowLength);

            s2Arr[newChar - 'a']++;
            s2Arr[oldChar - 'a']--;

            if (Arrays.equals(s1Arr, s2Arr)) { // O(26)
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println("Sliding Window - Fixed Size .. Medium");

        // Leet code 438. Find All Anagrams in a String
        // c b a e b a b a c d
   //    String s = "cbaebabacd";
   //    String p = "abc";

       String s = "abab";
       String p = "ab";

        /*
            Brute force approach

            Time complexity:
                           Outer loop runs : n - m times (Length of string s - length of string p)
                           Inner loop runs for m times (length of string p)
                           Total so far : O(n * m)

                           Sorting array of m elements : O(m log m) : Avg tc for merge sort

                           Final total time complexity : O(n * m log m)

            Space complexity:
                            char [] conversion takes O(m) space for string p
                            Extra space to index position is worst case is O(n)
                            Total space complexity : O(m + n)
         */
        var indexList = testFindAllAnagramsV1(s, p);
        System.out.println("indexList V1: " + indexList);

        /*
            Time complexity:
                            while loop iterates n times : O(n)
                            sorting of string on length m in each loop iteration takes O(m log m)
                            Total time complexity : O(n * m log n)

            Space complexity: O(m + n)
                               n elements in worst case stored in List for index positions
                               char [] conversion takes O(m) space for storing string of length p
         */
        indexList = testFindAllAnagramsV2(s, p);
        System.out.println("indexList V2: " + indexList);

        /*
        Time complexity:
                        O(n): To iterate over the n elements of the s string char []
                        O(k): k iterations over the k unique elements in pmap and smap
                        Total: O(n * k)

        Space complexity: O(k): For k unique elements for both pMap and sMap
                          O(n): Index list to store anagram begin indices
     */

        indexList = testFindAllAnagramsV3(s, p);
        System.out.println("indexList V3: " + indexList);


        // Leet code 567. Permutation in String
        String s2 = "eidbaooo";
        String s1 = "ab";

        /*
            Brute Force approach
            Time complexity: O(m * n)
                             Outer loop iterates through the complete string s2 length: O(n)
                             For each character of s1 matching in s2, the inner loop iterates s1 length : O(m)

            Space complexity: O(m) => Two Hash frequency maps storing the occurrences of each character of string 1 of length m
         */
        var result = testPermutationInStringV1(s1, s2);
        System.out.println("Permutation in String result: V1: " + result);

        /*
            Sliding window approach, using Maps : Used when the string is a combination of mixed characters
            Time complexity: O(n + m)
                                => Iteration for the frequency map for s1 : O(m)
                                => Iteration over the length of string s2 : O(n)


            Space complexity: O(m) : Two frequency map stores frequency of s1 length of characters
                                     Worst case : O(26)
         */

        result = testPermutationInStringV2(s1, s2);
        System.out.println("Permutation in String result: V2: " + result);

        /*
            Sliding window using arrays : Used when the string contains either all lowercase or uppercase characters

            Time complexity: O(n + m)
                             => s1 string subArr iteration: O(m)
                             => s2 string subArr iteration: O(n - m)
                             => Arrays.equals : Constant time : O(26) : worst case
                             Concluded to : O(m = n)

            Space complexity: O(26) => Concluded to O(1)

         */
        result = testPermutationInStringV3(s1, s2);
        System.out.println("Permutation in String result: V3: " + result);


        // Leet code 2461. Maximum Sum of Distinct Subarrays With Length K
        int [] nums = {9,9,9,1,2,3};
        int k = 3;

        /*
            Two pointer approach with sliding window
            Time complexity: O(n): One pass over the nums[] array

            Space complexity: O(k): Max k elements ine frequency map to check for duplicates
         */
        long sum = testSumOfDistinctSubArrays(nums, k);
        System.out.println("Sum: " + sum);
    }

    private static long testSumOfDistinctSubArrays(int[] nums, int k) {
        int maxSum = 0;
        int sum = 0;

        int left = 0;
        int right = 0;

        Map<Integer, Integer> freqMap = new HashMap<>();

        while (right < k) {
            int currVal = nums[right];
            freqMap.put(currVal, freqMap.getOrDefault(currVal, 0) + 1);
            sum += currVal;
            right++;
        }

        if (freqMap.size() == k) {
            maxSum = Math.max(maxSum, sum);
        }

        int freq = freqMap.get(nums[left]);
        freq = freq -1;
        if (freq == 0) {
            freqMap.remove(nums[left]);
        } else {
            freqMap.put(nums[left], freq);
        }
        sum -= nums[left];
        left++;

        while (right < nums.length) { // O(n)
            int leftVal = nums[left];
            int rightVal = nums[right];

            freqMap.put(rightVal, freqMap.getOrDefault(rightVal, 0) + 1);

            sum += rightVal;

            if (freqMap.size() == k) {
                maxSum = Math.max(maxSum, sum);
            }


            freq = freqMap.get(nums[left]);
            freq = freq -1;
            if (freq == 0) {
                freqMap.remove(nums[left]);
            } else {
                freqMap.put(nums[left], freq);
            }
            sum -= leftVal;
            left++;
            right++;

        }
        return maxSum;
    }


}
