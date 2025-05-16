package org.self.yahoo.leetcode.stacks;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Medium {
    private static String testRemoveDuplicateLetters(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }

        Set<Character> visited = new HashSet<>();
        Map<Character, Integer> freqMap = new HashMap<>();
        Stack<Character> stack = new Stack<>();

        char [] chrArr = s.toCharArray(); // O(n)

        for (char tmpChar : chrArr) { // O(n)
            freqMap.put(tmpChar, freqMap.getOrDefault(tmpChar, 0) + 1);
        }

        for (char currChar : chrArr) { // O(n)
            if (stack.isEmpty()) {
                stack.push(currChar);
                visited.add(currChar);
                freqMap.put(currChar, freqMap.get(currChar) - 1);
            } else {
                char tmpChar = stack.peek();
                freqMap.put(currChar, freqMap.get(currChar) - 1); // O(1)
                while (!stack.isEmpty() && tmpChar >= currChar && freqMap.get(tmpChar) > 0 && !visited.contains(currChar)) {
                    stack.pop();
                    visited.remove(tmpChar); // O(1)
                    if (!stack.isEmpty()) {
                        tmpChar = stack.peek();
                    }
                }
                if (!visited.contains(currChar)) {
                    stack.push(currChar); // O(1)
                    visited.add(currChar); // O(1)
                }

            }
        }
        return reverseStack(stack, new StringBuilder()); // O(n)

    }

    private static String reverseStack(Stack<Character> stack, StringBuilder sb) {
        if (stack.isEmpty()) {
            return sb.toString();
        }
        Character top = stack.pop();
        reverseStack(stack, sb);
        sb.append(top);
        return sb.toString();
    }

    private static void testMinimumStack() {
        MinStack minStack = new MinStack();
        // ["MinStack","push","push","push","getMin","pop","top","getMin"]
        // [   [],      [-2],  [0],   [-3],   [],      [],  [],     []]
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        int min = minStack.getMin();
        System.out.println("Min val: " + min);
        System.out.println("Min test: min == -3: " + (min == -3));
        minStack.pop();
        int top = minStack.top();
        System.out.println("Top val: " + top);
        System.out.println("Top test: : " + (top == 0));

        min = minStack.getMin();
        System.out.println("Min val: " + min);
        System.out.println("Min test: min == -2: " + (min == -2));
    }

    private static int[] testDailyTemperatureV1(int[] temperature) {
        if (temperature.length == 1) {
            return new int [] {0};
        }
        int length = temperature.length;
        int [] result = new int [length];
        Stack<Integer> mainStack = new Stack<>();
        Stack<Integer> tempStack = new Stack<>();

        for (int i = length -1; i >= 0; i--) { // O(n)
            mainStack.push(temperature[i]);
        }

        int lastSeen = mainStack.pop();
        int index = 0;

        while(!mainStack.isEmpty()) {   // O(n)
            int currTemp = mainStack.pop();

            if (currTemp > lastSeen) {
                result[index] = 1;
                lastSeen = currTemp;
                index++;
            } else {
                tempStack.clear();
                int tempItem = currTemp;
                boolean matchFound = false;
                while (tempItem <= lastSeen && !mainStack.isEmpty()) { // O(m)
                    tempItem = mainStack.pop();
                    tempStack.push(tempItem);
                    if (tempItem > lastSeen) {
                        matchFound = true;
                    }
                }
                int posn = 0;
                if (matchFound) {
                    posn = tempStack.isEmpty() ? 0 : tempStack.size() + 1;
                }
                result[index] = posn;
                while (!tempStack.isEmpty()) {
                    mainStack.push(tempStack.pop());
                }
                index++;
                lastSeen = currTemp;
            }
        }
        return result;
    }

    private static int[] testDailyTemperatureV2(int[] temperature) {
        if (temperature.length == 1) {
            return new int [] {0};
        }
        int length = temperature.length;
        int [] result = new int [length];

        Stack<Integer> stack = new Stack<>();


        for (int i = length -1; i >= 0; i--) { // O(n)
            if (stack.isEmpty()) {
                stack.push(i);
                result[i] = 0;
            } else {
                int val = temperature[i];

                int stackIndex = stack.peek();
                if (val < temperature[stackIndex]) {
                    stack.push(i);
                    result[i] = stackIndex - i;
                } else {
                    while (!stack.isEmpty() && val >= temperature[stackIndex]) { // O(m)
                        stack.pop();
                        if (!stack.isEmpty()) {
                            stackIndex = stack.peek();
                        }
                    }
                    if (stack.isEmpty()) {
                        result[i] = 0;
                    } else {
                        result[i] = stackIndex -i;
                    }
                    stack.push(i);
                }

            }
        }
        return result;
    }


    public static void main(String[] args) {
        System.out.println("Stack Medium ......");

        // Leet code 316. Remove Duplicate Letters

        //String s = "cbacdcbc";
        //String s = "bcabc";
        //String s = "ecbacba";
        //String s = "abacb";
        String s = "bbcaac";

        /*
            Time complexity: O(n): Loops through the string char elements once
                                   All the freqMap and visited hash set operations takes constant O(1)

            Space complexity: O(n + m)
                                  char[] stores n string char elements + m (max 26) unique elements stored in Stack, freqMap and visited
                                  Recursion stack will take O(n) method call stack
                                  Final concluded space complexity : O(n + 26) => O(n)
         */
        String result = testRemoveDuplicateLetters(s);
        System.out.println("testRemoveDuplicateLetters: " + result);

        // Leet code 155. Min Stack
        /*
            Time complexity: O(1) All the stack operations pop(), top(), push() and getMin() takes constant time

            Space complexity: O(n + m): Two internal stacks storing n and m least elements at any give time.
                              m is small and can be ignored. Concluded to : O(n)
         */
        testMinimumStack();

        // Leet Code 739. Daily Temperatures
        int[] temperature = new int[] {55,38,53,81,61,93,97,32,43,78};
        /*
            Using two stacks approach

            Time complexity: O(n * m) For each main stack entry the inner stack pushes m elements each time
                             Stack operations like push()/pop()/size() : O(1) constant time

            Space complexity: O(n)
                              Input and the result array both take O(n) space
                              Main stack loads all n elements in it : O(n)
                              Inner stack in worst case will store n - 1 elements : Rounded off to O(n)
                              Overall space complexity : O(n)
         */
        int [] resp = testDailyTemperatureV1(temperature);
        System.out.println("testDailyTemperatureV1: " + Arrays.toString(resp));

        /*
            Using Monotonic stacks

            Time complexity: O(n + n)
                             For loop iterates over the int [] n times
                             Nested while loop although looks to add a complexity of O(n) with total being O(n ^ 2), that is NOT true
                             In the worst case all the elements of the int [] will at most be pushed and popped only once
                             Although the while() loop is nested it will not cross a max of n push() or pop() operations in the whole
                             method execution.
                             Hence the total time complexity is O(n + n) => Concluded to O(n) overall

            Space complexity: O(n)
                              O(n) for the input and result []
                              O(n) for the stack to store at max n elements
                              Concluded to : O(n) overall
         */

        resp = testDailyTemperatureV2(temperature);
        System.out.println("testDailyTemperatureV2: " + Arrays.toString(resp));

        // Leet Code 496. Next Greater Element I
        int [] nums2 = new int[]{1,3,4,2};
        int [] nums1 = new int[]{4,1,2};

        /*
            Using a single stack approach

            Time complexity: O(n + m)
                             Outer for loop iterate through all the n nums[] elements : O(n)
                             while loop : Each element of nums[] will be pushed and popped a max of 1 time in the whole operation.
                                while loop can be ignored
                             final for loop : O(m) : Loops through the num1[] elements which in worst case can be n elements : O(n)
                             Map and Stack operations takes constant time : O(1)
                             Final time complexity: O(n + m)

            Space complexity: O(n + m)
                              nums2[]: Stores n elements
                              nums1[]: Worst case will have n elements
                              Map and Stack (in worst case),uses extra space of O(n)
                              final result array will have space complexity of O(m)
                              Final space complexity : O(m + n)
         */
        resp = testNextGreaterElement(nums1, nums2);
        System.out.println("testNextGreaterElement: " + Arrays.toString(resp));

    }

    private static int [] testNextGreaterElement(int[] nums1, int[] nums2) {
        if (nums1.length == 1 && nums2.length == 1) {
            return new int [] {-1};
        }
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        int [] result = new int [nums1.length];

        for (int i = nums2.length - 1; i >= 0; i--) {
            int currElement = nums2[i];
            if (stack.isEmpty()) {
                stack.push(currElement);
                map.put(currElement, -1);
            } else {
                int currTop = stack.peek();
                if (currTop < currElement) {
                    while(!stack.isEmpty() && currTop < currElement) {
                        stack.pop();
                        if (!stack.isEmpty()) {
                            currTop = stack.peek();
                        }
                    }
                }
                stack.push(currElement);
                if (currTop < currElement) {
                    map.put(currElement, -1);
                } else {
                    map.put(currElement, currTop);
                }
            }
        }
        for (int j = 0; j < nums1.length; j++) {
            int nextGreater = map.get(nums1[j]);
            result[j] = nextGreater;
        }
        return result;
    }


}



class MinStack {
    Stack<Integer> stack = new Stack<Integer>();
    Stack<Integer> minStack = new Stack<Integer>();

    public MinStack() {

    }

    public void push(int val) {
        stack.push(val);

        if (minStack.isEmpty()) {
            minStack.push(val);
        } else {
            int minValue = minStack.peek();
            if (val <= minValue) {
                minStack.push(val);
            }
        }

    }

    public void pop() {
        if (!stack.isEmpty() && !minStack.isEmpty()) {
            int item = stack.pop();
            int minValue = minStack.peek();

            if (minValue >= item) {
                minStack.pop();
            }

        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
