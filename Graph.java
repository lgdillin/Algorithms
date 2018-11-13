
enum GColor {
  WHITE, GREY, BLACK;
}

class GNode {
  GColor color;
  GNode predecessor;
  int distance;

  GNode(GNode p) {
    predecessor = p;
    color = GColor.WHITE;
  }


}

class Graph {

  static void bfs(Graph g, GNode s) {

  }


  public static void main(String[] args) {

  }
}
