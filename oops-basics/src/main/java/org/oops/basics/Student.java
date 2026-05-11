package org.oops.basics;

public class Student {

    // one copy per class -- if we don't make it private anyone can change numStudents which is not correct
    // it is meant to be incremented/changed only inside the constructor
    private static int numStudents;
    private String name;

    // once initialized cannot be changed --either here itself or inside the constructor -- read only field
    private final int rollNo;

    Student(String name, int rollNo){
        this.name = name;
        this.rollNo = rollNo;
        numStudents++;
    }

    // static method to access numStudents
    public static int getNumStudents() {
        return numStudents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNo() {
        return rollNo;
    }
}
