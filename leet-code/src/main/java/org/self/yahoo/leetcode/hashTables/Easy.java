package org.self.yahoo.leetcode.hashTables;

import java.util.HashMap;
import java.util.Map;

public class Easy {

    private static boolean testIsomorphicStrings(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        Map<Character, Character> characterMap = new HashMap<>();
        Map<Character, Character> reverseMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char key = s.charAt(i);
            char value = t.charAt(i);

            if (!characterMap.containsKey(key)) {
                characterMap.put(key, value);
                if (reverseMap.containsKey(value)) {
                    int tmpKey = reverseMap.get(value);
                    if (tmpKey != key) {
                        return false;
                    }
                } else {
                    reverseMap.put(value, key);
                }

            } else {
                if (reverseMap.containsKey(value)) {
                    char rVal = reverseMap.get(value);
                    if (rVal != key) {
                        return false;
                    }
                }
                char tmpValue = characterMap.get(key);
                if (tmpValue != value) {
                    return false;
                }

            }

        }
        return true;
    }

    private static boolean testContainsDuplicatesV1(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        for(int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    int diff = j - i;
                    if (diff <= k) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean testContainsDuplicatesV2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        Map<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int currKey = nums[i];

            if (!hashMap.containsKey(currKey)) {
                hashMap.put(currKey, i);
            } else {
                int currIndex = hashMap.get(currKey);

                if (i - currIndex <= k) {
                    return true;
                } else {
                    hashMap.put(currKey, i);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Hash Tables easy..");
        System.out.println("************************************ 205. Isomorphic Strings");
        // Leet code 205. Isomorphic Strings
        //String s = "badc";
        //String t = "baba";

       // String s = "paper";
       // String t = "title";

        String s = "egg";
        String t = "add";

        /*
            Time complexity: O(n) Single pass over the string of length : n

            Space complexity: O(n) Maps stores a max of n character mapping
         */
        var isIsomorphic = testIsomorphicStrings(s, t);
        System.out.println("isIsomorphic: " + isIsomorphic);

        System.out.println("************************************ 219. Contains Duplicate II");
        // Leet code 219. Contains Duplicate II
        /*
            Brute force approach

            Time complexity : O(n ^ 2) : For each iteration of the outer loop element n, the inner loop iterates n times : O(n * n)

            Space complexity: O((1) : No extra space required
         */
        // int [] arr = new int [] {1,2,3,1,2,3};
        // int k = 2;

        int [] arr = new int [] {1,0,1,1};
        int k = 2;
        var containsDuplicates = testContainsDuplicatesV1(arr, k);
        System.out.println("containsDuplicates V1: " + containsDuplicates);

        /*
            Time complexity: O(n + m): n elements and m map look ups

            Space complexity: O(n): Map to store the element last index diff
         */
        containsDuplicates = testContainsDuplicatesV2(arr, k);
        System.out.println("containsDuplicates V2: " + containsDuplicates);
    }
}
