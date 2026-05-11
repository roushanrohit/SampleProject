package org.oops.basics;

import java.util.Date;
import java.util.concurrent.Semaphore;

public class FizzBuzz3 {

    private int count = 1;
    private final Semaphore semaphore = new Semaphore(5);

    void printFizz(int n) throws InterruptedException {
        while(true) {
            semaphore.acquire();
            if (count > n) {
                semaphore.release();
                return;
            }
            if (count % 3 == 0 && count % 5 != 0) {
                System.out.println("fizz");
                count++;
            }
            semaphore.release();
        }
    }

    void printBuzz(int n) throws InterruptedException {
        while(true){
            semaphore.acquire();
            if (count > n) {
                semaphore.release();
                return;
            }
            if(count % 3 != 0 && count % 5 == 0){
                System.out.println("buzz");
                count++;
            }
            semaphore.release();
        }
    }

    void printFizzBuzz(int n) throws InterruptedException {
        while(true){
            semaphore.acquire();
            if (count > n) {
                semaphore.release();
                return;
            }
            if(count % 3 == 0 && count % 5 == 0){
                System.out.println("fizzbuzz");
                count++;
            }
            semaphore.release();
        }
    }

    void print(int n) throws InterruptedException {
        while(true){
            semaphore.acquire();
            if (count > n) {
                semaphore.release();
                return;
            }
            if(count % 3 != 0 && count % 5 != 0){
                System.out.println(count);
                count++;
            }
            semaphore.release();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        int n = 150000;
        FizzBuzz3 fizzBuzz = new FizzBuzz3();

        Thread fizzThread = new Thread(() -> {
            try {
                fizzBuzz.printFizz(n);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread buzzThread = new Thread(() -> {
            try {
                fizzBuzz.printBuzz(n);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread fizzBuzzThread = new Thread(() -> {
            try {
                fizzBuzz.printFizzBuzz(n);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread numberThread = new Thread(() -> {
            try {
                fizzBuzz.print(n);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Date startTime = new Date();
        fizzThread.start();
        buzzThread.start();
        fizzBuzzThread.start();
        numberThread.start();

        fizzThread.join();
        buzzThread.join();
        fizzBuzzThread.join();
        numberThread.join();
        Date endTime = new Date();
        System.out.println("Time taken: " + (endTime.getTime() - startTime.getTime()) + " ms");
    }
}
