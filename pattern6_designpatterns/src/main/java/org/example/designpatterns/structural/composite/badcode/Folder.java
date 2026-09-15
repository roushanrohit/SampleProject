package org.example.designpatterns.structural.composite.badcode;

import java.util.ArrayList;
import java.util.List;

public class Folder {

    private String name;

    /*
        This code does not treat files and folders uniformly.
     */
    private List<File> files;

    public Folder(String name){
        this.name = name;
        files = new ArrayList<>();
    }

    public void add(File file){
        files.add(file);
    }

    public void showDetails(){
        System.out.println("Folder: " + name);
        for(File file : files){
            file.showDetails();
        }
    }
}
