package com.devlopp.teq.login;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.devlopp.teq.databasehelper.DatabaseDriverHelper;
import com.devlopp.teq.databasehelper.DatabaseInsertHelper;
import com.devlopp.teq.databasehelper.DatabaseSelectHelper;
import com.devlopp.teq.generics.Roles;
import com.devlopp.teq.security.PasswordHelper;

public class TestAuthentication {

    static final String TEST_DB_NAME = "test.db";

    static void cleanDb() {
        if (!DatabaseDriverHelper.databaseExists()) {
            DatabaseDriverHelper.initializeDatabase();
        } else if (DatabaseDriverHelper.deleteDatabase()) {
            DatabaseDriverHelper.initializeDatabase();
        }
    }

    @Test
    @DisplayName("test nonexistant user")
    void testNonexistantUser() {
        cleanDb();
        assertFalse(PasswordHelper.authenticateUser("alicetequser", "Secure123!"));
    }

    @Test
    @DisplayName("test authentication")
    void testAuthentication() {
        cleanDb();
        String username = "alicetequser";
        String password = "Secure123!";
        int roleId = DatabaseSelectHelper.getPlatformRoleId(Roles.UTSC_STAFF.toString());
        DatabaseInsertHelper.insertPlatformUser(username, password, roleId);
        assertTrue(PasswordHelper.authenticateUser(username, password));
    }

    @Test
    @DisplayName("test incorrect password authentication")
    void testBadAuthentication() {
        cleanDb();
        String username = "alicetequser";
        String password = "Secure123!";
        int roleId = DatabaseSelectHelper.getPlatformRoleId(Roles.UTSC_STAFF.toString());
        DatabaseInsertHelper.insertPlatformUser(username, password, roleId);
        assertFalse(PasswordHelper.authenticateUser(username, "Incorrect123!"));
    }

}
