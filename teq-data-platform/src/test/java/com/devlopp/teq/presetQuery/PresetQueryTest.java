package com.devlopp.teq.presetQuery;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

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
import com.devlopp.teq.databasehelper.DatabasePresetQueryHelper;
import com.devlopp.teq.databasehelper.DatabaseSelectHelper;
import com.devlopp.teq.service.IServiceBuilder;
import com.devlopp.teq.service.NewcomerChildCare;
import com.devlopp.teq.service.Service;
import com.devlopp.teq.service.ServiceBuilder;
import com.devlopp.teq.service.commconn.CommunityConnections;
import com.devlopp.teq.service.commconn.CommunityConnectionsBuilder;
import com.devlopp.teq.service.commconn.ICommunityConnectionsBuilder;

public class PresetQueryTest {
	// incremented after each instance of a client
	static int clientIdSt = 0;
	
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
    
    static int createClient() {
        IAddressBuilder addressBuilder = new AddressBuilder();
        Address address = addressBuilder.setPostalCode("M1G3L2").setUnitNumber(Integer.parseInt("1606"))
                .setStreetNumber(Integer.parseInt("3050")).setStreetName("Ellesmere").setStreetType("Road")
                .setStreetDirection(ExcelDriver.fixDirection("East")).setCity("Toronto").setProvince("Ontario")
                .create();
        
        // insert address to db
        DatabaseInsertHelper.insertAddress(address);
        
        // client object
        IClientBuilder clientBuilder = new ClientBuilder();
        Client client = clientBuilder.setId(clientIdSt).setIdType(1)
                .setBirthDate(ExcelDriver.fixDate("1965-09-13", "yyyy-MM-dd")).setPhoneNumber("1234567890")
                .setEmailAddress("test@gmail.com").setAddress(address).setLanguage("ENG")
                .setConsent(ExcelDriver.parseYesNo("Yes")).create();
   
        clientIdSt++;
       
        // insert client to db with address
        int clientId = DatabaseInsertHelper.insertClient(client);
		return clientId;  
    }
    
    static void createManyClients() {
    	//for testing the different ages
        IClientBuilder clientBuilder = new ClientBuilder();
        Client client = clientBuilder.setId(clientIdSt).setIdType(1)
                .setBirthDate(ExcelDriver.fixDate("1985-12-31", "yyyy-MM-dd")).setPhoneNumber("1234567890")
                .setEmailAddress("test@gmail.com").setAddress(new Address()).setLanguage("ENG")
                .setConsent(ExcelDriver.parseYesNo("Yes")).create();
        clientIdSt++;
        DatabaseInsertHelper.insertClient(client);
        
        IClientBuilder clientBuilder2 = new ClientBuilder();
        Client client2 = clientBuilder2.setId(clientIdSt).setIdType(1)
                .setBirthDate(ExcelDriver.fixDate("1934-11-12", "yyyy-MM-dd")).setPhoneNumber("1234567890")
                .setEmailAddress("test@gmail.com").setAddress(new Address()).setLanguage("ENG")
                .setConsent(ExcelDriver.parseYesNo("Yes")).create();
        clientIdSt++;
        DatabaseInsertHelper.insertClient(client2);
        
        IClientBuilder clientBuilder3 = new ClientBuilder();
        Client client3 = clientBuilder3.setId(clientIdSt).setIdType(1)
                .setBirthDate(ExcelDriver.fixDate("1964-03-23", "yyyy-MM-dd")).setPhoneNumber("1234567890")
                .setEmailAddress("test@gmail.com").setAddress(new Address()).setLanguage("ENG")
                .setConsent(ExcelDriver.parseYesNo("Yes")).create();
        clientIdSt++;
        DatabaseInsertHelper.insertClient(client3);
    }
    
    static int createService() {
        // insert client to db with address
        int clientId = createClient();  
        
    	// community connections object
        List<String> tempList = new ArrayList<String>();
        List<NewcomerChildCare> tempList2 = new ArrayList<NewcomerChildCare>();
        ICommunityConnectionsBuilder communityConnectionsBuilder = new CommunityConnectionsBuilder();
        CommunityConnections communityConnections = (CommunityConnections) communityConnectionsBuilder.setEventType("Neighbourhood day")
        		.setMainTopic("Access to local community services").setServiceReceived("One-on-one orientation").setParticipants("10 - 20 people")
        		.setVolunteers(true).setReasonForLeave("Found employment").setStatus("Ongoing").setStartDate("2017-11-11")
        		.setEndDate("2018-03-12").setProjectedEndDate("2018-02-12").setLengthHours(23).setLengthMinutes(32)
        		.setClientId(clientId).setPostalCode("M1C 1A4").setLanguage("ENG").setOrganizationType("Public library")
        		.setReferredBy("Employer / co-worker").setUpdateReason("Amend record").setServiceType("Public library")
        		.setEssentialSkills(tempList).setSupportServices(tempList).setTargetGroups(tempList)
        		.setChildCares(tempList2).create();
        
        // insert service object to db
        int serviceId = DatabaseInsertHelper.insertCommunityConnections(communityConnections);
        return serviceId;
    }
    
    @Test
    @DisplayName("test count number of clients for a service")
    void testClientCount() throws SQLException {
        cleanDb();
        int serviceId = createService();
        int numClients = DatabasePresetQueryHelper.getNumberOfClients(serviceId);
        assertEquals(numClients, 1);
    }
    
    @Test
    @DisplayName("test get birth date of client")
    void testClientBirthdate() throws SQLException {
        cleanDb();
        int clientId = createClient();
        Date birthDate = DatabasePresetQueryHelper.getBirthDate(clientId);
        assertEquals("1965-09-13", birthDate.toString());
    }
    
    @Test
    @DisplayName("test get age of client from birthdate")
    void testClientAge() throws SQLException {
        cleanDb();
        
        // client object for testing else if statement of getAgeOfClient method
        IClientBuilder clientBuilder = new ClientBuilder();
        Client client = clientBuilder.setId(clientIdSt).setIdType(1)
                .setBirthDate(ExcelDriver.fixDate("1985-12-31", "yyyy-MM-dd")).setPhoneNumber("1234567890")
                .setEmailAddress("test@gmail.com").setAddress(new Address()).setLanguage("ENG")
                .setConsent(ExcelDriver.parseYesNo("Yes")).create();
        clientIdSt++;
        int tempClientId = DatabaseInsertHelper.insertClient(client);
        
        // client object for testing else statement of getAgeOfClient method
        IClientBuilder clientBuilder3 = new ClientBuilder();
        Client client3 = clientBuilder3.setId(clientIdSt).setIdType(1)
                .setBirthDate(ExcelDriver.fixDate("1934-11-12", "yyyy-MM-dd")).setPhoneNumber("1234567890")
                .setEmailAddress("test@gmail.com").setAddress(new Address()).setLanguage("ENG")
                .setConsent(ExcelDriver.parseYesNo("Yes")).create();
        clientIdSt++;
        int tempClientId3 = DatabaseInsertHelper.insertClient(client3);
        
        int clientId = createClient();
        Date birthDate = DatabasePresetQueryHelper.getBirthDate(clientId);
        int age = DatabasePresetQueryHelper.getAgeOfClient(birthDate);
        Date birthDate2 = DatabasePresetQueryHelper.getBirthDate(tempClientId);
        int age2 = DatabasePresetQueryHelper.getAgeOfClient(birthDate2);
        Date birthDate3 = DatabasePresetQueryHelper.getBirthDate(tempClientId3);
        int age3 = DatabasePresetQueryHelper.getAgeOfClient(birthDate3);
        assertEquals(53, age);
        assertEquals(32, age2);
        assertEquals(84, age3);
    }
    
    @Test
    @DisplayName("test get all client ids")
    void testGetClientIDs() throws SQLException {
        cleanDb();
        createClient();
        createManyClients();
        List<Integer> clientIDs = DatabasePresetQueryHelper.getClientIds();
        
        //test if client ids and ages are correct
        assertEquals(53, DatabasePresetQueryHelper.getAgeOfClient(
        		DatabasePresetQueryHelper.getBirthDate(clientIDs.get(0))));
        assertEquals(32, DatabasePresetQueryHelper.getAgeOfClient(
        		DatabasePresetQueryHelper.getBirthDate(clientIDs.get(1))));
        assertEquals(84, DatabasePresetQueryHelper.getAgeOfClient(
        		DatabasePresetQueryHelper.getBirthDate(clientIDs.get(2))));
        assertEquals(54, DatabasePresetQueryHelper.getAgeOfClient(
        		DatabasePresetQueryHelper.getBirthDate(clientIDs.get(3))));
    }
    
    @Test
    @DisplayName("test get list of client ages")
    void testGetClientAges() throws SQLException {
        cleanDb();
        createClient();
        createManyClients();
        List<Integer> clientAgeList = DatabasePresetQueryHelper.getListOfAges();
        assertEquals(Integer.valueOf(53), clientAgeList.get(0));
        assertEquals(Integer.valueOf(32), clientAgeList.get(1));
        assertEquals(Integer.valueOf(84), clientAgeList.get(2));
        assertEquals(Integer.valueOf(54), clientAgeList.get(3));  
    }
    
    @Test
    @DisplayName("test get average client age")
    void testGetAverageClientAge() throws SQLException {
        cleanDb();
        createClient();
        createManyClients();
        double averageClientAge = DatabasePresetQueryHelper.getAverageClientAge();
        assertEquals(averageClientAge, 55.75);
        
    }

    @Test
    @DisplayName("test get percentage of clients within an age range")
    void testPercentageOfClientsWithinAgeRange() throws SQLException {
        cleanDb();
        createClient();
        createManyClients();
        String percentageWithinRange = DatabasePresetQueryHelper
        		.getPercentageOfClientsWithinAgeRange(32, 53);
        assertEquals(percentageWithinRange, "50.0%");
        String percentageWithinRange2 = DatabasePresetQueryHelper
        		.getPercentageOfClientsWithinAgeRange(32, 54);
        assertEquals(percentageWithinRange2, "75.0%");
        
    }

}