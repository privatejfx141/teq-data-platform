package com.devlopp.teq.databasehelper;

import java.sql.Connection;
import java.sql.SQLException;

import com.devlopp.teq.address.Address;
import com.devlopp.teq.client.Client;
import com.devlopp.teq.course.Course;
import com.devlopp.teq.database.DatabaseInsertException;
import com.devlopp.teq.database.DatabaseInserter;
import com.devlopp.teq.service.assessment.Assessment;
import com.devlopp.teq.service.commconn.CommunityConnections;
import com.devlopp.teq.service.courseenroll.CourseEnroll;
import com.devlopp.teq.service.courseexit.CourseExit;
import com.devlopp.teq.service.employment.Employment;
import com.devlopp.teq.service.orientation.Orientation;

public class DatabaseInsertHelper extends DatabaseInserter {
    /**
     * Inserts client details into the TEQ database and returns the client ID if
     * successful.
     * 
     * @param client client details to insert
     * @return client ID if successful, -1 otherwise
     */
    public static int insertClient(Client client) {
        int clientId = -1;
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        try {
            clientId = DatabaseInserter.insertClient(connection, client);
        } catch (DatabaseInsertException exception) {
            clientId = -1;
        } finally {
            try {
                connection.close();
            } catch (SQLException closeConnectionException) {
                /* Do not need to do anything, connection was already closed */
            }
        }
        return clientId;
    }

    /**
     * Inserts course details into the TEQ database and returns the course code if
     * successful.
     * 
     * @param course course details to insert
     * @return courseCode if successful, empty string otherwise
     */
    public static String insertCourse(Course course) {
        String courseCode = "";
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        try {
            courseCode = DatabaseInserter.insertCourse(connection, course);
        } catch (DatabaseInsertException exception) {
            courseCode = "";
        } finally {
            try {
                connection.close();
            } catch (SQLException closeConnectionException) {
                /* Do not need to do anything, connection was already closed */
            }
        }
        return courseCode;
    }

    /**
     * Inserts an address into the TEQ database and returns the address ID.
     * 
     * @param address address details to insert
     * @return address ID if successful, -1 otherwise
     */
    public static int insertAddress(Address address) {
        int addressId = -1;
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        // attempt to insert into database
        try {
            addressId = DatabaseInserter.insertAddress(connection, address);
        } catch (DatabaseInsertException exception) {
            addressId = -1;
        } finally {
            try {
                connection.close();
            } catch (SQLException closeConnectionException) {
                /* Do not need to do anything, connection was already closed */
            }
        }
        return addressId;
    }

    /**
     * Inserts an assessment service into the TEQ database and returns the service
     * ID.
     * 
     * @param assessment assessment service to insert
     * @return assessment service ID if successful, -1 otherwise
     */
    public static int insertAssessment(Assessment assessment) {
        int assessmentId = -1;
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        // attempt to insert into database
        try {
            assessmentId = DatabaseInserter.insertAssessment(connection, assessment);
        } catch (DatabaseInsertException exception) {
            assessmentId = -1;
        } finally {
            try {
                connection.close();
            } catch (SQLException closeConnectionException) {
                /* Do not need to do anything, connection was already closed */
            }
        }
        return assessmentId;
    }

    /**
     * Inserts a community connections service into the TEQ database and returns the
     * service ID.
     * 
     * @param community community connections service to insert
     * @return community connections service ID if successful, -1 otherwise
     */
    public static int insertCommunityConnections(CommunityConnections community) {
        int communityId = -1;
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        // attempt to insert into database
        try {
            communityId = DatabaseInserter.insertCommunityConnections(connection, community);
        } catch (DatabaseInsertException exception) {
            communityId = -1;
        } finally {
            try {
                connection.close();
            } catch (SQLException closeConnectionException) {
                /* Do not need to do anything, connection was already closed */
            }
        }
        return communityId;
    }

    /**
     * Inserts an orientation service into the TEQ database and returns the service
     * ID.
     * 
     * @param orientation orientation service to insert
     * @return orientation service ID if successful, -1 otherwise
     */
    public static int insertOrientation(Orientation orientation) {
        int orientationId = -1;
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        // attempt to insert into database
        try {
            orientationId = DatabaseInserter.insertOrientation(connection, orientation);
        } catch (DatabaseInsertException exception) {
            orientationId = -1;
        } finally {
            try {
                connection.close();
            } catch (SQLException closeConnectionException) {
                /* Do not need to do anything, connection was already closed */
            }
        }
        return orientationId;
    }

    /**
     * Inserts an employment service into the TEQ database and returns the service
     * ID.
     * 
     * @param employment employment service to insert
     * @return employment service ID if successful, -1 otherwise
     */
    public static int insertEmployment(Employment employment) {
        int employmentId = -1;
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        // attempt to insert into database
        try {
            employmentId = DatabaseInserter.insertEmployment(connection, employment);
        } catch (DatabaseInsertException exception) {
            employmentId = -1;
        } finally {
            try {
                connection.close();
            } catch (SQLException closeConnectionException) {
                /* Do not need to do anything, connection was already closed */
            }
        }
        return employmentId;
    }

    public static int insertCourseEnroll(CourseEnroll courseEnroll) {
        int enrollId = -1;
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        // attempt to insert into database
        try {
            enrollId = DatabaseInserter.insertCourseEnroll(connection, courseEnroll);
        } catch (DatabaseInsertException exception) {
            enrollId = -1;
        } finally {
            try {
                connection.close();
            } catch (SQLException closeConnectionException) {
                /* Do not need to do anything, connection was already closed */
            }
        }
        return enrollId;
    }
    
    public static int insertCourseExit(CourseExit courseExit) {
        int exitId = -1;
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        // attempt to insert into database
        try {
            exitId = DatabaseInserter.insertCourseExit(connection, courseExit);
        } catch (DatabaseInsertException exception) {
            exitId = -1;
        } finally {
            try {
                connection.close();
            } catch (SQLException closeConnectionException) {
                /* Do not need to do anything, connection was already closed */
            }
        }
        return exitId;
    }
}
