public class Heartbeat2 implements Runnable { 
    int pulse;
    public Heartbeat2(int p) { pulse = p * 1000; } 
    
    public void run() {
        while(true) {
            try { 
                Thread.sleep(pulse); 
            } 
            catch(InterruptedException e) {} 
            System.out.println("poch");
        } 
    }

    public static void main(String[] args) { 
        Thread t = new Thread(new Heartbeat2(2)); 
        t.start();
    }
}