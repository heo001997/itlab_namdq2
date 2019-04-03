public class TimeOutThread extends Thread {
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " is running...");
            try {
                Thread.sleep(0);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt(); // very important
                break;
            }
        }
        System.out.println("Shutting down thread");
    }
}
