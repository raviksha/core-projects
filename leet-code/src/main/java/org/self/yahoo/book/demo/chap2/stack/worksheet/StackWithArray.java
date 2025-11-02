package org.self.yahoo.book.demo.chap2.stack.worksheet;

import java.util.Arrays;

public class StackWithArray {
    private int capacity = -1;
    private int headPointer = -1;
    private int size = -1;
    private int[] stackArr;

    public StackWithArray(int capacity) {
        this.capacity = capacity;
        stackArr = new int[capacity];
        initializeStack();
    }

    private void initializeStack() {
        Arrays.fill(stackArr, -1);
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setHeadPointer(int headPointer) {
        this.headPointer = headPointer;
    }

    public int getHeadPointer() {
        return headPointer;
    }

    public int getCapacity() {
        return capacity;
    }

    public void push(int item) {
        if (headPointer >= capacity) {
            System.out.println("Stack is full ....");
            return;
        }

        size++;
        headPointer++;
        stackArr[headPointer] = item;
    }

    public int pop() {
        if (headPointer == -1) {
            System.out.println("Stack is empty");
            return -1;
        }
        size--;

        int item = stackArr[headPointer];
        headPointer--;
        return item;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ ");
        int ctr = headPointer;
        for (int i = ctr; i >= 0; i--) {
            stringBuilder.append(stackArr[i]);

            if (i > 0) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(" ]");
        return stringBuilder.toString();
    }

    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        StackWithArray stackWithArray = new StackWithArray(5);
        stackWithArray.push(5);
        stackWithArray.push(4);
        stackWithArray.push(3);
        stackWithArray.push(2);
        stackWithArray.push(1);
        System.out.println(stackWithArray); // Head => [ 1, 2, 3, 4, 5 ]

        stackWithArray.pop();
        int delItem;
        stackWithArray.pop();
        delItem = stackWithArray.pop();
        System.out.println("Last item popped: " + delItem); // Head => [ 4, 5 ]
        System.out.println(stackWithArray);
    }
}
