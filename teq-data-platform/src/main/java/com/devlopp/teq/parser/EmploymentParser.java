package com.devlopp.teq.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.devlopp.teq.service.employment.EmploymentBuilder;
import com.devlopp.teq.service.employment.IEmploymentBuilder;
import com.devlopp.teq.service.employment.LongTermInterventionBuilder;

public class EmploymentParser extends ServiceParser {

    public EmploymentParser() {
        allData = new HashMap<>();
        allData.put("PROCESSING DETAILS", new ArrayList<>());
        allData.put("UPDATE RECORD ID", new ArrayList<>());
        allData.put("UNIQUE IDENTIFIER", new ArrayList<>());
        allData.put("UNIQUE IDENTIFIER VALUE", new ArrayList<>());
        allData.put("DATE OF BIRTH (YYYY-MM-DD)", new ArrayList<>());
        allData.put("POSTAL CODE WHERE THE SERVICE WAS RECEIVED", new ArrayList<>());
        allData.put("REGISTRATION IN AN EMPLOYMENT INTERVENTION", new ArrayList<>());
        allData.put("A REFERRAL TO", new ArrayList<>());
        allData.put("LANGUAGE OF SERVICE", new ArrayList<>());
        allData.put("OFFICIAL LANGUAGE OF PREFERENCE", new ArrayList<>());
        allData.put("TYPE OF INSTITUTION/ORGANIZATION WHERE CLIENT RECEIVED SERVICES", new ArrayList<>());
        allData.put("REFERRED BY", new ArrayList<>());
        allData.put("REFERRAL DATE (YYYY-MM-DD)", new ArrayList<>());
        allData.put("EMPLOYMENT STATUS", new ArrayList<>());
        allData.put("EDUCATION STATUS", new ArrayList<>());
        allData.put("OCCUPATION IN CANADA", new ArrayList<>());
        allData.put("INTENDED OCCUPATION", new ArrayList<>());
        allData.put("INTERVENTION TYPE", new ArrayList<>());
        allData.put("LONG TERM INTERVENTION: INTERVENTION RECEIVED", new ArrayList<>());
        allData.put("LONG TERM INTERVENTION: STATUS OF INTERVENTION", new ArrayList<>());
        allData.put("LONG TERM INTERVENTION: REASON FOR LEAVING INTERVENTION", new ArrayList<>());
        allData.put("LONG TERM INTERVENTION: START DATE (YYYY-MM-DD)", new ArrayList<>());
        allData.put("LONG TERM INTERVENTION: END DATE (YYYY-MM-DD)", new ArrayList<>());
        allData.put("LONG TERM INTERVENTION: SIZE OF EMPLOYER", new ArrayList<>());
        allData.put("LONG TERM INTERVENTION: PLACEMENT WAS", new ArrayList<>());
        allData.put("LONG TERM INTERVENTION: HOURS PER WEEK", new ArrayList<>());
        allData.put("LONG TERM INTERVENTION: CLIENT MET MENTOR REGULARLY AT", new ArrayList<>());
        allData.put("LONG TERM INTERVENTION: AVERAGE HOURS/WEEK IN CONTACT WITH MENTOR", new ArrayList<>());
        allData.put("LONG TERM INTERVENTION: PROFESSION/TRADE FOR WHICH SERVICES WERE RECEIVED", new ArrayList<>());
        allData.put("WAS ESSENTIAL SKILLS AND APTITUDE TRAINING RECEIVED AS PART OF THIS SERVICE?", new ArrayList<>());
        allData.put("COMPUTER SKILLS", new ArrayList<>());
        allData.put("DOCUMENT USE", new ArrayList<>());
        allData.put("INTERPERSONAL SKILLS AND WORKPLACE CULTURE", new ArrayList<>());
        allData.put("LEADERSHIP TRAINING", new ArrayList<>());
        allData.put("NUMERACY", new ArrayList<>());
        allData.put("SHORT TERM INTERVENTION: SERVICE RECEIVED", new ArrayList<>());
        allData.put("SHORT TERM INTERVENTION: DATE (YYYY-MM-DD)", new ArrayList<>());
        allData.put("SHORT TERM INTERVENTION: SERVICE RECEIVED", new ArrayList<>());
        allData.put("SHORT TERM INTERVENTION: DATE (YYYY-MM-DD)", new ArrayList<>());
        allData.put("SHORT TERM INTERVENTION: SERVICE RECEIVED", new ArrayList<>());
        allData.put("SHORT TERM INTERVENTION: DATE (YYYY-MM-DD)", new ArrayList<>());
        allData.put("SHORT TERM INTERVENTION: SERVICE RECEIVED", new ArrayList<>());
        allData.put("SHORT TERM INTERVENTION: DATE (YYYY-MM-DD)", new ArrayList<>());
        allData.put("SHORT TERM INTERVENTION: SERVICE RECEIVED", new ArrayList<>());
        allData.put("SHORT TERM INTERVENTION: DATE (YYYY-MM-DD)", new ArrayList<>());
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
        allData.put("TIME SPENT WITH CLIENT/ADDRESSING CLIENT'S EMPLOYMENT NEEDS: HOURS", new ArrayList<>());
        allData.put("TIME SPENT WITH CLIENT/ADDRESSING CLIENT'S EMPLOYMENT NEEDS: MINUTES", new ArrayList<>());
        allData.put("REASON FOR UPDATE", new ArrayList<>());
    }

    protected IEmploymentBuilder parseIntervention(IEmploymentBuilder builder, int recordIndex) {
        String type = FieldParser.getFieldString(allData, "INTERVENTION TYPE", recordIndex);
        if (type.equals("Short Term")) {
            // TODO: some complicated crap here
        } else if (type.equals("Long Term")) {
            LongTermInterventionBuilder ltiBuilder = new LongTermInterventionBuilder();
            ltiBuilder.setServiceRecieved(FieldParser.getFieldString(allData, "LONG TERM INTERVENTION: INTERVENTION RECEIVED", recordIndex))
                .setStatus(FieldParser.getFieldString(allData, "LONG TERM INTERVENTION: STATUS OF INTERVENTION", recordIndex))
                .setReasonForLeave(FieldParser.getFieldString(allData, "LONG TERM INTERVENTION: REASON FOR LEAVING INTERVENTION", recordIndex))
                .setStartDate(FieldParser.getFieldString(allData, "LONG TERM INTERVENTION: START DATE (YYYY-MM-DD)", recordIndex))
                .setEndDate(FieldParser.getFieldString(allData, "LONG TERM INTERVENTION: END DATE (YYYY-MM-DD)", recordIndex))
                .setEmployerSize(FieldParser.getFieldInt(allData, "LONG TERM INTERVENTION: SIZE OF EMPLOYER", recordIndex))
                .setPlacement(FieldParser.getFieldString(allData, "LONG TERM INTERVENTION: PLACEMENT WAS", recordIndex))
                .setHoursPeerWeek(FieldParser.getFieldInt(allData, "LONG TERM INTERVENTION: HOURS PER WEEK", recordIndex))
                .setMetMentorAt(FieldParser.getFieldString(allData, "LONG TERM INTERVENTION: CLIENT MET MENTOR REGULARLY AT", recordIndex))
                .setAverageHoursPerWeek(FieldParser.getFieldString(allData, "LONG TERM INTERVENTION: AVERAGE HOURS/WEEK IN CONTACT WITH MENTOR", recordIndex))
                .setProfession(FieldParser.getFieldString(allData, "LONG TERM INTERVENTION: PROFESSION/TRADE FOR WHICH SERVICES WERE RECEIVED", recordIndex));
            builder.setLongTermIntervention(ltiBuilder.create());
        }
        return builder;
    }
    
    @Override
    public List<Object> parse() {
        List<Object> records = new ArrayList<>();
        for (int i = 0; i < numRecords; i++) {
            IEmploymentBuilder builder = (IEmploymentBuilder) parseServiceData(new EmploymentBuilder(), i);
            // parse yes-no responses
            builder.setEssentialSkills(parseEssentialSkills(i))
                .setSupportServices(parseSupportServices(i))
                .setChildCares(parseChildCares(i));
            // parse employment service data
            builder.setRegistration(FieldParser.getFieldBoolean(allData, "REGISTRATION IN AN EMPLOYMENT INTERVENTION", i))
                .setReferralTo(FieldParser.getFieldString(allData, "A REFERRAL TO", i))
                .setReferralDate(FieldParser.getFieldString(allData, "REFERRAL DATE (YYYY-MM-DD)", i))
                .setEmploymentStatus(FieldParser.getFieldString(allData, "EMPLOYMENT STATUS", i))
                .setEducationStatus(FieldParser.getFieldString(allData, "EDUCATION STATUS", i))
                .setOccupationCanada(FieldParser.getFieldString(allData, "OCCUPATION IN CANADA", i))
                .setOccupationIntended(FieldParser.getFieldString(allData, "INTENDED OCCUPATION", i))
                .setInterventionType(FieldParser.getFieldString(allData, "INTERVENTION TYPE", i))
                .setTimeSpentHours(FieldParser.getFieldInt(allData, "TIME SPENT WITH CLIENT/ADDRESSING CLIENT'S EMPLOYMENT NEEDS: HOURS", i))
                .setTimeSpentMinutes(FieldParser.getFieldInt(allData, "TIME SPENT WITH CLIENT/ADDRESSING CLIENT'S EMPLOYMENT NEEDS: MINUTES", i));
            // parse intervention
            builder = parseIntervention(builder, i);
            records.add(builder.create());
        }
        return records;
    }
}
