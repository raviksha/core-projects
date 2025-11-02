package org.self.yahoo.leetcode75.stacks;

import java.util.Stack;

public class Medium {
    public static void main(String[] args) {
        System.out.println("Stacks .. Medium...");
        // Leet code 155. Min Stack
        /*
            Time complexity: O(1)
                             pop()/getMin()/top()/push(): Takes O(1) constant time

            Space complexity: O(n + m)
                              Stack to all current elements in the stack: O(n)
                              Stack to currentMin: O(m): Worst case when all elements are inserted in descending order
                              Concluding s/c O(n) => Ignoring m as it is small
         */
        testMinStack();
    }

    private static void testMinStack() {
        MinStack obj = new MinStack();
        // ["MinStack","push","push","push","getMin","pop","top","getMin"]
        // [   [],      [-2],  [0],   [-3],   [],      [],  [],     []]
        obj.push(-2);
        obj.push(0);
        obj.push(-3);
        int min = obj.getMin();
        System.out.println("min -3: " + (min == -3));
        obj.pop();
        int top = obj.top();
        System.out.println("top 0: " + (top == 0));
        min = obj.getMin();
        System.out.println("min -2: " + (min == -2));
    }
}


class MinStack {
    Stack<Integer> stack = new Stack<Integer>();
    Stack<Integer> minStack = new Stack<Integer>();

    public MinStack() {

    }

    public void push(int val) {
        stack.push(val);

        if (minStack.empty() || val <= minStack.peek()) {
            minStack.push(val);
        }

    }

    public void pop() {
        int val = stack.pop();

        if (val == minStack.peek()) {
            minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
//
///**
// * Your MinStack object will be instantiated and called as such:
// * MinStack obj = new MinStack();
// * obj.push(val);
// * obj.pop();
// * int param_3 = obj.top();
// * int param_4 = obj.getMin();
// */