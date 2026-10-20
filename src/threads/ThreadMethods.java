package threads;

class Task {

    Object lock1 = new Object();
    Object lock2 = new Object();

    void m1() {
        System.out.println("Thread entered inside m1");
        synchronized (lock1) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Thread exiting m1");
    }

    void m2() {
        System.out.println("Thread entered inside m2");
        synchronized (lock2) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Thread exiting m2");
    }
}

class StaticTask {
    public static void check1() {
        synchronized (StaticTask.class) {
            System.out.println("Inside static synchronized block of StaticTask - Check1");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Exiting static synchronized block of StaticTask - Check1");
        }
    }

    public static void check2() {
        synchronized (StaticTask.class) {
            System.out.println("Inside static synchronized block of StaticTask - Check2");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Exiting static synchronized block of StaticTask - Check2"); 
        }
    }

}

class StaticAndNormalTask{
    public static void check1() {
        synchronized (StaticTask.class) {
            System.out.println("Inside static synchronized block of StaticAndNormalTask - Check1");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Exiting static synchronized block of StaticAndNormalTask - Check1");
        }
    }

    public void check2() {
        synchronized (this) {
            System.out.println("Inside non-static synchronized block of StaticAndNormalTask - Check2");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Exiting non-static synchronized block of StaticAndNormalTask - Check2");
        }
    }
}

public class ThreadMethods {

    public static void main(String[] args) throws InterruptedException {
        // System.out.println("Main thread works");

        // Thread t1 = new Thread(() -> {
        // try {
        // Thread.sleep(2000);
        // } catch (InterruptedException e) {
        // }
        // System.out.println("Thread - 0");
        // });

        // t1.start();
        // t1.join(); // Main thread waits until the t1 threads finishes its execution
        // // t1.interrupt();
        // System.out.println("Main thread ends");

        // Task task = new Task(); // If thread1 get the lock of TASK first then Thread2
        // needs to wait until Thread1 exits the synchronized method,
        // // but why ? Thread2 is trying to access different method
        // // Thread2 is trying to access different method but Thread1 got the lock
        // first and the LOCK is the Class/Object level Lock so Thread1 got the Task
        // object lock while calling m1()
        // // so until Thread1 exits the synchronized method m1(), Thread2 cannot enter
        // the synchronized method m2() because it needs the same Task object lock.
        // Thread t1 = new Thread(() -> task.m1());
        // Thread t2 = new Thread(() -> task.m2());
        // t1.start();
        // t2.start();

        // Task task2 = new Task(); // Another instance of Task, different lock
        // Task task3 = new Task(); // Yet another instance of Task, different lock
        // Thread t3 = new Thread(() -> task2.m1());
        // Thread t4 = new Thread(() -> task3.m2());
        // t3.start();
        // t4.start();

        // Custom locks
        // Task task = new Task();
        // Thread t1 = new Thread(() -> task.m1());
        // Thread t2 = new Thread(() -> task.m2());
        // t1.start();
        // t2.start();

        // Thread t1 = new Thread(() -> StaticTask.check1());
        // Thread t2 = new Thread(() -> StaticTask.check2());
        // t1.start();
        // t2.start();

        StaticAndNormalTask task = new StaticAndNormalTask();
        Thread t1 = new Thread(() -> StaticAndNormalTask.check1());
        Thread t2 = new Thread(() -> task.check2());
        t1.start();
        t2.start();
    }
}
