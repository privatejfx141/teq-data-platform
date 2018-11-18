
package com.devlopp.teq.databasehelper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class DatabasePresetQueryHelper {
	
	
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
            while(result.next()) {
            	count = result.getInt(1);
            	return count;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new SQLException();
    }
    
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
            while(result.next()) {
            	birthDate = result.getDate(1);
            	return birthDate;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new SQLException();
    }
    
    /**
     * Connects to the TEQ database and gets the age for a specific client
     * Returns the number of clients
     * 
     * @param clientId id of the service
     * @return the age of the client
     * @throws SQLException on failure of selection
     */
    public static int getAgeOfClient(java.util.Date birthDate) throws SQLException {
        int age;
      
        // convert date format into a list of strings where a[0] = year, a[1] = month and a[2] = day
    	LocalDate now = LocalDate.now();
    	String birthDateString = birthDate.toString();
        String[] birthDateList = birthDateString.split(Pattern.quote("-"));
    	String nowString = now.toString();
        String[] nowList = nowString.split(Pattern.quote("-"));
        
        // logic for getting the age of a client
        if ((Integer.parseInt(nowList[1]) - Integer.parseInt(birthDateList[1])) > 0 ) {
        	age = Integer.parseInt(nowList[0]) - Integer.parseInt(birthDateList[0]);
        } else if ((Integer.parseInt(nowList[1]) - Integer.parseInt(birthDateList[1])) == 0 &&
        		(Integer.parseInt(nowList[2]) - Integer.parseInt(birthDateList[2])) >= 0) {
        	age = Integer.parseInt(nowList[0]) - Integer.parseInt(birthDateList[0]);
        } else {
        	age = Integer.parseInt(nowList[0]) - Integer.parseInt(birthDateList[0]) - 1;
        }

    	return age;
    }
    
    /**
     * Connects to the TEQ database and returns all the client id's in the db
     * Returns the client id's
     * 
     * @return a list containing the client id's
     * @throws SQLException on failure of selection
     */
    public static  List<Integer> getClientIds() throws SQLException {
    	Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        List<Integer> clientIDList= new ArrayList<Integer>();
        String sql = "SELECT id FROM Client";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
            	clientIDList.add(result.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientIDList;
    }
    
    /**
     * Connects to the TEQ database and a list of the ages for a client
     * Returns the client ages
     * 
     * @return a list containing the client's ages
     * @throws SQLException on failure of selection
     */
    public static  List<Integer> getListOfAges() throws SQLException {
    	List<Integer> clientID = DatabasePresetQueryHelper.getClientIds();
        List<Integer> clientAgeList= new ArrayList<Integer>();
        int count = 0;
        while(count < clientID.size()) {
        	int tempAge = getAgeOfClient(getBirthDate(clientID.get(count)));
        	clientAgeList.add(tempAge);
        	count ++;
        }

        return clientAgeList;
    }
    

    /**
     * Connects to the TEQ database and returns the average age of a client
     * Returns the average client age
     * 
     * @return a double representing the average client age
     * @throws SQLException on failure of selection
     */
    public static double getAverageClientAge() throws SQLException {
        List<Integer> clientAgeList = DatabasePresetQueryHelper.getListOfAges();
        double sum = 0;
        int count = 0;
        while(count < clientAgeList.size()) {
        	sum = sum +clientAgeList.get(count);
        	count++;
        }
        double averageAge = sum/clientAgeList.size();
        return averageAge;
    }
    
    /**
     * Connects to the TEQ database and returns the percentage of clients within an age range
     * Returns the average client age
     * 
     * @param minAge minimum range searched for
     * @param maxAge maximum age searched for
     * @return a double representing the average client age
     * @throws SQLException on failure of selection
     */
    public static String getPercentageOfClientsWithinAgeRange(int minAge, int maxAge) throws SQLException {
        List<Integer> clientAgeList = DatabasePresetQueryHelper.getListOfAges();
        double numberOfClients = clientAgeList.size();
        int count = 0, clientsWithinRange = 0;
        while (count < numberOfClients) {
        	if (clientAgeList.get(count) >= minAge && clientAgeList.get(count) <= maxAge) {
        		clientsWithinRange++;
        	}
        	count++;
        }
        
        double percentage = (clientsWithinRange/numberOfClients) * 100;
        String percentageOfClients = percentage + "%";
        return percentageOfClients;
    }
    
    

}