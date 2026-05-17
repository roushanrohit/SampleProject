package org.example.designpatterns.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

public class BulletTypeFactory {

    private static final Map<String, BulletType> bulletTypes = new HashMap<>();

    public static BulletType getBulletType(String color){
        if(!bulletTypes.containsKey(color)){
            bulletTypes.put(color, new BulletType(color,color+"_bullet.jpg"));
        }
        return bulletTypes.get(color);
    }
}
