package org.example.designpatterns.creational.singleton.goodcode;

public class WithSingletonPattern {

    public static void main(String[] args) {

        AppSettings appSettings = AppSettings.getInstance();
        appSettings.setApiKey("1234");
        AppSettings appSettings1 = AppSettings.getInstance();
        appSettings1.setApiKey("5678");

        System.out.println(appSettings.getApiKey());
        System.out.println(appSettings1.getApiKey());

        // now both are referring to the same object because of singleton pattern
        System.out.println(appSettings == appSettings1); // prints true
    }
}
