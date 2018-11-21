package com.devlopp.teq.app;

import java.util.List;

import com.devlopp.teq.databasehelper.DatabaseSelectHelper;


public class generateReport {
	
	public static void main(String[] args) {
		List<Integer> ids = DatabaseSelectHelper.getAllClientIds();
		System.out.println(ids.toString());
		for (Integer id : ids) {
			System.out.println(DatabaseSelectHelper.getClientServices(id));
		}
		
		
		
	}
}
