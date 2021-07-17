public class SkipList {
    public static int negInf = Integer.MIN_VALUE; // -inf 
    public static int posInf = Integer.MAX_VALUE; // +inf
    
    static class SLItem {
        public SLItem(int i) { element = i; }
        int element; // Element
        SLItem up, down, // Li+1 und Li-1
        prev, next; // Vorgänger, Nachfolger 
    }

    SLItem head, tail;
    
    public boolean find(int o) {
        SLItem item = head; 
        while (true) {
            // zunächst nach rechts suchen ...
            while (item.next.element != posInf && item.next.element <= o)
                item = item.next;
            if (item.down != null) 
                // eine Ebene nach unten 
                item = item.down;
            else
                // Ebene L0 erreicht
                break; 
        }
        return item.element == o; 
    }

    public static void main(String[] args) {
        SkipList slist = new SkipList();
        // TODO: build
        System.out.println("found: " + slist.find(42));
    }
}