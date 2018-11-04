package com.devlopp.teq.app;

import java.util.Arrays;
import java.util.List;

import com.devlopp.teq.client.Client;
import com.devlopp.teq.databasehelper.DatabaseDriverHelper;
import com.devlopp.teq.databasehelper.DatabaseInsertHelper;
import com.devlopp.teq.databasehelper.DatabaseSelectHelper;
import com.devlopp.teq.service.assessment.Assessment;
import com.devlopp.teq.service.assessment.AssessmentBuilder;
import com.devlopp.teq.service.assessment.IAssessmentBuilder;

public class App {
    
    // temporary value for testing
    public static String filePath = "client_profile.xlsx";
    
    public static void main(String[] args) {
        System.out.println("TEQ LIP data platform");
        
        boolean dbExists = DatabaseDriverHelper.databaseExists();
        System.out.println("Database exists: " + dbExists);
        if (!dbExists) {
            DatabaseDriverHelper.initializeDatabase();
        }

        List<Client> clients = ExcelDriver.readClientProfile(filePath, 0);
        for (Client client : clients) {
            // insert client next
            int clientId = DatabaseInsertHelper.insertClient(client);
            System.out.println("New client ID at: " + clientId);
            
            // get client and its address from database
            client = DatabaseSelectHelper.getClient(clientId);
            System.out.println("Retrieved results from database:");
            System.out.println(client);
            System.out.println(client.getAddress());
            
            // 
            IAssessmentBuilder assessBuilder = new AssessmentBuilder();
            assessBuilder = (IAssessmentBuilder) assessBuilder
                    .setId(1)
                    .setClientId(clientId)
                    .setLanguage("English")
                    .setPostalCode("M4H2A5")
                    .setReferredBy("Method of referral")
                    .setOrganizationType("Organization type")
                    .setUpdateReason("Reason for update")
                    .setServiceType("A")
                    .setEssentialSkills(Arrays.asList("Computer skills", "Numeracy"))
                    .setSupportServices(Arrays.asList("Transportation, Translation"))
                    .setTargetGroups(Arrays.asList("Senior", "Refugees"));
            Assessment assessment = assessBuilder
                    .setStartDate("2017-01-01")
                    .setLanguageGoal("Language skill goal")
                    .setOtherGoal("Other skill goal")
                    .setIntendsCitizenship(true)
                    .setReqSupportServices(true)
                    .setPlanComplete(true)
                    .setEndDate("2018-01-01")
                    .setFindEmployment("Time frame", "Min experience", "Skill level", "Wants to intent")
                    .create();
            assessment.addIncrease("Social networks", true);
            assessment.addNonIRCCService("Financial");
            
            int serviceId = DatabaseInsertHelper.insertAssessment(assessment);
            System.out.println("New service ID at: " + serviceId);
            
            assessment = DatabaseSelectHelper.getAssessment(serviceId);
            System.out.println(assessment);

        }
        
    }
    
}
