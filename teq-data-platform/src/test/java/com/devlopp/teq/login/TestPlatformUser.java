package com.devlopp.teq.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.devlopp.teq.databasehelper.DatabaseDriverHelper;
import com.devlopp.teq.databasehelper.DatabaseInsertHelper;
import com.devlopp.teq.databasehelper.DatabaseSelectHelper;
import com.devlopp.teq.databasehelper.DatabaseUpdateHelper;
import com.devlopp.teq.generics.Roles;
import com.devlopp.teq.security.PasswordHelper;

public class TestPlatformUser {

    static final String TEST_DB_NAME = "test.db";

    static void cleanDb() {
        if (!DatabaseDriverHelper.databaseExists() || DatabaseDriverHelper.deleteDatabase()) {
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

    @Test
    @DisplayName("test update password")
    void testUpdatePassword() {
        cleanDb();
        String username = "alicetequser";
        String password = "Secure123!";
        String newPassword = "NewSecure123!";
        String hashNewPassword = PasswordHelper.passwordHash(newPassword);
        int roleId = DatabaseSelectHelper.getPlatformRoleId(Roles.UTSC_STAFF.toString());
        int userId = DatabaseInsertHelper.insertPlatformUser(username, password, roleId);
        assertTrue(DatabaseUpdateHelper.updatePlatformPassword(userId, newPassword));
        String dbUsername = DatabaseSelectHelper.getPlatformUsername(userId);
        String dbPassword = DatabaseSelectHelper.getPlatformPassword(userId);
        assertEquals(username, dbUsername);
        assertEquals(hashNewPassword, dbPassword);
    }
    
    @Test
    @DisplayName("test select role")
    void testSelectRole() {
        cleanDb();
        String username = "alicetequser";
        String password = "Secure123!";
        String hashPassword = PasswordHelper.passwordHash(password);
        int roleId = DatabaseSelectHelper.getPlatformRoleId(Roles.UTSC_STAFF.toString());
        int userId = DatabaseInsertHelper.insertPlatformUser(username, password, roleId);
        String dbUsername = DatabaseSelectHelper.getPlatformUsername(userId);
        String dbPassword = DatabaseSelectHelper.getPlatformPassword(userId);
        String dbRoleName = DatabaseSelectHelper.getPlatformRole(roleId);
        assertEquals(username, dbUsername);
        assertEquals(hashPassword, dbPassword);
        assertEquals(Roles.UTSC_STAFF.toString(), dbRoleName);
    }

}
