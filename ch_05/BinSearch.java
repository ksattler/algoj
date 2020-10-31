public class BinSearch {
  public final static int NO_KEY = -1;
  
  static int search(int[] array, int key) {
    int u = 0, o = array.length - 1;
    while (u <= o) {
      int m = (u + o) / 2;
      if (array[m] == key)
	return m;
      else if (array[m] > key)
	o = m - 1;
      else
	u = m + 1;
    }
    return NO_KEY;
  }
  
  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("usage: BinSearch <key>");
      return;
    }
    int[] f = { 2, 4, 5, 6, 7, 8, 9, 11 };
    int k = Integer.parseInt(args[0]);
    System.out.println("Binär: " + search(f, k));
  }
}
