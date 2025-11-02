package org.self.yahoo.leetcode.greedy;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Medium {
    private static boolean testJumpGame(int[] nums) {
        int targetIndex = nums.length -1;
        int size = nums.length - 1;
        boolean isReachable = true;

        for (int i = size -1; i >=0 ; i--) {

            if (i + nums[i] < targetIndex) {
                isReachable = false;
            } else {
                isReachable = true;
                targetIndex = i;
            }

        }
        return isReachable;
    }

    private static int testJumpGameII(int[] nums) {
        int targetIndex = nums.length -1;
        int coverage = 0;
        int lastJumpIndex = 0;
        int totalJumps = 0;

        for (int i = 0; i < targetIndex; i++) {
            coverage = Math.max(coverage, i + nums[i]);

            if (i == lastJumpIndex) {
                lastJumpIndex = coverage;
                totalJumps++;


                if (coverage >= targetIndex) {
                    return totalJumps;
                }


            }
        }
        return totalJumps;
    }

    private static int testGasStation(int[] gas, int[] cost) {
        int totalGas = 0;
        int totalCost = 0;
        int startIndex = 0;
        int gasInTank = 0;

        for (int i = 0; i < gas.length; i++) {
            totalCost += cost[i];
            totalGas += gas[i];

            gasInTank += gas[i] - cost[i];

            if (gasInTank < 0) {
                startIndex = i + 1;
                gasInTank = 0;
            }
        }
        return totalGas < totalCost ? -1 : startIndex;
    }

    private static int testTaskScheduler(char[] tasks, int n) {
        Comparator<Task> comparator = Comparator.comparingInt((Task t) -> t.freq).reversed();
        PriorityQueue<Task> priorityQueue = new PriorityQueue<>(comparator);
        Queue<Task> coolingQueue = new LinkedList<>();

        int [] freqArr = new int[26];
        int time = 0;
        for (char c : tasks) { // t/c : O(n)
            freqArr[c - 'A'] += 1;
        }

        for (int freq : freqArr) { // O(t)
            if (freq > 0) {
                Task t = new Task(freq, 'A', time);
                priorityQueue.add(t); // O(log t)
            }

        }

        while (!priorityQueue.isEmpty() || !coolingQueue.isEmpty()) { // O(t)
            if (coolingQueue.peek() != null && time - coolingQueue.peek().time > n) {
                priorityQueue.add(coolingQueue.poll()); // O(log t)
            }

            Task t = priorityQueue.poll(); // O(log t)
            if (t != null) {
                t.freq -= 1;
                if (t.freq > 0) {
                    t.time = time;
                    coolingQueue.add(t);
                }
            }

            time++;
        }

        return time;


    }

    public static void main(String[] args) {
        // Leet code 55. Jump Game
        int [] nums = {2,3,1,1,4};

        /*
            Approach used here is: Greedy approach where the array is traversed from the rear and checked for each i - 1 index if the targetIndex is reachable;

            Time complexity: O(n): nums[] is traversed linearly

            Space complexity: O(1): No extra compute space is used
         */
        var isReachable = testJumpGame(nums);
        System.out.println("isReachable: " + isReachable);

        /*
            Approach is using a Greedy algo.
            BruteForce/Recursive/Dynamic Programming: Provides a solution but with a high exponential t/c

            Time complexity: O(n): nums[] is traversed linearly

            Space complexity: O(1): No extra compute space required

         */

        // Leet code 45. Jump Game II

        int numberOfJumps = testJumpGameII(nums);
        System.out.println("testJumpGameII: " + numberOfJumps);


        // Leet code 134. Gas Station
        int [] gas = {1,2,3,4,5};
        int [] cost = {3,4,5,1,2};

        /*
            Approach is using a Greedy algorithm

            Time complexity: O(n): Does a single linear pass over the gas[] and cost []

            Space complexity: O(1): No extra compute space required
         */
        int beginIndex = testGasStation(gas, cost);
        System.out.println("testGasStation: " + beginIndex);

        //  Leet code 621. Task Scheduler
        char [] tasks = {'A','A','A','B','B','B'};
        int n = 2;

        /*
            Approach is using a Priority queue max heap;

            Time complexity: O(n log t)
                             Looping all elements of the tasks[]: O(n)
                             Adding elements with frequencies in the Priority Queue: O(log t) t => number of unique task labels with different frequencies
                             Elements added to the colling queue :O(n) max entries
                             Total time complexity: O(n log t)

            Space complexity: O(t)
                             Priority queue holds t number of unique task labels
                             Cooling queue will hold a max of O(t) elements at any given time
                             Concluding space complexity: O(t)
         */
        int cpuIntervals = testTaskScheduler(tasks, n);
        System.out.println("testTaskScheduler : " + cpuIntervals);
    }




}

class Task {
    int freq;
    char label;
    int time;;

    Task(int freq, char label, int time) {
        this.freq = freq;
        this.label = label;
        this.time = time;
    }
}
