package org.example.designpatterns.creational.singleton.goodcode;

/*
    In certain situations such as managing a database connection, logging or
    configuration settings, we want to ensure that only one instance of the class
    is created throughout the application life cycle
    1. To avoid inconsistent states - multiple configuration values in multiple configuration instances
    2. If multiple instances of resource heavy classes are created, it can lead to performance degradation
 */
public class AppSettings {

    private static AppSettings instance;
    private String databaseURL;
    private String apiKey;

    // private constructor to prevent direct object creation
    private AppSettings(){
        databaseURL = "";
        apiKey = "";
    }

    /*
         static because we want to create an object using the class itself
         synchronized because we want it to be thread safe -- two threads should not read
         the instance as null at the same time
     */
    public static synchronized AppSettings getInstance(){
        if(instance == null){
            instance = new AppSettings();
        }
        return instance;
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

    // to prevent cloning of the instance
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
