package racecondition;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreExample {

    int balance = 100;
    Semaphore semaphore = new Semaphore(2);
    void withdraw(int amount) throws Exception {
        semaphore.acquire();
        try {
            if (balance >= amount) {
                Thread.sleep(100);
                balance -= amount;
                System.out.println(Thread.currentThread().getName() + " success");
            } else {
                System.out.println(Thread.currentThread().getName() + " failed");
            }
        } finally {
            semaphore.release();
        }
    }

    public static void main(String[] args) {

        SemaphoreExample semaphoreExample = new SemaphoreExample();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> {
            try {
                semaphoreExample.withdraw(80);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        executorService.submit(() -> {
            try {
                semaphoreExample.withdraw(80);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        executorService.shutdown();
    }
}
