package org.self.yahoo.leetcode75.bfs;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Medium {
    public static void main(String[] args) {
        System.out.println("Breadth First Search ......  Medium ....");
        // Leet code 994. Rotting Oranges
        int [] [] grid = {{2,1,1},{1,1,0},{0,1,1}};

        /*
            Approach: Need to use BFS as this needs to be solved one level at a time

            Time complexity: O(m * n)
                             Each element is the grid is visited only once O(m * n)
                             Final t/c: O(m * n)

            Space complexity: O(m * n)
                              Extra compute space to store visited[][]: O(m * n)
                              BFS Queue, in the worst case would need O(m * n), if all fresh apples are connected in the first attempt
                              Final s/c: O(m * n)
         */
        int result = testRottingOranges(grid);
        System.out.println("testRottingOranges: " + result);
    }

    private static int testRottingOranges(int[][] grid) {
        int [][] visited = new int[grid.length][grid[0].length]; // O(V + E)
        Queue<Node> queue = new LinkedList<>();
        int freshCtr = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 2) {
                    visited[i][j] = 2;
                    Node n = new Node(i, j, 0);
                    queue.offer(n);
                }

                if (grid[i][j] == 1) {
                    freshCtr++;
                }
            }
        }
        int time = -1;
        while (!queue.isEmpty()) { // O(V + E)
            int size = queue.size();
            time++;

            for (int i = 0; i < size; i++) {
                Node tmpNode = queue.poll(); //O(1)
                int x = tmpNode.x;
                int y = tmpNode.y;

                // Check up

                if (x - 1 >= 0 && grid[x -1][y] == 1 && visited[x - 1][y] != 2) {
                    visited[x -1][y] = 2;
                    Node n = new Node(x - 1, y, time);
                    queue.offer(n); //O(1)
                    freshCtr--;
                }

                // Check down

                if (x + 1 < grid.length && grid[x + 1][y] == 1 && visited[x + 1][y] != 2) {
                    visited[x + 1][y] = 2;
                    Node n = new Node(x + 1, y, time);
                    queue.offer(n);
                    freshCtr--;
                }

                // Check left
                if (y - 1 >= 0 && grid[x][y - 1] == 1 && visited[x][y - 1] != 2) {
                    visited[x][y - 1] = 2;
                    Node n = new Node(x, y - 1, time);
                    queue.offer(n);
                    freshCtr--;
                }

                // Check right

                if (y + 1 < grid[x].length && grid[x][y + 1] == 1 && visited[x][y + 1] != 2) {
                    visited[x][y + 1] = 2;
                    Node n = new Node(x, y + 1, time);
                    queue.offer(n);
                    freshCtr--;
                }

            }
        }
        return freshCtr == 0 ? time : -1;
    }
}

class Node {
    int x;
    int y;
    int time;

    Node (int x, int y, int time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }
}