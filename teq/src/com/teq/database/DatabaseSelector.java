package com.teq.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseSelector {
  
    protected static ResultSet selectAllTypes(Connection connection, String tableName) throws SQLException {
        String sql = "SELECT * FROM " + tableName;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }
    
    protected static ResultSet selectClient(Connection connection, int clientId) throws SQLException {
        String sql = "SELECT * FROM Client WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, clientId);
        return preparedStatement.executeQuery();
    }
    
    protected static ResultSet selectService(Connection connection, int serviceId) throws SQLException {
        String sql = "SELECT * FROM Service WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, serviceId);
        return preparedStatement.executeQuery();
    }
    
    protected static ResultSet selectCourse(Connection connection, String courseCode) throws SQLException {
        String sql = "SELECT * FROM Course WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, courseCode);
        return preparedStatement.executeQuery();
    }
    
}
