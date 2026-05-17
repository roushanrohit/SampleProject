package org.example.designpatterns.structural.composite.goodcode;

/*
    Composite pattern allows to treat individual objects and
    composition of objects uniformly by representing part whole hierarchies

    1. Treat File and Folder objects uniformly even though they are structurally different.
    2. We can add new components - Shortcut. The code is scalable and maintainable.
 */
public class Main {

    public static void main(String[] args) {

        File file1 = new File("file1.txt");
        File file2 = new File("file2.txt");

        Folder folder = new Folder("documents");
        folder.add(file1);
        folder.add(file2);

        Folder subFolder = new Folder("subfolder");
        File file3 = new File("file3.txt");
        subFolder.add(file3);

        // add subfolder to folder
        folder.add(subFolder);

        File file4 = new File("file4.txt");
        folder.add(file4);

        Shortcut shortcut = new Shortcut("Desktop.ini");
        folder.add(shortcut);

        // showDetails() in a DFS fashion
        folder.showDetails();
    }
}
