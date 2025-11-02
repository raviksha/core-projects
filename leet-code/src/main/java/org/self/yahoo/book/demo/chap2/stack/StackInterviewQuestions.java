package org.self.yahoo.book.demo.chap2.stack;

import java.awt.image.SampleModel;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StackInterviewQuestions {

    private static void testStringReverseUsingStack() {
        String sample = new String("YAHOO");
        if (sample != null) {
            char[] charArray = sample.toCharArray();
            Stack stack = new Stack();

            for (char c : charArray) {
                stack.push((int) c);
            }

            System.out.println("Stack Input: " + stack);

            // Process the reverse String by popping items out of the stack
            StringBuilder stringBuilder = new StringBuilder();
            int output = stack.pop();
            while (output != -1) {
                stringBuilder.append((char) output + " ");
                output = stack.pop();
            }
            System.out.println(stringBuilder);

        } else {
            System.out.println("Input string is empty ...");
        }

    }

    // Evaluate Post fix expressions
    private static void evaluatePostFixExpression() {
        /*
            Post fix expression examples :
                1 + 2       = 1 2 +
                1 + 2 * 3   = 1 2 3 * +
                (1 + 2) * 3 = 1 2 + 3 *
         */

        //String postFixExpression = "1 2 & #";  // Invalid input scenario
        // String postFixExpression = "1 2 +";  // 1 + 2
        // String postFixExpression = "1 2 -";  // 1 - 2
        // String postFixExpression = "1 2 *";  // 1 * 2
        // String postFixExpression = "4 2 /";  // 4 / 2
        String postFixExpression = "1 2 3 * +";  // Actual : 3 * 2 +  1    Expected : 1 + 2 * 3


        String [] expArr  = postFixExpression.split(" ");
        Set<String> operations = new HashSet<String>(Arrays.<String>asList("+", "-", "*", "/"));
        System.out.println("Input expression array: " + operations);

        StringStack strstack = new StringStack();
        for (String item: expArr) {
            if (operations.contains(item)) {
                System.out.println("Operator: " + item);

                // Addition operator
                if (item.equals("+")) {
                    String op1 = strstack.pop();
                    String op2 = strstack.pop();
                    int result = 0;
                    if (op1 != null && op2 != null) {
                        result = Integer.parseInt(op2) + Integer.parseInt(op1);
                    }
                    strstack.push(Integer.toString(result));
                }

                // Subtraction operator
                if (item.equals("-")) {
                    String op1 = strstack.pop();
                    String op2 = strstack.pop();
                    int result = 0;
                    if (op1 != null && op2 != null) {
                        result = Integer.parseInt(op2) - Integer.parseInt(op1);
                    }
                    strstack.push(Integer.toString(result));
                }

                // Multiplication operator
                if (item.equals("*")) {
                    String op1 = strstack.pop();
                    String op2 = strstack.pop();
                    int result = 0;
                    if (op1 != null && op2 != null) {
                        result = Integer.parseInt(op2) * Integer.parseInt(op1);
                    }
                    strstack.push(Integer.toString(result));
                }

                // Division operation

                if (item.equals("/")) {
                    String op1 = strstack.pop();
                    String op2 = strstack.pop();
                    int result = 0;
                    if (op1 != null && op2 != null) {
                        result = Integer.parseInt(op2) / Integer.parseInt(op1);
                    }
                    strstack.push(Integer.toString(result));
                }

            } else {
                strstack.push(String.valueOf(Integer.parseInt(item)));
            }

        }
        System.out.println("Expression evaluation result : " + strstack);

    }

    public static void main(String[] args) {
        // Reverse a string using stack data structures
        //testStringReverseUsingStack();

        // Evaluate Post fix expressions
        evaluatePostFixExpression();
    }

}
