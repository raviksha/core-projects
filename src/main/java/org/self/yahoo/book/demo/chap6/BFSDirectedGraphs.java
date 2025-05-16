package org.self.yahoo.book.demo.chap6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BFSDirectedGraphs {
    private Map<Integer, ArrayList<Integer>> adjList;

    public BFSDirectedGraphs() {
        adjList = new HashMap<>();
    }

    // Add vertex to the Graph
    public void addVertex(int v) {
        adjList.putIfAbsent(v, new ArrayList());
    }

    // Add an edge for an Directed graphs
    public void addEdge(int source, int destination) {
        adjList.putIfAbsent(source, new ArrayList());
        adjList.get(source).add(destination);

        // Remove this for a directed graph
//        adjList.putIfAbsent(destination, new ArrayList<>());
//        adjList.get(destination).add(source);
    }

    // Print the Adjacency list

    private void printGraph() {
        for (var entry : adjList.entrySet()) {
            System.out.println(entry.getKey() + ": -> " + entry.getValue());
        }
    }


    /*
        BFS Traversal

        Time complexity : O(V+E)
                           Each node is dequeued once → O(V)
                          	O(E) (each edge is processed once)

        Space complexity : O(V)
                           HashSet used to store V nodes
     */
    public void bfs(int start) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            System.out.print(currentNode + " ");
            List<Integer> edgesList = adjList.getOrDefault(currentNode, new ArrayList<>());
            for (int edge : edgesList) {
                if (!visited.contains(edge)) {
                    visited.add(edge);
                    queue.add(edge);
                }
            }
        }
        System.out.println();
    }


    /*
        DFS Traversal

        Time complexity: O (V+E)
                         Each node is traversed once: O(V)
                         For eah node, the edge is traversed once : O(E)

        Space complexity: O(V)
                         Hashset stores V nodes : O(V)
                         Recursion stack for V calls : O(V)


     */
    public void dfs(int start) {
        Set<Integer> visited = new HashSet<>();
        dfsHelper(start, visited);
    }

    private void dfsHelper(int node, Set<Integer> visited) {
        if (visited.contains(node)) {
            return;
        }

        System.out.print(node + " ");
        visited.add(node);

        for (var edge : adjList.getOrDefault(node, new ArrayList<>())) {
            dfsHelper(edge, visited);
        }
    }

    public static void main(String[] args) {
        BFSDirectedGraphs g = new BFSDirectedGraphs();
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        g.addEdge(3, 4);

        System.out.println("Graph representation:");
        g.printGraph();

        System.out.println("\nBFS traversal from Node 0:");
        g.bfs(0);

        System.out.println("\n DFS traversal from Node 0");
        g.dfs(0);
    }
}
