package com.devlopp.teq.app;

import com.devlopp.teq.databasehelper.DatabaseDriverHelper;

public class App {

    public static void main(String[] args) {
        System.out.println("TEQ LIP data platform");

        boolean dbExists = DatabaseDriverHelper.databaseExists();
        System.out.println("Database exists: " + dbExists);
        if (!dbExists) {
            DatabaseDriverHelper.initializeDatabase();
        }

        
        CreateObject.createManyClients();
        CreateObject.createDifferentServices();
        CreateObject.createServiceWithLanguage("Spanish");
        CreateObject.createServiceWithLanguage("Arabic");
        CreateObject.createServiceWithLanguage("English");
        CreateObject.createServiceWithLanguage("Spanish");
        CreateObject.createServiceObject("2018-01-01", "2018-01-30");
        
    }

}
