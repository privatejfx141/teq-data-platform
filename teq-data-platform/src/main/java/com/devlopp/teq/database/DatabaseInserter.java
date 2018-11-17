package com.devlopp.teq.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Map;

import com.devlopp.teq.address.Address;
import com.devlopp.teq.client.Client;
import com.devlopp.teq.course.Course;
import com.devlopp.teq.course.CourseContact;
import com.devlopp.teq.service.NewcomerChildCare;
import com.devlopp.teq.service.Service;
import com.devlopp.teq.service.assessment.Assessment;
import com.devlopp.teq.service.assessment.FindEmployment;
import com.devlopp.teq.service.commconn.CommunityConnections;
import com.devlopp.teq.service.courseenroll.CourseEnroll;
import com.devlopp.teq.service.courseexit.CourseExit;
import com.devlopp.teq.service.employment.Employment;
import com.devlopp.teq.service.employment.LongTermIntervention;
import com.devlopp.teq.service.employment.ShortTermIntervention;
import com.devlopp.teq.service.orientation.Orientation;
import com.devlopp.teq.sql.SQLDriver;

public class DatabaseInserter {

    protected static int insertNewUser(Connection connection, String username, String password, int roleId)
            throws DatabaseInsertException {
        String sql = "INSERT INTO User (username, password, role_id) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setInt(3, roleId);
            if (statement.executeUpdate() > 0) {
                ResultSet uniqueKey = statement.getGeneratedKeys();
                if (uniqueKey.next()) {
                    return uniqueKey.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new DatabaseInsertException();
    }
    
    protected static int insertNewUserRole(Connection connection, String roleName) throws DatabaseInsertException {
        String sql = "INSERT INTO UserRole (name) VALUES (?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, roleName);
            if (statement.executeUpdate() > 0) {
                ResultSet uniqueKey = statement.getGeneratedKeys();
                if (uniqueKey.next()) {
                    return uniqueKey.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new DatabaseInsertException();
    }

    /**
     * Inserts a client (and its address) into the TEQ database and returns the
     * client ID if insertion was successful.
     * 
     * @param connection connection to the TEQ database
     * @param client     client info to insert
     * @return client ID (primary key) of the inserted client
     * @throws DatabaseInsertException on failure of insert
     */
    protected static int insertClient(Connection connection, Client client) throws DatabaseInsertException {
        // insert address object from client into database
        int addressId = insertAddress(connection, client.getAddress());
        // insert client object into database
        String sql = "INSERT INTO Client ("
                + "id,id_type,birth_date,phone_number,email_address,address_id,language,consents"
                + ") VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, client.getId());
            statement.setInt(2, client.getIdType());
            statement.setDate(3, SQLDriver.parseDate(client.getBirthDate()));
            statement.setString(4, client.getPhoneNumber());
            statement.setString(5, client.getEmailAddress());
            statement.setInt(6, addressId);
            statement.setString(7, client.getLanguage());
            statement.setBoolean(8, client.getConsent());
            if (statement.executeUpdate() > 0) {
                ResultSet uniqueKey = statement.getGeneratedKeys();
                if (uniqueKey.next()) {
                    return uniqueKey.getInt(1);
                }
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        throw new DatabaseInsertException();
    }

    /**
     * Connects to the TEQ database and inserts an address to the Address table.
     * Returns the address ID if successful, -1 otherwise.
     * 
     * @param connection connection to the TEQ database
     * @param address    the address object that describes the address
     * @return the address ID number, -1 otherwise
     * @throws DatabaseInsertException on failure of insert
     */
    protected static int insertAddress(Connection connection, Address address) throws DatabaseInsertException {
        String sql = "INSERT INTO Address("
                + "postal_code,unit_number,street_number,street_name,street_type,street_direction,city,province"
                + ") VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, address.getPostalCode());
            statement.setInt(2, address.getUnitNumber());
            statement.setInt(3, address.getStreetNumber());
            statement.setString(4, address.getStreetName());
            statement.setString(5, address.getStreetType());
            statement.setString(6, address.getStreetDirection());
            statement.setString(7, address.getCity());
            statement.setString(8, address.getProvince());
            if (statement.executeUpdate() > 0) {
                ResultSet uniqueKey = statement.getGeneratedKeys();
                if (uniqueKey.next()) {
                    return uniqueKey.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new DatabaseInsertException();
    }

    // -- Service supertype insertions --
    /**
     * Inserts a service into the TEQ database and returns the service ID if
     * insertion was successful.
     * 
     * @param connection connection to the TEQ database
     * @param service    service info to insert
     * @return service ID (primary key) of the inserted service
     * @throws DatabaseInsertException on failure of insert
     */
    protected static int insertService(Connection connection, Service service) throws DatabaseInsertException {
        // insert service details into database
        int serviceId = insertServiceDetails(connection, service);
        try {
            // insert all support services into database
            for (String supportService : service.getSupportServices()) {
                int typeId = DatabaseSelector.getTypeId(connection, "SupportService", supportService);
                insertServiceSupportService(connection, serviceId, typeId);
            }
            // insert all essential skills into database
            for (String essentialSkill : service.getEssentialSkills()) {
                int typeId = DatabaseSelector.getTypeId(connection, "EssentialSkill", essentialSkill);
                insertServiceEssentialSkill(connection, serviceId, typeId);
            }
            // insert all target groups into database
            for (String targetGroup : service.getTargetGroups()) {
                int typeId = DatabaseSelector.getTypeId(connection, "TargetGroup", targetGroup);
                insertServiceTargetGroup(connection, serviceId, typeId);
            }
            // insert all newcomer child care responses into database
            for (NewcomerChildCare childCare : service.getNewcomerChildCare()) {
                insertServiceNewcomerChildCare(connection, serviceId, childCare);
            }
        } catch (SQLException e) {
            throw new DatabaseInsertException();
        }
        // return service id
        return serviceId;
    }

    /**
     * Inserts a service supertype into the TEQ database and returns the service ID
     * if insertion was successful.
     * 
     * @param connection connection to the TEQ database
     * @param service    service info to insert
     * @return service ID (primary key) of the inserted service
     * @throws DatabaseInsertException on failure of insert
     */
    private static int insertServiceDetails(Connection connection, Service service) throws DatabaseInsertException {
        String sql = "INSERT INTO Service ("
                + "client_id,postal_code,language,organization_type,referred_by,update_reason,service_type"
                + ") VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, service.getClientId());
            statement.setString(2, service.getPostalCode());
            statement.setString(3, service.getLanguage());
            statement.setString(4, service.getOrganizationType());
            statement.setString(5, service.getReferredBy());
            statement.setString(6, service.getUpdateReason());
            statement.setString(7, service.getServiceType());
            if (statement.executeUpdate() > 0) {
                ResultSet uniqueKey = statement.getGeneratedKeys();
                if (uniqueKey.next()) {
                    return uniqueKey.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new DatabaseInsertException();
    }

    /**
     * Inserts a service newcomer child care response into the TEQ database and
     * returns the service ID if insertion was successful.
     * 
     * @param connection connection to the TEQ database
     * @param serviceId
     * @param childCare  newcomer child care response to insert
     * @return record ID of the newcomer child care response
     * @throws DatabaseInsertException on failure of insert
     */
    private static int insertServiceNewcomerChildCare(Connection connection, int serviceId, NewcomerChildCare childCare)
            throws DatabaseInsertException {
        String sql = "INSERT INTO NewcomerChildCare (service_id, age, care_type) " + "VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, serviceId);
            statement.setString(2, childCare.getAge());
            statement.setString(3, childCare.getCareType());
            if (statement.executeUpdate() > 0) {
                ResultSet uniqueKey = statement.getGeneratedKeys();
                if (uniqueKey.next()) {
                    return uniqueKey.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new DatabaseInsertException();
    }

    /**
     * Inserts a service type relationship into an associative entity.
     * 
     * @param connection   connection to the TEQ database
     * @param tableName    name of the associative entity table
     * @param serviceIdCol name of the service ID column
     * @param typeIdCol    name of the type ID column
     * @param serviceId    ID of the service
     * @param typeId       ID of the type
     * @return ID of the service type relationship
     * @throws DatabaseInsertException on failure of insert
     */
    protected static int insertServiceRelationship(Connection connection, String tableName, String serviceIdCol,
            String typeIdCol, int serviceId, int typeId) throws DatabaseInsertException {
        String sql = String.format("INSERT INTO %s(%s,%s) VALUES(?,?)", tableName, serviceIdCol, typeIdCol);
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, serviceId);
            statement.setInt(2, typeId);
            if (statement.executeUpdate() > 0) {
                ResultSet uniqueKey = statement.getGeneratedKeys();
                if (uniqueKey.next()) {
                    return uniqueKey.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new DatabaseInsertException();
    }

    /**
     * Inserts a service type relationship with a referral attribute into an
     * associative entity.
     * 
     * @param connection   connection to the TEQ database
     * @param tableName    name of the associative entity table
     * @param serviceIdCol name of the service ID column
     * @param typeIdCol    name of the type ID column
     * @param serviceId    ID of the service
     * @param typeId       ID of the type
     * @param referrals    whether or not there were referrals
     * @return ID of the service type relationship
     * @throws DatabaseInsertException on failure of insert
     */
    protected static int insertServiceRelationship(Connection connection, String tableName, String serviceIdCol,
            String typeIdCol, int serviceId, int typeId, boolean referrals) throws DatabaseInsertException {
        String sql = String.format("INSERT INTO %s(%s,%s,referrals) VALUES(?,?,?)", tableName, serviceIdCol, typeIdCol);
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, serviceId);
            statement.setInt(2, typeId);
            statement.setBoolean(3, referrals);
            if (statement.executeUpdate() > 0) {
                ResultSet uniqueKey = statement.getGeneratedKeys();
                if (uniqueKey.next()) {
                    return uniqueKey.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new DatabaseInsertException();
    }

    /**
     * Inserts a service essential skill relationship.
     * 
     * @param connection connection to the TEQ database
     * @param serviceId  service ID
     * @param skillId    essential skill ID
     * @return ID of the service essential skill relationship
     * @throws DatabaseInsertException on failure of insert
     */
    protected static int insertServiceEssentialSkill(Connection connection, int serviceId, int skillId)
            throws DatabaseInsertException {
        return insertServiceRelationship(connection, "ServiceEssentialSkill", "service_id", "essential_skill_id",
                serviceId, skillId);
    }

    /**
     * Inserts a service target group relationship.
     * 
     * @param connection connection to the TEQ database
     * @param serviceId  service ID
     * @param groupId    target group ID
     * @return ID of the service target group relationship
     * @throws DatabaseInsertException on failure of insert
     */
    protected static int insertServiceTargetGroup(Connection connection, int serviceId, int groupId)
            throws DatabaseInsertException {
        return insertServiceRelationship(connection, "ServiceTargetGroup", "service_id", "target_group_id", serviceId,
                groupId);
    }

    /**
     * Inserts a service support service relationship.
     * 
     * @param connection connection to the TEQ database
     * @param serviceId  service ID
     * @param supportId  support service ID
     * @return ID of the service support service relationship
     * @throws DatabaseInsertException on failure of insert
     */
    protected static int insertServiceSupportService(Connection connection, int serviceId, int supportId)
            throws DatabaseInsertException {
        return insertServiceRelationship(connection, "ServiceSupportService", "service_id", "support_service_id",
                serviceId, supportId);
    }

    // -- Assessment service insertions --
    /**
     * Inserts an assessment service into the TEQ database and returns the service
     * ID if insertion was successful.
     *
     * @param connection connection to the TEQ database
     * @param service    assessment service info to insert
     * @return service ID (primary key) of the inserted assessment service
     * @throws DatabaseInsertException on failure of insert
     */
    protected static int insertAssessment(Connection connection, Assessment assessment) throws DatabaseInsertException {
        // insert assessment details into assessment table
        int assessmentId = insertAssessmentDetails(connection, assessment);
        try {
            // insert find employment responses
            FindEmployment findEmployment = assessment.getFindEmployment();
            if (assessment.getFindEmployment() != null) {
                insertAssessmentFindEmployment(connection, assessmentId, findEmployment);
            }
            // insert all improvements into database
            for (Map.Entry<String, Boolean> increase : assessment.getIncreases().entrySet()) {
                int typeId = DatabaseSelector.getTypeId(connection, "Increase", increase.getKey());
                insertAssessmentIncrease(connection, assessmentId, typeId, increase.getValue());
            }
            // insert all non-IRCC services into database
            for (String service : assessment.getNonIRCCServices()) {
                int typeId = DatabaseSelector.getTypeId(connection, "NonIRCCService", service);
                insertAssessmentNonIRCCService(connection, assessmentId, typeId);
            }
        } catch (SQLException e) {
            throw new DatabaseInsertException();
        }
        // return service ID generated by database
        return assessmentId;
    }

    /**
     * Inserts the assessment service details into the TEQ database and returns the
     * service ID if insertion was successful.
     *
     * @param connection connection to the TEQ database
     * @param service    assessment service to insert
     * @return service ID (primary key) of the inserted assessment service
     * @throws DatabaseInsertException on failure of insert
     */
    private static int insertAssessmentDetails(Connection connection, Assessment assessment)
            throws DatabaseInsertException {
        int serviceId = insertService(connection, assessment);
        String sql = "INSERT INTO Assessment (" + "service_id, start_date, language_skill_goal, other_skill_goal, "
                + "intends_citizenship, req_support_service, plan_complete, end_date"
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, serviceId);
            statement.setDate(2, SQLDriver.parseDate(assessment.getStartDate()));
            statement.setString(3, assessment.getLanguageSkillGoal());
            statement.setString(4, assessment.getOtherSkillGoal());
            statement.setBoolean(5, assessment.wantsCitizenship());
            statement.setBoolean(6, assessment.reqSupportService());
            statement.setBoolean(7, assessment.isPlanComplete());
            statement.setDate(8, SQLDriver.parseDate(assessment.getEndDate()));
            if (statement.executeUpdate() > 0) {
                ResultSet uniqueKey = statement.getGeneratedKeys();
                if (uniqueKey.next()) {
                    return uniqueKey.getInt(1);
                }
            }
        } catch (SQLException | ParseException e) {
            throw new DatabaseInsertException();
        }
        return serviceId;
    }

    /**
     * Inserts an assessment improvement relationship.
     * 
     * @param connection connection to the TEQ database
     * @param serviceId  service ID
     * @param increaseId increase/improvement ID
     * @param referrals  whether or not there were referrals
     * @return ID of the assessment improvement relationship
     * @throws DatabaseInsertException on failure of insert
     */
    protected static int insertAssessmentIncrease(Connection connection, int serviceId, int increaseId,
            boolean referrals) throws DatabaseInsertException {
        return insertServiceRelationship(connection, "AssessmentIncrease", "assessment_id", "increase_id", serviceId,
                increaseId, referrals);
    }

    /**
     * Inserts an assessment non-IRCC service relationship.
     * 
     * @param connection connection to the TEQ database
     * @param serviceId  service ID
     * @param nonIRCCid  non-IRCC service ID
     * @return ID of the assessment non-IRCC service relationship
     * @throws DatabaseInsertException on failure of insert
     */
    protected static int insertAssessmentNonIRCCService(Connection connection, int serviceId, int nonIRCCid)
            throws DatabaseInsertException {
        return insertServiceRelationship(connection, "AssessmentNonIRCCService", "assessment_id", "non_ircc_service_id",
                serviceId, nonIRCCid);
    }

    /**
     * Inserts the assessment service find employment responses into the TEQ
     * database and returns the service ID if insertion was successful.
     * 
     * @param connection     connection to the TEQ database
     * @param serviceId      ID of the assessment service
     * @param findEmployment find employment responses to insert
     * @return service ID (primary key) of the assessment service
     * @throws DatabaseInsertException on failure of insert
     */
    private static int insertAssessmentFindEmployment(Connection connection, int serviceId,
            FindEmployment findEmployment) throws DatabaseInsertException {
        String sql = "INSERT INTO AssessmentFindEmployment ("
                + "assessment_id, time_frame, min_one_year, skill_level, intends_to_obtain"
                + ") VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, serviceId);
            statement.setString(2, findEmployment.getTimeFrame());
            statement.setString(3, findEmployment.getMinExperience());
            statement.setString(4, findEmployment.getSkillLevel());
            statement.setString(5, findEmployment.getIntendsToObtain());
            if (statement.executeUpdate() > 0) {
                ResultSet uniqueKey = statement.getGeneratedKeys();
                if (uniqueKey.next()) {
                    return uniqueKey.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseInsertException();
        }
        return serviceId;
    }

    /**
     * Inserts the community connections service into the TEQ database and returns
     * the service ID if insertion was successful.
     * 
     * @param connection connection to the TEQ database
     * @param community  community connections service to insert
     * @return service ID (primary key) of the inserted community connections
     *         service
     * @throws DatabaseInsertException on failure of insert
     */
    protected static int insertCommunityConnections(Connection connection, CommunityConnections community)
            throws DatabaseInsertException {
        int communityId = insertCommunityConnectionsDetails(connection, community);
        return communityId;
    }

    /**
     * Inserts the community connections service details into the TEQ database and
     * returns the service ID if insertion was successful.
     * 
     * @param connection connection to the TEQ database
     * @param community  community connections service to insert
     * @return service ID (primary key) of the inserted community connections
     *         service
     * @throws DatabaseInsertException on failure of insert
     */
    private static int insertCommunityConnectionsDetails(Connection connection, CommunityConnections community)
            throws DatabaseInsertException {
        int communityId = insertService(connection, community);
        String sql = "INSERT INTO CommunityConnections ("
                + "service_id, event_type, main_topic, service_recieved, participants, volunteers, status, "
                + "reason_for_leave, start_date, end_date, projected_end_date, length_hours, length_minutes"
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, communityId);
            statement.setString(2, community.getEventType());
            statement.setString(3, community.getMainTopic());
            statement.setString(4, community.getServiceReceived());
            statement.setString(5, community.getParticipants());
            statement.setBoolean(6, community.getHasVolunteers());
            statement.setString(7, community.getStatus());
            statement.setString(8, community.getReasonForLeave());
            statement.setDate(9, SQLDriver.parseDate(community.getStartDate()));
            statement.setDate(10, SQLDriver.parseDate(community.getEndDate()));
            statement.setDate(11, SQLDriver.parseDate(community.getProjectedEndDate()));
            statement.setInt(12, community.getLengthHours());
            statement.setInt(13, community.getlengthMinutes());
            if (statement.executeUpdate() > 0) {
                ResultSet uniqueKey = statement.getGeneratedKeys();
                if (uniqueKey.next()) {
                    return uniqueKey.getInt(1);
                }
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        throw new DatabaseInsertException();
    }

    // -- Orientation service insertions --
    /**
     * Inserts the orientation service into the TEQ database and returns the service
     * ID if insertion was successful.
     * 
     * @param connection  connection to the TEQ database
     * @param orientation orientation service to insert
     * @return service ID (primary key) of the inserted orientation service
     * @throws DatabaseInsertException on failure of insert
     */
    protected static int insertOrientation(Connection connection, Orientation orientation)
            throws DatabaseInsertException {
        int orientationId = insertOrientationDetails(connection, orientation);
        // insert all topics into database
        try {
            for (Map.Entry<String, Boolean> topic : orientation.getTopics().entrySet()) {
                int typeId = DatabaseSelector.getTypeId(connection, "Topic", topic.getKey());
                insertAssessmentIncrease(connection, orientationId, typeId, topic.getValue());
            }
        } catch (SQLException e) {
            throw new DatabaseInsertException();
        }
        return orientationId;
    }

    /**
     * Inserts the orientation service details into the TEQ database and returns the
     * service ID if insertion was successful.
     * 
     * @param connection  connection to the TEQ database
     * @param orientation orientation service to insert
     * @return service ID (primary key) of the inserted orientation service
     * @throws DatabaseInsertException on failure of insert
     */
    private static int insertOrientationDetails(Connection connection, Orientation orientation)
            throws DatabaseInsertException {
        int serviceId = insertService(connection, orientation);
        String sql = "INSERT INTO Orientation ("
                + "service_id, service_recieved, total_length, length_hours, length_minutes, "
                + "number_of_clients, end_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, serviceId);
            statement.setString(2, orientation.getServiceReceived());
            statement.setString(3, orientation.getTotalLength());
            statement.setInt(4, orientation.getLengthHours());
            statement.setInt(5, orientation.getLengthMinutes());
            statement.setString(6, orientation.getNumberOfClients());
            statement.setDate(7, SQLDriver.parseDate(orientation.getEndDate()));
            if (statement.executeUpdate() > 0) {
                ResultSet uniqueKey = statement.getGeneratedKeys();
                if (uniqueKey.next()) {
                    return uniqueKey.getInt(1);
                }
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        throw new DatabaseInsertException();
    }

    /**
     * Inserts an orientation topic relationship.
     * 
     * @param connection connection to the TEQ database
     * @param serviceId  service ID
     * @param topicId    orientation topic ID
     * @param referrals  whether or not there were referrals
     * @return ID of the orientation topic relationship
     * @throws DatabaseInsertException on failure of insert
     */
    protected static int insertOrientationTopic(Connection connection, int serviceId, int topicId, boolean referrals)
            throws DatabaseInsertException {
        return insertServiceRelationship(connection, "OrientationTopic", "orientation_id", "topic_id", serviceId,
                topicId, referrals);
    }

    // -- Employment service insertions --
    /**
     * Inserts the employment service into the TEQ database and returns the service
     * ID if insertion was successful.
     * 
     * @param connection connection to the TEQ database
     * @param employment ID of the employment service
     * @return service ID (primary key) of the inserted employment service
     * @throws DatabaseInsertException on failure of insert
     */
    protected static int insertEmployment(Connection connection, Employment employment) throws DatabaseInsertException {
        int employmentId = insertEmploymentDetails(connection, employment);
        // insert long term intervention responses
        LongTermIntervention lti = employment.getLongTermIntervention();
        if (lti != null) {
            insertEmploymentLTI(connection, employmentId, lti);
        }
        // insert short term intervention responses
        for (ShortTermIntervention sti : employment.getShortTermIntervention()) {
            insertEmploymentSTI(connection, employmentId, sti);
        }
        return employmentId;
    }

    /**
     * Inserts the employment service details into the TEQ database and returns the
     * service ID if insertion was successful.
     * 
     * @param connection connection to the TEQ database
     * @param employment ID of the employment service
     * @return service ID (primary key) of the inserted employment service
     * @throws DatabaseInsertException on failure of insert
     */
    private static int insertEmploymentDetails(Connection connection, Employment employment)
            throws DatabaseInsertException {
        int serviceId = insertService(connection, employment);
        String sql = "INSERT INTO Employment ("
                + "service_id, registration, referral_to, referral_date, employment_status, education_status, "
                + "occupation_canada, occupation_intend, intervention_type, time_spent_hours, time_spent_minutes"
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, serviceId);
            statement.setBoolean(2, employment.getRegistration());
            statement.setString(3, employment.getReferral());
            if (employment.getDate().isEmpty()) {
                statement.setNull(4, java.sql.Types.DATE);
            } else {
                statement.setDate(4, SQLDriver.parseDate(employment.getDate()));
            }
            statement.setString(5, employment.getEmploymentStatus());
            statement.setString(6, employment.getEducationStatus());
            statement.setString(7, employment.getOccupationCanada());
            statement.setString(8, employment.getOccupationIntended());
            statement.setString(9, employment.getInterventionType());
            statement.setInt(10, employment.getTimeSpentHours());
            statement.setInt(11, employment.getTimeSpentMinutes());
            if (statement.executeUpdate() > 0) {
                ResultSet uniqueKey = statement.getGeneratedKeys();
                if (uniqueKey.next()) {
                    return uniqueKey.getInt(1);
                }
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        throw new DatabaseInsertException();
    }

    /**
     * Inserts the employment service long-term intervention responses and returns
     * the employment service ID if insertion was successful.
     *
     * @param connection connection to the TEQ database
     * @param serviceId  ID of the employment service
     * @param lti        long-term intervention responses
     * @return service ID (primary key) of the employment service
     * @throws DatabaseInsertException on failure of insert
     */
    private static int insertEmploymentLTI(Connection connection, int serviceId, LongTermIntervention lti)
            throws DatabaseInsertException {
        String sql = "INSERT INTO LongTermIntervention ("
                + "employment_id, service_recieved, status, reason_for_leave, start_date, end_date, employer_size, "
                + "placement_was, avg_hours_per_week, met_mentor_at, hours_per_week, profession"
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, serviceId);
            statement.setString(2, lti.getServiceRecieved());
            statement.setString(3, lti.getStatus());
            statement.setString(4, lti.getReasonForLeave());
            statement.setDate(5, SQLDriver.parseDate(lti.getStartDate()));
            statement.setDate(6, SQLDriver.parseDate(lti.getEndDate()));
            statement.setString(7, lti.getEmployerSize());
            statement.setString(8, lti.getPlacement());
            statement.setString(9, lti.getAverageHoursPerWeek());
            statement.setString(10, lti.getMetMentorAt());
            statement.setString(11, lti.getHoursPerWeek());
            statement.setString(12, lti.getProfession());
            if (statement.executeUpdate() > 0) {
                ResultSet uniqueKey = statement.getGeneratedKeys();
                if (uniqueKey.next()) {
                    return uniqueKey.getInt(1);
                }
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        throw new DatabaseInsertException();
    }

    /**
     * Inserts the employment service short-term intervention responses and returns
     * the ID of the short-term intervention record if insertion was successful.
     * 
     * @param connection connection to the TEQ database
     * @param serviceId  ID of the employment service
     * @param sti        short-term intervention responses
     * @return ID of the short-term intervention record
     * @throws DatabaseInsertException on failure of insert
     */
    private static int insertEmploymentSTI(Connection connection, int serviceId, ShortTermIntervention sti)
            throws DatabaseInsertException {
        String sql = "INSERT INTO ShortTermIntervention (" + "employment_id, service_recieved, date"
                + ") VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, serviceId);
            statement.setString(2, sti.getServiceRecieved());
            statement.setDate(3, SQLDriver.parseDate(sti.getDate()));
            if (statement.executeUpdate() > 0) {
                ResultSet uniqueKey = statement.getGeneratedKeys();
                if (uniqueKey.next()) {
                    return uniqueKey.getInt(1);
                }
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        throw new DatabaseInsertException();
    }

    protected static int insertCourseEnroll(Connection connection, CourseEnroll courseEnroll)
            throws DatabaseInsertException {
        int enrolId = insertCourseEnrollDetails(connection, courseEnroll);
        return enrolId;
    }

    private static int insertCourseEnrollDetails(Connection connection, CourseEnroll courseEnroll)
            throws DatabaseInsertException {
        int serviceId = insertService(connection, courseEnroll);
        String sql = "INSERT INTO CourseEnroll (service_id, course_code, first_class_date) " + "VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, serviceId);
            statement.setString(2, courseEnroll.getCourseCode());
            statement.setDate(3, SQLDriver.parseDate(courseEnroll.getFirstClassDate()));
            if (statement.executeUpdate() > 0) {
                ResultSet uniqueKey = statement.getGeneratedKeys();
                if (uniqueKey.next()) {
                    return uniqueKey.getInt(1);
                }
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        throw new DatabaseInsertException();
    }

    protected static int insertCourseExit(Connection connection, CourseExit courseExit) throws DatabaseInsertException {
        int exitId = insertCourseExitDetails(connection, courseExit);
        return exitId;
    }

    private static int insertCourseExitDetails(Connection connection, CourseExit courseExit)
            throws DatabaseInsertException {
        int serviceId = insertService(connection, courseExit);
        String sql = "INSERT INTO CourseExit (service_id, course_code, exit_date, reason, "
                + "listening_level, reading_level, speaking_level, writing_level) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, serviceId);
            statement.setString(2, courseExit.getCourseCode());
            statement.setDate(3, SQLDriver.parseDate(courseExit.getExitDate()));
            statement.setString(4, courseExit.getReason());
            statement.setString(5, courseExit.getListeningLevel());
            statement.setString(6, courseExit.getReadingLevel());
            statement.setString(7, courseExit.getSpeakingLevel());
            statement.setString(8, courseExit.getWritingLevel());
            if (statement.executeUpdate() > 0) {
                ResultSet uniqueKey = statement.getGeneratedKeys();
                if (uniqueKey.next()) {
                    return uniqueKey.getInt(1);
                }
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        throw new DatabaseInsertException();
    }

    /**
     * Inserts a course into the TEQ database and returns the course code if
     * insertion was successful.
     * 
     * @param connection connection to the TEQ database
     * @param course     course info to insert
     * @return courseCode (primary key) of the inserted course
     * @throws DatabaseInsertException
     */
    protected static String insertCourse(Connection connection, Course course) throws DatabaseInsertException {
        // insert into the course table
        String courseCode = course.getCourseCode();
        insertCourseDetails(connection, course);
        // insert relationships
        try {
            insertCourseContact(connection, courseCode, course.getContact());
            for (String schedule : course.getSchedules()) {
                int typeId = DatabaseSelector.getTypeId(connection, "Schedule", schedule);
                insertCourseSchedule(connection, courseCode, typeId);
            }
            for (String supportService : course.getSupportServices()) {
                int typeId = DatabaseSelector.getTypeId(connection, "SupportService", supportService);
                insertCourseSupportService(connection, courseCode, typeId);
            }
            for (String targetGroup : course.getTargetGroups()) {
                int typeId = DatabaseSelector.getTypeId(connection, "TargetGroup", targetGroup);
                insertCourseTargetGroup(connection, courseCode, typeId);
            }
        } catch (SQLException e) {
            throw new DatabaseInsertException();
        }
        // return courseCode
        return courseCode;
    }

    private static String insertCourseDetails(Connection connection, Course course) throws DatabaseInsertException {
        String sql = "INSERT INTO Course (course_code, notes, ongoing_basis, language, training_format, classes_held_at, "
                + "inperson_instruct, online_instruct, number_of_spots, number_of_ircc, enrollment_type, start_date, end_date, "
                + "instruct_hours, hours_per_week, weeks, weeks_per_year, dominant_focus)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, course.getCourseCode());
            statement.setString(2, course.getLanguage());
            statement.setBoolean(3, course.isOngoingBasis());
            statement.setString(4, course.getLanguage());
            statement.setString(5, course.getTrainingFormat());
            statement.setString(6, course.getClassLocation());
            statement.setFloat(7, course.getInPercentInstruct());
            statement.setFloat(8, course.getOnlineInstruct());
            statement.setInt(9, course.getNumberOfSpots());
            statement.setInt(10, course.getNumberOfIRCC());
            statement.setString(11, course.getEnrollmentType());
            statement.setString(12, course.getStartDate());
            statement.setString(13, course.getEndDate());
            statement.setString(14, course.getInstructHours());
            statement.setInt(15, course.getWeeklyHours());
            statement.setInt(16, course.getNumWeeks());
            statement.setInt(17, course.getNumWeeksPerYear());
            statement.setString(18, course.getDominantFocus());
            if (statement.executeUpdate() > 0) {
                ResultSet uniqueKey = statement.getGeneratedKeys();
                if (uniqueKey.next()) {
                    return uniqueKey.getString(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new DatabaseInsertException();
    }

    /**
     * Inserts a course contact into the TEQ database and returns the resulting ID
     * if insertion was successful.
     * 
     * @param connection      connection to the TEQ database
     * @param courseCode      course code
     * @param contactName     course contact name
     * @param addressId       address ID of the course contact
     * @param telephoneNumber telephone number
     * @param telephoneExt    telephone extension
     * @param emailAddress    email address
     * @return row ID if insertion was successful
     * @throws DatabaseInsertException on failure of insert
     */
    protected static int insertCourseContact(Connection connection, String courseCode, CourseContact courseContact)
            throws DatabaseInsertException {
        String sql = "INSERT INTO CourseContact(course_code,contact_name,address_id,telephone_number,telephone_ext,email_address)"
                + " VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, courseCode);
            statement.setString(2, courseContact.getName());
            statement.setInt(3, insertAddress(connection, courseContact.getAddress()));
            statement.setString(4, courseContact.getTelephoneNumber());
            statement.setString(5, courseContact.getTelephoneExt());
            statement.setString(6, courseContact.getEmailAddress());
            if (statement.executeUpdate() > 0) {
                ResultSet uniqueKey = statement.getGeneratedKeys();
                if (uniqueKey.next()) {
                    return uniqueKey.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new DatabaseInsertException();
    }

    /**
     * Inserts a course schedule relationship.
     * 
     * @param connection connection to the TEQ database
     * @param courseCode courseCode
     * @param scheduleId schedule ID
     * @return ID of the course schedule relationship
     * @throws DatabaseInsertException on failure of insert
     */
    protected static int insertCourseSchedule(Connection connection, String courseCode, int scheduleId)
            throws DatabaseInsertException {
        String sql = "INSERT INTO CourseSchedule(course_code,schedule_id) VALUES(?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, courseCode);
            statement.setInt(2, scheduleId);
            if (statement.executeUpdate() > 0) {
                ResultSet uniqueKey = statement.getGeneratedKeys();
                if (uniqueKey.next()) {
                    return uniqueKey.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new DatabaseInsertException();
    }

    /**
     * Inserts a course support service relationship.
     * 
     * @param connection connection to the TEQ database
     * @param courseCode courseCode
     * @param supportId  support service ID
     * @return ID of the support service relationship
     * @throws DatabaseInsertException on failure of insert
     */
    protected static int insertCourseSupportService(Connection connection, String courseCode, int supportId)
            throws DatabaseInsertException {
        String sql = "INSERT INTO CourseSupportService(course_code,support_service_id) VALUES(?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, courseCode);
            statement.setInt(2, supportId);
            if (statement.executeUpdate() > 0) {
                ResultSet uniqueKey = statement.getGeneratedKeys();
                if (uniqueKey.next()) {
                    return uniqueKey.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new DatabaseInsertException();
    }

    /**
     * Inserts a course target group relationship.
     * 
     * @param connection connection to the TEQ database
     * @param courseCode course ID
     * @param groupId    target group ID
     * @return ID of the target group relationship
     * @throws DatabaseInsertException on failure of insert
     */
    protected static int insertCourseTargetGroup(Connection connection, String courseCode, int groupId)
            throws DatabaseInsertException {
        String sql = "INSERT INTO CourseTargetGroup(course_code,target_group_id) VALUES(?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, courseCode);
            statement.setInt(2, groupId);
            if (statement.executeUpdate() > 0) {
                ResultSet uniqueKey = statement.getGeneratedKeys();
                if (uniqueKey.next()) {
                    return uniqueKey.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new DatabaseInsertException();
    }

    /**
     * Inserts a course reading skill into the TEQ database and returns the
     * resulting ID if insertion was successful.
     * 
     * @param connection connection to the TEQ database
     * @param courseCode course code
     * @param level      reading skill level
     * @param value      reading skill value
     * @return row ID if insertion was successful
     * @throws DatabaseInsertException on failure of insert
     */
    protected static int insertCourseReadingSkill(Connection connection, String courseCode, int level, String value)
            throws DatabaseInsertException {
        String sql = "INSERT INTO CourseReadingSkill(course_code,level,value)" + " VALUES(?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, courseCode);
            statement.setInt(2, level);
            statement.setString(3, value);
            if (statement.executeUpdate() > 0) {
                ResultSet uniqueKey = statement.getGeneratedKeys();
                if (uniqueKey.next()) {
                    return uniqueKey.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new DatabaseInsertException();
    }

    /**
     * Inserts a course listening skill into the TEQ database and returns the
     * resulting ID if insertion was successful.
     * 
     * @param connection connection to the TEQ database
     * @param courseCode course code
     * @param level      listening skill level
     * @param value      listening skill value
     * @return row ID if insertion was successful
     * @throws DatabaseInsertException on failure of insert
     */
    protected static int insertCourseListeningSkill(Connection connection, String courseCode, int level, String value)
            throws DatabaseInsertException {
        String sql = "INSERT INTO CourseListeningSkill(course_code,level,value)" + " VALUES(?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, courseCode);
            statement.setInt(2, level);
            statement.setString(3, value);
            if (statement.executeUpdate() > 0) {
                ResultSet uniqueKey = statement.getGeneratedKeys();
                if (uniqueKey.next()) {
                    return uniqueKey.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new DatabaseInsertException();
    }

    /**
     * Inserts a course writing skill into the TEQ database and returns the
     * resulting ID if insertion was successful.
     * 
     * @param connection connection to the TEQ database
     * @param courseCode course code
     * @param level      writing skill level
     * @param value      writing skill value
     * @return row ID if insertion was successful
     * @throws DatabaseInsertException on failure of insert
     */
    protected static int insertCourseWritingSkill(Connection connection, String courseCode, int level, String value)
            throws DatabaseInsertException {
        String sql = "INSERT INTO CourseWritingSkill(course_code,level,value)" + " VALUES(?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, courseCode);
            statement.setInt(2, level);
            statement.setString(3, value);
            if (statement.executeUpdate() > 0) {
                ResultSet uniqueKey = statement.getGeneratedKeys();
                if (uniqueKey.next()) {
                    return uniqueKey.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new DatabaseInsertException();
    }

    /**
     * Inserts a course speaking skill into the TEQ database and returns the
     * resulting ID if insertion was successful.
     * 
     * @param connection connection to the TEQ database
     * @param courseCode course code
     * @param level      speaking skill level
     * @param value      speaking skill value
     * @return row ID if insertion was successful
     * @throws DatabaseInsertException on failure of insert
     */
    protected static int insertCourseSpeakingSkill(Connection connection, String courseCode, int level, String value)
            throws DatabaseInsertException {
        String sql = "INSERT INTO CourseSpeakingLevel(course_code,level,value)" + " VALUES(?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, courseCode);
            statement.setInt(2, level);
            statement.setString(3, value);
            if (statement.executeUpdate() > 0) {
                ResultSet uniqueKey = statement.getGeneratedKeys();
                if (uniqueKey.next()) {
                    return uniqueKey.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new DatabaseInsertException();
    }
}
