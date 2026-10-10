package locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Locks {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        try {
            lock.lock();
            System.out.println("Lock");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            lock.unlock();
        }
    }
}
