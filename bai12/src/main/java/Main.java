import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TimeOutThread timeOutThread = new TimeOutThread(5000, TimeUnit.MILLISECONDS);
        timeOutThread.start();
        timeOutThread.join();

        System.out.println("resume Main");
    }
}
