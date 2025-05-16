package org.self.yahoo.leetcode.mergesort;

import java.util.Arrays;

public class Easy {
    public static void main(String[] args) {
        System.out.println("Merge sort .. easy..");
        int[] numbers = {3, 2, 4, 1, 3, 11, 0};
        int low = 0;
        int high = numbers.length - 1;
        testMergeSortNumbers(numbers, low, high);
        System.out.println(Arrays.toString(numbers));
    }

    /*
        Using the Divide and Conquer technique

     */
    private static void testMergeSortNumbers(int[] numbers, int low, int high) {
        if (low >= high) {
            return;
        }

        int mid = low + (high - low) /2;
        testMergeSortNumbers(numbers, low, mid);
        testMergeSortNumbers(numbers, mid +1, high);

        mergeArrays(numbers, low, mid, high);

    }

    private static void mergeArrays(int[] numbers, int low, int mid, int high) {
        int counter = 0;
        int leftIndex = low;
        int rightIndex = mid + 1;
        int [] tmpArr = new int[high - low + 1];


        while (leftIndex <= mid && rightIndex <= high) {

            if (numbers[leftIndex] < numbers[rightIndex]) {
                tmpArr[counter] = numbers[leftIndex];
                leftIndex++;
                counter++;
            } else {
                tmpArr[counter] = numbers[rightIndex];
                rightIndex++;
                counter++;
            }
        }

        while(leftIndex <= mid) {
            tmpArr[counter] = numbers[leftIndex];
            leftIndex++;
            counter++;
        }

        while (rightIndex <= high) {
            tmpArr[counter] = numbers[rightIndex];
            rightIndex++;
            counter++;
        }

        for (int i = 0, j = low; i < counter; i++, j++) {
            numbers[j] = tmpArr[i];
        }
    }
}
