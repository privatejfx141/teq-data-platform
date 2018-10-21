package com.teq.database;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseDriver {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
      Connection connection = InitializeDatabase.initializeDatabase();
      
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
  }

}
