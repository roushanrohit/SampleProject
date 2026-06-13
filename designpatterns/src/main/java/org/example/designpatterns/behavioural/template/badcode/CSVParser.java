package org.example.designpatterns.behavioural.template.badcode;

public class CSVParser {

    public void parse(){

        openFile();
        // csv specific parsing logic
        System.out.println("Parsing a CSV file");
        closeFile();
    }

    /*
        Duplicated across two different classes
        Any change in common logic has to be done in all parser classes
     */
    private void closeFile() {
        System.out.println("Closing file");
    }

    private void openFile() {
        System.out.println("Opening file");
    }
}
