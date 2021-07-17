public class ArrayQueue implements Queue {
  private Object[] elements = null; // Elemente
  private int l = 0, u = 0; // unterer und oberer Zeiger
  
  // Queue mit vorgegebener Kapazität erzeugen
  public ArrayQueue(int size) {
    elements = new Object[size];
  }
  
  // Queue mit Standardkapazität erzeugen
  public ArrayQueue() {
    elements = new Object[100];
  }
  
  public void enter(Object obj) throws QueueException {
    if ((elements.length - l + u) % elements.length == elements.length - 1)
      // Kapazität ist erschöpft
      throw new QueueException();
    elements[u] = obj;
    // oberen Zeiger aktualisieren
    u = (u + 1) % elements.length;
  }
  
  public Object leave() throws QueueException {
    if (isEmpty())
      throw new QueueException();
    Object obj = elements[l];
    elements[l] = null;
    // unteren Zeiger aktualisieren
    l = (l + 1) % elements.length;
    return obj;
  }
  
  public Object front() throws QueueException {
    if (isEmpty())
      throw new QueueException();
    return elements[l];
  }
  
  public boolean isEmpty() {
    return l == u;
  }
}
