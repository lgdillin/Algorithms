import java.io.*;
import java.util.*;


// class Graph {
//   enum Color { WHITE, GRAY, BLACK }
//   int nVertices;
//   double iProduct; // Stores the product for an inefficient system
//   double[][] adj;
//   Vertex source;
//   LinkedList<Vertex> vertices;
//   Stack<Vertex> iPath; // Stores the inefficient path (if it exists)
//
//   Graph(int n) {
//     nVertices = n;
//     adj = new double[n][n];
//
//     vertices = new LinkedList<Vertex>();
//     for(int i = 0; i < n; ++i) {
//       vertices.add(new Vertex(i+1));
//     }
//   }
//
//   double getEdgeWeight(Vertex u, Vertex v) {
//     return adj[u.id - 1][v.id - 1];
//   }
//
//   void setWhite() {
//     Iterator<Vertex> it = vertices.iterator();
//     while(it.hasNext()) {
//       Vertex u = it.next();
//       u.color = Color.WHITE;
//       u.pred = 0;
//     }
//   }
//
//   static void dfs(Graph g) {
//     Iterator<Vertex> it = g.vertices.iterator();
//     while(it.hasNext()) {
//       Vertex u = it.next();
//       u.color = Color.WHITE;
//       u.pred = 0;
//     }
//
//     it = g.vertices.iterator();
//     while(it.hasNext()) {
//
//       // u is our new source
//       Vertex u = it.next();
//       g.source = u;
//
//       if(u.color == Color.WHITE) {
//           if(dfsVisit(g, u))  {
//             return;
//           }
//       }
//
//       //System.out.println(g.source.id);
//
//       //g.setWhite();
//
//     }
//   }
//
//   static boolean dfsVisit(Graph g, Vertex u) {
//     boolean flag = false; // flags if we found an inefficient system
//
//     u.color = Color.GRAY;
//     for(int i = 0; i < g.nVertices; ++i) {
//       if(g.adj[u.id - 1][i] != 0) {
//         Vertex v = g.vertices.get(i);
//
//         // Check if we found a cycle
//         if(v == g.source) {
//           v.pred = u.id;
//           if(inefficient(g, v)) return true;
//           //else g.setWhite();
//         }
//
//         if(v.color == Color.WHITE) {
//           v.pred = u.id;
//           flag = dfsVisit(g, v);
//           if(flag) return true;
//         }
//       }
//     }
//     u.color = Color.WHITE;
//     return flag;
//   }
//
//   static boolean inefficient(Graph g, Vertex u) {
//     Vertex t = u;
//     double product = 1;
//
//     product *= g.getEdgeWeight(g.vertices.get(t.pred-1), t);
//     t = g.vertices.get(t.pred - 1);
//     while(t.id != g.source.id) {
//       product *= g.getEdgeWeight(g.vertices.get(t.pred-1), t);
//       t = g.vertices.get(t.pred - 1);
//     }
//
//     // If we find an inefficient system we need to save the path
//     if(product > 1) {
//       g.iPath = new Stack<Vertex>();
//
//       // first push the source in
//       g.iPath.push(g.source);
//
//       Vertex v = g.vertices.get(u.pred - 1);
//       g.iPath.push(v);
//
//       while(v.id != g.source.id) {
//         g.iPath.push(v);
//         v = g.vertices.get(v.pred - 1);
//         g.iPath.push(v);
//       }
//       g.iProduct = product;
//
//       return true;
//     }
//     return false;
//   }
//
//   class Vertex {
//     int id, pred;
//     Color color;
//
//     Vertex(int i) {
//       id = i;
//     }
//
//   }
// }

<<<<<<< HEAD
=======
class Graph {
  enum Color { WHITE, GRAY, BLACK }
  int nVertices;
  double iProduct; // Stores the product for an inefficient system
  double[][] adj;
  Vertex source;
  LinkedList<Vertex> vertices;
  Stack<Vertex> iPath; // Stores the inefficient path (if it exists)

  Graph(int n) {
    nVertices = n;
    adj = new double[n][n];

    vertices = new LinkedList<Vertex>();
    for(int i = 0; i < n; ++i) {
      vertices.add(new Vertex(i+1));
    }
  }

  double getEdgeWeight(Vertex u, Vertex v) {
    return adj[u.id - 1][v.id - 1];
  }

  void setWhite() {
    Iterator<Vertex> it = vertices.iterator();
    while(it.hasNext()) {
      Vertex u = it.next();
      u.color = Color.WHITE;
      u.pred = 0;
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

System.out.println(g.source.id);

      if(u.color == Color.WHITE) {
          if(dfsVisit(g, u))  {
            return;
          }
      }

      //System.out.println(g.source.id);

      //g.setWhite();

    }
  }

  static boolean dfsVisit(Graph g, Vertex u) {
    boolean flag = false; // flags if we found an inefficient system

    u.color = Color.GRAY;
    for(int i = 0; i < g.nVertices; ++i) {
      if(g.adj[u.id - 1][i] != 0) {
        Vertex v = g.vertices.get(i);

        // Check if we found a cycle
        if(v == g.source) {
          v.pred = u.id;
          if(inefficient(g, v)) return true;
          //else g.setWhite();
        }

        if(v.color == Color.WHITE) {
          v.pred = u.id;
          flag = dfsVisit(g, v);
          if(flag) return true;
        }
      }
    }
    u.color = Color.WHITE;
    return flag;
  }

  static boolean inefficient(Graph g, Vertex u) {
    Vertex t = u;
    double product = 1;

    product *= g.getEdgeWeight(g.vertices.get(t.pred-1), t);
    t = g.vertices.get(t.pred - 1);
    while(t.id != g.source.id) {
      product *= g.getEdgeWeight(g.vertices.get(t.pred-1), t);
      t = g.vertices.get(t.pred - 1);
    }

    // If we find an inefficient system we need to save the path
    if(product > 1) {
      g.iPath = new Stack<Vertex>();

      // first push the source in
      g.iPath.push(g.source);

      Vertex v = g.vertices.get(u.pred - 1);
      g.iPath.push(v);

      while(v.id != g.source.id) {
        g.iPath.push(v);
        v = g.vertices.get(v.pred - 1);
        g.iPath.push(v);
      }
      g.iProduct = product;

      return true;
    }
    return false;
  }

  class Vertex {
    int id, pred;
    Color color;

    Vertex(int i) {
      id = i;
    }

  }
}

>>>>>>> e7db19ca916b0e016900861396c0826684d490f4
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

    double[][][] data = null; // Matrix to store data for output

    /// READ the input file
    try {
      File file = new File(args[0]);

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
        double x = Double.parseDouble(st.nextToken());
        double y = Double.parseDouble(st.nextToken());

        g.adj[i][j] = y / x;

        data[i][j][0] = x;
        data[i][j][1] = y;
        // b.adj[j][i] = x / y;
      }

    } catch(IOException e) {
      e.printStackTrace();
    }

    // for(int i = 0; i < data.length; ++i) {
    //   for(int j = 0; j < data.length; ++j) {
    //     System.out.println((i+1) + " " + (j+1) + " " + data[i][j][0] + " " + data[i][j][1]);
    //   }
    //   System.out.println();
    // }
    // System.out.println("--------------------");

    // Do the code part here
    //b.printAdj(g);

<<<<<<< HEAD
    // Graph.dfs(g);
    //
    // String out = "";
    // if(g.iPath == null) {
    //   out += "no";
    // } else {
    //   out += "yes\n";
    //   while(!g.iPath.empty()) {
    //     int i = g.iPath.pop().id;
    //     int j = g.iPath.pop().id;
    //     out += i + " " + j + " ";
    //     out += data[i-1][j-1][0] + " " + data[i-1][j-1][1] + "\n";
    //   }
    // }
    // out += "one kg of product " + g.source.id + " gets " + String.format("%.4f", g.iProduct)
    //   + " of product " + g.source.id + " from the above sequence";
    // System.out.println(out);
=======
    Graph.dfs(g);

    String out = "";
    if(g.iPath == null) {
      out += "no";
    } else {
      out += "yes\n";
      while(!g.iPath.empty()) {
        int i = g.iPath.pop().id;
        int j = g.iPath.pop().id;
        out += i + " " + j + " ";
        out += data[i-1][j-1][0] + " " + data[i-1][j-1][1] + "\n";
      }
      out += "one kg of product " + g.source.id + " gets " + String.format("%.4f", g.iProduct)
        + " of product " + g.source.id + " from the above sequence";
    }
    System.out.println(out);
>>>>>>> e7db19ca916b0e016900861396c0826684d490f4

    /// WRITE to the output file
    try {
      BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));

      //bw.write();
      // bw.newLine();


      //bw.write(s1);
      bw.close();
    } catch(IOException e) {
      e.printStackTrace();
    }
    System.out.println("done");
  }
}
