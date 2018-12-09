import java.util.Comparator;

class EdgeCompare implements Comparator<Edge> {
  public int compare(Edge a, Edge b) {
    if(a.w < b.w) return -1;
    else if(a.w > b.w) return 1;
    else return 0;
  }
}
