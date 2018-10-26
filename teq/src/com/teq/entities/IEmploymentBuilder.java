package com.teq.entities;

public interface IEmploymentBuilder extends IServiceBuilder{
	
	public IEmploymentBuilder setServiceId(int id);
	
	public IEmploymentBuilder setRegistration(boolean status);
	
	public IEmploymentBuilder setReferralTo(String referral);
	
	public IEmploymentBuilder setReferralDate(String date);

	public IEmploymentBuilder setEmploymentStatus(String employment_status);
	
	public IEmploymentBuilder setOccupationCanada(String occupation);
	
	public IEmploymentBuilder setOccupationIntended(String occupation);
	
	public IEmploymentBuilder setInterventionType(char intType);
	
	public IEmploymentBuilder setTimeSpentHours(int hours);
	
	public IEmploymentBuilder setTimeSpentMinutes(int minutes);
	
}
