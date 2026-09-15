package org.example.designpatterns.behavioural.command.badcode;

public class BoldButton {

    TextEditor textEditor;

    public BoldButton(TextEditor editor){
        this.textEditor = editor;
    }

    public void onClick(){
        textEditor.boldText();
    }
}
