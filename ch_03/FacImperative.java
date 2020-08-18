// Programm 3-1
import algoj.IOUtils;

public class FacImperative {
  public static int factorial(int x) {
    int y = 1;
	
    while (x > 1) { 
      y = y * x; 
      x--; 
    }
    return y;
  }

  public static void main(String[] args) {
    int z;
    
    System.out.print("Zahl: ");
    z = IOUtils.readInt();
    System.out.println("Fakultät(" + z + ") = " + 
			factorial(z));
  }
}
