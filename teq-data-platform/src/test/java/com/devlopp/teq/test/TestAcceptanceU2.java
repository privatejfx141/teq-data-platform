package com.devlopp.teq.test;

import com.devlopp.teq.client.Client;
import com.devlopp.teq.course.Course;
import com.devlopp.teq.databasehelper.DatabaseDriverHelper;
import com.devlopp.teq.databasehelper.DatabaseInsertHelper;

import com.devlopp.teq.parser.AssessmentParser;
import com.devlopp.teq.parser.ClientProfileParser;
import com.devlopp.teq.parser.CommunityConnectionsParser;
import com.devlopp.teq.parser.CourseEnrollParser;
import com.devlopp.teq.parser.CourseExitParser;
import com.devlopp.teq.parser.CourseSetupParser;
import com.devlopp.teq.parser.EmploymentParser;
import com.devlopp.teq.parser.OrientationParser;
import com.devlopp.teq.parser.TemplateParser;

import com.devlopp.teq.service.assessment.Assessment;
import com.devlopp.teq.service.commconn.CommunityConnections;
import com.devlopp.teq.service.courseenroll.CourseEnroll;
import com.devlopp.teq.service.courseexit.CourseExit;
import com.devlopp.teq.service.employment.Employment;
import com.devlopp.teq.service.orientation.Orientation;

public class TestAcceptanceU2 {

    public static void testAcceptanceU2() {
        boolean dbExists = DatabaseDriverHelper.databaseExists();
        System.out.println("Database exists: " + dbExists);
        if (!dbExists) {
            DatabaseDriverHelper.initializeDatabase();
        }

        String filePath = "src/main/java/com/devlopp/teq/excel/iCARE_Templates.xlsx";

        TemplateParser parser;
        parser = new ClientProfileParser();
        parser.read(filePath, 2);
        for (Object record : parser.parse()) {
            Client client = (Client) record;
            DatabaseInsertHelper.insertClient(client);
        }

        parser = new AssessmentParser();
        parser.read(filePath, 3);
        for (Object record : parser.parse()) {
            Assessment service = (Assessment) record;
            DatabaseInsertHelper.insertAssessment(service);
        }

        parser = new CommunityConnectionsParser();
        parser.read(filePath, 4);
        for (Object record : parser.parse()) {
            CommunityConnections service = (CommunityConnections) record;
            DatabaseInsertHelper.insertCommunityConnections(service);
        }

        parser = new OrientationParser();
        parser.read(filePath, 5);
        for (Object record : parser.parse()) {
            Orientation service = (Orientation) record;
            DatabaseInsertHelper.insertOrientation(service);
        }

        parser = new EmploymentParser();
        parser.read(filePath, 6);
        for (Object record : parser.parse()) {
            Employment service = (Employment) record;
            DatabaseInsertHelper.insertEmployment(service);
        }

        parser = new CourseEnrollParser();
        parser.read(filePath, 7);
        for (Object record : parser.parse()) {
            CourseEnroll service = (CourseEnroll) record;
            DatabaseInsertHelper.insertCourseEnroll(service);
        }

        parser = new CourseSetupParser();
        parser.read(filePath, 8);
        for (Object record : parser.parse()) {
            Course course = (Course) record;
            DatabaseInsertHelper.insertCourse(course);
        }

        parser = new CourseExitParser();
        parser.read(filePath, 9);
        for (Object record : parser.parse()) {
            CourseExit service = (CourseExit) record;
            DatabaseInsertHelper.insertCourseExit(service);
        }

    }

}