package com.teq.databasehelper;

import java.sql.Connection;
import java.sql.SQLException;

import com.teq.address.Address;
import com.teq.client.Client;
import com.teq.database.DatabaseDriver;
import com.teq.database.DatabaseInsertException;
import com.teq.database.DatabaseInserter;

public class DatabaseInsertHelper extends DatabaseInserter {
    public static int insertClient(Client client) {
        int clientId = -1;
        Connection connection = DatabaseDriver.connectOrCreateDatabase();
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
        Connection connection = DatabaseDriver.connectOrCreateDatabase();
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

    public static int insertServiceEssentialSkill(int serviceId, int skillId) {
        int id = -1;
        Connection connection = DatabaseDriver.connectOrCreateDatabase();
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
