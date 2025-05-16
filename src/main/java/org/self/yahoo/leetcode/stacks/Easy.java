package org.self.yahoo.leetcode.stacks;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Easy {

    private static boolean testValidParenthesesV1(String s) {
        char [] arr = s.toCharArray();
        if (arr.length % 2 != 0) {
            return false;
        }
        Map<Character, Character> charMap = new HashMap<>();
        charMap.put('[', ']');
        charMap.put('(', ')');
        charMap.put('{', '}');

        Stack<Character> stack = new Stack<>();
        for (char c: arr) {
            if (charMap.containsKey(c)) {
                stack.push(c);
            } else if (charMap.containsValue(c)) {
                if (stack.isEmpty()) {
                    return false;
                }
                char tmp = stack.pop();
                if (charMap.get(tmp) != c) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private static boolean testValidParenthesesV2(String s) {
        char [] arr = s.toCharArray();
        if (arr.length % 2 != 0) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (char c: arr) { // O(n)
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                char value = stack.pop();
                if (c == ')' && value != '(') {
                    return false;
                } else if (c == '}' && value != '{') {
                    return false;
                } else if (c == ']' && value != '[') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println("Stacks easy .....");

        // Leet code 20. Valid Parentheses
        String s = "()[]{}";

        /*
            Time complexity: O(n)
                            Loops through the char array once
                             All operation on the Stack like push() and pop() takes constant time
                             charMap.containsValue(c) : Takes O(m) time where m = number of elements.
                             Other map operation like get() and put() takes constant O(1) time

            Space complexity: O(n)
                               Stack storing n  elements, in worst cases when the input contains all opening brackets
                               char [] : Storing n elements also takes O(n) space
                               Map containing n mapping elements, in worst case when the input contains all opening brackets

         */
        boolean isValid = testValidParenthesesV1(s);
        System.out.println("testValidParenthesesV1: " + isValid);

        /*
            Time complexity: O(n): Loops through the char array once
                                   All operation on the Stack like push() and pop() takes constant time

            Space complexity: O(n):
                            Stack storing n  elements in cases when the input contains all opening brackets
                            char [] : Storing n elements also takes O(n) space
         */

        isValid = testValidParenthesesV2(s);
        System.out.println("testValidParenthesesV2: " + isValid);
    }




}
