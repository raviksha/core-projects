package org.self.yahoo.book.demo.chap6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CyclicUnDirectedGraphs {
    Map<Integer, ArrayList<Integer>> adjList = new HashMap<>();

    private void addEdge(int node, int edge) {
        adjList.putIfAbsent(node, new ArrayList<>());
        adjList.get(node).add(edge);
    }

    private boolean isCyclic() {
        Set<Integer> visited = new HashSet<>();
        for (var node : adjList.keySet()) {
            if (!visited.contains(node)) {
                if (dfs(node, visited, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(Integer node, Set<Integer> visited, int parentNode) {
        visited.add(node);

        for (int neighbour : adjList.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbour)) {
                if (dfs(neighbour, visited, parentNode)) {
                    return true;
                }
            } else if (neighbour != parentNode){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Cycle detection in Un directed Graph
        CyclicUnDirectedGraphs graph = new CyclicUnDirectedGraphs();
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0); // This creates a cycle

        var isCyclic = graph.isCyclic();

        System.out.println("isCyclic: " + isCyclic);
    }
}
