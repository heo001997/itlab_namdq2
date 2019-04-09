import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TimeOutThread extends Thread {
    private final ReentrantLock lock;
    private final Condition condition;
    private long timeWait;

    public TimeOutThread(long timeOut, TimeUnit timeUnit) {
        this.lock = new ReentrantLock();
        this.condition = lock.newCondition();
    }

    public void run() {
        try {
            lock.lock();
            System.out.println("\t" + Thread.currentThread().getName() + " is running...");
            for (; ; ) {

                //  todoSomething();

                if (timeWait <= 0) {
                    break;
                }
                try {
                    timeWait = condition.await(timeOut, timeUnit);
                } catch (InterruptedException ie) {
                    break;
                }
            }
            System.out.println("\t" + Thread.currentThread().getName() + " is shutdown.");
        } finally {
            lock.unlock();
        }
    }

//    downloadUsingStream("https://speed.hetzner.de/100MB.bin", "100MB.bin");

//    private static void downloadUsingStream(String urlStr, String file) {
//        try {
//            System.out.println("Start download");
//            URL url = new URL(urlStr);
//            BufferedInputStream bis = new BufferedInputStream(url.openStream());
//            FileOutputStream fis = new FileOutputStream(file);
//            byte[] buffer = new byte[1024];
//            int count = 0;
//            while ((count = bis.read(buffer, 0, 1024)) != -1) {
//                fis.write(buffer, 0, count);
//            }
//            fis.close();
//            bis.close();
//            System.out.println("Finish download");
//        } catch (IOException e) {
//            System.out.println("Duong dan tep tin bi loi!");
//        }
//    }
}
