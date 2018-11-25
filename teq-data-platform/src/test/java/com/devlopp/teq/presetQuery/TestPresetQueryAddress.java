package com.devlopp.teq.presetQuery;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.List;

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
import com.devlopp.teq.databasepreset.DatabasePresetQuery;

public class TestPresetQueryAddress {

    static int clientIdSt = 1;

    static void cleanDb() {
        if (!DatabaseDriverHelper.databaseExists() || DatabaseDriverHelper.deleteDatabase()) {
            DatabaseDriverHelper.initializeDatabase();
        }
    }

    static Address createAddress(String postalCode) {
        IAddressBuilder addressBuilder = new AddressBuilder();
        Address address = addressBuilder.setPostalCode(postalCode) //
                .setUnitNumber(Integer.parseInt("1606")) //
                .setStreetNumber(Integer.parseInt("3050")) //
                .setStreetName("Ellesmere").setStreetType("Road") //
                .setStreetDirection(ExcelDriver.fixDirection("East")) //
                .setCity("Toronto") //
                .setProvince("Ontario") //
                .create();
        return address;
    }

    static Address createAddress(String city, String province) {
        IAddressBuilder addressBuilder = new AddressBuilder();
        Address address = addressBuilder.setPostalCode("M1G3L2") //
                .setUnitNumber(Integer.parseInt("1606")) //
                .setStreetNumber(Integer.parseInt("3050")) //
                .setStreetName("Ellesmere").setStreetType("Road") //
                .setStreetDirection(ExcelDriver.fixDirection("East")) //
                .setCity(city) //
                .setProvince(province) //
                .create();
        return address;
    }

    static int createAndInsertClient(Address address) {
        IClientBuilder clientBuilder = new ClientBuilder();
        Client client = clientBuilder.setId(clientIdSt++) //
                .setIdType(1) //
                .setBirthDate("1970-10-21") //
                .setPhoneNumber("1234567890") //
                .setEmailAddress("test@gmail.com") //
                .setAddress(address) //
                .setLanguage("ENG") //
                .setConsent(true) //
                .create();
        return DatabaseInsertHelper.insertClient(client);
    }

    @Test
    @DisplayName("test one client id from postal code")
    void getClientIdFromPostalCode() throws SQLException {
        cleanDb();
        Address address = createAddress("M1P4X4");
        int clientId = createAndInsertClient(address);
        List<Integer> clientIds = DatabasePresetQuery.getClientIDsAtAddress("M1P4X4");
        assertEquals(1, clientIds.size());
        assertEquals(clientId, clientIds.get(0).intValue());
    }

    @Test
    @DisplayName("test many client ids from postal code")
    void getManyClientIdsFromPostalCode() throws SQLException {
        cleanDb();
        Address address = createAddress("M1P4X4");
        int clientId1 = createAndInsertClient(address);
        int clientId2 = createAndInsertClient(address);
        int clientId3 = createAndInsertClient(address);
        List<Integer> clientIds = DatabasePresetQuery.getClientIDsAtAddress("M1P4X4");
        assertEquals(3, clientIds.size());
        assertTrue(clientIds.contains(clientId1));
        assertTrue(clientIds.contains(clientId2));
        assertTrue(clientIds.contains(clientId3));
    }

    @Test
    @DisplayName("test many client ids with two postal codes")
    void getManyClientIdsWithTwoPostalCodes() throws SQLException {
        cleanDb();
        Address address1 = createAddress("M1P4X4");
        Address address2 = createAddress("M1H3C3");
        int clientId1 = createAndInsertClient(address1);
        int clientId2 = createAndInsertClient(address2);
        int clientId3 = createAndInsertClient(address2);
        int clientId4 = createAndInsertClient(address1);
        List<Integer> list1 = DatabasePresetQuery.getClientIDsAtAddress("M1P4X4");
        assertEquals(2, list1.size());
        assertTrue(list1.contains(clientId1));
        assertTrue(list1.contains(clientId4));
        List<Integer> list2 = DatabasePresetQuery.getClientIDsAtAddress("M1H3C3");
        assertEquals(2, list2.size());
        assertTrue(list2.contains(clientId2));
        assertTrue(list2.contains(clientId3));
    }

    @Test
    @DisplayName("test client id from city and province")
    void getClientIdFromCityProvince() throws SQLException {
        cleanDb();
        Address address = createAddress("Scarborough", "Ontario");
        int clientId = createAndInsertClient(address);
        List<Integer> clientIds = DatabasePresetQuery.getClientIDsAtAddress("Scarborough", "Ontario");
        assertEquals(1, clientIds.size());
        assertEquals(clientId, clientIds.get(0).intValue());
    }

}
