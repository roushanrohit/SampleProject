package org.oops.basics;

public class DeadLockExample {

    public static void main(String[] args) {

        System.out.println("Entering deadlock: ");
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("This statement will never be executed.");
    }
}
