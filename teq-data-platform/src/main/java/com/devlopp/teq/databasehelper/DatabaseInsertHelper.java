package com.devlopp.teq.databasehelper;

import java.sql.Connection;
import java.sql.SQLException;

import com.devlopp.teq.address.Address;
import com.devlopp.teq.client.Client;
import com.devlopp.teq.course.Course;
import com.devlopp.teq.database.DatabaseInsertException;
import com.devlopp.teq.database.DatabaseInserter;
import com.devlopp.teq.security.PasswordHelper;
import com.devlopp.teq.service.Service;
import com.devlopp.teq.service.assessment.Assessment;
import com.devlopp.teq.service.commconn.CommunityConnections;
import com.devlopp.teq.service.courseenroll.CourseEnroll;
import com.devlopp.teq.service.courseexit.CourseExit;
import com.devlopp.teq.service.employment.Employment;
import com.devlopp.teq.service.orientation.Orientation;

public class DatabaseInsertHelper extends DatabaseInserter {

    /**
     * Inserts a new TEQ data platform role into the database
     * 
     * @param roleName full name of the role
     * @return ID of the role
     */
    public static int insertPlatformRole(String roleName) {
        int roleId = DatabaseValidHelper.INVALID_ID;
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        try {
            roleId = DatabaseInserter.insertPlatformRole(connection, roleName);
        } catch (DatabaseInsertException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return roleId;
    }

    /**
     * Inserts a new TEQ data platform user into the database.
     * 
     * @param username username
     * @param password cleartext password
     * @param roleId   ID of the user's role
     * @return ID of the user
     */
    public static int insertPlatformUser(String username, String password, int roleId) {
        int userId = DatabaseValidHelper.INVALID_ID;
        boolean validUsername = DatabaseValidHelper.validUsername(username);
        boolean validPassword = DatabaseValidHelper.validPassword(password);
        if (validUsername && validPassword) {
            // hash the password
            password = PasswordHelper.passwordHash(password);
            Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
            try {
                userId = DatabaseInserter.insertPlatformUser(connection, username, password, roleId);
            } catch (DatabaseInsertException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return userId;
    }

    /**
     * Inserts a TEQ record into the database, and returns the record ID.
     * 
     * @param record TEQ record to insert (i.e. client, service, course, etc.)
     * @return record ID
     */
    public static Object insertRecord(Object record) {
        Object recordId = DatabaseValidHelper.INVALID_ID;
        if (record instanceof Client) {
            recordId = insertClient((Client) record);
        } else if (record instanceof Assessment) {
            recordId = insertAssessment((Assessment) record);
        } else if (record instanceof CommunityConnections) {
            recordId = insertCommunityConnections((CommunityConnections) record);
        } else if (record instanceof Orientation) {
            recordId = insertOrientation((Orientation) record);
        } else if (record instanceof Employment) {
            recordId = insertEmployment((Employment) record);
        } else if (record instanceof Course) {
            recordId = insertCourse((Course) record);
        } else if (record instanceof CourseEnroll) {
            recordId = insertCourseEnroll((CourseEnroll) record);
        } else if (record instanceof CourseExit) {
            recordId = insertCourseExit((CourseExit) record);
        }
        return recordId;
    }

    /**
     * Inserts client details into the TEQ database and returns the client ID if
     * successful.
     * 
     * @param client client details to insert
     * @return client ID if successful, -1 otherwise
     */
    public static int insertClient(Client client) {
        int clientId = DatabaseValidHelper.INVALID_ID;
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        try {
            clientId = DatabaseInserter.insertClient(connection, client);
        } catch (DatabaseInsertException exception) {
            clientId = DatabaseValidHelper.INVALID_ID;
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
        int addressId = DatabaseValidHelper.INVALID_ID;
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        // attempt to insert into database
        try {
            addressId = DatabaseInserter.insertAddress(connection, address);
        } catch (DatabaseInsertException exception) {
            addressId = DatabaseValidHelper.INVALID_ID;
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
     * Inserts a service into the TEQ database and returns the service ID if
     * successful.
     * 
     * @param service service to insert
     * @return service ID if successful, -1 otherwise
     */
    public static int insertService(Service service) {
        int serviceId = DatabaseValidHelper.INVALID_ID;
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        if (service instanceof Assessment) {
            serviceId = insertAssessment((Assessment) service);
        } else if (service instanceof CommunityConnections) {
            serviceId = insertCommunityConnections((CommunityConnections) service);
        } else if (service instanceof Orientation) {
            serviceId = insertOrientation((Orientation) service);
        } else if (service instanceof Employment) {
            serviceId = insertEmployment((Employment) service);
        } else if (service instanceof CourseEnroll) {
            serviceId = insertCourseEnroll((CourseEnroll) service);
        } else if (service instanceof CourseExit) {
            serviceId = insertCourseExit((CourseExit) service);
        } else {
            try {
                serviceId = DatabaseInserter.insertService(connection, service);
            } catch (DatabaseInsertException e) {
                serviceId = DatabaseValidHelper.INVALID_ID;
            }
        }
        return serviceId;
    }

    /**
     * Inserts an assessment service into the TEQ database and returns the service
     * ID.
     * 
     * @param assessment assessment service to insert
     * @return assessment service ID if successful, -1 otherwise
     */
    public static int insertAssessment(Assessment assessment) {
        int assessmentId = DatabaseValidHelper.INVALID_ID;
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        // attempt to insert into database
        try {
            assessmentId = DatabaseInserter.insertAssessment(connection, assessment);
        } catch (DatabaseInsertException exception) {
            assessmentId = DatabaseValidHelper.INVALID_ID;
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
        int communityId = DatabaseValidHelper.INVALID_ID;
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        // attempt to insert into database
        try {
            communityId = DatabaseInserter.insertCommunityConnections(connection, community);
        } catch (DatabaseInsertException exception) {
            communityId = DatabaseValidHelper.INVALID_ID;
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
        int orientationId = DatabaseValidHelper.INVALID_ID;
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        // attempt to insert into database
        try {
            orientationId = DatabaseInserter.insertOrientation(connection, orientation);
        } catch (DatabaseInsertException exception) {
            orientationId = DatabaseValidHelper.INVALID_ID;
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
        int employmentId = DatabaseValidHelper.INVALID_ID;
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        // attempt to insert into database
        try {
            employmentId = DatabaseInserter.insertEmployment(connection, employment);
        } catch (DatabaseInsertException exception) {
            employmentId = DatabaseValidHelper.INVALID_ID;
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
        int enrollId = DatabaseValidHelper.INVALID_ID;
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        // attempt to insert into database
        try {
            enrollId = DatabaseInserter.insertCourseEnroll(connection, courseEnroll);
        } catch (DatabaseInsertException exception) {
            enrollId = DatabaseValidHelper.INVALID_ID;
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
        int exitId = DatabaseValidHelper.INVALID_ID;
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        // attempt to insert into database
        try {
            exitId = DatabaseInserter.insertCourseExit(connection, courseExit);
        } catch (DatabaseInsertException exception) {
            exitId = DatabaseValidHelper.INVALID_ID;
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
