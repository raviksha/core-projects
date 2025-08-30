package org.self.yahoo.leetcode75.twopointers;

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
    }


}


//class Solution {
//    public int maxArea(int[] h) {
//        int maxArea = 0;
//        int start = 0;
//        int end = h.length - 1;
//
//        while (start < end) {
//            int height = Math.min(h[start], h[end]);
//            int area = height * (end - start);
//            maxArea = Math.max(maxArea, area);
//
//            if (h[start] <= h[end]) {
//                start++;
//            } else {
//                end--;
//            }
//        }
//
//
//        return maxArea;
//    }
//}
