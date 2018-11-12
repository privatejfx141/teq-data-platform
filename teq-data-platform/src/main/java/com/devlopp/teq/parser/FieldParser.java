package com.devlopp.teq.parser;

import java.util.ArrayList;
import java.util.HashMap;

public class FieldParser {
    public static int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static float parseFloat(String value) {
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static String getFieldString(HashMap<String, ArrayList<String>> readData, String attribute,
            int recordIndex) {
        try {
            return readData.get(attribute).get(recordIndex);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            return "";
        }
    }

    public static int getFieldInt(HashMap<String, ArrayList<String>> readData, String attribute, int recordIndex) {
        try {
            return parseInt(readData.get(attribute).get(recordIndex));
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            return -1;
        }
    }

    public static float getFieldFloat(HashMap<String, ArrayList<String>> readData, String attribute, int recordIndex) {
        try {
            return parseFloat(readData.get(attribute).get(recordIndex));
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            return -1;
        }
    }

    public static boolean getFieldBoolean(HashMap<String, ArrayList<String>> readData, String attribute,
            int recordIndex) {
        if (readData.containsKey(attribute)) {
            String value = readData.get(attribute).get(recordIndex);
            return value.equalsIgnoreCase("Yes");
        }
        return false;
    }
}
