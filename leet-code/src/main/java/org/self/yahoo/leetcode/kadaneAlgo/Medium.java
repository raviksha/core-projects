package org.self.yahoo.leetcode.kadaneAlgo;

public class Medium {

    private static int testContiguousArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int sum = nums[0];
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int currVal = nums[i];
            sum += currVal;
            if (currVal > sum) {
                sum = currVal;
            }

            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    private static int testMaxProductSubArray(int[] nums) {
        int leftProd = 1;
        int rightProd = 1;
        int maxProd = Integer.MIN_VALUE;
        int leftIndex = 0;
        int rightIndex = nums.length - 1;

        while (leftIndex < nums.length) {

            leftProd = leftProd == 0 ? 1 : leftProd;
            rightProd = rightProd == 0 ? 1 : rightProd;

            int leftVal = nums[leftIndex];
            int rightVal = nums[rightIndex];

            leftProd = leftProd * leftVal;
            rightProd = rightProd * rightVal;

            maxProd = Math.max(maxProd, Math.max(leftProd, rightProd));
            leftIndex++;
            rightIndex--;
        }
        return maxProd;
    }

    public static void main(String[] args) {
        System.out.println("Kadane Algo: Medium...");

        System.out.println("***********************  Contiguous Array **********************");
        // Leet code 53. Maximum Subarray sum (Kadane Algo)
        /*
            Approach :
                var sum : calculates the running sum of the sub array ending at index i
                          it resets the sub array at position i if nums[i] > sum + nums[i]
                var maxSum: the max sub array sum found so far

            Time complexity: O(n): Loops over the nums [] in a single pass

            Space complexity : O(1): No extra compute space
         */
        int [] nums = new int [] {-2,1,-3,4,-1,2,1,-5,4};
        int maxSum = testContiguousArray(nums);
        System.out.println("testContiguousArray : " + maxSum);


        System.out.println("***********************  Maximum Product Subarray **********************");
        // Leet code 152. Maximum Product Subarray
        nums = new int[] {2,3,-2,4};
        /*
            Time complexity: O(n): Single pass over the nums []
            Space complexity: O(1): No extra compute space
         */
        int maxProduct = testMaxProductSubArray(nums);
        System.out.println("testMaxProductSubArray: " + maxProduct);

    }
}
