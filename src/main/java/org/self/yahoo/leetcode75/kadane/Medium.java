package org.self.yahoo.leetcode75.kadane;

public class Medium {
    public static void main(String[] args) {
        System.out.println("Kadane's Algo ...Medium");

        // Leet code 53. Maximum Subarray
        int  [] nums = {-2,1,-3,4,-1,2,1,-5,4};
        /*
            Using Kadane's Algo
            Time complexity: O(n) Single pass over the nums []

            Space complexity: O(1) No extra compute space required
         */
        int result = testMaximumSubArray(nums);
        System.out.println("testMaximumSubArray: " + result);
    }

    private static int testMaximumSubArray(int[] nums) {
        int maxSoFar = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            maxSoFar = Math.max(nums[i], nums[i] + maxSoFar);
            maxSum = Math.max(maxSum, maxSoFar);
        }
        return maxSum;
    }
}



//class Solution {
//    public int maxSubArray(int[] nums) {
//
//        int maxSum = nums[0];
//        int sum = nums[0];
//
//        for (int i = 1; i < nums.length; i++) {
//            sum = Math.max(nums[i], sum + nums[i]);
//            maxSum = Math.max(maxSum, sum);
//
//        }
//        return maxSum;
//    }
//}