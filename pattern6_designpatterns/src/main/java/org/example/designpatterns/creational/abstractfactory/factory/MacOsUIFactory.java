package org.example.designpatterns.creational.abstractfactory.factory;

import org.example.designpatterns.creational.abstractfactory.button.Button;
import org.example.designpatterns.creational.abstractfactory.button.MacOsButton;
import org.example.designpatterns.creational.abstractfactory.scrollbar.MacOsScrollbar;
import org.example.designpatterns.creational.abstractfactory.scrollbar.Scrollbar;

public class MacOsUIFactory implements UIFactory {

    @Override
    public Button createButton() {
        return new MacOsButton();
    }

    @Override
    public Scrollbar createScrollbar() {
        return new MacOsScrollbar();
    }
}
