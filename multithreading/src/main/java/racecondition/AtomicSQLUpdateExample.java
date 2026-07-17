package racecondition;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicSQLUpdateExample {

    AtomicInteger balance = new AtomicInteger(100);

    private void withdraw(int amount) {

        while(true){
            int current = balance.get();
            if (current < amount) {
                System.out.println(Thread.currentThread().getName() + " failed");
                return;
            }
            if (balance.compareAndSet(current, current - amount)) {
                System.out.println(Thread.currentThread().getName() + " success, balance = " + balance.get());
                return;
            }
        }
    }

    public static void main(String[] args) {

        AtomicSQLUpdateExample raceConditionExample = new AtomicSQLUpdateExample();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(() -> raceConditionExample.withdraw(80));
        executorService.submit(() -> raceConditionExample.withdraw(80));
        executorService.submit(() -> raceConditionExample.withdraw(70));
        executorService.shutdown();
    }
}
