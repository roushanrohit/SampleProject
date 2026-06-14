package org.example.designpatterns.behavioural.command.badcode;

public class Main {

    public static void main(String[] args) {

        TextEditor editor = new TextEditor();
        BoldButton boldButton = new BoldButton(editor);
        ItalicButton italicButton = new ItalicButton(editor);

        boldButton.onClick();
        italicButton.onClick();
    }
}
