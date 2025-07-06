package org.self.yahoo.leetcode.greedy;

public class Medium {
    private static boolean testJumpGame(int[] nums) {
        int targetIndex = nums.length -1;
        int size = nums.length - 1;
        boolean isReachable = true;

        for (int i = size -1; i >=0 ; i--) {

            if (i + nums[i] < targetIndex) {
                isReachable = false;
            } else {
                isReachable = true;
                targetIndex = i;
            }

        }
        return isReachable;
    }

    private static int testJumpGameII(int[] nums) {
        int targetIndex = nums.length -1;
        int coverage = 0;
        int lastJumpIndex = 0;
        int totalJumps = 0;

        for (int i = 0; i < targetIndex; i++) {
            coverage = Math.max(coverage, i + nums[i]);

            if (i == lastJumpIndex) {
                lastJumpIndex = coverage;
                totalJumps++;


                if (coverage >= targetIndex) {
                    return totalJumps;
                }


            }
        }
        return totalJumps;
    }

    public static void main(String[] args) {
        // Leet code 55. Jump Game
        int [] nums = {2,3,1,1,4};

        /*
            Approach used here is: Greedy approach where the array is traversed from the rear and checked for each i - 1 index if the targetIndex is reachable;

            Time complexity: O(n): nums[] is traversed linearly

            Space complexity: O(1): No extra compute space is used
         */
        var isReachable = testJumpGame(nums);
        System.out.println("isReachable: " + isReachable);

        /*
            Approach is using a Greedy algo.
            BruteForce/Recursive/Dynamic Programming: Provides solution but with a a high exponeential t/c

            Time complexity: O(n): nums[] is traversed linearly

            Space complexity: O(1): No extra compute space required

         */

        // Leet code 45. Jump Game II

        int numberOfJumps = testJumpGameII(nums);
        System.out.println("testJumpGameII: " + numberOfJumps);
    }

}

