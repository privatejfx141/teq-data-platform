package com.devlopp.teq.parser;

import java.util.ArrayList;
import java.util.HashMap;

public class ExcelTable {
    private HashMap<String, ArrayList<String>> dataMap = new HashMap<>();
    private int numRecords = 0;

    public void addColumn(String columnName) {
        columnName = columnName.trim().toUpperCase();
        if (dataMap.containsKey(columnName)) {
            columnName = columnName + "2";
            addColumn(columnName);
        } else {
            dataMap.put(columnName, new ArrayList<>());
        }
    }

    public void addFieldValue(String columnName, String value) {
        columnName = columnName.trim().toUpperCase();
        dataMap.get(columnName).add(value);
    }

    public String getFieldString(String columnName, int recordIndex) {
        try {
            return dataMap.get(columnName).get(recordIndex);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            return null;
        }
    }

    public int getFieldInt(String columnName, int recordIndex) {
        try {
            return Integer.parseInt(dataMap.get(columnName).get(recordIndex));
        } catch (NullPointerException | NumberFormatException | IndexOutOfBoundsException e) {
            return -1;
        }
    }

    public float getFieldFloat(String columnName, int recordIndex) {
        try {
            return Float.parseFloat(dataMap.get(columnName).get(recordIndex));
        } catch (NullPointerException | NumberFormatException | IndexOutOfBoundsException e) {
            return -1;
        }
    }

    public boolean getFieldBoolean(String columnName, int recordIndex) {
        if (dataMap.containsKey(columnName)) {
            String value = dataMap.get(columnName).get(recordIndex);
            return value.toUpperCase().equals("YES");
        }
        return false;
    }

    public int getNumRecords() {
        return numRecords;
    }
}
