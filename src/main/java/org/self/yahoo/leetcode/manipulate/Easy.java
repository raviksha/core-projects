package org.self.yahoo.leetcode.manipulate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Easy {
    private static int testSingleNumberV1(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();

        for (int curElement : nums) {
            freqMap.put(curElement, freqMap.getOrDefault(curElement, 0) + 1);
        }

        for (Entry<Integer, Integer> entry :freqMap.entrySet()){
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        return -1; // Control should not reach here
    }

    private static int testSingleNumberV2(int[] nums) {

        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            result = result ^ nums[i];
        }
        return result;
    }


    private static int [] testCountingBitsV1(int n) {
        int [] result = new int[n + 1];

        for (int i = 0; i < result.length; i++) {
            int num = i;
            int count = 0;

            while (num != 0) { // Runs O(log n) times for a given n
                count += num % 2;
                num = num / 2; // Breaking the number into half = O(log n)
            }
            result[i] = count;
        }
        return result;
    }

    private static int testGetBinaryForInteger(int k) {
        StringBuilder binarystr = new StringBuilder();

        while (k != 0) {
            binarystr.append(k % 2);
            k = k /2;
        }

        return Integer.parseInt(binarystr.reverse().toString());
    }

    public static void main(String[] args) {
        System.out.println("Bit manipulation Easy...");

        // Leet code 136. Single Number
        int [] nums = {2,2,1};
        /*
            Approach of using a Frequency map

            Time complexity: O(n): Loops through all the elements of the nums []

            Space complexity: O(n): Extra space required to build the frequency map
         */
        int result = testSingleNumberV1(nums);
        System.out.println("testSingleNumberV1: " + result);

        /*
            Approach of using a XOR operator
            Bitwise operation formulated used here is:
            1. XOR of 2 same numbers will always be ZERO, irrespective of the ordering sequence
            2. Numbers ordering sequence does not matter Eg: {4,1,2,1,2}
                4 ^ 1 = n1
                4 ^ 1 ^ 2 = n2
                4 ^ 1 ^ 2 ^ 1 = 4 ^ 2 coz 1 ^ 1 = 0
                4 ^ 2 ^ 2 = 4 coz 2 ^ 2 = 0
                Answer: 4

            Time complexity: O(n): Loops through the nums[] once
            Space complexity: O(1): No extra compute space required

         */
        result = testSingleNumberV2(nums);
        System.out.println("testSingleNumberV2: " + result);

        // Leet code 338. Counting Bits
        int n = 2;

        /*
            Approach is taking a number at each iteration and:
                1. number % 2 = Returns if the last digit is 1
                2. number = number /2 = Divides the number to check if there are more 1's

            Time complexity: O(n log n)
                            For each n, the while loop runs O(log n) times.
                            For total n element the t/c is : O(n log n)
                            Concluding t/c : O(n log n)

            Space complexity: O(n + 1): Result array containing the 1's count
         */
        var bitCounts = testCountingBitsV1(n);
        System.out.println("testCountingBitsV1: " + Arrays.toString(bitCounts));

        /*
            The Approach is to fall back on the core patterns of bits shift between number and number * 2
            Number of 1 bit in a number = number of 1 bits in n /2
            The difference in the bit patterns in a n and n * 2, is that the bits gets shifted to left;

            Time complexity: O(n): Loops just once over the nums[]
            Space complexity: O(n): Result array containing the bit count for each element of nums []


         */
        bitCounts = testCountingBitsV2(n);
        System.out.println("testCountingBitsV2: " + Arrays.toString(bitCounts));



        // Drive the binary equivalent of a given Integer
        int k = 3;
        /*
            Time complexity: O(log n)
            Space complexity: O(1)
         */
        int binaryNum = testGetBinaryForInteger(k);
        System.out.println("testGetBinaryForInteger: " + binaryNum);
    }

    private static int[] testCountingBitsV2(int n) {
        int [] result = new int[n + 1];

        for (int i = 0; i < result.length; i++) {

            if (i % 2 == 0) {
                result[i] = result[i/ 2];
            } else {
                result[i] = result[i / 2] + 1;
            }
        }
        return result;
    }


}
