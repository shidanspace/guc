import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author shidan
 * @version 1.0
 * Atomic:cas
 * Sync:重量锁
 * LongAdder:分段锁
 *
 * Atomic:100000000 time 4663
 * Sync:100000000 time 10832
 * LongAdder:100000000 time 1300
 */
public class T02_AtomicVsSyncVsLongAdder {
    static int NUM = 1000;
    static int M = 100000;
    static AtomicLong count1 = new AtomicLong(0L);
    static long count2 = 0L;
    static LongAdder count3 = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[NUM];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int k = 0; k < M; k++) count1.incrementAndGet();
            });

        }

        long start = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        long end = System.currentTimeMillis();
        System.out.println("Atomic:" + count1.get() + " time " + (end - start));


        Object lock = new Object();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int k = 0; k < M; k++) {
                    synchronized (lock) {
                        count2++;
                    }
                }
            });

        }

        start = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        end = System.currentTimeMillis();
        System.out.println("Sync:" + count2 + " time " + (end - start));


        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int k = 0; k < M; k++) count3.increment();
            });

        }

        start = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        end = System.currentTimeMillis();
        System.out.println("LongAdder:" + count3 + " time " + (end - start));


    }
}
