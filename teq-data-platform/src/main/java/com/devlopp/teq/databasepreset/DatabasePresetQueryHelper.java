package com.devlopp.teq.databasepreset;

import java.sql.Connection;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.devlopp.teq.databasehelper.DatabaseDriverHelper;
import com.devlopp.teq.databasehelper.DatabaseSelectHelper;

public class DatabasePresetQueryHelper {
	
    /**
     * Connects to the TEQ database and gets the date of birth for a specific client
     * Returns the birth date of a client
     * 
     * @param clientId id of the service
     * @return the age of the client
     * @throws SQLException on failure of selection
     */
    public static Date getBirthDate(int clientId) throws SQLException {
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        String sql = "SELECT birth_date FROM Client WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, clientId);
            ResultSet result = statement.executeQuery();
            Date birthDate;
            while (result.next()) {
                birthDate = result.getDate(1);
                return birthDate;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new SQLException();
    }
    
    /**
     * Connects to the TEQ database and gets the age for a specific client Returns
     * the number of clients
     * 
     * @param birthDate Date object representing birthday must be in form YYYY-MM-DD
     * @return the age of the client
     * @throws SQLException on failure of selection
     */
    public static int getAgeOfClient(java.util.Date birthDate) throws SQLException {
        int age;
        // convert date format into a list of strings where a[0] = year, a[1] = month
        // and a[2] = day
        LocalDate now = LocalDate.now();
        String birthDateString = birthDate.toString();
        String[] birthDateList = birthDateString.split(Pattern.quote("-"));
        String nowString = now.toString();
        String[] nowList = nowString.split(Pattern.quote("-"));
        // logic for getting the age of a client
        if ((Integer.parseInt(nowList[1]) - Integer.parseInt(birthDateList[1])) > 0) {
            age = Integer.parseInt(nowList[0]) - Integer.parseInt(birthDateList[0]);
        } else if ((Integer.parseInt(nowList[1]) - Integer.parseInt(birthDateList[1])) == 0
                && (Integer.parseInt(nowList[2]) - Integer.parseInt(birthDateList[2])) >= 0) {
            age = Integer.parseInt(nowList[0]) - Integer.parseInt(birthDateList[0]);
        } else {
            age = Integer.parseInt(nowList[0]) - Integer.parseInt(birthDateList[0]) - 1;
        }

        return age;
    }
    
    
    /**
     * Connects to the TEQ database and returns all the client id's with the given constraints
     * Returns a list of client id's
     * 
     * @param attribute the column you want to put a constraint on
     * @param constraint the value of the constraint
     * @return a list containing the client id's
     * @throws SQLException on failure of selection
     */
    public static  List<Integer> getClientIDWithConstraint(String attribute, String constraint) throws SQLException {
    	Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        List<Integer> clientIDList= new ArrayList<Integer>();
        String sql = "SELECT id FROM Client WHERE " + attribute + " = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, constraint );
            ResultSet result = statement.executeQuery();
            while (result.next()) {
            	clientIDList.add(result.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientIDList;
    }
    
    /**
     * Connects to the TEQ database and returns a list of the ages for a client
     * Returns the client ages
     * 
     * @return a list containing the client's ages
     * @throws SQLException on failure of selection
     */
    public static List<Integer> getListOfAges() throws SQLException {
        List<Integer> clientID = DatabaseSelectHelper.getAllClientIds();
        List<Integer> clientAgeList = new ArrayList<Integer>();
        int count = 0;
        while (count < clientID.size()) {
            int tempAge = DatabasePresetQueryHelper.getAgeOfClient(DatabasePresetQueryHelper.getBirthDate(clientID.get(count)));
            clientAgeList.add(tempAge);
            count++;
        }
        return clientAgeList;
    }
    
    /**
     * Given a list of client ids, return a list of their ages
     * Returns list of client ages
     * 
     * @param clientList list of client ids
     * @return a list containing the client's ages
     * @throws SQLException on failure of selection
     */
    public static  List<Integer> getListOfAges(List<Integer> clientIDs) throws SQLException {
        List<Integer> clientAgeList= new ArrayList<Integer>();
        int count = 0;
        while (count < clientIDs.size()) {
        	int tempAge = DatabasePresetQueryHelper.getAgeOfClient(DatabasePresetQueryHelper.getBirthDate(clientIDs.get(count)));
        	clientAgeList.add(tempAge);
        	count ++;
        }

        return clientAgeList;
    }

    /**
     * Connects to the TEQ database and returns the client ids for the user of a service
     * 
     * @param serviceType type of service queried
     * @return clientIDList list of client ids of users that user a specific service
     * @throws SQLException on failure of selection
     */
    public static List<Integer> getClientIDsForService(String serviceType) throws SQLException {
       
        List<Integer> clientIDList = new ArrayList<Integer>();
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        String sql = "SELECT client_id FROM Service," + serviceType + " WHERE Service.ID = " + serviceType + ".service_id";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
            	clientIDList.add(result.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
   
        return clientIDList;
    }
    
    /**
     * Connects to the TEQ database and returns the start date and end date of a
     * service Returns the start date of service
     * 
     * @param serviceId   id of the service
     * @param serviceType type of service queried
     * @return a Date object representing the start date
     * @throws SQLException on failure of selection
     */
    public static Date getServiceStartDate(int serviceId, String serviceType) throws SQLException {
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        String sql = "SELECT start_date FROM " + serviceType + " WHERE service_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, serviceId);
            ResultSet result = statement.executeQuery();
            Date startDate;
            while (result.next()) {
                startDate = result.getDate(1);
                return startDate;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new SQLException();
    }

    /**
     * Connects to the TEQ database and returns the end date and end date of a
     * service Returns the start date of service
     * 
     * @param serviceId   id of the service
     * @param serviceType type of service queried
     * @return a Date object representing the start date
     * @throws SQLException on failure of selection
     */
    public static Date getServiceEndDate(int serviceId, String serviceType) throws SQLException {
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        String sql = "SELECT end_date FROM " + serviceType + " WHERE service_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, serviceId);
            ResultSet result = statement.executeQuery();
            Date endDate;
            while (result.next()) {
                endDate = result.getDate(1);
                return endDate;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new SQLException();
    }
    
    /**
     * Connects to the TEQ database and returns a list of the start date for a
     * service
     * 
     * @param serviceType type of service queried
     * @return list of start dates for a service
     * @throws SQLException on failure of selection
     */
    public static List<Date> getListOfStartDates(String serviceType) throws SQLException {
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        String sql = "SELECT start_date FROM " + serviceType;
        List<Date> listStartDate = new ArrayList<Date>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                listStartDate.add(result.getDate(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listStartDate;
    }

    /**
     * Connects to the TEQ database and returns a list of the end date for a service
     * 
     * @param serviceType type of service queried
     * @return list of end dates for a service
     * @throws SQLException on failure of selection
     */
    public static List<Date> getListOfEndDates(String serviceType) throws SQLException {
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        String sql = "SELECT end_date FROM " + serviceType;
        List<Date> listEndDate = new ArrayList<Date>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                listEndDate.add(result.getDate(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return listEndDate;
    }
    
    /**
     * Connects to the TEQ database and gets the number of clients for a service
     * Returns the number of clients
     * 
     * @param serviceId id of the service
     * @return the number of clients in a service
     * @throws SQLException on failure of selection
     */
    public static int getNumberOfClients(int serviceId) throws SQLException {
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        String sql = "SELECT count(*) FROM Service WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, serviceId);
            ResultSet result = statement.executeQuery();
            int count;
            while (result.next()) {
                count = result.getInt(1);
                return count;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new SQLException();
    }

}