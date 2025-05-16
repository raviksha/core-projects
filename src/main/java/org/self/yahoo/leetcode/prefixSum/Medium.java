package org.self.yahoo.leetcode.prefixSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Medium {

    private static int testContainerWithMostWaterV1(int[] h) {
        if (h == null || h.length == 0) {
            return 0;
        }
        int maxArea = 0;

        for (int i = 0; i < h.length; i++) {
           // System.out.println("****** " + i + " **********");
            for (int j = i + 1; j < h.length; j++) {
                int height = Math.min(h[i], h[j]);
                int area = height * (j - i);
                //System.out.println("Local area: " + area);
                maxArea = Math.max(area, maxArea);
            }
            //System.out.println("****************");
        }
        return maxArea;

    }

    private static int testContainerWithMostWaterV2(int[] nums) {
        int maxArea = 0;
        int left = 0;
        int right = nums.length -1;

        while (left < right) {
            int height = Math.min(nums[left], nums[right]);
            int area = height * (right - left);
            maxArea = Math.max(area, maxArea);

            if (nums[left] <= nums[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    private static int[] testTwoSumV1(int[] numbers, int target) {
        int size = numbers.length - 1;
        int [] result = new int [2];

        for (int i = 0; i <= size; i++) {
            for (int j = i + 1; j<= size; j++) {
                if (numbers[i] + numbers[j] == target) {
                    result[0] = i + 1;
                    result[1] = j + 1;
                    return result;
                }
            }
        }
        return null; // this line shud never be reached
    }

    private static int[] testTwoSumV2(int[] nums, int target) {
        int size = nums.length;
        int [] result = new int [2];

        int leftPointer = 0;
        int rightPointer = 1;

        while (leftPointer != size && rightPointer != size) {
            int leftVal = nums[leftPointer];
            int rightVal = nums[rightPointer];

            if (leftVal + rightVal == target) {
                result[0] = leftPointer + 1;
                result[1] = rightPointer + 1;
                return result;
            } else if (leftVal + rightVal > target) {
                leftPointer++;
                rightPointer = leftPointer + 1;
            } else {
                rightPointer++;
                if (rightPointer == size) {
                    leftPointer++;
                    rightPointer = leftPointer + 1;
                }
            }
        }
        return null; // Shud never be reached
    }

    private static int[] testTwoSumV3(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        Map<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            int complement = target - value;

            if (hashMap.containsKey(complement)) {
                return new int [] {hashMap.get(complement) + 1, i + 1};
            }
            hashMap.put(value, i);
        }
        return null; // should never be reached
    }


    private static List<List<Integer>> test3SumV1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i< nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> list = new ArrayList<>();
                        int [] tmpArr = {nums[i] ,nums[j], nums[k]};
                        Arrays.sort(tmpArr);

                        for (int num : tmpArr) {
                            list.add(num);
                        }

                        if (!result.contains(list)) {
                            result.add(list);
                        }

                    }

                }
            }
        }
        return result;
    }

    private static List<List<Integer>> test3SumV2(int[] nums) {
        if (nums == null && nums.length < 3) {
            return null;
        }
        Arrays.sort(nums);
        Set<List<Integer>> result = new HashSet<>();
        for (int i = 0; i < nums.length -2; i++) {
            int left = i + 1;
            int right = nums.length -1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return new ArrayList<>(result);
    }

    private static int testSubArraySumV1(int [] nums, int k) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        // Continuous Sub array Sum Equals K
        int count = 0;
        for (int i = 0; i < nums.length; i++) { // Outer loop is to loop through the inner loop n times
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];

                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    private static int testSubArraySumV2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        Map<Integer, Integer> hashMap = new HashMap<>();
        int count = 0;
        int sum = 0;

        hashMap.put(0, 1); // Handles case when the current num[i] element == k

        for (int i = 0; i < nums.length; i++) {
            int key = nums[i];
            sum += key;

            if (hashMap.containsKey(sum - k )) {
                count += hashMap.get(sum - k);
            }

            hashMap.put(sum, hashMap.getOrDefault(sum, 0) + 1);
        }
        return count;

    }


    private static int testContiguousArrayV1(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();

        int maxLength = 0;
        int sum = 0;

        freqMap.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            int currVal = nums[i];

            if (currVal == 0) {
                sum = sum - 1;
            } else {
                sum = sum + 1;
            }
            if (freqMap.containsKey(sum)) {
                int lastSeenIndex = freqMap.get(sum);
                maxLength = Math.max(i - lastSeenIndex, maxLength);
            }
            else {
                freqMap.put(sum, i);
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println("Prefix sum Medium....");

        System.out.println("***********************  Container With Most Water **********************");
        // Leet code 11. Container With Most Water
        int [] nums = {1,8,6,2,5,4,8,3,7};
        /*
            Brute force approach
            Time complexity: O(n ^ 2) : Brute force where the inner loop does n iterations for each integration of the outer loop

            Space complexity: O(1) : No extra space required
         */
        int maxArea = testContainerWithMostWaterV1(nums);
        System.out.println("maxArea: V1 " + maxArea);

        nums = new int[] {1,8,6,2,5,4,8,3,7};
        /*
                Two pointer approach
                Time complexity: O(n) : Single pass over the nums []
                Space complexity: O(1) : No extra storage used in the computation
         */
        maxArea = testContainerWithMostWaterV2(nums);
        System.out.println("maxArea: V2 " + maxArea);

        System.out.println("***********************  Two Sum II - Input Array Is Sorted **********************");
        // Leet code 167. Two Sum II - Input Array Is Sorted

        nums = new int[] {2,7,11,15};
        int target = 9;
        /*
            Brute Force
            Time complexity: O(n ^ 2) : For each element n in the outer array, inner array is looped n times

            Space complexity: O(1) : No extra space required
         */
        int [] result = testTwoSumV1(nums, target);
        System.out.println("testTwoSum V1: " + Arrays.toString(result));

        /*
            Two pointer approach : Works only for sorted array
            Time complexity : O(n) : Does single pass over the nums []
            Space complexity : O(1): No extra space required
         */
        result = testTwoSumV2(nums, target);
        System.out.println("testTwoSum V2: " + Arrays.toString(result));

        /*
            Using a HashMap :
                Most widely used approach to solve Two Sum problem
                Works for unsorted Arrays as well

            Time complexity : O(n) : Does a single pass over the array

            Space complexity: O(n) : Extra map to store the values with their indices
         */
        result = testTwoSumV3(nums, target);
        System.out.println("testTwoSum V3: " + Arrays.toString(result));

        System.out.println("***********************  3Sum **********************");
        // Leet code 15. 3Sum
        int [] numsArr = {-1,0,1,2,-1,-4};

        /*
            Brute force approach
            Time complexity: O(n ^ 3) : For each element in the out the loop the control runs two full passes via the inner loop
                                        to check for all possible combinations leading to 0 sum

            Space complexity: O(n ^ 2) : Store list of n array elements matching the sum equation
         */

        List<List<Integer>> resp = test3SumV1(numsArr);
        System.out.println("test3Sum V1: " + resp);

        /*
            3 sum approach
            Time complexity: O(n ^ 2): For each element n in num[], there runs a two sum approach for the in a nested while loop => O(n * n) : O(n ^2)

            Space complexity: O(n): Extra hashset to store the unique triplets which sums up to 0
         */
        resp = test3SumV2(numsArr);
        System.out.println("test3Sum V2: " + resp);

        System.out.println("***********************  Subarray Sum Equals K **********************");
        // Leet code 560. Subarray Sum Equals K
        nums = new int[]{1,2,3};
        int k = 3;
        /*
            Brute Force approach
            Time complexity : O(n ^ 2) : Outer loop loops n times to trigger the contiguous sum on elements for all elements again in the inner loop
                              => O(n) * O(n) = O(n ^ 2)
            Space complexity : O(1)
         */
        int count = testSubArraySumV1(nums, k);
        System.out.println("testSubArraySum V1: " + count);

        /*
            Prefix sum approach

            Time complexity: O(n): Single pass over the nums[] to calculate the Pref fix at each nums[] array index

            Space complexity: O(n) : Extra Map to store the Prefix sum at each index
         */
        count = testSubArraySumV2(nums, k);
        System.out.println("testSubArraySum V2: " + count);


        System.out.println("***********************  Contiguous Array **********************");

        // Leet code 525. Contiguous Array
        nums = new int[]{0,1,1,1,1,1,0,0,0};

        /*
            Time complexity: O(n) : Single pass over the nums []

            Space complexity: O(n) : Extra space required for the FrequencyMap to store the frequency of sum at each iteration
         */
        var maxArrayLength = testContiguousArrayV1(nums);
        System.out.println("maxArrayLength : " + maxArrayLength);

    }
}
