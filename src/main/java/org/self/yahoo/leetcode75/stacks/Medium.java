package org.self.yahoo.leetcode75.stacks;


import java.util.Stack;

public class Medium {
    public static void main(String[] args) {
        System.out.println("Stacks .....Medium..");
        // Leet code 20. Valid Parentheses
        String str = "()[]{}";

        /*
            Time complexity: O(n) Single pass over the str chars

            Space complexity: O(n) Extra space required to store the open parentheses in a stack
                                   Worst case when the string consists of only open parentheses
         */
        boolean result = testValidParentheses(str);
        System.out.println("testValidParentheses: " + result);
    }

    private static boolean testValidParentheses(String str) {
        if (str.length() % 2 != 0) {
            return false;
        }

        Stack<Character> stack = new Stack<>();

        for (char item : str.toCharArray()) {
            if (item == '(' || item == '[' || item == '{') {
                stack.push(item);
                continue;
            }

            char currItem = stack.isEmpty() ? '0' : stack.peek();

            if (item == ')' && currItem != '(') {
                return false;
            }

            if (item == ']' && currItem != '[') {
                return false;
            }

            if (item == '}' && currItem != '{') {
                return false;
            }
            stack.pop();
        }
        return stack.isEmpty();
    }
}
