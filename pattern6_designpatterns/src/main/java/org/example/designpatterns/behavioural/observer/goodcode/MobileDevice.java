package org.example.designpatterns.behavioural.observer.goodcode;

public class MobileDevice implements Observer{
    @Override
    public void update(float temp) {
        System.out.println("Temperature on mobile is: "+ temp);
    }
}
