package org.self.yahoo.leetcode75.prefixsum;

import java.util.HashMap;
import java.util.Map;

public class Medium {
    public static void main(String[] args) {
        System.out.println("Prefix sum ... Medium...");
        // Leet code 560. Sub array Sum Equals K
        int [] nums = {1,2,3};
        int k = 3;

        /*
            Time complexity: O(n) Single pass over the nums []

            Space complexity: O(n) Extra compute space to store the prefix sum map
         */
        int result = testSubArraySumEqualsK(nums, k);
        System.out.println("testSubArraySumEqualsK: " + result);
    }

    private static int testSubArraySumEqualsK(int[] nums, int k) {
        Map<Integer, Integer> prefixMap = new HashMap<>();
        int sum = 0;
        int count = 0;
        prefixMap.put(0, 1);
        for (int item : nums) { // O(n)
            sum += item;
            int key = sum - k;

            if (prefixMap.containsKey(key)) { //O(1)
                count += prefixMap.get(key);
            }
            prefixMap.put(sum, prefixMap.getOrDefault(sum, 0) + 1); //O(1)
        }
        return count;
    }
}


//class Solution {
//    public int subarraySum(int[] nums, int k) {
//        if (nums == null || nums.length == 0) {
//            return -1;
//        }
//        Map<Integer, Integer> hashMap = new HashMap<>();
//        int count = 0;
//        int sum = 0;
//
//        hashMap.put(0, 1); // Handles case when the current num[i] element == k
//
//        for (int i = 0; i < nums.length; i++) {
//            int key = nums[i];
//            sum += key;
//
//            if (hashMap.containsKey(sum - k )) {
//                count += hashMap.get(sum - k);
//            }
//
//            hashMap.put(sum, hashMap.getOrDefault(sum, 0) + 1);
//        }
//        return count;
//
//    }
//}