package com.teq.service;

public class CommunityConnectionsBuilder implements ICommunityConnectionsBuilder {
	
    private CommunityConnections communityconnections;

    public CommunityConnectionsBuilder() {
    	communityconnections = new CommunityConnections();
    }

	@Override
	public ICommunityConnectionsBuilder setEventType(String eventType) {
		communityconnections.eventType = eventType;
		return this;
	}

	@Override
	public ICommunityConnectionsBuilder setMainTopic(String mainTopic) {
		communityconnections.mainTopic = mainTopic;
		return this;
	}

	@Override
	public ICommunityConnectionsBuilder setServiceReceived(String serviceReceived) {
		communityconnections.serviceReceived = serviceReceived;
		return this;
	}
	@Override
	public ICommunityConnectionsBuilder setParticipants(int participants) {
		communityconnections.participants = participants;
		return this;
	}

	@Override
	public ICommunityConnectionsBuilder setVolunteers(boolean hasVolunteers) {
		communityconnections.hasVolunteers = hasVolunteers;
		return this;
	}

	@Override
	public ICommunityConnectionsBuilder setStatus(String status) {
		communityconnections.status = status;
		return this;
	}

	@Override
	public ICommunityConnectionsBuilder setReasonForLeave(String reasonForLeave) {
		communityconnections.reasonForLeave = reasonForLeave;
		return this;
	}

	@Override
	public ICommunityConnectionsBuilder setStartDate(String startDate) {
		communityconnections.startDate = startDate;
		return this;
	}

	@Override
	public ICommunityConnectionsBuilder setEndDate(String endDate) {
		communityconnections.endDate = endDate;
		return this;
	}

	@Override
	public ICommunityConnectionsBuilder setProjectedEndDate(String projectedEndDate) {
		communityconnections.projectedEndDate = projectedEndDate;
		return this;
	}

	@Override
	public ICommunityConnectionsBuilder setLengthHours(int lengthHours) {
		communityconnections.lengthHours = lengthHours;
		return this;
	}

	@Override
	public ICommunityConnectionsBuilder setLengthMinutes(int lengthMinutes) {
		communityconnections.lengthMinutes = lengthMinutes;
		return this;
	}

}
