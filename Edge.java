

class Edge {
  int u, v; // vertex ids
  int w;

  Edge(int u, int v, int weight) {
    this.u = u;
    this.v = v;
    w = weight;
  }

  Edge(Vertex u, Vertex v, int weight) {
    this.u = u.id;
    this.v = v.id;
    w = weight;
  }
}
