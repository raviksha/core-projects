package org.self.yahoo.leetcode75.tsort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Medium {
    public static void main(String[] args) {
        System.out.println("Topological sort: ...... Medium..." );
        // Leet code 210. Course Schedule II
        int [][] preReq = {{1,0},{2,0},{3,1},{3,2}};
        int numOfCourses = 4;

        /*
            Time complexity: O(V + E) => t/c for a DAG using BFS

            Space complexity: O(V + E)
                              O(E) => Adjacency list storing all edges
                              O(V) => Visited array
                              Final s/c: O(V + E)
         */
        int [] result = testCourseScheduleIIBFS(numOfCourses, preReq);
        System.out.println("testCourseScheduleIIBFS: " + Arrays.toString(result));
    }

    private static int[] testCourseScheduleIIBFS(int numOfCourses, int[][] preReqArr) {
        List<List<Integer>> adjList = new ArrayList<>(); // O(n)
        Stack<Integer> stack = new Stack<>(); // O(n)
        int [] visited = new int[numOfCourses];

        for (int i = 0; i < numOfCourses; i++) {
            adjList.add(i, new ArrayList<>());
        }

        for (int[] item : preReqArr) {
            int preReq = item[1];
            int node = item[0];
            adjList.get(node).add(preReq);
        }

        for (int i = 0; i < adjList.size(); i++) {
            for (int val : adjList.get(i)) {
                visited[val] += 1;
            }
        }
        Queue<Integer> queue = new LinkedList<>(); // O(V): Worst case, if one node is connected to all other


        for (int i = 0; i < numOfCourses; i++) {
            if (visited[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {

            for (int i = 0; i < queue.size(); i++) {
                int item = queue.poll();
                stack.push(item);

                for (int depItem : adjList.get(item)) {
                    visited[depItem] -= 1;

                    if (visited[depItem] == 0) {
                        queue.offer(depItem);
                    }
                }


            }
        }
        System.out.println("stack : " + stack);
        if (stack.size() == numOfCourses) {
            int [] result = new int[stack.size()];
            int index = 0;
            while (!stack.isEmpty()) {
                result[index++] = stack.pop();
            }
            return result;
        } else {
            return new int[] {0};
        }

    }
}


//class Solution {
//    public int[] findOrder(int numCourses, int[][] prerequisites) {
//
//        List<List<Integer>> graph = new ArrayList<>();
//        Stack<Integer> stack = new Stack<>();
//        Queue<Integer> queue = new LinkedList<>();
//
//        int [] inDegree = new int[numCourses];
//
//        for (int i = 0; i < numCourses; i++) {
//            graph.add(i, new ArrayList<>());
//        }
//
//        for (int adajList[]: prerequisites) {
//            int dep = adajList[1];
//            int node = adajList[0];
//            graph.get(node).add(dep);
//        }
//
//        System.out.println("graph: " + graph);
//
//        // for (int i = 0; i < graph.size(); i++) {
//        //     var adajList = graph.get(i);
//        //     for (int j = 0; j < adajList.size(); j++) {
//        //         int val = adajList.get(j);
//        //         System.out.println("Populate inDegree");
//        //         inDegree[val] += 1;
//        //     }
//        // }
//
//        for (int i = 0; i < graph.size(); i++) {
//            for (int val : graph.get(i)) {
//                inDegree[val] += 1;
//            }
//        }
//
//        System.out.println("inDegree: " + Arrays.toString(inDegree));
//
//        for (int j = 0; j < numCourses; j++) {
//            if (inDegree[j] == 0) {
//                queue.add(j);
//            }
//        }
//        System.out.println("Pre queue: " + queue);
//        while(!queue.isEmpty()) {
//            int node = queue.poll();
//            stack.push(node);
//
//            for (int val: graph.get(node)) {
//                inDegree[val] -= 1;
//
//                if (inDegree[val] ==0) {
//                    queue.offer(val);
//                }
//                System.out.println("InProgress queue: " + queue);
//            }
//        }
//        System.out.println("Stack: " + stack);
//        int [] result;
//
//        if (stack.size() == numCourses) {
//            result = new int [numCourses];
//            int index = 0;
//            while (!stack.isEmpty()) {
//                result[index] = stack.pop();
//                index++;
//            }
//            return result;
//        }
//        return new int [0];
//    }
//}