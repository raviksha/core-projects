package org.self.yahoo.leetcode.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Medium {

    private static int testNumberOfIslands(char[][] grid) {

        if (grid.length == 0) {
            return 0;
        }
        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }

            }
        }
        return count;
    }

    private static void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length ||
                j < 0 || j >= grid[0].length ||
                grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0';

        // Perform DFS
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }

    private static Node testCloneGraph(Node node) {
        if (node == null) {
            return node;
        }
        Map<Node, Node> nodeMap = new HashMap<>();
        return dfsGraph(node, nodeMap);
    }

    private static Node dfsGraph(Node node, Map<Node, Node> nodeMap) {
        if (nodeMap.containsKey(node)) {
            return nodeMap.get(node);
        }

        Node newNode = new Node(node.val);
        nodeMap.put(node, newNode);

        for (Node n : node.neighbors) {
            newNode.neighbors.add(dfsGraph(n, nodeMap));
        }

        return newNode;
    }

    private static void testSurroundedRegions(char[][] board) {
        if (board.length == 0) {
            return;
        }
        boolean [][] visited = new boolean[board.length][board[0].length];
        // Mark the edge O's and its adjacent O's

        // Mark the top and bottom rows

        // First and last rows

        for (int i = 0; i < board[0].length; i++) {

            //
            if (board[0][i] == 'O') {
                dfsSurroundingRegions(board, visited, 0, i);
            }

            if (board[board.length - 1][i] == 'O') {
                dfsSurroundingRegions(board, visited, board.length - 1, i);
            }
        }

        // Left and right columns
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == 'O') {
                dfsSurroundingRegions(board, visited, i, 0);
            }

            if (board[i][board[0].length -1] == 'O') {
                dfsSurroundingRegions(board, visited, i, board[0].length -1);
            }
        }

        // Swap the non-edge O's with 'X'
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O' && !visited[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }

    }

    private static void dfsSurroundingRegions(char[][] board, boolean[][] visited, int i, int j) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == 'X' || visited[i][j]) {
            return;
        }

        visited[i][j] = true;

        dfsSurroundingRegions(board, visited, i - 1, j);
        dfsSurroundingRegions(board, visited, i + 1, j);

        dfsSurroundingRegions(board, visited, i, j + 1);
        dfsSurroundingRegions(board, visited, i, j - 1);
    }



    private static boolean testIsGraphBipartiteUsingBFS(int[][] graph) {
        if (graph.length == 0) {
            return false;
        }
        int [] colors = new int[graph.length];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < graph.length; i++) {
            if (colors[i] != 0) {
                continue;
            }
            colors[i] = 1;
            queue.add(i);

            while (!queue.isEmpty()) {
                int index = queue.poll();

                for (int edge : graph[index]) {
                    if (colors[edge] == 0) {
                        colors[edge] = -colors[index];
                        queue.add(edge);
                    } else if (colors[edge] == colors[index]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static boolean testIsGraphBipartiteUsingDFS(int[][] graph) {
        if (graph.length == 0) {
            return false;
        }
        /*
             0: Not colored
             1: Blue
            -1: Yellow
         */
        int [] colors = new int[graph.length];

        for (int i = 0; i < graph.length; i++) {

            if (colors[i] == 0 && !IsValidColor(i, colors, graph, 1)) {
                return false;
            }

        }
        return true;
    }

    private static boolean IsValidColor(int i, int[] colors, int[][] graph, int color) {

        if (colors[i] == 0) {
            colors[i] = color;
        } else {
            return colors[i] == color;
        }

        int [] edges = graph[i];

        for (int j = 0; j < edges.length; j++) {
            if (!IsValidColor(edges[j], colors, graph, -color)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Medium DFS...");

        // Leet code 200. Number of Islands
        char [] [] grid = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};

        /*
            Approach is using a DFS search

            Time complexity: O(m * n)
                             Traverses through each element of the grid[][]

            Space complexity: O(m * n) Worst case for max depth of the recursion call stack when its filled with all 1's
                              Avg/Best case: O(min(m , n)) : Max depth of a single DFS
         */
        int result = testNumberOfIslands(grid);
        System.out.println("testNumberOfIslands: " + result);


        // Leet code 133. Clone Graph
        Node node = new Node();
        /*
            Approach is using DFS

            Time complexity: O(V + E) : t/c of a undirected graph
                             V: Number of vertices/nodes
                             E: Number of edges or connectors
            Space complexity: O(V)
                              Map string a mapping of old and new vertices
                              Recursion stack depth: O(V) in worst case
                              Total: O(V + V) => O(V)
         */
        testCloneGraph(node);

        // Leet code 130. Surrounded Regions
        char [][] board = new char[][] {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};

        /*
            Approach is using a DFS

            Time complexity: O(m * n)
                             Each element is visited at most once during DFS traversal

            Space complexity: O(m * n)
                              Auxiliary space required for the visited [][] O(m * n)
                              Extra space for the recursion stack, in worst case : O(m * n), when all the elements are 'O'
                              Avg case: O(min (m , n)) => due to natural stopping via borders or zeroes

         */
        testSurroundedRegions(board);
        for (char[] chars : board) {
            System.out.println(Arrays.toString(chars));
        }

        // Leet code 785. Is Graph Bipartite?
        int[][] graph = new int[][] {{1,2,3},{0,2},{0,1,3},{0,2}};

        /*
            Approach is using a BFS approach

            Time complexity: O(V + E)
                             Each vertex is visited once O(V)
                             Each edge of each vertex is visited O(E)
                             Total t/c: O(V + E);

            Space complexity: O(V)
                              Color [] of size V: O(V)
                              BFS queue will hold a max of O(V) elements in worst case
                              Conclusion: O(V)


         */
        boolean isBipartite = testIsGraphBipartiteUsingBFS(graph);
        System.out.println("testIsGraphBipartiteUsingBFS: " + isBipartite);

        /*
            Approach is using a DFS approach

            Time complexity: O(V + E)
                             Each element in the graph[][] matrix undirected graph is visited once

            Space complexity: O(V)
                              colors[] stores a max of V elements
                              Recursion stack in the worst case would be V elements deep
                              Concluding s/c: O(V)

             NOTE:
                1. DFS runs with a risk of stack overflow for deeply skewed graphs
                2. BFS is more suitable for handling large/wide graphs due to controlled memory usage using Queues Vs Stacks
         */
        isBipartite = testIsGraphBipartiteUsingDFS(graph);
        System.out.println("testIsGraphBipartiteUsingDFS: " + isBipartite);

        //  Leet code 417. Pacific Atlantic Water Flow
        //testPacificAtlanticWaterFlow();

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