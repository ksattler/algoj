import java.util.*;

public class ExtendibleHashing implements Hashing { 
    final static int MAXSIZE = 5;
    
    static class Block {
        ArrayList elems = new ArrayList(); 
        int bdepth;
    
        public Block(int d) { bdepth = d; }

        public ArrayList elements() { return elems; }
        public int getDepth() { return bdepth; } 

        public String toString() {
            return "Block(" + bdepth + " : " + elems + ")";
        }
    }

    private int depth;
    private Block hashIndex[] = null;

    private BitSet hashValueToBitSet(Object o) { 
        BitSet bits = new BitSet(32);
        int h = o.hashCode();
        for (int i = 0; i < 32; i++)
            bits.set(i, (h & (1 << i)) != 0); 
        return bits;
    }

    private int getPosition(Object o, int d) { 
        BitSet bits = hashValueToBitSet(o);
        int pos = 0;
        for (int i = 0; i < d; i++) {
            pos *= 2;
            if (bits.get(i)) 
                pos++; 
        }
        return pos; 
    }

    public ExtendibleHashing() { 
        depth = 1;
        hashIndex = new Block[2]; 
        hashIndex[0] = new Block(1); 
        hashIndex[1] = new Block(1);
    }
        
    private void extendIndex() {
        int nsize = 1 << depth;
        Block newIndex[] = new Block[nsize * 2];
        for (int i = 0; i < nsize; i++) { 
            newIndex[2 * i] = hashIndex[i]; 
            newIndex[2 * i + 1] = hashIndex[i];
        }
        hashIndex = newIndex;
        depth++;
    }
    
    public void add(Object o) {
        int idx = getPosition(o, depth); 
        Block b = hashIndex[idx];
        if (b.elements().contains(o)) 
            return;
        while (b.elements().size() == MAXSIZE) { 
            // solange Block voll ist
            if (b.getDepth() == depth) {
                extendIndex();
                idx = getPosition(o, depth);
            }
            splitBlock(idx);
            b = hashIndex[idx];
        }
        // Element hinzufügen
        b.elements().add(o);
    }

    public boolean contains(Object o) {
        int idx = getPosition(o, depth); 
        Block b = hashIndex[idx];
        return b.elements().contains(o);
    }

    private void splitBlock(int idx) {
        Block b = hashIndex[idx];
        Block b0 = new Block(b.getDepth() + 1); 
        Block b1 = new Block(b.getDepth() + 1); 
        Iterator it = b.elements().iterator(); 
        
        while (it.hasNext()) {
            // Elemente neu verteilen
            Object elem = it.next();
            BitSet bits = hashValueToBitSet(elem); 
            if (! bits.get(b.getDepth()))
                b0.elements().add(elem);
            else
                b1.elements().add(elem);
            it.remove();
        }
        int diff = depth - b.getDepth();
        int zdiff = 1 << diff; // 2^diff Einträge
        int start = (idx / zdiff) * zdiff;
        for (int i = start; i < start + zdiff; i++) {
            if (i < start + (1 << (diff - 1))) 
                hashIndex[i] = b0;
            else
                hashIndex[i] = b1;
        }
    }

    public String toString() {
        StringBuffer sbuf = new StringBuffer();
        for (int i = 0; i < hashIndex.length; i++) {
            sbuf.append(hashIndex[i] + "\n");
        }
        return sbuf.toString();
    }

    public static void main(String[] args) {
        ExtendibleHashing htable = new ExtendibleHashing();
        for (int i = 0; i < 50; i++) {
            htable.add(i);
        }

        System.out.println(htable);

        System.out.println("contains 20 -> " + htable.contains(20));
        System.out.println("contains 7 -> " + htable.contains(7));
        System.out.println("contains 60 -> " + htable.contains(60));
    }
}