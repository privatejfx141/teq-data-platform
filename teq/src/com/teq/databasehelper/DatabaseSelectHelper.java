package com.teq.databasehelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.teq.address.Address;
import com.teq.address.AddressBuilder;
import com.teq.address.IAddressBuilder;
import com.teq.database.DatabaseDriver;
import com.teq.database.DatabaseSelector;

public class DatabaseSelectHelper extends DatabaseSelector {
    public static List<String> getAllTypes(String tableName) {
        List<String> list = new ArrayList<>();
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        try {
            ResultSet results = DatabaseSelector.getAllTypes(connection, tableName);
            while (results.next()) {
                list.add(results.getString("description"));
            }
        } catch (SQLException e) {
            list.clear();
        } finally {
            try {
                connection.close();
            } catch (SQLException closeConnectionException) {
                /* Do not need to do anything, connection was already closed */
            }
        }
        return list;
    }

    public static List<String> getServiceEssentialSkill(int serviceId) {
        List<String> list = new ArrayList<>();
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        try {
            ResultSet results = DatabaseSelector.getServiceEssentialSkill(connection, serviceId);
            while (results.next()) {
                list.add(results.getString(1));
            }
        } catch (SQLException e) {
            list.clear();
        } finally {
            try {
                connection.close();
            } catch (SQLException closeConnectionException) {
                /* Do not need to do anything, connection was already closed */
            }
        }
        return list;
    }

    /**
     * Connects to database, obtains and returns an address with ID number addressId
     * from the Address table. Returns <code>null</code> if addressId is invalid.
     * 
     * @param addressId address ID
     * @return address with the ID number addressId, <code>null</code> if invalid
     *         addressId
     */
    public static Address getAddress(int addressId) {
        Address address = null;
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        try {
            ResultSet results = DatabaseSelector.getAddress(connection, addressId);
            while (results.next()) {
                IAddressBuilder builder = new AddressBuilder();
                address = builder.setId(results.getInt("id")).setPostalCode(results.getString("postal_code"))
                        .setStreetNumber(results.getInt("street_number"))
                        .setStreetName(results.getString("street_name"))
                        .setStreetDirection(results.getString("street_direction")).setCity(results.getString("city"))
                        .setProvince(results.getString("province")).create();
            }
        } catch (SQLException e) {
            address = null;
        } finally {
            try {
                connection.close();
            } catch (SQLException closeConnectionException) {
                /* Do not need to do anything, connection was already closed */
            }
        }
        return address;
    }
}
