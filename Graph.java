import java.io.*;
import java.util.*;

class Graph {
  int nVertices, nEdges;
  int[][] adj;
  LinkedList<Vertex> vertices;
  LinkedList<Edge> edges;

  Graph(int n) {
    nEdges = 0;
    nVertices = n;
    adj = new int[n][n];
    edges = new LinkedList<Edge>();

    vertices = new LinkedList<Vertex>();
    for(int i = 0; i < n; ++i) {
      vertices.add(new Vertex(i+1));
    }
  }

  //
  void clearVertices() {
    vertices = new LinkedList<Vertex>();
  }

  int weight(Vertex u, Vertex v) {
    return adj[u.id - 1][v.id - 1];
  }

  void addEdge(Edge e) {
    edges.add(e);
    adj[e.u - 1][e.v - 1] = e.w;
    ++nEdges;
  }

  static void printAdj(Graph g) {
    System.out.print("   ");
    for(int i = 0; i < g.adj.length; ++i) {
      // System.out.print((i + 1) + "    ");
      System.out.print((i + 1) + "  ");
    }
    System.out.println();

    for(int i = 0; i < g.adj.length; ++i) {
      System.out.print((i + 1) + ": ");
      for(int j = 0; j < g.adj.length; ++j) {
        if(g.adj[i][j] == Integer.MAX_VALUE) {
          //System.out.print("  ∞, ");
          System.out.print("∞, ");
        } else {
          //System.out.print(String.format("%.1f", g.adj[i][j]) + ", ");
          System.out.print(g.adj[i][j] + ", ");
        }
      }
      System.out.println();
    }
  }

  static void printEdges(Graph g) {
    Iterator<Edge> it = g.edges.iterator();
    while(it.hasNext()) {
      Edge e = it.next();
      System.out.println(e.u + " ---" + String.format("%.1f", e.w) + "--> " + e.v);
    }
  }
}
