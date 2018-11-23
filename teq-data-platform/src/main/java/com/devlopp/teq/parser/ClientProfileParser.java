package com.devlopp.teq.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.devlopp.teq.address.Address;
import com.devlopp.teq.address.AddressBuilder;
import com.devlopp.teq.client.Client;
import com.devlopp.teq.client.ClientBuilder;
import com.devlopp.teq.databasehelper.DatabaseSelectHelper;
import com.devlopp.teq.excel.ExcelReader;

public class ClientProfileParser implements TemplateParser {

    private HashMap<String, ArrayList<String>> allData;
    private int numRecords = 0;

    public ClientProfileParser() {
        allData = new HashMap<>();
        allData.put("PROCESSING DETAILS", new ArrayList<String>());
        allData.put("UNIQUE IDENTIFIER", new ArrayList<String>());
        allData.put("UNIQUE IDENTIFIER VALUE", new ArrayList<String>());
        allData.put("DATE OF BIRTH (YYYY-MM-DD)", new ArrayList<String>());
        allData.put("PHONE NUMBER", new ArrayList<String>());
        allData.put("DOES THE CLIENT HAVE AN EMAIL ADDRESS", new ArrayList<String>());
        allData.put("EMAIL ADDRESS", new ArrayList<String>());
        allData.put("STREET NUMBER", new ArrayList<String>());
        allData.put("STREET NAME", new ArrayList<String>());
        allData.put("STREET TYPE", new ArrayList<String>());
        allData.put("STREET DIRECTION", new ArrayList<String>());
        allData.put("UNIT/SUITE/APT", new ArrayList<String>());
        allData.put("CITY", new ArrayList<String>());
        allData.put("PROVINCE", new ArrayList<String>());
        allData.put("POSTAL CODE", new ArrayList<String>());
        allData.put("OFFICIAL LANGUAGE OF PREFERENCE", new ArrayList<String>());
        allData.put("CONSENT FOR FUTURE RESEARCH/CONSULTATION", new ArrayList<String>());
    }

    @Override
    public void read(String filePath, int sheetNumber) {
        try {
            ArrayList<ArrayList<String>> data = ExcelReader.readExcelFile(filePath, sheetNumber);
            ArrayList<String> header = data.get(0);
            for (ArrayList<String> recordData : data.subList(1, data.size())) {
                for (int i = 0; i < recordData.size(); i++) {
                    String field = header.get(i).trim().toUpperCase();
                    String value = recordData.get(i).trim();
                    allData.get(field).add(value);
                }
            }
            numRecords = data.size() - 1;
        } catch (InvalidFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Object> parse() {
        List<Object> clients = new ArrayList<>();
        for (int i = 0; i < numRecords; i++) {
            // build address
            Address address = new AddressBuilder() //
                    .setPostalCode(FieldParser.getFieldString(allData, "POSTAL CODE", i)) //
                    .setUnitNumber(FieldParser.getFieldInt(allData, "UNIT/SUITE/APT", i)) //
                    .setStreetNumber(FieldParser.getFieldInt(allData, "STREET NUMBER", i)) //
                    .setStreetDirection(FieldParser.getFieldString(allData, "STREET DIRECTION", i)) //
                    .setStreetName(FieldParser.getFieldString(allData, "STREET NAME", i)) //
                    .setStreetType(FieldParser.getFieldString(allData, "STREET TYPE", i)) //
                    .setCity(FieldParser.getFieldString(allData, "CITY", i)) //
                    .setProvince(FieldParser.getFieldString(allData, "PROVINCE", i)) //
                    .create(); //
            // get client ID type ID
            String clientIdType = allData.get("UNIQUE IDENTIFIER").get(i);
            int clientIdTypeId = DatabaseSelectHelper.getClientIDType(clientIdType);
            // build client
            Client client = new ClientBuilder() //
                    .setId(FieldParser.getFieldInt(allData, "UNIQUE IDENTIFIER VALUE", i)) //
                    .setIdType(clientIdTypeId) //
                    .setBirthDate(FieldParser.getFieldString(allData, "DATE OF BIRTH (YYYY-MM-DD)", i)) //
                    .setPhoneNumber(FieldParser.getFieldString(allData, "PHONE NUMBER", i)) //
                    .setEmailAddress(FieldParser.getFieldString(allData, "EMAIL ADDRESS", i)) //
                    .setAddress(address) //
                    .setLanguage(FieldParser.getFieldString(allData, "OFFICIAL LANGUAGE OF PREFERENCE", i)) //
                    .setConsent(FieldParser.getFieldBoolean(allData, "CONSENT FOR FUTURE RESEARCH/CONSULTATION", i)) //
                    .create(); //
            clients.add(client);
        }
        return clients;
    }

}
