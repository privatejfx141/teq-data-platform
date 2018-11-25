
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
        } finally {
            connection.close();
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
     * Connects to the TEQ database and returns all the client id's with the given
     * constraints Returns a list of client id's
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
        } finally {
            connection.close();
        }
        return clientIDList;
    }

    /**
     * Connects to the TEQ database and returns a list of the ages for a client
     * Returns the client ages ======= Connects to the TEQ database and a list of
     * the ages for a client Returns the client ages >>>>>>> master
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
        } finally {
            connection.close();
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
        } finally {
            connection.close();
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
        connection.close();
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
        connection.close();
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
        connection.close();
        return clientIDList;
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
        List<Integer> serviceClientIDs = getClientIDsForService(serviceType);
        List<Integer> clientAges = getListOfAges(serviceClientIDs);
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
