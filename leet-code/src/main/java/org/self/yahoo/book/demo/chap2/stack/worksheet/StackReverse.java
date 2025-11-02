package org.self.yahoo.book.demo.chap2.stack.worksheet;

import org.self.yahoo.book.demo.chap2.stack.Stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StackReverse {
    /*
        Using 2 Stacks objects
        Time complexity : O(n)
        Space complexity : O(n)
     */
    private static void reverseStackV1(Stack origStack, Stack revStack) {
        if (origStack.isEmpty()) {
            return;
        }
        int currentItem = origStack.pop();
        while (currentItem != -1) {
            revStack.push(currentItem);
            currentItem = origStack.pop();
        }
    }

    /*
        Using recursion
     */

    private static void pushAtBottom(Stack stack, int item) {
        if(stack.isEmpty()) {
            stack.push(item);
            return;
        }

        int currentItem = stack.pop();
        pushAtBottom(stack, item);
        stack.push(currentItem);


    }
    private static void reverseStackV2(Stack origStack) {
        if (origStack.isEmpty()) {
            return;
        }
        int currentItem = origStack.pop();
        reverseStackV2(origStack);
        pushAtBottom(origStack, currentItem);
    }

    /*
        Using an new Array
        Time complexity : O(n)
        Space complexity : O(n)
     */
    private static void reverseStackV3(Stack origStack) {
        if (origStack.isEmpty()) {
            return;
        }

        Integer arr[] = new Integer[origStack.getSize()];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = origStack.pop();
        }

        for (int i = 0; i < arr.length; i++) {
            origStack.push(arr[i]);
        }
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
        Stack revStack = new Stack();
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);

        System.out.println("Input Stack V1: " + stack); // Head => [1, 2, 3, 4, 5]
        System.out.println("Stack size: " + stack.getSize());

        // V1 : Using nee Stack approach
        reverseStackV1(stack, revStack);
        System.out.println("Reversed Stack V1: " + revStack); // Head => [5, 4, 3, 2, 1]
        System.out.println("Stack size: " + revStack.getSize());

        // V3 : Using Array approach
        stack = new Stack();
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        System.out.println("Input Stack V3: " + stack); // Head => [5, 4, 3, 2, 1]
        reverseStackV3(stack);
        System.out.println("Reversed Stack V3: " + stack); // Head => [1, 2, 3, 4, 5]


        // V2 : Using Recursion approach
        stack = new Stack();
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        System.out.println("Input Stack V2: " + stack);
        reverseStackV2(stack);
        System.out.println("Reversed Stack V2: " + stack);




       //  V3 : Using Collections API
        java.util.Stack<Integer>  utilStack = new java.util.Stack<>();
        utilStack.push(5);
        utilStack.push(4);
        utilStack.push(3);
        utilStack.push(2);
        utilStack.push(1);
        System.out.println("Java Util Input Stack V3: " + utilStack);
        List<Integer> list = new ArrayList<>(utilStack);
        Collections.reverse(list);
        utilStack.clear();
        utilStack.addAll(list);
        System.out.println("Java Util Reversed Stack V3: " + utilStack);



    }
}
