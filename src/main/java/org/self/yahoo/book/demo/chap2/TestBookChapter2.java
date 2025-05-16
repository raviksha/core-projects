package org.self.yahoo.book.demo.chap2;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class TestBookChapter2 {
    public static void main(String[] args) {

        /* Bubble Sort */
        // bubbleSortV1();
        // bubbleSortV2();
        // bubbleSortV3();

        /* Selection Sort */
        // selectionSortV1();

        /* Binary Search Recursion */
        // binarySearchRecursive();

        /* Quick Sort */
        quickSortRecursiveV1();

        /* Merge Sort */
        mergeSortRecursiveV1();
    }

    private static void mergeSortRecursiveV1() {
        /*   Time complexity :
                Best and worst
                      O (n log n)
         *   Space complexity : O(n) : Worse than Quick sort

         */

        int[] sample = {4, 6, 2, 8, 9, 0, 100, 23, 5, 6, 67};
//        int[] sample = new int[10000];
//        Random rand = new Random();
//        for (int i = 0; i < sample.length; i++) {
//            sample[i] = rand.nextInt();
//        }
        int start = 0;
        int end = sample.length - 1;
        mergeSort(sample, start, end);
        System.out.println("Merge sort V1: " + Arrays.toString(sample));

    }

    private static void mergeSort(int[] sample, int start, int end) {
        if (start < end) {
            int pivot = (start + end) / 2;
            mergeSort(sample, start, pivot);
            mergeSort(sample, pivot + 1, end);
            merge(sample, start, pivot, end);
        }
    }

    private static void merge(int[] sample, int start, int middle, int end) {
        int leftHalf = start;
        int rightHalf = middle + 1;
        int[] arrTemp = new int[(end - start) + 1];
        int x = 0;
        while ((leftHalf <= middle) && (rightHalf <= end)) {
            if (sample[leftHalf] < sample[rightHalf]) {
                arrTemp[x] = sample[leftHalf];
                x++;
                leftHalf++;
            } else {
                arrTemp[x] = sample[rightHalf];
                x++;
                rightHalf++;
            }
            System.out.println("**********");
            System.out.println("Left Half: " + leftHalf);
            System.out.println("Middle: " + middle);
            System.out.println("\n");
            System.out.println("Right Half: " + rightHalf);
            System.out.println("End: " + end);
            System.out.println("**********");
        }

        System.out.println("################");
        System.out.println("Left Half: " + leftHalf);
        System.out.println("Middle: " + middle);
        System.out.println("\n");
        System.out.println("Right Half: " + rightHalf);
        System.out.println("End: " + end);
        System.out.println("################");

        /*
            Add the remaining elements in our left or right array
         */
        while (leftHalf <= middle) {
            //System.out.println("Remaining left half .....  " + leftHalf);
            arrTemp[x] = sample[leftHalf];
            x++;
            leftHalf++;
        }

        while (rightHalf <= end) {
            //System.out.println("Remaining right half .....   " + rightHalf);
            arrTemp[x] = sample[rightHalf];
            x++;
            rightHalf++;
        }

        for (int i = 0, j = start; i < arrTemp.length; i++, j++) {
            sample[j] = arrTemp[i];
        }
        //System.arraycopy(arrTemp, 0, sample, start, arrTemp.length);
    }

    private static void quickSortRecursiveV1() {
        /* Time complexity : O(n log n) NOTE its NOT O(log n)
         Worst case : O(n ^ 2) : Happens when the array is already sorted
                        In this case the pivot will always be at the end of the list and except the last nth element,
                        the whole n -1 elements needs to be looped again.
                        eg {2, 4, 6, 8, 10, 12} will split the pivot point into : {2, 4, 6, 8, 10} and {12} splits
        */

        /* Suggested approach to handle the worst case Time complexity of Quick Sort
            1. Select the middle element as pivot
            2. Select any random element as pivot
        */

        /*
            Space complexity :
                 Best case : O( log n) Based on the height of the split pivot tree
                 Worst case : O(n)
         */

        int sample[] = {4, 6, 2, 8, 9, 0, 100, 23, 5, 6, 67};
        int start = 0;
        int end = sample.length - 1;
        quickSort(sample, start, end, "main");
        System.out.println("Quick sort V1: " + Arrays.toString(sample));
    }

    private static int partition(int[] sample, int start, int end) {
        int x = start - 1;
        int pivot = sample[end];
        for (int i = start; i <= end; i++) {
            if (sample[i] < pivot) {
                x++;
                swap(sample, i, x);
            }
        } // By the end of the for loop the array is sorted with elements smaller than pivot to left of `x` and the larger to the right of `x`
        swap(sample, x + 1, end);
        // Final swap is done to swap the first item that is larger than pivot with the pivot
        return x + 1;
    }

    private static void quickSort(int[] sample, int start, int end, String mode) {
        System.out.println("********  " + mode + "    ***********");
        if (start < end) {
            int partitionPosition = partition(sample, start, end);
            quickSort(sample, start, partitionPosition - 1, "pre");
            quickSort(sample, partitionPosition + 1, end, "post");
        }
    }

    private static void binarySearchRecursive() {
        // Time complexity : O(log n)
        // Space complexity : O(1)
        int sample[] = {4, 6, 2, 8, 9, 0, 100, 23, 5, 6, 67};
        int start = 0;
        int end = sample.length;
        int searchItem = 100;
        Arrays.sort(sample);
        System.out.println("Sorted sample: " + Arrays.toString(sample));
        var isPresent = binarySearch(searchItem, sample, start, end);
        System.out.println("Binary Search V1 response: " + isPresent);
    }

    private static boolean binarySearch(int x, int[] sortedSample, int start, int end) {
        if (start <= end) {
            int mid = (start + end) / 2;
            System.out.println("**********");
            System.out.println("Start counter: " + start);
            System.out.println("Mid counter: " + mid);
            System.out.println("End counter: " + end);
            System.out.println("**********");
            if (sortedSample[mid] == x) {
                return true;
            } else if (x < sortedSample[mid]) {
                return binarySearch(x, sortedSample, start, mid - 1);
            } else if (x > sortedSample[mid]) {
                return binarySearch(x, sortedSample, mid + 1, end);
            }
        }
        return false;
    }

    /**
     * Bubble Sort Vs Selection Sort
     * Bubble Sort:
     * => Bubble Sort works by repeatedly swapping adjacent elements if they are in the wrong order,
     * “bubbling” the largest element to the end in each pass.
     * => Makes more number of iterations (compared to Selection sort) as the inner loop begins from j=0 instead of j=i
     * => Space Complexity:  O(1)  (in-place sorting)
     * => Key Characteristics:
     * ✔️ Simple and easy to implement
     * ❌ Inefficient for large datasets
     * ❌ Many swaps, compared to Selction sort, making it slower
     * <p>
     * Selection Sort
     * => Selection Sort works by finding the minimum element in each pass and placing it in its correct position.
     * => Makes less number of iterations (compared to Bubble sort) as the inner loop begins from j=i instead of j=0
     * => Space Complexity:  O(1)  (in-place sorting)
     * => Key Characteristics:
     * ✔️ Fewer swaps compared to Bubble Sort
     * ❌ Still inefficient for large datasets
     * ❌ Always  O(n^2) , even for sorted arrays
     */
    private static void selectionSortV1() {
        // Time complexity : O(n^2)
        // Space Complexity:  O(1)  (in-place sorting)
        int[] sample = {4, 6, 2, 8, 9, 0};
        for (int i = 0; i < sample.length - 1; i++) {
            System.out.println("*******");
            for (int j = i; j < sample.length; j++) {
                // After each inner loop iteration of J, the smallest element will move to the start of the array
                if (sample[j] < sample[i]) {
                    swap(sample, j, i);
                }
                System.out.println("Selection Sort V1 : " + "Iteration: " + j + " : " + Arrays.toString(sample));
            }
            System.out.println("*******");

        }
        System.out.println("Selection Sort V1 : " + Arrays.toString(sample));
    }

    private static void bubbleSortV3() {
        // Best case Time complexity = O(n) if the sample array is already sorted.
        // Worst case : O(n^2)
        int[] sample = {4, 6, 2, 8, 9, 0};
        int i = 1;
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int j = 0; j < sample.length - i; j++) {
                if (sample[j] > sample[j + 1]) {
                    swap(sample, j, j + 1);
                    swapped = true;
                }
            }
            System.out.println("Bubble sorted Array V3:" + Arrays.toString(sample));
        }
    }

    private static void bubbleSortV1() {
        // Time complexity : O(n^2)
        int[] sample = {4, 6, 2, 8, 9, 0};
        for (int i = 1; i < sample.length; i++) {
            for (int j = 0; j < sample.length - 1; j++) {
                if (sample[j] > sample[j + 1]) {
                    swap(sample, j, j + 1);
                }
            }
            System.out.println("Bubble sorted Array V1:" + Arrays.toString(sample));
        }
    }

    private static void bubbleSortV2() {
        // Worst case Time Complexity : O(n^2)
        int[] sample = {4, 6, 2, 8, 9, 0};
        for (int i = 1; i < sample.length; i++) {
            // j < sample.length -i
            // Improvement is that the inner loop now iterates only till the last bubbled sorted position
            // instead of the complete array. Skips the elements already sorted at the tail end of the array
            for (int j = 0; j < sample.length - i; j++) {
                if (sample[j] > sample[j + 1]) {
                    swap(sample, j, j + 1);
                }
            }
            System.out.println("Bubble sorted Array V2:" + Arrays.toString(sample));
        }
    }

    /**
     * Swaps the elements in an Integer Array
     *
     * @param sample
     * @param j
     * @param j      +1
     */
    private static void swap(int[] sample, int j, int j1) {
        var temp = sample[j];
        sample[j] = sample[j1];
        sample[j1] = temp;
    }
}
