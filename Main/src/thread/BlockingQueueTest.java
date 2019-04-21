package thread;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
/**
 * Created by Zen9 on 2016/3/16.
 */
public class BlockingQueueTest {
    public static void main(String[] args) {        // 创建容量为1的BlockingQueue
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(1);        // 启动3个生产者线程
        new Producer(blockingQueue).start();
        new Producer(blockingQueue).start();
        new Producer(blockingQueue).start();        // 启动1个消费者线程
        new Consumer(blockingQueue).start();
    }
}

class Producer extends Thread{
    int i;
    private BlockingQueue<String> blockingQueue;
    public Producer(BlockingQueue<String> blockingQueue){
        this.blockingQueue = blockingQueue;
        i=1;
    }
    @Override
    public void run() {
        while(true){
            try{

                blockingQueue.put("生产"+i);
                i++;
            }catch (InterruptedException e){

            }
        }
    }
}

class Consumer extends Thread{
    private BlockingQueue<String> blockingQueue;
    public Consumer(BlockingQueue<String> blockingQueue){
        this.blockingQueue = blockingQueue;
    }
    @Override
    public void run() {
        while(true){
            try{
                Thread.sleep(1100);
                System.out.println(blockingQueue.take());
            }catch (InterruptedException e){

            }
        }
    }
}