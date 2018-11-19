package com.developp.teq.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.devlopp.teq.security.PasswordHelper;

public class TestPassword {

    @Test
    @DisplayName("test hash length")
    void testHashLength() {
        String password = "Secure123!";
        String hashed = PasswordHelper.passwordHash(password);
        assertEquals(64, hashed.length());
    }

    @Test
    @DisplayName("test different hash")
    void testDiffusion() {
        String hashed1 = PasswordHelper.passwordHash("password");
        String hashed2 = PasswordHelper.passwordHash("Password");
        assertNotEquals(hashed1, hashed2);
    }

}