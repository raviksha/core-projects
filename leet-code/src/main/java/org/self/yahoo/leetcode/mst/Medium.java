package org.self.yahoo.leetcode.mst;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;

public class Medium {
    public static void main(String[] args) {
        System.out.println("Minimum Spanning Tree.. Medium");

        // Leet code 1584. Min Cost to Connect All Points
        int [][] points = new int[][] {{0,0},{2,2},{3,10},{5,2},{7,0}};

        /*
            Approach: Uses the approach using a Min Spanning Tree along witha Min heap

            Time complexity: O(n ^ 2 log n)
                            Building adjacency list: O(n ^ 2)
                            Prim’s MST traversal: O(n ^ 2 log n)
                            Final: O(n ^ 2 log n)

            Space complexity: O(n ^ 2)
                              Adjacency map: O(n ^ 2)
                              Visited: O(n)
                              Priority Queue: O(n ^ 2): Worst case
                              Final: O(n ^ 2)
         */
        int result = testMinCostToConnect(points);
        System.out.println("testMinCostToConnect: " + result);
    }

    private static int testMinCostToConnect(int[][] points) {

        Map<Integer, List<Point>> adjMap = new HashMap<>();

        for (int i = 0; i < points.length; i++) { // O(V)
            adjMap.put(i, new ArrayList<>());
        }

        for (int i = 0; i < points.length; i++) { //O(V * E)
            int [] root = points[i];
            for (int j = i + 1; j < points.length; j++) {
                int [] neighbor = points[j];
                int manhattanDist = Math.abs((root[0] - neighbor[0])) + Math.abs((root[1] - neighbor[1]));
                Point pJ = new Point(manhattanDist, j);
                Point pI = new Point(manhattanDist, i);
                adjMap.get(i).add(pJ);
                adjMap.get(j).add(pI);
            }
        }
        int cost = 0;
        Set<Integer> visited = new HashSet<>();
        Comparator<Point> comparator = Comparator.comparingInt((Point p) -> p.distance);
        PriorityQueue<Point> priorityQueue = new PriorityQueue<>(comparator);

        priorityQueue.offer(new Point(0, 0)); // O(log n)

        while (visited.size() < points.length && !priorityQueue.isEmpty()) { // O(V * E)
            Point p = priorityQueue.poll(); // O(log n)
            int node = p.node;
            if (!visited.contains(node)) { // O(1)
                int dist = p.distance;
                visited.add(node);
                cost += dist;
                List<Point> neighbors = adjMap.get(node);
                for (Point point : neighbors) {
                    if (!visited.contains(point.node)) {
                        priorityQueue.offer(point);
                    }
                }
            }
        }
        return cost;
    }
}

class Point {
    int distance;
    int node;

    Point(int distance, int node) {
        this.distance = distance;
        this.node = node;
    }
}
