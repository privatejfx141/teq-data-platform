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
import com.teq.client.Client;
import com.teq.client.ClientBuilder;
import com.teq.client.IClientBuilder;
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

    public static Client getClient(int clientId) {
        Client client = null;
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        try {
            ResultSet results = DatabaseSelector.getClient(connection, clientId);
            while (results.next()) {       
                IClientBuilder builder = new ClientBuilder();
                client = builder.setId(results.getInt("id"))
                        .setIdType(results.getInt("id_type"))
                        .setBirthDate(results.getDate("birth_date").toString())
                        .setPhoneNumber(results.getString("phone_number"))
                        .setEmailAddress(results.getString("email_address"))
                        .setAddress(getAddress(results.getInt("address_id")))
                        .setLanguage(results.getString("language"))
                        .setConsent(results.getBoolean("consents"))
                        .create();
            }
        } catch (SQLException e) {
            client = null;
        } finally {
            try {
                connection.close();
            } catch (SQLException closeConnectionException) {
                /* Do not need to do anything, connection was already closed */
            }
        }
        return client;
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
                address = builder.setId(results.getInt("id"))
                        .setPostalCode(results.getString("postal_code"))
                        .setUnitNumber(results.getInt("unit_number"))
                        .setStreetNumber(results.getInt("street_number"))
                        .setStreetName(results.getString("street_name"))
                        .setStreetType(results.getString("street_type"))
                        .setStreetDirection(results.getString("street_direction"))
                        .setCity(results.getString("city"))
                        .setProvince(results.getString("province"))
                        .create();
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
