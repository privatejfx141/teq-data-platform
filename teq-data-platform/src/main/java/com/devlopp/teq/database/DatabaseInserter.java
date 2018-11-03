package com.devlopp.teq.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import com.devlopp.teq.address.Address;
import com.devlopp.teq.client.Client;
import com.devlopp.teq.course.Course;
import com.devlopp.teq.service.Service;
import com.devlopp.teq.service.assessment.Assessment;
import com.devlopp.teq.service.employment.Employment;
import com.devlopp.teq.service.orientation.Orientation;
import com.devlopp.teq.sql.SQLDriver;

public class DatabaseInserter {
    /**
     * Inserts a client (and its address) into the TEQ database and returns the
     * client ID if insertion was successful.
     * 
     * @param connection connection to the TEQ database
     * @param client     client info to insert
     * @return client ID (primary key) of the inserted client
     * @throws DatabaseInsertException
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
            // insert all improvements into database
            for (String increase : assessment.getIncreases()) {
                int typeId = DatabaseSelector.getTypeId(connection, "Increase", increase);
                insertAssessmentIncrease(connection, assessmentId, typeId, false);
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
        int assessmentId = insertService(connection, assessment);
        String sql = "INSERT INTO Assessment("
                + "service_id,start_date,language_skill_goal,other_skill_goal,"
                + "intends_citizenship,req_support_service,plan_complete,end_date"
                + ") VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, assessment.getId());
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
            e.printStackTrace();
        }
        return assessmentId;
    }

    // -- Orientation service insertions --
    protected static int insertOrientation(Connection connection, Orientation orientation)
            throws DatabaseInsertException {
        int orientationId = insertOrientationDetails(connection, orientation);
        return orientationId;
    }

    private static int insertOrientationDetails(Connection connection, Orientation orientation)
            throws DatabaseInsertException {
        // TODO
        throw new DatabaseInsertException();
    }

    // -- Employment service insertions --
    protected static int insertEmployment(Connection connection, Employment employment) throws DatabaseInsertException {
        int employmentId = insertEmploymentDetails(connection, employment);
        return employmentId;
    }

    private static int insertEmploymentDetails(Connection connection, Employment employment)
            throws DatabaseInsertException {
        // TODO
        throw new DatabaseInsertException();
    }

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

    /**
     * Inserts a course into the TEQ database and returns the course code if
     * insertion was successful.
     * 
     * @param connection connection to the TEQ database
     * @param course     course info to insert
     * @return course code (primary key) of the inserted course
     */
    protected static String insertCourse(Connection connection, Course course) {
        return "";
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
    protected static int insertCourseContact(Connection connection, String courseCode, String contactName,
            int addressId, String telephoneNumber, String telephoneExt, String emailAddress)
            throws DatabaseInsertException {
        String sql = "INSERT INTO CourseContact(course_code,contact_name,address_id,telephone_number,telephone_ext,email_address)"
                + " VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, courseCode);
            statement.setString(2, contactName);
            statement.setInt(3, addressId);
            statement.setString(4, telephoneNumber);
            statement.setString(5, telephoneExt);
            statement.setString(6, emailAddress);
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
     * @param courseId   course ID
     * @param scheduleId schedule ID
     * @return ID of the course schedule relationship
     * @throws DatabaseInsertException on failure of insert
     */
    protected static int insertCourseSchedule(Connection connection, int courseId, int scheduleId)
            throws DatabaseInsertException {
        String sql = "INSERT INTO CourseSchedule(course_id,schedule_id) VALUES(?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, courseId);
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
     * @param courseId   course ID
     * @param supportId  support service ID
     * @return ID of the support service relationship
     * @throws DatabaseInsertException on failure of insert
     */
    protected static int insertCourseSupportService(Connection connection, int courseId, int supportId)
            throws DatabaseInsertException {
        String sql = "INSERT INTO CourseSupportService(course_id,support_service_id) VALUES(?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, courseId);
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
     * @param courseId   course ID
     * @param groupId    target group ID
     * @return ID of the target group relationship
     * @throws DatabaseInsertException on failure of insert
     */
    protected static int insertCourseTargetGroup(Connection connection, int courseId, int groupId)
            throws DatabaseInsertException {
        String sql = "INSERT INTO CourseTargetGroup(course_id,target_group_id) VALUES(?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, courseId);
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
}
