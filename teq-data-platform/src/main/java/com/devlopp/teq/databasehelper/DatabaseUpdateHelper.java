package com.devlopp.teq.databasehelper;

import java.sql.Connection;
import java.sql.SQLException;

import com.devlopp.teq.database.DatabaseUpdater;

public class DatabaseUpdateHelper extends DatabaseUpdater {
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
