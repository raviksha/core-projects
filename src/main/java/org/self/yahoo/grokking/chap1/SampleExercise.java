package org.self.yahoo.grokking.chap1;

public class SampleExercise {

    private static void testBinarySearchV1(int[] input, int target) {
        int right = input.length - 1;
        int left = 0;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (input[mid] == target) {
                System.out.println("Key found at index V1: " + mid);
                return;
            } else if (input[mid] < target) {
                left = mid + 1 ;
            } else if (input[mid] > target){
                right = mid - 1;
            }
        }
        System.out.println("Key not found..");
    }

    private static boolean testBinarySearchV2(int[] input, int left, int right, int target) {
        if (left > right) {
            return false;
        }
        int mid = left + (right - left) / 2;

        if (input[mid] == target) {
            System.out.println("Key found at index V2: " + mid);
            return true;
        } else if (input[mid] < target) {
            return testBinarySearchV2(input, mid +1, right, target);
        } else {
            return testBinarySearchV2(input, left, mid -1, target);
        }
    }

    public static void main(String[] args) {
        System.out.println("Samples....");

        // Binary Search
        int [] input = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20};
        int target = 12;

        /*
            Does not use recursion.

            Time complexity : O(log n) : Divides the array into half each time
            Space complexity : O(1)    : No extra space. Better than recursion as it does not need O(n) for the recursion stack
         */
        testBinarySearchV1(input, target);

        int left = 0;
        int right = input.length -1;

        /*
            Time complexity : O(log n)  : If n = 10 then the loop iterates : Log^2 10 times =
            Space complexity : O(log n) : log N recursion stack
         */

        var res = testBinarySearchV2(input, left, right, target);
        System.out.println("Search V2 result: " + res);
    }

}
