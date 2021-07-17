public class List {
	class Node {
		Object obj;
		Node next;
		
		public Node (Object o, Node n) {
			obj = o;
			next = n;
		}
		
		public Node () {
			obj = null;
			next = null;
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
	}
	
	private Node head = null;
	
	public List () {
		head = new Node ();
	}
	
	public void addFirst (Object o) {
		Node n = new Node (o, head.getNext ());
		head.setNext (n);
	}
	
	public void addLast (Object o) {
		Node l = head;
		while (l.getNext () != null)
		l = l.getNext ();
		Node n = new Node (o, null);
		l.setNext (n);
	}
	
	public Object getFirst () throws ListEmptyException {
		if (isEmpty ())
		throw new ListEmptyException ();
		return head.getNext ().getElement ();
	}
	
	public Object getLast () throws ListEmptyException {
		if (isEmpty ())
		throw new ListEmptyException ();
		Node l = head;
		while (l.getNext () != null)
		l = l.getNext ();
		return l.getElement ();
	}
	
	public Object removeFirst () throws ListEmptyException {
		if (isEmpty ())
		throw new ListEmptyException ();
		Object o = head.getNext ().getElement ();
		head.setNext (head.getNext ().getNext ());
		return o;
	}
	
	public Object removeLast () throws ListEmptyException {
		if (isEmpty ())
		throw new ListEmptyException ();
		Node l = head;
		while (l.getNext ().getNext () != null)
		l = l.getNext ();
		Object o = l.getNext ().getElement ();
		l.setNext (null);
		return o;
	}
	
	public int size () {
		int s = 0;
		Node n = head;
		while (n.getNext () != null) {
			s++;
			n = n.getNext ();
		}
		return s;
	}
	
	public boolean isEmpty () {
		return head.getNext () == null;
	}
	
	public static void main (String args[]) {
		List lst = new List ();
		lst.addFirst ("Drei");
		lst.addFirst ("Zwei");
		lst.addFirst ("Eins");
		
		lst.addLast ("Vier");
		lst.addLast ("Fünf");
		lst.addLast ("Sechs");
		
		while (! lst.isEmpty ()) {
			System.out.println ((String) lst.removeFirst ());
		}
	}
}

