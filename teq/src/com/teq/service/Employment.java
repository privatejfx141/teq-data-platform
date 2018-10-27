package com.teq.service;

public class Employment extends Service {
    protected boolean registration;
    protected String referral;
    protected String date;
    protected String employmentStatus;
    protected String occupationCanada;
    protected String occupationIntended;
    protected char interventionType;
    protected int timeSpentHours;
    protected int timeSpentMinutes;

    public boolean getRegistration() {
        return registration;
    }

    public String getReferral() {
        return referral;
    }

    public String getDate() {
        return date;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public String getOccupationCanada() {
        return occupationCanada;
    }

    public String getOccupationIntended() {
        return occupationIntended;
    }

    public char getInterventionType() {
        return interventionType;
    }

    public int getTimeSpentHours() {
        return timeSpentHours;
    }

    public int getTimeSpentMinutes() {
        return timeSpentMinutes;
    }
}
