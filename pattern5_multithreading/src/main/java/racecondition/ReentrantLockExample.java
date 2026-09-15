package racecondition;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/*
    is ReentrantLock (Manual Lock) same as Pessimistic Lock(synchronized method) ?
    yes—they are very similar. Both ensure: only one thread/transaction can enter the critical section at a time. Others wait.
    However, they operate at different levels.
    ReentrantLock:
        Java/JVM level
        Locks Java objects
        Only works within one JVM
        Doesn't involve the database
    Pessimistic Lock:
        Database level
        Locks database rows
        Works across all application instances
        Database manages the lock
    Everyone must respect the database lock.
    If your application has: 1 instance, 1 JVM, both will appear to behave similarly. But if tomorrow Kubernetes scales
    your service to 5 pods, only the database lock continues to work correctly.
 */
public class ReentrantLockExample {

    int balance = 100;
    ReentrantLock lock = new ReentrantLock();

    private void withdraw(int amount) {
        lock.lock();
        try {
            if (balance >= amount) {
                Thread.sleep(100);
                balance -= amount;
                System.out.println(Thread.currentThread().getName() + " success");
            } else {
                System.out.println(Thread.currentThread().getName() + " failed");
            }
        } catch (InterruptedException e) {
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        ReentrantLockExample reentrantLockExample = new ReentrantLockExample();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> reentrantLockExample.withdraw(80));
        executorService.submit(() -> reentrantLockExample.withdraw(80));
        executorService.shutdown();
    }
}
