package com.teq.entities;

public interface ICommunityConnections {
	
	public ICommunityConnections setServiceId(int id);
	
	public ICommunityConnections setEventType(String EventType);
	
	public ICommunityConnections setMainTopic(String MainTopic);
	
	public ICommunityConnections setServiceReceived(String ServiceReceived);
	
	public ICommunityConnections setParticipants(int Participants);
	
	public ICommunityConnections setVolunteers(boolean Volunteers);
	
	public ICommunityConnections setStatus(String Status);
	
	public ICommunityConnections setReasonForLeave(String ReasonForLeave);
	
	public ICommunityConnections setStartDate(String StartDate);
	
	public ICommunityConnections setEndDate(String EndDate);
	
	public ICommunityConnections setProjectedEndDate(String ProjectedEndDate);
	
	public ICommunityConnections setLengthHours(int LengthHours);
	
	public ICommunityConnections setLengthMinutes(int LengthMinutes);

}
