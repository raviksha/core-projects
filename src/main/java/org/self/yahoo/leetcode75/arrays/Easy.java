package org.self.yahoo.leetcode75.arrays;

public class Easy {
    public static void main(String[] args) {
        System.out.println("Arrays ....easy...");

        // Leet code: 169. Majority Element
        int [] nums = {2,2,1,1,1,2,2};
        /*
            Approach: Uses Boyer–Moore Voting Algorithm
            Time complexity: O(n) Loops through the nums[] only once

            Space complexity: O(1) No extra compute space required
         */
        int result = testMajorityElement(nums);
        System.out.println("testMajorityElement: " + result);

    }

    private static int testMajorityElement(int[] nums) {
        int candidate = 0;
        int counter = 0;

        for (int item : nums) {
            if (counter == 0) {
                candidate = item;
            }

            if (item == candidate) {
                counter++;
            } else {
                counter--;
            }
        }
        return candidate;
    }
}
