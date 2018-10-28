package com.teq.app;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.teq.address.Address;
import com.teq.client.Client;
import com.teq.databasehelper.DatabaseDriverHelper;
import com.teq.databasehelper.DatabaseInsertHelper;
import com.teq.databasehelper.DatabaseSelectHelper;

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
            
            // insert address first
            Address address = client.getAddress();
            int addressId = DatabaseInsertHelper.insertAddress(address);
            System.out.println("New address ID at: " + addressId);
            
            // insert client next
            client.setAddressId(addressId);
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
