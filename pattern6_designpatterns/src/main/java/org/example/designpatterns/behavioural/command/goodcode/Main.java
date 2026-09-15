package org.example.designpatterns.behavioural.command.goodcode;

/*
    Command pattern use cases:
    1. GUI Applications -- commands can be associated with buttons, menus and keyboard shortcuts in
                           applications like text editors, spreadsheets etc.
    2. Task Scheduling -- commands can be placed in a queue and executed later, useful in batch
                          processing.
    3. Undo/Redo Functionality -- commands can be stored in a stack and rolled back to provide undo redo
                          functionality, in IDEs, word processors etc.
    4. Macro Recording -- actions performed by a user can be recorded as a series of commands, which can then
                          be played back as macros.
 */
public class Main {

    public static void main(String[] args) {

        TextEditor textEditor = new TextEditor();
        Command boldCommand = new BoldCommand(textEditor);
        Command italicizeCommand = new ItalicizeCommand(textEditor);

        // Button class can be a part of different package -- decoupled from the text editor
        Button button = new Button(boldCommand);
        button.onClick();
        button.setCommand(italicizeCommand);
        button.onClick();
    }
}
