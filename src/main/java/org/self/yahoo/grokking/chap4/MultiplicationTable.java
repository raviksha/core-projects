package org.self.yahoo.grokking.chap4;

public class MultiplicationTable {
    public static void main(String[] args) {
        System.out.println("MultiplicationTable...");

        // Create multiplication table with all elements in the array
        int [] arr = {2, 3, 7, 8, 10};
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[i] * arr[j] + " ");
            }
            System.out.println();
        }
    }
}
