
package com.devlopp.teq.databasepreset;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import com.devlopp.teq.databasehelper.DatabaseDriverHelper;
import com.devlopp.teq.databasehelper.DatabaseSelectHelper;

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
        } finally {
            connection.close();
        }
        throw new SQLException();
    }

    /**
     * Connects to the TEQ database and returns the average age of all clients.
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
     * age range for all clients
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
<<<<<<< HEAD
=======
<<<<<<< HEAD
     * Connects to the TEQ database and returns the client ids for the user of a
     * service
     * 
     * @param serviceType type of service queried
     * @return clientIDList list of client ids of users that user a specific service
     * @throws SQLException on failure of selection
     */
    public static List<Integer> getClientIDsForService(String serviceType) throws SQLException {
        List<Integer> clientIDList = new ArrayList<Integer>();
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        String sql = "SELECT client_id FROM Service," + serviceType + " WHERE Service.ID = " + serviceType
                + ".service_id";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                clientIDList.add(result.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection.close();
        return clientIDList;
    }

    /**
>>>>>>> c201e47d1cc02b9ee0896ab3d933f737aa9bd894
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

    /**
     * Connects to the TEQ database and returns a list of IDs of clients residing at
     * the address with the given postal code.
     * 
     * @param postalCode postal code
     * @return list of client IDs at the postal code
     * @throws SQLException on failure of selection
     */
    public static List<Integer> getClientIDsAtAddress(String postalCode) throws SQLException {
        List<Integer> clientIds = new ArrayList<>();
        String sql = "SELECT DISTINCT c.id FROM Client c WHERE address_id IN "
                + "(SELECT DISTINCT a.id FROM Address a WHERE UPPER(postal_code) = ?)";
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, postalCode.toUpperCase());
        ResultSet results = statement.executeQuery();
        while (results.next()) {
            clientIds.add(results.getInt(1));
        }
        connection.close();
        return clientIds;
    }

    /**
     * Connects to the TEQ database and returns a list of IDs of clients residing at
     * the given city and province.
     * 
     * @param city     name of city
     * @param province name of province
     * @return list of IDs of clients residing at city, province
     * @throws SQLException on failure of selection
     */
    public static List<Integer> getClientIDsAtAddress(String city, String province) throws SQLException {
        List<Integer> clientIds = new ArrayList<>();
        String sql = "SELECT DISTINCT c.id FROM Client c WHERE address_id IN "
                + "(SELECT DISTINCT a.id FROM Address a WHERE LOWER(city) = ? AND LOWER(province) = ?)";
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, city.toLowerCase());
        statement.setString(2, province.toLowerCase());
        ResultSet results = statement.executeQuery();
        while (results.next()) {
            clientIds.add(results.getInt(1));
        }
        connection.close();
        return clientIds;
    }

    /**
     * Connects to the TEQ database and returns a list of IDs of clients that have
     * taken the course (entered or exited) of the given course code.
     * 
     * @param courseCode course code
     * @return list of IDs of clients that taken course
     * @throws SQLException on failure of selection
     */
    public static List<Integer> getAllCourseStudents(String courseCode) throws SQLException {
        List<Integer> clientIds = new ArrayList<>();
        String sql = "SELECT DISTINCT client_id FROM (SELECT s.client_id FROM Service s JOIN CourseEnroll ce "
                + "ON (s.id = ce.service_id) WHERE UPPER(course_code) = ? UNION SELECT s.client_id "
                + "FROM Service s JOIN CourseExit cx ON (s.id = cx.service_id) WHERE UPPER(course_code) = ?) "
                + "ORDER BY client_id";
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, courseCode.toUpperCase());
        statement.setString(2, courseCode.toUpperCase());
        ResultSet results = statement.executeQuery();
        while (results.next()) {
            clientIds.add(results.getInt(1));
        }
        connection.close();
        return clientIds;
    }

    /**
     * Connects to the TEQ database and returns a list of course codes of courses taken
     * by the client with the given client ID.
     * 
     * @param clientId ID of the client
     * @return list of course codes of the courses taken
     * @throws SQLException on failure of selection
     */
    public static List<String> getCoursesTaken(int clientId) throws SQLException {
        List<String> courseCodes = new ArrayList<>();
        String sql = "SELECT DISTINCT course_code FROM (SELECT ce.course_code FROM CourseEnroll ce JOIN Service s "
                + "ON (s.id = ce.service_id) WHERE s.client_id = ? UNION SELECT cx.course_code FROM CourseExit cx "
                + "JOIN Service s ON (s.id = cx.service_id) WHERE s.client_id = ?) ORDER BY course_code";
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, clientId);
        statement.setInt(2, clientId);
        ResultSet results = statement.executeQuery();
        while (results.next()) {
            courseCodes.add(results.getString(1));
        }
        connection.close();
        return courseCodes;
    }

}
