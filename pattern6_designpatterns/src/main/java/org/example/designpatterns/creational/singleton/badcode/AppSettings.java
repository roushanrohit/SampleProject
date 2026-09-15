package org.example.designpatterns.creational.singleton.badcode;

public class AppSettings {

    private String databaseURL;
    private String apiKey;

    AppSettings(){
        databaseURL = "";
        apiKey = "";
    }

    public String getDatabaseURL() {
        return databaseURL;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setDatabaseURL(String databaseURL) {
        this.databaseURL = databaseURL;
    }
}
