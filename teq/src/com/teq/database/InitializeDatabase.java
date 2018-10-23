package com.teq.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.teq.sql.SQLDriver;

public class InitializeDatabase {

  /**
   * Connect to a sample database
   *
   * @param fileName the database file name
   */
  public static Connection createNewDatabase(String fileName) {

      String url = "jdbc:sqlite:" + fileName;
      Connection conn = null;
      try {
        conn = DriverManager.getConnection(url);
        if (conn != null) {
            DatabaseMetaData meta = conn.getMetaData();
            System.out.println("The driver name is " + meta.getDriverName());
            System.out.println("A new database has been created.");
        }
      } catch (SQLException e) {
          System.out.println(e.getMessage());
      }
      return conn;
  }
  
  public static Connection initializeDatabase() {
    Connection connection = createNewDatabase("teq.db");
    
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

    return connection;    
  }
  
}
