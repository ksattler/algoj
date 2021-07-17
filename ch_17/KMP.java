public class KMP {
  static int n = 1;
  private static int[] initNext (String pattern) {
    int[] next = new int [pattern.length ()];
    int i = 0, j = -1;
    next[0] = -1;
    while (i < pattern.length () - 1) {
      while (j >= 0 && pattern.charAt (i) != pattern.charAt (j))
      j = next[j];
      i++; j++;
      next[i] = j;
    }
    return next;
  }
  
  private static boolean cmp (char c1, char c2, int i) {
    System.out.println ("n = " + n++ + " (i = " + i + ")");
    return c1 != c2;
  }
  
  public static int kmpSearch (String text, String pattern) {
    int[] next = initNext (pattern);
    int i = 0, j = 0;
    n = 1;
    while (i < text.length ()) {
      while (j >= 0 && /*pattern.charAt (j) != text.charAt (i)*/ cmp (pattern.charAt (j), text.charAt(i), i)) {
        System.out.println (">>>i = " + i + ", j = " + j + 
        ", next[j] = " + next[j] + " (" +
        pattern.charAt (j) + " == " + text.charAt (i) + 
        ")");
        j = next[j];
      }
      i++; j++;
      if (j == pattern.length ())
      return i - pattern.length ();
    }
    return -1;
  }
  
  public static void main (String[] args) {
    String p = "abcaab";
    int[] next = initNext (p);
    for (int i = 0; i < next.length; i++)
    System.out.println ("next[" + i + "] = " + next[i]);
    System.out.println ("pos = " + kmpSearch ("abcabababcaabab", p));
  }
}
