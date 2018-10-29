package com.devlopp.teq.service;

public class EmploymentBuilder implements IEmploymentBuilder {
	
	
    private Employment employment;

    public EmploymentBuilder() {
    	employment = new Employment();
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
	public IEmploymentBuilder setEmploymentStatus(String employmentStatus) {
		employment.employmentStatus = employmentStatus;
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
	public IEmploymentBuilder setInterventionType(char intType) {
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

}
