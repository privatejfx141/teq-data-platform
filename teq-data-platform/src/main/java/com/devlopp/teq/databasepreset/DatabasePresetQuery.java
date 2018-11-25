
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
     * Connects to the TEQ database and returns all the client id's
     * with the given constraints Returns a list of client id's
     * 
     * @param attribute  the column you want to put a constraint on
     * @param constraint the value of the constraint
     * @return a list containing the client id's
     * @throws SQLException on failure of selection
     */
    public static List<Integer> getClientIDWithConstraint(String attribute, String constraint) throws SQLException {
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        List<Integer> clientIDList = new ArrayList<Integer>();
        String sql = "SELECT id FROM Client WHERE " + attribute + " = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, constraint);
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
     * Connects to the TEQ database and a list of
     * the ages for a client Returns the client ages
     * 
     * @return a list containing the client's ages
     * @throws SQLException on failure of selection
     */
    public static List<Integer> getListOfAges() throws SQLException {
        List<Integer> clientID = DatabaseSelectHelper.getAllClientIds();
        List<Integer> clientAgeList = new ArrayList<Integer>();
        int count = 0;
        while (count < clientID.size()) {
            int tempAge = getAgeOfClient(getBirthDate(clientID.get(count)));
            clientAgeList.add(tempAge);
            count++;
        }
        return clientAgeList;
    }

    /**
     * Given a list of client ids, return a list of their ages Returns list of
     * client ages
     * 
     * @param clientList list of client ids
     * @return a list containing the client's ages
     * @throws SQLException on failure of selection
     */
    public static List<Integer> getListOfAges(List<Integer> clientIDs) throws SQLException {
        List<Integer> clientAgeList = new ArrayList<Integer>();
        int count = 0;
        while (count < clientIDs.size()) {
            int tempAge = getAgeOfClient(getBirthDate(clientIDs.get(count)));
            clientAgeList.add(tempAge);
            count++;
        }

        return clientAgeList;
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

        return clientIDList;
    }

    /**
     * Connects to the TEQ database and returns the number of users within a certain
     * age range that are using a specific service
>>>>>>> master
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
