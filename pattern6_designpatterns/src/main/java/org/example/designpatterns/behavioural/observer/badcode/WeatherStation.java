package org.example.designpatterns.behavioural.observer.badcode;

public class WeatherStation {

    private float temperature;

    // multiple display devices can be connected to a weather station
    private final DisplayDevice displayDevice;

    WeatherStation(DisplayDevice displayDevice){
        this.displayDevice = displayDevice;
    }

    public void setTemperature(float temp){
        this.temperature = temp;
        notifyDevice();
    }

    private void notifyDevice() {
        displayDevice.showTemp(temperature);
    }
}
