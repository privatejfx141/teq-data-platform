package com.devlopp.teq.databasehelper;

import java.sql.SQLException;
import java.util.List;

import com.devlopp.teq.databasepreset.DatabasePresetQuery;

/**
 * Helper methods for checking if input to database is valid.
 */
public class DatabaseValidHelper {

    /**
     * Minimum or starting ID number.
     */
    public static final int MIN_ID = 1;

    /**
     * Invalid ID number to return for exceptions.
     */
    public static final int INVALID_ID = -1;

    /**
     * Minimum password length.
     */
    public static final int MIN_PASSWORD_LENGTH = 4;

    /**
     * Maximum password length.
     */
    public static final int MAX_PASSWORD_LENGTH = 64;

    /**
     * Returns <code>true</code> if ID is a non-negative integer.
     * 
     * @param id an ID number
     * @return <code>true</code> if id is a non-negative integer
     */
    public static boolean validId(int id) {
        return id >= MIN_ID;
    }

    /**
     * Returns <code>true</code> if username is valid, between 4 and 64 characters
     * in length.
     * 
     * @param username a username
     * @return <code>true</code> if username length is between 4 and 64 characters
     */
    public static boolean validUsername(String username) {
        boolean valid;
        try {
            valid = username.length() >= 4;
            valid = valid && username.length() <= 64;
        } catch (NullPointerException npe) {
            valid = false;
        }
        return valid;
    }

    /**
     * Returns <code>true</code> if password is valid, between 4 and 64 characters
     * in length.
     * 
     * @param password a password
     * @return <code>true</code> if password length is between 4 and 64 characters
     */
    public static boolean validPassword(String password) {
        boolean valid;
        try {
            // check if password has between 4 and 64 chars
            valid = password.length() >= MIN_PASSWORD_LENGTH;
            valid = valid && password.length() <= MAX_PASSWORD_LENGTH;
        } catch (NullPointerException npe) {
            // return false if null was passed in
            valid = false;
        }
        return valid;
    }

    /**
     * Returns <code>true</code> if the client of clientId has taken the service of
     * serviceId.
     * 
     * @param clientId  unique ID of the client
     * @param serviceId service ID
     * @return <code>true</code> if client has taken the service
     */
    public static boolean clientTakenService(int clientId, int serviceId) {
        List<Integer> serviceIds = DatabaseSelectHelper.getClientServices(clientId);
        return serviceIds.contains(serviceId);
    }

    /**
     * Returns <code>true</code> if the client of clientId has taken the course of
     * courseCode, given that they have taken the LT course enroll or LT course exit
     * services.
     * 
     * @param clientId   unique ID of the client
     * @param courseCode course code
     * @return <code>true</code> if client has taken the course
     */
    public static boolean clientTakenCourse(int clientId, String courseCode) {
        try {
            List<String> coursesTaken = DatabasePresetQuery.getCoursesTaken(clientId);
            return coursesTaken.contains(courseCode);
        } catch (SQLException e) {
            return false;
        }
    }

}
