package org.self.yahoo.leetcode75.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Medium {

    private static int testNumberOfIslands(char[][] grid) {
        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    private static void dfs(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        dfs(grid, i + 1, j); // down
        dfs(grid, i - 1, j); // up
        dfs(grid, i, j + 1); // right
        dfs(grid, i, j - 1); // left
    }

    private static Node testCloneGraph(Node node) {
        if (node == null) {
            return node;
        }
        Map<Node, Node> nodeMap = new HashMap<>();
        return cloneGraphDfs(node, nodeMap);
    }

    private static Node cloneGraphDfs(Node node, Map<Node, Node> nodeMap) {
        if (nodeMap.containsKey(node)) {
            return nodeMap.get(node);
        }

        Node newNode = new Node(node.val);
        nodeMap.put(node, newNode);

        for (Node adjNode : node.neighbors) {
            newNode.neighbors.add(cloneGraphDfs(adjNode, nodeMap));
        }
        return newNode;
    }

    public static void main(String[] args) {
        System.out.println("Depth First Search .... Medium...");
        // Leet code 200. Number of Islands
        char [] [] grid = new char[][] {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};

        /*
            Approach: Using DFS

            Time complexity: O(m * n): Each cell in the grid[][] is visited only once, including the outer for loop and the nested dfs()

            Space complexity: O(m * n): Worst case when all the cells of the grid[][] are filled with `1`
         */
        int result = testNumberOfIslands(grid);
        System.out.println("testNumberOfIslands: " + result);

        // Leet code 133. Clone Graph
        // [[2,4],[1,3],[2,4],[1,3]]
        Node node = new Node();

        /*
            Approach: Using DFS to traverse an uni directed graph
            V: Number of nodes
            E: Number of edges connecting the nodes

            Time complexity: O(V + E)
                             Each node and edge is visited only once coz of the color []
                             Without the color [] the t/c wud become: O(V * E)

            Space complexity: O(V + E)
                            Map stores one entry per node: O(V)
                            Recursion stack depth: O(V) => Worst case when all nodes are part of the single deep recursion path
                            Cloned nodes and adjacency list: O(V + E)
                            Final s/c: O(V + E)


         */
        Node resp = testCloneGraph(node);
        System.out.println("testCloneGraph: " + resp.val);

        // Leet code 785. Is Graph Bipartite?
        int [] [] graph = {{1,2,3},{0,2},{0,1,3},{0,2}};

        /*
            Time complexity: O(V + E) Each node and edge of the undirected graph is visited only once

            Space complexity: O(V):
                                Recursion stack depth: O(V)
                                color array:         : O(V)
         */
        boolean isBipartite = testIsGraphBipartite(graph);
        System.out.println("isBipartite: " + isBipartite);

    }

    private static boolean testIsGraphBipartite(int[][] graph) {
        int [] colors = new int[graph.length];
        Arrays.fill(colors, -1); // O(V)
        for (int i = 0; i < graph.length; i++) { // O(V)
            if (colors[i] == -1) {
                if (!dfsBipartite(i, graph, colors, 0)) { // O(V + E)
                    return false;
                }
            }

        }
        return true;
    }

    private static boolean dfsBipartite(int node, int[][] graph, int[] colors, int flag) {

        if (colors[node] != -1) {
            return colors[node] == flag;
        }
        colors[node] = flag;

        for (int edge : graph[node]) {
            if (!dfsBipartite(edge, graph, colors, 1 - flag)) { // O(E)
                return false;
            }
        }
        return true;
    }
}



class Node {
    int val;
    List<Node> neighbors;
    Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}



//class Solution {
//    public boolean isBipartite(int[][] graph) {
//
//        if (graph.length == 0) {
//            return false;
//        }
//        /*
//             0: Not colored
//             1: Blue
//            -1: Yellow
//         */
//        int [] colors = new int[graph.length];
//
//        for (int i = 0; i < graph.length; i++) {
//
//            if (colors[i] == 0 && !IsValidColor(i, colors, graph, 1)) {
//                return false;
//            }
//
//        }
//        return true;
//
//    }
//
//    private boolean IsValidColor(int i, int[] colors, int[][] graph, int color) {
//
//        if (colors[i] == 0) {
//            colors[i] = color;
//        } else {
//            return colors[i] == color;
//        }
//
//        int [] edges = graph[i];
//
//        for (int j = 0; j < edges.length; j++) {
//            if (!IsValidColor(edges[j], colors, graph, -color)) {
//                return false;
//            }
//        }
//        return true;
//    }
//}
