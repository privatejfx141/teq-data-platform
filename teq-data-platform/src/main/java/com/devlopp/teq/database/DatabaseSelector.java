package com.devlopp.teq.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseSelector {
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

    /* Specific service methods */
    protected static ResultSet getAssessmentDetails(Connection connection, int serviceId) throws SQLException {
        String sql = "SELECT * FROM Assessment WHERE service_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, serviceId);
        return preparedStatement.executeQuery();
    }

    protected static ResultSet getAssessmentFindEmployment(Connection connection, int assessmentId) throws SQLException {
        String sql = "SELECT * FROM AssessmentFindEmployment WHERE assessment_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, assessmentId);
        return preparedStatement.executeQuery();
    }

    protected static ResultSet getAssessmentIncrease(Connection connection, int assessmentId) throws SQLException {
        String sql = "SELECT a.description, b.referrals" + " FROM AssessmentIncrease b JOIN Increase a"
                + " ON a.id = b.increase_id WHERE b.assessment_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, assessmentId);
        return preparedStatement.executeQuery();
    }

    protected static ResultSet getAssessmentNonIRCCService(Connection connection, int assessmentId)
            throws SQLException {
        String sql = "SELECT description FROM NonIRCCService t WHERE t.id IN"
                + " (SELECT DISTINCT non_ircc_service_id FROM AssessmentNonIRCCService WHERE assessment_id = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, assessmentId);
        return preparedStatement.executeQuery();
    }

    protected static ResultSet getOrientationTopic(Connection connection, int orientationId) throws SQLException {
        String sql = "SELECT description FROM Topic t WHERE t.id IN"
                + " (SELECT DISTINCT topic_id FROM OrientationTopic WHERE orientation_id = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, orientationId);
        return preparedStatement.executeQuery();
    }

    protected static ResultSet getEmploymentDetails(Connection connection, int serviceId) throws SQLException {
        String sql = "SELECT * FROM Employment WHERE service_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, serviceId);
        return preparedStatement.executeQuery();
    }

    protected static ResultSet getEmploymentLTI(Connection connection, int employmentId) throws SQLException {
        String sql = "SELECT * FROM LongTermIntervention WHERE employment_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, employmentId);
        return preparedStatement.executeQuery();
    }

    protected static ResultSet getEmploymentSTI(Connection connection, int employmentId) throws SQLException {
        String sql = "SELECT * FROM ShortTermIntervention WHERE employment_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, employmentId);
        return preparedStatement.executeQuery();
    }

    /* Course methods */
    protected static ResultSet getCourse(Connection connection, String courseCode) throws SQLException {
        String sql = "SELECT * FROM Course WHERE id = ?";
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
}
