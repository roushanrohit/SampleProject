package org.example.designpatterns.behavioural.template.goodcode;

public class Main {

    public static void main(String[] args) {

        DataParser csvParser = new CSVParser();
        DataParser jsonParser = new JSONParser();

        csvParser.parse();
        jsonParser.parse();
    }
}
