package org.example.designpatterns.behavioural.template.badcode;

public class Main {

    public static void main(String[] args) {

        CSVParser csvParser = new CSVParser();
        JSONParser jsonParser = new JSONParser();

        csvParser.parse();
        jsonParser.parse();
    }
}
