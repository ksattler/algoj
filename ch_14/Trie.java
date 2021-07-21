import java.util.BitSet;

public class Trie {
  class Node {
    protected Node[] children = null;
    
    public Node() {}
    
    public boolean isLeaf() {
      return false;
    }
    
    public Node getChild(boolean b) {
      return children == null ? null : children[b ? 1 : 0];
    }
    
    public void setChild(boolean b, Node n) {
      if (children == null)
        children = new Node[2];
      children[b ? 1 : 0] = n;
    }
  }
  
  class Leaf extends Node {
    private char key;
    
    public Leaf(char k) {
      key = k;
    }
    
    public boolean isLeaf() {
      return true;
    }
    
    public char getKey() {
      return key;
    }
  }
  
  private Node root = null;
  
  public Trie() {}
  
  int compareBitSets(BitSet b1, BitSet b2) {
    int len = Math.min(b1.size(), b2.size());
    for (int i = 0; i < len; i++)
      if (b1.get(i) != b2.get(i))
        return i;
    return len;
  }
  
  BitSet keyToBitSet(char c) {
    BitSet bits = new BitSet(5);
    int v = c - 'a' + 1;
    for (int i = 0; i < 5; i++) {
      int b = (1 << i);
      bits.set(i, (v & b) > 0);
    }
    return bits;
  }
  
  public void insertKey(char c) {
    BitSet bits = keyToBitSet(c);
    Leaf leaf = new Leaf(c);
    int pos = 0;
    
    if (root == null) {
      root = new Node();
      root.setChild(bits.get(0), leaf);
    }
    else {
      Node node = root;
      while (node != null) {
        Node parent = node;
        node = node.getChild(bits.get(pos));
        if (node == null) {
          parent.setChild(bits.get(pos), leaf);
          break;
        }
        else if (node.isLeaf()) {
          Leaf oldLeaf = (Leaf) node;
          BitSet otherBits = keyToBitSet(oldLeaf.getKey());
          int eqBits = compareBitSets(bits, otherBits) - pos;
          for (int i = 0; i < eqBits; i++) {
            Node newNode = new Node();
            parent.setChild(bits.get(pos + i), newNode);
            parent = newNode;
          }
          parent.setChild(bits.get(pos + eqBits), leaf);
          parent.setChild(! bits.get(pos + eqBits), oldLeaf);
          break;
        }
        pos++;
      }
    }
  }
  
  boolean lookupKey(char c) {
    BitSet bits = keyToBitSet(c);
    Node node = root;
    int pos = 0;
    
    while (node != null) {
      node = node.getChild(bits.get(pos++));
      if (node == null)
      break;
      if (node.isLeaf()) {
        Leaf leaf = (Leaf) node;
        return leaf.getKey() == c;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    Trie trie = new Trie();
    trie.insertKey('a');
    trie.insertKey('l');
    trie.insertKey('g');
    trie.insertKey('o');
    System.out.println("a: " + trie.lookupKey('a'));
    System.out.println("g: " + trie.lookupKey('g'));
    System.out.println("z: " + trie.lookupKey('z'));
  }
}
