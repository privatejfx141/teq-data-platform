package com.devlopp.teq.app;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.devlopp.teq.address.Address;
import com.devlopp.teq.address.AddressBuilder;
import com.devlopp.teq.address.IAddressBuilder;
import com.devlopp.teq.client.Client;
import com.devlopp.teq.client.ClientBuilder;
import com.devlopp.teq.client.IClientBuilder;
import com.devlopp.teq.excel.ExcelReader;

public class ExcelDriver {
    
    public static String fixDate(String dateString, String dateFormat) {
        SimpleDateFormat inputDateFormat = new SimpleDateFormat(dateFormat);
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = inputDateFormat.parse(dateString);
            return outputDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String fixDirection(String direction) {
        String[] split = direction.split(" ");
        return split[0];
    }
    
    public static boolean parseYesNo(String response) {
        response = response.toLowerCase();
        return response.contains("ye") || response.equals("y");
    }

    public static List<Client> readClientProfile(String filePath, int sheetNumber) {
        List<Client> clients = new ArrayList<>();
        try {
            ArrayList<ArrayList<String>> clientProfileData = ExcelReader.parseForDB(sheetNumber, filePath);
            for (ArrayList<String> clientData : clientProfileData.subList(1, clientProfileData.size())) {
                // build the address object
                IAddressBuilder addressBuilder = new AddressBuilder();
                Address address = addressBuilder
                        .setPostalCode(clientData.get(14))
                        .setUnitNumber(Integer.parseInt(clientData.get(11)))
                        .setStreetNumber(Integer.parseInt(clientData.get(7)))
                        .setStreetName(clientData.get(8))
                        .setStreetType(clientData.get(9))
                        .setStreetDirection(fixDirection(clientData.get(10)))
                        .setCity(clientData.get(12))
                        .setProvince(clientData.get(13))
                        .create();
                // build the client object
                IClientBuilder clientBuilder = new ClientBuilder();
                Client client = clientBuilder.setId(Integer.parseInt(clientData.get(2)))
                        .setIdType(1)
                        .setBirthDate(fixDate(clientData.get(3), "yyyy-MM-dd"))
                        .setPhoneNumber(clientData.get(4))
                        .setEmailAddress(clientData.get(6))
                        .setAddress(address)
                        .setLanguage(clientData.get(15))
                        .setConsent(parseYesNo(clientData.get(16)))
                        .create();
                // add client object to list to return
                clients.add(client);
            }
        } catch (InvalidFormatException | IOException e) {
            e.printStackTrace();
        }
        return clients;
    }
}
