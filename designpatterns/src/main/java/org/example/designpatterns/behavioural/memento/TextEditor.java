package org.example.designpatterns.behavioural.memento;

/*
    captures the internal state of an object and later on
    allows it to restore it without revealing its internal state
 */
public class TextEditor {

    private String content;

    public EditorMemento save(){
        return new EditorMemento(content);
    }

    public void restore(EditorMemento memento){
        this.content = memento.getContent();
    }

    public void write(String text){
        this.content = text;
    }

    public String getContent(){
        return content;
    }

}
