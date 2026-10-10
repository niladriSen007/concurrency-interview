package locks;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

class NewResource {
    ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private int value = 0;
    StampedLock lock = new StampedLock();

    public void read() {
        long timestamp = lock.readLock();
        try {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " reads value as " + value);
        } catch (Exception e) {
        } finally {
            lock.unlockRead(timestamp);
        }
    }


    public void write(int newValue) {
        long timestamp = lock.writeLock();
        try {
            Thread.sleep(2000);
            value = newValue;
            System.out.println(Thread.currentThread().getName() + " writes value as " + value);
        } catch (Exception e) {
        } finally {
            lock.unlockWrite(timestamp);
        }
    }

    public void optimisticRead(){
        long stamp = lock.tryOptimisticRead();
        try {
            Thread.sleep(2000);
            if (lock.validate(stamp)) {
                System.out.println(Thread.currentThread().getName() + " optimistically reads value as " + value);
            } else {
                System.out.println(Thread.currentThread().getName() + " could not read optimistically, falling back to read lock");
                read();
            }
        } catch (Exception e) {
        }
    }
}

public class StampedLockDemo {
    public static void main(String[] args) {
        NewResource nw = new NewResource();

        Thread r1 = new Thread(() -> nw.read());
        Thread r2 = new Thread(() -> nw.read());
        Thread r3 = new Thread(() -> nw.read());
        Thread r4 = new Thread(() -> nw.read());

        Thread w1 = new Thread(() -> nw.write(1));
        Thread w2 = new Thread(() -> nw.write(2));
        Thread w3 = new Thread(() -> nw.write(3));
        Thread w4 = new Thread(() -> nw.write(4));

        r1.start();
        r2.start();
        r3.start();
        r4.start();

        w1.start();
        w2.start();
        w3.start();
        w4.start();
    }
}
