package org.example.designpatterns.structural.flyweight;

/*
    Creating many identical or similar objects in memory heavy applications
    can lead to excessive memory consumption
    By sharing the common state among multiple objects, pattern reduces the memory footprint
    eg: Creating a graphics system rendering a huge number of trees in a forest where each tree
        shares the same image but may vary by position or size
        Google Map using image thumbnails for hotels, petrol pumps etc
 */
public class Main {

    public static void main(String[] args) {

        // 5 Red bullet objects
        for(int i = 0; i < 5; i ++){
            Bullet bullet = new Bullet(i*10, i*12, "Red", 40);
        }

        // 5 green bullet objects
        for(int i = 0; i < 5; i ++){
            Bullet bullet = new Bullet(i*15, i*20, "Green", 50);
        }
    }
}
