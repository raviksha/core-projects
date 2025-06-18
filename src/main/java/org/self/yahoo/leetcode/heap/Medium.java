package org.self.yahoo.leetcode.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Medium {
    private static int testFurthestBuildingToReach(int[] height, int ladder, int bricks) {
        // Creation of a min heap
        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        int prevHeight = height[0];

        for (int i = 1; i < height.length; i++) { // O(n)
            int currHeight = height[i];
            int jumpHeight = currHeight - prevHeight;

            if (jumpHeight > 0) {
                pQ.add(jumpHeight); // O(log l)
            }

            if (pQ.size() > ladder) {
                bricks = bricks - pQ.poll(); // O(log l)
            }

            if (bricks < 0) {
                return i -1;
            }
            prevHeight = currHeight;
        }

        return height.length - 1;
    }
    public static void main(String[] args) {
        System.out.println("Medium heap .....");

        // Leet code 1642. Furthest Building You Can Reach
        int [] height = {4,2,7,6,9,14,12};
        int ladder = 1;
        int bricks = 5;

        /*
            Uses a Min heap approach by using Priority Queue.
            The Top element of the queue will store the min jump height, which bricks can replace

            Time complexity: O(n) * O(log l) => O(n log l)
                             l = number of ladder = Max number of elements in the min heap PQ
                             t/c to iterate over the height array of n elements: O(n)
                             t/c to push() and pop() elements from Priority Queue: O(log l)

            Space complexity: O(l): Max number of items in the PQ = number of ladders
         */
        int result = testFurthestBuildingToReach(height, ladder, bricks);
        System.out.println("testFurthestBuildingToReach: " + result);

        // Leet code 1834. Single-Threaded CPU
        int [] [] tasks = new int [] []{{1,2},{2,4},{3,2},{4,1}};

        /*
            Approach is using two queues with a min heap Priority queue

            Time complexity:  O(n log n)
                              Sorting the task list on enqueue time: O(n log n)
                              Priority Q operations: offer() and poll(): O(log n)
                              Concluding t/c: O(n log n)

            Space complexity: O(n)
                              Extra space required to create n Task objects and the result task execution indexes order
         */
        var taskOrder = testSingleThreadedCPU(tasks);
        System.out.println("testSingleThreadedCPU: " + Arrays.toString(taskOrder));
    }

    private static int [] testSingleThreadedCPU(int[][] tasks) {
        Comparator<Task> cpuComp = Comparator.comparingInt((Task t) -> t.processingT).thenComparingInt((Task t) -> t.index);
        Comparator<Task> enqTimeComp = Comparator.comparingInt((Task t) -> t.enQueueT);
        Task [] taskList = new Task[tasks.length];
        int [] result = new int[tasks.length];

        for (int i = 0; i < tasks.length; i++) {
            int [] entry = tasks[i];
            int enQTime = entry[0];
            int processTime = entry[1];
            taskList[i] = new Task(enQTime, processTime, i);
        }

        Arrays.sort(taskList, enqTimeComp); // O(n log n)
        PriorityQueue<Task> cpuQ = new PriorityQueue<>(cpuComp);

        int time = 0;
        int resIndex = 0;
        int taskIndex = 0;
        int n = tasks.length;

        while (resIndex < n) {
            while (taskIndex < n && taskList[taskIndex].enQueueT <= time) {
                cpuQ.offer(taskList[taskIndex]); // O(log n)
                taskIndex++;
            }

            if (cpuQ.isEmpty()) {
                time = taskList[taskIndex].enQueueT;
            } else {
                Task t = cpuQ.poll(); // O(log n)
                time += t.processingT;
                result[resIndex] = t.index;
                resIndex++;

            }
        }
        return result;
    }


}

class Task {
    int enQueueT;
    int processingT;
    int index;

    Task (int enQueueT, int processingT, int index) {
        this.enQueueT = enQueueT;
        this.processingT = processingT;
        this.index = index;
    }
}
