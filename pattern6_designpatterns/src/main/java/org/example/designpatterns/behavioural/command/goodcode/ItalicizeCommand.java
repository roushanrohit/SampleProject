package org.example.designpatterns.behavioural.command.goodcode;

public class ItalicizeCommand implements Command {

    private TextEditor textEditor;

    public ItalicizeCommand(TextEditor textEditor){
        this.textEditor = textEditor;
    }

    @Override
    public void execute() {
        textEditor.italicizeText();
    }
}
