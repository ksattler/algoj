public class ListEmptyException extends RuntimeException {
    public ListEmptyException (String msg) {
	super (msg);
    }

    public ListEmptyException () {
	super ();
    }
}
