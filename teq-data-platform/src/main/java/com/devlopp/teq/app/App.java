package com.devlopp.teq.app;

import com.devlopp.teq.databasehelper.DatabaseDriverHelper;
import com.devlopp.teq.parser.CommunityConnectionsParser;
import com.devlopp.teq.parser.TemplateParser;
import com.devlopp.teq.reporting.generateReport;
import com.devlopp.teq.service.commconn.CommunityConnections;

public class App {
   
    public static void main(String[] args) {
        System.out.println("TEQ LIP data platform");
        
        boolean dbExists = DatabaseDriverHelper.databaseExists();
        System.out.println("Database exists: " + dbExists);
        if (!dbExists) {
            DatabaseDriverHelper.initializeDatabase();
        }
        
/*        String filePath = "src/main/java/com/devlopp/teq/excel/iCARE_Templates.xlsx";
        TemplateParser parser = new CommunityConnectionsParser();
        parser.read(filePath, 4);
        for (Object record : parser.parse()) {
            CommunityConnections comm = (CommunityConnections) record;
            System.out.println(comm.getClientId());
            System.out.println(comm.getReasonForLeave());
            System.out.println(comm.getEssentialSkills());
            System.out.println(comm.getTargetGroups());
        }*/
        generateReport.generateTrendsInService("CommunityConnections", 110, 120);
    }
    
}
