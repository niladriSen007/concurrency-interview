package locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class SharedResource {
    java.util.concurrent.locks.ReadWriteLock lock = new ReentrantReadWriteLock(true);
    Lock readLock = lock.readLock();
    Lock writeLock = lock.writeLock();

    private int value = 0;

    public void read() {
        readLock.lock();
        try {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " reads value as " + value);
        } catch (Exception e) {
        } finally {
            readLock.unlock();
        }
    }

    public void write(int newVal) {
        writeLock.lock();
        try {
            Thread.sleep(3000);
            value = newVal;
            System.out.println(Thread.currentThread().getName() + " changes value to " + value);
        } catch (Exception e) {
        } finally {
            writeLock.unlock();
        }
    }
}

public class ReadWriteLock {
    public static void main(String[] args) {
        SharedResource s1 = new SharedResource();

        Thread r1 = new Thread(() -> s1.read());
        Thread r2 = new Thread(() -> s1.read());
        Thread r3 = new Thread(() -> s1.read());
        Thread r4 = new Thread(() -> s1.read());

        Thread w1 = new Thread(() -> s1.write(2));
        Thread w2 = new Thread(() -> s1.write(3));
        Thread w3 = new Thread(() -> s1.write(4));
        Thread w4 = new Thread(() -> s1.write(5));

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
