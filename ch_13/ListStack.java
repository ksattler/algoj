public class ListStack implements Stack {
  private List list; // Liste zur Verwaltung der Elemente

  public ListStack() {
    list = new List();
  }

  public void push(Object obj) {
    // Element vorn anfügen
    list.addFirst(obj);
  }

  public Object pop() throws StackException {
    if (isEmpty())
      throw new StackException();
    // Element von vorn entfernen
    return list.removeFirst();
  }

  public Object top() throws StackException {
    if (isEmpty())
      throw new StackException();
    return list.getFirst();
  }

  public boolean isEmpty() {
    return list.isEmpty();
  }

  public static void main(String[] args) {
    ListStack stack = new ListStack();

    stack.push(1);
    stack.push(2);
    stack.push(3);
    stack.push(4);
    stack.push(5);

    while (!stack.isEmpty()) 
      System.out.println(stack.pop());
  }
}
