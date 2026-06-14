package org.example.designpatterns.behavioural.command.goodcode;

/*
    Generic button, can hold reference to any type of command :
    Bold Command
    Italic Command
    Undo Command
    Redo Command
 */
public class Button {

    private Command command;

    public Button(Command command){
        this.command = command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void onClick(){
        command.execute();
    }
}
