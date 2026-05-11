package org.oops.basics;

import java.util.Date;

public class StudentUse {
    public static void main(String[] args) {

        Student s1 = new Student("Rohit", 1);
        Student s2 = new Student("Ayush", 2);

        System.out.println(Student.getNumStudents());

        int x = 23534, y = 33648;
        System.out.println("HCF of " + x + " and " + y + " : " + findHCF(x, y));
        System.out.println("LCM of " + x + " and " + y + " : " + findLCM(x, y));
    }

    private static int findHCF(int x, int y) {

        for(int i = Math.min(x, y); i > 1; i--){
            if(x % i == 0 && y % i == 0){
                return i;
            }
        }
        return 1; // return 1 if no hcf is found
    }

    private static int findLCM(int x, int y){
        return (x * y) / findHCFByEuclidMethod(x, y);
    }

    private static int findHCFByEuclidMethod(int x, int y){

        // base case, HCF of any number with 0 is the number itself
        if(y == 0) return x;

        return findHCFByEuclidMethod(y, x % y);
    }
}