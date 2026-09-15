package org.example.designpatterns.creational.abstractfactory.scrollbar;

public class MacOsScrollbar implements Scrollbar {

    public void scroll(){
        System.out.println("Rendering Mac OS scrollbar");
    }
}
