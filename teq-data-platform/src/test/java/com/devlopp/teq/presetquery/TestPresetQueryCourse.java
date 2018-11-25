package com.devlopp.teq.presetquery;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.devlopp.teq.address.Address;
import com.devlopp.teq.address.AddressBuilder;
import com.devlopp.teq.address.IAddressBuilder;
import com.devlopp.teq.app.ExcelDriver;
import com.devlopp.teq.client.Client;
import com.devlopp.teq.client.ClientBuilder;
import com.devlopp.teq.client.IClientBuilder;
import com.devlopp.teq.course.Course;
import com.devlopp.teq.course.CourseBuilder;
import com.devlopp.teq.course.CourseContact;
import com.devlopp.teq.course.CourseContactBuilder;
import com.devlopp.teq.course.ICourseBuilder;
import com.devlopp.teq.databasehelper.DatabaseDriverHelper;
import com.devlopp.teq.databasehelper.DatabaseInsertHelper;
import com.devlopp.teq.databasepreset.DatabasePresetQuery;
import com.devlopp.teq.service.courseenroll.CourseEnroll;
import com.devlopp.teq.service.courseenroll.CourseEnrollBuilder;
import com.devlopp.teq.service.courseenroll.ICourseEnrollBuilder;

public class TestPresetQueryCourse {

    static int clientIdSt = 1;

    static void cleanDb() {
        if (!DatabaseDriverHelper.databaseExists() || DatabaseDriverHelper.deleteDatabase()) {
            DatabaseDriverHelper.initializeDatabase();
        }
    }

    static Address createAddress() {
        IAddressBuilder addressBuilder = new AddressBuilder();
        Address address = addressBuilder.setPostalCode("M1G3L2") //
                .setUnitNumber(Integer.parseInt("1606")) //
                .setStreetNumber(Integer.parseInt("3050")) //
                .setStreetName("Ellesmere") //
                .setStreetType("Road") //
                .setStreetDirection(ExcelDriver.fixDirection("East")) //
                .setCity("Toronto") //
                .setProvince("Ontario") //
                .create();
        return address;
    }

    static int createAndInsertClient() {
        Address address = createAddress();
        // insert address to db
        DatabaseInsertHelper.insertAddress(address);
        // client object
        IClientBuilder clientBuilder = new ClientBuilder();
        Client client = clientBuilder.setId(clientIdSt++) //
                .setIdType(1) //
                .setBirthDate("1965-09-13") //
                .setPhoneNumber("1234567890") //
                .setEmailAddress("test@gmail.com") //
                .setAddress(address) //
                .setLanguage("ENG") //
                .setConsent(true) //
                .create();
        // insert client to db with address
        return DatabaseInsertHelper.insertClient(client);
    }

    static String createAndInsertCourse(String courseCode) {
        // create address
        Address address = createAddress();
        // create course contact
        CourseContactBuilder contactBuilder = new CourseContactBuilder();
        CourseContact contact = contactBuilder //
                .setContactName("Hope Nester") //
                .setAddress(address) //
                .setTelephoneNumber("416-757-7010") //
                .setTelephoneExt("2232") //
                .setEmailAddress("hnester@cathcrosscultural.org") //
                .create();
        // create course
        ICourseBuilder builder = new CourseBuilder();
        Course course = builder.setCourseCode(courseCode) //
                .setCourseContact(contact) //
                .setOngoingBasis(false) //
                .setTrainingFormat("Training format") //
                .setNumberOfSpots(10) //
                .setNumberOfIRCC(10) //
                .setEnrollmentType("Enrollment type") //
                .setStartDate("2018-09-01") //
                .setEndDate("2018-10-01") //
                .setInstructHours("100") //
                .setWeeklyHours(10) //
                .setDominantFocus("Dominant focus") //
                .create();
        return DatabaseInsertHelper.insertCourse(course);
    }

    static int createAndInsertCourseEnroll(String courseCode, int clientId) {
        ICourseEnrollBuilder builder = new CourseEnrollBuilder();
        CourseEnroll enroll = (CourseEnroll) builder //
                .setCourseCode(courseCode) //
                .setFirstClassDate("2018-09-10") //
                .setClientId(clientId) //
                .create();
        return DatabaseInsertHelper.insertCourseEnroll(enroll);
    }

    @Test
    @DisplayName("test one client id from course")
    void testOneCourseStudent() throws SQLException {
        cleanDb();
        int clientId = createAndInsertClient();
        String courseCode = createAndInsertCourse("L-CCSNAR18008");
        createAndInsertCourseEnroll(courseCode, clientId);
        List<Integer> clientIds = DatabasePresetQuery.getAllCourseStudents(courseCode);
        assertEquals(1, clientIds.size());
        assertEquals(clientId, clientIds.get(0).intValue());
    }

    @Test
    @DisplayName("test many client ids from course (enrol)")
    void testManyCourseStudents() throws SQLException {
        cleanDb();
        int clientId1 = createAndInsertClient();
        int clientId2 = createAndInsertClient();
        int clientId3 = createAndInsertClient();
        String courseCode = createAndInsertCourse("L-CCSNAR18008");
        createAndInsertCourseEnroll(courseCode, clientId1);
        createAndInsertCourseEnroll(courseCode, clientId2);
        createAndInsertCourseEnroll(courseCode, clientId3);
        List<Integer> clientIds = DatabasePresetQuery.getAllCourseStudents(courseCode);
        assertEquals(3, clientIds.size());
        assertTrue(clientIds.contains(clientId1));
        assertTrue(clientIds.contains(clientId2));
        assertTrue(clientIds.contains(clientId3));
    }

}
