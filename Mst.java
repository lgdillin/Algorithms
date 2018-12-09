import java.io.*;
import java.util.*;


class Mst {
  EdgeCompare comp;
  VertexCompare vcomp;

  Mst() {
    comp = new EdgeCompare();
    vcomp = new VertexCompare();
  }

  // Creates a graph/forest made of subtrees
  Graph mstKruskal(Graph g) {
    Graph a = new Graph(g.nVertices);

    // Here in the psuedocode we are supposed to make
    // singleton sets of every v in G
    Iterator<Vertex> it1 = g.vertices.iterator();
    while(it1.hasNext()) {
      Vertex v = it1.next();
      MySet.makeSet(v);
    }

    // Sort the edges in nondecreasing order
    Collections.sort(g.edges, comp);
    //Graph.printEdges(g);

    Iterator<Edge> it2 = g.edges.iterator();
    while(it2.hasNext()) {
      Edge e = it2.next();

      Vertex u = g.vertices.get(e.u - 1);
      Vertex v = g.vertices.get(e.v - 1);
      if(MySet.findSet(u) != MySet.findSet(v)) {
        a.addEdge(e);
        MySet.union(u, v);
      }
    }

    return a;
  }

  Graph mstPrim(Graph g, Vertex r) {
    Graph a = new Graph(g.nVertices);

    Iterator<Vertex> it = g.vertices.iterator();
    while(it.hasNext()) {
      Vertex u = it.next();
      u.key = 1000000;
      u.p = null;
    }

    r.key = 0;
    LinkedList<Vertex> q = new LinkedList<Vertex>(g.vertices);
    Collections.sort(q, vcomp);

    while(q.size() != 0) {
      Vertex u = q.poll();
      for(int i = 0; i < g.nVertices; ++i) {
        if(g.adj[u.id - 1][i] != 0) {
          Vertex v = g.vertices.get(i);

          if(q.contains(v) && g.weight(u, v) < v.key) {
            v.p = u;
            a.addEdge(new Edge(u.id, v.id, g.weight(u, v)));
            v.key = g.weight(u, v);
          }
        }
      }
    }

    return a;
  }


  public static void main(String[] args) {
    Mst m = new Mst();
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
        double x = Double.parseDouble(st.nextToken());
        double y = Double.parseDouble(st.nextToken());

        g.adj[i][j] = y / x;
        if(g.adj[i][j] != 0) {
          ++g.nEdges;
          g.edges.add(new Edge(i+1, j+1, y / x));
        }

        data[i][j][0] = x;
        data[i][j][1] = y;
        // b.adj[j][i] = x / y;
      }

    } catch(IOException e) {
      e.printStackTrace();
    }

    //Graph a = m.mstKruskal(g);
    Graph a = m.mstPrim(g, g.vertices.get(0));
    Graph.printAdj(a);
  }
}
