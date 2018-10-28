package com.teq.databasehelper;

import java.sql.Connection;

import com.teq.database.DatabaseDriver;

public class DatabaseDriverHelper extends DatabaseDriver {

    protected static Connection connectOrCreateDatabase() {
        return DatabaseDriver.connectOrCreateDatabase();
    }
    
    public static void initializeDatabase() {
        DatabaseDriver.initializeDatabase();
    }
    
    public static boolean databaseExists() {
        return DatabaseDriver.databaseExists();
    }

}
