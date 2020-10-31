public class FibonacciPool {
    public static void main(String[] args) { 
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 50; i++) {
            Runnable task = new Fibonacci(i, 
                (long)(Math.random() * 50) + 1);
            executor.execute(task);
        }
        executor.shutdown();
    }
}