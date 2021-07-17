import java.util.LinkedList;

public class NEA  {
  public static final int SCAN = -1;

  // Klasse zur Repräsentation der Zustände  
  static class State {
    public State(char s, int n1, int n2) {
      symbol = s; next1 = n1; next2 = n2;
    }
    char symbol; // zu akzeptierendes Symbol
    int next1, next2; // Nachfolgezustände
  }
  
  // "Programm" des NEA
  State[] states = {
    new State(' ', 1, 3), new State('A', 2, 2), 
    new State(' ', 3, 1), new State('B', 4, 4), 
    new State('A', 5, 5), new State(' ', 0, 0)
  };
  
  public NEA() {}
  
  public boolean match(String s) {
    LinkedList<Integer> deque = 
          new LinkedList<Integer>();
    // Initialisierung
    int j = 0, state = states[0].next1;
    if (states[0].next1 != states[0].next2)
	deque.addFirst(Integer.valueOf(states[0].next2));
    deque.addLast(Integer.valueOf(SCAN));
    while (state != 0) {

      if (state == SCAN) {
	j++; 
        deque.addLast(Integer.valueOf(SCAN));
      }
      else if (states[state].symbol == ' ') {
        // "leeres" Zeichen -> Nullzustand
	int n1 = states[state].next1;
	int n2 = states[state].next2;
	deque.addFirst(Integer.valueOf(n1));
	if (n1 != n2) 
	  deque.addFirst(Integer.valueOf(n2));
      }
      else if (j < s.length() && 
               states[state].symbol == s.charAt(j))
        // Zeichen akzeptiert 
	deque.addLast(Integer.valueOf(states[state].next1));
      if (deque.isEmpty() || j > s.length())
        // kein Endzustand erreicht -> Fehler
	return false;

      // (!) neuen Zustand einnehmen
      state = ((Integer) deque.removeFirst()).intValue();
    }
    // Endzustand: Eingabe akzeptieren
    return true;
  }
  
  public static void main(String[] args) {
    NEA nea = new NEA();
    System.out.println("accept = " + nea.match("AABA"));
  }
}
