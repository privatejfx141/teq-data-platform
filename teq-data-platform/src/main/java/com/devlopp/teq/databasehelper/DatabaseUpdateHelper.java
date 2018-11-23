package com.devlopp.teq.databasehelper;

import java.sql.Connection;
import java.sql.SQLException;

import com.devlopp.teq.database.DatabaseUpdater;
import com.devlopp.teq.security.PasswordHelper;

public class DatabaseUpdateHelper extends DatabaseUpdater {

    public static boolean updatePlatformPassword(int userId, String password) {
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        String hashPassword = PasswordHelper.passwordHash(password);
        boolean result = DatabaseUpdater.updatePlatformPassword(connection, userId, hashPassword);
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean updateClientBirthDate(int clientId, String birthDate) {
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        boolean result = DatabaseUpdater.updateClientBirthDate(connection, clientId, birthDate);
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean updateClientPhoneNumber(int clientId, String phoneNumber) {
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        boolean result = DatabaseUpdater.updateClientPhoneNumber(connection, clientId, phoneNumber);
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
