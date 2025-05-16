package org.self.yahoo.leetcode.arrays;

import java.util.Arrays;

public class Medium {

    private static void testRotateArrayV1(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int [] arr = new int[nums.length];
        int length = nums.length;

        /*
            eg :
                nums length = 2, k = 3 => k = 3/2 = 1
                nums length = 7, k = 3 => k = 3/7 = 3
         */
        k = k  % length;

        int count = k;
        int x = 0;

        while (count > 0) {
            arr[x] = nums[length - count];
            x++;
            count--;
        }

        for (int i = 0; i < length - k; i++) {
            arr[x] = nums[i];
            x++;
        }

        System.arraycopy(arr, 0, nums, 0, nums.length);
    }

    private static void testRotateArrayV2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int length = nums.length;

        k = k % length; // Handles cases when k > array length

        for (int j = 0; j < k; j++) {

            int lastElement = nums[nums.length - 1];
            for (int i = length - 1; i > 0; i--) {
                nums[i] = nums[i - 1];
            }

            nums[0] = lastElement;
        }
    }

    private static int testBestTimeToBuySellStocksV1(int[] prices) {

        if (prices == null || prices.length == 0) {
            return 0;
        }
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i -1]) {
                maxProfit += prices[i] - prices[i -1];
            }
        }
        return maxProfit;
    }



    private static int testBestTimeToBuySellStocksV2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxProfit = 0;
        int peak = 0;
        int valley = 0;

        int i = 0;
        // 7, 1, 5, 3, 6, 4
        while (i < nums.length -1) {
            // Increment i to the position of the first valley in the array

            while (i < nums.length -1 && nums[i + 1] < nums[i]) {
                i++; // Valley found
            }
            valley = nums[i];

            while (i < nums.length -1 && nums[i + 1] > nums[i]) {
                i++;
            }
            peak = nums[i];

            maxProfit += peak - valley;
        }
        return maxProfit;
    }

    private static int testNumberOfZeroFilledArraysV1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int count = nums[0] == 0 ? 1 : 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0) {
                count++;
                int tmpCounter = i;
                while (tmpCounter -1 >= 0 && nums[tmpCounter - 1] == 0) {
                    count++;
                    tmpCounter--;
                }
            }
        }
        return count;
    }


    private static long testNumberOfZeroFilledArraysV2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        long count = 0;
        int zeroCount = 0;

        for (int num : nums) {
            if (num == 0) {
                zeroCount++;
            } else {
                count += zeroCount * (zeroCount + 1) / 2;
                zeroCount = 0;
            }
        }
        // Handle if the last element in the array was zero
        count += zeroCount * (zeroCount + 1) / 2;
        return count;
    }


    private static boolean testIncreasingTripletSequenceV1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int seqLength = 2;

        for (int i = 0; i < nums.length - seqLength; i++) {
            int currentItem = nums[i];
            int seqCounter = 0;
            for (int j = 1; j < nums.length; j++) {

                if (nums[j] > currentItem) {
                    seqCounter++;
                }

                if (nums[j] > nums[i]) {
                    currentItem = nums[j];
                }

                if (seqCounter >= 2) {
                    return true;
                }
            }

        }

        return false;

    }

    private static boolean testIncreasingTripletSequenceV2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int smallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] <= smallest) {
                smallest = nums[i];
            } else if (nums[i] <= secondSmallest) {
                secondSmallest = nums[i];
            } else if (nums[i] > smallest && nums[i] > secondSmallest) {
                // Condition ONLY reached when the element is greater than smallest and second smallest
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Medium........");

        // Leet code 189. Rotate Array
        int [] nums = {1,2,3,4,5,6,7};
        int k = 3; // Number of positions to be rotated to right

        /*
            // Using a separate temp array
            Time complexity: O(n) : Control loops over the array for all n elements in a single pass

            Space complexity: O(n) : Extra space required for storing temp array
         */
        testRotateArrayV1(nums, k);
        System.out.println("Rotated Array V1: " + Arrays.toString(nums));

        nums = new int [] {1,2,3,4,5,6,7};
        k = 3; // Number of positions to be rotated to right
        /*
            Uses Brute force approach

            Time Complexity : O (n * k) : Traverses the array k times and moves all n elements in each iteration

            Space complexity : O(1) : No extra space required as the array is modified in place

         */
        testRotateArrayV2(nums, k);
        System.out.println("Rotated Array V2: " + Arrays.toString(nums));

        System.out.println("Medium .........................");

        /*
            Greedy Approach

            Leet code 122. Best Time to Buy and Sell Stock II

            Time complexity: O(n) : Iterates over the array in a single pass

            Space complexity: O(1) : No extra space required in computation
         */
        nums = new int [] {7, 1, 5, 3, 6, 4};
        var maxProfit = testBestTimeToBuySellStocksV1(nums);
        System.out.println("maxProfit V1: " + maxProfit);


        nums = new int [] {7, 1, 5, 3, 6, 4};
        maxProfit = testBestTimeToBuySellStocksV2(nums);
        System.out.println("maxProfit V2: " + maxProfit);

        System.out.println("Medium .........................");

        // Leet code 2348. Number of Zero-Filled Sub arrays
        nums = new int [] {1,3,0,0,2,0,0,4};

        /*
                Brute force approach

                Time complexity:
                            Worst case: O(n ^ 2) : For every iteration the loops iterates back k continuous occurrences of 0
                                                  eg : [0,0,0,0,0,0,0,0]
                            Best case : O(n) : Array contains no consecutive zeros eg : [2,10,2019]

                Space complexity: O(1) : No extra space required
         */
        var count = testNumberOfZeroFilledArraysV1(nums);
        System.out.println("Number of Sub Arrays V1: " + count);

        nums = new int [] {0,0,0,2,0,0};
       // nums = new int [] {1,3,0,0,2,0,0,4};

        /*
            Linear scan approach.
            Works on the approach of using the combinatorial arithmetic
            Each time a continuous zero is encountered in the array then the total count is calculated using :
                count = zeroCount * (zeroCount + 1) / 2;

            Time Complexity : O(n) : Iterates the array in a single pass

            Space complexity : O(1) : No extra space required
         */
        var arrCount = testNumberOfZeroFilledArraysV2(nums);
        System.out.println("Number of Sub Arrays V2: " + arrCount);

        System.out.println("Medium .........................");

        // Leet code 334. Increasing Triplet Subsequence
        // [1,5,0,4,1,3]
        // [5,4,3,2,1]
        // [2,1,5,0,4,6]
        // [0,4,2,1,0,-1,-3]
        // [6,7,1,2]
        // {1, 2, 3, 4, 5}
        nums = new int [] {1,5,0,4,1,3};

        /*
             Brute force approach
            Time complexity: O(n ^ 2): For each element n, the control loops n times : n * n = N^2

            Space complexity: O(1): No extra storage required
         */
        var isTripletSeq = testIncreasingTripletSequenceV1(nums);
        System.out.println("isTripletSeq V1: " + isTripletSeq);

        /*
            Two pointer linear approach : Loops over the array and keeps track of 2 elements i.e smallest and second smallest
            If a third element is found which is greater than the second element then the triplet pattern is found
            Most interesting way encountered for looking at a problem which seems so complex initially but has a very simple solution

            Time complexity: O(n): Control does a single pass over the array of n elements : O(n)

            Space complexity: O(1): No extra space required.
         */
        nums = new int [] {2, 7, 3, 1, 5, 4, 6};
        isTripletSeq = testIncreasingTripletSequenceV2(nums);
        System.out.println("isTripletSeq V2: " + isTripletSeq);
    }

}
