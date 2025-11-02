package org.self.yahoo.book.demo.chap6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;

class Edge {
    int dest;
    int weight;

    Edge(int dest, int weight) {
        this.dest = dest;
        this.weight = weight;
    }
}

class Pair {
    int dist;
    int node;

    Pair(int dist, int node) {
        this.dist = dist;
        this.node = node;
    }
}

public class Dijkstra {
    private final Map<Integer, List<Edge>> adjList;

    // Constructor
    public Dijkstra() {
        this.adjList = new HashMap<>();
    }

    // Add edge to the Directed Graph
    private void addEdge(int src, int dest, int weight) {
        adjList.putIfAbsent(src, new ArrayList<>());
        adjList.putIfAbsent(dest, new ArrayList<>());


        adjList.get(src).add(new Edge(dest, weight)); // Add edge src -> dest
        adjList.get(dest).add(new Edge(src, weight)); //  Add edge dest -> src (undirected)
    }

    // Dijkstra’s ALgo to fine the shortest path from a given source node
    private void findShortestPath(int srcNode) {
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a.dist));
        Map<Integer, Integer> distance = new HashMap<>();
        TreeMap<Integer, Integer> parent = new TreeMap<>(Comparator.reverseOrder());
        Set<Integer> visited = new HashSet<>();

        // Initialize distance to Infinity

        for (int node : adjList.keySet()) {
            distance.put(node, Integer.MAX_VALUE);
            parent.put(node, node);
        }

        // Source distance is zero
        distance.put(srcNode, 0);

        // Initialize Priority queue with source
        priorityQueue.offer(new Pair(0, srcNode));

        while (!priorityQueue.isEmpty()) {
            Pair current = priorityQueue.poll();
            int node = current.node;
            int dist = current.dist;

            if (visited.contains(node)) {
                continue;
            }
            visited.add(node);
            var edgesList = adjList.getOrDefault(node, new ArrayList<>());

            for (var edge : edgesList) {
                int newDist = dist + edge.weight;

                if (newDist < distance.get(edge.dest)) { // Found a shorter path
                    distance.put(edge.dest, newDist);
                    priorityQueue.offer(new Pair(newDist, edge.dest));
                    parent.put(edge.dest, node);
                }
            }
        }
        
        // Print the shortest path
        for (var entry : distance.entrySet()) {
            if (entry.getValue() == Integer.MAX_VALUE) {
                System.out.println("Node: " + entry.getKey()  + " is not reachable");
            } else {
                System.out.println("Node: " + entry.getKey()  + "  ->  "  + entry.getValue());
            }

        }

        // Print parent nodes
        StringBuilder stringBuilder = new StringBuilder();
        int firstKey = parent.firstKey();
        int firstValue = parent.get(firstKey);

        while (firstKey != firstValue) {
            stringBuilder.append(firstKey).append(" ");
            firstKey = firstValue;
            firstValue = parent.get(firstValue);
        }
        stringBuilder.append(firstKey);

        System.out.println("Shortest path: " + stringBuilder.reverse());
    }





    public static void main(String[] args) {

        // https://takeuforward.org/data-structure/g-35-print-shortest-path-dijkstras-algorithm/
        System.out.println("Dijkstra.......");

        Dijkstra graph = new Dijkstra();

        graph.addEdge(1, 2, 5);
        graph.addEdge(1, 3, 10);
        graph.addEdge(2, 4, 3);
        graph.addEdge(3, 4, 7);
        graph.addEdge(4, 5, 2);

        // Input to simulate if a node is not reachable
        //graph.addEdge(6, 7, 2);

        /*
            Time complexity :  O((V + E) log V) (same as directed case).

            Space complexity : O(V + E) for storing adjacency list and priority queue.
         */
        graph.findShortestPath(1);

    }
}
