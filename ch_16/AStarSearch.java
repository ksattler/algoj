import java.util.*;

public class AStarSearch {
  private Graph graph;
  private PriorityQueue<Graph.Node> openList = new PriorityQueue<Graph.Node>();
  private List<Graph.Node> closedList = new LinkedList<Graph.Node>();
  
  public AStarSearch(Graph g) { graph = g; }
  
  private int estimateCost(Graph.Node node, Graph.Node target) {
    return node.getCost(Graph.Node.H_COST);
  }
  
  public List<Graph.Node> search(String from, String to) {
    Graph.Node fromNode = graph.getNode(from);
    Graph.Node toNode = graph.getNode(to);
    
    openList.offer(fromNode);
    
    while(! openList.isEmpty()) {
      // Knoten mit den geringsten f-Kosten entnehmen
      Graph.Node node = openList.poll();
      // und zur CLOSED-Liste bewegen -> ist fertig behandelt
      closedList.add(node);
      // Ziel erreicht?
      if (node.equals(toNode)) 
        // Pfad rekonstruieren
        return reconstructPath(node);
      // alle Nachbarknoten durchlaufen
      for (Graph.Edge e : node.adjList) {
        Graph.Node succ = e.getDestNode();
        // Knoten wurde bereits behandelt?
        if (closedList.contains(succ))
          continue;
        
        Graph.Edge ee = node.getEdgeTo(succ);
        // f-Kosten berechnen
        int tf = node.getCost(Graph.Node.G_COST) + 
        ee.getCost() + estimateCost(succ, toNode);
        // der neue Weg ist teurer als der alte Weg -> ignorieren
        if (openList.contains(succ) && tf > succ.getCost(Graph.Node.F_COST))
          continue;
        
        // vorläufigen Vorgänger setzen
        succ.setPredecessor(node);
        // Kosten aktualisieren
        succ.setCost(Graph.Node.F_COST, tf);
        succ.setCost(Graph.Node.G_COST, 
        node.getCost(Graph.Node.G_COST) + 
        ee.getCost());
        // ggf. zur OPEN-Liste hinzufügen
        if (openList.contains(succ))
        openList.remove(succ);
        openList.offer(succ);
      }
    }
    return null;
  }
  
  private List<Graph.Node> reconstructPath(Graph.Node node) {
    List<Graph.Node> path = new LinkedList<Graph.Node>();
    path.add(node);
    while (node.getPredecessor() != null) {
      node = node.getPredecessor();
      path.add(0, node);
    }
    return path;
  }
}
