public class Machine {
  private Configuration config = null;
  private Instruction[] program = null;

  public Machine() {
    config = new Configuration();
  }

  public void setProgram(Instruction[] prog) {
    program = new Instruction[prog.length];
    System.arraycopy(prog, 0, program, 0, prog.length);
  }

  public void run() {
    while (! program[config.getICounter()].toString().equals("END"))
      program[config.getICounter()].eval(config);
  }

  public void printConfiguration() {
    System.out.println(config);
  }

  public Configuration getConfiguration() {
    return config;
  }

  public static void main(String[] args) {
    Instruction[] prog = {
      new Load(1), new Div(2), new Mult(2), new Store(3),
      new Load(1), new Sub(3), new Store(3), new End()
    };

    Machine machine = new Machine();
    machine.setProgram(prog);
    machine.getConfiguration().init();
    machine.getConfiguration().setRegister(1, 32);
    machine.getConfiguration().setRegister(2, 5);
    machine.run();
    machine.printConfiguration();
  }
}
