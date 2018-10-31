package com.devlopp.teq.app;

import java.util.List;

import com.devlopp.teq.client.Client;
import com.devlopp.teq.databasehelper.DatabaseDriverHelper;
import com.devlopp.teq.databasehelper.DatabaseInsertHelper;
import com.devlopp.teq.databasehelper.DatabaseSelectHelper;

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

        List<Client> clients = ExcelDriver.readClientProfile(filePath);
        for (Client client : clients) {
            // insert client next
            int clientId = DatabaseInsertHelper.insertClient(client);
            System.out.println("New client ID at: " + clientId);
            
            // get client and its address from database
            client = DatabaseSelectHelper.getClient(clientId);
            System.out.println("Retrieved results from database:");
            System.out.println(client);
            System.out.println(client.getAddress());
            
        }
        
    }
    
}
