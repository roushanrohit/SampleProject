package org.example.designpatterns.behavioural.observer.goodcode;

public class DisplayDevice implements Observer{
    @Override
    public void update(float temp) {
        System.out.println("Temperature on device is: "+ temp);
    }
}
