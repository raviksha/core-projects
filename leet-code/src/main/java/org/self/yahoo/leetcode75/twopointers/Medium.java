package org.self.yahoo.leetcode75.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Medium {

    private static int testContainerWithMostWater(int[] height) {
        int n = height.length;
        int left = 0;
        int right = n - 1;
        int maxArea = 0;

        while (left < right) {
            int area = 0;
            int h = Math.min(height[left], height[right]);
            int width = right - left;
            area = h * width;

            maxArea = Math.max(maxArea, area);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    private static List<List<Integer>> test3Sum(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        Arrays.sort(nums); // O(n log n)
        int n = nums.length;

        for (int i = 0; i < n; i++) { // O(n ^ 2)
            int target = - nums[i];
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = nums[left] + nums[right];

                if (sum == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    result.add(list);
                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return new ArrayList<>(result);
    }

    public static void main(String[] args) {
        System.out.println("Two pointers .. Medium...");
        // Leet code 11. Container With Most Water
        int [] height = {1,8,6,2,5,4,8,3,7};
        /*
            Time complexity: O(n) Single pass over the height[] using 2 pointers

            Space complexity: O(1) No extra compute space required
         */
        int result = testContainerWithMostWater(height);
        System.out.println("testContainerWithMostWater: " + result);

        // Leet code 15. 3Sum
        int [] nums = {-1,0,1,2,-1,-4};
        /*
            Time complexity: O(n ^ 2)
                             Sorting: O(n log n): Arrays.sort() uses merge sort
                             Outer loop: O(n)
                                Inner loop: O(n) Pointers left and right moves n positions in total
                             Final t/c: O(n ^ 2)

            Space complexity: O(n ^ 2): Worst case when all triplets are unique and each triplet sum = 0

         */
        List<List<Integer>> tripletList  = test3Sum(nums);
        System.out.println("test3Sum : " + tripletList);
    }
}
