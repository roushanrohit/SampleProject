package org.example.designpatterns.behavioural.template.goodcode;

public abstract class DataParser {

    /* template method -- define the skeleton of the algorithm,
       let the child classes implement specific parts.
     */
    public final void parse(){

        openFile();
        parseData();
        closeFile();
    }

    // child class must provide an implementation for this -- called the hook
    protected abstract void parseData();

    protected void closeFile() {
        System.out.println("Closing file");
    }

    protected void openFile() {
        System.out.println("Opening file");
    }


}
