package org.self.yahoo.leetcode75.hashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Medium {

    private static List<List<String>> testGroupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> mapA = new HashMap<>();

        for (String item : strs) { // O(n)
            char [] chrArr = item.toCharArray();
            Arrays.sort(chrArr); // O(n log n)

            String key = new String(chrArr);
            List<String> value = mapA.getOrDefault(key, new ArrayList<String>());
            value.add(item);
            mapA.put(key, value);
        }

        for (List<String> item : mapA.values()) {
            result.add(item);
        }
        return result;
    }

    private static int testLongestConsecutiveSequence(int[] nums) {

        if (nums.length == 0) {
            return 0;
        }

        int globalSeq = 0;

        Map<Integer, Boolean> seqMap = new HashMap<>();

        for (int item : nums) {
            seqMap.put(item, false);
        }

        for (int item : nums) {
            int localSeq = 0;

            int tempNum = item;

            while (seqMap.containsKey(tempNum) && !seqMap.get(tempNum)) {
                localSeq++;
                seqMap.put(tempNum, true);
                tempNum += 1;
            }

            tempNum = item - 1;

            while (seqMap.containsKey(tempNum) && !seqMap.get(tempNum)) {
                localSeq++;
                seqMap.put(tempNum, true);
                tempNum -= 1;
            }
            globalSeq = Math.max(globalSeq, localSeq);
        }
        return globalSeq;
    }

    public static void main(String[] args) {
        System.out.println("Hash table ...Medium ..");

        // Leet code 49. Group Anagrams
        String [] strs = {"eat","tea","tan","ate","nat","bat"};

        /*
            Time complexity: O(n * m log m)
                             Sorting each string: O (m log m)
                             Looping through n elements -n the nums[] : O(n)
                             Total t/c for all strings: O(n * m log m)

            Space complexity: O(m * n)
                              HashMap storage: A worst case, each string is sorted and stored in its own group, storing the string itself as key and values.
                                               No grouping scenario exists: O(m * n)
                              Result list: O(n): Stores the result strings in a list with or without any grouping

         */
        List<List<String>> result = testGroupAnagrams(strs);
        System.out.println("testGroupAnagrams: " + result);

        // Leet code 128. Longest Consecutive Sequence
        int [] nums = {100,4,200,1,3,2};

        /*
            Time complexity: O(n)
                             Single pass over the nums[]: O(n)
                             Inner while loop ensures that each element is visited only once and does not add to the t/c
                             Final t/c: O(n)

            Space complexity: O(n)
                              Extra compute space required to build the seqMap
         */
        int longSeq = testLongestConsecutiveSequence(nums);
        System.out.println("testLongestConsecutiveSequence: " + longSeq);
    }
}




