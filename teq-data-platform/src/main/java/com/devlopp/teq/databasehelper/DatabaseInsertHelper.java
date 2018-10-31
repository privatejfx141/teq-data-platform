package com.devlopp.teq.databasehelper;

import java.sql.Connection;
import java.sql.SQLException;

import com.devlopp.teq.address.Address;
import com.devlopp.teq.client.Client;
import com.devlopp.teq.database.DatabaseDriver;
import com.devlopp.teq.database.DatabaseInsertException;
import com.devlopp.teq.database.DatabaseInserter;
import com.devlopp.teq.service.assessment.Assessment;

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
     * Inserts an assessment service into the TEQ database and returns the service ID.
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
    
    
    public static int insertServiceEssentialSkill(int serviceId, int skillId) {
        int id = -1;
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        // attempt to insert into database
        try {
            id = DatabaseInserter.insertServiceEssentialSkill(connection, serviceId, skillId);
        } catch (DatabaseInsertException e) {
            id = -1;
        } finally {
            try {
                connection.close();
            } catch (SQLException closeConnectionException) {
                /* Do not need to do anything, connection was already closed */
            }
        }
        return id;
    }
}
