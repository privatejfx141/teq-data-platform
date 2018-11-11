package com.devlopp.teq.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.devlopp.teq.service.orientation.IOrientationBuilder;
import com.devlopp.teq.service.orientation.OrientationBuilder;

public class OrientationParser extends ServiceParser {
    
    public OrientationParser() {
        allData = new HashMap<>();
        allData.put("PROCESSING DETAILS", new ArrayList<>());
        allData.put("UPDATE RECORD ID", new ArrayList<>());
        allData.put("UNIQUE IDENTIFIER", new ArrayList<>());
        allData.put("UNIQUE IDENTIFIER VALUE", new ArrayList<>());
        allData.put("DATE OF BIRTH (YYYY-MM-DD)", new ArrayList<>());
        allData.put("POSTAL CODE WHERE THE SERVICE WAS RECEIVED", new ArrayList<>());
        allData.put("START DATE OF SERVICE (YYYY-MM-DD)", new ArrayList<>());
        allData.put("LANGUAGE OF SERVICE", new ArrayList<>());
        allData.put("OFFICIAL LANGUAGE OF PREFERENCE", new ArrayList<>());
        allData.put("TYPE OF INSTITUTION/ORGANIZATION WHERE CLIENT RECEIVED SERVICES", new ArrayList<>());
        allData.put("REFERRED BY", new ArrayList<>());
        allData.put("SERVICES RECEIVED", new ArrayList<>());
        allData.put("TOTAL LENGTH OF ORIENTATION", new ArrayList<>());
        allData.put("TOTAL LENGTH OF ORIENTATION: HOURS", new ArrayList<>());
        allData.put("TOTAL LENGTH OF ORIENTATION: MINUTES", new ArrayList<>());
        allData.put("NUMBER OF CLIENTS IN GROUP", new ArrayList<>());
        allData.put("DIRECTED AT A SPECIFIC TARGET GROUP", new ArrayList<>());
        allData.put("TARGET GROUP: CHILDREN (0-14 YRS)", new ArrayList<>());
        allData.put("TARGET GROUP: YOUTH (15-24 YRS)", new ArrayList<>());
        allData.put("TARGET GROUP: SENIORS", new ArrayList<>());
        allData.put("TARGET GROUP: GENDER-SPECIFIC", new ArrayList<>());
        allData.put("TARGET GROUP: REFUGEES", new ArrayList<>());
        allData.put("TARGET GROUP: ETHNIC/CULTURAL/LINGUISTIC GROUP", new ArrayList<>());
        allData.put("TARGET GROUP: DEAF OR HARD OF HEARING", new ArrayList<>());
        allData.put("TARGET GROUP: BLIND OR PARTIALLY SIGHTED", new ArrayList<>());
        allData.put("TARGET GROUP: LESBIAN, GAY, BISEXUAL, TRANSGENDER, QUEER (LGBTQ)", new ArrayList<>());
        allData.put("TARGET GROUP: FAMILIES/PARENTS", new ArrayList<>());
        allData.put("TARGET GROUP: CLIENTS WITH OTHER IMPAIRMENTS (PHYSICAL, MENTAL)", new ArrayList<>());
        allData.put("TARGET GROUP: CLIENTS WITH INTERNATIONAL TRAINING IN A REGULATED PROFESSION", new ArrayList<>());
        allData.put("TARGET GROUP: CLIENTS WITH INTERNATIONAL TRAINING IN A REGULATED TRADE", new ArrayList<>());
        allData.put("TARGET GROUP: OFFICIAL LANGUAGE MINORITIES", new ArrayList<>());
        allData.put("OVERVIEW OF CANADA", new ArrayList<>());
        allData.put("OVERVIEW OF CANADA REFERRALS", new ArrayList<>());
        allData.put("SOURCES OF INFORMATION", new ArrayList<>());
        allData.put("SOURCES OF INFORMATION REFERRALS", new ArrayList<>());
        allData.put("RIGHTS AND FREEDOMS", new ArrayList<>());
        allData.put("RIGHTS AND FREEDOMS REFERRALS", new ArrayList<>());
        allData.put("CANADIAN LAW AND JUSTICE", new ArrayList<>());
        allData.put("CANADIAN LAW AND JUSTICE REFERRALS", new ArrayList<>());
        allData.put("IMPORTANT DOCUMENTS", new ArrayList<>());
        allData.put("IMPORTANT DOCUMENTS REFERRALS", new ArrayList<>());
        allData.put("IMPROVING ENGLISH OR FRENCH", new ArrayList<>());
        allData.put("IMPROVING ENGLISH OR FRENCH REFERRALS", new ArrayList<>());
        allData.put("EMPLOYMENT AND INCOME", new ArrayList<>());
        allData.put("EMPLOYMENT AND INCOME REFERRALS", new ArrayList<>());
        allData.put("EDUCATION", new ArrayList<>());
        allData.put("EDUCATION REFERRALS", new ArrayList<>());
        allData.put("HOUSING", new ArrayList<>());
        allData.put("HOUSING REFERRALS", new ArrayList<>());
        allData.put("HEALTH", new ArrayList<>());
        allData.put("HEALTH REFERRALS", new ArrayList<>());
        allData.put("MONEY AND FINANCES", new ArrayList<>());
        allData.put("MONEY AND FINANCES REFERRALS", new ArrayList<>());
        allData.put("TRANSPORTATION", new ArrayList<>());
        allData.put("TRANSPORTATION REFERRALS", new ArrayList<>());
        allData.put("COMMUNICATIONS AND MEDIA", new ArrayList<>());
        allData.put("COMMUNICATIONS AND MEDIA REFERRALS", new ArrayList<>());
        allData.put("COMMUNITY ENGAGEMENT", new ArrayList<>());
        allData.put("COMMUNITY ENGAGEMENT REFERRALS", new ArrayList<>());
        allData.put("BECOMING A CANADIAN CITIZEN", new ArrayList<>());
        allData.put("BECOMING A CANADIAN CITIZEN REFERRALS", new ArrayList<>());
        allData.put("INTERPERSONAL CONFLICT", new ArrayList<>());
        allData.put("INTERPERSONAL CONFLICT REFERRALS", new ArrayList<>());
        allData.put("WAS ESSENTIAL SKILLS AND APTITUDE TRAINING RECEIVED AS PART OF THIS SERVICE?", new ArrayList<>());
        allData.put("COMPUTER SKILLS", new ArrayList<>());
        allData.put("DOCUMENT USE", new ArrayList<>());
        allData.put("INTERPERSONAL SKILLS AND WORKPLACE CULTURE", new ArrayList<>());
        allData.put("LEADERSHIP TRAINING", new ArrayList<>());
        allData.put("NUMERACY", new ArrayList<>());
        allData.put("WAS LIFE SKILLS OR RESPONSIBILITIES OF CITIZENSHIP INFORMATION RECEIVED AS PART OF THIS SERVICE?", new ArrayList<>());
        allData.put("LIFE SKILLS", new ArrayList<>());
        allData.put("RIGHTS AND RESPONSIBILITIES OF CITIZENSHIP (BASED ON DISCOVER CANADA)", new ArrayList<>());
        allData.put("SUPPORT SERVICES RECEIVED", new ArrayList<>());
        allData.put("CARE FOR NEWCOMER CHILDREN", new ArrayList<>());
        allData.put("CHILD 1: AGE", new ArrayList<>());
        allData.put("CHILD 1: TYPE OF CARE", new ArrayList<>());
        allData.put("CHILD 2: AGE", new ArrayList<>());
        allData.put("CHILD 2: TYPE OF CARE", new ArrayList<>());
        allData.put("CHILD 3: AGE", new ArrayList<>());
        allData.put("CHILD 3: TYPE OF CARE", new ArrayList<>());
        allData.put("CHILD 4: AGE", new ArrayList<>());
        allData.put("CHILD 4: TYPE OF CARE", new ArrayList<>());
        allData.put("CHILD 5: AGE", new ArrayList<>());
        allData.put("CHILD 5: TYPE OF CARE", new ArrayList<>());
        allData.put("TRANSPORTATION", new ArrayList<>());
        allData.put("PROVISIONS FOR DISABILITIES", new ArrayList<>());
        allData.put("TRANSLATION", new ArrayList<>());
        allData.put("BETWEEN", new ArrayList<>());
        allData.put("AND", new ArrayList<>());
        allData.put("INTERPRETATION", new ArrayList<>());
        allData.put("BETWEEN", new ArrayList<>());
        allData.put("AND", new ArrayList<>());
        allData.put("CRISIS COUNSELLING", new ArrayList<>());
        allData.put("END DATE OF SERVICE (YYYY-MM-DD)", new ArrayList<>());
        allData.put("REASON FOR UPDATE", new ArrayList<>());
    }
    
    @Override
    public List<Object> parse() {
        List<Object> records = new ArrayList<>();
        for (int i = 0; i < numRecords; i++) {
            IOrientationBuilder builder = (IOrientationBuilder) parseServiceData(new OrientationBuilder(), i);
            // get yes-no responses
            builder.setTargetGroups(parseTargetGroups(i))
                .setEssentialSkills(parseEssentialSkills(i))
                .setChildCares(parseChildCares(i));
            // build the orientation service object
            builder.setServiceReceived(FieldParser.getFieldString(allData, "SERVICES RECEIVED", i))
                .setTotalLength(FieldParser.getFieldString(allData, "TOTAL LENGTH OF ORIENTATION", i))
                .setLengthHours(FieldParser.getFieldInt(allData, "TOTAL LENGTH OF ORIENTATION: HOURS", i))
                .setLengthMinutes(FieldParser.getFieldInt(allData, "TOTAL LENGTH OF ORIENTATION: MINUTES", i))
                .setNumberOfClients(FieldParser.getFieldInt(allData, "NUMBER OF CLIENTS IN GROUP", i))
                .setEndDate(FieldParser.getFieldString(allData, "END DATE OF SERVICE (YYYY-MM-DD)", i));
            Object record = builder.create();
            records.add(record);
        }
        return records;
    }
}
