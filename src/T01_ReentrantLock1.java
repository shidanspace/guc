import java.util.concurrent.TimeUnit;

/**
 * @author shidan
 * @version 1.0
 */
public class T01_ReentrantLock1 {
    static int NUM=10;

    synchronized void m1(){
        for (int i = 0; i <NUM ; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);

            if(i==2) m2();
        }
    }

    synchronized void m2(){
        System.out.println("m2...");
    }

    public static void main(String[] args) {

        T01_ReentrantLock1 r1=new T01_ReentrantLock1();
        new Thread(r1::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //new Thread(r1::m2).start();


    }






}
