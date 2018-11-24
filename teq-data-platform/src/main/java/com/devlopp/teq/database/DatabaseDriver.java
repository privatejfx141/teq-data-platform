package com.devlopp.teq.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.devlopp.teq.generics.Roles;
import com.devlopp.teq.sql.SQLDriver;

public class DatabaseDriver {

    protected static String DB_NAME = "teq.db";

    protected static Connection connectOrCreateDatabase(String dbName) {
        Connection connection = null;
        String url = "jdbc:sqlite:" + dbName;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    protected static Connection connectOrCreateDatabase() {
        return connectOrCreateDatabase(DB_NAME);
    }

    protected static void initializeDatabase(String dbName) {
        Connection connection = connectOrCreateDatabase(dbName);
        // create and populate user platform authentication tables
        SQLDriver.runScript(connection, "scripts/login/Create_User.sql");
        initializeUserRoles(dbName);
        // run the scripts to create the record tables
        SQLDriver.runScript(connection, "scripts/Create_Address.sql");
        SQLDriver.runScript(connection, "scripts/Create_Assessment.sql");
        SQLDriver.runScript(connection, "scripts/Create_Client.sql");
        SQLDriver.runScript(connection, "scripts/Create_CommunityConnections.sql");
        SQLDriver.runScript(connection, "scripts/Create_Course.sql");
        SQLDriver.runScript(connection, "scripts/Create_CourseService.sql");
        SQLDriver.runScript(connection, "scripts/Create_Employment.sql");
        SQLDriver.runScript(connection, "scripts/Create_EssentialSkill.sql");
        SQLDriver.runScript(connection, "scripts/Create_Increase.sql");
        SQLDriver.runScript(connection, "scripts/Create_LiteracySkill.sql");
        SQLDriver.runScript(connection, "scripts/Create_NewcomerChildCare.sql");
        SQLDriver.runScript(connection, "scripts/Create_NonIRCCService.sql");
        SQLDriver.runScript(connection, "scripts/Create_Orientation.sql");
        SQLDriver.runScript(connection, "scripts/Create_Schedule.sql");
        SQLDriver.runScript(connection, "scripts/Create_Service.sql");
        SQLDriver.runScript(connection, "scripts/Create_SupportService.sql");
        SQLDriver.runScript(connection, "scripts/Create_TargetGroup.sql");
        SQLDriver.runScript(connection, "scripts/Create_Topic.sql");
        SQLDriver.runScript(connection, "scripts/Create_Translation.sql");
        // return the connection to the database
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static void initializeDatabase() {
        initializeDatabase(DB_NAME);
    }

    protected static void initializeUserRoles(String dbName) {
        Connection connection = connectOrCreateDatabase();
        for (Roles roleEnum : Roles.values()) {
            String roleName = roleEnum.toString();
            try {
                DatabaseInserter.insertPlatformRole(connection, roleName);
            } catch (DatabaseInsertException e) {
                e.printStackTrace();
            }
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static void initializeUserRoles() {
        initializeUserRoles(DB_NAME);
    }

    protected static boolean databaseExists(String dbName) {
        File dbFile = new File(dbName);
        return dbFile.exists();
    }

    protected static boolean databaseExists() {
        return databaseExists(DB_NAME);
    }

    protected static boolean deleteDatabase(String dbName) {
        File dbFile = new File(dbName);
        return dbFile.delete();
    }

    protected static boolean deleteDatabase() {
        return deleteDatabase(DB_NAME);
    }

}
