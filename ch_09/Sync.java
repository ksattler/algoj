    class Producer implements Runnable {
        int[] buf;

        Producer(int[] b) { buf = b; }

        public void run() { 
            for (int i = 0; i < 10; i++) {
                int v = (int)(Math.random() * 100) + 1;
                System.out.println("--> " + v);
                synchronized(buf) {
                    buf[i] = v;
                }
                try { Thread.sleep(100); } catch(InterruptedException e) {}
            }
        }
    }

    class Consumer implements Runnable {
        int[] buf;

        Consumer(int[] b) { buf = b; }

        public void run() { 
            int n = 0;
            while (n < 10) {
                synchronized(buf) {
                    if (buf[n] != 0) {
                        System.out.println("<-- " + buf[n]);
                        n++;
                    }
                }
                try { Thread.sleep(50); } catch(InterruptedException e) {}
            }
        }
    }

public class Sync {
    public static void main(String[] args) {
        int[] buffer = new int[10];
        Thread t1 = new Thread(new Producer(buffer)); 
        Thread t2 = new Thread(new Consumer(buffer)); 
        t1.start();
        t2.start();
    }
}