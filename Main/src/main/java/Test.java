import java.util.concurrent.atomic.AtomicReference;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        SpinLock lock = new SpinLock();
        MyRunnable runnable = new MyRunnable(lock);;
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(runnable);
            t.start();
        }
        if (Thread.activeCount() > 2){
            Thread.yield();
        }
        if (runnable!=null){
            System.out.println(runnable.sum);
        }
    }
}

class MyRunnable implements Runnable{
    int sum;
    /**
     * 将锁放在创建线程的Runnable对象里面
     */
    private SpinLock lock;

    public MyRunnable(SpinLock lock) {
        this.lock = lock;
        sum=0;
    }

    @Override
    public void run() {
        this.lock.lock();
        this.lock.lock();
        sum++;
        this.lock.unLock();
        this.lock.unLock();
    }
}


/**
 * 可重入的自旋锁  (重新进入 不会出现死锁)
 */
class SpinLock {
    //持有自旋锁的线程对象
    AtomicReference<Thread> owner = new AtomicReference<>();
    //用一个计数器 来做 重入锁获取次数的计数
    private int count;

    public void lock() {
        Thread cur = Thread.currentThread();
        //同一线程可以多次加锁
        if (cur == owner.get()) {
            count++;
            return;
        }
        //这里耗时很严重
        while (!owner.compareAndSet(null, cur)) {
        }
    }

    public void unLock() {
        Thread cur = Thread.currentThread();
        if (cur == owner.get()) {
            if (count > 0) {
                count--;
            } else {
                owner.compareAndSet(cur, null);
            }
        }
    }
}