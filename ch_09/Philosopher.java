class Philosopher extends Thread {
  private Forks forks;     // Gabeln
  int leftFork, rightFork; // die linke und rechte Gabel
  
  // Konstruktor: initialisiert alle  
  // Attribute und startet den Thread
  public Philosopher(Forks f, int left, int right) {
    leftFork = left;
    rightFork = right;
    forks = f;
    start();
  }
  
  // Lebenslauf eines Philosophen
  public void run() {
    // Anfangszustand: "Denkend"
    System.out.println("Denken...  #" + leftFork);
    while (true) {
      // die Gabeln sind geschützt
      synchronized(forks) {
        // Warten, bis beide Gabeln verfügbar sind
        while (!forks.isAvailable(leftFork) || 
        !forks.isAvailable(rightFork)) {
          // Gabeln sind belegt: Zustand ist
          // "Hungrig" --> er muss warten
          System.out.println("Hungrig... #" + leftFork);
          try {
            forks.wait();
          } catch (InterruptedException exc) { }
        }
        // beide Gabeln aufnehmen
        forks.take(leftFork);
        forks.take(rightFork);
      }
      
      // jetzt kann er eine Weile essen
      try {
        System.out.println("Essen...   #" + leftFork);
        sleep((int) (Math.random () * 3000.0));
      } catch (InterruptedException exc) { }
      
      // Gabeln sind wieder geschützt
      synchronized(forks) {
        // Gabeln niederlegen
        forks.put(leftFork);
        forks.put(rightFork);
        // alle wartenden Philosophen aufwecken
        forks.notifyAll();
      }
      
      // wieder eine Weile nachdenken ...
      try {
        System.out.println("Denken...  #" + leftFork);
        sleep((int) (Math.random() * 5000.0));
      } catch (InterruptedException exc) { }
    }
  }
}
