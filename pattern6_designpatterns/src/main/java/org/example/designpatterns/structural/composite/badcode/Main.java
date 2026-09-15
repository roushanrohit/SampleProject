package org.example.designpatterns.structural.composite.badcode;

public class Main {

    public static void main(String[] args) {

        File file1 = new File("file1.txt");
        File file2 = new File("file2.txt");

        Folder folder = new Folder("documents");
        folder.add(file1);
        folder.add(file2);

        folder.showDetails();
    }
}
