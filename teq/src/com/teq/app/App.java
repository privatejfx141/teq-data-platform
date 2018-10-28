package com.teq.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.teq.databasehelper.DatabaseDriverHelper;
import com.teq.inputCheck.errorCheck;

public class App {
    
    public static void main(String[] args) {
        System.out.println("TEQ LIP data platform");
        
        boolean dbExists = DatabaseDriverHelper.databaseExists();
        System.out.println("Database exists: " + dbExists);
        if (!dbExists) {
            DatabaseDriverHelper.initializeDatabase();
        }
        
        String filePath = "icare.xlsx";
        try {
            ArrayList<ArrayList<String>> values = errorCheck.parseForDB(filePath);
            for (ArrayList<String> list : values) {
                String repr = Arrays.toString(list.toArray());
                System.out.println(repr);
            }
        } catch (InvalidFormatException | IOException e) {
            e.printStackTrace();
        }
        
    }
    
}
