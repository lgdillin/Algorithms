enum Color { WHITE, GRAY, BLACK }

class Vertex {
  Vertex p;
  int id, rank;
  int d;
  Color color;

  Vertex(int i) {
    id = i;
  }
}
