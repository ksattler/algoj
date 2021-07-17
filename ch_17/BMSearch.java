public class BMSearch {
  // last-Tabelle initialisieren
  private static int[] initLast(String pat) {
    int[] last = new int[256];
    int i;
    
    for (i = 0; i < 256; i++) 
    last[i] = -1;
    for (i = 0; i < pat.length(); i++)
    last[pat.charAt(i)] = i;
    return last;
  }
  
  // shift-Tabelle initialisieren
  private static int[] initShift(String pat) {
    int m = pat.length();
    int[] shift = new int[m + 1];
    int[] suffix = new int[m + 1];
    
    int i, j, h = 0;
    suffix[m - 1] = m;
    j = m - 1;
    for (i = m - 2; i >= 0; i--) {
      if (i > j && suffix[i + m - 1 - h] < i - j)
      suffix[i] = suffix[i + m - 1 - h];
      else {
        if (i < j) 
        j = i;
        h = i;
        while (j >= 0 && 
        pat.charAt(j) == 
        pat.charAt(j+m-1-h))
        j--;
        suffix[i] = h - j;
      }
    }
    
    for (i = 0; i < m; i++)
    shift[i] = m;
    j = 0;
    for (i = m - 1; i >= -1; i--)
    if (i == -1 || suffix[i] == i + 1)
    while (j < m - 1 - i) {
      if (shift[j] == m)
      shift[j] = m - 1 - i;
      j++;
    }
    for (i = 0; i < m - 1; i++)
    shift[m - 1 - suffix[i]] = m - i - 1;
    return shift;
  }
  
  // Suchfunktion
  public static int bmSearch(String text, String pat) {
    int last[] = initLast(pat);
    int shift[] = initShift(pat);
    
    int i = 0;
    while (i <= text.length() - pat.length()) {
      int j = pat.length() - 1; 
      while (j >= 0 && 
      pat.charAt(j) == text.charAt(i+j)) 
      j--;
      if (j < 0)
      return i;
      else
      i += Math.max(shift[j], 
      j - last[text.charAt(i+j)]);
    }
    return -1;
  }

  public static void main(String[] args) {
    System.out.println("accept = " + BMSearch.bmSearch("AABBACCBACBACBABCBA", "ACBABCBA"));
  }
}
