import java.util.concurrent.TimeUnit;

public class NormalThread extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running...");

        TimeOutThread timeOutThread = new TimeOutThread(5000, TimeUnit.MILLISECONDS);
        timeOutThread.start();

        try {
            System.out.println(Thread.currentThread().getName() + " is waiting...");
            timeOutThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " running...");
        System.out.println(Thread.currentThread().getName() + " is shutdown.");
    }
}
