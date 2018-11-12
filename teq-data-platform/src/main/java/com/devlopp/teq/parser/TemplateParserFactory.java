package com.devlopp.teq.parser;

public class TemplateParserFactory {
    
    public static TemplateParser getParser(String templateType) {
        TemplateParser parser = null;
        templateType = templateType.toUpperCase();
        if (templateType.contains("CLIENT")) {
            parser = new ClientProfileParser();
        } else if (templateType.contains("ASSESS")) {
            parser = new AssessmentParser();
        } else if (templateType.contains("COMMUNITY")) {
            parser = new CommunityConnectionsParser();
        } else if (templateType.contains("ORIENTATION")) {
            parser = new OrientationParser();
        } else if (templateType.contains("EMPLOYMENT")) {
            parser = new EmploymentParser();
        } else if (templateType.contains("SETUP")) {
            parser = new CourseSetupParser();
        } else if (templateType.contains("ENROL")) {
            parser = new CourseEnrollParser();
        } else if (templateType.contains("EXIT")) {
            parser = new CourseExitParser();
        } else {
            System.out.println("Error: invalid template type");
        }
        return parser;
    }
    
}
