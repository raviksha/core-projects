package org.self.yahoo.leetcode75.btracking;

import java.util.ArrayList;
import java.util.List;

public class Medium {

    private static List<List<Integer>> testPermutations(int[] nums, List<List<Integer>> result, List<Integer> subList, int i) {
        if (i == nums.length) {
            result.add(new ArrayList<>(subList));
            return result;
        }

        for (int j = 0; j < nums.length; j++) {
            if (subList.contains(nums[j])) {
                continue;
            }
            subList.add(nums[j]);
            testPermutations(nums, result, subList, i + 1);
            subList.remove(subList.size() - 1);
        }
        return result;
    }

    private static List<List<Integer>> testPermutations(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> subList = new ArrayList<>();
        int i = 0;
        return testPermutations(nums, result, subList, i);
    }

    public static void main(String[] args) {
        System.out.println("Backtracking ....Medium...");
        // Leet code 46. Permutations
        int [] nums = {1, 2, 3};

        /*
            Time complexity: O(n! * n)
                Generating permutations: O(n! * n)
                At each recursive call extra contains check for the subList: O(n)
                Final t/c: O(n! * n ^ 2)
            Space complexity:
                Recursion depth: O(n): Auxiliary space
                Temporary storage: O(n) Stores n elements per combination
                Result storage: O(n! * n) =>  n! combination * n element per combination
         */
        List<List<Integer>> result = testPermutations(nums);
        System.out.println("testPermutations : " + result);

        // Leet code 78. Subsets
        nums = new int[] {1, 2, 3};
        /*
            Problems related t back tracking are mostly solved using TAKE/NOT TAKE approach
            Time complexity: O(2 ^ n * n)
                    t/c to compute the total number of subsets for n element: O(2 ^ n)
                    t/c to copy the a List holding n elements: O(n)
                    Final t/c O(2 ^ n * n)

            Space complexity:
                    Recursion stack depth: O(n)
                    Extra space to store result for 2 ^n subsets and each holding n elements:  O(2 ^ n * n)
                    Total s/c O(n) + O(2 ^ n * n) => O(2 ^ n * n)
         */
        result = testSubsets(nums);
        System.out.println("testSubsets: " + result);
    }

    private static List<List<Integer>> testSubsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int index = 0;
        return testSubsets(result, new ArrayList<Integer>(), index, nums);
    }

    private static <E> List<List<Integer>> testSubsets(List<List<Integer>> result, ArrayList<Integer> subList, int index, int[] nums) {
        if (index == nums.length) {
            result.add(new ArrayList<>(subList));
            return result;
        }

        subList.add(nums[index]);
        testSubsets(result, subList, index + 1, nums); // Pick the current element
        subList.remove(subList.size() - 1);
        testSubsets(result, subList, index + 1, nums); // Not pick the current element
        return result;
    }
}



//class Solution {
//    public List<List<Integer>> subsets(int[] nums) {
//        List<List<Integer>> result = new ArrayList();
//        int i = 0;
//        List<Integer> subSet = new ArrayList<>();
//        printAllSubSet(nums, result, i, subSet);
//        return result;
//    }
//
//    private void printAllSubSet(int [] nums, List<List<Integer>>  result, int i, List<Integer>  subSet) {
//
//        if (i == nums.length) {
//            result.add(new ArrayList<>(subSet));
//            return;
//        }
//        subSet.add(nums[i]);
//        printAllSubSet(nums, result, i + 1, subSet);
//        subSet.remove(subSet.size() - 1);
//        printAllSubSet(nums, result, i + 1, subSet);
//    }
//}