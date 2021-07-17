public class IfGoto implements Instruction {
  private int pos; // Sprungziel

  public IfGoto(int p) {
    pos = p;
  }

  // Befehl IF c[0]=0 GOTO pos ausführen
  public void eval(Configuration config) {
    // Inhalt des Akkumulators prüfen
    if (config.getRegister(0) == 0)
      // Sprung ausführen
      config.setICounter(pos - 1);
    else 
      // sonst zum nächsten Befehl
      config.incICounter();
  }

  // Textuelle Repräsentation des Befehls
  public String toString() {
    return "IF c[0] = 0 GOTO " + pos;
  }
}
