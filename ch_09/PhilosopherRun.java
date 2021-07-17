public class PhilosopherRun {
    public static void main(String[] args) {
        // Gabeln erzeugen
        Forks forks = new Forks();

        // 5 Philosophen erzeugen und ihnen ihre
        // Gabeln zuweisen
        Philosopher[] philosophers = new Philosopher[5];
        for (int i = 0; i < 5; i++)
            philosophers[i] = new Philosopher(forks, i, (i+1) % 5);
    }
}
