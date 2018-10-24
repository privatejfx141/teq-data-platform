package com.teq.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import com.teq.databasehelper.DatabaseSelectHelper;

public class DatabaseDriver {

  public static Connection connectToDatabase() {
    Connection connection = null;
    String url = "jdbc:sqlite:teq.db";
    try {
      connection = DriverManager.getConnection(url);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return connection;
  }
  
  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    
    // if argument is set to initialize database
    if (args.length >= 1 && args[0].equals("-1")) {
      Connection connection = InitializeDatabase.initializeDatabase();
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    printList(DatabaseSelectHelper.selectTargetGroups());
    printList(DatabaseSelectHelper.selectIncreases());

  }
  
  public static <T> void printList(List<T> list) {
      System.out.println(Arrays.toString(list.toArray()));
  }
  

}
