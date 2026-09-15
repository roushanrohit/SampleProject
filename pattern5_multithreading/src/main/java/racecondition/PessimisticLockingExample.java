package racecondition;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
    @Repository
    public interface LoanRepository extends JpaRepository<Loan, Long> {

        @Lock(LockModeType.PESSIMISTIC_WRITE)
        @Query("SELECT l FROM Loan l WHERE l.id = :id")
        Optional<Loan> findByIdForUpdate(@Param("id") Long id);
    }
    @Transactional
    public void repay(Long loanId, BigDecimal amount) {

        // A database lock belongs to a transaction. When the transaction ends, the lock is released.
        // If you don't have a surrounding transaction, JPA creates a transaction only for this query, for rest of the code, no lock is acquired
        Loan loan = repository.findByIdForUpdate(loanId).orElseThrow();

        loan.setOutstandingAmount(loan.getOutstandingAmount().subtract(amount));
        // save is optional if the entity is managed, JPA will flush changes on transaction commit
    }
    What SQL does Hibernate generate?
    SELECT * FROM loan WHERE id = ? FOR UPDATE;
    The FOR UPDATE is what acquires the database row lock.

 */
public class PessimisticLockingExample {

    private int balance = 100;

    // equivalent to pessimistic lock
    private synchronized void withdraw(int amount) {
        if (balance >= amount) {
            try {
                // simulate DB/network delay
                Thread.sleep(100);
            } catch (InterruptedException e) {}

            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " withdrew " + amount + ", balance = " + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + " Failed due to Pessimistic Lock");
        }
    }

    public static void main(String[] args) {

        PessimisticLockingExample raceConditionExample = new PessimisticLockingExample();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> raceConditionExample.withdraw(80));
        executorService.submit(() -> raceConditionExample.withdraw(80));
        executorService.shutdown();
    }
}
