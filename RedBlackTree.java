import java.util.Random;


// enum Color {
//   RED, BLACK;
// }

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
    root.parent = nil;
  }

  static RBNode rbMinimum(RBNode x) {
    while(x.left != null)
      x = x.left;
    return x;
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
      y.right = z;

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

  static void rbDelete(RedBlackTree rbt, RBNode z) {
    RBNode y = z;
    Color yOriginalColor = y.color;

    RBNode x;
    if(z.left == rbt.nil) {
      x = z.right;
      transplant(rbt, z, z.right);
    } else if(z.right == rbt.nil){
      x = z.left;
      transplant(rbt, z, z.left);
    } else {
      y = rbMinimum(z.right);
      yOriginalColor = y.color;
      x = y.right;

      if(y.parent == z) {
        x.parent = y;
      } else {
        transplant(rbt, y, y.right);
        y.right = z.right;
        y.right.parent = y;
      }

      transplant(rbt, z, y);
      y.left = z.left;
      y.left.parent = y;
      y.color = z.color;
    }

    if(yOriginalColor == Color.BLACK)
      rbDeleteFix(rbt, x);
  }

  static void rbDeleteFix(RedBlackTree rbt, RBNode x) {
    while(x != rbt.root && x.color != Color.BLACK) {
      if(x == x.parent.left) {
        RBNode w = x.parent.left;

        // Case 1: x's uncle is red (w is red)
        if(w.color == Color.RED) {
          w.color = Color.BLACK;
          x.parent.color = Color.RED;
          leftRotate(rbt, x.parent);
          w = x.parent.right;
        }

        // Case 2: if w is black and z is a right child
        if(w.left.color == Color.BLACK && w.right.color == Color.BLACK) {
          w.color = Color.RED;
          x = x.parent;

        // Case 3: w is black and z is a right child
        } else if(w.right.color == Color.BLACK) {
          w.left.color = Color.BLACK;
          w.color = Color.RED;
          rightRotate(rbt, w);
          w = x.parent.right;
        } else {
          // Case 4: x’s sibling w is black, and w’s right child is red
          w.color = x.parent.color;
          x.parent.color = Color.BLACK;
          w.right.color = Color.BLACK;
          leftRotate(rbt, x.parent);
          x = rbt.root;
        }

      } else {
        RBNode w = x.parent.right;

        // Case 1: z's uncle is red (w is red)
        if(w.color == Color.RED) {
          w.color = Color.BLACK;
          x.parent.color = Color.RED;
          rightRotate(rbt, x.parent);
          w = x.parent.left;
        }

        // Case 2: if w is black and z is a left child
        if(w.right.color == Color.BLACK && w.left.color == Color.BLACK) {
          w.color = Color.RED;
          x = x.parent;

        // Case 3: x’s sibling w is black, w’s left child is red, and w’s right child is black
        } else if(w.left.color == Color.BLACK) {
            w.right.color = Color.BLACK;
            w.color = Color.RED;
            leftRotate(rbt, w);
            w = x.parent.left;
        } else {
          // Case 4: x’s sibling w is black, and w’s right child is red
          w.color = x.parent.color;
          x.parent.color = Color.BLACK;
          w.right.color = Color.BLACK;
          leftRotate(rbt, x.parent);
          x = rbt.root;
        }

      }
    }
  }

  static void transplant(RedBlackTree rbt, RBNode u, RBNode v) {
    if(u.parent == rbt.nil) {
      rbt.root = v;
    } else if(u == u.parent.left) {
      u.parent.left = v;
    } else {
      u.parent.right = v;
    }
    v.parent = u.parent;
  }

  void print(RBNode x) {
    if(x != nil) {
      print(x.left);
      System.out.println(x.key);
      print(x.right);
    }
  }

  public static void main(String[] args) {
    Random r = new Random(123456);
    RedBlackTree rbt = new RedBlackTree();

    // Delete these nodes;
    RBNode a = null, b = null, c = null;

    for(int i = 0; i < 30; ++i) {
      RBNode x = new RBNode(r.nextInt(300));
      RedBlackTree.rbInsert(rbt, x);

      if(r.nextDouble() < 0.2)
        a = x;
      else if(r.nextDouble() < 0.5)
        b = x;
      else if(r.nextDouble() < 0.7)
        c = x;
    }

    rbt.print(rbt.root);
    System.out.println();
    System.out.println();
    System.out.println();

    if(a != null) RedBlackTree.rbDelete(rbt, a);
    if(b != null) RedBlackTree.rbDelete(rbt, b);
    if(c != null) RedBlackTree.rbDelete(rbt, c);
    rbt.print(rbt.root);

    //rbt.print(rbt.root);
  }
}
