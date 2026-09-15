package org.example.designpatterns.behavioural.observer.goodcode;

import java.util.ArrayList;
import java.util.List;

public class WeatherStation implements Subject{

    private float temperature;
    private List<Observer> observers;

    WeatherStation(float temperature){
        this.temperature = temperature;
        observers = new ArrayList<>();
    }

    public void setTemperature(float temperature){
        this.temperature = temperature;
        notifyObservers();
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(Observer obs : observers){

            // different observers can have different implementations of the update method
            obs.update(temperature);
        }
    }
}
