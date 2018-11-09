package com.devlopp.teq.app;

import com.devlopp.teq.course.Course;
import com.devlopp.teq.databasehelper.DatabaseDriverHelper;
import com.devlopp.teq.parser.CourseSetupParser;
import com.devlopp.teq.parser.TemplateParser;

public class App {
   
    public static void main(String[] args) {
        System.out.println("TEQ LIP data platform");
        
        boolean dbExists = DatabaseDriverHelper.databaseExists();
        System.out.println("Database exists: " + dbExists);
        if (!dbExists) {
            DatabaseDriverHelper.initializeDatabase();
        }
        
        String filePath = "iCARE_Templates.xlsx";
        TemplateParser parser = new CourseSetupParser();
        parser.read(filePath, 8);
        for (Object record : parser.parse()) {
            Course course = (Course) record;
            System.out.println(course.getCourseCode());
            System.out.println(course.getContact().getName());
        }
        
    }
    
}
