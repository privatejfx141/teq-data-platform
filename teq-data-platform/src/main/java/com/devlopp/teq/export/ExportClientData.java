package com.devlopp.teq.export;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import com.devlopp.teq.address.Address;
import com.devlopp.teq.client.Client;

public class ExportClientData {

    private static final String CSV_SEPARATOR = ",";

    public static void writeToCSV(String filePath, List<Client> clients) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"));
            String[] header = new String[] { "Unique Identifier", "Date of Birth (YYYY-MM-DD)", "Phone Number",
                    "Does the Client Have an Email Address", "Email Address", "Street Number", "Street Name",
                    "Street Type", "Street Direction", "Unit/Suite/Apt", "City", "Province", "Postal Code",
                    "Official Language of Preference", "Consent for Future Research/Consultation" };
            StringBuffer headerLine = new StringBuffer();
            headerLine.append(String.join(CSV_SEPARATOR, header));
            bw.write(headerLine.toString());
            bw.newLine();
            for (Client client : clients) {
                Address address = client.getAddress();
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(client.getId() <= 0 ? "" : client.getId());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(client.getBirthDate());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(client.getPhoneNumber());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(client.getEmailAddress());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(address.getStreetNumber());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(address.getStreetName());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(address.getStreetType());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(address.getUnitNumber());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(address.getCity());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(address.getProvince());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(address.getPostalCode());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(client.getLanguage());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(client.getConsent() ? "Yes" : "No");
                oneLine.append(CSV_SEPARATOR);
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
