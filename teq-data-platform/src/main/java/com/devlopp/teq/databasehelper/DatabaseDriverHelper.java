package com.devlopp.teq.databasehelper;

import java.sql.Connection;

import com.devlopp.teq.database.DatabaseDriver;

public class DatabaseDriverHelper extends DatabaseDriver {

    public static Connection connectOrCreateDatabase() {
        return DatabaseDriver.connectOrCreateDatabase();
    }
    
    public static void initializeDatabase() {
        DatabaseDriver.initializeDatabase();
    }
    
    public static boolean databaseExists() {
        return DatabaseDriver.databaseExists();
    }

}
