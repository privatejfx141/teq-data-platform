package com.devlopp.teq.presetQueries;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.devlopp.teq.address.Address;
import com.devlopp.teq.app.ExcelDriver;
import com.devlopp.teq.client.Client;
import com.devlopp.teq.client.ClientBuilder;
import com.devlopp.teq.client.IClientBuilder;
import com.devlopp.teq.databasehelper.DatabaseInsertHelper;
import com.devlopp.teq.databasehelper.DatabaseSelectHelper;
import com.devlopp.teq.databasepreset.DatabasePresetQuery;
import com.devlopp.teq.databasepreset.DatabasePresetQueryHelper;

public class TestPresetQuery {

    @Test
    @DisplayName("test count number of clients for a service")
    void testClientCount() throws SQLException {
        CreateObject.cleanDb();
        int serviceId = CreateObject.createService();
        int numClients = DatabasePresetQueryHelper.getNumberOfClients(serviceId);
        assertEquals(numClients, 1);
    }

    @Test
    @DisplayName("test get birth date of client")
    void testClientBirthdate() throws SQLException {
        CreateObject.cleanDb();
        int clientId = CreateObject.createClient();
        Date birthDate = DatabasePresetQueryHelper.getBirthDate(clientId);
        assertEquals("1965-09-13", birthDate.toString());
    }

    @Test
    @DisplayName("test get age of client from birthdate")
    void testClientAge() throws SQLException {
        CreateObject.cleanDb();
        // client object for testing else if statement of getAgeOfClient method
        IClientBuilder clientBuilder = new ClientBuilder();
        Client client = clientBuilder.setId(CreateObject.clientIdSt).setIdType(1)
                .setBirthDate(ExcelDriver.fixDate("1985-12-31", "yyyy-MM-dd")).setPhoneNumber("1234567890")
                .setEmailAddress("test@gmail.com").setAddress(new Address()).setLanguage("ENG")
                .setConsent(ExcelDriver.parseYesNo("Yes")).create();
        CreateObject.clientIdSt++;
        int tempClientId = DatabaseInsertHelper.insertClient(client);

        // client object for testing else statement of getAgeOfClient method
        IClientBuilder clientBuilder3 = new ClientBuilder();
        Client client3 = clientBuilder3.setId(CreateObject.clientIdSt).setIdType(1)
                .setBirthDate(ExcelDriver.fixDate("1934-11-12", "yyyy-MM-dd")).setPhoneNumber("1234567890")
                .setEmailAddress("test@gmail.com").setAddress(new Address()).setLanguage("ENG")
                .setConsent(ExcelDriver.parseYesNo("Yes")).create();
        CreateObject.clientIdSt++;
        int tempClientId3 = DatabaseInsertHelper.insertClient(client3);
        int clientId = CreateObject.createClient();
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
        CreateObject.cleanDb();
        CreateObject.createClient();
        CreateObject.createManyClients();
        List<Integer> clientIDs = DatabaseSelectHelper.getAllClientIds();

        // test if client ids and ages are correct
        assertEquals(53,
                DatabasePresetQueryHelper.getAgeOfClient(DatabasePresetQueryHelper.getBirthDate(clientIDs.get(0))));
        assertEquals(32,
                DatabasePresetQueryHelper.getAgeOfClient(DatabasePresetQueryHelper.getBirthDate(clientIDs.get(1))));
        assertEquals(84,
                DatabasePresetQueryHelper.getAgeOfClient(DatabasePresetQueryHelper.getBirthDate(clientIDs.get(2))));
        assertEquals(54,
                DatabasePresetQueryHelper.getAgeOfClient(DatabasePresetQueryHelper.getBirthDate(clientIDs.get(3))));
    }

    @Test
    @DisplayName("test get list of client ages")
    void testGetClientAges() throws SQLException {
        CreateObject.cleanDb();
        CreateObject.createClient();
        CreateObject.createManyClients();
        List<Integer> clientAgeList = DatabasePresetQueryHelper.getListOfAges();
        assertEquals(Integer.valueOf(53), clientAgeList.get(0));
        assertEquals(Integer.valueOf(32), clientAgeList.get(1));
        assertEquals(Integer.valueOf(84), clientAgeList.get(2));
        assertEquals(Integer.valueOf(54), clientAgeList.get(3));
    }

    @Test
    @DisplayName("test get average client age")
    void testGetAverageClientAge() throws SQLException {
        CreateObject.cleanDb();
        CreateObject.createClient();
        CreateObject.createManyClients();
        double averageClientAge = DatabasePresetQuery.getAverageClientAge();
        assertEquals(averageClientAge, 55.75);

    }

    @Test
    @DisplayName("test get percentage of clients within an age range")
    void testPercentageOfClientsWithinAgeRange() throws SQLException {
        // instantiate database
        CreateObject.cleanDb();
        CreateObject.createClient();
        CreateObject.createManyClients();
        // try different age ranges
        String percentageWithinRange = DatabasePresetQuery.getPercentageOfClientsWithinAgeRange(32, 53);
        assertEquals(percentageWithinRange, "50.0%");
        String percentageWithinRange2 = DatabasePresetQuery.getPercentageOfClientsWithinAgeRange(32, 54);
        assertEquals(percentageWithinRange2, "75.0%");
    }

    @Test
    @DisplayName("test get start date and end date of a service")
    void testNumberOfUsersForAService() throws SQLException {
        CreateObject.cleanDb();
        CreateObject.createClient();
        // create service object with test values for start and end date
        int service_id1 = CreateObject.createServiceObject("1980-03-27", "2017-05-24");
        int service_id2 = CreateObject.createServiceObject("2014-04-15", "2018-07-24");
        // get start and end date of service
        Date startDateS1 = DatabasePresetQueryHelper.getServiceStartDate(service_id1, "CommunityConnections");
        Date startDateS2 = DatabasePresetQueryHelper.getServiceStartDate(service_id2, "CommunityConnections");
        Date endDateS1 = DatabasePresetQueryHelper.getServiceEndDate(service_id1, "CommunityConnections");
        Date endDateS2 = DatabasePresetQueryHelper.getServiceEndDate(service_id2, "CommunityConnections");
        assertEquals(startDateS1.toString() + " to " + endDateS1.toString(), "1980-03-27 to 2017-05-24");
        assertEquals(startDateS2.toString() + " to " + endDateS2.toString(), "2014-04-15 to 2018-07-24");
    }

    @Test
    @DisplayName("test gets a list of start dates for a service")
    void testListOfStartDates() throws SQLException {
        CreateObject.cleanDb();
        CreateObject.createClient();
        // create service object with test values for start and end date
        CreateObject.createServiceObject("1980-03-27", "2017-05-24");
        CreateObject.createServiceObject("2014-04-15", "2018-07-24");

        // test whether a list of start dates is returned for a service type
        List<java.sql.Date> list = DatabasePresetQueryHelper.getListOfStartDates("CommunityConnections");
        assertEquals("1980-03-27", list.get(0).toString());
        assertEquals("2014-04-15", list.get(1).toString());
    }

    @Test
    @DisplayName("test gets a list of end dates for a service")
    void testListOfEndDates() throws SQLException {
        CreateObject.cleanDb();
        CreateObject.createClient();
        // create service object with test values for start and end date
        CreateObject.createServiceObject("1980-03-27", "2017-05-24");
        CreateObject.createServiceObject("2014-04-15", "2018-07-24");

        // test whether a list of start dates is returned for a service type
        List<java.sql.Date> list = DatabasePresetQueryHelper.getListOfEndDates("CommunityConnections");
        assertEquals("2017-05-24", list.get(0).toString());
        assertEquals("2018-07-24", list.get(1).toString());
    }

    @Test
    @DisplayName("test gets the number of users that have used a service within the date")
    void testNumberOfUsersWithinDate() throws SQLException, ParseException {
        CreateObject.cleanDb();
        CreateObject.createClient();
        // create service object with test values for start and end date
        CreateObject.createServiceObject("1980-03-27", "2017-05-19"); // within range
        CreateObject.createServiceObject("2014-04-15", "2018-07-28"); // within range
        CreateObject.createServiceObject("2012-04-15", "2013-03-12"); // not in range
        CreateObject.createServiceObject("2019-04-15", "2020-02-05"); // not in range

        // created Date objects for the range to be checked
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
        Date startDate = sdf.parse("2014-05-08");
        Date endDate = sdf.parse("2018-12-23");

        int numberOfUsers = DatabasePresetQuery.getNumOfUsersWithinRange("CommunityConnections", startDate, endDate);
        assertEquals(numberOfUsers, 2);
    }

    @Test
    @DisplayName("test gets client ids with constraint")
    void testGetClientIDsWithConstraint() throws SQLException, ParseException {
        CreateObject.cleanDb();
        int clientID = CreateObject.createClient();
        int clientID2 = CreateObject.createClient();
        int clientID3 = CreateObject.createClient();
        // createManyClients creates 3 clients but only the last one has language as
        // "ENG"
        int clientID4 = clientID3 + 3;
        CreateObject.createManyClients();
        List<Integer> clientIDs = DatabasePresetQueryHelper.getClientIDWithConstraint("Language", "ENG");
        assertEquals(clientIDs.get(0).intValue(), clientID);
        assertEquals(clientIDs.get(1).intValue(), clientID2);
        assertEquals(clientIDs.get(2).intValue(), clientID3);
        assertEquals(clientIDs.get(3).intValue(), clientID4);
    }

    @Test
    @DisplayName("test gets client ids for a specific service")
    void testGetNumUsersWithinAgeRange() throws SQLException, ParseException {
        CreateObject.cleanDb();
        // create 3 services and get their client ids
        int serviceId = CreateObject.createService();
        int clientID = DatabaseSelectHelper.getClientID(serviceId);
        CreateObject.createService();
        CreateObject.createService();
        CreateObject.createManyClients();
        List<Integer> clientIDList = DatabasePresetQueryHelper.getClientIDsForService("CommunityConnections");
        assertEquals(clientID, clientIDList.get(0).intValue());
        assertEquals(clientID + 1, clientIDList.get(1).intValue());
        assertEquals(clientID + 2, clientIDList.get(2).intValue());
    }

    @Test
    @DisplayName("test get number of users that have used a service within an age range")
    void testGetClientIDsforService() throws SQLException, ParseException {
        CreateObject.cleanDb();
        // create services with different ages for the client
        CreateObject.createService("1985-03-12"); // 33
        CreateObject.createService("1983-04-16"); // 35
        CreateObject.createService("2015-03-22"); // 3

        int numUsers = DatabasePresetQuery.getNumUsersOfServiceWithinAgeRange("CommunityConnections", "30-45,20-24");
        assertEquals(2, numUsers);
    }

    @Test
    @DisplayName("test get languages spoken for a service")
    void testGetLanguagesSPoken() throws SQLException, ParseException {
        CreateObject.cleanDb();
        // create services with different languages for the clients
        CreateObject.createServiceWithLanguage("ENG");
        CreateObject.createServiceWithLanguage("FRN");
        CreateObject.createServiceWithLanguage("CHN");
        CreateObject.createServiceWithLanguage("ENG");
        CreateObject.createServiceWithLanguage("ENG");
        String languagesSpoken = DatabasePresetQuery.getLanguagesSpoken("CommunityConnections");
        assertEquals(languagesSpoken, "Languages Spoken\nFRN:1\nCHN:1\nENG:3");
    }

    @Test
    @DisplayName("test get number of users for all services")
    void testGetUsersForServices() throws SQLException, ParseException {
        CreateObject.cleanDb();
        CreateObject.createService(); // communityconnections
        CreateObject.createService(); // communityconnections
        CreateObject.createService(); // assessment,orientation,employment
        CreateObject.createDifferentServices();
        String userCount = DatabasePresetQuery.getNumberUsersServices();
        assertEquals(userCount,
                "Number of Users\nCommunityConnections:3\nAssessment:1\nOrientation:1\nCourse:0\nEmployment:1\nCourseEnroll:0\nCourseExit:0");
    }

}
