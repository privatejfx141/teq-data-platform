package com.devlopp.teq.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.devlopp.teq.service.courseexit.CourseExitBuilder;
import com.devlopp.teq.service.courseexit.ICourseExitBuilder;

public class CourseExitParser extends ServiceParser {
    public CourseExitParser() {
        allData = new HashMap<>();
        allData.put("PROCESSING DETAILS", new ArrayList<>());
        allData.put("UPDATE RECORD ID", new ArrayList<>());
        allData.put("UNIQUE IDENTIFIER TYPE", new ArrayList<>());
        allData.put("UNIQUE IDENTIFIER VALUE", new ArrayList<>());
        allData.put("CLIENT DATE OF BIRTH (YYYY-MM-DD)", new ArrayList<>());
        allData.put("COURSE CODE", new ArrayList<>());
        allData.put("CLIENT'S TRAINING STATUS", new ArrayList<>());
        allData.put("DATE CLIENT EXITED COURSE (YYYY-MM-DD)", new ArrayList<>());
        allData.put("REASON FOR EXITING COURSE", new ArrayList<>());
        allData.put("LISTENING CLB LEVEL", new ArrayList<>());
        allData.put("SPEAKING CLB LEVEL", new ArrayList<>());
        allData.put("READING CLB LEVEL", new ArrayList<>());
        allData.put("WRITING CLB LEVEL", new ArrayList<>());
        allData.put("WAS A CERTIFICATE ISSUED TO THE CLIENT?", new ArrayList<>());
        allData.put("LISTENING LEVEL INDICATED ON CERTIFICATE", new ArrayList<>());
        allData.put("SPEAKING LEVEL INDICATED ON CERTIFICATE", new ArrayList<>());
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
        allData.put("TRANSLATION LANGUAGE BETWEEN", new ArrayList<>());
        allData.put("TRANSLATION LANGUAGE AND", new ArrayList<>());
        allData.put("INTERPRETATION", new ArrayList<>());
        allData.put("BETWEEN", new ArrayList<>());
        allData.put("AND", new ArrayList<>());
        allData.put("CRISIS COUNSELLING", new ArrayList<>());
        allData.put("REASON FOR UPDATE", new ArrayList<>());
    }

    @Override
    public List<Object> parse() {
        List<Object> records = new ArrayList<>();
        for (int i = 0; i < numRecords; i++) {
            ICourseExitBuilder builder = (ICourseExitBuilder) parseServiceData(new CourseExitBuilder(), i);
            // parse yes-no responses
            builder.setSupportServices(parseSupportServices(i));
            builder.setChildCares(parseChildCares(i));
            // parse course exit data
            builder.setCourseCode(FieldParser.getFieldString(allData, "COURSE CODE", i))
                .setExitDate(FieldParser.getFieldString(allData, "DATE CLIENT EXITED COURSE (YYYY-MM-DD)", i))
                .setReason(FieldParser.getFieldString(allData, "REASON FOR EXITING COURSE", i))
                .setListeningLevel(FieldParser.getFieldString(allData, "LISTENING CLB LEVEL", i))
                .setReadingLevel(FieldParser.getFieldString(allData, "READING CLB LEVEL", i))
                .setSpeakingLevel(FieldParser.getFieldString(allData, "SPEAKING CLB LEVEL", i))
                .setWritingLevel(FieldParser.getFieldString(allData, "WRITING CLB LEVEL", i));
            records.add(builder.create());
        }
        return records;
    }
}
