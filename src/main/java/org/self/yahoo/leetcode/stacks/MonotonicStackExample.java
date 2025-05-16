package org.self.yahoo.leetcode.stacks;

import java.util.Stack;

public class MonotonicStackExample {

    public static void main(String[] args) {
        int[] temperature = {73, 74, 75, 71, 69, 72, 76, 73};

        // Monotonically Increasing Stack (stores indices)
        Stack<Integer> increasingStack = new Stack<>();
        System.out.println("Processing for Monotonically Increasing Stack:");
        for (int i = 0; i < temperature.length; i++) {
            System.out.println("  Processing temperature[" + i + "] = " + temperature[i]);
            while (!increasingStack.isEmpty() && temperature[i] > temperature[increasingStack.peek()]) {
                int poppedIndex = increasingStack.pop();
                System.out.println("    Popping index " + poppedIndex + " because temperature[" + i + "] > temperature[" + poppedIndex + "]");
            }
            increasingStack.push(i);
            System.out.println("    Pushing index " + i);
            System.out.println("    Stack: " + increasingStack);
        }
        System.out.println("Final Monotonically Increasing Stack (indices): " + increasingStack);

        System.out.println("\n--------------------\n");

        // Monotonically Decreasing Stack (stores indices)
//        Stack<Integer> decreasingStack = new Stack<>();
//        System.out.println("Processing for Monotonically Decreasing Stack:");
//        for (int i = 0; i < temperature.length; i++) {
//            System.out.println("  Processing temperature[" + i + "] = " + temperature[i]);
//            while (!decreasingStack.isEmpty() && temperature[i] < temperature[decreasingStack.peek()]) {
//                int poppedIndex = decreasingStack.pop();
//                System.out.println("    Popping index " + poppedIndex + " because temperature[" + i + "] < temperature[" + poppedIndex + "]");
//            }
//            decreasingStack.push(i);
//            System.out.println("    Pushing index " + i);
//            System.out.println("    Stack: " + decreasingStack);
//        }
//        System.out.println("Final Monotonically Decreasing Stack (indices): " + decreasingStack);
    }
}
