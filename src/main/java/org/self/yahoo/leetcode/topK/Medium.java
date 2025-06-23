package org.self.yahoo.leetcode.topK;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class Medium {

    private static int [] testTopKElementsV1(int[] nums, int k) {

        Comparator<Pair> comparator = Comparator.comparingInt((Pair p) -> p.freq).reversed();
        PriorityQueue<Pair> pQueue = new PriorityQueue<>(comparator);
        Map<Integer, Integer> freqMap = new HashMap<>();
        int [] result = new int[k];

        for (int val : nums) { // O(n log n)
            freqMap.put(val, freqMap.getOrDefault(val, 0) + 1); // O(log n)
        }

        for (Entry<Integer, Integer> entry : freqMap.entrySet()) { // O(n log n)
            int num = entry.getKey();
            int freq = entry.getValue();
            Pair p = new Pair(num, freq); // O(log n)
            pQueue.offer(p);
        }

        int index = 0;

        while (index < k && !pQueue.isEmpty()) { // O(k log n)
            Pair p = pQueue.poll();
            result[index] = p.num;
            index++;
        }

        return result;

    }

    private static int[] testTopKElementsV2(int[] nums, int k) {
        Comparator<Pair> comparator = Comparator.comparingInt((Pair p) -> p.freq);
        PriorityQueue<Pair> pQueue = new PriorityQueue<>(comparator);
        Map<Integer, Integer> freqMap = new HashMap<>();
        int [] result = new int[k];

        for (int key : nums) { // O(n)
            freqMap.put(key, freqMap.getOrDefault(key, 0) + 1); // O(1)
        }

        for (Map.Entry<Integer, Integer> entry: freqMap.entrySet()) { // O(n log k)
            Pair p = new Pair(entry.getKey(), entry.getValue());

            if (pQueue.size() < k) { // O(log k)
                pQueue.offer(p);
            } else if (p.freq > pQueue.peek().freq) {
                pQueue.poll();
                pQueue.offer(p);
            }
        }

        int index = 0;
        while (!pQueue.isEmpty()) { // O(k log k)
            Pair p = pQueue.poll();
            result[index] = p.num;
            index++;
        }

        return result;

    }

    public static void main(String[] args) {
        System.out.println("Medium .. Top K elements ..");

        // Leet code 347. Top K Frequent Elements
        int [] nums = {1,1,1,2,2,3};
        int k = 2;

        /*
            Approach is using a PQueue to store the frequency of each number and poll() the top k

            Time complexity: O(n log n)
                             PQueue t/c for poll() and offer() are O(log n) for each element. O(n log n): total for n elements
                             PQueues after each poll() or offer() re-heapifies to maintain the heap property

            Space complexity: O(n): Extra space to compute the frequency map
         */
        var result = testTopKElementsV1(nums, k);
        System.out.println("testTopKElementsV1: " + Arrays.toString(result));

        /*
            Approach is the same as testTopKElementsV1() instead the P queue is having a max of k entries instead of n
            This reduces the overall time complexity from O(n log n) => O(n log k)

             Time complexity: O(n log k)

             Space complexity: O(n): Extra space to compute the frequency map
         */
        result = testTopKElementsV2(nums, k);
        System.out.println("testTopKElementsV2: " + Arrays.toString(result));


        // Leet code 973. K Closest Points to Origin
        int [] [] points = {{1,3},{-2,2}};
        k = 1;

        /*
            Approach is using a Priority queue with a custom Comparator to compute the distance from origin

            Time complexity: O(n log n)
                             Map operations: O(1) * n elements = O(n)
                             P Queue operations: O(log n) per poll() and offer(): O(n log n) for n elements

            Space complexity: O(n): Extra compute space to store n space coordinates in the Priority Queue

         */
        var nearestPoints = testKClosestPointsToOrigin(points, k);
        System.out.println("testKClosestPointsToOrigin: ");
        for (int[] point : nearestPoints) {
            System.out.print(Arrays.toString(point) + " ");
        }
    }

    private static int[][] testKClosestPointsToOrigin(int[][] points, int k) {
        Comparator<Points> comparator = Comparator.comparingInt((Points p) -> p.distance);
        PriorityQueue<Points> priorityQueue = new PriorityQueue<>(comparator);
        int [][] results = new int [k][2];

        for (int[] point : points) {
            Points p = new Points(point[0], point[1]);
            priorityQueue.offer(p);
        }

        int index = 0;
        while (index < k && !priorityQueue.isEmpty()) {
            Points p = priorityQueue.poll();
            results[index] = new int [] {p.x, p.y};
            index++;
        }

        return results;
    }


}


class Points {
    int x;
    int y;
    int distance;

    Points(int x, int y) {
        this.x = x;
        this.y = y;
        distance = (x * x) + (y * y);
    }
}

class Pair {
    int num;
    int freq;

    Pair(int num, int freq) {
        this.num = num;
        this.freq = freq;
    }
}
