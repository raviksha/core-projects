package org.self.yahoo.leetcode.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

public class Medium {
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

        // Leet code 787. Cheapest Flights Within K Stops
        // [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]]
        int[][] flights = {{0,1,100},{1,2,100},{2,0,100},{1,3,600},{2,3,200}};
        n = 4;
        int src = 0;
        int dst = 3;
        k = 1;

        /*
            Approach is using the Dijkstra Algo but instead of tracking the path for the cheapest route,
            we need to also track the number of hops to not exceed from the desired input k

            Time complexity: O(V * E)
                            Build an adjacency list: O(E)
                            Updating the minPrice []: O(V)
                            Queue operations pop() and offer(): O(1)
                            Traversing all elements in the queue: O(V * E): Each element is the list is visited multiple times for each path traversal
                            Final t/c: O(V * E)

            Space complexity: O(V + E)
                            Build an adjacency list: O(E)
                            Updating the minPrice []: O(V)
                            Queue: At worst case can store up to O(E) as each city an enqueue multiple routes
                            Final s/c: O(V + E)
         */
        int price = testCheapestFlightWithinKStops(n, flights, src, dst,k);
        System.out.println("testCheapestFlightWithinKStops: " + price);
    }

    private static int testCheapestFlightWithinKStops(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<Route>> adajMap = new HashMap<>();
        Queue<Route> queue = new LinkedList<>();

        // Build an adjacency list

        for (int[] edge : flights) {
            int fromCity = edge[0];
            int toCity = edge[1];
            int price = edge[2];

            // Route(int stops, int dst, int price)
            List<Route> adjList = adajMap.getOrDefault(fromCity, new ArrayList<>());
            Route r = new Route(0, toCity, price);
            adjList.add(r);
            adajMap.put(fromCity, adjList);
        }

        int [] minPrice = new int[n + 1];
        Arrays.fill(minPrice, Integer.MAX_VALUE);
        minPrice[src] = 0;

        Route root = new Route(0, src, 0);
        queue.offer(root);

        while (!queue.isEmpty()) {
            Route r = queue.poll();
            int destCity = r.dst;
            int stops = r.stops;
            int price = r.price;

            if (stops > k) {
                continue;
            }
            List<Route> list = adajMap.getOrDefault(destCity, new ArrayList<>());

            for (Route nextR : list) {
                int newDest = nextR.dst;
                int totalPrice = price + nextR.price;
                int totalStops = stops + 1;

                if (totalPrice < minPrice[newDest]) {
                    minPrice[newDest] = totalPrice;
                    Route enRoute = new Route(totalStops, newDest, totalPrice);
                    queue.offer(enRoute);
                }
            }
        }

        if (minPrice[dst] != Integer.MAX_VALUE) {
            return minPrice[dst];
        }
        return -1;
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

    @Override
    public String toString() {
        return srcNode + " : " + destNode + " : " + distance;
    }
}

class Route {
    int stops;
    int dst;
    int price;

    Route(int stops, int dst, int price) {
        this.stops = stops;
        this.dst = dst;
        this.price = price;
    }

    @Override
    public String toString() {
        return stops + " : " + dst + " : " + price;
    }
}
