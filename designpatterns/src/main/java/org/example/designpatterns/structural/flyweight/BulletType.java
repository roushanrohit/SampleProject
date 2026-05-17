package org.example.designpatterns.structural.flyweight;

/*
    Flyweight class
 */
public class BulletType {

    private final String color;
    private String imageData;
    private final String imageFileName;

    public BulletType(String color, String imageFileName){
        this.color = color;
        this.imageFileName = imageFileName;
        loadImageFromDisk(imageFileName);
    }

    private void loadImageFromDisk(String imageFileName) {
        System.out.println("Loading image from disk: " + imageFileName);
        imageData = "Image Data";
    }
}
