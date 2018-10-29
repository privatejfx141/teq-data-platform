package com.devlopp.teq.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.devlopp.teq.address.Address;
import com.devlopp.teq.sql.SQLDriver;

public class DatabaseUpdater {
    protected static boolean updateClientBirthDate(Connection connection, int clientId, String birthDate) {
        String sql = "UPDATE Client SET birth_date = ? WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, SQLDriver.parseDate(birthDate));
            statement.setInt(2, clientId);
            statement.executeUpdate();
            return true;
        } catch (SQLException | ParseException e) {
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
        String sql = "UPDATE Course SET start_date = ? WHERE course_code = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, SQLDriver.parseDate(startDate));
            statement.setString(2, courseCode);
            statement.executeUpdate();
            return true;
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected static boolean updateCourseEndDate(Connection connection, String courseCode, String endDate) {
        String sql = "UPDATE Course SET end_date = ? WHERE course_code = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, SQLDriver.parseDate(endDate));
            statement.setString(2, courseCode);
            statement.executeUpdate();
            return true;
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected static boolean updateCourseContactName(Connection connection, String courseCode, String contactName) {
        String sql = "UPDATE CourseContact SET contact_name = ? WHERE course_code = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, courseCode);
            statement.setString(2, contactName);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected static boolean updateCourseContactAddress(Connection connection, String courseCode, int addressId) {
        String sql = "UPDATE CourseContact SET address_id = ? WHERE course_code = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, addressId);
            statement.setString(2, courseCode);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected static boolean updateCourseContactTelephoneNumber(Connection connection, String courseCode,
            String number) {
        String sql = "UPDATE CourseContact SET telephone_number = ? WHERE course_code = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, number);
            statement.setString(2, courseCode);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected static boolean updateCourseContactTelephoneNumber(Connection connection, String courseCode, String number,
            String ext) {
        if (updateCourseContactTelephoneNumber(connection, courseCode, number)) {
            String sql = "UPDATE CourseContact SET telephone_ext = ? WHERE course_code = ?";
            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, ext);
                statement.setString(2, courseCode);
                statement.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    protected static boolean updateCourseContactEmailAddress(Connection connection, String courseCode, String email) {
        String sql = "UPDATE CourseContact SET email_address = ? WHERE course_code = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, courseCode);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
