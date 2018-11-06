package com.devlopp.teq.service.employment;

import java.util.ArrayList;
import java.util.List;

import com.devlopp.teq.service.Service;

public class Employment extends Service {
    protected boolean registration;
    protected String referral;
    protected String date;
    protected String employmentStatus;
    protected String educationStatus;
    protected String occupationCanada;
    protected String occupationIntended;
    protected String interventionType;
    protected int timeSpentHours;
    protected int timeSpentMinutes;

    protected LongTermIntervention lti;
    protected List<ShortTermIntervention> sti = new ArrayList<>();

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

    public String getEducationStatus() {
        return educationStatus;
    }

    public String getOccupationCanada() {
        return occupationCanada;
    }

    public String getOccupationIntended() {
        return occupationIntended;
    }

    public String getInterventionType() {
        return interventionType;
    }

    public int getTimeSpentHours() {
        return timeSpentHours;
    }

    public int getTimeSpentMinutes() {
        return timeSpentMinutes;
    }

    public LongTermIntervention getLongTermIntervention() {
        return lti;
    }

    public List<ShortTermIntervention> getShortTermIntervention() {
        return sti;
    }

    public void addShortTermIntervention(ShortTermIntervention intervention) {
        sti.add(intervention);
    }

}
