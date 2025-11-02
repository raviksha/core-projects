package org.self.yahoo.leetcode75.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Medium {

    private static int[][] testMergeIntervals(int[][] intervals) {
        Comparator<Interval> comparator = Comparator.comparingInt((Interval i) -> i.start);
        PriorityQueue<Interval> priorityQueue = new PriorityQueue<>(comparator);
        List<Interval> intervalList = new ArrayList<>();

        for (int[] currItem : intervals) { // O(n log n)
            Interval i = new Interval(currItem[0], currItem[1]);
            priorityQueue.offer(i);
        }

        Interval currItem = priorityQueue.poll();
        intervalList.add(currItem);

        for (int i = 1; i < intervals.length; i++) { // O(n log n)
            Interval prev = intervalList.get(intervalList.size() - 1);
            Interval next = priorityQueue.poll();

            if (next.start <= prev.end) {
                int end = Math.max(prev.end, next.end);
                Interval tmp = new Interval(prev.start, end);
                intervalList.remove(intervalList.size() - 1);
                intervalList.add(tmp);
            } else {
                intervalList.add(next);
            }
        }

        int [][] result = new int[intervalList.size()][2];

        for (int i = 0; i < intervalList.size(); i++) { // O(n)
            Interval tmp = intervalList.get(i);
            result[i] = new int[2];
            result[i][0] = tmp.start;
            result[i][1] = tmp.end;
        }

        return result;
    }


    public static void main(String[] args) {
        System.out.println("Intervals ... Medium");
        // Leet code 56. Merge Intervals
        int [] [] intervals = {{1,3},{2,6},{8,10},{15,18}};

        /*
            Time complexity: O(o log n)
                            Operations on Priority Queue: poll(), offer() : O(log n) per element * n = O(n log n)
                            List operations: add() and remove() takes O(n) time as it needs to shuffle the remaining elements
                            Fetching elements from a give index takes constant time: get(int index), addFirst() etc

            Space complexity: O(n)
                              Worst case when there are no overlapping intervals
                              PriorityQueue, List, result [][] will need extra auxiliary space of O(n)
         */
        int [][] result = testMergeIntervals(intervals);
        System.out.println("testMergeIntervals: ");
        for (int[] item : result) {
            System.out.println(Arrays.toString(item));
        }

        // Leet code 435. Non-overlapping Intervals
        intervals = new int[][] {{1,2},{2,3},{3,4},{1,3}};
        /*
            Time complexity: O(n log n)
                            Sort the interval[][]: O(n log n): Min heap using Priority Queue
                            Loop and merge time intervals: O(n log n): poll() operations compute time: O(log n) per element
                            ArrayList: get() and delete(<<lastElement>>): O(1)
                            Final t/c: O(n log n)


            Space complexity: O(n)
                              Extra compute space to create the min heap using Priority Queue


         */
        int resp = testNonOverlappingIntervals(intervals);
        System.out.println("testNonOverlappingIntervals: " + resp);
    }

    private static int testNonOverlappingIntervals(int[][] intervals) {
        Comparator<Interval> comparator = Comparator.comparingInt((Interval i) -> i.start);
        PriorityQueue<Interval> priorityQueue = new PriorityQueue<>(comparator);
        List<Interval> intervalList = new ArrayList<>();

        for (int[] currItem : intervals) { // O (n log n)
            Interval i = new Interval(currItem[0], currItem[1]);
            priorityQueue.offer(i);
        }

        Interval root = priorityQueue.poll();
        intervalList.add(root);

        int result = 0;
        for (int i = 1; i < intervals.length; i++) {
            Interval next = priorityQueue.poll();
            Interval prev = intervalList.get(intervalList.size() - 1); // O(1)
            // In an ArrayList, the last element removal is always O(1). No re shuffling of remaining elements are required

            if (prev.start <= next.start && prev.end > next.start) {
                result += 1;

                if (prev.end > next.end) {
                    intervalList.remove(intervalList.size() -1);
                    intervalList.add(next);
                }
            } else {
                intervalList.add(next);
            }
        }
        return result;
    }


}

class Interval {
    int start;
    int end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

//class Solution1 {
//    public int eraseOverlapIntervals(int[][] intervals) {
//        if (intervals.length == 0) {
//            return 0;
//        }
//        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1])); // O(n log n)
//        int count = 1;
//        int index = 0;
//
//        for (int i = 1; i < intervals.length; i++) {
//            int prevEnd = intervals[index][1];
//            int currStart = intervals[i][0];
//
//            if (currStart >= prevEnd) {
//                count++;
//                index = i;
//            }
//        }
//        return intervals.length - count;
//    }
//}