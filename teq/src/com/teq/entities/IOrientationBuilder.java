package com.teq.entities;

public interface IOrientationBuilder extends IServiceBuilder{
	
	
	public IEmploymentBuilder setServiceId(int serviceId);
	
	public IEmploymentBuilder setServiceReceived(String ServiceReceived);
	
	public IEmploymentBuilder setTotalLength(String TotalLength);
	
	public IEmploymentBuilder setLengthHours(int LengthHours);

	public IEmploymentBuilder setLengthMinutes(int LengthMinutes);
	
	public IEmploymentBuilder setNumberOfClients(int NumberOfClients);
	
	public IEmploymentBuilder setEndDate(String EndDate);
	
}
