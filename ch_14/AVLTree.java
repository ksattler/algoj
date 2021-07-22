public class AVLTree {
  static class AVLNode {
    AVLNode left = null, right = null;
    Object key;
    private int balance; // -1, 0 oder 1
    public AVLNode(Object o) { key = o; balance = 0; } 
    public Object getKey() { return key; }
    public AVLNode getLeft() { return left; } 
    public AVLNode getRight() { return right; } 
    public void setLeft(AVLNode n) { left = n; } 
    public void setRight(AVLNode n) { right = n; } 
    public int getBalance() { return balance; } 
    public void setBalance(int b) { balance = b; }
    
    public int compareKeyTo(Comparable c) { 
      return (key == null ? -1 : ((Comparable) key).compareTo(c));
    }
    
    public String toString () { return key.toString () + ":" + balance; }
  }
  
  private AVLNode head, nullNode;
  private boolean rebalance = false;    
  // Hilfsvariable fuer Einfuegen u. Loeschen
  
  private int numElements = 0;
  
  public AVLTree() {
    head = new AVLNode(null); 
    nullNode = new AVLNode(null); 
    head.setLeft(nullNode);
    head.setRight(nullNode);
  }
  
  public boolean find(Comparable c) {
    return (findNode(c) != null);
  }
  
  public AVLNode findNode(Comparable c) {
    AVLNode n = head.getRight();
    while (n != nullNode) {
      int cmp = c.compareTo (n.getKey ());
      if (cmp == 0)
      return n;
      else if (cmp < 0)
      n = n.getLeft ();
      else
      n = n.getRight ();
    }
    return null;
  }
  
  private AVLNode rotateLeft(AVLNode n) { 
    AVLNode tmp = n.getRight(); 
    n.setRight(n.getRight().getLeft()); 
    tmp.setLeft(n);
    return tmp; 
  }
  
  private AVLNode rotateRight(AVLNode n) { 
    AVLNode tmp = n.getLeft(); 
    n.setLeft(n.getLeft().getRight()); 
    tmp.setRight(n);
    return tmp; 
  }
  
  
  AVLNode insertNode(AVLNode n, Comparable k) {
    AVLNode tmp;
    if (n.compareKeyTo(k) == 0) {
      rebalance = false;
      return n;
    }
    else if (n.compareKeyTo(k) < 0) { 
      // weiter nach rechts gehen
      if (n.getRight () != nullNode) {
        // rechts einfügen
        n.setRight(insertNode(n.getRight(), k));
        if (n != head && rebalance) {
          // (2) Ausgleichen notwendig
          switch (n.getBalance ()) {
            case  1: 
            if (n.getRight ().getBalance () == 1) {
              // Rotation nach links
              tmp = rotateLeft (n);
              tmp.getLeft().setBalance(0);
            } 
            else { 
              // doppelte Rotation rechts-links
              int b = n.getRight().getLeft().getBalance ();
              n.setRight(rotateRight(n.getRight()));
              tmp = rotateLeft(n);
              tmp.getRight().setBalance((b == -1) ?  1 : 0);
              tmp.getLeft().setBalance((b == 1)  ? -1 : 0);
            }
            tmp.setBalance (0); 
            rebalance = false; 
            return tmp;
            case  0: 
            n.setBalance(1); 
            return n; 
            case -1: 
            n.setBalance(0); 
            rebalance = false; 
            return n;
          }
        }
        else 
        return n;
      }
      else {
        AVLNode newNode = new AVLNode(k); 
        newNode.setLeft(nullNode); 
        newNode.setRight(nullNode); 
        n.setRight(newNode);
        numElements++;
        n.setBalance(n.getBalance () + 1); 
        rebalance = (n.getBalance () >= 1);
        return n;
      }
    }
    else { // links einfuegen (analog)
      if (n.getLeft() != nullNode) {
        n.setLeft(insertNode(n.getLeft(), k));
        if (n != head && rebalance) {
          switch (n.getBalance()) {
            case -1:
            if (n.getLeft().getBalance () == -1) { 
              // Rechtsrotation
              tmp = rotateRight(n);
              tmp.getRight().setBalance (0);
            }
            else { 
              // Linksrechts-Rotation
              int b = n.getLeft().getRight().getBalance();
              n.setLeft(rotateLeft(n.getLeft()));
              tmp = rotateRight(n);
              tmp.getRight().setBalance ((b == -1) ?  1 : 0);
              tmp.getLeft().setBalance ((b == 1) ? -1 : 0);
            }
            tmp.setBalance(0); 
            rebalance = false; 
            return tmp;
            case 0: 
            n.setBalance(-1); 
            return n; // rebalance bleibt true
            case 1: 
            n.setBalance(0); 
            rebalance = false; 
            return n;
          }
        }
        else return n;
      }
      else {
        // (5) neuen Knoten anlegen
        AVLNode newNode = new AVLNode(k); 
        newNode.setLeft(nullNode); 
        newNode.setRight(nullNode); 
        n.setLeft(newNode); 
        numElements++;
        n.setBalance(n.getBalance() - 1); 
        rebalance = (n.getBalance() <= -1);
        return n;
      }
    }
    return null;
  }
  
  public void insert(Comparable k) { 
    insertNode(head, k);
  }
  
  public int size() {
    return numElements;
  }
  
  public String toString() {
    StringBuffer sb = new StringBuffer ();
    if (head.getRight() != nullNode)
    printNode(sb, 0, head.getRight());
    return sb.toString();
  }
  
  
  private void printNode(StringBuffer sb, int depth, AVLNode n) {
    for (int i = 0; i < depth; i++)
    sb.append("  ");
    sb.append(n.toString() + "\n");
    if (n.getLeft() != nullNode)
    printNode(sb, depth + 1, n.getLeft());
    else  {
      for (int i = 0; i < depth + 1; i++)
      sb.append("  ");
      sb.append("(null)\n");
    }
    if (n.getRight() != nullNode)
    printNode(sb, depth + 1, n.getRight());
    else {
      for (int i = 0; i < depth + 1; i++)
      sb.append("  ");
      sb.append("(null)\n");
    }
  }
  
  public static void main(String[] args) {
    AVLTree tree = new AVLTree();
    tree.insert(3);
    tree.insert(2);
    tree.insert(1);
    tree.insert(4);
    tree.insert(5);
    tree.insert(6);
    tree.insert(7);
    tree.insert(16);
    tree.insert(15);
    System.out.println(tree);
  }
}
