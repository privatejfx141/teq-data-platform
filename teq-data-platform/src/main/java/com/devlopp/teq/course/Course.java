package com.devlopp.teq.course;

import java.util.ArrayList;
import java.util.List;

public class Course {
    protected String courseCode;
    protected String notes;
    protected boolean ongoingBasis;
    protected String language;
    protected String trainingFormat;
    protected String classLocation;
    protected float inpersonInstruct;
    protected float onlineInstruct;
    protected int numberOfSpots;
    protected int numberOfIRCC;
    protected String enrollmentType;
    protected String startDate;
    protected String endDate;
    protected int instructHours;
    protected int weeklyHours;
    protected int numWeeks;
    protected int numWeeksPerYear;
    protected String dominantFocus;
    protected List<String> schedules = new ArrayList<>();
    protected List<String> supportServices = new ArrayList<>();
    protected List<String> targetGroups = new ArrayList<>();
    protected List<String> materials = new ArrayList<>();
    protected CourseContact contact;

    public String getCourseCode() {
        return courseCode;
    }

    public String getNotes() {
        return notes;
    }

    public boolean isOngoingBasis() {
        return ongoingBasis;
    }

    public String getLanguage() {
        return language;
    }

    public String getTrainingFormat() {
        return trainingFormat;
    }

    public String getClassLocation() {
        return classLocation;
    }

    public float getInPercentInstruct() {
        return inpersonInstruct;
    }

    public float getOnlineInstruct() {
        return onlineInstruct;
    }

    public int getNumberOfSpots() {
        return numberOfSpots;
    }

    public int getNumberOfIRCC() {
        return numberOfIRCC;
    }

    public String getEnrollmentType() {
        return enrollmentType;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getInstructHours() {
        return instructHours;
    }

    public int getWeeklyHours() {
        return weeklyHours;
    }

    public int getNumWeeks() {
        return numWeeks;
    }

    public int getNumWeeksPerYear() {
        return numWeeksPerYear;
    }

    public String getDominantFocus() {
        return dominantFocus;
    }

    public List<String> getSchedules() {
        return schedules;
    }

    public void addSchedule(String schedule) {
        schedules.add(schedule);
    }

    public List<String> getSupportServices() {
        return supportServices;
    }

    public void addSupportService(String supportService) {
        schedules.add(supportService);
    }

    public List<String> getTargetGroups() {
        return targetGroups;
    }

    public void addTargetGroup(String targetGroup) {
        schedules.add(targetGroup);
    }
    
    public List<String> getMaterials() {
        return materials;
    }

    public void addMaterial(String material) {
        materials.add(material);
    }

    public CourseContact getContact() {
        return contact;
    }
}
