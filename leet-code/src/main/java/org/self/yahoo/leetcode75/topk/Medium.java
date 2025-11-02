package org.self.yahoo.leetcode75.topk;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.PriorityQueue;

public class Medium {
    public static void main(String[] args) {
        System.out.println("Top K frequent elements.... Medium ...");
        // Leet code 347. Top K Frequent Elements
        int [] nums = {1,1,1,2,2,3};
        int k = 2;

        /*
            Time complexity: O(n log n)
                             Operations in hashMap takes constant time: O(1) for all put() and get() operations
                             Adding and removing elements in Priority queue: O(log n), shuffling elements upon each offer() and poll()
                             Adding n elements: O(n log n)

            Space complexity: O(n)
                              Extra auxiliary space to store n elements of the nums[] in the freqMap and Priority queue
                              Worst case when all elements are unique: O(n)
         */

        int [] result = testTopKFrequentElement(nums, k);
        System.out.println("testTopKFrequentElement: " + Arrays.toString(result));
    }

    private static int[] testTopKFrequentElement(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        Comparator<Pair> comparator = Comparator.comparingInt((Pair p) -> p.freq).reversed();
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>(comparator);

        for (int curr : nums) { // O(n)
            freqMap.put(curr, freqMap.getOrDefault(curr, 0) + 1);
        }

        for (Entry<Integer, Integer> entry : freqMap.entrySet()) { // O(n log n)
            Pair p = new Pair(entry.getKey(), entry.getValue());
            priorityQueue.offer(p);
        }

        int ctr = 0;
        int [] result = new int[k];
        while (ctr < k) { // O(k)
            result[ctr] = Objects.requireNonNull(priorityQueue.poll()).num;
            ctr++;
        }

        return result;
    }
}




class Pair {
    int freq;
    int num;

    Pair(int freq, int num) {
        this.freq = freq;
        this.num = num;
    }
}