package com.teq.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.teq.entities.Address;
import com.teq.entities.Client;
import com.teq.entities.Course;
import com.teq.entities.Service;

public class DatabaseInserter {
    /**
     * Inserts a client into the TEQ database and returns the client ID if insertion
     * was successful.
     * 
     * @param connection connection to the TEQ database
     * @param client     client info to insert
     * @return client ID (primary key) of the inserted client
     */
    protected static int insertClient(Connection connection, Client client) {
        return -1;
    }

    /**
     * Inserts a service into the TEQ database and returns the service ID if
     * insertion was successful.
     * 
     * @param connection connection to the TEQ database
     * @param service    service info to insert
     * @return service ID (primary key) of the inserted service
     */
    protected static int insertService(Connection connection, Service service) {
        return -1;
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
     * 
     * @param connection      connection to the TEQ database
     * @param postalCode      postal code
     * @param streetNumber    street number
     * @param streetName      street name
     * @param streetDirection street direction (N, S, E, W)
     * @param city            city name
     * @param province        province name
     * @return the address ID number, -1 otherwise
     * @throws DatabaseInsertException
     */
    protected static int insertAddress(Connection connection, String postalCode, int streetNumber, String streetName,
            String streetDirection, String city, String province) throws DatabaseInsertException {
        String sql = "INSERT INTO Address(postal_code,street_number,street_name,street_direction,city,province)"
                + " VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, postalCode);
            statement.setInt(2, streetNumber);
            statement.setString(3, streetName);
            statement.setString(4, streetDirection);
            statement.setString(5, city);
            statement.setString(6, province);
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
