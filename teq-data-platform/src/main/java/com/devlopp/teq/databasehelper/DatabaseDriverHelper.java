package com.devlopp.teq.databasehelper;

import java.sql.Connection;

import com.devlopp.teq.database.DatabaseDriver;

public class DatabaseDriverHelper extends DatabaseDriver {

    protected static Connection connectOrCreateDatabase() {
        return DatabaseDriver.connectOrCreateDatabase();
    }
    
    protected static Connection connectOrCreateDatabase(String dbName) {
        return DatabaseDriver.connectOrCreateDatabase(dbName);
    }
    
    public static void initializeDatabase() {
        DatabaseDriver.initializeDatabase();
    }
    
    public static boolean databaseExists() {
        return DatabaseDriver.databaseExists();
    }

}
