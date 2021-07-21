public class HeapSort {
  private static void percolate(Comparable[] f, int idx, int last) {
    int i = idx + 1, j;
    while (2 * i <= last) { // f[i] hat linken Sohn
      j = 2 * i;  // f[j] ist linker Sohn von f[i]
      if (j < last)  // f[i] hat auch rechten Sohn
	if (f[j-1].compareTo(f[j]) > 0) 
	  j++;    // f[j] ist jetzt kleiner
      if (f[i-1].compareTo(f[j-1]) > 0) {
	swap(f,i-1,j-1);
	i = j;    // versickere weiter
      }
      else  
	// halte an, heap-Bedingung erfüllt
	break;      
    }
  }

  private static void swap(Comparable[] f, int i1, int i2) {
    Comparable tmp = f[i1];
    f[i1] = f[i2];
    f[i2] = tmp;
  }
  
  public static void heapSort(Comparable[] f) {
    int i;
    for (i = f.length / 2; i >= 0; i--)
      percolate(f, i, f.length);
    
    for(i = f.length - 1; i > 0; i--) {
      // tauscht jeweils letztes Element des Heaps mit dem ersten
      swap(f, 0, i); 
      // heap wird von Position 0 bis i hergestellt
      percolate(f, 0, i); 
    }
  }

  public static void main(String[] args) {
    Integer[] feld = { 8, 3, 2, 5, 9, 1};
    HeapSort.heapSort(feld);
    for (int i = 0; i < feld.length; i++) 
      System.out.print(feld[i] + " ");
    System.out.println();
  }
}
