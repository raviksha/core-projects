package org.self.yahoo.leetcode.dp;
/*
    Tabulation : Bottom to up : 0 => n
    Recursion + Memoization: Top down : n - 1 => 0
 */
public class Medium {
    private static int testHouseRobber(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int n = nums.length;
        int [] maxLoot = new int[n];

        maxLoot[0] = nums[0];
        maxLoot[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            maxLoot[i] = Math.max(maxLoot[i - 2] + nums[i], maxLoot[i - 1]);
        }

        return maxLoot[n - 1];
    }

    public static void main(String[] args) {

        System.out.println("Dynamic programming .. Medium");

        int [] nums = {2,7,9,3,1};
        // Leet code 198. House Robber
        /*
            Approach is using a Dynamic programming and NOT using Greedy algo

            Time complexity: O(n): Does a single pass over the nums[]

            Space complexity: O(n): Extra compute space to store at max at each n index
         */
        int maxRob = testHouseRobber(nums);
        System.out.println("testHouseRobber: " + maxRob);

        // Leet code 213. House Robber II
        nums = new int[] {1,7,9,2};
        /*
            Approach is using Dynamic programming and NOT using Greedy algo

            Time complexity: O(n): 2 single passes over the nums []

            Space complexity: O(n): Auxiliary space to store max at each index of nums []
         */
        maxRob = testHouseRobberII(nums);
        System.out.println("testHouseRobberII: " + maxRob);
    }

    private static int testHouseRobberII(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int maxLeftRob = 0;
        int maxRightRob = 0;
        int n = nums.length;

        // Calculate max from a left section skipping the last one
        int index = 0;
        int [] maxRob = new int[n];

        maxRob[index] = nums[0];
        index++;
        maxRob[index] = Math.max(nums[0], nums[1]);
        index++;

        for (int i = 2; i < n - 1; i++) {
            maxRob[index] = Math.max((maxRob[index - 2] + nums[i]), maxRob[index -1]);
            index++;
        }
        maxLeftRob = maxRob[index -1];

        // Calculate max from a right section skipping the first one
        index = 0;
        maxRob = new int[n];

        maxRob[index] = nums[1];
        index++;
        maxRob[index] = Math.max(nums[1], nums[2]);
        index++;

        for (int i = 3; i < n; i++) {
            maxRob[index] = Math.max((maxRob[index - 2] + nums[i]), maxRob[index -1]);
            index++;
        }

        maxRightRob = maxRob[index - 1];

        return Math.max(maxLeftRob, maxRightRob);

    }


}
