import algoj.IOUtils;

public class Markov {
  // Initialisierung: die Markov-Tafel
  int len = 3;
  String[] phi = { "|", "x|", "x" };
  String[] psi = { "x|", "||x", "" };
  int[] i = { 1, 1, 3 };
  int[] j = { 3, 2, -1 };
  
  boolean verbose = true;
  
  public Markov(boolean v) {
    verbose = v;
  }
  
  // Methode zur Ausführung
  public String run(String word) {
    int line = 0;
    int pos;
    while (line < len) {
      pos = word.indexOf(phi[line]);
      if (verbose)
        System.out.println("Zeile = " + line + ", Wort = " + word + ", Position = " + pos);
      if (pos > -1) {
        // Suchwort enthalten
        word = word.substring(0, pos) + psi[line] + word.substring(pos + phi[line].length (), word.length ());
        line = i[line];
      }
      else
        // Suchwort nicht enthalten
        line = j[line];
      if (verbose)
        System.out.println("Ergebnis = " + word + " goto " + line);
    }
    return word;
  }
  
  public static void main(String[] args) {
    Markov markov = new Markov(true);
    System.out.print("Eingabe: ");
    String word = IOUtils.readString();
    System.out.println("Wort = " + word);
    System.out.println("Ergebnis = " + markov.run(word));
  }
}
