package org.self.yahoo.book.demo.chap6.graphs;

public class AdjacencyMatrixWeightedUndirected {
    int adj[][];

    public AdjacencyMatrixWeightedUndirected(int nodes) {
        adj = new int[nodes][nodes];
    }

    public void addEdge(int u, int v, int weight) {
        adj[u][v] = weight;
    }

    public int edgeWeight(int u, int v) {
        return adj[u][v];
    }
}
