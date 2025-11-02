package org.self.yahoo.book.demo.chap2.stack;

public class TestStackOperations {
    public static void main(String[] args) {
        // Push : Adds alements on top of the stack

        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        System.out.println(stack); // Head -> [ 5, 4, 3, 2, 1 ]

        int itemRemoved = stack.pop();
        System.out.println("Item removed: " + itemRemoved);
        itemRemoved = stack.pop();
        System.out.println("Item removed: " + itemRemoved);

        System.out.println(stack); // Head -> [ 3, 2, 1 ]

    }
}
