import java.io.*;
import java.util.*;


class SingleSource {
  EdgeCompare comp;
  VertexCompare vcomp;

  SingleSource() {
    comp = new EdgeCompare();
    vcomp = new VertexCompare();
  }

  void initSingleSource(Graph g, Vertex s) {
    Iterator<Vertex> it = g.vertices.iterator();
    while(it.hasNext()) {
      Vertex v = it.next();
      v.d = Integer.MAX_VALUE;
      v.p = null;
    }
    s.d = 0;
  }

  void relax(Vertex u, Vertex v, Graph g) {
    if(v.d > u.d + g.weight(u, v)) {
      v.d = u.d + g.weight(u, v);
      v.p = u;
    }
  }

  boolean bellmanFord(Graph g, Vertex s) {
    initSingleSource(g, s);

    // for(int i = 0; i < g.nVertices; ++i) {
    //   System.out.print((i+1) + "    ");
    // } System.out.println();
    //
    // for(int i = 0; i < g.nVertices; ++i) {
    //   System.out.print(String.format("%.1f", g.vertices.get(i).d) + " ");
    // } System.out.println();

    for(int i = 1; i <= g.nVertices - 1; ++i) {
      for(int j = 0; j < g.nEdges; ++j) {
        Edge e = g.edges.get(j);

        Vertex u = g.vertices.get(e.u - 1);
        Vertex v = g.vertices.get(e.v - 1);
        relax(u, v, g);
      }
    }

    Iterator<Edge> it = g.edges.iterator();
    while(it.hasNext()) {
      Edge e = it.next();

      Vertex u = g.vertices.get(e.u - 1);
      Vertex v = g.vertices.get(e.v - 1);

      System.out.println(String.format("%.1f",v.d) + " > "
        + String.format("%.1f",u.d) + " + " + String.format("%.1f",g.weight(u,v)));
      if(v.d > u.d + g.weight(u,v)) {
        return false;
      }
    }
    return true;
  }

  void dijkstra(Graph g, Vertex s) {
    initSingleSource(g, s);
    LinkedList<Vertex> set = new LinkedList<Vertex>();
    LinkedList<Vertex> q = new LinkedList<Vertex>(g.vertices);
    Collections.sort(q, vcomp);

    while(q.size() != 0) {
      Vertex u = q.poll();

      // Add u to s
      if(!set.contains(u))
        set.add(u);



      for(int i = 0; i < g.nVertices; ++i) {
        if(g.adj[u.id - 1][i] != 0) {
          Vertex v = g.vertices.get(i);
          relax(u, v, g);
        }
      }

    }

    for(int i = 0; i < g.nVertices; ++i) {
      Vertex v = g.vertices.get(i);
      System.out.println(i + " " + String.format("%.1f",v.d));
    }
    System.out.println();

  }

  void printAllPairsSP(int[][] pi, int i, int j) {
    if(i == j) {
      System.out.println(i);
    } else if(pi[i][j] == 0) {
      System.out.println("No path from: " + i + " to " + j + " exists");
    } else {
      printAllPairsSP(pi, i, pi[i][j]);
      System.out.println(j);
    }
  }

  int[][] extendSP(int[][] bigL, int[][] bigW) {
    int n = bigL.length;
    int[][] lPrime = new int[n][n];

    for(int i = 0; i < n; ++i) {
      for(int j = 0; j < n; ++j) {
        lPrime[i][j] = Integer.MAX_VALUE;
        for(int k = 0; k < n; ++k) {
          lPrime[i][j] = Math.min(Math.abs(lPrime[i][j]), Math.abs(lPrime[i][k] + bigW[k][j]));
        }
      }
    }
    printMatrix(lPrime);
    return lPrime;
  }

  void printMatrix(int[][] m) {
    System.out.print("   ");
    for(int i = 0; i < m.length; ++i) {
      // System.out.print((i + 1) + "    ");
      System.out.print((i + 1) + "  ");
    }
    System.out.println();

    for(int i = 0; i < m.length; ++i) {
      System.out.print((i + 1) + ": ");
      for(int j = 0; j < m.length; ++j) {
        if(m[i][j] == Integer.MAX_VALUE) {
          //System.out.print("  ∞, ");
          System.out.print("∞, ");
        } else {
          //System.out.print(String.format("%.1f", m[i][j]) + ", ");
          System.out.print(m[i][j] + ", ");
        }
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    SingleSource ss = new SingleSource();

    Graph g = null;

    double[][][] data = null; // Matrix to store data for output

    /// READ the input file
    try {
      File file = new File("iinput.txt");

      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String line;

      // Get the first line
      line = bufferedReader.readLine();
      StringTokenizer st = new StringTokenizer(line);

      // Set the number of products
      int numProducts = Integer.parseInt(st.nextToken());
      g = new Graph(numProducts);
      data = new double[numProducts][numProducts][2];

      while ((line = bufferedReader.readLine()) != null) {
        st = new StringTokenizer(line);

        int i = Integer.parseInt(st.nextToken()) - 1;
        int j = Integer.parseInt(st.nextToken()) - 1;
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        g.adj[i][j] = y / x;
        if(g.adj[i][j] != 0) {
          ++g.nEdges;
          g.edges.add(new Edge(i+1, j+1, y / x));
        }

        data[i][j][0] = x;
        data[i][j][1] = y;
        // b.adj[j][i] = x / y;
      }

      // w_i,j =
      // 0 if i = j
      // w if i != j and i,j in E
      // inf otherwise
      for(int i = 0; i < g.nVertices; ++i) {
        for(int j = 0; j < g.nVertices; ++j) {
          if(i == j) {
            g.adj[i][j] = 0;
          } else if(g.adj[i][j] != 0) {
            // do nothing
          } else {
            g.adj[i][j] = Integer.MAX_VALUE;
          }
        }
      }

    } catch(IOException e) {
      e.printStackTrace();
    }

    Graph.printAdj(g);
    int[][] l = new int[g.nVertices][g.nVertices];
    for(int i = 0; i < g.nVertices; ++i) {
      for(int j = 0; j < g.nVertices; ++j) {
        if(i == j) {
          l[i][j] = 0;
        } else {
          l[i][j] = Integer.MAX_VALUE;
        }
      }
    }
    int[][] a = ss.extendSP(l, g.adj);
    // for(int i = 0; i < g.nVertices; ++i) {
    //   Vertex s = g.vertices.get(i);
    //   ss.dijkstra(g, s);
    // }
    //   if(ss.bellmanFord(g, s))
    //     System.out.println("For source: " + (i+1) + " no negative cycles");
    // }
  }
}
