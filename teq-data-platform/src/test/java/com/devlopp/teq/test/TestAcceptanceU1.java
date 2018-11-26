package com.devlopp.teq.test;

import java.util.List;

import com.devlopp.teq.address.Address;
import com.devlopp.teq.app.ExcelDriver;
import com.devlopp.teq.client.Client;
import com.devlopp.teq.databasehelper.DatabaseDriverHelper;
import com.devlopp.teq.databasehelper.DatabaseInsertHelper;
import com.devlopp.teq.databasehelper.DatabaseSelectHelper;

public class TestAcceptanceU1 {

    public static void testAcceptanceU1() {
        String filePath = "client_profile.xlsx";
        System.out.println("TEQ LIP data platform");
        boolean dbExists = DatabaseDriverHelper.databaseExists();
        System.out.println("Database exists: " + dbExists);
        // check if DB already exists
        if (!dbExists) {
            DatabaseDriverHelper.initializeDatabase();
        }
        // read client profile
        List<Client> clients = ExcelDriver.readClientProfile(filePath, 0);
        for (Client client : clients) {
            // insert address first
            Address address = client.getAddress();
            int addressId = DatabaseInsertHelper.insertAddress(address);
            System.out.println("New address ID at: " + addressId);
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
