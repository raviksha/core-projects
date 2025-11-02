package org.self.yahoo.book.demo.chap2.stack;

import java.util.Arrays;

public class TestStackOperationWithArray {

    public static void main(String[] args) {
        StackWithArray stackWithArray = new StackWithArray(5);
        // Push in Stack Array
        stackWithArray.push(1);
        stackWithArray.push(2);
        stackWithArray.push(3);
        stackWithArray.push(4);
        stackWithArray.push(5);
        System.out.println(stackWithArray); // [1, 2, -1, -1, -1]

        // Pop from Array
        int deletedItem = stackWithArray.pop();
        System.out.println("Deleted item: " + deletedItem);
        System.out.println(stackWithArray); // [1, 2, 3, 4, -1]

        deletedItem = stackWithArray.pop();
        System.out.println("Deleted item: " + deletedItem);
        deletedItem = stackWithArray.pop();
        System.out.println("Deleted item: " + deletedItem);
        deletedItem = stackWithArray.pop();
        System.out.println("Deleted item: " + deletedItem);
        deletedItem = stackWithArray.pop();
        System.out.println("Deleted item: " + deletedItem);
        System.out.println(stackWithArray); // [-1, -1, -1, -1, -1]

        //Stack capacity underflow...
        deletedItem = stackWithArray.pop();



    }
}
