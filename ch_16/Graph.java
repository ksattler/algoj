import java.util.*;

public class Graph {
  public final static int WHITE = 0;
  public final static int GRAY = 1;
  public final static int BLACK = 2;
  
  static class Edge {
    Node dest; // Zielknoten 
    int cost; // Kantengewicht
    public Edge(Node n, int c) { dest = n; cost = c; } 
    public Node getDestNode() { return dest; }
    public int getCost() { return cost; }
  }
  
  // Knoten
  static class Node {
    String label; // Knotenbezeichner
    ArrayList<Edge> adjList = new ArrayList<Edge>(); // Adjazenzliste
    Node pred = null;
    public int H_COST = 0, F_COST = 0, G_COST = 0;
    
    public Node(String s) { label = s; }
    public Node(String s, int h, Node p) { label = s; H_COST = h; pred = p; }
    
    public String getLabel() { return label; } 
    public void addEdge(Edge e) { adjList.add(e); }
    public Iterator<Edge> getEdges() { 
      return adjList.iterator(); 
    }
    
    public Edge getEdgeTo(Node n) { 
      for (Edge e : adjList) {
        if (e.dest.equals(n)) 
          return e;
      }
      return null; 
    }

    public Node getPredecessor() {
      return pred;
    }
  }
 
  public Node addNode(String label) throws NodeAlreadyDefinedException {
    if (nodeSet.containsKey(label))
      throw new NodeAlreadyDefinedException();
      Node n = new Node(label); 
      nodeSet.put(label, n); 
      return n;
    }
    
  public Node getNode(String label) throws NoSuchElementException {
    Node n = nodeSet.get(label); 
    if (n == null)
      throw new NoSuchElementException(); 
    return n;
  }

  public void addEdge(String src, String dest, int cost) { 
    Node srcNode = getNode(src);
    Node destNode = getNode(dest);
    srcNode.addEdge(new Edge(destNode, cost));
  }

  // Verzeichnis aller Knoten des Graphen
  private HashMap<String, Node> nodeSet = new HashMap<String, Node>();   
  
  public Graph () {} 
}
