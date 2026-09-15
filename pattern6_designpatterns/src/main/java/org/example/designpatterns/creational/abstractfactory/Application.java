package org.example.designpatterns.creational.abstractfactory;

import org.example.designpatterns.creational.abstractfactory.button.Button;
import org.example.designpatterns.creational.abstractfactory.factory.MacOsUIFactory;
import org.example.designpatterns.creational.abstractfactory.factory.UIFactory;
import org.example.designpatterns.creational.abstractfactory.factory.WindowsUIFactory;
import org.example.designpatterns.creational.abstractfactory.scrollbar.Scrollbar;

public class Application {

    Button button;
    Scrollbar scrollbar;

    public Application(UIFactory uiFactory){
        this.button = uiFactory.createButton();
        this.scrollbar = uiFactory.createScrollbar();
    }

    public void renderUI(){
        button.render();
        scrollbar.scroll();
    }

    public static void main(String[] args) {

        UIFactory windowsFactory = new WindowsUIFactory();
        Application windowsApp = new Application(windowsFactory);
        windowsApp.renderUI();

        UIFactory macOsFactory = new MacOsUIFactory();
        Application macOSApp = new Application(macOsFactory);
        macOSApp.renderUI();
    }
}
