package com.devlopp.teq.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.devlopp.teq.excel.ExcelReader;
import com.devlopp.teq.service.assessment.AssessmentBuilder;
import com.devlopp.teq.service.assessment.IAssessmentBuilder;

public class AssessmentParser extends ServiceParser {
    private ArrayList<ArrayList<String>> data;
    private int numRecords = 0;

    public AssessmentParser() {
        allData = new HashMap<>();
        allData.put("PROCESSING DETAILS", new ArrayList<>());
        allData.put("UPDATE RECORD ID", new ArrayList<>());
        allData.put("UNIQUE IDENTIFIER", new ArrayList<>());
        allData.put("UNIQUE IDENTIFIER VALUE", new ArrayList<>());
        allData.put("DATE OF BIRTH (YYYY-MM-DD)", new ArrayList<>());
        allData.put("POSTAL CODE WHERE THE SERVICE WAS RECEIVED", new ArrayList<>());
        allData.put("START DATE OF ASSESSMENT (YYYY-MM-DD)", new ArrayList<>());
        allData.put("LANGUAGE OF SERVICE", new ArrayList<>());
        allData.put("OFFICIAL LANGUAGE OF PREFERENCE", new ArrayList<>());
        allData.put("TYPE OF INSTITUTION/ORGANIZATION WHERE CLIENT RECEIVED SERVICES", new ArrayList<>());
        allData.put("REFERRED BY", new ArrayList<>());
        allData.put("INCREASE KNOWLEDGE OF: LIFE IN CANADA", new ArrayList<>());
        allData.put("INCREASE KNOWLEDGE OF: LIFE IN CANADA REFERRALS", new ArrayList<>());
        allData.put("INCREASE KNOWLEDGE OF: COMMUNITY AND GOVERNMENT SERVICES", new ArrayList<>());
        allData.put("INCREASE KNOWLEDGE OF: COMMUNITY AND GOVERNMENT SERVICES REFERRALS", new ArrayList<>());
        allData.put("INCREASE KNOWLEDGE OF: WORKING IN CANADA", new ArrayList<>());
        allData.put("INCREASE KNOWLEDGE OF: WORKING IN CANADA REFERRALS", new ArrayList<>());
        allData.put("INCREASE KNOWLEDGE OF: EDUCATION IN CANADA", new ArrayList<>());
        allData.put("INCREASE KNOWLEDGE OF: EDUCATION IN CANADA REFERRALS", new ArrayList<>());
        allData.put("INCREASE THE FOLLOWING: SOCIAL NETWORKS", new ArrayList<>());
        allData.put("INCREASE THE FOLLOWING: SOCIAL NETWORKS REFERRALS", new ArrayList<>());
        allData.put("INCREASE THE FOLLOWING: PROFESSIONAL NETWORKS", new ArrayList<>());
        allData.put("INCREASE THE FOLLOWING: PROFESSIONAL NETWORKS REFERRALS", new ArrayList<>());
        allData.put("INCREASE THE FOLLOWING: ACCESS TO LOCAL COMMUNITY SERVICES", new ArrayList<>());
        allData.put("INCREASE THE FOLLOWING: ACCESS TO LOCAL COMMUNITY SERVICES REFERRALS", new ArrayList<>());
        allData.put("INCREASE THE FOLLOWING: LEVEL OF COMMUNITY INVOLVEMENT", new ArrayList<>());
        allData.put("INCREASE THE FOLLOWING: LEVEL OF COMMUNITY INVOLVEMENT REFERRALS", new ArrayList<>());
        allData.put("IMPROVE LANGUAGE SKILLS", new ArrayList<>());
        allData.put("IMPROVE LANGUAGE SKILLS REFERRALS", new ArrayList<>());
        allData.put("IMPROVE LANGUAGE SKILLS TO", new ArrayList<>());
        allData.put("IMPROVE OTHER SKILLS", new ArrayList<>());
        allData.put("IMPROVE OTHER SKILLS REFERRALS", new ArrayList<>());
        allData.put("IMPROVE OTHER SKILLS TO", new ArrayList<>());
        allData.put("FIND EMPLOYMENT", new ArrayList<>());
        allData.put("FIND EMPLOYMENT REFERRALS", new ArrayList<>());
        allData.put("FIND EMPLOYMENT: TIMEFRAME", new ArrayList<>());
        allData.put("FIND EMPLOYMENT: MINIMUM ONE YEAR'S WORK EXPERIENCE?", new ArrayList<>());
        allData.put(
                "FIND EMPLOYMENT: INTENDS TO WORK IN AN OCCUPATION CORRESPONDING TO WHICH NATIONAL OCCUPATION CLASSIFICATION SKILL LEVEL?",
                new ArrayList<>());
        allData.put("FIND EMPLOYMENT: INTENDS TO OBTAIN CREDENTIAL RECOGNITION OR OBTAIN LICENSE TO WORK IN CANADA?",
                new ArrayList<>());
        allData.put("CLIENT INTENDS TO BECOME A CANADIAN CITIZEN?", new ArrayList<>());
        allData.put("SUPPORT SERVICES MAY BE REQUIRED", new ArrayList<>());
        allData.put("CARE FOR NEWCOMER CHILDREN", new ArrayList<>());
        allData.put("TRANSPORTATION", new ArrayList<>());
        allData.put("PROVISIONS FOR DISABILITIES", new ArrayList<>());
        allData.put("TRANSLATION", new ArrayList<>());
        allData.put("INTERPRETATION", new ArrayList<>());
        allData.put("CRISIS COUNSELLING", new ArrayList<>());
        allData.put("NON-IRCC PROGRAM SERVICES NEEDED", new ArrayList<>());
        allData.put("FOOD/CLOTHING/OTHER MATERIAL NEEDS", new ArrayList<>());
        allData.put("FOOD/CLOTHING/OTHER MATERIAL NEEDS REFERRALS", new ArrayList<>());
        allData.put("HOUSING/ACCOMMODATION", new ArrayList<>());
        allData.put("HOUSING/ACCOMMODATION REFERRALS", new ArrayList<>());
        allData.put("HEALTH/MENTAL HEALTH/WELL BEING", new ArrayList<>());
        allData.put("HEALTH/MENTAL HEALTH/WELL BEING REFERRALS", new ArrayList<>());
        allData.put("FINANCIAL", new ArrayList<>());
        allData.put("FINANCIAL REFERRALS", new ArrayList<>());
        allData.put("FAMILY SUPPORT", new ArrayList<>());
        allData.put("FAMILY SUPPORT REFERRALS", new ArrayList<>());
        allData.put("LANGUAGE (NON-IRCC)", new ArrayList<>());
        allData.put("LANGUAGE (NON-IRCC) REFERRALS", new ArrayList<>());
        allData.put("EDUCATION/SKILLS DEVELOPMENT", new ArrayList<>());
        allData.put("EDUCATION/SKILLS DEVELOPMENT REFERRALS", new ArrayList<>());
        allData.put("EMPLOYMENT-RELATED", new ArrayList<>());
        allData.put("EMPLOYMENT-RELATED REFERRALS", new ArrayList<>());
        allData.put("LEGAL INFORMATION AND SERVICES", new ArrayList<>());
        allData.put("LEGAL INFORMATION AND SERVICES REFERRALS", new ArrayList<>());
        allData.put("COMMUNITY SERVICES", new ArrayList<>());
        allData.put("COMMUNITY SERVICES REFERRALS", new ArrayList<>());
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
        allData.put("SETTLEMENT PLAN COMPLETED AND SHARED WITH CLIENT", new ArrayList<>());
        allData.put("END DATE OF ASSESSMENT (YYYY-MM-DD)", new ArrayList<>());
        allData.put("REASON FOR UPDATE", new ArrayList<>());
    }

    protected IAssessmentBuilder parseFindEmployment(IAssessmentBuilder builder, int recordIndex) {
        if (FieldParser.getFieldBoolean(allData, "FIND EMPLOYMENT", recordIndex)) {
            String timeFrame = FieldParser.getFieldString(allData, "FIND EMPLOYMENT: TIMEFRAME", recordIndex);
            String minExp = FieldParser.getFieldString(allData, "FIND EMPLOYMENT: MINIMUM ONE YEAR'S WORK EXPERIENCE?",
                    recordIndex);
            String skillLevel = FieldParser.getFieldString(allData,
                    "FIND EMPLOYMENT: INTENDS TO WORK IN AN OCCUPATION CORRESPONDING TO WHICH NATIONAL OCCUPATION CLASSIFICATION SKILL LEVEL?",
                    recordIndex);
            String intends = FieldParser.getFieldString(allData,
                    "FIND EMPLOYMENT: INTENDS TO OBTAIN CREDENTIAL RECOGNITION OR OBTAIN LICENSE TO WORK IN CANADA?",
                    recordIndex);
            return builder.setFindEmployment(timeFrame, minExp, skillLevel, intends);
        }
        return builder;
    }

    @Override
    public List<Object> parse() {
        List<Object> records = new ArrayList<>();
        for (int i = 0; i < numRecords; i++) {
            IAssessmentBuilder builder = (IAssessmentBuilder) parseServiceData(new AssessmentBuilder(), i);
            // parse yes-no responses
            builder.setSupportServices(parseSupportServices(i))
                .setChildCares(parseChildCares(i));
            // parse assessment details
            builder.setStartDate(FieldParser.getFieldString(allData, "START DATE OF ASSESSMENT (YYYY-MM-DD)", i))
                    .setLanguageGoal(FieldParser.getFieldString(allData, "IMPROVE LANGUAGE SKILLS TO", i))
                    .setOtherGoal(FieldParser.getFieldString(allData, "IMPROVE OTHER SKILLS TO", i))
                    .setIntendsCitizenship(
                            FieldParser.getFieldBoolean(allData, "CLIENT INTENDS TO BECOME A CANADIAN CITIZEN?", i))
                    .setReqSupportServices(FieldParser.getFieldBoolean(allData, "SUPPORT SERVICES MAY BE REQUIRED", i))
                    .setPlanComplete(FieldParser.getFieldBoolean(allData,
                            "SETTLEMENT PLAN COMPLETED AND SHARED WITH CLIENT", i));
            // parse find employment value
            builder = parseFindEmployment(builder, i);
            Object record = builder.create();
            records.add(record);
        }
        return records;
    }
}
