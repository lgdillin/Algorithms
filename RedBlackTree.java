
enum Color {
  RED, BLACK;
}

class RBNode {
  Color color;
  int key;
  RBNode left, right, parent;

  RBNode(int k) {
    key = k;
  }
}

class RedBlackTree extends BinarySearchTree {
  RBNode nil, root;

  RedBlackTree() {
    nil.color = Color.BLACK;
    nil.left = nil;
    nil.right = nil;
    nil.parent = nil;

    root.parent = nil;
  }

  static void leftRotate(RedBlackTree rbt, RBNode x) {
    RBNode y = x.right; // set y
    x.right = y.left; // turn y's left subtree into x's subtree

    if(y.left != rbt.nil)
      y.left.parent = x;

    y.parent = x.parent; // link x's parent to y

    if(x.parent == rbt.nil)
      rbt.root = y;
    else if(x == x.parent.left)
      x.parent.left = y;
    else
      x.parent.right = y;

    x.left = x;
    x.parent = y; // put x on y's left
  }

  static void rightRotate(RedBlackTree rbt, RBNode y) {
    RBNode y = x.left; // set y
    x.left = y.right; // turn y's right subtree in to x's subtree

    if(y.right != rbt.nil)
      y.right.parent = x;

    y.parent = x.parent; // link x's parent to y
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
      if(z.parent == z.parent.parent.left) {
        RBNode y = z.parent.parent.right;

        if(y.color == Color.RED) {
          z.parent.color = Color.BLACK;
          y.color = Color.BLACK;
          z.parent.parent.color = Color.RED;
          z = z.parent.parent;
        } else if(z == z.parent.right) {
          z = z.parent;
          leftRotate(rbt, z);
        }


      }
    } else
  }
}
