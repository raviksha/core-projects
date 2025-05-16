package org.self.yahoo.book.demo.chap6.graphs;

import java.util.Arrays;

public class AdjacencyMatrixGraph {
    int [][] adj;

    public AdjacencyMatrixGraph(int nodes) {
        this.adj = new int [nodes][nodes];
    }

    public void addEdge(int u, int v) {
        adj[u][v] = 1;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < adj.length; i++) {
            stringBuilder.append(i + ": ");

            for (int j = 0; j < adj[i].length; j++) {
                stringBuilder.append(" " + adj[i][j]);
            }

            if (i < adj.length) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        AdjacencyMatrixGraph g = new AdjacencyMatrixGraph(6);
        g.addEdge(0, 1);
        g.addEdge(0, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 4);
        g.addEdge(2, 5);
        g.addEdge(3, 1);
        g.addEdge(4, 3);
        g.addEdge(5, 5);
        System.out.println(g);
    }
}
