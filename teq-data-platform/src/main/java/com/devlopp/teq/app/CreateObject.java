package com.devlopp.teq.app;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.devlopp.teq.address.Address;
import com.devlopp.teq.address.AddressBuilder;
import com.devlopp.teq.address.IAddressBuilder;
import com.devlopp.teq.app.ExcelDriver;
import com.devlopp.teq.client.Client;
import com.devlopp.teq.client.ClientBuilder;
import com.devlopp.teq.client.IClientBuilder;
import com.devlopp.teq.databasehelper.DatabaseDriverHelper;
import com.devlopp.teq.databasehelper.DatabaseInsertHelper;
import com.devlopp.teq.service.NewcomerChildCare;
import com.devlopp.teq.service.assessment.*;
import com.devlopp.teq.service.commconn.CommunityConnections;
import com.devlopp.teq.service.commconn.CommunityConnectionsBuilder;
import com.devlopp.teq.service.commconn.ICommunityConnectionsBuilder;
import com.devlopp.teq.service.employment.*;
import com.devlopp.teq.service.orientation.*;

// class for creating table objects for testing
public class CreateObject {
    // incremented after each instance of a client
    public static int clientIdSt = 0;

    public static void cleanDb() {
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

    public static int createClient() {
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
                .setEmailAddress("test@gmail.com").setAddress(address).setLanguage("ENG").setConsent(true).create();

        clientIdSt++;

        // insert client to db with address
        int clientId = DatabaseInsertHelper.insertClient(client);
        return clientId;
    }

    public static int createClientWithLanguage(String language) {
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
                .setEmailAddress("test@gmail.com").setAddress(address).setLanguage(language).setConsent(true).create();

        clientIdSt++;

        // insert client to db with address
        int clientId = DatabaseInsertHelper.insertClient(client);
        return clientId;
    }

    public static int createClient(String birthDate) {
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
                .setBirthDate(ExcelDriver.fixDate(birthDate, "yyyy-MM-dd")).setPhoneNumber("1234567890")
                .setEmailAddress("test@gmail.com").setAddress(address).setLanguage("ENG").setConsent(true).create();

        clientIdSt++;

        // insert client to db with address
        int clientId = DatabaseInsertHelper.insertClient(client);
        return clientId;
    }

    public static void createManyClients() {
        // for testing the different ages
        IClientBuilder clientBuilder = new ClientBuilder();
        Client client = clientBuilder.setId(clientIdSt).setIdType(1)
                .setBirthDate(ExcelDriver.fixDate("1985-12-31", "yyyy-MM-dd")).setPhoneNumber("1234567890")
                .setEmailAddress("test@gmail.com").setAddress(new Address()).setLanguage("FRE").setConsent(true)
                .create();
        clientIdSt++;
        DatabaseInsertHelper.insertClient(client);

        IClientBuilder clientBuilder2 = new ClientBuilder();
        Client client2 = clientBuilder2.setId(clientIdSt).setIdType(1)
                .setBirthDate(ExcelDriver.fixDate("1934-11-12", "yyyy-MM-dd")).setPhoneNumber("1234567890")
                .setEmailAddress("test@gmail.com").setAddress(new Address()).setLanguage("RUS").setConsent(true)
                .create();
        clientIdSt++;
        DatabaseInsertHelper.insertClient(client2);

        IClientBuilder clientBuilder3 = new ClientBuilder();
        Client client3 = clientBuilder3.setId(clientIdSt).setIdType(1)
                .setBirthDate(ExcelDriver.fixDate("1964-03-23", "yyyy-MM-dd")).setPhoneNumber("1234567890")
                .setEmailAddress("test@gmail.com").setAddress(new Address()).setLanguage("ENG").setConsent(true)
                .create();
        clientIdSt++;
        DatabaseInsertHelper.insertClient(client3);
    }

    public static int createService() {
        // insert client to db with address
        int clientId = createClient();

        // community connections object
        List<String> tempList = new ArrayList<String>();
        List<NewcomerChildCare> tempList2 = new ArrayList<NewcomerChildCare>();
        ICommunityConnectionsBuilder communityConnectionsBuilder = new CommunityConnectionsBuilder();
        CommunityConnections communityConnections = (CommunityConnections) communityConnectionsBuilder
                .setEventType("Neighbourhood day").setMainTopic("Access to local community services")
                .setServiceReceived("One-on-one orientation").setParticipants("10 - 20 people").setVolunteers(true)
                .setReasonForLeave("Found employment").setStatus("Ongoing").setStartDate("2017-11-11")
                .setEndDate("2018-03-12").setProjectedEndDate("2018-02-12").setLengthHours(23).setLengthMinutes(32)
                .setClientId(clientId).setPostalCode("M1C 1A4").setLanguage("ENG").setOrganizationType("Public library")
                .setReferredBy("Employer / co-worker").setUpdateReason("Amend record").setServiceType("Public library")
                .setEssentialSkills(tempList).setSupportServices(tempList).setTargetGroups(tempList)
                .setChildCares(tempList2).create();

        // insert service object to db
        int serviceId = DatabaseInsertHelper.insertCommunityConnections(communityConnections);
        return serviceId;
    }

    public static int createServiceWithLanguage(String language) {
        // insert client to db with address
        int clientId = createClientWithLanguage(language);

        // community connections object
        List<String> tempList = new ArrayList<String>();
        List<NewcomerChildCare> tempList2 = new ArrayList<NewcomerChildCare>();
        ICommunityConnectionsBuilder communityConnectionsBuilder = new CommunityConnectionsBuilder();
        CommunityConnections communityConnections = (CommunityConnections) communityConnectionsBuilder
                .setEventType("Neighbourhood day").setMainTopic("Access to local community services")
                .setServiceReceived("One-on-one orientation").setParticipants("10 - 20 people").setVolunteers(true)
                .setReasonForLeave("Found employment").setStatus("Ongoing").setStartDate("2017-11-11")
                .setEndDate("2018-03-12").setProjectedEndDate("2018-02-12").setLengthHours(23).setLengthMinutes(32)
                .setClientId(clientId).setPostalCode("M1C 1A4").setLanguage("ENG").setOrganizationType("Public library")
                .setReferredBy("Employer / co-worker").setUpdateReason("Amend record").setServiceType("Public library")
                .setEssentialSkills(tempList).setSupportServices(tempList).setTargetGroups(tempList)
                .setChildCares(tempList2).create();

        // insert service object to db
        int serviceId = DatabaseInsertHelper.insertCommunityConnections(communityConnections);
        return serviceId;
    }

    public static int createService(String birthDate) {
        // insert client to db with address
        int clientId = createClient(birthDate);

        // community connections object
        List<String> tempList = new ArrayList<String>();
        List<NewcomerChildCare> tempList2 = new ArrayList<NewcomerChildCare>();
        ICommunityConnectionsBuilder communityConnectionsBuilder = new CommunityConnectionsBuilder();
        CommunityConnections communityConnections = (CommunityConnections) communityConnectionsBuilder
                .setEventType("Neighbourhood day").setMainTopic("Access to local community services")
                .setServiceReceived("One-on-one orientation").setParticipants("10 - 20 people").setVolunteers(true)
                .setReasonForLeave("Found employment").setStatus("Ongoing").setStartDate("2017-11-11")
                .setEndDate("2018-03-12").setProjectedEndDate("2018-02-12").setLengthHours(23).setLengthMinutes(32)
                .setClientId(clientId).setPostalCode("M1C 1A4").setLanguage("ENG").setOrganizationType("Public library")
                .setReferredBy("Employer / co-worker").setUpdateReason("Amend record").setServiceType("Public library")
                .setEssentialSkills(tempList).setSupportServices(tempList).setTargetGroups(tempList)
                .setChildCares(tempList2).create();

        // insert service object to db
        int serviceId = DatabaseInsertHelper.insertCommunityConnections(communityConnections);
        return serviceId;
    }

    public static int createServiceObject(String startDate, String endDate) {
        // insert client to db with address
        int clientId = createClient();

        // community connections object
        List<String> tempList = new ArrayList<String>();
        List<NewcomerChildCare> tempList2 = new ArrayList<NewcomerChildCare>();
        ICommunityConnectionsBuilder communityConnectionsBuilder = new CommunityConnectionsBuilder();
        CommunityConnections communityConnections = (CommunityConnections) communityConnectionsBuilder
                .setEventType("Neighbourhood day").setMainTopic("Access to local community services")
                .setServiceReceived("One-on-one orientation").setParticipants("10 - 20 people").setVolunteers(true)
                .setReasonForLeave("Found employment").setStatus("Ongoing").setStartDate(startDate).setEndDate(endDate)
                .setProjectedEndDate("2018-02-12").setLengthHours(23).setLengthMinutes(32).setClientId(clientId)
                .setPostalCode("M1C 1A4").setLanguage("ENG").setOrganizationType("Public library")
                .setReferredBy("Employer / co-worker").setUpdateReason("Amend record").setServiceType("Public library")
                .setEssentialSkills(tempList).setSupportServices(tempList).setTargetGroups(tempList)
                .setChildCares(tempList2).create();

        // insert service object to db
        int serviceId = DatabaseInsertHelper.insertCommunityConnections(communityConnections);
        return serviceId;
    }

    public static void createDifferentServices() {
        // create an object for most of the services
        List<String> tempList = new ArrayList<String>();
        Map<String, Boolean> tempMap = new HashMap<String, Boolean>();
        IAssessmentBuilder assessmentBuilder = new AssessmentBuilder();
        Assessment assessment = (Assessment) assessmentBuilder.setStartDate("2018-4-05").setLanguageGoal("Goal")
                .setOtherGoal("Other").setIntendsCitizenship(true).setReqSupportServices(true).setPlanComplete(false)
                .setEndDate("2019-03-28").setFindEmployment("2015-04-03", "4", "4", "true").setIncrease(tempMap)
                .setNonIRCCServices(tempList).setId(4).create();
        DatabaseInsertHelper.insertAssessment(assessment);

        IOrientationBuilder orientationBuilder = new OrientationBuilder();
        Orientation orientation = (Orientation) orientationBuilder.setServiceReceived("Service").setTotalLength("24")
                .setLengthHours(45).setLengthMinutes(54).setNumberOfClients("200").setEndDate("2019-03-28").create();
        DatabaseInsertHelper.insertOrientation(orientation);

        IEmploymentBuilder employmentBuilder = new EmploymentBuilder();
        Employment employment = (Employment) employmentBuilder.setRegistration(true).setReferralDate("")
                .setReferralTo("").setEmploymentStatus("").setEducationStatus("").setOccupationCanada("")
                .setOccupationIntended("").setInterventionType("").setTimeSpentHours(1).setTimeSpentMinutes(1).create();
        DatabaseInsertHelper.insertEmployment(employment);

    }
}