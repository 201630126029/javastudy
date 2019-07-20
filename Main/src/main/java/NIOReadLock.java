/* NIOReadLock.java */

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.FileChannel.MapMode;

public class NIOReadLock {
    private static RandomAccessFile raf;

    public static void main(String[] args) throws Exception {
        raf = new RandomAccessFile("D:/tmp/data.dat", "rw");
        FileChannel fc = raf.getChannel();
        MappedByteBuffer mbb = fc.map(MapMode.READ_WRITE, 0, 1024);
        FileLock flock;

        for(int i=0;i<26;i++){
            //上锁
            flock=fc.lock();
            System.out.println( System.currentTimeMillis() +  ":read:" + (char)mbb.get(i));
            //释放锁
            flock.release();
            Thread.sleep(1000);
        }
    }
}
