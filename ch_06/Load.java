public class Load implements Instruction {
  private int reg; // Register

  public Load(int i) {
    reg = i;
  }

  // Befehl LOAD ausführen
  public void eval(Configuration config) {
    // Akkumulator laden
    config.setRegister(0, config.getRegister(reg));
    // Befehlszähler inkrementieren
    config.incICounter();
  }

  // Textuelle Repräsentation des Befehls
  public String toString() {
    return "LOAD " + reg;
  }
}
