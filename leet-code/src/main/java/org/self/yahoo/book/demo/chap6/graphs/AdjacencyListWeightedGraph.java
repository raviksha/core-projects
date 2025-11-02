package org.self.yahoo.book.demo.chap6.graphs;

import java.util.ArrayList;

class Edge {
    int node;
    int edge;
    int weight;

    Edge(int u, int v, int weight) {
        this.node = u;
        this.edge = v;
        this.weight = weight;
    }
}

public class AdjacencyListWeightedGraph {
    ArrayList<Edge> [] adj;

    public AdjacencyListWeightedGraph(int nodes) {
        adj = new ArrayList[nodes];
        for (int i = 0; i < nodes; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(int node, int edge, int weight) {
        adj[node].add(new Edge(node, edge, weight));
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("");    
        for (int i = 0; i < adj.length; i++) {
            res.append(i + ":");
            var arrayList = adj[i];
            for (int j = 0; j < arrayList.size(); j++) {
                Edge edge = arrayList.get(j);
                res.append(" " + edge.edge + " (" + edge.weight + ")");
            }

            if (i + 1 < adj.length) {
                res.append("\n");
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        AdjacencyListWeightedGraph g = new AdjacencyListWeightedGraph(6);
        g.addEdge(0, 1, 1);
        g.addEdge(0, 3, 4);
        g.addEdge(1, 4, 3);
        g.addEdge(2, 4, 10);
        g.addEdge(2, 5, 4);
        g.addEdge(3, 1, 5);
        g.addEdge(4, 3, 1);
        g.addEdge(5, 5, 2);
        System.out.println(g);
    }
}
