package org.self.yahoo.leetcode.arrays;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Easy {

    private static void testMoveAllZerosToEndV1(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("Invalid input..");
            return;
        }
        int [] tmp = new int[arr.length];
        int count = 0;

        for (int i=0; i < arr.length; i++) {
            if (arr[i] != 0) {
                tmp[count] = arr[i];
                count++;
            }
        }
        for (int i = 0; i < tmp.length; i++) {
            arr[i] = tmp[i];
        }
    }


    private static void testMoveAllZerosToEndV2(int[] arr) {
        if (arr == null || arr.length < 2) {
            System.out.println("Minimum array element misisng...");
            return;
        }

        int counter = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[counter] = arr[i];
                counter++;
            }
        }
        while (counter < arr.length) {
            arr[counter] = 0;
            counter++;
        }
    }

    private static int testReturnMajorityElementV1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int arrKey = 0;
        int arrKeyCount = 0;
        Set<Integer> keySet = new HashSet<>();

        for(int i = 0; i < nums.length; i++) {
            int key = nums[i];
            if (!keySet.contains(key)) {
                keySet.add(key);
                int count = 0;

                for(int j = 0; j < nums.length; j++) {
                    if (nums[j] == key) {
                        count++;
                    }
                }
                if (count > arrKeyCount && count >= nums.length / 2) {
                    arrKey = key;
                    arrKeyCount = count;
                }
            }
        }
        return arrKey;
    }

    private static int testReturnMajorityElementV2(int[] num) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < num.length; i++) {
            int key = num[i];
            var currCount = map.getOrDefault(key, 0);
            map.put(key, currCount + 1);

            if (currCount + 1 > num.length / 2) {
                System.out.println("Length of nums: " + num.length);
                System.out.println("Value of I on return: " + i);
                return key;
            }

        }
        return -1;
    }


    private static int testReturnMajorityElementV3(int[] nums) {
        // {3, 3, 4, 2, 4, 4, 2, 4, 4}
        if (nums == null || nums.length == 0) {
            return -1; // Invalid input
        }

        int candidate = nums[0]; // current selected element
        int counter = 0;    // Number of occurrences of candidate

        for (int num : nums) {
            if (counter == 0) {
                candidate = num;
            }

            if (num == candidate) {
                counter++;
            } else {
                counter --;
            }
        }
        return candidate; // final candidate with > n/2 occurrences
    }


    private static int testRemoveDuplicatesFromSortedArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        // Using 2 pointers approach
        int uniQCtr = 0; // Index of the last unique element of the array. Begins at 0 index

        // Loop begins at index 1 for comparison
        for (int i = 1; i < nums.length; i++) {
            if (nums[uniQCtr] != nums[i]) { // Unique element is found
                uniQCtr++; // This increment will move the uniQCtr to the new index to capture the unique element
                nums[uniQCtr] = nums[i];
            }
        }
        // Should return the length of the array containing the unique elements only.
        // Need to increment the uniQCtr to capture the length as the index begins from 0 and runs till length - 1
        return uniQCtr + 1; //

    }

    private static int testRemoveDuplicatesFromUnSortedArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        Set<Integer> uniCtrSet = new HashSet<>();
        int counter = 0;

        for (int i = 0; i < nums.length; i++) {
            if (!uniCtrSet.contains(nums[i])) {
                uniCtrSet.add(nums[i]);
                nums[counter] = nums[i];
                counter++;
            }
        }
        System.out.println("After shuffle :" + Arrays.toString(nums));
        return counter;
    }


    private static int testBestTimeToBuyAndSellStocks(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {

            if (price < minPrice) {
                minPrice = price;
            }

            int profit = price - minPrice;

            maxProfit = Math.max(profit, maxProfit);

        }
        return maxProfit;
    }


    public static void main(String[] args) {
        System.out.println("Easy .........................");
        // Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
        // https://leetcode.com/problems/move-zeroes/description/

        int [] arr = {0, 0, 1};
        //int [] arr = {0,1,0,3,12};

        /*
            Leetcode 283: Move Zeroes
            Brute Force Approach
            Time complexity  : O(n) :
                                     Needs multiple passes over the array
                                     Loops n times twice for all the n elements
            Space complexity : O(n) : Extra space required to store the tmp array with N elements
         */
        testMoveAllZerosToEndV1(arr);
        System.out.println("Result array V1: " + Arrays.toString(arr));

        /*
            Leetcode 283: Move Zeroes :
            Two pointer approach.

            Time complexity : O(n)  : More efficient as it makes a single pass over the n elements
            SPace complexity : O(1) : Does in place sorting and does not need additional storage compared to Brute force approach

         */
        arr = new int[]{0, 1, 0, 3, 12};
        testMoveAllZerosToEndV2(arr);
        System.out.println("Result array V2: " + Arrays.toString(arr));

        //......................................................................................................//

        // Given an array nums of size n, return the majority element.
        // Leetcode 169. Majority Element

        int [] nums = {2,2,1,1,1,2,2};
        //int nums = new int[] {3,2,3};

        /*
            Time complexity  : O(n ^ 2) : For each element in the outer loop the inner loop run n times : O(n * n)

            Space complexity : O(n) : Auxiliary space required to store n elements in the HashSet
         */

        var majorElement = testReturnMajorityElementV1(nums);
        System.out.println("majorElement V1: " + majorElement);

        /*
            Time complexity : O(n) : Control iterates over the loop once n times. Hash map lookup takes constance O(1) time

            Space complexity : O(n) : Extra space required to store the counter in the HashMap
         */
        nums = new int[] {3, 3, 4, 3, 3, 2, 3, 3};
        majorElement = testReturnMajorityElementV2(nums);
        System.out.println("majorElement V2: " + majorElement);

        /*
            Boyer-Moore voting algo

            Time complexity : O(n) : Control loops through only once for n elements

            Space complexity : O(1) : No extra spaces required for storage

         */
        nums = new int[] {3, 3, 4, 2, 4, 4, 2, 4, 4};
        majorElement = testReturnMajorityElementV3(nums);
        System.out.println("majorElement V3: " + majorElement);


        //......................................................................................................//
        // Leet Code 26. Remove Duplicates from Sorted Array
        nums = new int[] {0,0,1,1,1,2,2,3,3,4};
        /*

            Uses 2 pointers approach.
                Pointer 1 keeps tracks of the index of the last unique element found
                Pointer 2 loops through the entire input array

            This approach works for sorted array coz in a sorted array the duplicate elements will always appear together
            In case of un sorted array the duplicate element can occur anywhere in the iteration and hence the two pointer approach will fail

            The answer this question on Leet Code is looking for :
                    1. Length of the unique array which will always be n(array index) + 1
                    2. Should not be confused with unique elements which will be : n

            Time complexity: O(n) : Control traversed through the entire array of n elements only one

            Space complexity: O(1) : No extra space is required as the swapping is done in place
         */
        var uniqueCtr = testRemoveDuplicatesFromSortedArray(nums);
        System.out.println("Sorted array unique counter: " + uniqueCtr);

        nums = new int[] {12,32,12,1,2,1,2,32,12};
        uniqueCtr = testRemoveDuplicatesFromUnSortedArray(nums);
        System.out.println("Unsorted unique counter: " + uniqueCtr);


        //......................................................................................................//
        // Leetcode 121. Best Time to Buy and Sell Stock
        nums = new int[] {7,1,5,3,6,4};

        /*
            Does NOT use Brute force approach which would result in O(n ^ 2) time complexity
            Time Complexity : O(n) : Iterates through the array one for all n elements

            Space complexity : O(1) : No extra space required for computation
         */

        var maxProfit = testBestTimeToBuyAndSellStocks(nums);
        System.out.println("Max profit: " + maxProfit);

    }
}
