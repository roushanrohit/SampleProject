package racecondition;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/*
    Does optimistic locking require a transaction?
    Strictly speaking:
    The version check happens during the UPDATE.
    The UPDATE itself is always executed in a transaction (JPA starts one if necessary).
    So optimistic locking can still detect conflicts without you explicitly annotating the service with @Transactional, because repository.save() runs in a transaction.
    However, best practice is to wrap the entire read-modify-write sequence in a single @Transactional method. That way:
    You read the entity.
    JPA keeps it in the persistence context (managed state).
    You modify it.
    At commit, Hibernate performs the version check and issues the UPDATE ... WHERE version = ?.

    key difference
    Pessimistic locking: @Transactional is essential because otherwise the lock disappears immediately.
    Optimistic locking: @Transactional is strongly recommended because it keeps the entire read-modify-write operation
                        as one transaction and lets JPA manage the entity correctly. The optimistic lock itself is enforced
                        by the version check in the UPDATE, not by holding a database lock.
 */
public class OptimisticLockingExample {

    private int balance = 100;
    AtomicInteger version = new AtomicInteger(0);

    // equivalent to optimistic lock
    private void withdraw(int amount) {

        // both are allowed to read simultaneously -- both will read balance 100 and version 0
        int currentVersion = version.get();
        int currentBalance = balance;

        if (currentBalance < amount) {
            System.out.println("Insufficient balance");
            return;
        }

        try {
            // simulate DB/network delay
            Thread.sleep(100);
        } catch (InterruptedException e) {}

        /*
            Only update the version to 1 if it is still 0 -- compareAndSet is atomic
            But wait... JPA uses a normal Long for @Version!
            Because the database provides the atomicity, not Java.
            Hibernate generates SQL like:
            UPDATE loan SET balance = ?, version = version + 1 WHERE id = ? AND version = ?;
        */
        if (version.compareAndSet(currentVersion, currentVersion + 1)) {
            balance = currentBalance - amount;
            System.out.println(Thread.currentThread().getName() + " success");
        } else {
            // Someone modified this object while you were processing
            System.out.println(Thread.currentThread().getName() + " OptimisticLockException");
        }
    }

    public static void main(String[] args) {

        OptimisticLockingExample raceConditionExample = new OptimisticLockingExample();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> raceConditionExample.withdraw(80));
        executorService.submit(() -> raceConditionExample.withdraw(80));
        executorService.shutdown();
    }
}
