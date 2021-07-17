public class DList {
    class Node {
	Object obj;
	Node prev, next;

	public Node (Object o, Node p, Node n) {
	    obj = o;
	    prev = p; next = n;
	}

	public Node () {
	    obj = null;
	    prev = next = null;
	}

	public void setElement (Object o) {
	    obj = o;
	}

	public Object getElement () {
	    return obj;
	}

	public void setNext (Node n) {
	    next = n;
	}

	public Node getNext () {
	    return next;
	}

	public void setPrevious (Node p) {
	    prev = p;
	}

	public Node getPrevious () {
	    return prev;
	}
    }

    class ListIterator implements java.util.Iterator {
	private Node node = null;

	public ListIterator (Node n) {
	    node = n;
	}

	public boolean hasNext () {
	    return node.getNext () != null;
	}

	public void remove () {
	    throw new UnsupportedOperationException ();
	}

	public Object next () {
	    if (! hasNext ())
		throw new java.util.NoSuchElementException ();
	    Object o = node.getElement ();
	    node = node.getNext ();
	    return o;
	}
    }

    private Node head = null;
    private Node tail = null;

    public DList () {
	head = new Node ();
	tail = new Node ();
	head.setNext (tail);
	tail.setPrevious (head);
    }

    public void addFirst (Object o) {
	Node n = new Node (o, head, head.getNext ());
	head.getNext ().setPrevious (n);
	head.setNext (n);
    }

    public void addLast (Object o) {
	Node l = tail.getPrevious ();
	Node n = new Node (o, l, tail);
	l.setNext (n);
	tail.setPrevious (n);
    }

    public Object getFirst () throws ListEmptyException {
	if (isEmpty ())
	    throw new ListEmptyException ();
	return head.getNext ().getElement ();
    }

    public Object getLast () throws ListEmptyException {
	if (isEmpty ())
	    throw new ListEmptyException ();
	return tail.getPrevious ().getElement ();
    }

    public Object removeFirst () throws ListEmptyException {
	if (isEmpty ())
	    throw new ListEmptyException ();
	Object o = head.getNext ().getElement ();
	head.setNext (head.getNext ().getNext ());
	head.getNext ().setPrevious (head);
	return o;
    }

    public Object removeLast () throws ListEmptyException {
	if (isEmpty ())
	    throw new ListEmptyException ();
	Node n = tail.getPrevious ();
	n.getPrevious ().setNext (tail);
	tail.setPrevious (n.getPrevious ());
	return n.getElement ();
    }

    public int size () {
	int s = 0;
	Node n = head;
	while (n.getNext () != tail) {
	    s++;
	    n = n.getNext ();
	}
	return s;
    }

    public boolean isEmpty () {
	return head.getNext () == tail;
    }

    public java.util.Iterator iterator () {
	return new ListIterator (head.getNext ());
    }

    public static void main (String args[]) {
	DList lst = new DList ();
	lst.addFirst ("Drei");
	lst.addFirst ("Zwei");
	lst.addFirst ("Eins");

	lst.addLast ("Vier");
	lst.addLast ("Fünf");
	lst.addLast ("Sechs");

	java.util.Iterator it = lst.iterator ();
	while (it.hasNext ()) {
	    System.out.println ((String) it.next ());
	}
	System.out.println ("--------------------------------");
	while (! lst.isEmpty ()) {
	    System.out.println ((String) lst.removeFirst ());
	}
    }
}

