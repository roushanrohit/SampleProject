package org.example.designpatterns.structural.composite.goodcode;

public class Shortcut implements FileSystemComponent {

    private String name;

    public Shortcut(String name){
        this.name = name;
    }

    /*
        showDetails() for a shortcut(leaf nodes) works differently than
        showDetails() for a folder(non-leaf nodes)
     */
    public void showDetails() {
        System.out.println("Shortcut : " + name);
    }
}
