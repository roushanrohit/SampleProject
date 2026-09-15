package org.example.designpatterns.behavioural.command.badcode;

public class ItalicButton {

    TextEditor textEditor;

    public ItalicButton(TextEditor editor){
        this.textEditor = editor;
    }

    public void onClick(){
        textEditor.italicizeText();
    }
}
