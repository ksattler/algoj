public class ArrayStack implements Stack {
  private Object elements[] = null; // Elemente
  private int num = 0; // aktuelle Anzahl
  
  // Stack mit vorgegebener Kapazität erzeugen
  public ArrayStack(int size) {
    elements = new Object[size];
  }
  
  // Stack mit Standardkapazität erzeugen
  public ArrayStack() {
    elements = new Object[100];
  }
  
  public void push(Object obj) throws StackException {
    if (num == elements.length)
      // Kapazität erschöpft
      throw new StackException();
    elements[num++] = obj;
  }
  
  public Object pop() throws StackException {
    if (isEmpty ())
      // Stack ist leer
      throw new StackException();
    Object o = elements[--num];
    elements[num] = null;
    return o;
  }
  
  public Object top() throws StackException {
    if (isEmpty())
      throw new StackException();
    return elements[num - 1];
  }
  
  public boolean isEmpty() {
    return num == 0;
  }

  public static void main(String[] args) {
    ArrayStack stack = new ArrayStack();

    stack.push(1);
    stack.push(2);
    stack.push(3);
    stack.push(4);
    stack.push(5);

    while (!stack.isEmpty()) 
      System.out.println(stack.pop());
  }
}
