package com.devlopp.teq.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.devlopp.teq.service.courseenroll.CourseEnrollBuilder;
import com.devlopp.teq.service.courseenroll.ICourseEnrollBuilder;

public class CourseEnrollParser extends ServiceParser {
    public CourseEnrollParser() {
        allData = new HashMap<>();
        allData.put("PROCESSING DETAILS", new ArrayList<>());
        allData.put("UPDATE RECORD ID", new ArrayList<>());
        allData.put("UNIQUE IDENTIFIER TYPE", new ArrayList<>());
        allData.put("UNIQUE IDENTIFIER VALUE", new ArrayList<>());
        allData.put("CLIENT DATE OF BIRTH (YYYY-MM-DD)", new ArrayList<>());
        allData.put("POSTAL CODE WHERE THE SERVICE WAS RECEIVED", new ArrayList<>());
        allData.put("COURSE CODE", new ArrayList<>());
        allData.put("DATE OF CLIENT'S FIRST CLASS (YYYY-MM-DD)", new ArrayList<>());
        allData.put("OFFICIAL LANGUAGE OF PREFERENCE", new ArrayList<>());
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
            ICourseEnrollBuilder builder = (ICourseEnrollBuilder) parseServiceData(new CourseEnrollBuilder(), i);
            // parse yes-no responses
            builder.setSupportServices(parseSupportServices(i));
            builder.setChildCares(parseChildCares(i));
            // parse course enroll service data
            builder.setCourseCode(FieldParser.getFieldString(allData, "COURSE CODE", i)).setFirstClassDate(
                    FieldParser.getFieldString(allData, "DATE OF CLIENT'S FIRST CLASS (YYYY-MM-DD)", i));
            records.add(builder.create());
        }
        return records;
    }
}
