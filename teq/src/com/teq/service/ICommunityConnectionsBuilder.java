package com.teq.service;

public interface ICommunityConnectionsBuilder {
    public ICommunityConnectionsBuilder setEventType(String eventType);

    public ICommunityConnectionsBuilder setMainTopic(String mainTopic);

    public ICommunityConnectionsBuilder setServiceReceived(String serviceReceived);

    public ICommunityConnectionsBuilder setParticipants(int participants);

    public ICommunityConnectionsBuilder setVolunteers(boolean hasVolunteers);

    public ICommunityConnectionsBuilder setStatus(String status);

    public ICommunityConnectionsBuilder setReasonForLeave(String reasonForLeave);

    public ICommunityConnectionsBuilder setStartDate(String startDate);

    public ICommunityConnectionsBuilder setEndDate(String endDate);

    public ICommunityConnectionsBuilder setProjectedEndDate(String projectedEndDate);

    public ICommunityConnectionsBuilder setLengthHours(int lengthHours);

    public ICommunityConnectionsBuilder setLengthMinutes(int lengthMinutes);
}
