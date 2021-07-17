import java.util.LinkedList;
import java.util.Iterator;

public class LinkedHashTable implements Hashing {
  LinkedList[] table; // Feld mit Listen

  public LinkedHashTable (int size) {
    // Feld aufbauen
    table = new LinkedList [size];
  }

  public void add (Object o) {
    // Feldindex über Hashwert bestimmen
    int idx = (o.hashCode () & 0x7fffffff) % table.length;
    if (table[idx] == null) 
      // noch keine Liste vorhanden
      table[idx] = new LinkedList ();
    // an Liste anh�ngen
    table[idx].addLast (o);
  }

  public boolean contains (Object o) {
    // Feldindex über Hashwert bestimmen
    int idx = (o.hashCode () & 0x7fffffff) % table.length;
    if (table[idx] != null) {
      // Liste gefunden
      Iterator it = table[idx].iterator ();
      while (it.hasNext ()) {
        // sequenzielle Suche nach Element
        Object obj = it.next ();
        if (obj.equals (o))
	  return true;
      }
    }
    return false;
  }

public static void main (String[] args) {
  LinkedHashTable tbl = new LinkedHashTable (20);
  tbl.add ("Hallo");
  tbl.add ("Du");
  tbl.add ("kleiner");
  tbl.add ("Hash");

  System.out.println ("Du: " + tbl.contains ("Du"));
  System.out.println ("großer: " + tbl.contains ("großer"));
}
}
