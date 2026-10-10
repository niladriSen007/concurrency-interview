package locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Resource {
    Lock lock = new ReentrantLock();

    void f1() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " entered");
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println(Thread.currentThread().getName() + " exited");
        } finally {
            lock.unlock();
        }
    }
}

public class LockPractice {
    public static void main(String[] args) {
        Resource resource = new Resource();

        Thread t1 = new Thread(() -> resource.f1());
        Thread t2 = new Thread(() -> resource.f1());
        Thread t3 = new Thread(() -> resource.f1());

        t1.start();
        t2.start();
        t3.start();
    }
}
