package com.teq.course;

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
    protected List<String> schedules;
    protected List<String> supportServices;
    protected List<String> targetGroups;

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

    public List<String> getSupportServices() {
        return supportServices;
    }

    public List<String> getTargetGroups() {
        return targetGroups;
    }
}