package org.example.designpatterns.structural.flyweight;

public class Bullet {

    // extrinsic property unique for every bullet
    private final int x;
    private final int y;
    private final int velocity;

    // all the intrinsic properties are held by the bullet type object
    private final BulletType bulletType;

    public Bullet(int x, int y, String color, int velocity){
        this.x = x;
        this.y = y;
        this.bulletType = BulletTypeFactory.getBulletType(color);
        this.velocity = velocity;
        display();
    }

    public void display(){
        System.out.println("bullet at coordinates " + x + " " + y + " and velocity: " + velocity);
    }
}
