package org.self.yahoo.leetcode75.greedy;

public class Medium {


    private static int testJumpGameIIV2(int[] nums) {
        int destination = nums.length - 1;
        int maxCoverage = 0;
        int lastJumpIndex = 0;
        int jumps = 0;

        if (nums.length == 1) {
            return jumps;
        }

        for (int i = 0; i <= destination; i++) { // O(n)
            int currJump = nums[i];
            maxCoverage = Math.max(maxCoverage, i + currJump);

            if (i == lastJumpIndex) {
                lastJumpIndex = maxCoverage;
                jumps++;
            }

            if (lastJumpIndex >= destination) {
                return jumps;
            }
        }

        return -1;
    }


    public static void main(String[] args) {
        System.out.println("Greedy ......Medium ...");
        // Leet code 45. Jump Game II
        int[] nums = {2, 3, 1, 1, 4};

        /*
            Approach: Greedy
            Time complexity: O(n) => Single pass over the nums[] and greedily picking the max jump reach at each level

            Space complexity: O(1) => No extra auxiliary space required
         */
        int result = testJumpGameIIV2(nums);
        System.out.println("testJumpGameIIV2: " + result);

        /*
            Approach: Using recursion: NEED TO DEEP DIVE

            Time complexity: O(n * n) => Exponential

            Space complexity: O(n): Recursion tack depth: O(n)
         */
        result = testJumpGameIIV1(nums);
        System.out.println("testJumpGameIIV1: " + result);
    }

    private static int testJumpGameIIV1(int[] nums) {
        return testJumpGameIIV1(nums, 0);
    }

    private static int testJumpGameIIV1(int[] nums, int index) {

        if (index >= nums.length - 1) {
            return 0;
        }

        if (nums[index] == 0) {
            return Integer.MAX_VALUE;
        }
        int minJumps = Integer.MAX_VALUE;

        for (int step = 1; step <= nums[index]; step++) {
            int jumps = testJumpGameIIV1(nums, index + step);

            if (jumps != Integer.MAX_VALUE) {
                minJumps = Math.min(minJumps, 1 + jumps);
            }
        }

        return minJumps;

    }


}


//class Solution {
//    public int jump(int[] nums) {
//        int targetIndex = nums.length - 1;
//        int totalJumps = 0;
//        int coverage = 0;
//        int nextJumpIndex = 0;
//
//        if (nums.length == 1) {
//            return 0;
//        }
//
//        for (int i = 0; i < nums.length; i++) {
//            coverage = Math.max(coverage, i + nums[i]);
//
//            if (i == nextJumpIndex) {
//                nextJumpIndex = coverage;
//                totalJumps++;
//
//
//                if (coverage >= targetIndex) {
//                    return totalJumps;
//                }
//            }
//        }
//
//        return totalJumps; // COndition not reachable
//
//    }
//}
