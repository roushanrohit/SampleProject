package org.example.designpatterns.creational.abstractfactory.factory;

import org.example.designpatterns.creational.abstractfactory.button.Button;
import org.example.designpatterns.creational.abstractfactory.scrollbar.Scrollbar;

public interface UIFactory {

    Button createButton();
    Scrollbar createScrollbar();
}
