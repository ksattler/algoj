public class Fibonacci implements Runnable { 
    long fi; // zu berechnende Fibonacci-Zahl
    int num; // Nummer des Threads

    public Fibonacci(int n, long f) { num = n; fi = f; } 
    
    long fibo(long f) {
        if (f < 2) return 1;
        else return fibo(f-1) + fibo(f-2); 
    }
    
    public void run() {
        System.out.println("Starte #" + num);
        long res = fibo(fi); 
        System.out.println("#" + num + "  Fibonacci(" + 
            fi + ") = " + res);
    } 

    public static void main(String[] args) { 
        Thread[] threads = new Thread[10]; 
        for (int i = 0; i < 10; i++)
            threads[i] = new Thread(new Fibonacci(i, 
                (long)(Math.random() * 50) + 1));

        for (int i = 0; i < 10; i++)
            threads[i].start(); 
    }
}