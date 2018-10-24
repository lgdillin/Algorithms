import java.util.Random;


class Node {
  int key;
  Node left, right, parent;

  Node(int k) {
    key = k;
  }
}

class BinarySearchTree {
  Node root;

  BinarySearchTree() {
    root = null;
  }

  static Node search(Node x, int k) {
    if(x == null || k == x.key)
      return x;
    if(k < x.key)
      return search(x.left, k);
    else
      return search(x.right, k);
  }

  static Node minimum(Node x) {
    while(x.left != null)
      x = x.left;
    return x;
  }

  static Node maximum(Node x) {
    while(x.right != null)
      x = x.right;
    return x;
  }

  int predecessor() {
    return 0;
  }

  Node successor(Node x) {
    if(x.right != null)
      return minimum(x.right);
    Node y = x.parent;
    while(y != null && x == y.right) {
      x = y;
      y = y.parent;
    }
    return y;
  }

  static void insert(BinarySearchTree bst, Node z) {
    Node y = null;
    Node x = bst.root;

    while(x != null) {
      y = x;
      if(z.key < x.key)
        x = x.left;
      else
        x = x.right;
    }

    z.parent = y;
    if(y == null)
      bst.root = z; // Tree was empty
    else if(z.key < y.key)
      y.left = z;
    else
      y.right = z;
  }

  static void delete(BinarySearchTree bst, Node z) {
    if(z.left == null)
      transplant(bst, z, z.right);
    else if(z.right == null)
      transplant(bst, z, z.left);
    else {
      Node y = minimum(z.right);

      if(y.parent != z) {
        transplant(bst, y, y.right);
        y.right = z.right;
        y.right.parent = y;
      }

      transplant(bst, z, y);
      y.left = z.left;
      y.left.parent = y;
    }

  }

  static void transplant(BinarySearchTree bst, Node u, Node v) {
    if(u.parent == null)
      bst.root = v;
    else if(u == u.parent.left)
      u.parent.left = v;
    else
      u.parent.right = v;
    if(v != null)
      v.parent = u.parent;
  }

  static void print(Node x) {
    if(x != null) {
      print(x.left);
      System.out.println(x.key);
      print(x.right);
    }
  }

  public static void main(String[] args) {
    Random r = new Random(123456);
    BinarySearchTree bst = new BinarySearchTree();

    for(int i = 0; i < 30; ++i) {
      Node x = new Node(r.nextInt(300));
      BinarySearchTree.insert(bst, x);
    }

    BinarySearchTree.print(bst.root);
  }
}
