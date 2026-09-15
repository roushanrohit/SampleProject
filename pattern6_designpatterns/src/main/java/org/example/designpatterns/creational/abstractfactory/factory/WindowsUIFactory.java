package org.example.designpatterns.creational.abstractfactory.factory;

import org.example.designpatterns.creational.abstractfactory.button.Button;
import org.example.designpatterns.creational.abstractfactory.button.WindowsButton;
import org.example.designpatterns.creational.abstractfactory.scrollbar.Scrollbar;
import org.example.designpatterns.creational.abstractfactory.scrollbar.WindowsScrollbar;

public class WindowsUIFactory implements UIFactory {

    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Scrollbar createScrollbar() {
        return new WindowsScrollbar();
    }
}
