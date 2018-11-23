
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
     * Connects to the TEQ database and returns all the client id's in the db
     * Returns the client id's
     * 
     * @return a list containing the client id's
     * @throws SQLException on failure of selection
     */
    public static List<Integer> getClientIds() throws SQLException {
        Connection connection = DatabaseDriverHelper.connectOrCreateDatabase();
        List<Integer> clientIDList = new ArrayList<Integer>();
        String sql = "SELECT id FROM Client";
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
     * Connects to the TEQ database and a list of the ages for a client Returns the
     * client ages
     * 
     * @return a list containing the client's ages
     * @throws SQLException on failure of selection
     */
    public static List<Integer> getListOfAges() throws SQLException {
        List<Integer> clientID = DatabasePresetQuery.getClientIds();
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
     * Connects to the TEQ database and returns the average age of a client Returns
     * the average client age
     * 
     * @return a double representing the average client age
     * @throws SQLException on failure of selection
     */
    public static double getAverageClientAge() throws SQLException {
        List<Integer> clientAgeList = DatabasePresetQuery.getListOfAges();
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
        List<Integer> clientAgeList = DatabasePresetQuery.getListOfAges();
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
        }
        return listEndDate;
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
        List<Date> listStartDate = getListOfStartDates(serviceType);
        List<Date> listEndDate = getListOfEndDates(serviceType);
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

}
