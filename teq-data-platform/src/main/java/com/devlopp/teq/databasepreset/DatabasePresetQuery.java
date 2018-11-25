
package com.devlopp.teq.databasepreset;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import com.devlopp.teq.databasehelper.DatabaseDriverHelper;

public class DatabasePresetQuery {

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

    /**
     * Connects to the TEQ database and returns the average age of a client Returns
     * the average client age
     * 
     * @return a double representing the average client age
     * @throws SQLException on failure of selection
     */
    public static double getAverageClientAge() throws SQLException {
        List<Integer> clientAgeList = DatabasePresetQueryHelper.getListOfAges();
        double sum = 0;
        int count = 0;
        while (count < clientAgeList.size()) {
            sum = sum + clientAgeList.get(count);
            count++;
        }
        double averageAge = sum / clientAgeList.size();
        return averageAge;
    }

    /**
     * Connects to the TEQ database and returns the percentage of clients within an
     * age range Returns the average client age
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
        double percentage = (clientsWithinRange / numberOfClients) * 100;
        String percentageOfClients = percentage + "%";
        return percentageOfClients;
    }


    /**
     * Connects to the TEQ database and returns the number of users that have used a
     * service within the start date and end date
     * 
     * @param serviceType type of service queried
     * @param startDate   date where usage started must be in format "YYYY-MM-DD"
     * @return list of start dates for a service
     * @throws SQLException on failure of selection
     */
    public static int getNumOfUsersWithinRange(String serviceType, java.util.Date startDate, java.util.Date endDate)
            throws SQLException {
        int numberOfUsers = 0;
        List<Date> listStartDate = DatabasePresetQueryHelper.getListOfStartDates(serviceType);
        List<Date> listEndDate = DatabasePresetQueryHelper.getListOfEndDates(serviceType);
        int count = 0;
        while (count < listStartDate.size()) {
            if (!(listStartDate.get(count).before(startDate) || listStartDate.get(count).after(endDate))) {
                numberOfUsers++;
            } else if (!(listEndDate.get(count).before(startDate) || listEndDate.get(count).after(endDate))) {
                numberOfUsers++;
            }
            count++;
        }
        return numberOfUsers;
    }

    /**
     * Connects to the TEQ database and returns the number of users within a certain
     * age range that are using a specific service
     * 
     * @param serviceType type of service queried
     * @param ageRange    must be in format "20-30,40-45,..." where age ranges are
     *                    separated by a comma
     * @return the number of users using the service within the age range
     * @throws SQLException on failure of selection
     */
    public static int getNumUsersOfServiceWithinAgeRange(String serviceType, String ageRange) throws SQLException {
    	int numberOfUsers = 0;
        List<Integer> serviceClientIDs = DatabasePresetQueryHelper.getClientIDsForService(serviceType);
        List<Integer> clientAges = DatabasePresetQueryHelper.getListOfAges(serviceClientIDs);
        String[] ageRanges = ageRange.split(Pattern.quote(","));
        // split age ranges at the '-' and check whether or not an a client is within
        // that age range
        for (int i = 0; i < ageRanges.length; i++) {
            String[] rangeSplit = ageRanges[i].split(Pattern.quote("-"));
            for (int k = 0; k < clientAges.size(); k++) {
                if (clientAges.get(k) >= Integer.parseInt(rangeSplit[0])
                        && clientAges.get(k) <= Integer.parseInt(rangeSplit[1])) {
                    numberOfUsers++;
                }
            }
        }
        return numberOfUsers;
    }
    
    /**
     * Connects to the TEQ database and returns a string with the number of different languages spoken for a service
     * 
     * @param serviceType type of service queried
     * @return a string with of the number of languages spoken
     * @throws SQLException on failure of selection
     */
    public static String getLanguagesSpoken(String serviceType) throws SQLException {
    	HashMap<String,Integer> languages = new HashMap<String,Integer>();
        List<Integer> serviceClientIDs = DatabasePresetQueryHelper.getClientIDsForService(serviceType);
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        for(int i=0; i<serviceClientIDs.size();i++) {
        	String sql = "SELECT language FROM Client WHERE id = ?";
            try {
            	PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, serviceClientIDs.get(i));
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                	if(languages.containsKey(result.getString(1))) {
                		int count = languages.get(result.getString(1));
                		languages.put(result.getString(1), count + 1);
                	} else {
                		languages.put(result.getString(1), 1);
                	}
                    
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }	
        }
        String languagesSpoken = "Languages Spoken\n";
        for (String key : languages.keySet()) {
        	languagesSpoken = languagesSpoken +key + ":" +languages.get(key) + "\n" ;
        }
        return languagesSpoken.substring(0, languagesSpoken.length() - 1);
    }
    
    /**
     * Connects to the TEQ database and returns the number of users for each service
     * @return the number of users for all services
     * @throws SQLException on failure of selection
     */
    public static String getNumberUsersServices() throws SQLException {
    	String userCount = "Number of Users\n";
    	String[] allServices = {"CommunityConnections","Assessment","Orientation","Course","Employment","CourseEnroll","CourseExit",};
    	Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        for(int i=0; i<allServices.length;i++) {
        	String sql = "SELECT Count(*) FROM " + allServices[i];
            try {
            	PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                	userCount = userCount + allServices[i] + ":" + result.getInt(1) + "\n";
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }	
        }
        return userCount.substring(0, userCount.length() - 1);
    }

}
