package com.devlopp.teq.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.devlopp.teq.service.commconn.CommunityConnectionsBuilder;
import com.devlopp.teq.service.commconn.ICommunityConnectionsBuilder;

public class CommunityConnectionsParser extends ServiceParser {
    public CommunityConnectionsParser() {
        allData = new HashMap<>();
        allData.put("PROCESSING DETAILS", new ArrayList<>());
        allData.put("UPDATE RECORD ID", new ArrayList<>());
        allData.put("UNIQUE IDENTIFIER", new ArrayList<>());
        allData.put("UNIQUE IDENTIFIER VALUE", new ArrayList<>());
        allData.put("DATE OF BIRTH (YYYY-MM-DD)", new ArrayList<>());
        allData.put("POSTAL CODE WHERE THE SERVICE WAS RECEIVED", new ArrayList<>());
        allData.put("LANGUAGE OF SERVICE", new ArrayList<>());
        allData.put("OFFICIAL LANGUAGE OF PREFERENCE", new ArrayList<>());
        allData.put("REFERRED BY", new ArrayList<>());
        allData.put("ACTIVITY UNDER WHICH CLIENT RECEIVED SERVICES", new ArrayList<>());
        allData.put("TYPE OF INSTITUTION/ORGANIZATION WHERE CLIENT RECEIVED SERVICES", new ArrayList<>());
        allData.put("TYPE OF EVENT ATTENDED", new ArrayList<>());
        allData.put("TYPE OF SERVICE", new ArrayList<>());
        allData.put("MAIN TOPIC/FOCUS OF THE SERVICE RECEIVED", new ArrayList<>());
        allData.put("SERVICE RECEIVED", new ArrayList<>());
        allData.put("NUMBER OF UNIQUE PARTICIPANTS", new ArrayList<>());
        allData.put("DID VOLUNTEERS FROM THE HOST COMMUNITY PARTICIPATE IN THE ACTIVITY", new ArrayList<>());
        allData.put("DIRECTED AT A SPECIFIC TARGET GROUP", new ArrayList<>());
        allData.put("TARGET GROUP: CHILDREN (0-14 YRS)", new ArrayList<>());
        allData.put("TARGET GROUP: YOUTH (15-24 YRS)", new ArrayList<>());
        allData.put("TARGET GROUP: SENIOR", new ArrayList<>());
        allData.put("TARGET GROUP: GENDER-SPECIFIC", new ArrayList<>());
        allData.put("TARGET GROUP: REFUGEES", new ArrayList<>());
        allData.put("TARGET GROUP: ETHNIC/CULTURAL/LINGUISTIC GROUP", new ArrayList<>());
        allData.put("TARGET GROUP: DEAF OR HARD OF HEARING", new ArrayList<>());
        allData.put("TARGET GROUP: BLIND OR PARTIALLY SIGHTED", new ArrayList<>());
        allData.put("TARGET GROUP: LESBIAN, GAY, BISEXUAL, TRANSGENDER, QUEER (LGBTQ)", new ArrayList<>());
        allData.put("TARGET GROUP: FAMILIES/PARENTS", new ArrayList<>());
        allData.put("TARGET GROUP: OTHER IMPAIRMENTS (PHYSICAL, MENTAL)", new ArrayList<>());
        allData.put("TARGET GROUP: CLIENTS WITH INTERNATIONAL TRAINING IN A REGULATED PROFESSION", new ArrayList<>());
        allData.put("TARGET GROUP: CLIENTS WITH INTERNATIONAL TRAINING IN A REGULATED TRADE", new ArrayList<>());
        allData.put("TARGET GROUP: OFFICIAL LANGUAGE MINORITIES", new ArrayList<>());
        allData.put("STATUS OF SERVICE", new ArrayList<>());
        allData.put("REASON FOR LEAVING SERVICE", new ArrayList<>());
        allData.put("START DATE (YYYY-MM-DD)", new ArrayList<>());
        allData.put("END DATE (YYYY-MM-DD)", new ArrayList<>());
        allData.put("PROJECTED END DATE (YYYY-MM-DD)", new ArrayList<>());
        allData.put("WAS ESSENTIAL SKILLS AND APTITUDES TRAINING RECEIVED AS PART OF THE SERVICE?", new ArrayList<>());
        allData.put("COMPUTER SKILLS", new ArrayList<>());
        allData.put("DOCUMENT USE", new ArrayList<>());
        allData.put("INTERPERSONAL SKILLS AND WORKPLACE CULTURE", new ArrayList<>());
        allData.put("LEADERSHIP TRAINING", new ArrayList<>());
        allData.put("LIFE SKILLS", new ArrayList<>());
        allData.put("NUMERACY", new ArrayList<>());
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
        allData.put("TOTAL LENGTH OF SERVICE: HOURS", new ArrayList<>());
        allData.put("TOTAL LENGTH OF SERVICE: MINUTES", new ArrayList<>());
        allData.put("REASON FOR UPDATE", new ArrayList<>());
    }

    @Override
    public List<Object> parse() {
        List<Object> records = new ArrayList<>();
        for (int i = 0; i < numRecords; i++) {
            ICommunityConnectionsBuilder builder = (ICommunityConnectionsBuilder) parseServiceData(
                    new CommunityConnectionsBuilder(), i);
            // add yes-no responses
            builder.setEssentialSkills(parseEssentialSkills(i))
            .setSupportServices(parseSupportServices(i))
            .setTargetGroups(parseTargetGroups(i))
            .setChildCares(parseChildCares(i));
            // build main community connections
            builder.setEventType(FieldParser.getFieldString(allData, "TYPE OF EVENT ATTENDED", i))
                    .setMainTopic(FieldParser.getFieldString(allData, "MAIN TOPIC/FOCUS OF THE SERVICE RECEIVED", i))
                    .setServiceReceived(FieldParser.getFieldString(allData, "SERVICE RECEIVED", i))
                    .setParticipants(FieldParser.getFieldString(allData, "NUMBER OF UNIQUE PARTICIPANTS", i))
                    .setVolunteers(FieldParser.getFieldBoolean(allData,
                            "DID VOLUNTEERS FROM THE HOST COMMUNITY PARTICIPATE IN THE ACTIVITY", i))
                    .setStatus(FieldParser.getFieldString(allData, "STATUS OF SERVICE", i))
                    .setReasonForLeave(FieldParser.getFieldString(allData, "REASON FOR LEAVING SERVICE", i))
                    .setEndDate(FieldParser.getFieldString(allData, "END DATE (YYYY-MM-DD)", i))
                    .setStartDate(FieldParser.getFieldString(allData, "START DATE (YYYY-MM-DD)", i))
                    .setProjectedEndDate(FieldParser.getFieldString(allData, "PROJECTED END DATE (YYYY-MM-DD)", i))
                    .setLengthHours(FieldParser.getFieldInt(allData, "TOTAL LENGTH OF SERVICE: HOURS", i))
                    .setLengthMinutes(FieldParser.getFieldInt(allData, "TOTAL LENGTH OF SERVICE: MINUTES", i));
            Object record = builder.create();
            records.add(record);
        }
        return records;
    }
}
