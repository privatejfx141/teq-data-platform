package com.devlopp.teq.login;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.devlopp.teq.databasehelper.DatabaseDriverHelper;
import com.devlopp.teq.databasehelper.DatabaseInsertHelper;
import com.devlopp.teq.databasehelper.DatabaseSelectHelper;
import com.devlopp.teq.generics.Roles;
import com.devlopp.teq.security.PasswordHelper;

public class TestPlatformUser {

    static final String TEST_DB_NAME = "test.db";

    static void cleanDb() {
        if (!DatabaseDriverHelper.databaseExists()) {
            DatabaseDriverHelper.initializeDatabase();
        } else if (DatabaseDriverHelper.deleteDatabase()) {
            DatabaseDriverHelper.initializeDatabase();
        }
    }

    @Test
    @DisplayName("test insert user")
    void testInsertUser() {
        cleanDb();
        String username = "alicetequser";
        String password = "Secure123!";
        String hashPassword = PasswordHelper.passwordHash(password);
        int roleId = DatabaseSelectHelper.getPlatformRoleId(Roles.UTSC_STAFF.toString());
        int userId = DatabaseInsertHelper.insertPlatformUser(username, password, roleId);
        String dbUsername = DatabaseSelectHelper.getPlatformUsername(userId);
        String dbPassword = DatabaseSelectHelper.getPlatformPassword(userId);
        assertEquals(username, dbUsername);
        assertEquals(hashPassword, dbPassword);
    }
}
