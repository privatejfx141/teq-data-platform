package com.teq.database;

import java.sql.Connection;

import com.teq.entities.Client;
import com.teq.entities.Course;
import com.teq.entities.Service;

public class DatabaseInserter {

    /**
     * Inserts a client into the TEQ database and returns the client ID if insertion was successful.
     * 
     * @param connection connection to the TEQ database
     * @param client client info to insert
     * @return client ID (primary key) of the inserted client
     */
    protected static int insertClient(Connection connection, Client client) {
        return -1;
    }
    
    /**
     * Inserts a service into the TEQ database and returns the service ID if insertion was successful.
     * 
     * @param connection connection to the TEQ database
     * @param service service info to insert
     * @return service ID (primary key) of the inserted service
     */
    protected static int insertService(Connection connection, Service service) {
        return -1;
    }
    
    /**
     * Inserts a course into the TEQ database and returns the course code if insertion was successful.
     * 
     * @param connection connection to the TEQ database
     * @param course course info to insert
     * @return course code (primary key) of the inserted course
     */
    protected static String insertCourse(Connection connection, Course course) {
        return "";
    }

}
