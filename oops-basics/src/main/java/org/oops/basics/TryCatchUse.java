package org.oops.basics;

public class TryCatchUse {

    public static void main(String[] args) {

        System.out.println("Value returned: " + returnAValue());
        try {
            throwAnException();
        } catch(Exception exception) {
            System.out.println("Exception caught: " + exception);
        }
        try {
            throwAnException2();
        } catch(Exception exception) {
            System.out.println("Exception caught: " + exception);
        }
        /*
            The finally block will always be executed even if there is return statement
            inside the try block, before the function returns, code inside the finally block will
            be executed

            Only if we terminate the JVM itself by calling System.exit(), finally block will not be executed
            or if the current thread is killed or interrupted
         */
        try {
            //return;
            System.exit(0);
        } finally {
            System.out.println("inside finally block");
        }

        /*
            The Java compiler does not always treat method calls like System.exit() as guaranteed to terminate the program.
            Since it doesn't analyze the body of the method, it doesn't flag the unreachable code -- no compilation error.
         */
        System.out.println("......");
    }

    private static void throwAnException2() {
        try {
            throw new Exception();
        }
        catch(Exception e) {
            throw new ArithmeticException();
        } finally {

            /*
                exception of finally block will override the exception of catch block
                only the finally block's exception will be propagated
             */
            throw new ClassCastException();
        }
    }

    private static void throwAnException() {
        try {
            throw new ClassCastException();
        } finally {

            /*
                exception of finally block will override the exception of try block
                only the finally block's exception will be propagated
             */
            throw new ArithmeticException();
        }
    }

    private static int returnAValue() {
        try {
            return 0;
        } finally {
            System.out.println("inside finally block of returnAValue method");

            /*
                return of finally block will override the return of try block
             */
            return 1;
        }
    }
}
