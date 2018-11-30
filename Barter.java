import java.io.*;
import java.util.*;

// class Graph {
//   Vertex[] nodes;
//   double[][] adj;
//
//   Graph(int n) {
//     adj = new double[n][n];
//     nodes = new Vertex[n];
//
//     for(int i = 0; i < nodes.length; ++i) {
//       nodes[i] = new Vertex(i + 1);
//     }
//   }
//
//   static void initSingleSource(Graph g, Vertex s) {
//     for(int i = 0; i < g.nodes.length; ++i) {
//       g.nodes[i].d = 100000;
//       g.nodes[i].pred = null;
//     }
//     g.nodes[s.id - 1].d = 0;
//   }
//
//   static boolean bellmanFord(Graph g, Vertex s) {
//     initSingleSource(g, s);
//
//     for(int i = 0; i < g.nodes.length - 1; ++i) {
//       Vertex v = g.nodes[i];
//
//       for(int j = 0; j < g.nodes.length; ++j) {
//         if(g.adj[i][j] != 0)
//           g.relax(g.nodes[i], g.nodes[j]);
//       }
//     }
//
//     for(int i = 0; i < g.nodes.length; ++i) {
//       for(int j = 0; j < g.nodes.length; ++j) {
//         if(g.adj[i][j] != 0) {
//           Vertex u = g.nodes[i];
//           Vertex v = g.nodes[j];
//
//           if(v.d > u.d + g.w(u,v))
//             return false;
//         }
//       }
//     }
//     return true;
//   }
//
//   void relax(Vertex u, Vertex v) {
//     if(v.d > u.d + w(u,v)) {
//       v.d = u.d + w(u,v);
//       v.pred = u;
//     }
//   }
//
//   double w(Vertex u, Vertex v) {
//     return adj[u.id - 1][v.id - 1];
//   }
//
//   class Vertex {
//     int id;
//     double d;
//     Vertex pred;
//
//     Vertex(int id) {
//       this.id = id;
//     }
//
//
//   }
// }
//
// class Graph {
//     LinkedList<Integer> list;
//     int nVertex;
//     double[][] adj;
//
//     Graph(int n) {
//       nVertex = n;
//       adj = new double[n][n];
//     }
//
//     boolean func(int src, int a, int b, double product) {
//       // We found a cycle that includes our source
//       if(a != src) {
//         if((b) == src) {
//           // We found an inefficient system
//           if(product > 1)
//             return true;
//           else return false;
//         }
//       }
//
//       // We increment b to test if we have cycled back to our source
//       // We only want it this way specifically for the check
//       // Afterwards we reset it for iteration
//       // --b;
//
//       // We need some way to ship the src id back and forth so we don't
//       // accidentally run into our base case
//       // src--?
//       for(int i = b; i < nVertex; ++i) {
//         for(int j = a; j < nVertex; ++j) {
//
//           // If we find an adjacent vertex
//           if(adj[i][j] != 0) {
//             //list.add(j);
//
//             // If we find an inefficient system
//             if(func(src, i, j, product * adj[i][j])) {
//               System.out.println("WE GOT ONE");
//               /// TRACK our way back
//               return true;
//             } else {
//               //list.removeLast();
//             }
//           }
//
//         }
//       }
//
//       return false;
//     }
// }

class Graph {
  enum Color { WHITE, GRAY, BLACK }
  int nVertices;
  double[][] adj;
  Vertex source;
  LinkedList<Vertex> vertices;

  Graph(int n) {
    nVertices = n;
    adj = new double[n][n];

    vertices = new LinkedList<Vertex>();
    for(int i = 0; i < n; ++i) {
      vertices.add(new Vertex(i+1));
    }
  }

  static void dfs(Graph g) {
    Iterator<Vertex> it = g.vertices.iterator();
    while(it.hasNext()) {
      Vertex u = it.next();
      u.color = Color.WHITE;
      u.pred = 0;
    }

    it = g.vertices.iterator();
    while(it.hasNext()) {

      // u is our new source
      Vertex u = it.next();
      g.source = u;

      if(u.color == Color.WHITE) {

          dfsVisit(g, u);
      }
        // Check if we have found a cycle
        // if(dfsVisit(g, u)) {
        //   if(u.pred != null) System.out.println("null");
        //   while(u.pred != null) {
        //     u = u.pred;
        //     System.out.println(u.id);
        //   }
        // }
    }
  }

  static boolean dfsVisit(Graph g, Vertex u) {

    u.color = Color.GRAY;
    for(int i = 0; i < g.nVertices; ++i) {
      if(g.adj[u.id - 1][i] != 0) {
        Vertex v = g.vertices.get(i);
        System.out.println(v.id);

        // Check if we found a cycle
        if(v == g.source) {
          if(checkEfficiency(g, v)) {
            return true;
          } else {

          }
        }

        if(v.color == Color.WHITE) {
          v.pred = u.id;
          dfsVisit(g, v);
        }
      }
    }
    u.color = Color.BLACK;
    return false;
  }

  static boolean checkEfficiency(Graph g, Vertex u) {
    Vertex t = u;
    t = g.vertices.get(t.pred);
    System.out.println(t.pred + " " + g.source.id);
    while(t.id != g.source.id) {
      System.out.print(t.id + ", ");
      t = g.vertices.get(t.pred);
    }
    System.out.println();
    return true;
  }

  class Vertex {
    int id, pred;
    Color color;

    Vertex(int i) {
      id = i;
    }

  }
}

class Barter {

  Barter() {

  }

  void printAdj(Graph g) {
    System.out.print("   ");
    for(int i = 0; i < g.adj.length; ++i) {
      System.out.print((i + 1) + "    ");
    }
    System.out.println();

    for(int i = 0; i < g.adj.length; ++i) {
      System.out.print((i + 1) + ": ");
      for(int j = 0; j < g.adj.length; ++j) {
        System.out.print(String.format("%.1f", g.adj[i][j]) + ", ");
      }
      System.out.println();
    }
  }


  public static void main(String[] args) {
    Barter b = new Barter();
    Graph g = null;

    /// READ the input file
    try {
      File file = new File("barterinput.txt");

      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String line;

      // Get the first line
      line = bufferedReader.readLine();
      StringTokenizer st = new StringTokenizer(line);

      // Set the number of products
      g = new Graph(Integer.parseInt(st.nextToken()));

      while ((line = bufferedReader.readLine()) != null) {
        st = new StringTokenizer(line);

        int i = Integer.parseInt(st.nextToken()) - 1;
        int j = Integer.parseInt(st.nextToken()) - 1;
        double x = Double.parseDouble(st.nextToken());
        double y = Double.parseDouble(st.nextToken());

        g.adj[i][j] = y / x;
        // b.adj[j][i] = x / y;
      }

    } catch(IOException e) {
      e.printStackTrace();
    }

    // Do the code part here
    b.printAdj(g);

    Graph.dfs(g);

    //g.func(0, 0, 0, 1);
    // for(int i = 0; i < 4; ++i) {
    //   g.list = new LinkedList<Integer>();
    //   g.list.add(i);
    //   if(g.func(i, 0, i, 1)) {
    //     g.list.removeLast();
    //     System.out.println("---------------");
    //     while(g.list.size() != 0) {
    //       System.out.print((g.list.poll() + 1) + " ");
    //     }
    //     System.out.println();
    //   }
    // }


    /// WRITE to the output file
    try {
      BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));

      //bw.write(s);
      // bw.newLine();


      //bw.write(s1);
      bw.close();
    } catch(IOException e) {
      e.printStackTrace();
    }
    System.out.println("done");
  }
}
