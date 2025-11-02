package org.self.yahoo.leetcode.topkelem;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Easy {
    public static void main(String[] args) {
        System.out.println("Easy Top K elements ....");

        // Leet code 703. Kth Largest Element in a Stream
        //["KthLargest","add","add","add","add","add"]
        int [] nums = {4,5,8,2};
        int k = 3;

        /*
                Uses the approach os using a Min heap Priority Queue

                Time complexity: O(n log k) : Constructor
                                 Adding elements to P Queue: O(log n) per element.
                                 Total for k elements: O(n log k)

                                 add(): t/c O(log k)

                Space complexity: O(k) : Extra compute space for storing max k elements in the Priority Queue
         */
        testKLargestElementInStream(k, nums);
    }

    private static void testKLargestElementInStream(int k, int[] nums) {
        KthLargest kthLargest = new KthLargest(k, nums);
        System.out.println(kthLargest.add(3) == 4); // return 4
        System.out.println(kthLargest.add(5) == 5);
        System.out.println(kthLargest.add(10) == 5);
        System.out.println(kthLargest.add(9) == 8);
        System.out.println(kthLargest.add(4) == 8);
    }
}


class KthLargest {
    int k;
    int [] nums;
    PriorityQueue<Integer> priorityQueue;

    KthLargest(int k, int [] nums) {
        this.k = k;
        this.nums = nums;
        priorityQueue = new PriorityQueue<>();

        for (int currVal : nums) {
            addElement(currVal, priorityQueue);
        }
    }

    public int add(int val) {
        addElement(val, priorityQueue);
        return priorityQueue.peek();
    }

    private void addElement(int val, PriorityQueue<Integer> priorityQueue) {
        if (priorityQueue.size() < k) {
            priorityQueue.offer(val);
        } else if (priorityQueue.size() == k && priorityQueue.peek() < val) {
            priorityQueue.poll();
            priorityQueue.add(val);
        }

    }
}
