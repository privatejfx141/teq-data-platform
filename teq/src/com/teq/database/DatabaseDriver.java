package com.teq.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import com.teq.address.Address;
import com.teq.address.AddressBuilder;
import com.teq.address.IAddressBuilder;
import com.teq.databasehelper.DatabaseInsertHelper;
import com.teq.databasehelper.DatabaseSelectHelper;
import com.teq.sql.SQLDriver;

public class DatabaseDriver {
    public static final String DB_NAME = "teq.db";

    protected static Connection connectOrCreateDatabase() {
        Connection connection = null;
        String url = "jdbc:sqlite:" + DB_NAME;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    
    protected static void initializeDatabase() {
        Connection connection = connectOrCreateDatabase();
        // run the scripts to create the tables
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
        System.out.println("Database successfully initialized");
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    protected static boolean databaseExists() {
        File dbFile = new File(DB_NAME);
        return dbFile.exists();
    }

}
