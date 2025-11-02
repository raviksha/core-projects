package org.self.yahoo.book.demo.chap2.stack;

import java.util.Arrays;

public class StackWithArray {
    int capacity;
    int headPointer = -1;
    int[] stack = null;

    public StackWithArray(int capacity) {
        this.capacity = capacity;
        stack = new int[capacity];
        initializeArray();
    }

    private void initializeArray() {
        Arrays.fill(stack, -1);
    }

    public int getCapacity() {
        return capacity;
    }

    public int[] getStack() {
        return stack;
    }

    public int getHeadPointer() {
        return headPointer;
    }

    public void setHeadPointer(int headPointer) {
        this.headPointer = headPointer;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setStack(int[] stack) {
        this.stack = stack;
    }

    public void push(int item) {
        if (headPointer < stack.length) {
            stack[++headPointer] = item;
        } else {
            System.out.println("Stack capacity overflow...");
        }
    }

    public int pop() {
        if (headPointer < 0) {
            System.out.println("Stack capacity underflow...");
            return -1;
        }
        int deletedItem = stack[headPointer];
        stack[headPointer] = -1;
        --headPointer;
        return deletedItem;
    }

    @Override
    public String toString() {
        return Arrays.toString(stack);
    }
}
