package org.oops.basics;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FizzBuzz {

    private int count = 1;
    private Lock lock = new ReentrantLock();

    void printFizz(int n){
        while(true) {
            lock.lock();
            if (count > n) {
                lock.unlock();
                return;
            }
            if (count % 3 == 0 && count % 5 != 0) {
                System.out.println("fizz");
                count++;
            }
            lock.unlock();
        }
    }

    void printBuzz(int n){
        while(true){
            lock.lock();
            if (count > n) {
                lock.unlock();
                return;
            }
            if(count % 3 != 0 && count % 5 == 0){
                System.out.println("buzz");
                count++;
            }
            lock.unlock();
        }
    }

    void printFizzBuzz(int n){
        while(true){
            lock.lock();
            if (count > n) {
                lock.unlock();
                return;
            }
            if(count % 3 == 0 && count % 5 == 0){
                System.out.println("fizzbuzz");
                count++;
            }
            lock.unlock();
        }
    }

    void print(int n){
        while(true){
            lock.lock();
            if (count > n) {
                lock.unlock();
                return;
            }
            if(count % 3 != 0 && count % 5 != 0){
                System.out.println(count);
                count++;
            }
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        int n = 150000;
        FizzBuzz fizzBuzz = new FizzBuzz();

        Thread fizzThread = new Thread(() -> fizzBuzz.printFizz(n));
        Thread buzzThread = new Thread(() -> fizzBuzz.printBuzz(n));
        Thread fizzBuzzThread = new Thread(() -> fizzBuzz.printFizzBuzz(n));
        Thread numberThread = new Thread(() -> fizzBuzz.print(n));

        fizzThread.start();
        buzzThread.start();
        fizzBuzzThread.start();
        numberThread.start();

        fizzThread.join();
        buzzThread.join();
        fizzBuzzThread.join();
        numberThread.join();
    }
}
