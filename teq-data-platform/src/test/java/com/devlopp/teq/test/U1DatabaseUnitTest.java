package com.devlopp.teq.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.devlopp.teq.address.Address;
import com.devlopp.teq.address.AddressBuilder;
import com.devlopp.teq.address.IAddressBuilder;
import com.devlopp.teq.app.ExcelDriver;
import com.devlopp.teq.client.Client;
import com.devlopp.teq.client.ClientBuilder;
import com.devlopp.teq.client.IClientBuilder;
import com.devlopp.teq.databasehelper.DatabaseDriverHelper;
import com.devlopp.teq.databasehelper.DatabaseInsertHelper;
import com.devlopp.teq.databasehelper.DatabaseSelectHelper;

public class U1DatabaseUnitTest {

    static void cleanDb() {
        String DB_NAME = "teq.db";
        File dbFile = new File(DB_NAME);
        boolean dbExists = dbFile.exists();
        // System.out.println("Database exists: " + dbExists);
        if (!dbExists) {
            DatabaseDriverHelper.initializeDatabase();
        } else {
            if (dbFile.delete()) {
                // System.out.println("Database file deleted successfully");
                DatabaseDriverHelper.initializeDatabase();
            } else {
                System.out.println("Failed to delete the database file");
            }
        }
    }

    static Client createDummy() {
        IAddressBuilder addressBuilder = new AddressBuilder();
        Address address = addressBuilder.setPostalCode("M1E5E6").setUnitNumber(Integer.parseInt("1606"))
                .setStreetNumber(Integer.parseInt("3050")).setStreetName("Ellesmere").setStreetType("Road")
                .setStreetDirection(ExcelDriver.fixDirection("East")).setCity("Toronto").setProvince("Ontario")
                .create();
        // insert address first
        int addressId = DatabaseInsertHelper.insertAddress(address);
        // System.out.println("New address ID at: " + addressId);
        // build the client object
        IClientBuilder clientBuilder = new ClientBuilder();
        Client client = clientBuilder.setId(Integer.parseInt("0")).setIdType(1)
                .setBirthDate(ExcelDriver.fixDate("1998-04-05", "yyyy-MM-dd")).setPhoneNumber("1234567890")
                .setEmailAddress("ash@gmail.com").setAddress(address).setLanguage("English")
                .setConsent(ExcelDriver.parseYesNo("Yes")).create();
        // System.out.println(client);
        return client;
    }

    static int insertDummy(Client client) {
        // insert client next
        int clientId = DatabaseInsertHelper.insertClient(client);
        // System.out.println("New client ID at: " + clientId);
        return clientId;
    }

    @Test
    @DisplayName("test insert client id")
    void testClientId() {
        cleanDb();
        Client dummyClient = createDummy();
        int dummyClientId = insertDummy(dummyClient);
        Client resultClient = DatabaseSelectHelper.getClient(dummyClientId);
        // System.out.println(dummyClient.getId());
        // System.out.println(resultClient.getId());
        assertEquals(0, resultClient.getId());
    }

    @Test
    @DisplayName("test insert client id type")
    void testClientIdType() {
        cleanDb();
        Client dummyClient = createDummy();
        int dummyClientId = insertDummy(dummyClient);
        Client resultClient = DatabaseSelectHelper.getClient(dummyClientId);
        // System.out.println(dummyClient.getIdType());
        // System.out.println(resultClient.getIdType());
        assertEquals(1, resultClient.getIdType());
    }

    @Test
    @DisplayName("test insert client birthday")
    void testClientBDate() {
        cleanDb();
        Client dummyClient = createDummy();
        int dummyClientId = insertDummy(dummyClient);
        Client resultClient = DatabaseSelectHelper.getClient(dummyClientId);
        // System.out.println(dummyClient.getBirthDate());
        // System.out.println(resultClient.getBirthDate());
        assertEquals("1998-04-05", resultClient.getBirthDate());
    }

    @Test
    @DisplayName("test insert client phone number")
    void testClientPhone() {
        cleanDb();
        Client dummyClient = createDummy();
        int dummyClientId = insertDummy(dummyClient);
        Client resultClient = DatabaseSelectHelper.getClient(dummyClientId);
        // System.out.println(dummyClient.getPhoneNumber());
        // System.out.println(resultClient.getPhoneNumber());
        assertEquals("1234567890", resultClient.getPhoneNumber());
    }

    @Test
    @DisplayName("test insert client email")
    void testClientMail() {
        cleanDb();
        Client dummyClient = createDummy();
        int dummyClientId = insertDummy(dummyClient);
        Client resultClient = DatabaseSelectHelper.getClient(dummyClientId);
        // System.out.println(dummyClient.getEmailAddress());
        // System.out.println(resultClient.getEmailAddress());
        assertEquals("ash@gmail.com", resultClient.getEmailAddress());
    }

    @Test
    @DisplayName("test insert client language")
    void testClientLanguage() {
        cleanDb();
        Client dummyClient = createDummy();
        int dummyClientId = insertDummy(dummyClient);
        Client resultClient = DatabaseSelectHelper.getClient(dummyClientId);
        // System.out.println(dummyClient.getLanguage());
        // System.out.println(resultClient.getLanguage());
        assertEquals("English", resultClient.getLanguage());
    }

    @Test
    @DisplayName("test insert client address")
    void testAddress() {
        cleanDb();
        Client dummyClient = createDummy();
        int dummyClientId = insertDummy(dummyClient);
        Client resultClient = DatabaseSelectHelper.getClient(dummyClientId);
        Address address = dummyClient.getAddress();
        int streetNumber = address.getStreetNumber();
        String streetName = address.getStreetName();
        String streetType = address.getStreetType();
        String streetDirection = address.getStreetDirection();
        int unitNumber = address.getUnitNumber();
        String city = address.getCity();
        String province = address.getProvince();
        String streetRepr = String.format("%d %s %s %s %s %s", streetNumber, streetName, streetType, streetDirection,
                city, province);
        if (unitNumber != -1) {
            streetRepr = unitNumber + "-" + streetRepr;
        }
        Address resultAddress = resultClient.getAddress();
        int rstreetNumber = resultAddress.getStreetNumber();
        String rstreetName = resultAddress.getStreetName();
        String rstreetType = resultAddress.getStreetType();
        String rstreetDirection = resultAddress.getStreetDirection();
        int runitNumber = resultAddress.getUnitNumber();
        String rcity = resultAddress.getCity();
        String rprovince = resultAddress.getProvince();
        String rstreetRepr = String.format("%d %s %s %s %s %s", rstreetNumber, rstreetName, rstreetType,
                rstreetDirection, rcity, rprovince);
        if (runitNumber != -1) {
            rstreetRepr = runitNumber + "-" + rstreetRepr;
        }
        // System.out.println(streetRepr);
        // System.out.println(rstreetRepr);
        assertEquals(streetRepr, rstreetRepr);
    }

}
