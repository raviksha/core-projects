package org.self.yahoo.leetcode.topologicalsort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Medium {

    private static boolean testCourseSchedule(int[][] preRequisite, int numOfCourses) {
        List<List<Integer>> graph = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        int [] inDegree = new int[numOfCourses];

        for (int i = 0; i < numOfCourses; i++) {
            graph.add(i, new ArrayList<>());
        }
        // [1,0]
        for (int [] adjLiat : preRequisite) {
            int dep = adjLiat[1];
            int node = adjLiat[0];
            graph.get(node).add(dep);
        }

        // Identifies if there are any cycles in the DAG. If the cycle is present, then all inDegree[v] for all edge will be > 0
        for (int i = 0; i < graph.size(); i++) {
            for (int val : graph.get(i)) {
                inDegree[val] += 1;
            }
        }

        // Identifies the node to begin the graph traversal. Its all the nodes with inDegree[v] ==0, which are added to the queue
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        int numOfElements = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();

            numOfElements++;
            for (int val : graph.get(node)) {
                inDegree[val] -= 1;
                if (inDegree[val] == 0) {
                    queue.offer(val);
                }

            }
        }
        return numOfElements == numOfCourses;
    }

    private static int[] testCourseScheduleII(int numOfCourses, int[][] preRequisite) {

        List<List<Integer>> graph = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();

        // Initializes the graph with adjacency list with empty ArrayList
        for (int i = 0; i < numOfCourses; i++) { // O(E)
            graph.add(i, new ArrayList<>());
        }

        // Initialize each node with its adjacency list O(V)
        for (int [] adjList : preRequisite) {
            int dep = adjList[1];
            int node = adjList[0];

            graph.get(node).add(dep);
        }
        // graph: [[], [0], [0], [1, 2]]
        System.out.println("graph: " + graph);


        int [] inDegree = new int[numOfCourses];

        // Initialize the inDegree [] => Number of inward directed edges to the nodes
        for (int i = 0; i < graph.size(); i++) { // O(V + E)
            for (int val : graph.get(i)) {
                inDegree[val] += 1;
            }
        }
        // inDegree: [2, 1, 1, 0]
        System.out.println("inDegree: " + Arrays.toString(inDegree));
        // Add the leaf node of the graph with zero inward directed edge to the queue
        for (int i = 0; i < numOfCourses; i++) { // O(E)
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) { // O(E)
            int node = queue.poll();
            stack.push(node);
            // Visit each node in the graph and extract the inward edges and for each visited node, reduce the count by 1
            // If the final value is 0, then the node has no inward edges and can be added to the queue to be marked as visited
            for (int val: graph.get(node)) {
                inDegree[val] -= 1;

                if (inDegree[val] == 0) {
                    queue.offer(val);
                }
            }
        }
        int [] result = null;
        int index = 0;
        // Add the elements from the stack to the result [] for the final ordering of courses
        if (stack.size() == numOfCourses) { // O(V)
            result = new int[numOfCourses];
            while (!stack.isEmpty()) {

                result[index] = stack.pop();
                index++;
            }
            return result;
        }

        return new int[]{};
    }

    public static void main(String[] args) {
        System.out.println("Topological sort .. Medium");


        // Leet code 207. Course Schedule
        //[[1,0]]
        int [][] preRequisite = new int[][] {{1, 0}};
        int numOfCourses = 2;

        /*
            Approach is using a BFS traversal over the Directed Acyclic graph to check if there is a cycle

            Time complexity: O(V + E)
                             Building adjacency list: O(E) => Number of edges of the DAG
                             In-degree array: O(V) => Number of courses
                             BFS traversal: O(V + E)
                             Concluding: O(V + E)


            Space complexity: O(V + E)
                              Adjacency list: O(E)
                              In degree array: O(V)
                              Queue in the worst case will store O(V) if one node is connected to all other nodes
                              Concluding s/c: O(V + E)
         */

        boolean isCyclic = testCourseSchedule(preRequisite, numOfCourses);
        System.out.println("testCourseSchedule: " + isCyclic);





        // Leet code 210. Course Schedule II
        // [[1,0],[2,0],[3,1],[3,2]]
        preRequisite = new int[][] {{1,0},{2,0},{3,1},{3,2}};
        numOfCourses = 4;

        /*
            Approach is using a BFS traversal to compute a sequence for a DAG with no cycles

            Time complexity: O(V + E)
                             Building adjacency list: O(E): Number of edges of DAG
                             InDegree array: O(V): Number of courses
                             BFS traversal: O(V + E)
                             Concluding: O(V + E)


            Space complexity: O(V + E)
                            Building adjacency list: O(E)
                            In degree []: O(V)
                            Queue in the worst case will store O(V) if one edge connects to all nodes
                            Stack to store the ordering sequence: O(V)
                            Concluding: O(V + E)
         */
        int [] result = testCourseScheduleII(numOfCourses, preRequisite);
        System.out.println("testCourseScheduleII: " + Arrays.toString(result));

    }

}
