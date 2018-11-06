package com.devlopp.teq.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.List;

import com.devlopp.teq.address.Address;
import com.devlopp.teq.client.Client;
import com.devlopp.teq.course.Course;
import com.devlopp.teq.service.Assessment;
import com.devlopp.teq.service.CommunityConnections;
import com.devlopp.teq.service.Employment;
import com.devlopp.teq.service.Orientation;
import com.devlopp.teq.service.Service;
import com.devlopp.teq.sql.SQLDriver;

public class DatabaseInserter {
    /**
     * Inserts a client into the TEQ database and returns the client ID if insertion
     * was successful.
     * 
     * @param connection connection to the TEQ database
     * @param client     client info to insert
     * @return client ID (primary key) of the inserted client
     * @throws DatabaseInsertException
     */
    protected static int insertClient(Connection connection, Client client) throws DatabaseInsertException {
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
            statement.setInt(6, client.getAddressId());
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
     * @param connection      connection to the TEQ database
     * @param address the adress object that describes the adress
     * @return the address ID number, -1 otherwise
     * @throws DatabaseInsertException if fails to insert into db
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
        // insert into the service table
        int serviceId = insertServiceObject(connection, service);
        // insert relationships
        for (String supportService : service.getSupportServices()) {
            try {
                int typeId = DatabaseSelector.getTypeId(connection, "SupportService", supportService);
                insertServiceSupportService(connection, serviceId, typeId);
            } catch (SQLException e) {
                throw new DatabaseInsertException();
            }
        }
        for (String essentialSkill : service.getEssentialSkills()) {
            try {
                int typeId = DatabaseSelector.getTypeId(connection, "EssentialSkill", essentialSkill);
                insertServiceEssentialSkill(connection, serviceId, typeId);
            } catch (SQLException e) {
                throw new DatabaseInsertException();
            }
        }
        for (String targetGroup : service.getTargetGroups()) {
            try {
                int typeId = DatabaseSelector.getTypeId(connection, "TargetGroup", targetGroup);
                insertServiceTargetGroup(connection, serviceId, typeId);
            } catch (SQLException e) {
                throw new DatabaseInsertException();
            }
        }
        // return service id
        return serviceId;
    }

    private static int insertServiceObject(Connection connection, Service service) throws DatabaseInsertException {
        String sql = "INSERT INTO Service (client_id,language,organization_type,referred_by,update_reason,service_type)"
                + " VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, service.getClientId());
            statement.setString(2, service.getLanguage());
            statement.setString(3, service.getOrganizationType());
            statement.setString(4, service.getReferredBy());
            statement.setString(5, service.getUpdateReason());
            statement.setString(6, service.getServiceType());
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

    protected static int insertAssessment(Connection connection, Assessment assessment) throws DatabaseInsertException {
        int assessmentId = insertService(connection, assessment);
        String sql = "INSERT INTO Assessment(service_id,start_date,language_skill_goal_other_skill_goal,intends_citizenship,req_support_service,plan_complete,end_date)"
                + " VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assessmentId;
    }

    protected static int insertEmployment(Connection connection, Employment employment) throws DatabaseInsertException {
        int employmentId = insertService(connection, employment);
        return employmentId;
    }

    protected static int insertOrientation(Connection connection, Orientation orientation)
            throws DatabaseInsertException {
        int orientationId = insertService(connection, orientation);
        return orientationId;
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
     * @throws DatabaseInsertException 
     */
    protected static int insertCourse(Connection connection, Course course) throws DatabaseInsertException {
        // insert into the course table
        int courseId = insertCourseObject(connection, course);
        // insert relationships
        for (String schedule : course.getSchedules()) {
            try {
                int typeId = DatabaseSelector.getTypeId(connection, "Schedule", schedule);
                insertCourseSchedule(connection, courseId, typeId);
            } catch (SQLException e) {
                throw new DatabaseInsertException();
            }
        }
        for (String supportService : course.getSupportServices()) {
            try {
                int typeId = DatabaseSelector.getTypeId(connection, "SupportService", supportService);
                insertCourseSupportService(connection, courseId, typeId);
            } catch (SQLException e) {
                throw new DatabaseInsertException();
            }
        }
        for (String targetGroup : course.getTargetGroups()) {
            try {
                int typeId = DatabaseSelector.getTypeId(connection, "TargetGroup", targetGroup);
                insertCourseTargetGroup(connection, courseId, typeId);
            } catch (SQLException e) {
                throw new DatabaseInsertException();
            }
        }
        // return course id
        return courseId;
    }
    
    /**
     * Inserts a course into the TEQ database and returns the course ID if
     * insertion was successful.
     * 
     * @param connection connection to the TEQ database
     * @param course    course info to insert
     * @return course ID (primary key) of the inserted course
     * @throws DatabaseInsertException on failure of insert
     */
    private static int insertCourseObject(Connection connection, Course course) throws DatabaseInsertException {
        String sql = "INSERT INTO Course (courseCode,notes,ongoingBasis,language,trainingFormat,"
        		+ "classLocation,inpersonInstruct,onlineInstructnumberOfSpots,numberOfSpots,numberOfIRCC,"
        		+ "enrollmentType,startDate,endDate,instructHours,weeklyHours,numWeeks,numWeeksPerYear,dominantFocus)"
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
            statement.setInt(14, course.getInstructHours());
            statement.setInt(15, course.getWeeklyHours());
            statement.setInt(16, course.getNumWeeks());
            statement.setInt(17, course.getNumWeeksPerYear());
            statement.setString(18, course.getDominantFocus());
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
    protected static int insertCommunityConnections(Connection connection, CommunityConnections communityConnections) throws DatabaseInsertException {
        int communityConnectionsId = insertService(connection, communityConnections);
        String sql = "INSERT INTO communityConnections(service_id,eventType,mainTopic,serviceReceived,participants,hasVolunteers,reasonForLeave,status,startDate,endDate,projectedEndDate,lengthHours,lengthMinutes)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return communityConnectionsId;
    }

 /**
     * Inserts a course reading skill into the TEQ database and returns the resulting ID
     * if insertion was successful.
     * 
     * @param connection      connection to the TEQ database
     * @param courseCode      course code
     * @param level           reading skill level
     * @param value           reading skill value
     * @return row ID if insertion was successful
     * @throws DatabaseInsertException on failure of insert
     */
    protected static int insertCourseReadingSkill(Connection connection, String courseCode, int level,
            String value)
            throws DatabaseInsertException {
        String sql = "INSERT INTO CourseReadingSkill(course_code,level,value)"
                + " VALUES(?,?,?)";
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
     * Inserts a course listening skill into the TEQ database and returns the resulting ID
     * if insertion was successful.
     * 
     * @param connection      connection to the TEQ database
     * @param courseCode      course code
     * @param level           listening skill level
     * @param value           listening skill value
     * @return row ID if insertion was successful
     * @throws DatabaseInsertException on failure of insert
     */
    protected static int insertCourseListeningSkill(Connection connection, String courseCode, int level,
            String value)
            throws DatabaseInsertException {
        String sql = "INSERT INTO CourseListeningSkill(course_code,level,value)"
                + " VALUES(?,?,?)";
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
     * Inserts a course writing skill into the TEQ database and returns the resulting ID
     * if insertion was successful.
     * 
     * @param connection      connection to the TEQ database
     * @param courseCode      course code
     * @param level           writing skill level
     * @param value           writing skill value
     * @return row ID if insertion was successful
     * @throws DatabaseInsertException on failure of insert
     */
    protected static int insertCourseWritingSkill(Connection connection, String courseCode, int level,
            String value)
            throws DatabaseInsertException {
        String sql = "INSERT INTO CourseWritingSkill(course_code,level,value)"
                + " VALUES(?,?,?)";
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
     * Inserts a course speaking skill into the TEQ database and returns the resulting ID
     * if insertion was successful.
     * 
     * @param connection      connection to the TEQ database
     * @param courseCode      course code
     * @param level           speaking skill level
     * @param value           speaking skill value
     * @return row ID if insertion was successful
     * @throws DatabaseInsertException on failure of insert
     */
    protected static int insertCourseSpeakingSkill(Connection connection, String courseCode, int level,
            String value)
            throws DatabaseInsertException {
        String sql = "INSERT INTO CourseSpeakingLevel(course_code,level,value)"
                + " VALUES(?,?,?)";
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
