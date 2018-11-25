package com.devlopp.teq.databasehelper;

import java.sql.Connection;

import com.devlopp.teq.database.DatabaseDriver;

public class DatabaseDriverHelper extends DatabaseDriver {

    public static void setDatabase(String dbName) {
        DatabaseDriver.DB_NAME = dbName;
    }

    public static String getDatabaseName() {
        return DatabaseDriver.DB_NAME;
    }

    public static Connection connectOrCreateDatabase(String dbName) {
        return DatabaseDriver.connectOrCreateDatabase(dbName);
    }

    public static Connection connectOrCreateDatabase() {
        return DatabaseDriver.connectOrCreateDatabase();
    }

    public static void initializeDatabase(String dbName) {
        DatabaseDriver.initializeDatabase(dbName);
    }

    public static void initializeDatabase() {
        DatabaseDriver.initializeDatabase();
    }
    
    public static void initializeUserRoles() {
        DatabaseDriver.initializeUserRoles();
    }

    public static boolean databaseExists(String dbName) {
        return DatabaseDriver.databaseExists(dbName);
    }

    public static boolean databaseExists() {
        return DatabaseDriver.databaseExists();
    }

    public static boolean deleteDatabase(String dbName) {
        return DatabaseDriver.deleteDatabase(dbName);
    }

    public static boolean deleteDatabase() {
        return DatabaseDriver.deleteDatabase();
    }

}
