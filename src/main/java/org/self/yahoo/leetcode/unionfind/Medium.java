package org.self.yahoo.leetcode.unionfind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Medium {

    private static int testNumberOfProvincesDFSV1(int[][] isConnected) {
        boolean[] visited = new boolean[isConnected.length];
        int provinces = 0;

        for (int i = 0; i < isConnected.length; i++) { // O(V + E)
            if (!visited[i]) {
                dfs(isConnected, visited, i);
                provinces++;
            }
        }

        return provinces;
    }

    private static void dfs(int[][] isConnected, boolean[] visited, int city) { // O(E) : Worst case
        visited[city] = true;

        for (int neighbour = 0; neighbour < isConnected.length; neighbour++) {
            if (isConnected[city][neighbour] == 1 && !visited[neighbour]) {
                dfs(isConnected, visited, neighbour);
            }
        }
    }

    private static int testNumberOfProvincesBFS(int[][] isConnected) {
        boolean[] visited = new boolean[isConnected.length];

        int provinces = 0;
        for (int i = 0; i < isConnected.length; i++) {
            if (!visited[i]) {
                bfs(isConnected, visited, i);
                provinces++;
            }
        }

        return provinces;
    }

    private static void bfs(int[][] isConnected, boolean[] visited, int city) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(city);

        while (!queue.isEmpty()) {
            city = queue.poll();
            for (int neighbour = 0; neighbour < isConnected.length; neighbour++) {
                if (isConnected[city][neighbour] == 1 && !visited[neighbour]) {
                    queue.offer(neighbour);
                    visited[neighbour] = true;
                }
            }
        }

    }


    private static int testNumberOfProvincesDFSAdjList(int[][] isConnected) {
        boolean[] visited = new boolean[isConnected.length]; // O(V)

        List<List<Integer>> adjList = new ArrayList<>(); // O(V)
        int province = 0;
        int n = isConnected.length;

        // Correct way to initialize the adjacency list
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        // Build adjacency list from matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isConnected[i][j] == 1 && i != j) {
                    adjList.get(i).add(j);
                }
            }
        }


        for (int i = 0; i < isConnected.length; i++) { // O(V * E)
            if (!visited[i]) {
                dfsAdjList(adjList, i, visited);
                province++;
            }
        }

        return province;
    }

    private static void dfsAdjList(List<List<Integer>> adjList, int city, boolean[] visited) {
        visited[city] = true;

        for (int neighbor : adjList.get(city)) {
            if (neighbor != 0 && !visited[neighbor]) {
                dfsAdjList(adjList, neighbor, visited); // O(V): Recursion depth
            }
        }
    }

    private static int[] testRedundantConnectionDFS(int[][] edges) {
        List<List<Integer>> adjList = new ArrayList<>();

        int n = edges.length;

        for (int i = 0; i <= n; i++) { // O(n)
            adjList.add(i, new ArrayList<>());
        }

        for (int[] edge : edges) { // O(n)
            int u = edge[0];
            int v = edge[1];

            boolean[] visited = new boolean[n + 1]; // Accounts for one extra edge

            if (hasPath(adjList, visited, u, v)) { // O(n)
                return edge;
            }
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        return new int[]{};
    }

    private static boolean hasPath(List<List<Integer>> adjList, boolean[] visited, int source, int target) {
        if (source == target) { // Cycle found
            return true;
        }

        visited[source] = true;

        for (int neighbor : adjList.get(source)) {
            if (!visited[neighbor]) {
                if (hasPath(adjList, visited, neighbor, target)) {
                    return true;
                }
            }
        }

        return false;
    }


    public static void main(String[] args) {
        System.out.println("Union find.. Medium");

        // Leet code 547. Number of Provinces
        // [[1,1,0],[1,1,0],[0,0,1]]
        int[][] isConnected = new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        /*
            Approach is traversing the graph matrix using DFS

            Time complexity: O(V ^ 2)
                             Outer loop runs for all nodes/cities: O(V)
                             For each city the entire row is scanned to check connections O(V) per DFS call
                             Total t/c: O(V ^ V): O(V ^ 2)


            Space complexity: O(V)
                              visited []: O(V)
                              Recursion depth: O(V): Worst case when all cities and interconnected as a single province
                              Concluding s/c: O(V)
         */
        int result = testNumberOfProvincesDFSV1(isConnected);
        System.out.println("testNumberOfProvincesDFSV1: " + result);

        /*
            Approach: Using a BFS for the graph matrix traversal

            Time complexity: O(V ^ V)
                            Main for loop runs n times for all cities: O(V)
                            Inside the while loop for each city an inner loop runs n times over all neighbors: O(V)
                            Concluding time complexity: O(V * V)

            Space complexity: O(V)
                            Visited array requires: O(V) space
                            Queue is worst case scenario will contain: O(V) elements if the start city is connected to all cities
                            Final space complexity: O(V)


         */

        result = testNumberOfProvincesBFS(isConnected);
        System.out.println("testNumberOfProvincesBFS: " + result);


        /*
            Approach is converting the matrix into an adjacency list and traversing it using DFS

            Time complexity: O(V * E)
                            Adjacency list creation: O(V * E)
                            DFS traversal: O(V + E)
                            Concluding: O(V * E)

            Space complexity: O(V + E)
                            visited []: O(V)
                            Adjacency list: O(V + E)
                            Recursion stack: O(V): Worst case


         */

        result = testNumberOfProvincesDFSAdjList(isConnected);
        System.out.println("testNumberOfProvincesDFSAdjList: " + result);

        // Leet code 684. Redundant Connection
        // [[1,2],[1,3],[2,3]]
        int[][] edges = new int[][]{{1, 2}, {1, 3}, {2, 3}};

        /*
            Approach is traversing the tree using DFS to check if it has cycles

            Time complexity: O(n ^ 2)
                             Outer loop runs: O(n) times
                             For each edge hasPath() is called and in worst cast it will traverse nearly the whole graph
                             Total t/c: O(n ^ 2)

            Space complexity: O(n)
                              Adjacency list stores: O(n) elements
                              visited[] for each edge iteration will store O(n) elements
                              Recursion stack at most would be: O(n)
                              Total s/c: O(n)
         */
        int[] edge = testRedundantConnectionDFS(edges);
        System.out.println("testRedundantConnectionDFS: " + Arrays.toString(edge));


        /*
            Approach is traversing the tree looking for cycles using BFS traversal


            Time complexity: O(n ^ 2)
                             Traversing the edges [][]: O(n)
                             For each edge hasPath() is called and in worst case goes over all nodes: O(n * n)
                             Total t/c: O(n ^ 2)

            Space complexity: O(n)
                              adjList stores O(n) elements
                              visited [] stores a max of O(n) elements
                              Queue: Needs extra memory to store max O(n) elements
                              Total s/c: O(n)

         */
        edge = testRedundantConnectionBFS(edges);
        System.out.println("testRedundantConnectionBFS: " + Arrays.toString(edge));

    }

    private static int[] testRedundantConnectionBFS(int[][] edges) {

        List<List<Integer>> adjList = new ArrayList<>();
        int n = edges.length;

        for (int i = 0; i <= edges.length; i++) { // O(n)
            // Accounts for the extra edge i <= edges.length
            adjList.add(i, new ArrayList<>());
        }

        for (int[] edge : edges) { // O(n)
            int u = edge[0];
            int v = edge[1];

            boolean[] visited = new boolean[n + 1]; // O(n)
            // Accounts for the extra edge: n + 1
            if (hasPathBFS(adjList, visited, u, v)) {
                return edge;
            }

            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        return new int[]{};
    }

    private static boolean hasPathBFS(List<List<Integer>> adjList, boolean[] visited, int source, int target) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int neighbor : adjList.get(node)) {
                if (neighbor == target) {
                    return true;
                }
                if (!visited[neighbor]) {
                    queue.offer(neighbor);
                    visited[neighbor] = true;
                }
            }
        }
        return false;

    }
}
