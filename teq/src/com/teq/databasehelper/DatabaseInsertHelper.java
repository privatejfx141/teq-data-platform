package com.teq.databasehelper;

import java.sql.Connection;
import java.sql.SQLException;

import com.teq.database.DatabaseDriver;
import com.teq.database.DatabaseInsertException;
import com.teq.database.DatabaseInserter;
import com.teq.entities.Address;

public class DatabaseInsertHelper extends DatabaseInserter {
    
    public static int insertCourseContact() {
        return -1;
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
        // get values from address
        String postalCode = address.getPostalCode();
        int streetNumber = address.getStreetNumber();
        String streetName = address.getStreetName();
        String streetDirection = address.getStreetDirection();
        String city = address.getCity();
        String province = address.getProvince();
        // attempt to insert into database
        try {
            addressId = DatabaseInserter.insertAddress(connection, postalCode, streetNumber, streetName,
                    streetDirection, city, province);
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

}
