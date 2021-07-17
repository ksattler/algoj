public class StackExample {
  public static void main(String args[]) {
    Stack stack = new ArrayStack();
    try {
      // Elemente auf Stack ablegen
      stack.push("Eins");
      stack.push("Zwei");
      stack.push("Drei");
      while (! stack.isEmpty()) {
        // Elemente herunternehmen
	      String s = (String) stack.pop();
	      System.out.println(s);
      }
    } catch(StackException exc) {
      System.out.println(exc);
    }
  }
}
