package com.devlopp.teq.service.employment;

import com.devlopp.teq.service.ServiceBuilder;

public class EmploymentBuilder extends ServiceBuilder implements IEmploymentBuilder {
    private Employment employment;

    public EmploymentBuilder() {
        service = new Employment();
        setServiceType("E");
        employment = (Employment) service;
    }

    @Override
    public IEmploymentBuilder setRegistration(boolean status) {
        employment.registration = status;
        return this;
    }

    @Override
    public IEmploymentBuilder setReferralTo(String referral) {
        employment.referral = referral;
        return this;
    }

    @Override
    public IEmploymentBuilder setReferralDate(String date) {
        employment.date = date;
        return this;
    }

    @Override
    public IEmploymentBuilder setEmploymentStatus(String status) {
        employment.employmentStatus = status;
        return this;
    }

    @Override
    public IEmploymentBuilder setEducationStatus(String status) {
        employment.educationStatus = status;
        return this;
    }

    @Override
    public IEmploymentBuilder setOccupationCanada(String occupation) {
        employment.occupationCanada = occupation;
        return this;
    }

    @Override
    public IEmploymentBuilder setOccupationIntended(String occupation) {
        employment.occupationIntended = occupation;
        return this;
    }

    @Override
    public IEmploymentBuilder setInterventionType(String intType) {
        employment.interventionType = intType;
        return this;
    }

    @Override
    public IEmploymentBuilder setTimeSpentHours(int hours) {
        employment.timeSpentHours = hours;
        return this;
    }

    @Override
    public IEmploymentBuilder setTimeSpentMinutes(int minutes) {
        employment.timeSpentMinutes = minutes;
        return this;
    }

    @Override
    public IEmploymentBuilder setLongTermIntervention(LongTermIntervention lti) {
        employment.lti = lti;
        return this;
    }

    @Override
    public Employment create() {
        return employment;
    }
}
