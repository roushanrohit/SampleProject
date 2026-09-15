package org.example.designpatterns.structural.composite.goodcode;

import java.util.ArrayList;
import java.util.List;

public class Folder implements FileSystemComponent {

    private String name;

    private List<FileSystemComponent> fileSystemComponents;

    public Folder(String name){
        this.name = name;
        fileSystemComponents = new ArrayList<>();
    }

    public void add(FileSystemComponent fileSystemComponent){
        fileSystemComponents.add(fileSystemComponent);
    }

    /*
        showDetails() for a file(leaf nodes) works differently than
        showDetails() for a folder(non-leaf nodes)
     */
    public void showDetails(){
        System.out.println("Folder: " + name);
        for(FileSystemComponent component : fileSystemComponents){
            component.showDetails();
        }
    }
}
