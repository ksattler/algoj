public class Sort {

  /*
   * Hilfsmethode: Initialisierung eines Feldes mit Zufallszahlen
   */
  static int[] initArray (int num) {
    int[] result = new int[num];
    for (int i = 0; i < num; i++)
      result[i] = (int) (Math.random () * 100.0);
    return result;
  }
    
  /*
   * Hilfsmethode: Ausgabe der Elemente eines Feldes
   */
  static void printArray (int[] array) {
    for (int i = 0; i < array.length; i++)
      System.out.print (array[i] + " ");
    System.out.println ();
  }
    
  /*
   * Hilfsmethode: Austauch zweier Feldelemente
   */
  static void swap (int[] array, int idx1, int idx2) {
    int tmp = array[idx1];
    array[idx1] = array[idx2];
    array[idx2] = tmp;
  }
  
  static void insertionSort (int[] array) {
    for (int i = 1; i < array.length; i++) {
      int marker = i;
      int temp = array[i];
      // f�r alle Elemente links vom Marker-Feld
      while (marker > 0 && array[marker - 1] > temp) {
	// verschiebe alle gr��eren Element nach hinten
	array[marker] = array[marker - 1];
	marker--;
      }
      // setze temp auf das freie Feld 
      array[marker] = temp;
    }
  }

  /*
   * Implementierung des SelectionSort
   */
  static void selectionSort (int[] array) {
    int marker = array.length - 1;
    while (marker >= 0) {
      // bestimme gr��tes Element
      int max = 0;
      for (int i = 1; i <= marker; i++)
	if (array[i] > array[max])
	  max = i;
      
      // tausche array[marker] mit diesem Element
      swap (array, marker, max);
      marker--;
    }
  }
  
  /*
   * Implementierung des Bubble-Sort
   */
  static void bubbleSort1 (int[] array) {
    boolean swapped;
    
    do {
      swapped = false;
      
      for (int i = 0; i < array.length - 1; i++) {
	if (array[i] > array[i + 1]) {
	  // Elemente vertauschen
	  swap (array, i, i + 1);
	  swapped = true;
	}
      }
      // solange Vertauschung auftritt
    } while (swapped);
  }
  
  /*
   * Verbesserte Implementierung des Bubble-Sort
   */
  static void bubbleSort2 (int[] array) {
    boolean swapped; // Vertauschung ?
    int max = array.length - 1; // obere Feldgrenze
    do {
      swapped = false;
      
      for (int i = 0; i < max; i++) {
	if (array[i] > array[i + 1]) {
	  // Elemente vertauschen
	  swap (array, i, i + 1);
	  swapped = true;
	}
      }
      max--;
      // solange Vertauschung auftritt
    } while (swapped);
  }
  
  /*
   * Implementierung des MergeSort
   */
  
  // Hilfsmethode für rekursives Sortieren durch Mischen
  static void msort (int[] array, int le, int ri) {
    int i, j, k;
    int[] b = new int[array.length];
    
    if (ri > le) {
      // zu sortierendes Feld teilen
      int mid = (ri + le) / 2;
      // Teilfelder sortieren
      msort (array, le, mid);
      msort (array, mid + 1, ri);
      
      // Hilfsfeld aufbauen
      for (k = le; k <= mid; k++)
	      b[k] = array[k];
      for (k = mid; k < ri; k++)
	      b[ri + mid - k] = array[k + 1];

      // Ergebnisse mischen �ber Hilfsfeld b
      i = le; j = ri;	
      for (k = le; k <= ri; k++)
	      if (b[i] < b[j])
	        array[k] = b[i++];
	      else
	        array[k] = b[j--];
    }
  }
  
  static void mergeSort (int[] array) {
    msort (array, 0, array.length - 1);
  }
  
  /*
   * Implementierung des QuickSort
   */
  
  // Hilfsmethode für rekursives Sortieren
  static void qsort (int[] array, int le, int ri) {
    int lo = le, hi = ri;
    
    if (hi > lo) {
      // Pivotelement bestimmen
      int mid = array[(lo + hi) / 2];
      while (lo <= hi) {
	      // Erstes Element suchen, das gr��er oder gleich dem
	      // Pivotelement ist, beginnend vom linken Index
	      while (lo < ri && array[lo] < mid)
	        ++lo;
	
	      // Element suchen, das kleiner oder gleich dem
	      // Pivotelement ist, beginnend vom rechten Index
	      while (hi > le && array[hi] > mid)
	        --hi;
	
	      // Wenn Indexe nicht gekreuzt --> Inhalte vertauschen
	      if (lo <= hi) {
	        swap(array, lo, hi);
	        ++lo;
	        --hi;
	      }
      }
      // Linke Partition sortieren
      if (le < hi) {
	      qsort (array, le, hi);
      }
      
      // Rechte Partition sortieren
      if (lo < ri) {
	      qsort( array, lo, ri);
      }
    }
  }
  
  static void quickSort (int[] array) {
    qsort (array, 0, array.length - 1);
  }
  
  /*
   * Implementierung des QuickSort
   */
  static void radixSort(int[] array) {
    for (int i = 0; i < 3; i++)
      radixSortStep(array, i, 10);
  }

  static int getDigit(int val, int dpos, int base) {
    int[] div = { 1, 10, 100, 1000, 10000 };
    return (val / div[dpos]) % base;
  }

  static void radixSortStep(int[] array, int d, int base) {
    int[] histo = new int[base]; // Histogramm
    int[] offset = new int[base]; // aktuelle Position pro Partition

    // temporäres Feld
    int[] tmp = new int[array.length];
    System.arraycopy(array, 0, tmp, 0, array.length);

    // Histogramm konstruieren
    for (int i = 0; i < array.length; i++) {
        int digit = getDigit(array[i], d, base);
        histo[digit]++;
    }
    // kumulierte Häufigkeiten berechnen
    int sum = 0;
    for (int i = 0; i < histo.length; i++) {
        sum += histo[i];
        if (histo[i] > 0) histo[i] = sum - histo[i];
    }

    // Elemente an Position kopieren
    for (int i = 0; i < tmp.length; i++) {
      int digit = getDigit(tmp[i], d, base);
      int pos = histo[digit] + offset[digit]++;
      array[pos] = tmp[i];
    }
}
  public static void main(String[] args) {
	  int[] array = null;
	  array = initArray (20);
	  mergeSort (array);
	  printArray (array);

	  array = initArray (20);
	  quickSort (array);
	  printArray (array);

	  array = initArray (20);
	  selectionSort (array);
	  printArray (array);

	  array = initArray (20);
	  insertionSort (array);
	  printArray (array);

	  array = initArray (20);
	  bubbleSort2 (array);
	  printArray (array);
	
	  array = initArray (20);
	  mergeSort (array);
	  printArray (array);

	  array = initArray (20);
	  radixSort (array);
	  printArray (array);
  }
}
