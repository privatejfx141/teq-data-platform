package com.teq.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseSelector {
  
    protected static ResultSet getAllTypes(Connection connection, String tableName) throws SQLException {
        String sql = "SELECT * FROM " + tableName;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }
    
    protected static ResultSet getAllConstraints(Connection connection, String tableName, String columnName) throws SQLException {
        return null;
    }
    
    protected static ResultSet getClient(Connection connection, int clientId) throws SQLException {
        String sql = "SELECT * FROM Client WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, clientId);
        return preparedStatement.executeQuery();
    }
    
    protected static ResultSet getService(Connection connection, int serviceId) throws SQLException {
        String sql = "SELECT * FROM Service WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, serviceId);
        return preparedStatement.executeQuery();
    }
    
    public static ResultSet getServiceEssentialSkill(Connection connection, int serviceId) throws SQLException {
        return null;
    }
    
    public static ResultSet getServiceSupportService(Connection connection, int serviceId) throws SQLException {
        return null;
    }
    
    public static ResultSet getServiceTargetGroup(Connection connection, int serviceId) throws SQLException {
        return null;
    }
    
    protected static ResultSet getCourse(Connection connection, String courseCode) throws SQLException {
        String sql = "SELECT * FROM Course WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, courseCode);
        return preparedStatement.executeQuery();
    }
    
    public static ResultSet getCourseSchedule(Connection connection, String courseCode) throws SQLException {
        return null;
    }
    
    public static ResultSet getCourseSupportService(Connection connection, String courseCode) throws SQLException {
        return null;
    }
    
    public static ResultSet getCourseTargetGroup(Connection connection, String courseCode) throws SQLException {
        return null;
    }

    
    protected static ResultSet getCourseContact(Connection connection, String courseCode) throws SQLException {
        String sql = "SELECT * FROM CourseContact WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, courseCode);
        return preparedStatement.executeQuery();
    }
    
    protected static ResultSet getAddress(Connection connection, int addressId) throws SQLException {
        String sql = "SELECT * FROM Address WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, addressId);
        return preparedStatement.executeQuery();
    }
    
}
