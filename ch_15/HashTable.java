public class HashTable implements Hashing {
  Object[] table;

  public HashTable (int size) {
    table = new Object [size];
  }

  public void add (Object o) {
    int idx, oidx;
    
    oidx = idx = (o.hashCode () & 0x7fffffff) % table.length;
    while (table[idx] != null) {
      idx = ++idx % table.length;
      if (idx == oidx)
        throw new HashTableOverflowException ();
    }
    table[idx] = o;
  }

  public boolean contains (Object o) {
    int idx, oidx;
    
    oidx = idx = (o.hashCode () & 0x7fffffff) % table.length;
    while (table[idx] != null) {
      if (o.equals (table[idx]))
        return true;
      idx = ++idx % table.length;
      if (idx == oidx)
        break;
    }
    return false;
  }

  public static void main (String[] args) {
    HashTable tbl = new HashTable (20);
    tbl.add ("Hallo");
    tbl.add ("Du");
    tbl.add ("kleiner");
    tbl.add ("Hash");

    System.out.println ("Du: " + tbl.contains ("Du"));
    System.out.println ("gro�er: " + tbl.contains ("gro�er"));
  }
}
