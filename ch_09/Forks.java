class Forks {
  // 5 Gabeln: true -> frei, false -> belegt
  // Beginn: alle Gabeln sind verfügbar
  private boolean forks[] = {
    true, true, true, true, true
  };

  // Testet, ob Gabel verfügbar ist
  public boolean isAvailable(int f) {
    return forks[f];
  }

  // Aufnehmen der Gabel
  void take(int f) {
    forks[f] = false;
  }

  // Ablegen der Gabel
  void put(int f) {
    forks[f] = true;
  }
}
