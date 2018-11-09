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
            ArrayList<ArrayList<String>> data = ExcelReader.parseForDB(sheetNumber, filePath);
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
            Address address = new AddressBuilder()
                    .setPostalCode(allData.get("POSTAL CODE").get(i))
                    .setUnitNumber(FieldParser.parseInt(allData.get("UNIT/SUITE/APT").get(i)))
                    .setStreetNumber(FieldParser.parseInt(allData.get("STREET NUMBER").get(i)))
                    .setStreetDirection(allData.get("STREET DIRECTION").get(i))
                    .setStreetName(allData.get("STREET NAME").get(i))
                    .setStreetType(allData.get("STREET TYPE").get(i))
                    .setCity(allData.get("CITY").get(i))
                    .setProvince(allData.get("PROVINCE").get(i))
                    .create();
            // build client
            Client client = new ClientBuilder()
                    .setId(FieldParser.parseInt(allData.get("UNIQUE IDENTIFIER VALUE").get(i)))
                    .setIdType(1) // TODO: temporary solution
                    .setBirthDate(allData.get("DATE OF BIRTH (YYYY-MM-DD)").get(i))
                    .setPhoneNumber(allData.get("PHONE NUMBER").get(i))
                    .setEmailAddress(allData.get("EMAIL ADDRESS").get(i))
                    .setAddress(address)
                    .setLanguage(allData.get("OFFICIAL LANGUAGE OF PREFERENCE").get(i))
                    .setConsent(allData.get("CONSENT FOR FUTURE RESEARCH/CONSULTATION").get(i).equals("Yes"))
                    .create();
            clients.add(client);
        }
        return clients;
    }
}
