public class TreeNode {
    Object elem;
    TreeNode left = null, right = null;
    
    public TreeNode (Object e) { elem = e; }
    public TreeNode getLeft () { return left; }
    public TreeNode getRight () { return right; }
    public Object getElement () { return elem; }

    public void setLeft (TreeNode n) { left = n; }
    public void setRight (TreeNode n) { right = n; }

    public boolean isLeaf () {
      return left == null && right == null;
    }

    public String toString () { return elem.toString (); }

    public void printNode (StringBuffer sb, int depth) {
      for (int i = 0; i < depth; i++)
	      sb.append ("  ");
      sb.append (toString () + "\n");
      if (left != null)
	      left.printNode (sb, depth + 1);
      else if (! isLeaf ()) {
	      for (int i = 0; i < depth + 1; i++)
	            sb.append ("  ");
	sb.append ("(null)\n");
      }
      if (right != null)
	      right.printNode (sb, depth + 1);
      else if (! isLeaf ()) {
	      for (int i = 0; i < depth + 1; i++)
	            sb.append ("  ");
	      sb.append ("(null)\n");
      }
    }
}
