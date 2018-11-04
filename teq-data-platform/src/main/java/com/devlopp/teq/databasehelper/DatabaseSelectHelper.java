package com.devlopp.teq.databasehelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.devlopp.teq.address.Address;
import com.devlopp.teq.address.AddressBuilder;
import com.devlopp.teq.address.IAddressBuilder;
import com.devlopp.teq.client.Client;
import com.devlopp.teq.client.ClientBuilder;
import com.devlopp.teq.client.IClientBuilder;
import com.devlopp.teq.database.DatabaseSelector;
import com.devlopp.teq.service.IServiceBuilder;
import com.devlopp.teq.service.assessment.Assessment;
import com.devlopp.teq.service.assessment.AssessmentBuilder;
import com.devlopp.teq.service.assessment.IAssessmentBuilder;

public class DatabaseSelectHelper extends DatabaseSelector {
    public static List<String> getAllTypes(String tableName) {
        List<String> list = new ArrayList<>();
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        try {
            ResultSet results = DatabaseSelector.getAllTypes(connection, tableName);
            while (results.next()) {
                list.add(results.getString("description"));
            }
        } catch (SQLException e) {
            list.clear();
        } finally {
            try {
                connection.close();
            } catch (SQLException closeConnectionException) {
                /* Do not need to do anything, connection was already closed */
            }
        }
        return list;
    }

    /**
     * Returns the ID number corresponding to the client ID type in the ClientIDType
     * table.
     * 
     * @param idTypeString description of the client ID type
     * @return ID number corresponding to the client ID type
     */
    public static int getClientIDType(String idTypeString) {
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        try {
            ResultSet results = DatabaseSelector.getAllTypes(connection, "ClientIDType");
            while (results.next()) {
                String currentIdTypeString = results.getString("description");
                if (currentIdTypeString.toLowerCase().equals(idTypeString.toLowerCase())) {
                    return results.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException closeConnectionException) {
                /* Do not need to do anything, connection was already closed */
            }
        }
        return -1;
    }

    /**
     * Connects to database, obtains and returns a client with ID number clientId
     * from the Client table. Returns <code>null</code> if clientId is invalid.
     * 
     * @param clientId
     * @return client with the ID number addressId, <code>null</code> if invalid
     *         clientId
     */
    public static Client getClient(int clientId) {
        Client client = null;
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        try {
            ResultSet results = DatabaseSelector.getClient(connection, clientId);
            while (results.next()) {
                IClientBuilder builder = new ClientBuilder();
                client = builder.setId(results.getInt("id")).setIdType(results.getInt("id_type"))
                        .setBirthDate(results.getDate("birth_date").toString())
                        .setPhoneNumber(results.getString("phone_number"))
                        .setEmailAddress(results.getString("email_address"))
                        .setAddress(getAddress(results.getInt("address_id"))).setLanguage(results.getString("language"))
                        .setConsent(results.getBoolean("consents")).create();
            }
        } catch (SQLException e) {
            client = null;
        } finally {
            try {
                connection.close();
            } catch (SQLException closeConnectionException) {
                /* Do not need to do anything, connection was already closed */
            }
        }
        return client;
    }

    /**
     * Connects to database, obtains and returns an address with ID number addressId
     * from the Address table. Returns <code>null</code> if addressId is invalid.
     * 
     * @param addressId address ID
     * @return address with the ID number addressId, <code>null</code> if invalid
     *         addressId
     */
    public static Address getAddress(int addressId) {
        Address address = null;
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        try {
            ResultSet results = DatabaseSelector.getAddress(connection, addressId);
            while (results.next()) {
                IAddressBuilder builder = new AddressBuilder();
                address = builder.setId(results.getInt("id")).setPostalCode(results.getString("postal_code"))
                        .setUnitNumber(results.getInt("unit_number")).setStreetNumber(results.getInt("street_number"))
                        .setStreetName(results.getString("street_name")).setStreetType(results.getString("street_type"))
                        .setStreetDirection(results.getString("street_direction")).setCity(results.getString("city"))
                        .setProvince(results.getString("province")).create();
            }
        } catch (SQLException e) {
            address = null;
        } finally {
            try {
                connection.close();
            } catch (SQLException closeConnectionException) {
                /* Do not need to do anything, connection was already closed */
            }
        }
        return address;
    }

    /**
     * Populates the builder object for a service with service details.
     * 
     * @param serviceId service ID
     * @param builder   builder pattern object for the service
     * @return builder pattern object with service data
     */
    private static IServiceBuilder getServiceDetails(int serviceId, IServiceBuilder builder) {
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        try {
            ResultSet results = DatabaseSelector.getServiceDetails(connection, serviceId);
            // get data from service table
            builder.setId(results.getInt("id")).setClientId(results.getInt("client_id"))
                    .setPostalCode(results.getString("postal_code")).setLanguage(results.getString("language"))
                    .setOrganizationType(results.getString("organization_type"))
                    .setReferredBy(results.getString("referred_by")).setUpdateReason(results.getString("update_reason"))
                    .setServiceType(results.getString("service_type"));
            // get essential skills
            List<String> essentialSkills = new ArrayList<String>();
            results = DatabaseSelector.getServiceEssentialSkill(connection, serviceId);
            while (results.next()) {
                essentialSkills.add(results.getString("description"));
            }
            builder.setEssentialSkills(essentialSkills);
            // get support services
            List<String> supportServices = new ArrayList<String>();
            results = DatabaseSelector.getServiceSupportService(connection, serviceId);
            while (results.next()) {
                supportServices.add(results.getString("description"));
            }
            builder.setSupportServices(supportServices);
            // get target groups
            List<String> targetGroups = new ArrayList<String>();
            results = DatabaseSelector.getServiceTargetGroup(connection, serviceId);
            while (results.next()) {
                targetGroups.add(results.getString("description"));
            }
            builder.setTargetGroups(targetGroups);
        } catch (SQLException e) {
            builder = null;
        } finally {
            try {
                connection.close();
            } catch (SQLException closeConnectionException) {
                /* Do not need to do anything, connection was already closed */
            }
        }
        return builder;
    }

    /**
     * Connects to database, obtains and returns an assessment & referrals service
     * with ID number serviceId from the Assessment table. Returns <code>null</code>
     * if serviceId is invalid.
     * 
     * @param serviceId service ID
     * @return Assessment & referrals service with the ID number serviceId,
     *         <code>null</code> if invalid serviceId
     */
    public static Assessment getAssessment(int serviceId) {
        Assessment assessment = null;
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        try {
            // get find employment responses
            String timeFrame = "";
            String minExp = "";
            String skillLevel = "";
            String intends = "";
            ResultSet results = DatabaseSelector.getAssessmentFindEmployment(connection, serviceId);
            while (results.next()) {
                timeFrame = results.getString("time_frame");
                minExp = results.getString("min_one_year");
                skillLevel = results.getString("skill_level");
                intends = results.getString("intends_to_obtain");
            }
            // get data for assessment service object
            IAssessmentBuilder builder = (IAssessmentBuilder) getServiceDetails(serviceId, new AssessmentBuilder());
            results = DatabaseSelector.getAssessmentDetails(connection, serviceId);
            builder.setStartDate(results.getDate("start_date").toString())
                    .setLanguageGoal(results.getString("language_skill_goal"))
                    .setOtherGoal(results.getString("other_skill_goal"))
                    .setIntendsCitizenship(results.getBoolean("intends_citizenship"))
                    .setReqSupportServices(results.getBoolean("req_support_service"))
                    .setPlanComplete(results.getBoolean("plan_complete"))
                    .setEndDate(results.getDate("end_date").toString());
            if (!timeFrame.isEmpty() && !minExp.isEmpty() && !skillLevel.isEmpty() && !intends.isEmpty()) {
                builder.setFindEmployment(timeFrame, minExp, skillLevel, intends);
            }
            assessment = builder.create();
            // get assessment increases
            results = DatabaseSelector.getAssessmentIncrease(connection, serviceId);
            while (results.next()) {
                assessment.addIncrease(results.getString("description"), results.getBoolean("referrals"));
            }
            // get assessment non-IRCC services
            results = DatabaseSelector.getAssessmentNonIRCCService(connection, serviceId);
            while (results.next()) {
                assessment.addNonIRCCService(results.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            assessment = null;
        } finally {
            try {
                connection.close();
            } catch (SQLException closeConnectionException) {
                /* Do not need to do anything, connection was already closed */
            }
        }
        return assessment;
    }
}
