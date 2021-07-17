public class Add implements Instruction {
  private int reg;

  public Add (int i) {
    reg = i;
  }

  public void eval (Configuration config) {
    config.setRegister (0, config.getRegister (0) + config.getRegister (reg));
    config.incICounter ();
  }

  public String toString () {
    return "ADD " + reg;
  }
}
