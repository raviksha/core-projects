package org.self.yahoo.leetcode75.bit;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Medium {
    public static void main(String[] args) {
        System.out.println("Leet code ....Medium..");

        // Leet code 260. Single Number III
        int [] nums = {1,2,1,3,2,5};

        /*
            Time complexity: O(n): Does a single pass over the nums []

            Space complexity: O(1): Constant space
         */
        int [] result = testSingleNumberIII(nums);
        System.out.println("testSingleNumberIII: " + Arrays.toString(result));
    }

    private static int[] testSingleNumberIII(int[] nums) {
        Set<Integer> hashSet = new HashSet<>();
        int [] result = new int[2];
        for (int item : nums) { // O(n)
            if (hashSet.contains(item)) { //O(1)
                hashSet.remove(item); //O(1)
            } else {
                hashSet.add(item); // O(1)
            }
        }
        int index = 0;
        for (int item : hashSet) {
            result[index] = item;
            index++;
        }
        return result;
    }
}


//class Solution {
//    public int[] singleNumber(int[] nums) {
//        Map<Integer, Integer> freqMap = new HashMap<>();
//        int [] result = new int [2];
//        for (int curVal: nums) {
//            freqMap.put(curVal, freqMap.getOrDefault(curVal, 0) + 1);
//        }
//
//        int index = 0;
//
//        for (Map.Entry<Integer, Integer> entry: freqMap.entrySet()) {
//            if (entry.getValue() == 1) {
//                result[index] = entry.getKey();
//                index++;
//            }
//        }
//
//        return result;
//
//    }
//}
