public interface Queue {
  public void enter(Object obj)
    throws QueueException;
  public Object leave() 
    throws QueueException;
    public Object front() 
      throws QueueException;
  public boolean isEmpty();
}
