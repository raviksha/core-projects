package org.self.yahoo.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Medium {

    public static void testGenerateParenthesesV2(List<String> result, int max, int open, int close, StringBuilder interimB) {

        if (interimB.length() == (max * 2)) {
            result.add(interimB.toString());
            return;
        }

        if (open < max) {
            testGenerateParenthesesV2(result, max, open + 1, close, interimB.append('('));
            interimB.deleteCharAt(interimB.length() -1);
        }

        if (close < open) {
            testGenerateParenthesesV2(result, max, open, close + 1, interimB.append(')'));
            interimB.deleteCharAt(interimB.length() -1);
        }
    }

    /** Why interimP = interimP + "("; is NOT a good idea in recursion
     *  as it modifies and overrides the local reference if the recursion call stack itself
     * private static void test(String interim) {
     *     if (interim.length() == 2) {
     *         System.out.println(interim);
     *         return;
     *     }
     *
     *     interim = interim + "A";
     *     test(interim); // recursive call with modified interim
     *     System.out.println("Backtrack: " + interim); // this is now permanently changed
     * }
     */
    private static void testGenerateParenthesesV1(List<String> result, int max, int open, int close, String interimP) {
        if (interimP.length() == (max * 2)) {
            result.add(interimP);
            return;
        }

        if (open < max) {
            testGenerateParenthesesV1(result, max, open + 1, close, interimP + "(");
        }

        if (close < open) {
            testGenerateParenthesesV1(result, max, open, close + 1, interimP + ")");
        }
    }

    private static void getAllSubSets(int[] nums, List<List<Integer>> subSetsL, int i, List<Integer> subSet) {
        if (i == nums.length) {
            subSetsL.add(new ArrayList<>(subSet)); // Fix: make a copy
            return;
        }
        subSet.add(nums[i]);
        getAllSubSets(nums, subSetsL, i + 1, subSet);
        subSet.remove(subSet.size() -1);
        getAllSubSets(nums, subSetsL, i + 1, subSet);
    }

    public static void main(String[] args) {
        System.out.println("Backtracking Medium .....");

        //  Leet code 22. Generate Parentheses
        List<String> result = new ArrayList<>();
        int n = 3;
        int open = 0;
        int close = 0;
        String interimP = "";
        /*
            Using String objects which are not memory efficient

        Time complexity: O(4^n / \sqrt{n})
		Space complexity: O(n) for call stack per path (excluding result storage)

         */
        testGenerateParenthesesV1(result, n, open, close, interimP);
        System.out.println("testGenerateParenthesesV1: " + result);

        /*
            Using StringBuilder for a better memory performant solution


            Time complexity: O(4^n / \sqrt{n})
            Space complexity: O(n) for call stack per path (excluding result storage)

         */

        n = 3;
        open = 0;
        close = 0;
        StringBuilder interimB = new StringBuilder();
        List<String> resultB = new ArrayList<>();
        testGenerateParenthesesV2(resultB, n, open, close, interimB);
        System.out.println("testGenerateParenthesesV2: " + resultB);



        // Leet code 78. Subsets
       int [] nums = new int[] {1, 2, 3};
        List<List<Integer>> subSetsL = new ArrayList<>();
        int i = 0;
        List<Integer> subSet = new ArrayList<>();
        /*
            Time complexity: O(2 ^ n * n)
                             To calculate all the possible subsets for a n elements : 2 ^ n
                             n steps required to reach each possible subset of the array
                             Concluding time complexity: O(2 ^ n * n)

            Space complexity: O(2 ^ n * n) + O(n)
                              O(2 ^ n) subsets with each containing n elements : O(n * 2 ^n)
                              O(n): n recursion stacks for the back tracking using brute force
                              Total : O(2 ^ n * n) + O(n)
         */
        getAllSubSets(nums, subSetsL, i, subSet);
        System.out.println("getAllSubSets: " + subSetsL);


        // Leet code 90. Subsets II
        nums = new int[] {1, 2, 2};
        List<List<Integer>> resultNoDup = new ArrayList<>();
        i = 0;
        List<Integer> list = new ArrayList<>();

        /*
            Approach: To remove duplicate elements in the array, array is sorted first to ensure the duplicates are together

            Time complexity: O(2 ^ n * n)
                             O(2 ^ n): Different subsets for an array of n elements
                             n : Compute time required for each subset calculation
                             O(n log n) : Sorting the array
                             Total : O(2 ^n * n)

            Space complexity: O(2 ^n * n) + O(n)
                              O(2 ^ n) subsets with each subsets holding up to n elements: O(2 ^n * n)
                              O(n): recursion stack for n elements
                              Total : O(2 ^ n * n) + O(n)
         */
        Arrays.sort(nums);
        testSubSetsWithoutDuplicate(nums, resultNoDup, i, list);
        System.out.println("testSubSetsWithoutDuplicate: " + resultNoDup);



        // Leet code 46. Permutations
        nums = new int[] {1, 2, 3};

        List<List<Integer>> permL = new ArrayList<>();
        i = 0;
        List<Integer> permS = new ArrayList<>();

        /*
            Using a brute force recursion approach

            Time complexity: O(n * n!)
                            # of permutations for each element: n!
                            # of permutations for n elements: n * n!
                            Total: O(n * n!)

             Space complexity: O(n * n!)
                                Recursion depth: O(n)
                                O(n): List size for each permutations * n! permutations : O(n * n!)
                                Total: O(n * n!)

         */

        testPermutations(nums, permL, i, permS);


        System.out.println("testPermutations: " + permL);

    }

    //TODO : revisit this later
    private static void testPermutations(int[] nums, List<List<Integer>> permL, int i, List<Integer> permS) {
        if (i == nums.length) {
            permL.add(new ArrayList<>(permS));
            return;
        }

        for (int j = 0; j < nums.length; j++) {
            if (permS.contains(nums[j])) {
               continue;
            }
            permS.add(nums[j]);
            testPermutations(nums, permL, i + 1, permS);
            permS.remove(permS.size() -1);
        }
    }


    private static void testSubSetsWithoutDuplicate(int[] nums, List<List<Integer>> resultNoDup, int i, List<Integer> list) {

        if (i == nums.length) {
            resultNoDup.add(new ArrayList<>(list));
            return;
        }
        list.add(nums[i]);

        testSubSetsWithoutDuplicate(nums, resultNoDup, i + 1, list);

        list.remove(list.size() -1);

        int index = i + 1;

        while (index < nums.length && nums[i] == nums[index]) {
            index = index + 1;
        }

        testSubSetsWithoutDuplicate(nums, resultNoDup, index, list);
    }
}
