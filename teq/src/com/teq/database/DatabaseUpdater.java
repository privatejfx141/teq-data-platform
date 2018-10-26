package com.teq.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.teq.entities.Address;
import com.teq.sql.SQLDriver;

public class DatabaseUpdater {
    protected static boolean updateClientBirthDate(Connection connection, int clientId, Date birthDate) {
        String sql = "UPDATE Client SET birth_date = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareCall(sql);
            statement.setDate(1, birthDate);
            statement.setInt(2, clientId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected static boolean updateClientPhoneNumber(Connection connection, int clientId, String phoneNumber) {
        String sql = "UPDATE Client SET phone_number = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareCall(sql);
            statement.setString(1, phoneNumber);
            statement.setInt(2, clientId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected static boolean updateClientEmailAddress(Connection connection, int clientId, String emailAddress) {
        String sql = "UPDATE Client SET email_address = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareCall(sql);
            statement.setString(1, emailAddress);
            statement.setInt(2, clientId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected static boolean updateClientAddress(Connection connection, int clientId, int addressId) {
        String sql = "UPDATE Client SET address_id = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, addressId);
            statement.setInt(2, clientId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected static boolean updateClientAddress(Connection connection, int clientId, Address address) {
        return false;
    }

    protected static boolean updateClientLanguage(Connection connection, int clientId, String languageCode) {
        String sql = "UPDATE Client SET language = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, languageCode);
            statement.setInt(2, clientId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected static boolean updateClientConscents(Connection connection, int clientId, boolean conscents) {
        String sql = "UPDATE Client SET conscents = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBoolean(1, conscents);
            statement.setInt(2, clientId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected static boolean updateCourseLanguage(Connection connection, String courseCode, String languageCode) {
        String sql = "UPDATE Course SET language = ? WHERE course_code = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, languageCode);
            statement.setString(2, courseCode);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected static boolean updateCourseStartDate(Connection connection, String courseCode, String startDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String sql = "UPDATE Course SET start_date = ? WHERE course_code = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, SQLDriver.parseDate(startDate));
            statement.setString(2, courseCode);
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected static boolean updateCourseEndDate(Connection connection, String courseCode, String endDate) {
        return false;
    }

    protected static boolean updateCourseContactName(Connection connection, String courseCode, String contactName) {
        return false;
    }

    protected static boolean updateCourseContactAddress(Connection connection, String courseCode, int addressId) {
        return false;
    }

    protected static boolean updateCourseContactTelephoneNumber(Connection connection, String courseCode,
            String number) {
        return false;
    }

    protected static boolean updateCourseContactTelephoneNumber(Connection connection, String courseCode, String number,
            int ext) {
        return false;
    }

    protected static boolean updateCourseContactEmailAddress(Connection connection, String courseCode, String email) {
        return false;
    }
    
    
}
