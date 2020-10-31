public class SeqSearch {
  public final static int NO_KEY = -1;
  
  static int search(int[] array, int key) {
    for (int i = 0; i < array.length; i++)
      if (array[i] == key)
	return i;
    return NO_KEY;
  }
  
  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("usage: SeqSearch <key>");
      return;
    }
    int[] f = { 2, 4, 5, 6, 7, 8, 9, 11 };
    int k = Integer.parseInt(args[0]);
    System.out.println("Sequenziell: " + search(f, k));
  }
}
