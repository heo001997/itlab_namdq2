public class Main {
    public static void main(String[] args) throws InterruptedException {
        TimeOutThread timeOutThread = new TimeOutThread();
        timeOutThread.start();
        timeOutThread.join(2000);
        timeOutThread.interrupt();
    }
}
