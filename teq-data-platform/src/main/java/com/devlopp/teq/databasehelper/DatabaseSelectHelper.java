package com.devlopp.teq.databasehelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.devlopp.teq.address.*;
import com.devlopp.teq.client.*;
import com.devlopp.teq.database.DatabaseSelector;
import com.devlopp.teq.service.*;
import com.devlopp.teq.service.assessment.*;
import com.devlopp.teq.service.commconn.*;
import com.devlopp.teq.service.employment.*;
import com.devlopp.teq.service.orientation.*;

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
     * @param clientId unique ID of the client
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
     * @param addressId ID of the address record
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
     * @param serviceId ID of the service
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
            List<String> essentialSkills = new ArrayList<>();
            results = DatabaseSelector.getServiceEssentialSkill(connection, serviceId);
            while (results.next()) {
                essentialSkills.add(results.getString("description"));
            }
            builder.setEssentialSkills(essentialSkills);
            // get support services
            List<String> supportServices = new ArrayList<>();
            results = DatabaseSelector.getServiceSupportService(connection, serviceId);
            while (results.next()) {
                supportServices.add(results.getString("description"));
            }
            builder.setSupportServices(supportServices);
            // get target groups
            List<String> targetGroups = new ArrayList<>();
            results = DatabaseSelector.getServiceTargetGroup(connection, serviceId);
            while (results.next()) {
                targetGroups.add(results.getString("description"));
            }
            builder.setTargetGroups(targetGroups);
            // get newcomer child care responses
            List<NewcomerChildCare> childCares = new ArrayList<>();
            results = DatabaseSelector.getServiceNewcomerChildCare(connection, serviceId);
            while (results.next()) {
                int childCareId = results.getInt("id");
                String childCareAge = results.getString("age");
                String childCareType = results.getString("care_type");
                childCares.add(new NewcomerChildCare(childCareId, serviceId, childCareAge, childCareType));
            }
            builder.setChildCares(childCares);
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
     * @param serviceId ID of the assessment service
     * @return Assessment & referrals service with the ID number serviceId,
     *         <code>null</code> if invalid serviceId
     */
    public static Assessment getAssessment(int serviceId) {
        Assessment assessment = null;
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        IAssessmentBuilder builder = (IAssessmentBuilder) getServiceDetails(serviceId, new AssessmentBuilder());
        try {
            // get data for assessment service object
            ResultSet results = DatabaseSelector.getAssessmentDetails(connection, serviceId);
            builder.setStartDate(results.getDate("start_date").toString())
                    .setLanguageGoal(results.getString("language_skill_goal"))
                    .setOtherGoal(results.getString("other_skill_goal"))
                    .setIntendsCitizenship(results.getBoolean("intends_citizenship"))
                    .setReqSupportServices(results.getBoolean("req_support_service"))
                    .setPlanComplete(results.getBoolean("plan_complete"))
                    .setEndDate(results.getDate("end_date").toString());
            // get find employment responses
            results = DatabaseSelector.getAssessmentFindEmployment(connection, serviceId);
            while (results.next()) {
                String timeFrame = results.getString("time_frame");
                String minExp = results.getString("min_one_year");
                String skillLevel = results.getString("skill_level");
                String intends = results.getString("intends_to_obtain");
                builder.setFindEmployment(timeFrame, minExp, skillLevel, intends);
            }
            // create assessment service object
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

    public static CommunityConnections getCommunityConnection(int communityConnection) {
        CommunityConnections community = null;
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        
        
        return community;
    }
    
    public static Orientation getOrientation(int serviceId) {
        Orientation orientation = null;
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        IOrientationBuilder builder = (IOrientationBuilder) getServiceDetails(serviceId, new OrientationBuilder());
        try {
            // get orientation details
            ResultSet results = DatabaseSelector.getOrientationDetails(connection, serviceId);
            orientation = builder.setServiceReceived(results.getString("service_recieved"))
                .setTotalLength(results.getString("total_length"))
                .setLengthHours(results.getInt("length_hours"))
                .setLengthMinutes(results.getInt("length_minutes"))
                .setNumberOfClients(results.getInt("number_of_clients"))
                .setEndDate(results.getDate("end_date").toString())
                .create();
            // add orientation topics
            results = DatabaseSelector.getOrientationTopic(connection, serviceId);
            while (results.next()) {
                orientation.addTopic(results.getString("description"), results.getBoolean("referrals"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            orientation = null;
        } finally {
            try {
                connection.close();
            } catch (SQLException closeConnectionException) {
                /* Do not need to do anything, connection was already closed */
            }
        }
        return orientation;
    }
    
    /**
     * Connects to database, obtains and returns an employment service with ID
     * number serviceId from the Employment table. Returns <code>null</code> if
     * serviceId is invalid.
     * 
     * @param serviceId ID of the employment service
     * @return Employment service with the ID number serviceId, <code>null</code> if
     *         invalid serviceId
     */
    public static Employment getEmployment(int serviceId) {
        Employment employment = null;
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        // populate builder with general service information
        IEmploymentBuilder builder = (IEmploymentBuilder) getServiceDetails(serviceId, new EmploymentBuilder());
        // populate builder with employment information
        try {
            ResultSet results = DatabaseSelector.getEmploymentDetails(connection, serviceId);
            while (results.next()) {
                builder.setRegistration(results.getBoolean("registration"))
                        .setReferralTo(results.getString("referral_to"))
                        .setReferralDate(results.getDate("referral_date").toString())
                        .setEmploymentStatus(results.getString("employment_status"))
                        .setEducationStatus(results.getString("education_status"))
                        .setOccupationCanada(results.getString("occupation_canada"))
                        .setOccupationIntended(results.getString("occupation_intend"))
                        .setInterventionType(results.getString("intervention_type"))
                        .setTimeSpentHours(results.getInt("time_spent_hours"))
                        .setTimeSpentMinutes(results.getInt("time_spent_minutes"));
            }
            // get LTI responses, if any
            results = DatabaseSelector.getEmploymentLTI(connection, serviceId);
            while (results.next()) {
                LongTermIntervention lti = new LongTermInterventionBuilder()
                        .setServiceRecieved(results.getString("service_recieved"))
                        .setStatus(results.getString("status")).setReasonForLeave(results.getString("reason_for_leave"))
                        .setStartDate(results.getDate("start_date").toString())
                        .setEndDate(results.getDate("end_date").toString())
                        .setEmployerSize(results.getInt("employer_size"))
                        .setPlacement(results.getString("placement_was"))
                        .setAverageHoursPerWeek(results.getString("avg_hours_per_week"))
                        .setMetMentorAt(results.getString("hours_per_week"))
                        .setHoursPeerWeek(results.getInt("hours_per_week"))
                        .setProfession(results.getString("profession")).build();
                builder.setLongTermIntervention(lti);
            }
            // create employment service object, then add STI responses if any
            employment = builder.create();
            results = DatabaseSelector.getEmploymentSTI(connection, serviceId);
            while (results.next()) {
                String stiService = results.getString("service_recieved");
                String stiDate = results.getDate("date").toString();
                ShortTermIntervention sti = new ShortTermIntervention(stiService, stiDate);
                employment.addShortTermIntervention(sti);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            employment = null;
        }
        return employment;
    }
}
