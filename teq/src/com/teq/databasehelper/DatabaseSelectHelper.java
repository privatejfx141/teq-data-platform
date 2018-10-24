package com.teq.databasehelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.teq.database.DatabaseDriver;
import com.teq.database.DatabaseSelector;

public class DatabaseSelectHelper extends DatabaseSelector {
    
    private static List<String> selectAllTypes(String tableName) {
        List<String> list = new ArrayList<>();
        Connection connection = DatabaseDriver.connectToDatabase();
        try {
            ResultSet results = DatabaseSelector.selectAllTypes(connection, tableName);
            while (results.next()) {
                list.add(results.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    
    public static List<String> selectInterventionTypes() {
        return selectAllTypes("InterventionType");
    }
    
    public static List<String> selectEssentialSkills() {
        return selectAllTypes("EssentialSkill");
    }
    
    public static List<String> selectIncreases() {
        return selectAllTypes("Increase");
    }
    
    public static List<String> selectNonIRCCServices() {
        return selectAllTypes("NonIRCCService");
    }
    
    public static List<String> selectSchedules() {
        return selectAllTypes("Schedule");
    }
    
    public static List<String> selectServiceTypes() {
        return selectAllTypes("ServiceType");
    }
    
    public static List<String> selectSupportServices() {
        return selectAllTypes("SupportService");
    }
    
    public static List<String> selectTargetGroups() {
        return selectAllTypes("TargetGroup");
    }
    
    public static List<String> selectTopics() {
        return selectAllTypes("Topic");
    }
    
}
