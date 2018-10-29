import java.util.Random;


enum Color {
  RED, BLACK;
}

class RBNode {
  Color color;
  int key;
  RBNode left, right, parent;

  // Used for nil
  RBNode() {
    color = Color.BLACK;
  }

  RBNode(int k) {
    key = k;
    color = Color.RED;
  }
}

class RedBlackTree extends BinarySearchTree {
  RBNode nil, root;

  RedBlackTree() {
    nil = new RBNode();
    nil.color = Color.BLACK;
    nil.left = nil;
    nil.right = nil;
    nil.parent = nil;

    root = nil;
  }

  static void leftRotate(RedBlackTree rbt, RBNode x) {
    RBNode y = x.right; // set y
    x.right = y.left; // turn y's left subtree into x's right subtree

    if(y.left != rbt.nil)
      y.left.parent = x;

    y.parent = x.parent; // link y's parent to x's parent

    if(x.parent == rbt.nil)
      rbt.root = y;
    else if(x == x.parent.left)
      x.parent.left = y;
    else
      x.parent.right = y;

    y.left = x;
    x.parent = y; // put x on y's left
  }

  static void rightRotate(RedBlackTree rbt, RBNode y) {
    RBNode x = y.left; // set y
    y.left = x.right; // turn x's right subtree in to y's left subtree

    if(x.right != rbt.nil)
      x.right.parent = y;

    x.parent = y.parent; // link x's parent to y's parent

    if(y.parent == rbt.nil)
      rbt.root = x;
    else if(y == y.parent.right)
      y.parent.right = x;
    else
      y.parent.left = x;

    x.right = y;
    y.parent = x;
  }

  void print(RBNode x) {
    if(x != nil) {
      print(x.left);
      System.out.println(x.key);
      print(x.right);
    }
  }

  static void rbInsert(RedBlackTree rbt, RBNode z) {
    RBNode y = rbt.nil;
    RBNode x = rbt.root;


    while(x != rbt.nil) {
      y = x;
      if(z.key < x.key)
        x = x.left;
      else
        x = x.right;
    }

    z.parent = y;
    if(y == rbt.nil)
      rbt.root = z;
    else if(z.key < y.key)
      y.left = z;
    else
      z.left = z;

    z.left = rbt.nil;
    z.right = rbt.nil;
    z.color = Color.RED;
    rbInsertFix(rbt, z);
  }

  static void rbInsertFix(RedBlackTree rbt, RBNode z) {
    while(z.parent.color == Color.RED) {

      // if z's parent is the left child of its parent
      if(z.parent == z.parent.parent.left) {
        // set y to z's uncle
        RBNode y = z.parent.parent.right;

        // Case 1: if y is red then recolor
        if(y.color == Color.RED) {
          z.parent.color = Color.BLACK;
          y.color = Color.BLACK;
          z.parent.parent.color = Color.RED;
          z = z.parent.parent;

        // Case 2: if y is black and z is a right child
        } else if(z == z.parent.right) {
          z = z.parent;
          leftRotate(rbt, z);

        // Case 3: y is black and z is a left child
        } else {
          z.parent.color = Color.BLACK;
          z.parent.parent.color = Color.RED;
          rightRotate(rbt, z.parent.parent);
        }

      // If z's parent is the right child of its parent
      } else {
        // Set y to z's cousin
        RBNode y = z.parent.parent.left;

        // Case 1: if y is red then recolor
        if(y.color == Color.RED) {
          z.parent.color = Color.BLACK;
          y.color = Color.BLACK;
          z.parent.parent.color = Color.RED;
          z = z.parent.parent;

        // Case 2: if y is black and z is a right child
        } else if(z == z.parent.left) {
          z = z.parent;
          rightRotate(rbt, z);

        // Case 3: y is black and z is a right child
        } else {
          z.parent.color = Color.BLACK;
          z.parent.parent.color = Color.RED;
          leftRotate(rbt, z.parent.parent);
        }
      }
    }


    rbt.root.color = Color.BLACK;
  }

  public static void main(String[] args) {
    Random r = new Random(123456);
    RedBlackTree rbt = new RedBlackTree();

    for(int i = 0; i < 30; ++i) {
      RBNode x = new RBNode(r.nextInt(300));
      RedBlackTree.rbInsert(rbt, x);
    }

    rbt.print(rbt.root);
  }
}
