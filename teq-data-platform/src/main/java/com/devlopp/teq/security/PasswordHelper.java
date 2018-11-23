package com.devlopp.teq.security;

import java.security.MessageDigest;

import com.devlopp.teq.databasehelper.DatabaseSelectHelper;
import com.devlopp.teq.databasehelper.DatabaseValidHelper;

public class PasswordHelper {

    /**
     * Returns a hashed version of password to be stored in database.
     * 
     * @param password the unhashed password
     * @return the hashed password
     */
    public static String passwordHash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes("UTF-8"));
            byte[] digest = md.digest();
            return String.format("%064x", new java.math.BigInteger(1, digest));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Checks if the database password matches user provided password.
     * 
     * @param pw1 the password stored in the database
     * @param pw2 the user provided password (unhashed)
     * @return true if passwords match, false otherwise.
     */
    public static boolean comparePassword(String pw1, String pw2) {
        return pw1.equals(passwordHash(pw2));
    }

    /**
     * Checks if the user can be authenticated with the given username and password.
     * 
     * @param username username
     * @param password password
     * @return true if user is properly authenticated, false otherwise
     */
    public static boolean authenticateUser(String username, String password) {
        int userId = DatabaseSelectHelper.getPlatformUserId(username);
        if (userId != DatabaseValidHelper.INVALID_ID) {
            String dbPassword = DatabaseSelectHelper.getPlatformPassword(userId);
            return comparePassword(dbPassword, password);
        }
        return false;
    }

}
