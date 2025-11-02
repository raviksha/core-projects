package org.self.yahoo.leetcode.prefixSum;

import java.util.HashMap;
import java.util.Map;

public class Easy {
    private static int testRangeSumQueryV1(int[] nums, int left, int right) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }

        int sum = 0;
        for (int i = left; i <= right; i++) {
            sum += nums[i];
        }
        return sum;
    }


    private static int testRangeSumQueryV2(int[] nums, int left, int right) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        Map<Integer, Integer> hashMap = new HashMap<>();
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            hashMap.put(i, sum);
        }
        /*
            At this point the hashMap contains the sum of elements up to the index point
            In order to look up the range sum, we need to pick the sum at the right range and subtract with the sum at the left range to get the
            exact sum between the left and right range, except for the 0th index
         */

        if (left == 0) {
            return hashMap.get(right);
        } else {
            left--; // This is required to include the left index while calculating the range sum queries
            return hashMap.get(right) - hashMap.get(left);
        }

    }

    public static void main(String[] args) {
        System.out.println("Prefix sum Easy ..");

        System.out.println("*************************** Range Sum Query - Immutable ******************************");
        // Leet code 303. Range Sum Query - Immutable

        int [] nums = {-2,0,3,-5,2,-1};
        int left = 2;
        int right = 5;
        /*
            Time complexity: O(n) :
                                Total time: O(n) => Iterates over the nums array, in the worst case
                                Query time: O(n) => O(n) times to calculate the sum

            Space complexity: O(1) : Constant space.
         */
        int sum = testRangeSumQueryV1(nums, left, right);
        System.out.println("testRangeSumQuery V1: " + sum);

        /*
            Time complexity:
                             Total time: O(n) : Iterates over the n items in the nums []; O(n).
                             Query time: Constant time for map look up : O(1)

            Space complexity: O(n) : Map to store the total sum at each index
         */
        sum = testRangeSumQueryV2(nums, left, right);
        System.out.println("testRangeSumQuery V2: " + sum);
    }
}
