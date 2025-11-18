package org.self.yahoo.lcoderev1.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Easy {

    private static void testMoveZerosV2(int[] nums) {
        int insertPosition = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[insertPosition++] = nums[i];
            }
        }

        while (insertPosition < nums.length) {
            nums[insertPosition++] = 0;
        }
    }


    private static void testMoveZerosV1(int[] nums) {
        int [] tmp = new int [nums.length];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                tmp[index] = nums[i];
                index++;
            }
        }

        System.arraycopy(tmp, 0, nums, 0, nums.length);
    }

    public static void main(String[] args) {
        System.out.println("Arrays .. Easy ....");

        // Leet code 283. Move Zeroes
        int [] nums = {0,1,0,3,12};
        /*
            Approach: Using an extra array
            Time complexity: O(n)
            Space complexity: O(n)
         */
        testMoveZerosV1(nums);
        System.out.println("testMoveZerosV1: " + Arrays.toString(nums));


        nums = new int[] {0,1,0,3,12};
    /*
        Approach: In‑place compaction of non‑zero elements followed by zero‑fill.
        - Time Complexity: O(n)
            We scan the array once to move non‑zeros and once to fill zeros.
        - Space Complexity: O(1)
            No extra auxiliary array or data structure is created.
            Only a few integer variables are used → constant space.
     */
        testMoveZerosV2(nums);
        System.out.println("testMoveZerosV2: " + Arrays.toString(nums));


        // Leet code 169. Majority Element
        nums = new int[] {2,2,1,1,1,2,2};

        /*
            Time complexity: O(n)

            Space complexity: O(n): Worst case if the array has all unique elements
         */
        int resp = testMajorityElementV1(nums);
        System.out.println("testMajorityElementV1: " + resp);

        nums = new int[] {2,2,1,1,1,2,2};

        /*
            T/C: O(n)
                   NOTE: The T/C is similar to testMajorityElementV1() which implements the solution using HashMaps
                   Although operations with HashMap have an amortized T/C of O(1) but the implementation using HashMap is slower coz:
                   1. Boxing and Unboxing overheads
                   2. Computing hashcode
                   3. Object creation overheads
                   4. Equals checks

            S/C: O(1) : No extra compute/auxiliary space required
         */
        resp = testMajorityElementV2(nums);
        System.out.println("testMajorityElementV2: " + resp);

    }

    private static int testMajorityElementV2(int[] nums) {
        int candidate = 0;
        int counter = 0;

        for (int item: nums) {

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

    private static int testMajorityElementV1(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();

        int freq = Integer.MIN_VALUE;
        int element = 0;

        for(int item: nums) {
            int tmpFreq = freqMap.getOrDefault(item, 0) + 1;
            freqMap.put(item, tmpFreq);

            if (tmpFreq > freq) {
                freq = tmpFreq;
                element = item;
            }
        }

        return element;
    }


}
