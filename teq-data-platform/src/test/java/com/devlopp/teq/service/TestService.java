package com.devlopp.teq.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.devlopp.teq.databasehelper.DatabaseDriverHelper;
import com.devlopp.teq.databasehelper.DatabaseInsertHelper;

public class TestService {

    static void cleanDb() {
        if (!DatabaseDriverHelper.databaseExists() || DatabaseDriverHelper.deleteDatabase()) {
            DatabaseDriverHelper.initializeDatabase();
        }
    }
    
    static MockService createMockService() {
        MockService mock = (MockService) new MockService.Builder() //
                .setId(0) //
                .setClientId(0) //
                .setPostalCode(null) //
                .setLanguage(null) //
                .setReferredBy(null) //
                .setOrganizationType(null) //
                .setUpdateReason(null) //
                .setServiceType(null) //
                .setEssentialSkills(null) //
                .setSupportServices(null) //
                .setTargetGroups(null) //
                .setChildCares(null) //
                .create();
        return mock;
    }

    @Test
    @DisplayName("test insert mock service")
    void testMockService() {
        cleanDb();
        MockService mock = createMockService();
        int mockId = DatabaseInsertHelper.insertService(mock);
        assertTrue(mockId > 0);
    }

}
