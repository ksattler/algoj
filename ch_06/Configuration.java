public class Configuration {
  public final static int NUM_REGISTERS = 10;
  int ic;
  int registers[] = new int[NUM_REGISTERS];
  
  public Configuration() {
    init();
  }
  
  // Initialisierung der Register und des Befehlszählers
  public void init() {
    ic = 0;
    for (int i = 0; i < registers.length; i++)
    registers[i] = 0;
  }
  
  // Lesen und Setzen des Befehlszählers
  public int getICounter() { return ic; }
  public void setICounter(int nic) { ic = nic; }
  // Befehlszähler inkrementieren
  public void incICounter() { ic++; }
  
  // Register belegen und auslesen
  public void setRegister(int i, int val) { 
    registers[i] = val; 
  }
  
  public int getRegister(int i) { return registers[i]; }
  
  // Aktuelle Konfiguration als String ausgeben
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append("icounter = " + (ic + 1));
    for (int i = 0; i < registers.length; i++)
    sb.append(", c[" + i + "] = " + registers[i]);
    return sb.toString();
  }
}
