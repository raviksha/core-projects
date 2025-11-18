package org.self.yahoo.lcoderev1.arrays;

import java.util.Arrays;

public class Medium {

    private static int[] testProductOfArrayExceptSelf(int[] nums) {
        int n = nums.length;
        int [] result = new int[n];
        int [] left = new int[n];
        int [] right = new int[n];

        left[0] = 1;
        right[n - 1] = 1;

        int leftTotal = 1;
        int rightTotal = 1;

        // Traverse left and compute the product total of all elements to the left of the index

        for (int i = 1; i < n; i++) { // O(n)
            leftTotal *= nums[i - 1];
            left[i] = leftTotal;
        }

        // Traverse left and compute the product total of all elements to the left of the index

        for (int j = n - 2; j >= 0; j--) { //  // O(n)
            rightTotal *= nums[j + 1];
            right[j] = rightTotal;

        }


        for (int i = 0; i < n; i++) {    // O(n)
            result[i] = left[i] * right[i];
        }

        return result;

    }
    public static void main(String[] args) {
        System.out.println("Medium ..... Arrays ....");
        int [] nums = {1,2,3,4};
        // Leet code 238. Product of Array Except Self
        /*
            Approach: Compute the left product and right product of all elements to the left and right of the element

            T/C: O(n) Linear traversal of the nums[] arr

            S/C: O(1) No extra auxiliary space required

         */
        int [] result = testProductOfArrayExceptSelf(nums);
        System.out.println("testProductOfArrayExceptSelf: " + Arrays.toString(result));

        // Leet code 122. Best Time to Buy and Sell Stock II
        int [] prices = { 7,1,5,3,6,4 };
        /*
            T/C: O(n) Single pass over the prices array

            S/C: O(1) No extra auxiliary space required
         */
        int maxProfit = testBestTimeToBuyStocks(prices);
        System.out.println("testBestTimeToBuyStocks: " + maxProfit);

    }

    private static int testBestTimeToBuyStocks(int[] prices) {
        int curr = prices[0];
        int totalProfit = 0;
        for (int tmp : prices) {
            if (tmp > curr) {
                totalProfit += tmp - curr;
            }
            curr = tmp;
        }
        return totalProfit;
    }
}

//