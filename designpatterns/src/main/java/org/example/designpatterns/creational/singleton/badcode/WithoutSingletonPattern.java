package org.example.designpatterns.creational.singleton.badcode;

public class WithoutSingletonPattern {

    public static void main(String[] args) {

        AppSettings appSettings = new AppSettings();
        appSettings.setApiKey("1234");
        AppSettings appSettings1 = new AppSettings();
        appSettings1.setApiKey("5678");

        System.out.println(appSettings.getApiKey());
        System.out.println(appSettings1.getApiKey());
        System.out.println(appSettings == appSettings1);
    }
}
