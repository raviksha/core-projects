package org.self.yahoo.grokking.chap2;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Recursion {
    /*
        Time complexity: O(n) : Iterates through the list from 0 -> n times
        Space complexity: O(n) : Auxiliary space required for the recursion stack
     */
    private static void testRecursionPrintNumbers(int i) {
        if (i == 0) {
            System.out.print(i);
            return;
        }
        System.out.print(i + " ");
        testRecursionPrintNumbers(i -1);
    }

    /*
        Time complexity: O(n) : Iterates through the list from 0 -> n times
        Space complexity: O(n) : Auxiliary space required for the recursion stack
        Uses Divide and Conquer Algo
 */
    private static int testRecursionSumOfNumbers(int i) {
        if (i == 0) {
            return 0;
        }
        return i + testRecursionSumOfNumbers(i - 1);
    }

    /*
        Time complexity  : O(n) : Iterates through all the n elements in the list
        Space complexity : O(n) : Auxiliary space for recursion and the counter
     */
    private static int testNumberOfItems(ListIterator<Integer> listIterator, int counter) {
        if (!listIterator.hasNext()) {
            return counter;
        }
        listIterator.next();
        return testNumberOfItems(listIterator, counter + 1);
    }


    /*
        Time complexity: O(n) : Iterates through all the n items in the list

        Space complexity: O(n) : Auxiliary space required for recursion stack and the listIterator

     */
    private static int testFindMaxValueInAList(ListIterator<Integer> listIterator) {
        if (!listIterator.hasNext()) {
            return 0;
        }
        int currValue = listIterator.next();
        return Math.max(currValue, testFindMaxValueInAList(listIterator));
    }


    /*
            Time Complexity:  O(log n)

            Space Complexity: O(log n)
     */
    private static boolean testBinarySearch(int[] arr, int start, int end, int target) {

        if (start > end) { // Base case should not be start >= end as the middle element check should not be skipped when start == end
            System.out.println("Key not found");
            return false;
        }

        int mid = start + (end - start) /2; // Prevents the Integer overflow

        if (arr[mid] == target) {
            System.out.println("Target found at index: " + mid);
            return true;
        } else if (arr[mid] > target) {
            end = mid - 1;
        } else {
            start = mid + 1;
        }
        return testBinarySearch(arr, start, end, target);
    }

    public static void main(String[] args) {
        System.out.println("Recursion...");
        int i = 5;
        // Print numbers in descending order
        testRecursionPrintNumbers(i);

        // Calculates the sum of the number from i -> 0
        var sum = testRecursionSumOfNumbers(i);
        System.out.println();
        System.out.println("Sum: " + sum);

        // Calculate the number of items in a list
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        var itemsCount = testNumberOfItems(list.listIterator(), 0);
        System.out.println("Items in list: " + itemsCount);

        // FInd the maximum value in a list
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(44);
        list.add(5);
        var maxValue = testFindMaxValueInAList(list.listIterator());
        System.out.println("Max Value: " + maxValue);

        // Binary Search
        int [] arr = {23, 45, 76, 89, 105};
        int start = 0;
        int end = arr.length - 1;
        int target = 189;
        var isPresent = testBinarySearch(arr, start, end, target);
        System.out.println("isPresent: " + isPresent);
    }
}
