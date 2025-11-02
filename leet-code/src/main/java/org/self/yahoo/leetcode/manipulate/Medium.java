package org.self.yahoo.leetcode.manipulate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Medium {

    private static int [] testSingleNumberIII(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int [] result = new int [2];
        for (int curVal: nums) {
            freqMap.put(curVal, freqMap.getOrDefault(curVal, 0) + 1);
        }

        int index = 0;

        for (Map.Entry<Integer, Integer> entry: freqMap.entrySet()) {
            if (entry.getValue() == 1) {
                result[index] = entry.getKey();
                index++;
            }
        }

        return result;
    }

    /*
        Uses the approach of keeping a frequency map

        Time complexity: O(n)
                         Map put() and get() t/c : O(1) * n elements = O(n)

        Space complexity: O(n)
                          Stores the frequency map for O(n - m + 2) elements
                          n = total elements in nums[]
                          m = # of duplicates
                          2 = non-duplicate elements
                          Concluding: rounded off to O(n)

     */
    public static void main(String[] args) {
        System.out.println("Medium ..Bit manipulation ...");

        // Leet code 260. Single Number III
        int [] nums = {1,2,1,3,2,5};
        var result = testSingleNumberIII(nums);
        System.out.println("testSingleNumberIII: " + Arrays.toString(result));

    }



}
