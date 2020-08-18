// Programm 3-2
import algoj.IOUtils;

public class FacRecursive {
  public static int factorial(int x) {
    if (x <= 1)  
      return 1; 
    else 
      return x * factorial(x - 1);
  }

  public static void main(String[] args) {
    int z;
    
    System.out.print("Zahl: ");
    z = IOUtils.readInt();
    System.out.println("Fakultät(" + z + ") = " + 
			factorial(z));
  }
}
