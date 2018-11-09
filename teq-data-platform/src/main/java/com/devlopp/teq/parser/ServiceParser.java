package com.devlopp.teq.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.devlopp.teq.excel.ExcelReader;
import com.devlopp.teq.service.IServiceBuilder;
import com.devlopp.teq.service.ServiceBuilder;

public abstract class ServiceParser implements TemplateParser {
    protected HashMap<String, ArrayList<String>> allData;
    protected int numRecords = 0;

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

    protected IServiceBuilder parseServiceData(IServiceBuilder builder, int recordIndex) {
        // client id
        String field = "UNIQUE IDENTIFIER VALUE";
        if (allData.containsKey(field))
            builder.setClientId(FieldParser.parseInt(allData.get(field).get(recordIndex)));
        // postal code
        field = "POSTAL CODE WHERE THE SERVICE WAS RECEIVED";
        if (allData.containsKey(field))
            builder.setPostalCode(allData.get(field).get(recordIndex));
        // language
        field = "LANGUAGE OF SERVICE";
        if (allData.containsKey(field))
            builder.setLanguage(allData.get(field).get(recordIndex));
        // organization type
        field = "TYPE OF INSTITUTION/ORGANIZATION WHERE CLIENT RECEIVED SERVICES";
        if (allData.containsKey(field))
            builder.setOrganizationType(allData.get(field).get(recordIndex));
        // referred by
        field = "REFERRED BY";
        if (allData.containsKey(field))
            builder.setReferredBy(allData.get(field).get(recordIndex));
        // update reason
        field = "REASON FOR UPDATE";
        if (allData.containsKey(field))
            builder.setUpdateReason(allData.get(field).get(recordIndex));
        // return populated builder
        return builder;
    }

    protected List<String> parseSupportServices(int recordIndex) {
        List<String> supportServices = new ArrayList<>();
        try {
            if (allData.get("CARE FOR NEWCOMER CHILDREN").get(recordIndex).equals("Yes"))
                supportServices.add("Care for Newcomer Children");
            if (allData.get("TRANSPORTATION").get(recordIndex).equals("Yes"))
                supportServices.add("Transportation");
            if (allData.get("PROVISIONS FOR DISABILITIES").get(recordIndex).equals("Yes"))
                supportServices.add("Provisions for Disabilities");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return supportServices;
    }

    protected List<String> parseTargetGroups(int recordIndex) {
        List<String> targetGroups = new ArrayList<>();
        try {
            String field = "TARGET GROUP: CHILDREN (0-14 YRS)";
            if (allData.get(field).get(recordIndex).equals("Yes"))
                targetGroups.add("Children (0-14 yrs)");
            field = "TARGET GROUP: YOUTH (15-24 YRS)";
            if (allData.get(field).get(recordIndex).equals("Yes"))
                targetGroups.add("Youth (15-24 yrs)");
            field = "TARGET GROUP: SENIOR";
            if (allData.get(field).get(recordIndex).equals("Yes"))
                targetGroups.add("Senior");
            field = "TARGET GROUP: GENDER-SPECIFIC";
            if (allData.get(field).get(recordIndex).equals("Yes"))
                targetGroups.add("Gender-specific");
            field = "TARGET GROUP: REFUGEES";
            if (allData.get(field).get(recordIndex).equals("Yes"))
                targetGroups.add("Refugees");
            field = "TARGET GROUP: OFFICIAL LANGUAGE MINORITIES";
            if (allData.get(field).get(recordIndex).equals("Yes"))
                targetGroups.add("Official language minorities");
            field = "TARGET GROUP: ETHNIC/CULTURAL/LINGUISTIC GROUP";
            if (allData.get(field).get(recordIndex).equals("Yes"))
                targetGroups.add("Ethnic/cultural/linguistic group");
            field = "TARGET GROUP: DEAF OR HARD OF HEARING";
            if (allData.get(field).get(recordIndex).equals("Yes"))
                targetGroups.add("Deaf or Hard of Hearing");
            field = "TARGET GROUP: BLIND OR PARTIALLY SIGHTED";
            if (allData.get(field).get(recordIndex).equals("Yes"))
                targetGroups.add("Blind or Partially Sighted");
            field = "TARGET GROUP: CLIENTS WITH OTHER IMPAIRMENTS (PHYSICAL, MENTAL)";
            if (allData.get(field).get(recordIndex).equals("Yes"))
                targetGroups.add("Clients with other impairments (physical, mental)");
            field = "TARGET GROUP: LESBIAN, GAY, BISEXUAL, TRANSGENDER, QUEER (LGBTQ)";
            if (allData.get(field).get(recordIndex).equals("Yes"))
                targetGroups.add("Lesbian, Gay, Bisexual, Transgender, Queer (LGBTQ)");
            field = "TARGET GROUP: FAMILIES/PARENTS";
            if (allData.get(field).get(recordIndex).equals("Yes"))
                targetGroups.add("Families/Parents");
            field = "TARGET GROUP: CLIENTS WITH INTERNATIONAL TRAINING IN A REGULATED PROFESSION";
            if (allData.get(field).get(recordIndex).equals("Yes"))
                targetGroups.add("Clients with international training in a regulated profession");
            field = "TARGET GROUP: CLIENTS WITH INTERNATIONAL TRAINING IN A REGULATED TRADE";
            if (allData.get(field).get(recordIndex).equals("Yes"))
                targetGroups.add("Clients with international training in a regulated trade");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return targetGroups;
    }
}
