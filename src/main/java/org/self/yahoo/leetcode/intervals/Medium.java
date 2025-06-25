package org.self.yahoo.leetcode.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Medium {

    private static int[][] testMergeIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0])); // O(n log n)

        List<int []> list = new ArrayList<>();
        int startTime = intervals[0][0];
        int endTime = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) { // O(n)
            int currStart = intervals[i][0];
            int currEnd = intervals[i][1];

            if (currStart <= endTime) {
                endTime = Math.max(currEnd, endTime);
            } else {
                int [] tArr = new int [] {startTime, endTime};
                list.add(tArr);
                startTime = currStart;
                endTime = currEnd;
            }
        }
        int [] tArr = new int [] {startTime, endTime};
        list.add(tArr);
        int [] [] result = new int [list.size()][2];
        int index = 0;

        for (int[] item : list) { // O(n)
            result[index] = item;
            index++;
        }

        return result;

    }

    public static void main(String[] args) {
        System.out.println("Intervals medium ....");

        // Leet code 56. Merge Intervals
        int [] [] intervals = new int [] [] {{1,3},{2,6},{8,10},{15,18}};

        /*
            Using the approach of:
                1. Sort the intervals by start time
                2. Keep aggregating the intervals till the start time of the next interval > endTime of the previous
                3. Keep adding every interval break chunk in a list and return a result [][] at the end

                Time complexity: O(n log n)
                               : Sorting the 2D array : O(n log n): Internally uses merge sort
                               : Looping through all the elements on the intervals[][] O(n)
                               Dominating t/c : O(n log n)

                Space complexity: O(n): Extra compute space required to
                                   => Store all intervals in a list: Worst case all the intervals will have no overlap
                                   => Space consumed by result [][] is not considered as its part of the output
         */
        int [] [] result = testMergeIntervals(intervals);

        System.out.println("testMergeIntervals: ");
        for (int[] item : result) {
            System.out.println(Arrays.toString(item));
        }

        // Leet code 57. Insert Interval
        intervals = new int[][] {{1,3},{6,9}};
        int [] newInterval = {2, 5};

        /*
            The Approach used here is:
                1. First store all the elements in the list where the end time of the interval is less than the beginning time of the new interval
                2. Store all the intervals where the begin time is less than or equal to the end time of the new interval
                3.Finally store all the other remaining intervals

                Time complexity: O(n)
                                 Loops through the entire intervals[] once: O(n)
                                 Loops through all the stored merged intervals in the list, worst case without any overlap: O(n)
                                 Concluding t/c: O(n)

                Space complexity: O(n) Extra compute space required to store the merge intervals
                                       Worst case: O(n) when no interval overlap

         */
        result = testInsertIntervals(intervals, newInterval);

        System.out.println("testInsertIntervals: ");
        for (int[] item : result) {
            System.out.println(Arrays.toString(item));
        }
    }

    private static int[][] testInsertIntervals(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0 && newInterval.length > 0) {
            return new int[][] {newInterval};
        }

        List<int []> list = new ArrayList<>();

        int i = 0;
        int n = intervals.length;

        // Add all the intervals which ends before the newInterval starts
        while (i < n && intervals[i][1] < newInterval[0]) {
            list.add(intervals[i]);
            i++;
        }

        // Add all the intervals where the begin time of intervals[i] <= newInterval[1] end time

        int mergeBeginIndex = newInterval[0];
        int mergeEndIndex = newInterval[1];

        while (i < n && intervals[i][0] <= newInterval[1]) {
            mergeBeginIndex = Math.min(intervals[i][0], mergeBeginIndex);
            mergeEndIndex = Math.max(intervals[i][1], mergeEndIndex);
            i++;
        }
        list.add(new int[] {mergeBeginIndex, mergeEndIndex});

        while (i < n) {
            list.add(intervals[i]);
            i++;
        }

        int [] [] result = new int[list.size()][2];
        int index = 0;

        for (int[] item : list) {
            result[index] = item;
            index++;
        }

        return result;
    }


}
