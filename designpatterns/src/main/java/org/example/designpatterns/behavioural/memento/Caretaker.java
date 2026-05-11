package org.example.designpatterns.behavioural.memento;

import java.util.Stack;

public class Caretaker {

    private Stack<EditorMemento> history = new Stack<>();

    public void saveState(TextEditor editor){
        history.push(editor.save());
    }

    public void undo(TextEditor editor){
        if(history.size() > 1){
            history.pop();
            editor.restore(history.peek());
        }
    }
}
