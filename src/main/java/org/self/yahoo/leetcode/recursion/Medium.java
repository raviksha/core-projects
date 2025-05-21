package org.self.yahoo.leetcode.recursion;

import java.util.Arrays;
import java.util.Stack;

public class Medium {
    private static String testDecodeStringV1(String s) {
        char [] strArr = s.toCharArray();
        Stack<StringBuilder> stack = new Stack<StringBuilder>();
        StringBuilder temp;
        for (int i = strArr.length -1; i >=0; i--) { // O(n)
            temp = new StringBuilder();
            char currChar = strArr[i];

            if (currChar == '[') {
                int index = 1;
                StringBuilder multiplierBuilder = new StringBuilder();
                while (i - index >= 0 && Character.isDigit(strArr[i - index])) {
                    multiplierBuilder.append(strArr[i - index]);
                    index++;
                }


                int multiplier = Integer.parseInt(multiplierBuilder.reverse().toString());
                StringBuilder strBuilder = new StringBuilder();

                while(!stack.isEmpty() && !strBuilder.toString().equals("]")) {
                    strBuilder = stack.pop();
                    if (!strBuilder.toString().equals("]")) {
                        temp.append(strBuilder);
                    }
                }
                String base = temp.toString();
                while (multiplier > 1) {
                    temp.append(base);
                    multiplier--;
                }
                stack.push(temp);
                temp = new StringBuilder();
                i = i - (index -1);
            } else {
                temp.append(currChar);
                stack.push(temp);
            }
        }

        temp = new StringBuilder();
        while(!stack.isEmpty()) { // O(n)
            temp.append(stack.pop());
        }
        return temp.toString();
    }
    public static void main(String[] args) {
        System.out.println("Recursion medium......");

        // Leet Code 394. Decode String
        String s = "3[a]2[bc]";
        /*
            Using a single stack approach

            Time complexity: O(n)
                             Loops once over the whole string char array : O(n)
                             Inner while loop pops and pushes elements on the stack
                             but each element of the char[] is popped and pushed only once in the entire operation => Can be ignored
                             Final loop pops all the elements out of the stack : O(n) : Worst case
                             All stack operations takes O(1) constant time
                             StringBuilder append operations takes constant time : O(1)
                             Final time complexity: O(n)

            Space complexity: O(n)
                              char[] stores all the n char elements of the string: O(n)
                              Stack stores at most n elements in the worst case: O(n)
                              Final space complexity: O(n)

         */
        //TODO : To be revisited later for a cleaner solution using 2 stacks
        String resp = testDecodeStringV1(s);
        System.out.println("testDecodeStringV1: " + resp);

        // Test Binary search using recursion
        int [] arr  = {-1, 0, 1, 5, 9, 34, 67};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        int low = 0;
        int high = arr.length - 1;
        int target = 34;
        int searchIndex = testBinarySearchRecurv1(arr, low, high, target);
        System.out.println("testBinarySearch V1: " + searchIndex);


        searchIndex = testBinarySearchRecurV2(arr, target);
        System.out.println("testBinarySearch V2: " + searchIndex);
    }

    private static int testBinarySearchRecurv1(int[] arr, int low, int high, int target) {
        if (low > high) {
            return -100;
        }

        int mid = low + (high - low) / 2;

        if (arr[mid] == target) {
            return mid;
        }

        if (arr[mid] < target) {
            return testBinarySearchRecurv1(arr, mid + 1, high, target);
        } else {
            return testBinarySearchRecurv1(arr, low, mid -1, target);
        }
    }

    private static int testBinarySearchRecurV2(int[] arr, int target) {
        int low = 0;
        int high = arr.length -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) {
                return mid;
            }

            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid -1;
            }
        }
        return -100;

    }
}
