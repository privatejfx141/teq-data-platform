package com.devlopp.teq.app;

import com.devlopp.teq.databasehelper.DatabaseDriverHelper;
import ui.Main;

public class App {
   
    public static void main(String[] args) {
        System.out.println("TEQ LIP data platform");
        
        boolean dbExists = DatabaseDriverHelper.databaseExists();
        System.out.println("Database exists: " + dbExists);
        if (!dbExists) {
            DatabaseDriverHelper.initializeDatabase();
        }
        
    }
    
}
