import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;


public class Main {
    public static AtomicInteger race=new AtomicInteger(0);
    private static final int THREADS_COUNTS=20;
    public static void main(String[] args){
        Thread[] threads= new Thread[THREADS_COUNTS];
        for (int i = 0; i < THREADS_COUNTS; i++) {
            threads[i]=new Thread(()->{
                for (int i1 = 0; i1 < 1000; i1++) {
                    increase();
                }
            });
            threads[i].start();
        }
        while (Thread.activeCount() > 2){
            System.out.println(Thread.activeCount());
            Thread.currentThread().getThreadGroup().list();
            Thread.yield();
        }
        System.out.println(race);
    }

    public static void increase(){
        race.incrementAndGet();
    }
}


