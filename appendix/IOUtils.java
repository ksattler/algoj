package algoj;

import java.io.*;
import java.util.Vector;

// Die Klasse %%\texttt{IOUtils}%% beinhaltet Methoden, die die Ein- und
// Ausgabe von Werten vereinfachen. 
public class IOUtils {

  // Liest einen %%\texttt{int}%%-Wert von der Standardeingabe und gibt
  // diesen als Ergebnis zurück.
  // Beispiel:
  // %%\texttt{int i = IOUtils.readInt();}%%
  //
  // %%\textbf{Ergebnis:}%% der gelesene %%\texttt{int}%%-Wert
  //
  public static int readInt() {
    int result = 0;
    BufferedReader reader =
      new BufferedReader(
        new InputStreamReader(System.in));
    try {
      result = Integer.parseInt(reader.readLine());
    }
    catch (IOException e) {
      System.err.println("I/O Error: " + 
                         e.getMessage());
    }
    catch (NumberFormatException e) {
      System.err.println("Format Error: " + 
                         e.getMessage ());
    }
    return result;
  }
    
  // Liest einen %%\texttt{float}%%-Wert von der Standardeingabe und gibt
  // diesen als Ergebnis zurück.
  //
  // %%\textbf{Ergebnis:}%% der gelesene %%\texttt{float}%%-Wert
  //
  public static float readFloat() {
    float result = 0;
    BufferedReader reader =
      new BufferedReader(
        new InputStreamReader(System.in));
    try {
      result = Float.parseFloat(reader.readLine());
    }
    catch (IOException e) {
      System.err.println("I/O Error: " + 
                         e.getMessage ());
    }
    catch (NumberFormatException e) {
      System.err.println("Format Error: " + 
                         e.getMessage());
    }
    return result;
  }
  
  // Liest eine Zeichenkette von der Standardeingabe und gibt
  // diese als Ergebnis zurück.
  // Beispiel:
  // %%\texttt{String s = IOUtils.readString();}%%
  //
  // %%\textbf{Ergebnis:}%% die gelesene Zeichenkette als %%\texttt{String}%%-Objekt
  //
  public static String readString() {
    String result = null;
    BufferedReader reader =
      new BufferedReader(
        new InputStreamReader(System.in));
    try {
      result = reader.readLine();
    }
    catch (IOException e) {
      System.err.println("I/O Error: " + 
                         e.getMessage());
    }
    return result;
  }
  
  // Liest ein Zeichen von der Standardeingabe und gibt
  // dieses als Ergebnis zurück.
  //
  // %%\textbf{Ergebnis:}%% das gelesene Zeichen als %%\texttt{char}%%-Wert
  //
  public static char readChar() {
    char result = '\u0000';
    BufferedReader reader =
      new BufferedReader(
        new InputStreamReader(System.in));
    try {
      result = (char)reader.read();
    }
    catch (IOException e) {
      System.err.println("I/O Error: " + 
                         e.getMessage());
    }
    return result;
  }

  // Liest ein Folge von Integerwerten aus der angegebenen Datei 
  // und gibt diese in einem Feld zurück.
  // Im Fehlerfall wird %%\texttt{null}%% geliefert.
  //
  // %%\textbf{Parameter:} \texttt{filename}%% Der Name (inkl. Pfad) der einzulesenden Datei
  // %%\textbf{Ergebnis:}%% das %%\texttt{int}%%-Feld mit den Werten
  //
  public static int[] readIntArray(String filename) {
    int[] result = null;
    
    Vector values = new Vector();
    try {
      BufferedReader reader =
	new BufferedReader(new FileReader(filename));
      while (reader.ready()) {
	Integer value = Integer.valueOf(reader.readLine());
	values.addElement(value);
      }
      reader.close();
      result = new int[values.size()];
      for (int i = 0; i < values.size(); i++)
	result[i] = 
          ((Integer)values.elementAt(i)).intValue();
      
    }
    catch (IOException e) {
      System.err.println("I/O Error: " + 
                         e.getMessage ());
    }
    
    return result;
  }
}
