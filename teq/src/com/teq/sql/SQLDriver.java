package com.teq.sql;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class SQLDriver {

  public static boolean runScript(Connection connection, String fileName) {
    // initialize script runner
    ScriptRunner scriptRunner = new ScriptRunner(connection, false, true);
    try {
      // create buffered reader
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      
      // run the script
      scriptRunner.runScript(bufferedReader);

    } catch (IOException | SQLException e) {
      e.printStackTrace();
      return false;
    }   
    return true;
  }
  
}
