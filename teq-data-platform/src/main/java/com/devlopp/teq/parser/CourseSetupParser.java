package com.devlopp.teq.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.devlopp.teq.address.Address;
import com.devlopp.teq.address.AddressBuilder;
import com.devlopp.teq.course.Course;
import com.devlopp.teq.course.CourseBuilder;
import com.devlopp.teq.course.CourseContact;
import com.devlopp.teq.course.CourseContactBuilder;
import com.devlopp.teq.excel.ExcelReader;

public class CourseSetupParser implements TemplateParser {
    private HashMap<String, ArrayList<String>> allData;
    private int numRecords = 0;

    public CourseSetupParser() {
        allData = new HashMap<>();
        allData.put("PROCESSING DETAILS", new ArrayList<>());
        allData.put("UPDATE RECORD ID", new ArrayList<>());
        allData.put("COURSE CODE", new ArrayList<>());
        allData.put("NOTES", new ArrayList<>());
        allData.put("COURSE HELD ON AN ONGOING BASIS", new ArrayList<>());
        allData.put("OFFICIAL LANGUAGE OF COURSE", new ArrayList<>());
        allData.put("FORMAT OF TRAINING PROVIDED", new ArrayList<>());
        allData.put("CLASSES HELD AT", new ArrayList<>());
        allData.put("IN-PERSON INSTRUCTION (%)", new ArrayList<>());
        allData.put("ONLINE/DISTANCE INSTRUCTION (%)", new ArrayList<>());
        allData.put("TOTAL NUMBER OF SPOTS IN COURSE", new ArrayList<>());
        allData.put("NUMBER OF IRCC-FUNDED SPOTS IN COURSE", new ArrayList<>());
        allData.put("NEW STUDENTS CAN ENROL IN THE COURSE", new ArrayList<>());
        allData.put("SUPPORT SERVICES AVAILABLE FOR CLIENT IN THIS COURSE", new ArrayList<>());
        allData.put("CARE FOR NEWCOMER CHILDREN", new ArrayList<>());
        allData.put("TRANSPORTATION", new ArrayList<>());
        allData.put("PROVISIONS FOR DISABILITIES", new ArrayList<>());
        allData.put("COURSE START DATE (YYYY-MM-DD)", new ArrayList<>());
        allData.put("COURSE END DATE (YYYY-MM-DD)", new ArrayList<>());
        allData.put("SCHEDULE: MORNING", new ArrayList<>());
        allData.put("SCHEDULE: AFTERNOON", new ArrayList<>());
        allData.put("SCHEDULE: EVENING", new ArrayList<>());
        allData.put("SCHEDULE: WEEKEND", new ArrayList<>());
        allData.put("SCHEDULE: ANYTIME", new ArrayList<>());
        allData.put("SCHEDULE: ONLINE", new ArrayList<>());
        allData.put("INSTRUCTIONAL HOURS PER CLASS", new ArrayList<>());
        allData.put("CLASSES PER WEEK", new ArrayList<>());
        allData.put("WEEKS OF INSTRUCTION", new ArrayList<>());
        allData.put("WEEKS OF INSTRUCTION PER YEAR", new ArrayList<>());
        allData.put("DOMINANT FOCUS OF THE COURSE", new ArrayList<>());
        allData.put("COURSE DIRECTED AT A SPECIFIC TARGET GROUP", new ArrayList<>());
        allData.put("CHILDREN (0-14 YRS)", new ArrayList<>());
        allData.put("YOUTH (15-24 YRS)", new ArrayList<>());
        allData.put("SENIOR", new ArrayList<>());
        allData.put("GENDER-SPECIFIC", new ArrayList<>());
        allData.put("REFUGEES", new ArrayList<>());
        allData.put("OFFICIAL LANGUAGE MINORITIES", new ArrayList<>());
        allData.put("ETHNIC/CULTURAL/LINGUISTIC GROUP", new ArrayList<>());
        allData.put("DEAF OR HARD OF HEARING", new ArrayList<>());
        allData.put("BLIND OR PARTIALLY SIGHTED", new ArrayList<>());
        allData.put("CLIENTS WITH OTHER IMPAIRMENTS (PHYSICAL, MENTAL)", new ArrayList<>());
        allData.put("LESBIAN, GAY, BISEXUAL, TRANSGENDER, QUEER (LGBTQ)", new ArrayList<>());
        allData.put("FAMILIES/PARENTS", new ArrayList<>());
        allData.put("CLIENTS WITH INTERNATIONAL TRAINING IN A REGULATED PROFESSION", new ArrayList<>());
        allData.put("CLIENTS WITH INTERNATIONAL TRAINING IN A REGULATED TRADE", new ArrayList<>());
        allData.put("MATERIALS USED IN COURSE", new ArrayList<>());
        allData.put("CITIZENSHIP PREPARATION", new ArrayList<>());
        allData.put("PBLA LANGUAGE COMPANION", new ArrayList<>());
        allData.put("CONTACT NAME", new ArrayList<>());
        allData.put("STREET NUMBER", new ArrayList<>());
        allData.put("STREET NAME", new ArrayList<>());
        allData.put("STREET TYPE", new ArrayList<>());
        allData.put("STREET DIRECTION", new ArrayList<>());
        allData.put("UNIT/SUITE", new ArrayList<>());
        allData.put("PROVINCE", new ArrayList<>());
        allData.put("CITY", new ArrayList<>());
        allData.put("POSTAL CODE (A#A#A#)", new ArrayList<>());
        allData.put("TELEPHONE NUMBER (###-###-####)", new ArrayList<>());
        allData.put("TELEPHONE EXTENSION", new ArrayList<>());
        allData.put("EMAIL ADDRESS", new ArrayList<>());
        allData.put("LISTENING SKILL LEVEL 1", new ArrayList<>());
        allData.put("LISTENING SKILL LEVEL 2", new ArrayList<>());
        allData.put("LISTENING SKILL LEVEL 3", new ArrayList<>());
        allData.put("LISTENING SKILL LEVEL 4", new ArrayList<>());
        allData.put("LISTENING SKILL LEVEL 5", new ArrayList<>());
        allData.put("LISTENING SKILL LEVEL 6", new ArrayList<>());
        allData.put("LISTENING SKILL LEVEL 7", new ArrayList<>());
        allData.put("LISTENING SKILL LEVEL 8", new ArrayList<>());
        allData.put("LISTENING SKILL LEVEL 9", new ArrayList<>());
        allData.put("LISTENING SKILL LEVEL 10", new ArrayList<>());
        allData.put("LISTENING SKILL LEVEL 11", new ArrayList<>());
        allData.put("LISTENING SKILL LEVEL 12", new ArrayList<>());
        allData.put("SPEAKING SKILL LEVEL 1", new ArrayList<>());
        allData.put("SPEAKING SKILL LEVEL 2", new ArrayList<>());
        allData.put("SPEAKING SKILL LEVEL 3", new ArrayList<>());
        allData.put("SPEAKING SKILL LEVEL 4", new ArrayList<>());
        allData.put("SPEAKING SKILL LEVEL 5", new ArrayList<>());
        allData.put("SPEAKING SKILL LEVEL 6", new ArrayList<>());
        allData.put("SPEAKING SKILL LEVEL 7", new ArrayList<>());
        allData.put("SPEAKING SKILL LEVEL 8", new ArrayList<>());
        allData.put("SPEAKING SKILL LEVEL 9", new ArrayList<>());
        allData.put("SPEAKING SKILL LEVEL 10", new ArrayList<>());
        allData.put("SPEAKING SKILL LEVEL 11", new ArrayList<>());
        allData.put("SPEAKING SKILL LEVEL 12", new ArrayList<>());
        allData.put("READING SKILL LEVEL 1", new ArrayList<>());
        allData.put("READING SKILL LEVEL 2", new ArrayList<>());
        allData.put("READING SKILL LEVEL 3", new ArrayList<>());
        allData.put("READING SKILL LEVEL 4", new ArrayList<>());
        allData.put("READING SKILL LEVEL 5", new ArrayList<>());
        allData.put("READING SKILL LEVEL 6", new ArrayList<>());
        allData.put("READING SKILL LEVEL 7", new ArrayList<>());
        allData.put("READING SKILL LEVEL 8", new ArrayList<>());
        allData.put("READING SKILL LEVEL 9", new ArrayList<>());
        allData.put("READING SKILL LEVEL 10", new ArrayList<>());
        allData.put("READING SKILL LEVEL 11", new ArrayList<>());
        allData.put("READING SKILL LEVEL 12", new ArrayList<>());
        allData.put("READING SKILL LEVEL 13", new ArrayList<>());
        allData.put("READING SKILL LEVEL 14", new ArrayList<>());
        allData.put("READING SKILL LEVEL 15", new ArrayList<>());
        allData.put("READING SKILL LEVEL 16", new ArrayList<>());
        allData.put("READING SKILL LEVEL 17", new ArrayList<>());
        allData.put("WRITING SKILL LEVEL 1", new ArrayList<>());
        allData.put("WRITING SKILL LEVEL 2", new ArrayList<>());
        allData.put("WRITING SKILL LEVEL 3", new ArrayList<>());
        allData.put("WRITING SKILL LEVEL 4", new ArrayList<>());
        allData.put("WRITING SKILL LEVEL 5", new ArrayList<>());
        allData.put("WRITING SKILL LEVEL 6", new ArrayList<>());
        allData.put("WRITING SKILL LEVEL 7", new ArrayList<>());
        allData.put("WRITING SKILL LEVEL 8", new ArrayList<>());
        allData.put("WRITING SKILL LEVEL 9", new ArrayList<>());
        allData.put("WRITING SKILL LEVEL 10", new ArrayList<>());
        allData.put("WRITING SKILL LEVEL 11", new ArrayList<>());
        allData.put("WRITING SKILL LEVEL 12", new ArrayList<>());
        allData.put("WRITING SKILL LEVEL 13", new ArrayList<>());
        allData.put("WRITING SKILL LEVEL 14", new ArrayList<>());
        allData.put("WRITING SKILL LEVEL 15", new ArrayList<>());
        allData.put("WRITING SKILL LEVEL 16", new ArrayList<>());
        allData.put("WRITING SKILL LEVEL 17", new ArrayList<>());
    }

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

    private List<String> parseTargetGroups(int recordIndex) {
        List<String> targetGroups = new ArrayList<>();
        if (allData.get("CHILDREN (0-14 YRS)").get(recordIndex).equals("Yes"))
            targetGroups.add("Children (0-14 yrs)");
        if (allData.get("YOUTH (15-24 YRS)").get(recordIndex).equals("Yes"))
            targetGroups.add("Youth (15-24 yrs)");
        if (allData.get("SENIOR").get(recordIndex).equals("Yes"))
            targetGroups.add("Senior");
        if (allData.get("GENDER-SPECIFIC").get(recordIndex).equals("Yes"))
            targetGroups.add("Gender-specific");
        if (allData.get("REFUGEES").get(recordIndex).equals("Yes"))
            targetGroups.add("Refugees");
        if (allData.get("OFFICIAL LANGUAGE MINORITIES").get(recordIndex).equals("Yes"))
            targetGroups.add("Official language minorities");
        if (allData.get("ETHNIC/CULTURAL/LINGUISTIC GROUP").get(recordIndex).equals("Yes"))
            targetGroups.add("Ethnic/cultural/linguistic group");
        if (allData.get("DEAF OR HARD OF HEARING").get(recordIndex).equals("Yes"))
            targetGroups.add("Deaf or Hard of Hearing");
        if (allData.get("BLIND OR PARTIALLY SIGHTED").get(recordIndex).equals("Yes"))
            targetGroups.add("Blind or Partially Sighted");
        if (allData.get("CLIENTS WITH OTHER IMPAIRMENTS (PHYSICAL, MENTAL)").get(recordIndex).equals("Yes"))
            targetGroups.add("Clients with other impairments (physical, mental)");
        if (allData.get("LESBIAN, GAY, BISEXUAL, TRANSGENDER, QUEER (LGBTQ)").get(recordIndex).equals("Yes"))
            targetGroups.add("Lesbian, Gay, Bisexual, Transgender, Queer (LGBTQ)");
        if (allData.get("FAMILIES/PARENTS").get(recordIndex).equals("Yes"))
            targetGroups.add("Families/Parents");
        if (allData.get("CLIENTS WITH INTERNATIONAL TRAINING IN A REGULATED PROFESSION").get(recordIndex).equals("Yes"))
            targetGroups.add("Clients with international training in a regulated profession");
        if (allData.get("CLIENTS WITH INTERNATIONAL TRAINING IN A REGULATED TRADE").get(recordIndex).equals("Yes"))
            targetGroups.add("Clients with international training in a regulated trade");
        return targetGroups;
    }
    
    private List<String> parseSchedules(int recordIndex) {
        List<String> schedules = new ArrayList<>();
        if (allData.get("SCHEDULE: MORNING").get(recordIndex).equals("Yes"))
            schedules.add("Morning");
        if (allData.get("SCHEDULE: AFTERNOON").get(recordIndex).equals("Yes"))
            schedules.add("Afternoon");
        if (allData.get("SCHEDULE: EVENING").get(recordIndex).equals("Yes"))
            schedules.add("Evening");
        if (allData.get("SCHEDULE: WEEKEND").get(recordIndex).equals("Yes"))
            schedules.add("Weekend");
        if (allData.get("SCHEDULE: ONLINE").get(recordIndex).equals("Yes"))
            schedules.add("Online");
        return schedules;
    }
    
    private List<String> parseMaterials(int recordIndex) {
        List<String> materials = new ArrayList<>();
        if (allData.get("CITIZENSHIP PREPARATION").get(recordIndex).equals("Yes"))
            materials.add("Citizenship preparation");
        if (allData.get("PBLA LANGUAGE COMPANION").get(recordIndex).equals("Yes"))
            materials.add("PBLA language companion");
        return materials;
    }
    
    private List<String> parseSupportServices(int recordIndex) {
        List<String> supportServices = new ArrayList<>();
        if (allData.get("CARE FOR NEWCOMER CHILDREN").get(recordIndex).equals("Yes"))
            supportServices.add("Care for Newcomer Children");
        if (allData.get("TRANSPORTATION").get(recordIndex).equals("Yes"))
            supportServices.add("Transportation");
        if (allData.get("PROVISIONS FOR DISABILITIES").get(recordIndex).equals("Yes"))
            supportServices.add("Provisions for Disabilities");
        return supportServices;
    }
    
    @Override
    public List<Object> parse() {
        List<Object> courses = new ArrayList<>();
        for (int i = 0; i < numRecords; i++) {
            // create address
            Address address = new AddressBuilder()
                    .setUnitNumber(FieldParser.getFieldInt(allData, "UNIT/SUITE", i))
                    .setStreetNumber(FieldParser.getFieldInt(allData, "STREET NUMBER", i))
                    .setStreetName(FieldParser.getFieldString(allData, "STREET NAME", i))
                    .setStreetType(FieldParser.getFieldString(allData, "STREET TYPE", i))
                    .setStreetDirection(FieldParser.getFieldString(allData, "STREET DIRECTION", i))
                    .setCity(FieldParser.getFieldString(allData, "CITY", i))
                    .setProvince(FieldParser.getFieldString(allData, "PROVINCE", i))
                    .create();
            // create contact
            CourseContact contact = new CourseContactBuilder()
                    .setContactName(FieldParser.getFieldString(allData, "CONTACT NAME", i))
                    .setAddress(address)
                    .setEmailAddress(FieldParser.getFieldString(allData, "EMAIL ADDRESS", i))
                    .setTelephoneNumber(FieldParser.getFieldString(allData, "TELEPHONE NUMBER (###-###-####)", i))
                    .setTelephoneExt(FieldParser.getFieldString(allData, "TELEPHONE EXTENSION", i))
                    .create();
            // create course
            Course course = new CourseBuilder()
                    .setCourseCode(FieldParser.getFieldString(allData, "COURSE CODE", i))
                    .setNotes(FieldParser.getFieldString(allData, "NOTES", i))
                    .setOngoingBasis(FieldParser.getFieldString(allData, "COURSE HELD ON AN ONGOING BASIS", i).equals("Yes"))
                    .setLanguage(FieldParser.getFieldString(allData, "OFFICIAL LANGUAGE OF COURSE", i))
                    .setTrainingFormat(FieldParser.getFieldString(allData, "FORMAT OF TRAINING PROVIDED", i))
                    .setClassesHeldAt(FieldParser.getFieldString(allData, "CLASSES HELD AT", i))
                    .setInpersonInstruct(FieldParser.parseFloat(FieldParser.getFieldString(allData, "IN-PERSON INSTRUCTION (%)", i)))
                    .setOnlineInstruct(FieldParser.parseFloat(FieldParser.getFieldString(allData, "ONLINE/DISTANCE INSTRUCTION (%)", i)))
                    .setNumberOfSpots(FieldParser.getFieldInt(allData, "TOTAL NUMBER OF SPOTS IN COURSE", i))
                    .setNumberOfIRCC(FieldParser.getFieldInt(allData, "NUMBER OF IRCC-FUNDED SPOTS IN COURSE", i))
                    .setEnrollmentType(FieldParser.getFieldString(allData, "NEW STUDENTS CAN ENROL IN THE COURSE", i))
                    .setStartDate(FieldParser.getFieldString(allData, "COURSE START DATE (YYYY-MM-DD)", i))
                    .setEndDate(FieldParser.getFieldString(allData, "COURSE END DATE (YYYY-MM-DD)", i))
                    .setInstructHours(FieldParser.getFieldInt(allData, "INSTRUCTIONAL HOURS PER CLASS", i))
                    .setWeeklyHours(FieldParser.getFieldInt(allData, "CLASSES PER WEEK", i))
                    .setNumWeeks(FieldParser.getFieldInt(allData, "WEEKS OF INSTRUCTION", i))
                    .setNumWeeksPerYear(FieldParser.getFieldInt(allData, "WEEKS OF INSTRUCTION PER YEAR", i))
                    .setDominantFocus(FieldParser.getFieldString(allData, "DOMINANT FOCUS OF THE COURSE", i))
                    .setCourseContact(contact)
                    .setSchedules(parseSchedules(i))
                    .setSupportServices(parseSupportServices(i))
                    .setTargetGroups(parseTargetGroups(i))
                    .setMaterials(parseMaterials(i))
                    .create();
            courses.add(course);
        }
        return courses;
    }
}
