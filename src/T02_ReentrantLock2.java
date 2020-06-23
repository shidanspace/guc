import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shidan
 * @version 1.0
 */
public class T02_ReentrantLock2 {
    static int NUM = 10;
    Lock lock = new ReentrantLock();

    void m1() {
            try {
                lock.lock();
                for (int i = 0; i < NUM; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(i);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }


    }

    void m2() {

        try {
            lock.lock();
            System.out.println("m2...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();

        }
    }

    public static void main(String[] args) {

        T02_ReentrantLock2 r1 = new T02_ReentrantLock2();
        new Thread(r1::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r1::m2).start();


    }


}
