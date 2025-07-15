package org.self.yahoo.leetcode.bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Medium {

    private static int testRottingOrangesDFS(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return -1;
        }

        int[][] time = new int[grid.length][grid[0].length]; // s/c O(V * E)

        for (int i = 0; i < time.length; i++) { // O(V * E)
            for (int j = 0; j < time[0].length; j++) {
                if (grid[i][j] == 1 || grid[i][j] == 2) {
                    time[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int i = 0; i < grid.length; i++) { // O(V * E)
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    dfs(grid, time, i, j, 1);
                }
            }

        }

        int days = 0;

        for (int i = 0; i < time.length; i++) { // O(V * E)
            for (int j = 0; j < time[0].length; j++) {
                if (time[i][j] == Integer.MAX_VALUE) {
                    return -1;
                }
                days = Math.max(days, time[i][j]);
            }
        }
        return days;
    }

    private static void dfs(int[][] grid, int[][] time, int i, int j, int count) { // s/c O(max (V, E)) deep
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0 || time[i][j] <= count) {
            return;
        }

        time[i][j] = count;

        dfs(grid, time, i + 1, j, count + 1);
        dfs(grid, time, i - 1, j, count + 1);
        dfs(grid, time, i, j + 1, count + 1);
        dfs(grid, time, i, j - 1, count + 1);

    }

    public static void main(String[] args) {
        System.out.println("BFS medium ...");

        // Leet code 994. Rotting Oranges
        int[][] grid = new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};

                /*
                        Approach is using a DFS where it begins plotting the reachable neighboring nodes as rotten

                        Time complexity: O(V * E) Each orange cell is visited only once

                        Space complexity: O(V * E)
                                          Extra compute space for time[][] to store V * E elements
                                          Recursion call stack can, in the worst case can grow unto V * E  when all oranges are connected to 1 chain
                 */
        int noOfDays = testRottingOrangesDFS(grid);
        System.out.println("testRottingOrangesDFS: " + noOfDays);

        /*
                Approach is using BFS where each orange next to an rotten orange is put in a queue

                Time complexity: O(m * n) Each element of the grid[][] is visited once

                Space complexity: O(m * n):
                                  Extra compute space to all the m * n elements in the visited [][] : O(m * n)
                                  In the worst case, the Queue will hold up to all fresh oranges O(m * n)
                                  Concluding: O(m * n)
         */

        noOfDays = testRottingOrangesBFS(grid);
        System.out.println("testRottingOrangesBFS: " + noOfDays);
    }

    private static int testRottingOrangesBFS(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return -1;
        }

        int[][] visited = new int[grid.length][grid[0].length];
        int days = 0;
        Queue<Node> queue = new LinkedList<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    Node node = new Node(i, j, days);
                    queue.offer(node);
                    visited[i][j] = 2;
                }
            }
        }

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int row = node.row;
            int column = node.column;
            int time = node.time;

            int prevRow = row - 1;
            int nextRow = row + 1;
            int prevColumn = column - 1;
            int nextColumn = column + 1;

            days = Math.max(days, time);
            time = time + 1;

            // Prev Row

            if (prevRow >= 0 && grid[prevRow][column] == 1) {
                if (visited[prevRow][column] != 2) {
                    visited[prevRow][column] = 2;
                    node = new Node(prevRow, column, time);
                    queue.offer(node);
                }
            }

            // Next Row
            if (nextRow >= 0 && nextRow < grid.length && grid[nextRow][column] == 1) {
                if (visited[nextRow][column] != 2) {
                    visited[nextRow][column] = 2;
                    node = new Node(nextRow, column, time);
                    queue.offer(node);
                }
            }

            // Prev Column

            if (prevColumn >= 0 && grid[row][prevColumn] == 1) {
                if (visited[row][prevColumn] != 2) {
                    visited[row][prevColumn] = 2;
                    node = new Node(row, prevColumn, time);
                    queue.offer(node);
                }
            }

            // Next column
            if (nextColumn >= 0 && nextColumn < grid[0].length && grid[row][nextColumn] == 1) {
                if (visited[row][nextColumn] != 2) {
                    visited[row][nextColumn] = 2;
                    node = new Node(row, nextColumn, time);
                    queue.offer(node);
                }
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && visited[i][j] != 2) {
                    return -1;
                }
            }
        }

        return days;
    }

}

class Node {
    int row;
    int column;
    int time;

    Node(int row, int column, int time) {
        this.row = row;
        this.column = column;
        this.time = time;
    }
}