package com.devlopp.teq.test;

import java.util.List;

import com.devlopp.teq.address.Address;
import com.devlopp.teq.app.ExcelDriver;
import com.devlopp.teq.client.Client;
import com.devlopp.teq.databasehelper.DatabaseDriverHelper;
import com.devlopp.teq.databasehelper.DatabaseInsertHelper;
import com.devlopp.teq.databasehelper.DatabaseSelectHelper;

public class U1acceptance {
	
	public void u1_acceptance_test() {
	   
	   String filePath = "client_profile.xlsx";
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
