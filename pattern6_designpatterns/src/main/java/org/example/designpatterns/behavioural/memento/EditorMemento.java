package org.example.designpatterns.behavioural.memento;

// snapshots of the text editor class
public class EditorMemento {

    private String content;

    public EditorMemento(String content){
        this.content = content;
    }

    public String getContent(){
        return content;
    }
}
