package org.example.designpatterns.behavioural.memento;

public class TextEditorMain {

    public static void main(String[] args) {

        TextEditor editor = new TextEditor();
        Caretaker caretaker = new Caretaker();

        editor.write("Hello World");
        caretaker.saveState(editor);

        System.out.println(editor.getContent());

        editor.write("Hello Everyone");
        caretaker.saveState(editor);

        System.out.println(editor.getContent());

        caretaker.undo(editor);
        caretaker.undo(editor);

        System.out.println("|"+editor.getContent()+"|");
    }
}
