package org.self.yahoo.book.demo.chap2.stack.worksheet;

// Stack : LIFO
// Can be implemented using Single Linked list.
// Doubly LInked List is required for Queue data structure

import org.self.yahoo.book.demo.chap2.stack.Stack;

public class StackInsertAtLast {

    private static void insertAtBottom(Stack stack, int item) {
        if (stack.isEmpty()) {
            stack.push(item);
            return;
        }
        int currentItem = stack.pop(); // Pop out order : 12, 11, 10
        insertAtBottom(stack, item);   // Insert 5 at bottom of stack [5]
        stack.push(currentItem);       // Push in Order :  [12, 11, 10, 5]
    }
    /*
        Time complexity : O(n)
        Space complexity : O(n) : Coz each recursion will stores the stack frame until the base is reached
     */
    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(10);
        stack.push(11);
        stack.push(12);
        System.out.println("Stack Input: " + stack); // Head => [ 12, 11, 10]
        insertAtBottom(stack, 5);
        insertAtBottom(stack, 4);
        insertAtBottom(stack, 3);
        insertAtBottom(stack, 2);
        insertAtBottom(stack, 1);
        System.out.println("Stack Output: " + stack);  // Head : => [ 12, 11, 10, 5, 4, 3, 2, 1 ]

    }

}
