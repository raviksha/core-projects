package org.self.yahoo.leetcode.kwaymerge;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Medium {
    public static void main(String[] args) {
        System.out.println("K-Way merge Medium....");
        // Leet code 378. Kth Smallest Element in a Sorted Matrix
        // [[1,5,9],[10,11,13],[12,13,15]]
        int [] [] matrix = new int[][] {{1,5,9},{10,11,13},{12,13,15}};
        int k = 8;
        /*
            Approach is to store the k largest elements only in the max heap using Priority queue
            The top element of the max heap will be the k smallest element

            Time complexity: O(k log n)
                             Adding each element in the PQueue: O(log n) * max of k elements

            Space complexity: O(k): Extra compute space to store max k elements in the Priority Queue
         */
        int result = testKSmallestInMatrixV1(matrix, k);
        System.out.println("testKSmallestInMatrixV1: " + result);

    }

    private static int testKSmallestInMatrixV1(int[][] matrix, int k) {
        Comparator<Integer> comparator = Comparator.comparingInt((Integer value) -> value.intValue()).reversed();
        Comparator<Integer> comparator1 = Comparator.reverseOrder();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(comparator);

        for (int[] arr : matrix) {
            for (int val : arr) {
                priorityQueue.offer(val);

                if (priorityQueue.size() > k) {
                    priorityQueue.poll();
                }
            }
        }
        return priorityQueue.poll();
    }
}
