package org.self.yahoo.leetcode.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Medium {
    public static void main(String[] args) {
        System.out.println("Shortest path . Medium");

        // Leet code 743. Network Delay Time
        // [[2,1,1],[2,3,1],[3,4,1]]
        int [][] times = new int[][] {{2,1,1},{2,3,1},{3,4,1}};
        int n = 4;
        int k = 2;
        /*
            Approach is using Dijkstra Algo

            Time complexity: O(E log V)
                             E: Number of directed edges: times[][].length
                             V: Number of nodes

                             Building adjacency list: O(E): Number of edges
                             Priority queue operation:
                                    Each poll() and offer(): O(log E)
                                    Total for V nodes: O(E log V)
                             Final t/c: O(E log V)

            Space complexity: O(V + E)
                            adjMap: stores the mapping of all nodes to edges and distance: O(V + E)
                            distance []: Store distance for each node: O(V)
                            PQueue: In worst case will hold all V elements: O(V)
                            Final space complexity: O(V + E)
         */
        int delayTime = testNetworkDelayTime(times, n, k);
        System.out.println("testNetworkDelayTime: " + delayTime);
    }

    private static int testNetworkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<Pair>> adjMap = new HashMap<>();
        Comparator<Pair> comparator = Comparator.comparingInt(p -> p.distance);
        PriorityQueue<Pair> queue = new PriorityQueue<>(comparator);

        // Build the adjacency list

        for (int[] edge : times) {
            int srcNode = edge[0];
            int destNode = edge[1];
            int distance = edge[2];

            List<Pair> list = adjMap.getOrDefault(srcNode, new ArrayList<>());
            Pair p = new Pair(srcNode, destNode, distance);
            list.add(p);
            adjMap.put(srcNode, list);
        }

        int [] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        distance[k] = 0;

        Pair edge = new Pair(k,  k, 0);
        queue.offer(edge);

        while (!queue.isEmpty()) {
            edge = queue.poll();
            int srcNode = edge.destNode;
            int dist = edge.distance;

            var list = adjMap.getOrDefault(srcNode, new ArrayList<>());

            for (Pair p : list) {
                int destNode = p.destNode;
                int newDistance = dist + p.distance;

                if (newDistance < distance[destNode]) {
                    distance[destNode] = newDistance;
                    Pair tmp = new Pair(srcNode, destNode, newDistance);
                    queue.offer(tmp);
                }
            }

        }
        int maxDistance = 0;
        for (int i = 1; i <= n; i++) {
            if (distance[i] == Integer.MAX_VALUE) return -1;
            maxDistance = Math.max(maxDistance, distance[i]);
        }
        return maxDistance;
    }
}

class Pair {
    int srcNode;
    int destNode;
    int distance;

    Pair(int srcNode, int destNode, int distance) {
        this.srcNode = srcNode;
        this.destNode = destNode;
        this.distance = distance;
    }
}
