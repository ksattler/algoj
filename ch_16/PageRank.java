public class PageRank {
    int[][] graph = null; // Adjanzenzmatrix des Graphen
    double[] pageRank = null; // PageRank-Werte aller Knoten
    int numNodes = 0;
    double damping = 0.85;

    PageRank(int[][] g, int n) {
        graph = g;
        numNodes = n;
    }

    // Initialisierung der PageRank-Werte
    void initialize() {
        pageRank = new double[numNodes];
        for (int i = 0; i < numNodes; i++)
            pageRank[i] = 1.0 / numNodes;
    }

    // Anzahl der ausgehenden Kanten von Knoten n bestimmen
    int outgoingLinks(int n) {
        int num = 0;
        for (int i = 0; i < numNodes; i++) 
            num += graph[n][i];
        return num;
    }

    // Aktualisierung der PageRank-Werte
    void updatePageRank() {
        double[] oldValue = new double[numNodes];
        for (int i = 0; i < numNodes; i++) {
            oldValue[i] = pageRank[i];
            pageRank[i] = 0;
        }
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++)
                // falls Kante j->i existiert
                if (graph[j][i] == 1)
                    pageRank[i] += oldValue[j] / outgoingLinks(j);
        }
        
        for (int i = 0; i < numNodes; i++)
            pageRank[i] = (1 - damping) + damping * pageRank[i];
    }

    public void calc() {
        initialize();
        for (int i = 0; i < 100; i++) {
            updatePageRank();
		System.out.println(this);
	}
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < numNodes; i++)
            sb.append(i + " : " + pageRank[i] + "\n");
        return sb.toString();
    }

    public static void main(String[] args) {
        int[][] graph = {
         // A, B, C, D, E  
          { 0, 1, 1, 0, 0 }, // A
          { 1, 0, 1, 0, 1 }, // B
          { 0, 0, 0, 0, 1 }, // C
          { 0, 0, 1, 0, 0 }, // D
          { 0, 0, 0, 1, 0 }, // E
        };

        PageRank p = new PageRank(graph, graph.length);
        p.calc();
        System.out.println(p);
    }
}
