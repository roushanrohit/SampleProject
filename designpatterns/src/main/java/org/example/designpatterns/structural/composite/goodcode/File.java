package org.example.designpatterns.structural.composite.goodcode;

public class File implements FileSystemComponent {

    private String name;

    public File(String name){
        this.name = name;
    }

    /*
        showDetails() for a file(leaf nodes) works differently than
        showDetails() for a folder(non-leaf nodes)
     */
    public void showDetails() {
        System.out.println("File : " + name);
    }
}
