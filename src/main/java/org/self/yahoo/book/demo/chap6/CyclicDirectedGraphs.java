package org.self.yahoo.book.demo.chap6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CyclicDirectedGraphs {
    private Map<Integer, ArrayList<Integer>> adjList = new HashMap<>();

    public void addEdge(int node, int edge) {
        adjList.putIfAbsent(node, new ArrayList<>());
        adjList.get(node).add(edge);
    }

    public boolean hasCycle() {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> pathVisited = new HashSet<>();

        for (var node : adjList.keySet()) {
            if (dfs(node, visited, pathVisited)) {
                return true;
            }
        }
        return false;
    }

    private boolean dfs(Integer node, Set<Integer> visited, Set<Integer> pathVisited) {
        if (pathVisited.contains(node)) {
            return true;
        }

        if (visited.contains(node)) {
            return false;
        }

        pathVisited.add(node);
        visited.add(node);

        for (var edge : adjList.getOrDefault(node, new ArrayList<>())) {
            if (dfs(edge, visited, pathVisited)) {
                return true;
            }
        }
        pathVisited.remove(node);
        return false;
    }

    public static void main(String[] args) {
        /*
               Cycle detection in Directed Graph
                1 → 2 → 3 → 4 → 5 → 6
                        ↘   ↓
                        7 → 5

               8 → 9 → 10
               ↑       ↓
               └───────┘

               Time Complexity :  O(V + E) : Each node and edge is processed once
               Space Complexity : O(V) : Space required for visited and path visited
         */

        CyclicDirectedGraphs cyclicDirectedGraphs = new CyclicDirectedGraphs();
        cyclicDirectedGraphs.addEdge(1, 2);
        cyclicDirectedGraphs.addEdge(2, 3);
        cyclicDirectedGraphs.addEdge(3, 4);
        cyclicDirectedGraphs.addEdge(3, 7);
        cyclicDirectedGraphs.addEdge(4, 5);
        cyclicDirectedGraphs.addEdge(5, 6);
        cyclicDirectedGraphs.addEdge(7, 5);
        cyclicDirectedGraphs.addEdge(8, 9);
        cyclicDirectedGraphs.addEdge(9, 10);
        cyclicDirectedGraphs.addEdge(10, 8);

        var isCyclic = cyclicDirectedGraphs.hasCycle();
        System.out.println("isCyclic: " + isCyclic);

    }
}
