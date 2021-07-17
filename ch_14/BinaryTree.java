public class BinaryTree {
  protected TreeNode root;
  protected int size;
  
  public static final int INORDER = 1;
  public static final int PREORDER = 2;
  public static final int POSTORDER = 3;
  public static final int LEVELORDER = 4;
  
  public BinaryTree () {
    root = null;
    size = 0;
  }
  
  public int size () { return this.size; }
  public boolean isEmpty () { return root == null; }
  
  public TreeNode getRoot () { return root; }
  
  public boolean contains (Comparable o) {
    if (isEmpty ())
    return false;
    TreeNode n = findNode (root, o);
    return (o.compareTo (n.getElement ()) == 0);
  }
  
  protected TreeNode findNode (TreeNode n, Comparable o) {
    int cmp = o.compareTo (n.getElement ());
    if (cmp < 0 && n.getLeft () != null)
      return findNode (n.getLeft (), o);
    else if (cmp > 0 && n.getRight () != null)
      return findNode (n.getRight (), o);
    else
      return n;
  }
  
  public void insert (Comparable o) {
    if (isEmpty ())
      root = new TreeNode (o);
    else {
      TreeNode n = findNode (root, o);
      int cmp = o.compareTo (n.getElement ());
      if (cmp == 0)
        return;
      else if (cmp < 0)
        n.setLeft (new TreeNode (o));
      else
        n.setRight (new TreeNode (o));
    }
    size++;
  }
  
  protected void printPreorder (TreeNode n) {
    if (n != null) {
      System.out.println (n.toString ());
      printPreorder (n.getLeft ());
      printPreorder (n.getRight ());
    }
  }
  
  protected void printPostorder (TreeNode n) {
    if (n != null) {
      printPostorder (n.getLeft ());
      printPostorder (n.getRight ());
      System.out.println (n.toString ());
    }
  }
  
  protected void printInorder (TreeNode n) {
    if (n != null) {
      printInorder (n.getLeft ());
      System.out.println (n.toString ());
      printInorder (n.getRight ());
    }
  }
  
  protected void printLevelorder (Queue q) {
    while (! q.isEmpty ()) {
      TreeNode n = (TreeNode) q.leave ();
      if (n.getLeft () != null)
        q.enter (n.getLeft ());
      if (n.getRight () != null)
        q.enter (n.getRight ());
      System.out.println (n.toString ());
    }
  }
  
  public void traverse (int order) {
    switch (order) {
      case INORDER:
        printInorder (root);
        break;
      case PREORDER:
        printPreorder (root);
        break;
      case POSTORDER:
        printPostorder (root);
        break;
      case LEVELORDER:
        Queue queue = new ArrayQueue ();
        queue.enter (root);
        printLevelorder (queue);
        break;
      default:
    }
  }
  
  public static void main (String[] args) {
    BinaryTree tree = new BinaryTree ();
    tree.insert ("D");
    tree.insert ("B");
    tree.insert ("A");
    tree.insert ("C");
    tree.insert ("F");
    tree.insert ("E");
    tree.insert ("G");
    
    System.out.println("Inorder...");
    tree.traverse (BinaryTree.INORDER);
    System.out.println ("--------------------------------------");
    System.out.println("Postorder...");
    tree.traverse (BinaryTree.POSTORDER);
    System.out.println ("--------------------------------------");
    System.out.println("Preorder...");
    tree.traverse (BinaryTree.PREORDER);
    System.out.println ("--------------------------------------");
    System.out.println("Levelorder...");
    tree.traverse (BinaryTree.LEVELORDER);
  }
}


