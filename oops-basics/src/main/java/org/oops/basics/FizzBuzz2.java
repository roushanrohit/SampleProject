package org.oops.basics;

public class FizzBuzz2 {

    private int count = 1;

    synchronized void printFizz(int n) throws InterruptedException {
        while(true) {
            while (count <= n && (count % 3 != 0 || count % 5 == 0)) wait();
            if (count > n) {
                notifyAll();
                return;
            }
            System.out.println("fizz");
            count++;
            notifyAll();
        }
    }

    synchronized void printBuzz(int n) throws InterruptedException {
        while(true){
            while (count <= n && (count % 5 != 0 || count % 3 == 0)) wait();
            if (count > n) {
                notifyAll();
                return;
            }
            System.out.println("buzz");
            count++;
            notifyAll();
        }
    }

    synchronized void printFizzBuzz(int n) throws InterruptedException {
        while(true){
            while (count <= n && (count % 3 != 0 || count % 5 != 0)) wait();
            if (count > n) {
                notifyAll();
                return;
            }
            System.out.println("fizzbuzz");
            count++;
            notifyAll();
        }
    }

    synchronized void print(int n) throws InterruptedException {
        while(true){
            while (count <= n && (count % 3 == 0 || count % 5 == 0)) wait();
            if (count > n) {
                notifyAll();
                return;
            }
            System.out.println(count);
            count++;
            notifyAll();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        int n = 15;
        FizzBuzz2 fizzBuzz = new FizzBuzz2();

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
