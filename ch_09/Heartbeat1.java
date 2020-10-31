public class Heartbeat1 extends Thread {
    int pulse = 1000;
    
    public Heartbeat1(int p) { setPulse(p); }
    
    public void setPulse(int p) { pulse = p * 1000; }
    
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
        Heartbeat1 t = new Heartbeat1(2); 
        t.start();
    }
}