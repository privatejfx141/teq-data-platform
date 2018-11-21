package com.devlopp.teq.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseSelector {

    protected static ResultSet getPlatformRoleId(Connection connection, String roleName) throws SQLException {
        String sql = "SELECT role_id FROM Role WHERE description = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        return statement.executeQuery();
    }
    
    protected static ResultSet getPlatformUserId(Connection connection, String username) throws SQLException {
        String sql = "SELECT id FROM USER WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        return statement.executeQuery();
    }
    
    protected static ResultSet getPlatformUserRoleId(Connection connection, int userId) throws SQLException {
        String sql = "SELECT role_id FROM USER WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, userId);
        return statement.executeQuery();
    }
    
    protected static ResultSet getPlatformPassword(Connection connection, int userId) throws SQLException {
        String sql = "SELECT password FROM USER WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, userId);
        return statement.executeQuery();
    }
    
    protected static ResultSet getAllClientIds(Connection connection) throws SQLException {
        String sql = "SELECT DISTINCT id FROM Client";
        PreparedStatement statement = connection.prepareStatement(sql);
        return statement.executeQuery();
    }

    protected static ResultSet getAllCourseCodes(Connection connection) throws SQLException {
        String sql = "SELECT DISTINCT course_code FROM Course";
        PreparedStatement statement = connection.prepareStatement(sql);
        return statement.executeQuery();
    }

    protected static ResultSet getAllServiceIds(Connection connection) throws SQLException {
        String sql = "SELECT DISTINCT id FROM Service";
        PreparedStatement statement = connection.prepareStatement(sql);
        return statement.executeQuery();
    }

    protected static ResultSet getServiceType(Connection connection, int serviceId) throws SQLException {
        String sql = "SELECT service_type FROM Service WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, serviceId);
        return statement.executeQuery();
    }

    protected static ResultSet getAllTypes(Connection connection, String tableName) throws SQLException {
        String sql = "SELECT * FROM " + tableName;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    protected static int getTypeId(Connection connection, String tableName, String typeDesc) throws SQLException {
        String sql = String.format("SELECT DISTINCT id FROM %s WHERE LOWER(description) = ?", tableName);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, typeDesc.toLowerCase());
        ResultSet results = preparedStatement.executeQuery();
        int typeId = 0;
        while (results.next()) {
            typeId = results.getInt(1);
        }
        return typeId;
    }

    /* Client methods */
    protected static ResultSet getClient(Connection connection, int clientId) throws SQLException {
        String sql = "SELECT * FROM Client WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, clientId);
        return preparedStatement.executeQuery();
    }

    protected static ResultSet getClientServices(Connection connection, int clientId) throws SQLException {
        String sql = "SELECT id FROM Service WHERE client_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, clientId);
        return preparedStatement.executeQuery();
    }

    /* Service methods */
    protected static ResultSet getServiceDetails(Connection connection, int serviceId) throws SQLException {
        String sql = "SELECT * FROM Service WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, serviceId);
        return preparedStatement.executeQuery();
    }

    protected static ResultSet getServiceEssentialSkill(Connection connection, int serviceId) throws SQLException {
        String sql = "SELECT description FROM EssentialSkill t WHERE t.id IN"
                + " (SELECT DISTINCT essential_skill_id FROM ServiceEssentialSkill WHERE service_id = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, serviceId);
        return preparedStatement.executeQuery();
    }

    protected static ResultSet getServiceSupportService(Connection connection, int serviceId) throws SQLException {
        String sql = "SELECT description FROM SupportService t WHERE t.id IN"
                + " (SELECT DISTINCT support_service_id FROM ServiceSupportService WHERE service_id = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, serviceId);
        return preparedStatement.executeQuery();
    }

    protected static ResultSet getServiceTargetGroup(Connection connection, int serviceId) throws SQLException {
        String sql = "SELECT description FROM TargetGroup t WHERE t.id IN"
                + " (SELECT DISTINCT target_group_id FROM ServiceTargetGroup WHERE service_id = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, serviceId);
        return preparedStatement.executeQuery();
    }

    protected static ResultSet getServiceNewcomerChildCare(Connection connection, int serviceId) throws SQLException {
        String sql = "SELECT * FROM NewcomerChildCare WHERE service_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, serviceId);
        return preparedStatement.executeQuery();
    }

    protected static ResultSet getCommunityConnections(Connection connection, int serviceId) throws SQLException {
        String sql = "SELECT * FROM CommunityConnections service_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, serviceId);
        return preparedStatement.executeQuery();
    }

    /* Specific service methods */
    protected static ResultSet getAssessmentDetails(Connection connection, int serviceId) throws SQLException {
        String sql = "SELECT * FROM Assessment WHERE service_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, serviceId);
        return preparedStatement.executeQuery();
    }

    protected static ResultSet getAssessmentFindEmployment(Connection connection, int serviceId) throws SQLException {
        String sql = "SELECT * FROM AssessmentFindEmployment WHERE assessment_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, serviceId);
        return preparedStatement.executeQuery();
    }

    protected static ResultSet getAssessmentIncrease(Connection connection, int serviceId) throws SQLException {
        String sql = "SELECT a.description, b.referrals" + " FROM AssessmentIncrease b JOIN Increase a"
                + " ON a.id = b.increase_id WHERE b.assessment_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, serviceId);
        return preparedStatement.executeQuery();
    }

    protected static ResultSet getAssessmentNonIRCCService(Connection connection, int serviceId) throws SQLException {
        String sql = "SELECT description FROM NonIRCCService t WHERE t.id IN"
                + " (SELECT DISTINCT non_ircc_service_id FROM AssessmentNonIRCCService WHERE assessment_id = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, serviceId);
        return preparedStatement.executeQuery();
    }

    protected static ResultSet getCommunityConnectionsDetails(Connection connection, int serviceId)
            throws SQLException {
        String sql = "SELECT * FROM CommunityConnections WHERE service_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, serviceId);
        return preparedStatement.executeQuery();
    }

    protected static ResultSet getOrientationDetails(Connection connection, int serviceId) throws SQLException {
        String sql = "SELECT * FROM Orientation WHERE service_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, serviceId);
        return preparedStatement.executeQuery();
    }

    protected static ResultSet getOrientationTopic(Connection connection, int serviceId) throws SQLException {
        String sql = "SELECT description FROM Topic t WHERE t.id IN"
                + " (SELECT DISTINCT topic_id FROM OrientationTopic WHERE orientation_id = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, serviceId);
        return preparedStatement.executeQuery();
    }

    protected static ResultSet getEmploymentDetails(Connection connection, int serviceId) throws SQLException {
        String sql = "SELECT * FROM Employment WHERE service_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, serviceId);
        return preparedStatement.executeQuery();
    }

    protected static ResultSet getEmploymentLTI(Connection connection, int serviceId) throws SQLException {
        String sql = "SELECT * FROM LongTermIntervention WHERE employment_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, serviceId);
        return preparedStatement.executeQuery();
    }

    protected static ResultSet getEmploymentSTI(Connection connection, int serviceId) throws SQLException {
        String sql = "SELECT * FROM ShortTermIntervention WHERE employment_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, serviceId);
        return preparedStatement.executeQuery();
    }

    protected static ResultSet getCourseEnroll(Connection connection, int serviceId) throws SQLException {
        String sql = "SELECT * FROM CourseEnroll WHERE service_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, serviceId);
        return preparedStatement.executeQuery();
    }

    protected static ResultSet getCourseExit(Connection connection, int serviceId) throws SQLException {
        String sql = "SELECT * FROM CourseExit WHERE service_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, serviceId);
        return preparedStatement.executeQuery();
    }

    /* Course methods */
    protected static ResultSet getCourse(Connection connection, String courseCode) throws SQLException {
        String sql = "SELECT * FROM Course WHERE course_code = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, courseCode);
        return preparedStatement.executeQuery();
    }

    protected static ResultSet getCourseSchedule(Connection connection, String courseCode) throws SQLException {
        String sql = "SELECT description FROM Schedule t WHERE t.id IN"
                + "(SELECT DISTINCT schedule_id FROM CourseSchedule WHERE course_code = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, courseCode);
        return preparedStatement.executeQuery();
    }

    protected static ResultSet getCourseSupportService(Connection connection, String courseCode) throws SQLException {
        String sql = "SELECT description FROM SupportService t WHERE t.id IN"
                + "(SELECT DISTINCT support_service_id FROM CourseSupportService WHERE course_code = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, courseCode);
        return preparedStatement.executeQuery();
    }

    protected static ResultSet getCourseTargetGroup(Connection connection, String courseCode) throws SQLException {
        String sql = "SELECT description FROM TargetGroup t WHERE t.id IN"
                + "(SELECT DISTINCT target_group_id FROM CourseTargetGroup WHERE course_code = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, courseCode);
        return preparedStatement.executeQuery();
    }

    protected static ResultSet getCourseContact(Connection connection, String courseCode) throws SQLException {
        String sql = "SELECT * FROM CourseContact WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, courseCode);
        return preparedStatement.executeQuery();
    }

    protected static ResultSet getAddress(Connection connection, int addressId) throws SQLException {
        String sql = "SELECT * FROM Address WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, addressId);
        return preparedStatement.executeQuery();
    }

    protected static ResultSet getCourseEnroll(Connection connection, int serviceId, String courseCode)
            throws SQLException {
        String sql = "SELECT * FROM CourseEnroll WHERE course_code = ? AND serviceId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, courseCode);
        preparedStatement.setInt(2, serviceId);
        return preparedStatement.executeQuery();
    }

    protected static ResultSet getCourseExit(Connection connection, int serviceId, String courseCode)
            throws SQLException {
        String sql = "SELECT * FROM CourseExit WHERE course_code = ? AND serviceId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, courseCode);
        preparedStatement.setInt(2, serviceId);
        return preparedStatement.executeQuery();
    }
}
