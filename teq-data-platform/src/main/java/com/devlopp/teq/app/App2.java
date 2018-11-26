package com.devlopp.teq.app;

import com.devlopp.teq.client.Client;
import com.devlopp.teq.course.Course;
import com.devlopp.teq.databasehelper.DatabaseDriverHelper;
import com.devlopp.teq.databasehelper.DatabaseInsertHelper;
import com.devlopp.teq.databasehelper.DatabaseSelectHelper;
import com.devlopp.teq.parser.AssessmentParser;
import com.devlopp.teq.parser.ClientProfileParser;
import com.devlopp.teq.parser.CommunityConnectionsParser;
import com.devlopp.teq.parser.CourseEnrollParser;
import com.devlopp.teq.parser.CourseExitParser;
import com.devlopp.teq.parser.CourseSetupParser;
import com.devlopp.teq.parser.EmploymentParser;
import com.devlopp.teq.parser.OrientationParser;
import com.devlopp.teq.parser.ServiceParser;
import com.devlopp.teq.parser.TemplateParser;
import com.devlopp.teq.service.assessment.Assessment;
import com.devlopp.teq.service.commconn.CommunityConnections;
import com.devlopp.teq.service.courseenroll.CourseEnroll;
import com.devlopp.teq.service.courseexit.CourseExit;
import com.devlopp.teq.service.employment.Employment;
import com.devlopp.teq.service.orientation.Orientation;

public class App2 {

    public static void main(String[] args) {
        System.out.println("TEQ LIP data platform");

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
            Client comm = (Client) record;
            DatabaseInsertHelper.insertClient((Client) record);
        }

        parser = new AssessmentParser();
        parser.read(filePath, 3);
        for (Object record : parser.parse()) {
            Assessment comm = (Assessment) record;
            // DatabaseInsertHelper.insertAssessment((Assessment) record);
        }

        parser = new CommunityConnectionsParser();
        parser.read(filePath, 4);
        for (Object record : parser.parse()) {
            CommunityConnections comm = (CommunityConnections) record;
            DatabaseInsertHelper.insertCommunityConnections((CommunityConnections) record);
        }

        parser = new OrientationParser();
        parser.read(filePath, 5);
        for (Object record : parser.parse()) {
            Orientation comm = (Orientation) record;
            DatabaseInsertHelper.insertOrientation((Orientation) record);
        }

        parser = new EmploymentParser();
        parser.read(filePath, 6);
        for (Object record : parser.parse()) {
            Employment comm = (Employment) record;
            DatabaseInsertHelper.insertEmployment((Employment) record);
        }

        parser = new CourseEnrollParser();
        parser.read(filePath, 7);
        for (Object record : parser.parse()) {
            CourseEnroll comm = (CourseEnroll) record;
            DatabaseInsertHelper.insertCourseEnroll((CourseEnroll) record);
        }

        parser = new CourseSetupParser();
        parser.read(filePath, 8);
        for (Object record : parser.parse()) {
            Course comm = (Course) record;
            DatabaseInsertHelper.insertCourse((Course) record);
        }

        parser = new CourseExitParser();
        parser.read(filePath, 9);
        for (Object record : parser.parse()) {
            CourseExit comm = (CourseExit) record;
            DatabaseInsertHelper.insertCourseExit((CourseExit) record);
        }

    }

}
