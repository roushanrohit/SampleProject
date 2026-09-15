package org.example.designpatterns.structural.proxy;

public class Realimage implements Image {

    private String filename;

    public Realimage(String filename){
        this.filename = filename;
        /*
            Real Image is loaded as soon as it is initialized
         */
        loadImageFromDisk(filename);
    }

    private void loadImageFromDisk(String filename) {
        System.out.println("Loading Image from Disk..... " + filename);
    }

    @Override
    public void display() {
        System.out.println("Displaying Image: " + filename);
    }
}
