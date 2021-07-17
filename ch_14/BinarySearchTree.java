public class BinarySearchTree {
  
  protected TreeNode root;
  
  public BinarySearchTree () { root = null; }
  
  public boolean insert (Comparable c) {
    TreeNode parent = null, child = root;
    while (child != null) {
      parent = child;
      int cmp = c.compareTo (child.getElement ());
      if (cmp == 0)
        return false;
      else if (cmp < 0)
        child = child.getLeft ();
      else
        child = child.getRight ();
    }
    if (parent == null)
      root = new TreeNode (c);
    else if (c.compareTo (parent.getElement ()) < 0)
      parent.setLeft (new TreeNode (c));
    else
      parent.setRight (new TreeNode (c));
    return true;
  }
  
  public boolean find (Comparable c) {
    return (findNode (c) != null);
  }
  
  public TreeNode findNode (Comparable c) {
    TreeNode n = root;
    while (n != null) {
      int cmp = c.compareTo (n.getElement ());
      if (cmp == 0)
        return n;
      else if (cmp < 0)
        n = n.getLeft ();
      else
        n = n.getRight ();
    }
    return null;
  }
  
  protected TreeNode recursiveFind (TreeNode n, Comparable c) {
    if (n != null) {
      int cmp = c.compareTo (n.getElement ());
      if (cmp == 0)
        return n;
      else if (cmp < 0)
        return recursiveFind (n.getLeft (), c);
      else
        return recursiveFind (n.getRight (), c);
    }
    else
      return null;
  }
  
  public TreeNode recursiveFindNode (Comparable c) {
    return recursiveFind (root, c);
  }
  
  public Object findMinElement () {
    TreeNode n = root;
    while (n != null && n.getLeft () != null)
      n = n.getLeft ();
    return (n != null ? n.getElement () : null);
  }
  
  public Object findMaxElement () {
    TreeNode n = root;
    while (n != null && n.getRight () != null)
      n = n.getRight ();
    return (n != null ? n.getElement () : null);
  }
  
  public boolean remove (Comparable c) {
    TreeNode parent = null, node = root, child, tmp;
    if (root == null)
      return false;
    
    // zu löschenden Knoten suchen
    while (node != null && c.compareTo (node.getElement ()) != 0) {
      parent = node;
      if (c.compareTo (node.getElement ()) < 0)
        node = node.getLeft ();
      else
        node = node.getRight ();
    }
    if (node == null)
      // Kein Knoten gefunden
      return false;
    
    if (parent == null) {
      // Wurzel löschen
      if (node.getLeft () == null)
        root = node.getRight ();
      else if (node.getRight () == null)
        root = node.getLeft ();
      else {
        tmp = node; child = node.getRight ();
        while (child.getLeft () != null) {
          tmp = child;
          child = child.getLeft ();
        }
        if (/*child != node.getRight ()*/ node != tmp) {
          tmp.setLeft (child.getRight ());
          child.setRight (root.getRight ());
        }
        child.setLeft (root.getLeft ());
        root = child;
      }
    }
    
    // internen Knoten löschen
    else if (node.getLeft () == null) {
      if (parent.getLeft () == node)
        parent.setLeft (node.getRight ());
      else
        parent.setRight (node.getRight ());
    }
    else if (node.getRight () == null) {
      if (parent.getLeft () == node)
        parent.setLeft (node.getLeft ());
      else
        parent.setRight (node.getLeft ());
    }
    else {
      tmp = node; child = node.getRight ();
      while (child.getLeft () != null) {
        tmp = child;
        child = child.getLeft ();
      }
      child.setLeft (node.getLeft ());
      if (node != tmp /*child != node.getRight ()*/) {
        tmp.setLeft (child.getRight ());
        child.setRight (node.getRight ());
      }
      if (parent.getRight () == node)
        parent.setRight (child);
      else
        parent.setLeft (child);
    }
    return true;
  }
  
  
  public String toString () {
    StringBuffer sb = new StringBuffer ();
    if (root != null)
      root.printNode (sb, 0);
    return sb.toString ();
  }
  
  public static void main (String[] args) {
    BinarySearchTree tree = new BinarySearchTree ();
    System.out.println ("min = " + tree.findMinElement ());
    tree.insert (6);
    tree.insert (3);
    tree.insert (1);
    tree.insert (5);
    tree.insert (4);
    tree.insert (9);
    tree.insert (7);
    tree.insert (11);
    System.out.println (tree);
    tree.remove (9);
    System.out.println (tree);
    tree.remove (3);
    System.out.println (tree);
  }
}
