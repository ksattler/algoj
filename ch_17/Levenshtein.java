public class Levenshtein {
  public static int distance(String s1, String s2) {
    int m = s1.length(), n = s2.length();
    int[][] d = new int[m + 1][n + 1];
    int i, j;
	
    for (i = 0; i <= m; i++) d[i][0] = i;
    for (j = 0; j <= n; j++) d[0][j] = j;
    for (i = 1; i <= m; i++) {
      for (j = 1; j <= n; j++) {
        int cost = (s1.charAt(i-1) == s2.charAt(j-1) ? 0 : 1);
        d[i][j] = Math.min(
                    Math.min(d[i-1][j] + 1,  // Zeichen gelöscht
                             d[i][j-1] + 1), // Zeichen eingefügt
             d[i-1][j-1] + cost); // Zeichen ersetzt bzw. gleich
      }
    }
    return d[m][n];
  }

  public static void main(String[] args) {
    System.out.println(distance("Qualität", "Quantität"));
  }
}
