import java.util.Comparator;

class VertexCompare implements Comparator<Vertex> {
  public int compare(Vertex a, Vertex b) {
    if(a.d < b.d) return -1;
    else if(a.d > b.d) return 1;
    else return 0;
  }
}
