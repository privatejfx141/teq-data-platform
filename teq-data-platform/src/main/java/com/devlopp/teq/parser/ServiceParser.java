package com.devlopp.teq.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.devlopp.teq.excel.ExcelReader;
import com.devlopp.teq.service.IServiceBuilder;
import com.devlopp.teq.service.NewcomerChildCare;
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
            builder.setClientId(FieldParser.getFieldInt(allData, field, recordIndex));
        // postal code
        field = "POSTAL CODE WHERE THE SERVICE WAS RECEIVED";
        if (allData.containsKey(field))
            builder.setPostalCode(FieldParser.getFieldString(allData, field, recordIndex));
        // language
        field = "LANGUAGE OF SERVICE";
        if (allData.containsKey(field))
            builder.setLanguage(FieldParser.getFieldString(allData, field, recordIndex));
        // organization type
        field = "TYPE OF INSTITUTION/ORGANIZATION WHERE CLIENT RECEIVED SERVICES";
        if (allData.containsKey(field))
            builder.setOrganizationType(FieldParser.getFieldString(allData, field, recordIndex));
        // referred by
        field = "REFERRED BY";
        if (allData.containsKey(field))
            builder.setReferredBy(FieldParser.getFieldString(allData, field, recordIndex));
        // update reason
        field = "REASON FOR UPDATE";
        if (allData.containsKey(field))
            builder.setUpdateReason(FieldParser.getFieldString(allData, field, recordIndex));
        // return populated builder
        return builder;
    }

    protected List<String> parseEssentialSkills(int recordIndex) {
        List<String> essentialSkills = new ArrayList<>();
        if (FieldParser.getFieldBoolean(allData, "COMPUTER SKILLS", recordIndex))
            essentialSkills.add("Computer Skills");
        if (FieldParser.getFieldBoolean(allData, "DOCUMENT USE", recordIndex))
            essentialSkills.add("Document Use");
        if (FieldParser.getFieldBoolean(allData, "INTERPERSONAL SKILLS AND WORKPLACE CULTURE", recordIndex))
            essentialSkills.add("Interpersonal Skills and Workplace Culture");
        if (FieldParser.getFieldBoolean(allData, "LEADERSHIP TRAINING", recordIndex))
            essentialSkills.add("Leadership Training");
        if (FieldParser.getFieldBoolean(allData, "LIFE SKILLS", recordIndex))
            essentialSkills.add("Life Skills");
        if (FieldParser.getFieldBoolean(allData, "NUMERACY", recordIndex))
            essentialSkills.add("Numeracy");
        return essentialSkills;
    }
    
    protected List<String> parseSupportServices(int recordIndex) {
        List<String> supportServices = new ArrayList<>();
        try {
            if (FieldParser.getFieldBoolean(allData, "CARE FOR NEWCOMER CHILDREN", recordIndex))
                supportServices.add("Care for Newcomer Children");
            if (FieldParser.getFieldBoolean(allData, "TRANSPORTATION", recordIndex))
                supportServices.add("Transportation");
            if (FieldParser.getFieldBoolean(allData, "PROVISIONS FOR DISABILITIES", recordIndex))
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
            if (FieldParser.getFieldBoolean(allData, field, recordIndex))
                targetGroups.add("Children (0-14 yrs)");
            field = "TARGET GROUP: YOUTH (15-24 YRS)";
            if (FieldParser.getFieldBoolean(allData, field, recordIndex))
                targetGroups.add("Youth (15-24 yrs)");
            field = "TARGET GROUP: SENIOR";
            if (FieldParser.getFieldBoolean(allData, field, recordIndex))
                targetGroups.add("Senior");
            field = "TARGET GROUP: SENIORS";
            if (FieldParser.getFieldBoolean(allData, field, recordIndex))
                targetGroups.add("Senior");
            field = "TARGET GROUP: GENDER-SPECIFIC";
            if (FieldParser.getFieldBoolean(allData, field, recordIndex))
                targetGroups.add("Gender-specific");
            field = "TARGET GROUP: REFUGEES";
            if (FieldParser.getFieldBoolean(allData, field, recordIndex))
                targetGroups.add("Refugees");
            field = "TARGET GROUP: OFFICIAL LANGUAGE MINORITIES";
            if (FieldParser.getFieldBoolean(allData, field, recordIndex))
                targetGroups.add("Official language minorities");
            field = "TARGET GROUP: ETHNIC/CULTURAL/LINGUISTIC GROUP";
            if (FieldParser.getFieldBoolean(allData, field, recordIndex))
                targetGroups.add("Ethnic/cultural/linguistic group");
            field = "TARGET GROUP: DEAF OR HARD OF HEARING";
            if (FieldParser.getFieldBoolean(allData, field, recordIndex))
                targetGroups.add("Deaf or Hard of Hearing");
            field = "TARGET GROUP: BLIND OR PARTIALLY SIGHTED";
            if (FieldParser.getFieldBoolean(allData, field, recordIndex))
                targetGroups.add("Blind or Partially Sighted");
            field = "TARGET GROUP: CLIENTS WITH OTHER IMPAIRMENTS (PHYSICAL, MENTAL)";
            if (FieldParser.getFieldBoolean(allData, field, recordIndex))
                targetGroups.add("Other impairments (physical, mental)");
            field = "TARGET GROUP: OTHER IMPAIRMENTS (PHYSICAL, MENTAL)";
            if (FieldParser.getFieldBoolean(allData, field, recordIndex))
                targetGroups.add("Other impairments (physical, mental)");
            field = "TARGET GROUP: LESBIAN, GAY, BISEXUAL, TRANSGENDER, QUEER (LGBTQ)";
            if (FieldParser.getFieldBoolean(allData, field, recordIndex))
                targetGroups.add("Lesbian, Gay, Bisexual, Transgender, Queer (LGBTQ)");
            field = "TARGET GROUP: FAMILIES/PARENTS";
            if (FieldParser.getFieldBoolean(allData, field, recordIndex))
                targetGroups.add("Families/Parents");
            field = "TARGET GROUP: CLIENTS WITH INTERNATIONAL TRAINING IN A REGULATED PROFESSION";
            if (FieldParser.getFieldBoolean(allData, field, recordIndex))
                targetGroups.add("Clients with international training in a regulated profession");
            field = "TARGET GROUP: CLIENTS WITH INTERNATIONAL TRAINING IN A REGULATED TRADE";
            if (FieldParser.getFieldBoolean(allData, field, recordIndex))
                targetGroups.add("Clients with international training in a regulated trade");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return targetGroups;
    }

    protected List<NewcomerChildCare> parseChildCares(int recordIndex) {
        List<NewcomerChildCare> childCares = new ArrayList<>();
        if (FieldParser.getFieldBoolean(allData, "CARE FOR NEWCOMER CHILDREN", recordIndex)) {
            for (int i = 1; i <= 5; i++) {
                String ageField = String.format("CHILD %d: AGE", i);
                String typeField = String.format("CHILD %d: TYPE OF CARE", i);
                String ageValue = FieldParser.getFieldString(allData, ageField, i);
                String typeValue = FieldParser.getFieldString(allData, typeField, i);
                if (ageValue.isEmpty() || typeValue.isEmpty())
                    break;
                childCares.add(new NewcomerChildCare(i, ageValue, typeValue));
            }
        }
        return childCares;
    }
}
